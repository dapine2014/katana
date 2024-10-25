package servicio.katana.aplication.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import servicio.katana.aplication.dto.TaskDto;
import servicio.katana.domain.entities.Task;
import servicio.katana.domain.repositories.TaskRepository;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TaskEventServiceImplTest {

    @Mock
    private TaskRepository taskRepositoryMock;

    @Mock
    private ModelMapper modelMapperMock;

    @InjectMocks
    private TaskEventServiceImpl taskEventService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllTasks() {
        Pageable pageable = PageRequest.of(0, 10);
        Task task = new Task();
        TaskDto taskDto = new TaskDto();

        when(taskRepositoryMock.findAll(pageable)).thenReturn(new PageImpl<>(Collections.singletonList(task)));
        when(modelMapperMock.map(task, TaskDto.class)).thenReturn(taskDto);

        Page<TaskDto> result = taskEventService.getAllTasks(pageable);

        assertEquals(1, result.getContent().size());
        assertEquals(taskDto, result.getContent().get(0));
    }

    @Test
    void testFindTasksById() {
        Long id = 1L;
        Task task = new Task();
        TaskDto taskDto = new TaskDto();

        when(taskRepositoryMock.findById(id)).thenReturn(Optional.of(task));
        when(modelMapperMock.map(task, TaskDto.class)).thenReturn(taskDto);

        Optional<TaskDto> result = taskEventService.findTasksById(id);

        assertEquals(Optional.of(taskDto), result);
    }
}