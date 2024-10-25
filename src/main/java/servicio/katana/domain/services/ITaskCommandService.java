package servicio.katana.domain.services;

import servicio.katana.aplication.dto.TaskDto;


import java.util.Optional;

public interface ITaskCommandService {
    TaskDto createTicket(TaskDto ticketDto);
    Optional<TaskDto> updateTicket(Long id,TaskDto ticketDto);
    void deleteTicketById(Long id);
}
