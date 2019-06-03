package adver.example.adver.controllers;

import adver.example.adver.models.User;
import adver.example.adver.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/*
*@autor Hennadiy Voroboiv 
27.05.2019
7:21
*/
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String MyMain() {
        return "main";
    }

    @PostMapping(path = "/add") // Map ONLY GET Requests
    public @ResponseBody
    String addNewUser(@RequestParam String name
            , @RequestParam String email) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path = "/list")
    public String listUser(Map<String, Object> model) {
        Iterable<User> users = userRepository.findAll();
        model.put("listUser", users);
        return "list_users";
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }

    @PostMapping("filter")
    public String filtr(@RequestParam String filter, Map<String, Object> model) {
        Iterable<User> listName;
        if (filter != null && !filter.isEmpty()) {
            listName = userRepository.findByName(filter);
        } else {
            listName = userRepository.findAll();
        }
        model.put("listName", listName);
        return "main";
    }
}
