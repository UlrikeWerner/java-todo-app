package com.example.backend;

import com.example.backend.Entities.Exception.NoToDoForDelete;
import com.example.backend.Entities.Exception.NoToDoFound;
import com.example.backend.Entities.Exception.UpdateFailedToDoNotFound;
import com.example.backend.Entities.NewToDoDTO;
import com.example.backend.Entities.ToDo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ToDo getToDoById(@PathVariable String id) throws NoToDoFound {
        return toDoService.getToDoById(id);
    }

    @PutMapping("/{id}")
    public ToDo putToDo(@PathVariable String id, @RequestBody NewToDoDTO todo) throws UpdateFailedToDoNotFound {
        return toDoService.updateToDo(id, todo);
    }

    @PostMapping()
    public ToDo addToDo(@RequestBody NewToDoDTO newToDoDTO){
        return toDoService.addToDo(newToDoDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteToDoById(@PathVariable String id) throws NoToDoForDelete {
        toDoService.deleteToDoBy(id);
    }

    @ExceptionHandler(NoToDoFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoSuchElementException(NoToDoFound e){
        return "Das passende ToDo konnte nicht gefunden werden!";
    }

    @ExceptionHandler(NoToDoForDelete.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public String handleNoToDoForDelete(NoToDoForDelete e){
        return "Das ToDo konnte nicht gelöscht werden!";
    }

    @ExceptionHandler(UpdateFailedToDoNotFound.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public String UpdateFailedToDoNotFound(UpdateFailedToDoNotFound e){
        return "Das ToDo konnte nicht upgedatet werden!";
    }

}
