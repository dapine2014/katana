package servicio.katana.domain.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import servicio.katana.aplication.dto.TaskDto;


import java.util.List;
import java.util.Optional;

public interface ITaskEventService {
   Page<TaskDto> getAllTasks(Pageable pageable);
   Optional<TaskDto> findTasksById(Long id);
   List<TaskDto> findTasks();
}
