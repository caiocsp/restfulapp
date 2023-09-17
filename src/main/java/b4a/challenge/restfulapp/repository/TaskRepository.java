package b4a.challenge.restfulapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import b4a.challenge.restfulapp.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findById(Long id);

    List<Task> findByTaskStatus(Integer id);
}
