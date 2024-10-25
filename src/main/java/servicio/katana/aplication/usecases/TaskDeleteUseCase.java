package servicio.katana.aplication.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import servicio.katana.aplication.ports.inbound.ITaskDelete;
import servicio.katana.domain.services.ITaskCommandService;


@Component
public class TaskDeleteUseCase implements ITaskDelete {

    private final ITaskCommandService taskCommandService;

    @Autowired
    public TaskDeleteUseCase(ITaskCommandService taskCommandService) {
        this.taskCommandService = taskCommandService;
    }


    @Override
    public void deleteTask(Long id) {
        taskCommandService.deleteTaskById(id);
    }
}
