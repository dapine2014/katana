package servicio.katana.aplication.ports.outbound;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import servicio.katana.aplication.dto.TaskDto;


public interface ITaskRead {
    Page<TaskDto> findAll(Pageable pageable);
    TaskDto findOneById(Long id);
}
