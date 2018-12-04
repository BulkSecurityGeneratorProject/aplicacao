package br.com.connekt.aplicacao.service.impl;

import br.com.connekt.aplicacao.service.ResultsDetailsService;
import br.com.connekt.aplicacao.domain.ResultsDetails;
import br.com.connekt.aplicacao.repository.ResultsDetailsRepository;
import br.com.connekt.aplicacao.service.dto.ResultsDetailsDTO;
import br.com.connekt.aplicacao.service.mapper.ResultsDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing ResultsDetails.
 */
@Service
@Transactional
public class ResultsDetailsServiceImpl implements ResultsDetailsService {

    private final Logger log = LoggerFactory.getLogger(ResultsDetailsServiceImpl.class);

    private final ResultsDetailsRepository resultsDetailsRepository;

    private final ResultsDetailsMapper resultsDetailsMapper;

    public ResultsDetailsServiceImpl(ResultsDetailsRepository resultsDetailsRepository, ResultsDetailsMapper resultsDetailsMapper) {
        this.resultsDetailsRepository = resultsDetailsRepository;
        this.resultsDetailsMapper = resultsDetailsMapper;
    }

    /**
     * Save a resultsDetails.
     *
     * @param resultsDetailsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ResultsDetailsDTO save(ResultsDetailsDTO resultsDetailsDTO) {
        log.debug("Request to save ResultsDetails : {}", resultsDetailsDTO);

        ResultsDetails resultsDetails = resultsDetailsMapper.toEntity(resultsDetailsDTO);
        resultsDetails = resultsDetailsRepository.save(resultsDetails);
        return resultsDetailsMapper.toDto(resultsDetails);
    }

    /**
     * Get all the resultsDetails.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ResultsDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ResultsDetails");
        return resultsDetailsRepository.findAll(pageable)
            .map(resultsDetailsMapper::toDto);
    }


    /**
     * Get one resultsDetails by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ResultsDetailsDTO> findOne(Long id) {
        log.debug("Request to get ResultsDetails : {}", id);
        return resultsDetailsRepository.findById(id)
            .map(resultsDetailsMapper::toDto);
    }

    /**
     * Delete the resultsDetails by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ResultsDetails : {}", id);
        resultsDetailsRepository.deleteById(id);
    }
}
