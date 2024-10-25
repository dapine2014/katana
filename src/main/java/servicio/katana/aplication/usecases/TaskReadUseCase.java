package servicio.katana.aplication.usecases;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import servicio.katana.aplication.dto.TaskDto;
import servicio.katana.aplication.ports.outbound.ITaskRead;
import servicio.katana.domain.services.ITaskEventService;


@Component
public class TaskReadUseCase implements ITaskRead {

   private final ITaskEventService iTaskEventService;

   @Autowired
    public TaskReadUseCase(ITaskEventService iTaskEventService) {
        this.iTaskEventService = iTaskEventService;
    }


    @Override
    public Page<TaskDto> findAll(Pageable pageable) {
        return iTaskEventService.getAllTasks(pageable);
    }

    @Override
    public TaskDto findOneById(Long id) {
        return iTaskEventService.findTasksById(id).get();
    }

}
