package br.com.connekt.aplicacao.repository;

import br.com.connekt.aplicacao.domain.Results;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Results entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResultsRepository extends JpaRepository<Results, Long> {

}
