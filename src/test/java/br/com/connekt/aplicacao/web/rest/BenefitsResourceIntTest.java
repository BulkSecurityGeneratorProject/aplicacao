package br.com.connekt.aplicacao.web.rest;

import br.com.connekt.aplicacao.AplicacaoApp;

import br.com.connekt.aplicacao.domain.Benefits;
import br.com.connekt.aplicacao.repository.BenefitsRepository;
import br.com.connekt.aplicacao.service.BenefitsService;
import br.com.connekt.aplicacao.service.dto.BenefitsDTO;
import br.com.connekt.aplicacao.service.mapper.BenefitsMapper;
import br.com.connekt.aplicacao.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static br.com.connekt.aplicacao.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the BenefitsResource REST controller.
 *
 * @see BenefitsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AplicacaoApp.class)
public class BenefitsResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ICON = "AAAAAAAAAA";
    private static final String UPDATED_ICON = "BBBBBBBBBB";

    @Autowired
    private BenefitsRepository benefitsRepository;

    @Autowired
    private BenefitsMapper benefitsMapper;

    @Autowired
    private BenefitsService benefitsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBenefitsMockMvc;

    private Benefits benefits;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BenefitsResource benefitsResource = new BenefitsResource(benefitsService);
        this.restBenefitsMockMvc = MockMvcBuilders.standaloneSetup(benefitsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Benefits createEntity(EntityManager em) {
        Benefits benefits = new Benefits()
            .name(DEFAULT_NAME)
            .icon(DEFAULT_ICON);
        return benefits;
    }

    @Before
    public void initTest() {
        benefits = createEntity(em);
    }

    @Test
    @Transactional
    public void createBenefits() throws Exception {
        int databaseSizeBeforeCreate = benefitsRepository.findAll().size();

        // Create the Benefits
        BenefitsDTO benefitsDTO = benefitsMapper.toDto(benefits);
        restBenefitsMockMvc.perform(post("/api/benefits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(benefitsDTO)))
            .andExpect(status().isCreated());

        // Validate the Benefits in the database
        List<Benefits> benefitsList = benefitsRepository.findAll();
        assertThat(benefitsList).hasSize(databaseSizeBeforeCreate + 1);
        Benefits testBenefits = benefitsList.get(benefitsList.size() - 1);
        assertThat(testBenefits.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBenefits.getIcon()).isEqualTo(DEFAULT_ICON);
    }

    @Test
    @Transactional
    public void createBenefitsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = benefitsRepository.findAll().size();

        // Create the Benefits with an existing ID
        benefits.setId(1L);
        BenefitsDTO benefitsDTO = benefitsMapper.toDto(benefits);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBenefitsMockMvc.perform(post("/api/benefits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(benefitsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Benefits in the database
        List<Benefits> benefitsList = benefitsRepository.findAll();
        assertThat(benefitsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBenefits() throws Exception {
        // Initialize the database
        benefitsRepository.saveAndFlush(benefits);

        // Get all the benefitsList
        restBenefitsMockMvc.perform(get("/api/benefits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(benefits.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].icon").value(hasItem(DEFAULT_ICON.toString())));
    }
    
    @Test
    @Transactional
    public void getBenefits() throws Exception {
        // Initialize the database
        benefitsRepository.saveAndFlush(benefits);

        // Get the benefits
        restBenefitsMockMvc.perform(get("/api/benefits/{id}", benefits.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(benefits.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.icon").value(DEFAULT_ICON.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBenefits() throws Exception {
        // Get the benefits
        restBenefitsMockMvc.perform(get("/api/benefits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBenefits() throws Exception {
        // Initialize the database
        benefitsRepository.saveAndFlush(benefits);

        int databaseSizeBeforeUpdate = benefitsRepository.findAll().size();

        // Update the benefits
        Benefits updatedBenefits = benefitsRepository.findById(benefits.getId()).get();
        // Disconnect from session so that the updates on updatedBenefits are not directly saved in db
        em.detach(updatedBenefits);
        updatedBenefits
            .name(UPDATED_NAME)
            .icon(UPDATED_ICON);
        BenefitsDTO benefitsDTO = benefitsMapper.toDto(updatedBenefits);

        restBenefitsMockMvc.perform(put("/api/benefits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(benefitsDTO)))
            .andExpect(status().isOk());

        // Validate the Benefits in the database
        List<Benefits> benefitsList = benefitsRepository.findAll();
        assertThat(benefitsList).hasSize(databaseSizeBeforeUpdate);
        Benefits testBenefits = benefitsList.get(benefitsList.size() - 1);
        assertThat(testBenefits.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBenefits.getIcon()).isEqualTo(UPDATED_ICON);
    }

    @Test
    @Transactional
    public void updateNonExistingBenefits() throws Exception {
        int databaseSizeBeforeUpdate = benefitsRepository.findAll().size();

        // Create the Benefits
        BenefitsDTO benefitsDTO = benefitsMapper.toDto(benefits);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBenefitsMockMvc.perform(put("/api/benefits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(benefitsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Benefits in the database
        List<Benefits> benefitsList = benefitsRepository.findAll();
        assertThat(benefitsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBenefits() throws Exception {
        // Initialize the database
        benefitsRepository.saveAndFlush(benefits);

        int databaseSizeBeforeDelete = benefitsRepository.findAll().size();

        // Get the benefits
        restBenefitsMockMvc.perform(delete("/api/benefits/{id}", benefits.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Benefits> benefitsList = benefitsRepository.findAll();
        assertThat(benefitsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Benefits.class);
        Benefits benefits1 = new Benefits();
        benefits1.setId(1L);
        Benefits benefits2 = new Benefits();
        benefits2.setId(benefits1.getId());
        assertThat(benefits1).isEqualTo(benefits2);
        benefits2.setId(2L);
        assertThat(benefits1).isNotEqualTo(benefits2);
        benefits1.setId(null);
        assertThat(benefits1).isNotEqualTo(benefits2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BenefitsDTO.class);
        BenefitsDTO benefitsDTO1 = new BenefitsDTO();
        benefitsDTO1.setId(1L);
        BenefitsDTO benefitsDTO2 = new BenefitsDTO();
        assertThat(benefitsDTO1).isNotEqualTo(benefitsDTO2);
        benefitsDTO2.setId(benefitsDTO1.getId());
        assertThat(benefitsDTO1).isEqualTo(benefitsDTO2);
        benefitsDTO2.setId(2L);
        assertThat(benefitsDTO1).isNotEqualTo(benefitsDTO2);
        benefitsDTO1.setId(null);
        assertThat(benefitsDTO1).isNotEqualTo(benefitsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(benefitsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(benefitsMapper.fromId(null)).isNull();
    }
}
