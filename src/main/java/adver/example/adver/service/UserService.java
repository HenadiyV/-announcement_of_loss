package adver.example.adver.service;


import adver.example.adver.models.Role;
import adver.example.adver.models.User;
import adver.example.adver.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *05.06.2019
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
   private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return userRepository.findByName(name);
    }
    public  boolean addUser(User user) {
        User userFromDb = userRepository.findByName(user.getName());

        if (userFromDb != null) {
            return false;
        }
Role rl=new Role();
rl.setId(2);
rl.setName("user");
        user.setRole(rl);
        userRepository.save(user);

        return true;
    }

}
