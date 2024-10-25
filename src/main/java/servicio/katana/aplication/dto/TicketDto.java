package servicio.katana.aplication.dto;

import lombok.Data;
import servicio.ticket.domain.auxiliary.Estatus;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link servicio.ticket.domain.entities.Ticket}
 */

@Data
public class TicketDto implements Serializable {
    Long id;
    String usuario;
    LocalDateTime fechaCreacion;
    LocalDateTime fechaActualizacion;
    Estatus estatus;
}