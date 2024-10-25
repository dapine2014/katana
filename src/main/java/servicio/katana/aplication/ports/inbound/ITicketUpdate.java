package servicio.katana.aplication.ports.inbound;

import servicio.ticket.aplication.dto.TicketDto;

import java.util.Optional;

public interface ITicketUpdate {
    Optional<TicketDto> updateTicket (Long id, TicketDto updatedTicket);
}
