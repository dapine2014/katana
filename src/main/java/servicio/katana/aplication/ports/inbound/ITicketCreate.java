package servicio.katana.aplication.ports.inbound;

import servicio.ticket.aplication.dto.TicketDto;

public interface ITicketCreate {
    TicketDto TicketCreate(TicketDto ticketDto);
}
