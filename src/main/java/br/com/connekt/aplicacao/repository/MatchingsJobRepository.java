package br.com.connekt.aplicacao.repository;

import br.com.connekt.aplicacao.domain.MatchingsJob;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MatchingsJob entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MatchingsJobRepository extends JpaRepository<MatchingsJob, Long> {

}
