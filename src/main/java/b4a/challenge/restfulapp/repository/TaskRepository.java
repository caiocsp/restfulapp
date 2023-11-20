package b4a.challenge.restfulapp.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import b4a.challenge.restfulapp.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findById(Long id);

    List<Task> findByTaskStatus(Integer id);


    @Query(value = "SELECT * FROM Task WHERE CAST(Deadline AS Date) = CAST(:deadline AS Date)", nativeQuery = true)
    List<Task> findByDeadline(@Param("deadline") Timestamp deadline);


}
