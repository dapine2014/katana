package servicio.katana.aplication.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import servicio.katana.aplication.dto.TaskDto;
import servicio.katana.domain.entities.Task;
import servicio.katana.domain.repositories.TaskRepository;
import servicio.katana.domain.services.ITaskEventService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskEventServiceImpl implements ITaskEventService {

    private final TaskRepository ticketRepository;
    private final ModelMapper mapper;

    @Autowired
    public TaskEventServiceImpl(TaskRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
        this.mapper = new ModelMapper();
    }


    @Override
    public Page<TaskDto> getAllTasks(Pageable pageable) {

        Page<Task> Tasks = ticketRepository.findAll(pageable);
        return Tasks.map(task -> mapper.map(task, TaskDto.class));
    }

    @Override
    public Optional<TaskDto> findTasksById(Long id) {
        return Optional.of(mapper.map(ticketRepository.findById(id), TaskDto.class));
    }

    @Override
    public List<TaskDto> findTasks() {
        return ticketRepository.findAll().stream()
                .map(task -> mapper.map(task, TaskDto.class))
                .collect(Collectors.toList());
    }

}
