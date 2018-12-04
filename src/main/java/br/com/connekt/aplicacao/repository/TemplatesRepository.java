package br.com.connekt.aplicacao.repository;

import br.com.connekt.aplicacao.domain.Templates;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Templates entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TemplatesRepository extends JpaRepository<Templates, Long> {

}
