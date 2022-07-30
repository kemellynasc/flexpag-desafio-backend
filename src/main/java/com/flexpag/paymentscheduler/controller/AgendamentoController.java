package com.flexpag.paymentscheduler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flexpag.paymentscheduler.model.Agendamento;
import com.flexpag.paymentscheduler.repository.AgendamentosRepository;

@RestController
@RequestMapping("/agendamentos")
@CrossOrigin("*")
public class AgendamentoController {

	@Autowired
	private AgendamentosRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Agendamento>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Agendamento> GetById(@PathVariable long id){
		return repository.findById(id)
				.map(resp ->ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/status/{status}")
	public ResponseEntity<List<Agendamento>> GetByStatus(@PathVariable String status){
		return ResponseEntity.ok(repository.findAllByStatusContainingIgnoreCase(status));
	}
	
	@PostMapping
	public ResponseEntity<Agendamento> post (@RequestBody Agendamento agendamento){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(agendamento));
	}
	
	@PutMapping
	public ResponseEntity<Agendamento> put (@RequestBody Agendamento agendamento){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(agendamento));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
	repository.deleteById(id);
	}
	}

