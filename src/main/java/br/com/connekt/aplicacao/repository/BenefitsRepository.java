package br.com.connekt.aplicacao.repository;

import br.com.connekt.aplicacao.domain.Benefits;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Benefits entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BenefitsRepository extends JpaRepository<Benefits, Long> {

}
