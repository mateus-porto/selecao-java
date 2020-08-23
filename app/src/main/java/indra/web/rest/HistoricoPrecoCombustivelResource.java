package indra.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import indra.domain.HistoricoPrecoCombustivel;
import indra.service.HistoricoPrecoCombustivelService;
import indra.service.dto.HistoricoPrecoCombustivelDTO;
import indra.service.dto.HistoricoPrecoCombustivelVendaCompraDTO;
import indra.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link indra.domain.HistoricoPrecoCombustivel}.
 */
@RestController
@RequestMapping("/api")
public class HistoricoPrecoCombustivelResource {

    private final Logger log = LoggerFactory.getLogger(HistoricoPrecoCombustivelResource.class);

    private static final String ENTITY_NAME = "historicoPrecoCombustivel";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HistoricoPrecoCombustivelService historicoPrecoCombustivelService;

    public HistoricoPrecoCombustivelResource(HistoricoPrecoCombustivelService historicoPrecoCombustivelService) {
        this.historicoPrecoCombustivelService = historicoPrecoCombustivelService;
    }

    /**
     * {@code POST  /historico-preco-combustivels} : Create a new historicoPrecoCombustivel.
     *
     * @param historicoPrecoCombustivelDTO the historicoPrecoCombustivelDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new historicoPrecoCombustivelDTO, or with status {@code 400 (Bad Request)} if the historicoPrecoCombustivel has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/historico-preco-combustivels")
    public ResponseEntity<HistoricoPrecoCombustivelDTO> createHistoricoPrecoCombustivel(@RequestBody HistoricoPrecoCombustivelDTO historicoPrecoCombustivelDTO) throws URISyntaxException {
        log.debug("REST request to save HistoricoPrecoCombustivel : {}", historicoPrecoCombustivelDTO);
        if (historicoPrecoCombustivelDTO.getId() != null) {
            throw new BadRequestAlertException("A new historicoPrecoCombustivel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HistoricoPrecoCombustivelDTO result = historicoPrecoCombustivelService.save(historicoPrecoCombustivelDTO);
        return ResponseEntity.created(new URI("/api/historico-preco-combustivels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /historico-preco-combustivels} : Updates an existing historicoPrecoCombustivel.
     *
     * @param historicoPrecoCombustivelDTO the historicoPrecoCombustivelDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated historicoPrecoCombustivelDTO,
     * or with status {@code 400 (Bad Request)} if the historicoPrecoCombustivelDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the historicoPrecoCombustivelDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/historico-preco-combustivels")
    public ResponseEntity<HistoricoPrecoCombustivelDTO> updateHistoricoPrecoCombustivel(@RequestBody HistoricoPrecoCombustivelDTO historicoPrecoCombustivelDTO) throws URISyntaxException {
        log.debug("REST request to update HistoricoPrecoCombustivel : {}", historicoPrecoCombustivelDTO);
        if (historicoPrecoCombustivelDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HistoricoPrecoCombustivelDTO result = historicoPrecoCombustivelService.save(historicoPrecoCombustivelDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, historicoPrecoCombustivelDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /historico-preco-combustivels} : get all the historicoPrecoCombustivels.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of historicoPrecoCombustivels in body.
     */
    @GetMapping("/historico-preco-combustivels")
    public ResponseEntity<List<HistoricoPrecoCombustivelDTO>> getAllHistoricoPrecoCombustivels(Pageable pageable) {
        log.debug("REST request to get a page of HistoricoPrecoCombustivels");
        Page<HistoricoPrecoCombustivelDTO> page = historicoPrecoCombustivelService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /historico-preco-combustivels/:id} : get the "id" historicoPrecoCombustivel.
     *
     * @param id the id of the historicoPrecoCombustivelDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the historicoPrecoCombustivelDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/historico-preco-combustivels/{id}")
    public ResponseEntity<HistoricoPrecoCombustivelDTO> getHistoricoPrecoCombustivel(@PathVariable Long id) {
        log.debug("REST request to get HistoricoPrecoCombustivel : {}", id);
        Optional<HistoricoPrecoCombustivelDTO> historicoPrecoCombustivelDTO = historicoPrecoCombustivelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(historicoPrecoCombustivelDTO);
    }
    
    @GetMapping("/historico-preco-combustivels/preco-medio-combustivel/{municipio}")
    public ResponseEntity<Double> getPrecoMedioCombustivel(@PathVariable String municipio) {
        log.debug("REST request to get HistoricoPrecoCombustivel : {}", municipio);
        Double precoMedio = historicoPrecoCombustivelService.findAllPrecoMedioCombustivel(municipio);
        return ResponseEntity.ok(precoMedio);
    }
    
    @GetMapping("/historico-preco-combustivels/regiao/{regiao}")
    public ResponseEntity<List<HistoricoPrecoCombustivel>> getAllRegiao(@PathVariable String regiao) {
        log.debug("REST request to get HistoricoPrecoCombustivel : {}", regiao);
        List<HistoricoPrecoCombustivel> historicoPrecoCombustivelDTO = historicoPrecoCombustivelService.findAllRegiao(regiao);
        return ResponseEntity.ok(historicoPrecoCombustivelDTO);
    }
    
    @GetMapping("/historico-preco-combustivels/agrupar-distribuidora")
    public ResponseEntity<List<HistoricoPrecoCombustivel>> getAllAgrupadoDistribuidora() {
        List<HistoricoPrecoCombustivel> historicoPrecoCombustivelDTO = historicoPrecoCombustivelService.findAllAgrupadoDistribuidora();
        return ResponseEntity.ok(historicoPrecoCombustivelDTO);
    }
    
    @GetMapping("/historico-preco-combustivels/agrupar-data-coletada")
    public ResponseEntity<List<HistoricoPrecoCombustivel>> getAllAgrupadoDataColeta() {
        List<HistoricoPrecoCombustivel> historicoPrecoCombustivelDTO = historicoPrecoCombustivelService.findAllAgrupadoDataColeta();
        return ResponseEntity.ok(historicoPrecoCombustivelDTO);
    }
    
    
    @GetMapping("/historico-preco-combustivels/valo-medio-venda-compra-municipio/{municipio}")
    public ResponseEntity<HistoricoPrecoCombustivelVendaCompraDTO> getVendaCompraMunicipio(@PathVariable String municipio) {
        log.debug("REST request to get HistoricoPrecoCombustivel : {}", municipio);
        HistoricoPrecoCombustivelVendaCompraDTO historicoPrecoCombustivelVendaCompraDTO = historicoPrecoCombustivelService.findVendaCompraMunicipio(municipio);
        return ResponseEntity.ok(historicoPrecoCombustivelVendaCompraDTO);
    }
    
    @GetMapping("/historico-preco-combustivels/valo-medio-venda-compra-bandeira/{bandeira}")
    public ResponseEntity<HistoricoPrecoCombustivelVendaCompraDTO> getVendaCompraBndeira(@PathVariable String bandeira) {
        log.debug("REST request to get HistoricoPrecoCombustivel : {}", bandeira);
        HistoricoPrecoCombustivelVendaCompraDTO historicoPrecoCombustivelVendaCompraDTO = historicoPrecoCombustivelService.findVendaCompraBndeira(bandeira);
        return ResponseEntity.ok(historicoPrecoCombustivelVendaCompraDTO);
    }
    
    /**
     * {@code DELETE  /historico-preco-combustivels/:id} : delete the "id" historicoPrecoCombustivel.
     *
     * @param id the id of the historicoPrecoCombustivelDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/historico-preco-combustivels/{id}")
    public ResponseEntity<Void> deleteHistoricoPrecoCombustivel(@PathVariable Long id) {
        log.debug("REST request to delete HistoricoPrecoCombustivel : {}", id);
        historicoPrecoCombustivelService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
    
    @GetMapping("importa-csv")
    public void importaCsv() {
    	historicoPrecoCombustivelService.importaCsv();
    }
}
