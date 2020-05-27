package ru.mpt.convertor.controller.frontend;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mpt.convertor.model.User;
import ru.mpt.convertor.service.MailSender;
import ru.mpt.convertor.service.UserService;

import java.security.Principal;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final MailSender mailSender;

    public UserController(UserService userService, MailSender mailSender){
        this.userService = userService;
        this.mailSender = mailSender;
    }

    @RequestMapping(method = RequestMethod.GET, value = "reset")
    public String resetForm() {
        return "passwordReset";
    }

    @RequestMapping(method = RequestMethod.POST, value = "reset")
    public String sendResetCode(String email, Model model) {
        User user = userService.findByEmail(email);
        if (user == null) {
            model.addAttribute("message", "Пользователь не найден.");
            return "passwordReset";
        }
        user.setPasswordResetCode(UUID.randomUUID().toString());
        userService.save(user);
        String message = String.format(
                "Доброго времени суток, %s! \n" +
                        "Для перехода на страницу восстановления пароля перейдите по ссылке: http://localhost:8080/user/saveNew/%s",
                user.getFirstName(),
                user.getPasswordResetCode()
        );
        mailSender.send(user.getEmail(), "Код восстановления", message);
        model.addAttribute("checkEmail", true);
        return "passwordReset";
    }

    @RequestMapping(method = RequestMethod.GET, value = "saveNew/{code}")
    public String saveForm(Model model, @PathVariable String code) {
        User user = userService.findByPasswordResetCode(code);
        if (user == null) {
            model.addAttribute("message", "Пользователь не найден.");
            return "passwordReset";
        }
        model.addAttribute("userId", user.getId());
        return "passwordReset";
    }

    @RequestMapping(method = RequestMethod.POST, value = "saveNew")
    public String savePassword(@RequestParam(name = "userId") User user, String password) {
        if (user == null)
            return "redirect:/login";
        user.setPasswordResetCode(null);
        user.setPassword(password);
        userService.save(user);
        return "redirect:/login";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/edit")
    public String editUser(Principal principal, Model model){
        User user = userService.findByEmail(principal.getName());
        if (user == null)
            return "redirect:/login";
        model.addAttribute("user", user);
        model.addAttribute("customEdit", true);
        return "userEdit";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/edit")
    public String editUser(Principal principal, Model model,
                           @RequestParam String email,
                           @RequestParam String password,
                           @RequestParam String surname,
                           @RequestParam String firstName,
                           @RequestParam String secondName){
        User user = userService.findByEmail(principal.getName());
        if (email.trim().isEmpty() || password.trim().isEmpty() || firstName.trim().isEmpty()){
            model.addAttribute("message", "Одно из обязательных полей не заполнено.");
            return "userEdit";
        }
        user.setEmail(email.trim());
        user.setPassword(password.trim());
        user.setFirstName(firstName.trim());
        user.setSecondName(secondName.trim());
        user.setSurname(surname.trim());
        model.addAttribute("user", user);
        return "userEdit";
    }
}
