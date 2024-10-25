package servicio.katana.aplication.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import servicio.ticket.aplication.dto.TicketDto;
import servicio.ticket.aplication.ports.inbound.ITicketUpdate;
import servicio.ticket.domain.services.ITicketCommandService;

import java.util.Optional;

@Component
public class TicketUpdateUseCase implements ITicketUpdate {

    private final ITicketCommandService ticketCommandService;

    @Autowired
    public TicketUpdateUseCase(ITicketCommandService ticketCommandService) {
        this.ticketCommandService = ticketCommandService;
    }

    @Override
    public Optional<TicketDto> updateTicket(Long id, TicketDto updatedTicket) {
        return ticketCommandService.updateTicket(id, updatedTicket);
    }
}
