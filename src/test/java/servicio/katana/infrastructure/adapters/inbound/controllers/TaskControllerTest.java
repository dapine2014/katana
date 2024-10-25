package servicio.katana.infrastructure.adapters.inbound.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import servicio.katana.aplication.dto.TaskDto;
import servicio.katana.aplication.ports.inbound.ITaskCreate;
import servicio.katana.aplication.ports.inbound.ITaskDelete;
import servicio.katana.aplication.ports.inbound.ITaskUpdate;
import servicio.katana.aplication.ports.outbound.ITaskRead;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private ITaskCreate taskCreator;

    @MockBean
    private ITaskUpdate taskUpdater;

    @MockBean
    private ITaskDelete taskDeleter;

    @MockBean
    private ITaskRead taskReader;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    @Test
    void createTicket() throws Exception {
        TaskDto taskDto = new TaskDto();
        // Inicializar los atributos de taskDto aquí

        when(taskCreator.TaskCreate(any(TaskDto.class))).thenReturn(taskDto);

        MvcResult result = mockMvc.perform(
                post("/api/task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskDto))
        ).andReturn();

        assertEquals(201, result.getResponse().getStatus());
    }

    @Test
    void updateTicket() throws Exception{
        Long id = 1L;
        TaskDto taskDto = new TaskDto(); // Inicializar los atributos de taskDto aquí

        when(taskUpdater.updateTask(id, taskDto)).thenReturn(Optional.of(taskDto));

        MvcResult result = mockMvc.perform(put("/api/task/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(taskDto))).andReturn();

        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    void testDeleteTask() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/api/task/" + id))
                .andExpect(status().isNoContent());
    }


    @Test
    void testGetAllTasks() throws Exception {
        TaskDto taskDto = new TaskDto();
        List<TaskDto> taskDtoList = Collections.singletonList(taskDto);
        Page<TaskDto> taskDtoPage = new PageImpl<>(taskDtoList);

        when(taskReader.findAll(any(PageRequest.class))).thenReturn(taskDtoPage);

        MvcResult result = mockMvc.perform(get("/api/task?page=0&size=10")).andReturn();

        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    void testFindTaskById() throws Exception {
        Long id = 1L;
        TaskDto taskDto = new TaskDto(); // Inicializar los atributos de taskDto aquí

        when(taskReader.findOneById(id)).thenReturn(taskDto);

        MvcResult result = mockMvc.perform(get("/api/task/" + id)).andReturn();

        assertEquals(200, result.getResponse().getStatus());
    }
}