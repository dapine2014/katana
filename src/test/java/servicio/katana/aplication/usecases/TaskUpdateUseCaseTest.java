package servicio.katana.aplication.usecases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import servicio.katana.aplication.dto.TaskDto;
import servicio.katana.domain.services.ITaskCommandService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskUpdateUseCaseTest {

    @Mock
    private ITaskCommandService taskCommandServiceMock;

    @InjectMocks
    private TaskUpdateUseCase taskUpdateUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void updateTask() {
        Long id = 1L;
        TaskDto updatedTask = new TaskDto(); //Establecer los elementos en este DTO como se requiera para la prueba
        TaskDto expectedTask = new TaskDto(); //Establecer los elementos en este DTO como se espera que sean luego de la operación

        when(taskCommandServiceMock.updateTask(id, updatedTask)).thenReturn(Optional.of(expectedTask));

        Optional<TaskDto> result = taskUpdateUseCase.updateTask(id, updatedTask);

        verify(taskCommandServiceMock, times(1)).updateTask(id, updatedTask);
        assertEquals(Optional.of(expectedTask), result);
    }
}