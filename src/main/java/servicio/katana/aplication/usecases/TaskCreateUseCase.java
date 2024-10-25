package servicio.katana.aplication.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import servicio.katana.aplication.dto.TaskDto;
import servicio.katana.aplication.ports.inbound.ITaskCreate;
import servicio.katana.domain.services.ITaskCommandService;


@Component
public class TaskCreateUseCase implements ITaskCreate {

    private final ITaskCommandService taskCommandService;

    @Autowired
    public TaskCreateUseCase(ITaskCommandService taskCommandService) {
        this.taskCommandService = taskCommandService;
    }

    @Override
    public TaskDto TaskCreate(TaskDto taskDto) {
        return taskCommandService.createTask(taskDto);
    }
}
