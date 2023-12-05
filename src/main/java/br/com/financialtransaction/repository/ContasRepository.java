package br.com.financialtransaction.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.financialtransaction.models.Saldos;

@Repository
public interface ContasRepository extends CrudRepository<Saldos, Long> {

}
