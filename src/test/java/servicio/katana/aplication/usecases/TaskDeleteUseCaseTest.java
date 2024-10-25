package servicio.katana.aplication.usecases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import servicio.katana.domain.services.ITaskCommandService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class TaskDeleteUseCaseTest {
    @Mock
    private ITaskCommandService taskCommandServiceMock;

    @InjectMocks
    private TaskDeleteUseCase taskDeleteUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testDeleteTask() {
        Long id = 1L;

        //No necesitamos establecer un comportamiento para taskCommandServiceMock.deleteTaskById
        // ya que el método no devuelve un valor.

        taskDeleteUseCase.deleteTask(id);

        //Verifica que taskCommandServiceMock.deleteTaskById fue llamado una vez con el id especificado
        verify(taskCommandServiceMock, times(1)).deleteTaskById(id);
    }
}