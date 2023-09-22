package com.example.backend;

import com.example.backend.Entities.Exception.NoToDoForDelete;
import com.example.backend.Entities.Exception.NoToDoFound;
import com.example.backend.Entities.Exception.UpdateFailedToDoNotFound;
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

    public ToDo getToDoById(String id) throws NoToDoFound {
        return toDoRepository.findById(id).orElseThrow(NoToDoFound::new);
    }

    public ToDo updateToDo(String id, NewToDoDTO updateTodo) throws UpdateFailedToDoNotFound {
        try{
            ToDo testToDo = getToDoById(id);
            ToDo toDoToSave = new ToDo(id, updateTodo.description(), updateTodo.status());
            return toDoRepository.save(toDoToSave);
        } catch (NoToDoFound e){
            throw new UpdateFailedToDoNotFound();
        }
    }

    public void deleteToDoBy(String id) throws NoToDoForDelete {
        try{
            ToDo testToDo = getToDoById(id);
            toDoRepository.deleteById(id);
        } catch (NoToDoFound e){
            throw new NoToDoForDelete();
        }

    }
}
