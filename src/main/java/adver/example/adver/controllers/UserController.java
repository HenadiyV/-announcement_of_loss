package adver.example.adver.controllers;

import adver.example.adver.dto.MessageUserDTO;
import adver.example.adver.models.Message;
import adver.example.adver.models.User;
import adver.example.adver.repos.MessageRepository;
import adver.example.adver.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

/*
*@autor Hennadiy Voroboiv 
27.05.2019
*/
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;

    @GetMapping
    public String MyMain() {
        return "main";
    }

    @PostMapping(path = "/add")
    public @ResponseBody
    String addNewUser(@RequestParam String name
            , @RequestParam String email) {
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
        Iterable<User> listName=new ArrayList<>();
        if (filter != null && !filter.isEmpty()) {
            User us =userRepository.findByName(filter);;

            ((ArrayList<User>) listName).add(us);
        } else {
            listName = userRepository.findAll();
        }
        model.put("listName", listName);
        return "main";
    }

    @GetMapping(path = "/list_message_to")
    public String listUserMessageTo(@AuthenticationPrincipal User user,Map<String, Object> model) {
        Iterable<Message> usersTo = messageRepository.findByTo_Id(user.getId());
        ArrayList<MessageUserDTO> listUserMessage=new ArrayList<>();
        for(Message m:usersTo){
        MessageUserDTO userMessage=new MessageUserDTO();
        userMessage.setToUser(m.getId());
        userMessage.setTextMessage(m.getTextMessage());
       User us= userRepository.findById(m.getFrom().getId());
        userMessage.setFromUser(us.getName());
            listUserMessage.add(userMessage);
        }
        model.put("usersTo", listUserMessage);
        return "list_message_to";
    }

    @GetMapping(path = "/list_message_from")
    public String listUserMessageFrom(@AuthenticationPrincipal User user,Map<String, Object> model) {
        Iterable<Message> usersFrom = messageRepository.findByFrom_Id(user.getId());
        model.put("usersFrom", usersFrom);
        return "list_message_from";
    }

    @PostMapping(path = "/messageUser")
    public String listUserMessageDelete(@RequestParam("userMess")int id) {
        messageRepository.deleteById(id);
        return "main";
    }
}
