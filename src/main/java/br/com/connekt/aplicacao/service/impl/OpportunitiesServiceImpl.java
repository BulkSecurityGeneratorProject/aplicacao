package br.com.connekt.aplicacao.service.impl;

import br.com.connekt.aplicacao.service.OpportunitiesService;
import br.com.connekt.aplicacao.domain.Opportunities;
import br.com.connekt.aplicacao.repository.OpportunitiesRepository;
import br.com.connekt.aplicacao.service.dto.OpportunitiesDTO;
import br.com.connekt.aplicacao.service.mapper.OpportunitiesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Opportunities.
 */
@Service
@Transactional
public class OpportunitiesServiceImpl implements OpportunitiesService {

    private final Logger log = LoggerFactory.getLogger(OpportunitiesServiceImpl.class);

    private final OpportunitiesRepository opportunitiesRepository;

    private final OpportunitiesMapper opportunitiesMapper;

    public OpportunitiesServiceImpl(OpportunitiesRepository opportunitiesRepository, OpportunitiesMapper opportunitiesMapper) {
        this.opportunitiesRepository = opportunitiesRepository;
        this.opportunitiesMapper = opportunitiesMapper;
    }

    /**
     * Save a opportunities.
     *
     * @param opportunitiesDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OpportunitiesDTO save(OpportunitiesDTO opportunitiesDTO) {
        log.debug("Request to save Opportunities : {}", opportunitiesDTO);

        Opportunities opportunities = opportunitiesMapper.toEntity(opportunitiesDTO);
        opportunities = opportunitiesRepository.save(opportunities);
        return opportunitiesMapper.toDto(opportunities);
    }

    /**
     * Get all the opportunities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OpportunitiesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Opportunities");
        return opportunitiesRepository.findAll(pageable)
            .map(opportunitiesMapper::toDto);
    }


    /**
     * Get one opportunities by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OpportunitiesDTO> findOne(Long id) {
        log.debug("Request to get Opportunities : {}", id);
        return opportunitiesRepository.findById(id)
            .map(opportunitiesMapper::toDto);
    }

    /**
     * Delete the opportunities by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Opportunities : {}", id);
        opportunitiesRepository.deleteById(id);
    }
}
