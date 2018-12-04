package br.com.connekt.aplicacao.service.impl;

import br.com.connekt.aplicacao.service.BenefitsService;
import br.com.connekt.aplicacao.domain.Benefits;
import br.com.connekt.aplicacao.repository.BenefitsRepository;
import br.com.connekt.aplicacao.service.dto.BenefitsDTO;
import br.com.connekt.aplicacao.service.mapper.BenefitsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Benefits.
 */
@Service
@Transactional
public class BenefitsServiceImpl implements BenefitsService {

    private final Logger log = LoggerFactory.getLogger(BenefitsServiceImpl.class);

    private final BenefitsRepository benefitsRepository;

    private final BenefitsMapper benefitsMapper;

    public BenefitsServiceImpl(BenefitsRepository benefitsRepository, BenefitsMapper benefitsMapper) {
        this.benefitsRepository = benefitsRepository;
        this.benefitsMapper = benefitsMapper;
    }

    /**
     * Save a benefits.
     *
     * @param benefitsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BenefitsDTO save(BenefitsDTO benefitsDTO) {
        log.debug("Request to save Benefits : {}", benefitsDTO);

        Benefits benefits = benefitsMapper.toEntity(benefitsDTO);
        benefits = benefitsRepository.save(benefits);
        return benefitsMapper.toDto(benefits);
    }

    /**
     * Get all the benefits.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BenefitsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Benefits");
        return benefitsRepository.findAll(pageable)
            .map(benefitsMapper::toDto);
    }


    /**
     * Get one benefits by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BenefitsDTO> findOne(Long id) {
        log.debug("Request to get Benefits : {}", id);
        return benefitsRepository.findById(id)
            .map(benefitsMapper::toDto);
    }

    /**
     * Delete the benefits by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Benefits : {}", id);
        benefitsRepository.deleteById(id);
    }
}
