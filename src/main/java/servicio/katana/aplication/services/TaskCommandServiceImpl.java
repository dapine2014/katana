package servicio.katana.aplication.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import servicio.ticket.aplication.dto.TicketDto;
import servicio.ticket.domain.entities.Ticket;
import servicio.ticket.domain.repositories.TicketRepository;
import servicio.ticket.domain.services.ITicketCommandService;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TaskCommandServiceImpl implements ITicketCommandService {

    private final TicketRepository ticketRepository;
    private final ModelMapper mapper;

    @Autowired
    public TaskCommandServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
        mapper = new ModelMapper();
    }

    @Override
    public TicketDto createTicket(TicketDto ticketDto) {
        ticketDto.setFechaCreacion(LocalDateTime.now());
        ticketDto.setFechaActualizacion(LocalDateTime.now());
        Ticket ticket = ticketRepository.saveAndFlush(mapper.map(ticketDto, Ticket.class));
        ticketDto.setId(ticket.getId());

        return ticketDto;
    }

    @Override
    public Optional<TicketDto> updateTicket(Long id,TicketDto ticketDto) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        if (optionalTicket.isPresent()) {
            Ticket existingTicket = optionalTicket.get();
            existingTicket.setFechaActualizacion(LocalDateTime.now());
            existingTicket.setUsuario(ticketDto.getUsuario());
            existingTicket.setEstatus(ticketDto.getEstatus());
            ticketRepository.saveAndFlush(existingTicket);

            return Optional.of(mapper.map(existingTicket, TicketDto.class));
        }

        return Optional.empty();
    }

    @Override
    public void deleteTicketById(Long id) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        optionalTicket.ifPresent(ticketRepository::delete);
    }
}
