package net.laggserver.API.controller;

import net.laggserver.API.model.TodoItem;
import net.laggserver.API.repository.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todoitem")
public class TodoItemController {
		@Autowired private TodoItemRepository todoItemRepository;

		@GetMapping
		public List<TodoItem> getAllTodoItems() {
				return todoItemRepository.findAll();
		}

		@PostMapping
		public TodoItem createTodoItem(@RequestBody TodoItem todoItem) {
				TodoItem result = todoItemRepository.save(todoItem);
				return result;

		}

		@GetMapping("/{id}")
		public ResponseEntity<TodoItem> getTodoItemById(@PathVariable Long id) {
				Optional<TodoItem> itemOptional = todoItemRepository.findById(id);
				return itemOptional.map(item -> ResponseEntity.ok().body(item)).orElse(ResponseEntity.notFound().build());
		}

		@PutMapping("/{id}")
		public ResponseEntity<TodoItem> updateTodoItem(@PathVariable Long id, @RequestBody TodoItem itemDetails) {
				Optional<TodoItem> itemOptional = todoItemRepository.findById(id);
				if (itemOptional.isPresent()) {
						TodoItem item = itemOptional.get();
						item.cloneFrom(itemDetails);
						todoItemRepository.save(item);
						return ResponseEntity.ok().body(item);
				} else {
						return ResponseEntity.notFound().build();
				}
		}

		@DeleteMapping("/{id}")
		public ResponseEntity<TodoItem> deleteTodoItem(@PathVariable Long id) {
				Optional<TodoItem> itemOptional = todoItemRepository.findById(id);
				if (itemOptional.isPresent()) {
						TodoItem item = itemOptional.get();
						todoItemRepository.delete(item);
						return ResponseEntity.ok().build();
				} else {
						return ResponseEntity.notFound().build();
				}
		}
}
