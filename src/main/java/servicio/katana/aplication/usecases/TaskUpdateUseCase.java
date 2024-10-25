package servicio.katana.aplication.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import servicio.katana.aplication.dto.TaskDto;
import servicio.katana.aplication.ports.inbound.ITaskUpdate;
import servicio.katana.domain.services.ITaskCommandService;


import java.util.Optional;

@Component
public class TaskUpdateUseCase implements ITaskUpdate {

    private final ITaskCommandService ticketCommandService;

    @Autowired
    public TaskUpdateUseCase(ITaskCommandService ticketCommandService) {
        this.ticketCommandService = ticketCommandService;
    }


    @Override
    public Optional<TaskDto> updateTask(Long id, TaskDto updatedTask) {
        return ticketCommandService.updateTask(id, updatedTask);
    }
}
