package br.com.connekt.aplicacao.service.impl;

import br.com.connekt.aplicacao.service.ResultsService;
import br.com.connekt.aplicacao.domain.Results;
import br.com.connekt.aplicacao.repository.ResultsRepository;
import br.com.connekt.aplicacao.service.dto.ResultsDTO;
import br.com.connekt.aplicacao.service.mapper.ResultsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Results.
 */
@Service
@Transactional
public class ResultsServiceImpl implements ResultsService {

    private final Logger log = LoggerFactory.getLogger(ResultsServiceImpl.class);

    private final ResultsRepository resultsRepository;

    private final ResultsMapper resultsMapper;

    public ResultsServiceImpl(ResultsRepository resultsRepository, ResultsMapper resultsMapper) {
        this.resultsRepository = resultsRepository;
        this.resultsMapper = resultsMapper;
    }

    /**
     * Save a results.
     *
     * @param resultsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ResultsDTO save(ResultsDTO resultsDTO) {
        log.debug("Request to save Results : {}", resultsDTO);

        Results results = resultsMapper.toEntity(resultsDTO);
        results = resultsRepository.save(results);
        return resultsMapper.toDto(results);
    }

    /**
     * Get all the results.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ResultsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Results");
        return resultsRepository.findAll(pageable)
            .map(resultsMapper::toDto);
    }


    /**
     * Get one results by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ResultsDTO> findOne(Long id) {
        log.debug("Request to get Results : {}", id);
        return resultsRepository.findById(id)
            .map(resultsMapper::toDto);
    }

    /**
     * Delete the results by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Results : {}", id);
        resultsRepository.deleteById(id);
    }
}
