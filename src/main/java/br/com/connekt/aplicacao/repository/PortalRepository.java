package br.com.connekt.aplicacao.repository;

import br.com.connekt.aplicacao.domain.Portal;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Portal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PortalRepository extends JpaRepository<Portal, Long> {

}
