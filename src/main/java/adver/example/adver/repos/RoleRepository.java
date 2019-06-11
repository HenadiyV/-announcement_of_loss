package adver.example.adver.repos;

import adver.example.adver.models.Adver;
import adver.example.adver.models.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    //добавил коммит
    List<Role> findByAll();
}
