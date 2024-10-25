package servicio.katana.domain.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import servicio.katana.domain.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}