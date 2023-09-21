package com.example.backend;

import com.example.backend.Entities.NewToDo;
import com.example.backend.Entities.ToDo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class ToDoController {

    private final ToDoService toDoService;

    @GetMapping()
    public List<ToDo> getAllToDo() {
        return toDoService.getToDoList();
    }

    @PostMapping()
    public ToDo addToDo(@RequestBody NewToDo newToDo){
        return toDoService.addToDo(newToDo);
    }

}
