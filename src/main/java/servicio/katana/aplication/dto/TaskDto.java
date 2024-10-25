package servicio.katana.aplication.dto;

import lombok.*;
import servicio.katana.domain.auxiliary.Estatus;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link servicio.katana.domain.entities.Task}
 */

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskDto implements Serializable {
   private Long id;
   private  String title;
   private String description;
   private Estatus estatus;
   private LocalDateTime fechaCreacion;
   private  LocalDateTime fechaActualizacion;
}