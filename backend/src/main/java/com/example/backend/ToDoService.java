package com.example.backend;

import com.example.backend.Entities.NewToDoDTO;
import com.example.backend.Entities.ToDo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ToDoService {
    private final ToDoRepository toDoRepository;

    public List<ToDo> getToDoList() {
        return toDoRepository.findAll();
    }

    public ToDo addToDo(NewToDoDTO newToDoDTO) {
        ToDo toDo = new ToDo(UUID.randomUUID().toString(), newToDoDTO.description(), newToDoDTO.status());
        return toDoRepository.save(toDo);
    }

    public ToDo getToDoById(String id) {
        return toDoRepository.findById(id).orElseThrow();
    }

    public void deleteToDoBy(String id) {
            toDoRepository.deleteById(id);
    }

    public ToDo updateToDo(String id, NewToDoDTO updateTodo) {
        ToDo toDoToSave = new ToDo(id, updateTodo.description(), updateTodo.status());
        return toDoRepository.save(toDoToSave);
    }
}
