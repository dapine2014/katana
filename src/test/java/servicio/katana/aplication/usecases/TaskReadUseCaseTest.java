package servicio.katana.aplication.usecases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import servicio.katana.aplication.dto.TaskDto;
import servicio.katana.domain.services.ITaskEventService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class TaskReadUseCaseTest {

    @Mock
    private ITaskEventService iTaskEventServiceMock;

    @Mock
    private Pageable pageableMock;

    @InjectMocks
    private TaskReadUseCase taskReadUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAll() {
        Page<TaskDto> expectedPage = mock(Page.class); // you might want to use an actual Page instead of a mock

        when(iTaskEventServiceMock.getAllTasks(pageableMock)).thenReturn(expectedPage);

        Page<TaskDto> result = taskReadUseCase.findAll(pageableMock);

        verify(iTaskEventServiceMock, times(1)).getAllTasks(pageableMock);
        assertEquals(expectedPage, result);
    }

    @Test
    void findOneById() {
        Long id = 1L;
        TaskDto expectedTaskDto = mock(TaskDto.class); // you might want to use an actual TaskDto instead of a mock

        when(iTaskEventServiceMock.findTasksById(id)).thenReturn(Optional.of(expectedTaskDto));

        TaskDto result = taskReadUseCase.findOneById(id);

        verify(iTaskEventServiceMock, times(1)).findTasksById(id);
        assertEquals(expectedTaskDto, result);
    }
}