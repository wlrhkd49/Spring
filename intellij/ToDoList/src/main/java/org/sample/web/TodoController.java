package org.sample.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sample.model.TodoModel;
import org.sample.model.TodoRequest;
import org.sample.model.TodoResponse;
import org.sample.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j // log 사용하기 위해
@RestController
@CrossOrigin //CURS 문제 막음
@AllArgsConstructor
@RequestMapping("/")
public class TodoController {
    private final TodoService todoService;

    @PostMapping()
    public ResponseEntity<TodoResponse> create(@RequestBody TodoRequest request) {
        log.info("CREATE");

        if(ObjectUtils.isEmpty(request.getTitle())) // 타이틀 없는 경우 bad request
            return ResponseEntity.badRequest().build();

        if(ObjectUtils.isEmpty(request.getOrder()))
            request.setOrder(0L);

        if(ObjectUtils.isEmpty(request.getCompleted()))
            request.setCompleted(false);

        TodoModel result = this.todoService.add(request);
        return ResponseEntity.ok(new TodoResponse(result));
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoResponse> readOne(@PathVariable Long id) {
        log.info("READ ONE");
        TodoModel result = this.todoService.searchById(id);
        return ResponseEntity.ok(new TodoResponse(result));
    }

    @GetMapping()
    public ResponseEntity<List<TodoResponse>> readAll() {
        log.info("READ ALL");
        List<TodoModel> list = this.todoService.searchAll();
        List<TodoResponse> response = list.stream().map(TodoResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PatchMapping("{id}")
    public ResponseEntity<TodoResponse> update(@PathVariable Long id, @RequestBody TodoRequest request) {
        log.info("UPDATE");
        TodoModel result = this.todoService.updateById(id, request);
        return ResponseEntity.ok(new TodoResponse(result));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteOne(@PathVariable Long id) {
        log.info("DELETE");
        this.todoService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteAll() {
        log.info("DELETE ALL");
        this.todoService.deleteAll();
        return ResponseEntity.ok().build();
    }
}
