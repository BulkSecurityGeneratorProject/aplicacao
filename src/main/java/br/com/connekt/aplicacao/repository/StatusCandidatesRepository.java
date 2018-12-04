package br.com.connekt.aplicacao.repository;

import br.com.connekt.aplicacao.domain.StatusCandidates;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the StatusCandidates entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StatusCandidatesRepository extends JpaRepository<StatusCandidates, Long> {

}
