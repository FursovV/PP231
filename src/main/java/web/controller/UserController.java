package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping()
    public String showAll(Model model) {
        List<User> users = userService.getAllUser();
        model.addAttribute("users", users);
        return "list";
    }

    @GetMapping("/add")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        return "add";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") @Valid User user,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add";
        }
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/update")
    public String updateUser(@RequestParam(value = "id") long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "/update";
    }

    @PatchMapping("/update")
    public String updateUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "update";
        }
        userService.updateUser(user.getId(), user);
        return "redirect:/users";
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam(value = "id") long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}





