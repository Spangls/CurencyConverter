package ru.mpt.convertor.controller.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.mpt.convertor.model.Role;
import ru.mpt.convertor.model.User;
import ru.mpt.convertor.repos.UserRepo;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        if (user.getEmail().trim().isEmpty() || user.getPassword().trim().isEmpty() ||  user.getFirstName().trim().isEmpty()){
            model.put("message", "Не заполнено одно из обязательных полей.");
            return "registration";
        }

        User userFromDb = userRepo.findByEmail(user.getEmail().trim());
        if (userFromDb != null) {
            model.put("message", "Пользователь с такой почтой уже существует.");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return "redirect:/login";
    }
}
