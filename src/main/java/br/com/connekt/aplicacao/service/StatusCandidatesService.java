package br.com.connekt.aplicacao.service;

import br.com.connekt.aplicacao.service.dto.StatusCandidatesDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing StatusCandidates.
 */
public interface StatusCandidatesService {

    /**
     * Save a statusCandidates.
     *
     * @param statusCandidatesDTO the entity to save
     * @return the persisted entity
     */
    StatusCandidatesDTO save(StatusCandidatesDTO statusCandidatesDTO);

    /**
     * Get all the statusCandidates.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<StatusCandidatesDTO> findAll(Pageable pageable);


    /**
     * Get the "id" statusCandidates.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<StatusCandidatesDTO> findOne(Long id);

    /**
     * Delete the "id" statusCandidates.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
