package servicio.katana.domain.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import servicio.katana.aplication.dto.TaskDto;


import java.util.Optional;

public interface ITaskEventService {
   Page<TaskDto> getAllTickets(Pageable pageable);
   Optional<TaskDto> findTicketById(Long id);
}
