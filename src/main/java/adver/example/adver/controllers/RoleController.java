package adver.example.adver.controllers;


import adver.example.adver.DTO.RoleDto;
import adver.example.adver.models.Role;
import adver.example.adver.repos.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin_role/role")
public class RoleController {
    final RoleRepository roleRepo;

    @Autowired
    public RoleController(RoleRepository roleRepo) {
        this.roleRepo = roleRepo;
    }

    @GetMapping
    public List<Role> getListRoles(){
        RoleDto ST=new RoleDto((List<Role>)roleRepo.findAll());
        return ST.getListRoles();
    }

    @GetMapping("{id}")
    public Role getOne(@PathVariable("id") Role Role) {
        return Role;
    }

    @PostMapping
    public Role createRole(@RequestBody Role Role) { ;
        return roleRepo.save(Role);

    }

    @PutMapping("{id}")
    public Role updateRole(@RequestBody Role newRole, @PathVariable int id) {

        return roleRepo.findById(id)
                .map(Role -> {
                    Role.setName(newRole.getName());
                    return roleRepo.save(Role);
                }).get();

    }

    @DeleteMapping("{id}")
    public void deleteRole(@PathVariable("id") Role Role) {
        roleRepo.delete(Role);
    }
}