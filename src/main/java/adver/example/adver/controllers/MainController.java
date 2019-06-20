package adver.example.adver.controllers;

import adver.example.adver.models.Role;
import adver.example.adver.models.User;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping("/admin_category/")
    public String Category()
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            Role role= ((User)principal).getRole();
            if(!role.getName().equals("admin"))
                return "admin_page";
        }
        return "category";
    }

    @RequestMapping("/admin_city/")
    public String getCityView() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            Role role= ((User)principal).getRole();
            if(!role.getName().equals("admin"))
                return "admin_page";
        }
        return "city";
    }

    @RequestMapping("/admin_role/")
    public String getRoleView() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            Role role= ((User)principal).getRole();
            if(!role.getName().equals("admin"))
                return "admin_page";
        }

        return "role";
    }

    @RequestMapping("/admin_status/")
    public String getStatusView() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            Role role= ((User)principal).getRole();
            if(!role.getName().equals("admin"))
                return "admin_page";
        }

        return "status";
    }

    @RequestMapping("/admin")
    public String getStartAdminView() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
           Role role= ((User)principal).getRole();
           if(role.getName().equals("admin"))
            return "admin_page";
        } else {
            return "index";
        }
        return "index";
    }
}
