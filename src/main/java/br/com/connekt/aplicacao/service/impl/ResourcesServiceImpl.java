package br.com.connekt.aplicacao.service.impl;

import br.com.connekt.aplicacao.service.ResourcesService;
import br.com.connekt.aplicacao.domain.Resources;
import br.com.connekt.aplicacao.repository.ResourcesRepository;
import br.com.connekt.aplicacao.service.dto.ResourcesDTO;
import br.com.connekt.aplicacao.service.mapper.ResourcesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Resources.
 */
@Service
@Transactional
public class ResourcesServiceImpl implements ResourcesService {

    private final Logger log = LoggerFactory.getLogger(ResourcesServiceImpl.class);

    private final ResourcesRepository resourcesRepository;

    private final ResourcesMapper resourcesMapper;

    public ResourcesServiceImpl(ResourcesRepository resourcesRepository, ResourcesMapper resourcesMapper) {
        this.resourcesRepository = resourcesRepository;
        this.resourcesMapper = resourcesMapper;
    }

    /**
     * Save a resources.
     *
     * @param resourcesDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ResourcesDTO save(ResourcesDTO resourcesDTO) {
        log.debug("Request to save Resources : {}", resourcesDTO);

        Resources resources = resourcesMapper.toEntity(resourcesDTO);
        resources = resourcesRepository.save(resources);
        return resourcesMapper.toDto(resources);
    }

    /**
     * Get all the resources.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ResourcesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Resources");
        return resourcesRepository.findAll(pageable)
            .map(resourcesMapper::toDto);
    }


    /**
     * Get one resources by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ResourcesDTO> findOne(Long id) {
        log.debug("Request to get Resources : {}", id);
        return resourcesRepository.findById(id)
            .map(resourcesMapper::toDto);
    }

    /**
     * Delete the resources by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Resources : {}", id);
        resourcesRepository.deleteById(id);
    }
}
