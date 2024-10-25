package servicio.katana.aplication.usecases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import servicio.katana.aplication.dto.TaskDto;
import servicio.katana.domain.services.ITaskCommandService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TaskCreateUseCaseTest {

    @Mock
    private ITaskCommandService taskCommandServiceMock;

    @InjectMocks
    private TaskCreateUseCase taskCreateUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testTaskCreate() {
        TaskDto inputDto = new TaskDto(); //Establecer los elementos en este DTO como se requiera para la prueba
        TaskDto outputDto = new TaskDto(); //Establecer los elementos en este DTO como se espera que sean luego de la operación

        when(taskCommandServiceMock.createTask(any(TaskDto.class))).thenReturn(outputDto);

        TaskDto result = taskCreateUseCase.TaskCreate(inputDto);

        verify(taskCommandServiceMock, times(1)).createTask(inputDto);
        assertEquals(outputDto, result);
    }
}