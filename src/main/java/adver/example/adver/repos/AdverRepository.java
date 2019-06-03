package adver.example.adver.repos;

import adver.example.adver.models.Adver;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdverRepository extends CrudRepository<Adver, Integer> {
    List<Adver> findByStatus_Id(int id);
    List<Adver> findById(int id);
    List<Adver> findByStatusName(String name);

}
