package servicio.katana.domain.services;

import servicio.katana.aplication.dto.TaskDto;


import java.util.Optional;

public interface ITaskCommandService {
    TaskDto createTask(TaskDto taskDto);
    Optional<TaskDto> updateTask(Long id, TaskDto taskDto);
    void deleteTaskById(Long id);
}
