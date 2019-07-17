package adver.example.adver.repos;

import adver.example.adver.models.Adver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdverRepository extends CrudRepository<Adver, Integer> {
    Page<Adver> findByStatus_Id(int id, Pageable pageable);

    Adver findById(int id);

    List<Adver> findByStatusName(String name);

    List<Adver> findByCity_IdAndCategory_IdAndStatus_Id(int city, int category, int id);

    Page<Adver> findByCity_IdAndStatus_Id(int city, int id, Pageable pageable);

    Page<Adver> findByCategory_IdAndStatus_Id(int category, int id, Pageable pageable);
    List<Adver> findAll();

}
