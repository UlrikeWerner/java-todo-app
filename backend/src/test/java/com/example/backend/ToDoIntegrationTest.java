package com.example.backend;

import com.example.backend.Entities.Status;
import com.example.backend.Entities.ToDo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ToDoIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ToDoRepository toDoRepository;

    @Test
    @DirtiesContext
    void testGetAllToDo_ReturnEmptyList() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        []
                        """
                ));
    }

    @Test
    @DirtiesContext
    void testGetAllToDo() throws Exception {
        toDoRepository.save(new ToDo("1", "description for DONE", Status.DONE));
        toDoRepository.save(new ToDo("2", "test", Status.OPEN));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [
                            {
                                "id": "1",
                                "description": "description for DONE",
                                status: "DONE"
                           },
                           {
                                "id": "2",
                                "description": "test",
                                status: "OPEN"
                           }
                        ]
                        """
                ));
    }

    @Test
    @DirtiesContext
    void testGetToDoById() throws Exception {
        String id = "1";
        toDoRepository.save(new ToDo(id, "description for testing", Status.OPEN));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo/" + id))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                       {
                           "id": "1",
                           "description": "description for testing",
                           status: "OPEN"
                       }
                        """
                ));
    }

    @Test
    @DirtiesContext
    void testGetToDoById_ReturnException() throws Exception {
        String id = "1";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo/" + id))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Das passende ToDo konnte nicht gefunden werden!"));
    }

    @Test
    @DirtiesContext
    void testAddToDo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "description": "test description",
                        "status": "OPEN"
                    }
                """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    {
                        "description": "test description",
                        "status": "OPEN"
                    }
                """))
                .andExpect(jsonPath("$.id").isNotEmpty());
    }
}
