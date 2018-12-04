package br.com.connekt.aplicacao.repository;

import br.com.connekt.aplicacao.domain.Requests;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Requests entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RequestsRepository extends JpaRepository<Requests, Long> {

}
