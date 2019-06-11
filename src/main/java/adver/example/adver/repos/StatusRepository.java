package adver.example.adver.repos;

import adver.example.adver.models.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StatusRepository extends CrudRepository<Status, Integer> {
    List<Status> findByAll();
}
