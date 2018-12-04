package br.com.connekt.aplicacao.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.connekt.aplicacao.service.TemplatesService;
import br.com.connekt.aplicacao.web.rest.errors.BadRequestAlertException;
import br.com.connekt.aplicacao.web.rest.util.HeaderUtil;
import br.com.connekt.aplicacao.web.rest.util.PaginationUtil;
import br.com.connekt.aplicacao.service.dto.TemplatesDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Templates.
 */
@RestController
@RequestMapping("/api")
public class TemplatesResource {

    private final Logger log = LoggerFactory.getLogger(TemplatesResource.class);

    private static final String ENTITY_NAME = "templates";

    private final TemplatesService templatesService;

    public TemplatesResource(TemplatesService templatesService) {
        this.templatesService = templatesService;
    }

    /**
     * POST  /templates : Create a new templates.
     *
     * @param templatesDTO the templatesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new templatesDTO, or with status 400 (Bad Request) if the templates has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/templates")
    @Timed
    public ResponseEntity<TemplatesDTO> createTemplates(@RequestBody TemplatesDTO templatesDTO) throws URISyntaxException {
        log.debug("REST request to save Templates : {}", templatesDTO);
        if (templatesDTO.getId() != null) {
            throw new BadRequestAlertException("A new templates cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TemplatesDTO result = templatesService.save(templatesDTO);
        return ResponseEntity.created(new URI("/api/templates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /templates : Updates an existing templates.
     *
     * @param templatesDTO the templatesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated templatesDTO,
     * or with status 400 (Bad Request) if the templatesDTO is not valid,
     * or with status 500 (Internal Server Error) if the templatesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/templates")
    @Timed
    public ResponseEntity<TemplatesDTO> updateTemplates(@RequestBody TemplatesDTO templatesDTO) throws URISyntaxException {
        log.debug("REST request to update Templates : {}", templatesDTO);
        if (templatesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TemplatesDTO result = templatesService.save(templatesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, templatesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /templates : get all the templates.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of templates in body
     */
    @GetMapping("/templates")
    @Timed
    public ResponseEntity<List<TemplatesDTO>> getAllTemplates(Pageable pageable) {
        log.debug("REST request to get a page of Templates");
        Page<TemplatesDTO> page = templatesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/templates");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /templates/:id : get the "id" templates.
     *
     * @param id the id of the templatesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the templatesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/templates/{id}")
    @Timed
    public ResponseEntity<TemplatesDTO> getTemplates(@PathVariable Long id) {
        log.debug("REST request to get Templates : {}", id);
        Optional<TemplatesDTO> templatesDTO = templatesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(templatesDTO);
    }

    /**
     * DELETE  /templates/:id : delete the "id" templates.
     *
     * @param id the id of the templatesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/templates/{id}")
    @Timed
    public ResponseEntity<Void> deleteTemplates(@PathVariable Long id) {
        log.debug("REST request to delete Templates : {}", id);
        templatesService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
