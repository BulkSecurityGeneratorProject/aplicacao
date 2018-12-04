package br.com.connekt.aplicacao.service.impl;

import br.com.connekt.aplicacao.service.PortalService;
import br.com.connekt.aplicacao.domain.Portal;
import br.com.connekt.aplicacao.repository.PortalRepository;
import br.com.connekt.aplicacao.service.dto.PortalDTO;
import br.com.connekt.aplicacao.service.mapper.PortalMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Portal.
 */
@Service
@Transactional
public class PortalServiceImpl implements PortalService {

    private final Logger log = LoggerFactory.getLogger(PortalServiceImpl.class);

    private final PortalRepository portalRepository;

    private final PortalMapper portalMapper;

    public PortalServiceImpl(PortalRepository portalRepository, PortalMapper portalMapper) {
        this.portalRepository = portalRepository;
        this.portalMapper = portalMapper;
    }

    /**
     * Save a portal.
     *
     * @param portalDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PortalDTO save(PortalDTO portalDTO) {
        log.debug("Request to save Portal : {}", portalDTO);

        Portal portal = portalMapper.toEntity(portalDTO);
        portal = portalRepository.save(portal);
        return portalMapper.toDto(portal);
    }

    /**
     * Get all the portals.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PortalDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Portals");
        return portalRepository.findAll(pageable)
            .map(portalMapper::toDto);
    }


    /**
     * Get one portal by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PortalDTO> findOne(Long id) {
        log.debug("Request to get Portal : {}", id);
        return portalRepository.findById(id)
            .map(portalMapper::toDto);
    }

    /**
     * Delete the portal by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Portal : {}", id);
        portalRepository.deleteById(id);
    }
}
