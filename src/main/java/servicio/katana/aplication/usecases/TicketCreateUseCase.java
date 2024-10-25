package servicio.katana.aplication.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import servicio.ticket.aplication.dto.TicketDto;
import servicio.ticket.aplication.ports.inbound.ITicketCreate;
import servicio.ticket.domain.services.ITicketCommandService;

@Component
public class TicketCreateUseCase implements ITicketCreate {

    private final ITicketCommandService ticketCommandService;

    @Autowired
    public TicketCreateUseCase(ITicketCommandService ticketCommandService) {
        this.ticketCommandService = ticketCommandService;
    }

    @Override
    public TicketDto TicketCreate(TicketDto ticketDto) {
        return ticketCommandService.createTicket(ticketDto);
    }
}
