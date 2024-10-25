package servicio.katana.infrastructure.adapters.inbound.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import servicio.ticket.aplication.dto.TicketDto;
import servicio.ticket.aplication.ports.inbound.ITicketCreate;
import servicio.ticket.aplication.ports.inbound.ITicketDelete;
import servicio.ticket.aplication.ports.inbound.ITicketUpdate;
import servicio.ticket.aplication.ports.outbound.ITicketRead;

import java.util.Optional;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final ITicketCreate ticketCreator;
    private final ITicketUpdate ticketUpdater;
    private final ITicketDelete ticketDeleter;
    private final ITicketRead ticketReader;

    @Autowired
    public TicketController(ITicketCreate ticketCreator, ITicketUpdate ticketUpdater, ITicketDelete ticketDeleter, ITicketRead ticketReader) {
        this.ticketCreator = ticketCreator;
        this.ticketUpdater = ticketUpdater;
        this.ticketDeleter = ticketDeleter;
        this.ticketReader = ticketReader;
    }

    @PostMapping
    @Operation(summary = "ENPOINT DE CREACION DE TICKET")
    public ResponseEntity<TicketDto> createTicket(@RequestBody TicketDto ticketDto) {
        TicketDto createdTicket = ticketCreator.TicketCreate(ticketDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTicket);
    }

    @PutMapping("/{id}")
    @Operation(summary = "ENPOINT DE ACTUALIZACION DE TICKET")
    public ResponseEntity<TicketDto> updateTicket(@PathVariable Long id, @RequestBody TicketDto updatedTicketDto) {
        Optional<TicketDto> updatedTicket = ticketUpdater.updateTicket(id, updatedTicketDto);
        return updatedTicket.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "ENPOINT DE  BORRADO DE TICKET POR ID")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketDeleter.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "ENPOINT DE CONSULTA DE TICKETS 'PAGINADO'")
    public ResponseEntity<Page<TicketDto>> getAllTickets(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TicketDto> tickets = ticketReader.findAll(pageable);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/{id}")
    @Operation(summary = "ENPOINT DE CONSULTA UN TICKET POR SU ID")
    public ResponseEntity<TicketDto> getTicketById(@PathVariable Long id) {
        TicketDto ticket = ticketReader.findOneById(id);
        return Optional.ofNullable(ticket)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
