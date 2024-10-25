package servicio.katana.aplication.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import servicio.ticket.aplication.dto.TicketDto;
import servicio.ticket.domain.entities.Ticket;
import servicio.ticket.domain.repositories.TicketRepository;
import servicio.ticket.domain.services.ITicketEventService;
import java.util.Optional;

@Service
public class TaskEventServiceImpl implements ITicketEventService {

    private final TicketRepository ticketRepository;
    private final ModelMapper mapper;

    @Autowired
    public TaskEventServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
        this.mapper = new ModelMapper();
    }


    @Override
    public Page<TicketDto> getAllTickets(Pageable pageable) {

        Page<Ticket> tickets = ticketRepository.findAll(pageable);
        return tickets.map(ticket -> mapper.map(ticket, TicketDto.class));
    }

    @Override
    public Optional<TicketDto> findTicketById(Long id) {
        return Optional.of(mapper.map(ticketRepository.findById(id), TicketDto.class));
    }
}
