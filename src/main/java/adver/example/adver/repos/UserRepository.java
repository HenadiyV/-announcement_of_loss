package adver.example.adver.repos;

import adver.example.adver.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findByName(String name);
}