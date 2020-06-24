package br.com.flf.agenda.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.flf.agenda.domains.Contato;
import br.com.flf.agenda.services.ContatoService;

@RestController
@RequestMapping("/contatos")
public class ContatoControllers {

	@Autowired
	private ContatoService contatoService;

	// Buscar um contato pelo id
	@GetMapping("/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Contato contato = contatoService.find(id);

		return ResponseEntity.ok(contato);
	}

	// Listar todos os contatos
	@GetMapping
	public ResponseEntity<?> list() {
		List<Contato> contatos = contatoService.list();

		return ResponseEntity.ok(contatos);
	}

	// Salvar um novo contato
	@PostMapping
	public ResponseEntity<Void> save(@RequestBody Contato contato) {
		contatoService.save(contato);
		URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(contato.getId())
					.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	// Atualizar contato
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody Contato contato, @PathVariable Integer id) {
		contato.setId(id);
		contatoService.update(contato);
		
		return ResponseEntity.noContent().build();
	}

	// Deletar um contato pelo id
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		contatoService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
}
