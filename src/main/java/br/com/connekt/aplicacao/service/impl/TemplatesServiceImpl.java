package br.com.connekt.aplicacao.service.impl;

import br.com.connekt.aplicacao.service.TemplatesService;
import br.com.connekt.aplicacao.domain.Templates;
import br.com.connekt.aplicacao.repository.TemplatesRepository;
import br.com.connekt.aplicacao.service.dto.TemplatesDTO;
import br.com.connekt.aplicacao.service.mapper.TemplatesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Templates.
 */
@Service
@Transactional
public class TemplatesServiceImpl implements TemplatesService {

    private final Logger log = LoggerFactory.getLogger(TemplatesServiceImpl.class);

    private final TemplatesRepository templatesRepository;

    private final TemplatesMapper templatesMapper;

    public TemplatesServiceImpl(TemplatesRepository templatesRepository, TemplatesMapper templatesMapper) {
        this.templatesRepository = templatesRepository;
        this.templatesMapper = templatesMapper;
    }

    /**
     * Save a templates.
     *
     * @param templatesDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TemplatesDTO save(TemplatesDTO templatesDTO) {
        log.debug("Request to save Templates : {}", templatesDTO);

        Templates templates = templatesMapper.toEntity(templatesDTO);
        templates = templatesRepository.save(templates);
        return templatesMapper.toDto(templates);
    }

    /**
     * Get all the templates.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TemplatesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Templates");
        return templatesRepository.findAll(pageable)
            .map(templatesMapper::toDto);
    }


    /**
     * Get one templates by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TemplatesDTO> findOne(Long id) {
        log.debug("Request to get Templates : {}", id);
        return templatesRepository.findById(id)
            .map(templatesMapper::toDto);
    }

    /**
     * Delete the templates by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Templates : {}", id);
        templatesRepository.deleteById(id);
    }
}
