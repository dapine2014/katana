package servicio.katana.aplication.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import servicio.ticket.aplication.ports.inbound.ITicketDelete;
import servicio.ticket.domain.services.ITicketCommandService;

@Component
public class TicketDeleteUseCase implements ITicketDelete {

    private final ITicketCommandService ticketCommandService;

    @Autowired
    public TicketDeleteUseCase(ITicketCommandService ticketCommandService) {
        this.ticketCommandService = ticketCommandService;
    }


    @Override
    public void deleteTicket(Long id) {
      ticketCommandService.deleteTicketById(id);
    }
}
