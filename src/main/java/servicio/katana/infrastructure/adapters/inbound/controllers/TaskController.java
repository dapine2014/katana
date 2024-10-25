package servicio.katana.infrastructure.adapters.inbound.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import servicio.katana.aplication.dto.TaskDto;
import servicio.katana.aplication.ports.inbound.ITaskCreate;
import servicio.katana.aplication.ports.inbound.ITaskDelete;
import servicio.katana.aplication.ports.inbound.ITaskUpdate;
import servicio.katana.aplication.ports.outbound.ITaskRead;


import java.util.Optional;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    private final ITaskCreate taskCreator;
    private final ITaskUpdate taskUpdater;
    private final ITaskDelete taskDeleter;
    private final ITaskRead taskReader;

    @Autowired
    public TaskController(ITaskCreate taskCreator, ITaskUpdate taskUpdater, ITaskDelete taskDeleter, ITaskRead taskReader) {
        this.taskCreator = taskCreator;
        this.taskUpdater = taskUpdater;
        this.taskDeleter = taskDeleter;
        this.taskReader = taskReader;
    }


    @PostMapping
    @Operation(summary = "ENPOINT DE CREACION DE TASK")
    public ResponseEntity<Object> createTicket(@RequestBody TaskDto taskDto) {
        TaskDto tasked = taskCreator.TaskCreate(taskDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(tasked);
    }

    @PutMapping("/{id}")
    @Operation(summary = "ENPOINT DE ACTUALIZACION DE TASK")
    public ResponseEntity<TaskDto> updateTicket(@PathVariable Long id, @RequestBody TaskDto updatedTaskDto) {
        Optional<TaskDto> updatedTicket = taskUpdater.updateTask(id, updatedTaskDto);
        return updatedTicket.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "ENPOINT DE  BORRADO DE TASK POR ID")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        taskDeleter.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "ENPOINT DE CONSULTA DE TASK 'PAGINADO'")
    public ResponseEntity<Page<TaskDto>> getAllTickets(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TaskDto> tickets = taskReader.findAll(pageable);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/{id}")
    @Operation(summary = "ENPOINT DE CONSULTA UN TASK POR SU ID")
    public ResponseEntity<TaskDto> getTicketById(@PathVariable Long id) {
        TaskDto ticket = taskReader.findOneById(id);
        return Optional.ofNullable(ticket)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
