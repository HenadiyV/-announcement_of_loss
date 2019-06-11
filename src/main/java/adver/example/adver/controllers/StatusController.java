package adver.example.adver.controllers;


import adver.example.adver.models.Status;
import adver.example.adver.repos.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin_status/status")
public class StatusController {
    final StatusRepository statusRepo;

    @Autowired
    public StatusController(StatusRepository statusRepo) {
        this.statusRepo = statusRepo;
    }

    @GetMapping
    public List<Status> list(){
        return (List<Status>) statusRepo.findAll();
    }

    @GetMapping("{id}")
    public Status getOne(@PathVariable("id") Status status) {
        return status;
    }

    @PostMapping
    public Status create(@RequestBody Status status) { ;
        return statusRepo.save(status);

    }

    @PutMapping("{id}")
    public Status update(@RequestBody Status newStatus, @PathVariable int id) {

        return statusRepo.findById(id)
                .map(Status -> {
                    Status.setName(newStatus.getName());
                    return statusRepo.save(Status);
                }).get();

    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Status status) {
        statusRepo.delete(status);
    }
    //добавил коммит
}