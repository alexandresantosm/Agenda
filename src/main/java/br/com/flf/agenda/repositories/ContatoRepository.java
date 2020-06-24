package br.com.flf.agenda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.flf.agenda.domains.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Integer> {

}
