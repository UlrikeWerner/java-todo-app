package com.example.backend;

import com.example.backend.Entities.NewToDo;
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

    public ToDo addToDo(NewToDo newToDo) {
        ToDo toDo = new ToDo(UUID.randomUUID().toString(), newToDo.description(), newToDo.status());
        return toDoRepository.save(toDo);
    }
}
