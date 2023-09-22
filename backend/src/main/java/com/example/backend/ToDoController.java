package com.example.backend;

import com.example.backend.Entities.NewToDoDTO;
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

    @GetMapping("/{id}")
    public ToDo getToDoById(@PathVariable String id){
        return toDoService.getToDoById(id);
    }

    @PutMapping("/{id}")
    public ToDo putToDo(@PathVariable String id, @RequestBody NewToDoDTO todo){
        return toDoService.updateToDo(id, todo);
    }

    @PostMapping()
    public ToDo addToDo(@RequestBody NewToDoDTO newToDoDTO){
        return toDoService.addToDo(newToDoDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteToDoById(@PathVariable String id){
        toDoService.deleteToDoBy(id);
    }

}
