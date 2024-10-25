package servicio.katana.aplication.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import servicio.ticket.aplication.dto.TicketDto;
import servicio.ticket.aplication.ports.outbound.ITicketRead;
import servicio.ticket.domain.services.ITicketEventService;

@Component
public class TicketReadUseCase implements ITicketRead {

   private final ITicketEventService iTicketEventService;

    @Autowired
    public TicketReadUseCase(ITicketEventService iTicketEventService) {
        this.iTicketEventService = iTicketEventService;
    }

    @Override
    public Page<TicketDto> findAll(Pageable pageable) {
        return iTicketEventService.getAllTickets(pageable);
    }

    @Override
    public TicketDto findOneById(Long id) {
        return iTicketEventService.findTicketById(id).get();
    }

}
