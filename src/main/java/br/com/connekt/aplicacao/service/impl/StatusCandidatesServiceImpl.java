package br.com.connekt.aplicacao.service.impl;

import br.com.connekt.aplicacao.service.StatusCandidatesService;
import br.com.connekt.aplicacao.domain.StatusCandidates;
import br.com.connekt.aplicacao.repository.StatusCandidatesRepository;
import br.com.connekt.aplicacao.service.dto.StatusCandidatesDTO;
import br.com.connekt.aplicacao.service.mapper.StatusCandidatesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing StatusCandidates.
 */
@Service
@Transactional
public class StatusCandidatesServiceImpl implements StatusCandidatesService {

    private final Logger log = LoggerFactory.getLogger(StatusCandidatesServiceImpl.class);

    private final StatusCandidatesRepository statusCandidatesRepository;

    private final StatusCandidatesMapper statusCandidatesMapper;

    public StatusCandidatesServiceImpl(StatusCandidatesRepository statusCandidatesRepository, StatusCandidatesMapper statusCandidatesMapper) {
        this.statusCandidatesRepository = statusCandidatesRepository;
        this.statusCandidatesMapper = statusCandidatesMapper;
    }

    /**
     * Save a statusCandidates.
     *
     * @param statusCandidatesDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public StatusCandidatesDTO save(StatusCandidatesDTO statusCandidatesDTO) {
        log.debug("Request to save StatusCandidates : {}", statusCandidatesDTO);

        StatusCandidates statusCandidates = statusCandidatesMapper.toEntity(statusCandidatesDTO);
        statusCandidates = statusCandidatesRepository.save(statusCandidates);
        return statusCandidatesMapper.toDto(statusCandidates);
    }

    /**
     * Get all the statusCandidates.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<StatusCandidatesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all StatusCandidates");
        return statusCandidatesRepository.findAll(pageable)
            .map(statusCandidatesMapper::toDto);
    }


    /**
     * Get one statusCandidates by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<StatusCandidatesDTO> findOne(Long id) {
        log.debug("Request to get StatusCandidates : {}", id);
        return statusCandidatesRepository.findById(id)
            .map(statusCandidatesMapper::toDto);
    }

    /**
     * Delete the statusCandidates by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete StatusCandidates : {}", id);
        statusCandidatesRepository.deleteById(id);
    }
}
