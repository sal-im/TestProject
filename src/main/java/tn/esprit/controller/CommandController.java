package tn.esprit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.entites.Command;
import tn.esprit.service.CommandService;

@RestController
public class CommandController {

	@Autowired
	CommandService commandService;

	@PostMapping("add")
	public ResponseEntity<Command> add(@RequestBody Command entity) {
		return ResponseEntity.status(HttpStatus.OK).body(commandService.add(entity));
	}

	@GetMapping("get-all")
	public ResponseEntity<List<Command>> getAll() {
		List<Command> commands = commandService.getAll();

		return ResponseEntity.status(HttpStatus.OK).body(commands);
	}

	@GetMapping("get-by-id/{id}")
	public ResponseEntity<Command> getById(@PathVariable(name = "id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(commandService.getById(id));
	}

	@DeleteMapping("delete-by-id/{id}")
	public ResponseEntity<Boolean> deleteById(@PathVariable(name = "id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(commandService.delete(id));
	}
}
