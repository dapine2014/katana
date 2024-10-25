package servicio.katana.aplication.ports.inbound;


import servicio.katana.aplication.dto.TaskDto;

public interface ITaskCreate {
    TaskDto TaskCreate(TaskDto taskDto);
}
