package ru.mpt.convertor.controller.backend;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mpt.convertor.model.Role;
import ru.mpt.convertor.model.User;
import ru.mpt.convertor.service.UserService;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/admin/user")
public class AdminUserController {

    private final static String BACK_USER_BASE = "/admin/user";
    private final UserService userService;

    public AdminUserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String userList(Model model) {
        model.addAttribute("userList", userService.findAll());
        return "userList";
    }

    @RequestMapping(method = RequestMethod.GET, value = "{userId}")
    public String userEditForm(@PathVariable Integer userId, Model model) {
        Optional<User> user = userService.findById(userId);
        user.ifPresent(value -> model.addAttribute("user", value));
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public String saveUser(Model model,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String surname,
            @RequestParam String firstName,
            @RequestParam String secondName,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user) {
        if (email.trim().isEmpty() || password.trim().isEmpty() || firstName.trim().isEmpty()){
            model.addAttribute("message", "Одно из обязательных полей не заполнено.");
            return "userEdit";
        }
        user.setEmail(email.trim());
        user.setPassword(password.trim());
        user.setFirstName(firstName.trim());
        user.setSecondName(secondName.trim());
        user.setSurname(surname.trim());

        Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        user.getRoles().add(Role.USER);
        userService.save(user);
        return "redirect:/admin/user";
    }
}
