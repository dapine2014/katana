package servicio.katana.aplication.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import servicio.katana.aplication.dto.TaskDto;
import servicio.katana.domain.entities.Task;
import servicio.katana.domain.repositories.TaskRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TaskCommandServiceImplTest {

    @Mock
    private TaskRepository taskRepositoryMock;

    @InjectMocks
    private TaskCommandServiceImpl taskCommandService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateTask() {
        TaskDto inputDto = new TaskDto(); //Establecer los elementos en este DTO como se requiera para la prueba
        Task expectedEntity = new Task(); //Establecer los elementos en esta entidad como se espera que sean luego de la operación

        when(taskRepositoryMock.saveAndFlush(any(Task.class))).thenReturn(expectedEntity);

        TaskDto result = taskCommandService.createTask(inputDto);

        verify(taskRepositoryMock, times(1)).saveAndFlush(any(Task.class));
        assertEquals(inputDto.getId(), result.getId()); //Verifica que se haya establecido el ID en el DTO de salida
        //Aquí puedes agregar más asserts para asegurarte que los elementos en 'result' sean los esperados
    }

    @Test
    void testUpdateTask() {
        Long id = 1L;
        TaskDto updatedTaskDto = new TaskDto(); //Establecer los valores aquí según lo que se espera que se actualice
        Task existingTask = new Task(); //Establecer los valores actuales de la tarea

        when(taskRepositoryMock.findById(id)).thenReturn(Optional.of(existingTask));
        when(taskRepositoryMock.saveAndFlush(any(Task.class))).thenReturn(existingTask);

        Optional<TaskDto> result = taskCommandService.updateTask(id, updatedTaskDto);

        verify(taskRepositoryMock, times(1)).findById(id);
        verify(taskRepositoryMock, times(1)).saveAndFlush(any(Task.class));
        //Aquí puedes agregar asserts para verificar que los atributos en 'result' sean los esperados
    }

    @Test
    void testDeleteTaskById() {
        Long id = 1L;
        Task existingTask = new Task();

        when(taskRepositoryMock.findById(id)).thenReturn(Optional.of(existingTask));

        taskCommandService.deleteTaskById(id);

        verify(taskRepositoryMock, times(1)).findById(id);
        verify(taskRepositoryMock, times(1)).delete(existingTask);
    }
}