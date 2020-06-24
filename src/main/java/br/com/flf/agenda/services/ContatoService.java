package br.com.flf.agenda.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import br.com.flf.agenda.domains.Contato;
import br.com.flf.agenda.repositories.ContatoRepository;

@Service
public class ContatoService {

	private ContatoRepository contatoRepository;
	
	public Contato find(Integer id) {
		Optional<Contato> contato = contatoRepository.findById(id);
		
		return contato.orElseThrow(() -> new ObjectNotFoundException(
				"Contato não localizado com o id: " + id + ", Tipo: " + Contato.class.getName(), null));
	}
	
	public List<Contato> list() {
		return contatoRepository.findAll();
	}
	
	public Contato save(Contato contato) {
		return contatoRepository.save(contato);
	}
	
	public Contato update(Contato contato) {
		find(contato.getId());
		
		return contatoRepository.save(contato);
	}
	
	public void delete(Integer id) {
		find(id);
		
		contatoRepository.deleteById(id);
	}
}
