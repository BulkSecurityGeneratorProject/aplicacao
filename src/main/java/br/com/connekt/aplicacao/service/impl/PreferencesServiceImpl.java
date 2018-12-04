package br.com.connekt.aplicacao.service.impl;

import br.com.connekt.aplicacao.service.PreferencesService;
import br.com.connekt.aplicacao.domain.Preferences;
import br.com.connekt.aplicacao.repository.PreferencesRepository;
import br.com.connekt.aplicacao.service.dto.PreferencesDTO;
import br.com.connekt.aplicacao.service.mapper.PreferencesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Preferences.
 */
@Service
@Transactional
public class PreferencesServiceImpl implements PreferencesService {

    private final Logger log = LoggerFactory.getLogger(PreferencesServiceImpl.class);

    private final PreferencesRepository preferencesRepository;

    private final PreferencesMapper preferencesMapper;

    public PreferencesServiceImpl(PreferencesRepository preferencesRepository, PreferencesMapper preferencesMapper) {
        this.preferencesRepository = preferencesRepository;
        this.preferencesMapper = preferencesMapper;
    }

    /**
     * Save a preferences.
     *
     * @param preferencesDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PreferencesDTO save(PreferencesDTO preferencesDTO) {
        log.debug("Request to save Preferences : {}", preferencesDTO);

        Preferences preferences = preferencesMapper.toEntity(preferencesDTO);
        preferences = preferencesRepository.save(preferences);
        return preferencesMapper.toDto(preferences);
    }

    /**
     * Get all the preferences.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PreferencesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Preferences");
        return preferencesRepository.findAll(pageable)
            .map(preferencesMapper::toDto);
    }


    /**
     * Get one preferences by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PreferencesDTO> findOne(Long id) {
        log.debug("Request to get Preferences : {}", id);
        return preferencesRepository.findById(id)
            .map(preferencesMapper::toDto);
    }

    /**
     * Delete the preferences by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Preferences : {}", id);
        preferencesRepository.deleteById(id);
    }
}
