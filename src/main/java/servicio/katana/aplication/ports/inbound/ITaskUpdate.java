package servicio.katana.aplication.ports.inbound;

import servicio.katana.aplication.dto.TaskDto;


import java.util.Optional;

public interface ITaskUpdate {
    Optional<TaskDto> updateTask(Long id, TaskDto updatedTask);
}
