package servicio.katana.aplication.ports.outbound;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import servicio.ticket.aplication.dto.TicketDto;

public interface ITicketRead {
    Page<TicketDto> findAll(Pageable pageable);
    TicketDto findOneById(Long id);
}
