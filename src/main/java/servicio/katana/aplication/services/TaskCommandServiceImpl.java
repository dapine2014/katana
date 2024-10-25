package servicio.katana.aplication.services;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import servicio.katana.aplication.dto.TaskDto;
import servicio.katana.domain.entities.Task;
import servicio.katana.domain.repositories.TaskRepository;
import servicio.katana.domain.services.ITaskCommandService;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TaskCommandServiceImpl implements ITaskCommandService {

    private final TaskRepository taskRepository;
    private final ModelMapper mapper;

    @Autowired
    public TaskCommandServiceImpl(TaskRepository task) {
        this.taskRepository = task;
        mapper = new ModelMapper();
    }

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        taskDto.setFechaCreacion(LocalDateTime.now());
        taskDto.setFechaActualizacion(LocalDateTime.now());
        Task task = taskRepository.saveAndFlush( mapper.map(taskDto, Task.class));
        taskDto.setId(task.getId());

        return taskDto;
    }

    @Override
    public Optional<TaskDto> updateTask(Long id, TaskDto taskDto) {
        Optional<Task> optionalTicket = taskRepository.findById(id);
        if (optionalTicket.isPresent()) {
            Task existingTask = optionalTicket.get();
            existingTask.setFechaActualizacion(LocalDateTime.now());
            existingTask.setTitle(taskDto.getTitle());
            existingTask.setDescription(taskDto.getDescription());
            existingTask.setEstatus(taskDto.getEstatus());
            taskRepository.saveAndFlush(existingTask);

            return Optional.of(mapper.map(existingTask, TaskDto.class));
        }

        return Optional.empty();
    }

    @Override
    public void deleteTaskById(Long id) {
        Optional<Task> optionalTicket = taskRepository.findById(id);
        optionalTicket.ifPresent(taskRepository::delete);
    }
}
