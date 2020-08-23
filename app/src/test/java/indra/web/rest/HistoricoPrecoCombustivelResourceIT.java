package indra.web.rest;

import indra.AppApp;
import indra.domain.HistoricoPrecoCombustivel;
import indra.repository.HistoricoPrecoCombustivelRepository;
import indra.service.HistoricoPrecoCombustivelService;
import indra.service.dto.HistoricoPrecoCombustivelDTO;
import indra.service.mapper.HistoricoPrecoCombustivelMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link HistoricoPrecoCombustivelResource} REST controller.
 */
@SpringBootTest(classes = AppApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class HistoricoPrecoCombustivelResourceIT {

    private static final String DEFAULT_REGIAO_SIGLA = "AAAAAAAAAA";
    private static final String UPDATED_REGIAO_SIGLA = "BBBBBBBBBB";

    private static final String DEFAULT_ESTADO_SIGLA = "AAAAAAAAAA";
    private static final String UPDATED_ESTADO_SIGLA = "BBBBBBBBBB";

    private static final String DEFAULT_MUNICIPIO = "AAAAAAAAAA";
    private static final String UPDATED_MUNICIPIO = "BBBBBBBBBB";

    private static final String DEFAULT_REVENDA = "AAAAAAAAAA";
    private static final String UPDATED_REVENDA = "BBBBBBBBBB";

    private static final String DEFAULT_CNPJ = "AAAAAAAAAA";
    private static final String UPDATED_CNPJ = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUTO = "AAAAAAAAAA";
    private static final String UPDATED_PRODUTO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATA_COLETA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_COLETA = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_VALOR_VENDA = 1D;
    private static final Double UPDATED_VALOR_VENDA = 2D;

    private static final Double DEFAULT_VALOR_COMPRA = 1D;
    private static final Double UPDATED_VALOR_COMPRA = 2D;

    private static final String DEFAULT_UNIDADE = "AAAAAAAAAA";
    private static final String UPDATED_UNIDADE = "BBBBBBBBBB";

    private static final String DEFAULT_BANDEIRA = "AAAAAAAAAA";
    private static final String UPDATED_BANDEIRA = "BBBBBBBBBB";

    @Autowired
    private HistoricoPrecoCombustivelRepository historicoPrecoCombustivelRepository;

    @Autowired
    private HistoricoPrecoCombustivelMapper historicoPrecoCombustivelMapper;

    @Autowired
    private HistoricoPrecoCombustivelService historicoPrecoCombustivelService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restHistoricoPrecoCombustivelMockMvc;

    private HistoricoPrecoCombustivel historicoPrecoCombustivel;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HistoricoPrecoCombustivel createEntity(EntityManager em) {
        HistoricoPrecoCombustivel historicoPrecoCombustivel = new HistoricoPrecoCombustivel()
            .regiaoSigla(DEFAULT_REGIAO_SIGLA)
            .estadoSigla(DEFAULT_ESTADO_SIGLA)
            .municipio(DEFAULT_MUNICIPIO)
            .revenda(DEFAULT_REVENDA)
            .cnpj(DEFAULT_CNPJ)
            .produto(DEFAULT_PRODUTO)
            .dataColeta(DEFAULT_DATA_COLETA)
            .valorVenda(DEFAULT_VALOR_VENDA)
            .valorCompra(DEFAULT_VALOR_COMPRA)
            .unidade(DEFAULT_UNIDADE)
            .bandeira(DEFAULT_BANDEIRA);
        return historicoPrecoCombustivel;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HistoricoPrecoCombustivel createUpdatedEntity(EntityManager em) {
        HistoricoPrecoCombustivel historicoPrecoCombustivel = new HistoricoPrecoCombustivel()
            .regiaoSigla(UPDATED_REGIAO_SIGLA)
            .estadoSigla(UPDATED_ESTADO_SIGLA)
            .municipio(UPDATED_MUNICIPIO)
            .revenda(UPDATED_REVENDA)
            .cnpj(UPDATED_CNPJ)
            .produto(UPDATED_PRODUTO)
            .dataColeta(UPDATED_DATA_COLETA)
            .valorVenda(UPDATED_VALOR_VENDA)
            .valorCompra(UPDATED_VALOR_COMPRA)
            .unidade(UPDATED_UNIDADE)
            .bandeira(UPDATED_BANDEIRA);
        return historicoPrecoCombustivel;
    }

    @BeforeEach
    public void initTest() {
        historicoPrecoCombustivel = createEntity(em);
    }

    @Test
    @Transactional
    public void createHistoricoPrecoCombustivel() throws Exception {
        int databaseSizeBeforeCreate = historicoPrecoCombustivelRepository.findAll().size();
        // Create the HistoricoPrecoCombustivel
        HistoricoPrecoCombustivelDTO historicoPrecoCombustivelDTO = historicoPrecoCombustivelMapper.toDto(historicoPrecoCombustivel);
        restHistoricoPrecoCombustivelMockMvc.perform(post("/api/historico-preco-combustivels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(historicoPrecoCombustivelDTO)))
            .andExpect(status().isCreated());

        // Validate the HistoricoPrecoCombustivel in the database
        List<HistoricoPrecoCombustivel> historicoPrecoCombustivelList = historicoPrecoCombustivelRepository.findAll();
        assertThat(historicoPrecoCombustivelList).hasSize(databaseSizeBeforeCreate + 1);
        HistoricoPrecoCombustivel testHistoricoPrecoCombustivel = historicoPrecoCombustivelList.get(historicoPrecoCombustivelList.size() - 1);
        assertThat(testHistoricoPrecoCombustivel.getRegiaoSigla()).isEqualTo(DEFAULT_REGIAO_SIGLA);
        assertThat(testHistoricoPrecoCombustivel.getEstadoSigla()).isEqualTo(DEFAULT_ESTADO_SIGLA);
        assertThat(testHistoricoPrecoCombustivel.getMunicipio()).isEqualTo(DEFAULT_MUNICIPIO);
        assertThat(testHistoricoPrecoCombustivel.getRevenda()).isEqualTo(DEFAULT_REVENDA);
        assertThat(testHistoricoPrecoCombustivel.getCnpj()).isEqualTo(DEFAULT_CNPJ);
        assertThat(testHistoricoPrecoCombustivel.getProduto()).isEqualTo(DEFAULT_PRODUTO);
        assertThat(testHistoricoPrecoCombustivel.getDataColeta()).isEqualTo(DEFAULT_DATA_COLETA);
        assertThat(testHistoricoPrecoCombustivel.getValorVenda()).isEqualTo(DEFAULT_VALOR_VENDA);
        assertThat(testHistoricoPrecoCombustivel.getValorCompra()).isEqualTo(DEFAULT_VALOR_COMPRA);
        assertThat(testHistoricoPrecoCombustivel.getUnidade()).isEqualTo(DEFAULT_UNIDADE);
        assertThat(testHistoricoPrecoCombustivel.getBandeira()).isEqualTo(DEFAULT_BANDEIRA);
    }

    @Test
    @Transactional
    public void createHistoricoPrecoCombustivelWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = historicoPrecoCombustivelRepository.findAll().size();

        // Create the HistoricoPrecoCombustivel with an existing ID
        historicoPrecoCombustivel.setId(1L);
        HistoricoPrecoCombustivelDTO historicoPrecoCombustivelDTO = historicoPrecoCombustivelMapper.toDto(historicoPrecoCombustivel);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHistoricoPrecoCombustivelMockMvc.perform(post("/api/historico-preco-combustivels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(historicoPrecoCombustivelDTO)))
            .andExpect(status().isBadRequest());

        // Validate the HistoricoPrecoCombustivel in the database
        List<HistoricoPrecoCombustivel> historicoPrecoCombustivelList = historicoPrecoCombustivelRepository.findAll();
        assertThat(historicoPrecoCombustivelList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllHistoricoPrecoCombustivels() throws Exception {
        // Initialize the database
        historicoPrecoCombustivelRepository.saveAndFlush(historicoPrecoCombustivel);

        // Get all the historicoPrecoCombustivelList
        restHistoricoPrecoCombustivelMockMvc.perform(get("/api/historico-preco-combustivels?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(historicoPrecoCombustivel.getId().intValue())))
            .andExpect(jsonPath("$.[*].regiaoSigla").value(hasItem(DEFAULT_REGIAO_SIGLA)))
            .andExpect(jsonPath("$.[*].estadoSigla").value(hasItem(DEFAULT_ESTADO_SIGLA)))
            .andExpect(jsonPath("$.[*].municipio").value(hasItem(DEFAULT_MUNICIPIO)))
            .andExpect(jsonPath("$.[*].revenda").value(hasItem(DEFAULT_REVENDA)))
            .andExpect(jsonPath("$.[*].cnpj").value(hasItem(DEFAULT_CNPJ)))
            .andExpect(jsonPath("$.[*].produto").value(hasItem(DEFAULT_PRODUTO)))
            .andExpect(jsonPath("$.[*].dataColeta").value(hasItem(DEFAULT_DATA_COLETA.toString())))
            .andExpect(jsonPath("$.[*].valorVenda").value(hasItem(DEFAULT_VALOR_VENDA.doubleValue())))
            .andExpect(jsonPath("$.[*].valorCompra").value(hasItem(DEFAULT_VALOR_COMPRA.doubleValue())))
            .andExpect(jsonPath("$.[*].unidade").value(hasItem(DEFAULT_UNIDADE)))
            .andExpect(jsonPath("$.[*].bandeira").value(hasItem(DEFAULT_BANDEIRA)));
    }
    
    @Test
    @Transactional
    public void getHistoricoPrecoCombustivel() throws Exception {
        // Initialize the database
        historicoPrecoCombustivelRepository.saveAndFlush(historicoPrecoCombustivel);

        // Get the historicoPrecoCombustivel
        restHistoricoPrecoCombustivelMockMvc.perform(get("/api/historico-preco-combustivels/{id}", historicoPrecoCombustivel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(historicoPrecoCombustivel.getId().intValue()))
            .andExpect(jsonPath("$.regiaoSigla").value(DEFAULT_REGIAO_SIGLA))
            .andExpect(jsonPath("$.estadoSigla").value(DEFAULT_ESTADO_SIGLA))
            .andExpect(jsonPath("$.municipio").value(DEFAULT_MUNICIPIO))
            .andExpect(jsonPath("$.revenda").value(DEFAULT_REVENDA))
            .andExpect(jsonPath("$.cnpj").value(DEFAULT_CNPJ))
            .andExpect(jsonPath("$.produto").value(DEFAULT_PRODUTO))
            .andExpect(jsonPath("$.dataColeta").value(DEFAULT_DATA_COLETA.toString()))
            .andExpect(jsonPath("$.valorVenda").value(DEFAULT_VALOR_VENDA.doubleValue()))
            .andExpect(jsonPath("$.valorCompra").value(DEFAULT_VALOR_COMPRA.doubleValue()))
            .andExpect(jsonPath("$.unidade").value(DEFAULT_UNIDADE))
            .andExpect(jsonPath("$.bandeira").value(DEFAULT_BANDEIRA));
    }
    @Test
    @Transactional
    public void getNonExistingHistoricoPrecoCombustivel() throws Exception {
        // Get the historicoPrecoCombustivel
        restHistoricoPrecoCombustivelMockMvc.perform(get("/api/historico-preco-combustivels/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHistoricoPrecoCombustivel() throws Exception {
        // Initialize the database
        historicoPrecoCombustivelRepository.saveAndFlush(historicoPrecoCombustivel);

        int databaseSizeBeforeUpdate = historicoPrecoCombustivelRepository.findAll().size();

        // Update the historicoPrecoCombustivel
        HistoricoPrecoCombustivel updatedHistoricoPrecoCombustivel = historicoPrecoCombustivelRepository.findById(historicoPrecoCombustivel.getId()).get();
        // Disconnect from session so that the updates on updatedHistoricoPrecoCombustivel are not directly saved in db
        em.detach(updatedHistoricoPrecoCombustivel);
        updatedHistoricoPrecoCombustivel
            .regiaoSigla(UPDATED_REGIAO_SIGLA)
            .estadoSigla(UPDATED_ESTADO_SIGLA)
            .municipio(UPDATED_MUNICIPIO)
            .revenda(UPDATED_REVENDA)
            .cnpj(UPDATED_CNPJ)
            .produto(UPDATED_PRODUTO)
            .dataColeta(UPDATED_DATA_COLETA)
            .valorVenda(UPDATED_VALOR_VENDA)
            .valorCompra(UPDATED_VALOR_COMPRA)
            .unidade(UPDATED_UNIDADE)
            .bandeira(UPDATED_BANDEIRA);
        HistoricoPrecoCombustivelDTO historicoPrecoCombustivelDTO = historicoPrecoCombustivelMapper.toDto(updatedHistoricoPrecoCombustivel);

        restHistoricoPrecoCombustivelMockMvc.perform(put("/api/historico-preco-combustivels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(historicoPrecoCombustivelDTO)))
            .andExpect(status().isOk());

        // Validate the HistoricoPrecoCombustivel in the database
        List<HistoricoPrecoCombustivel> historicoPrecoCombustivelList = historicoPrecoCombustivelRepository.findAll();
        assertThat(historicoPrecoCombustivelList).hasSize(databaseSizeBeforeUpdate);
        HistoricoPrecoCombustivel testHistoricoPrecoCombustivel = historicoPrecoCombustivelList.get(historicoPrecoCombustivelList.size() - 1);
        assertThat(testHistoricoPrecoCombustivel.getRegiaoSigla()).isEqualTo(UPDATED_REGIAO_SIGLA);
        assertThat(testHistoricoPrecoCombustivel.getEstadoSigla()).isEqualTo(UPDATED_ESTADO_SIGLA);
        assertThat(testHistoricoPrecoCombustivel.getMunicipio()).isEqualTo(UPDATED_MUNICIPIO);
        assertThat(testHistoricoPrecoCombustivel.getRevenda()).isEqualTo(UPDATED_REVENDA);
        assertThat(testHistoricoPrecoCombustivel.getCnpj()).isEqualTo(UPDATED_CNPJ);
        assertThat(testHistoricoPrecoCombustivel.getProduto()).isEqualTo(UPDATED_PRODUTO);
        assertThat(testHistoricoPrecoCombustivel.getDataColeta()).isEqualTo(UPDATED_DATA_COLETA);
        assertThat(testHistoricoPrecoCombustivel.getValorVenda()).isEqualTo(UPDATED_VALOR_VENDA);
        assertThat(testHistoricoPrecoCombustivel.getValorCompra()).isEqualTo(UPDATED_VALOR_COMPRA);
        assertThat(testHistoricoPrecoCombustivel.getUnidade()).isEqualTo(UPDATED_UNIDADE);
        assertThat(testHistoricoPrecoCombustivel.getBandeira()).isEqualTo(UPDATED_BANDEIRA);
    }

    @Test
    @Transactional
    public void updateNonExistingHistoricoPrecoCombustivel() throws Exception {
        int databaseSizeBeforeUpdate = historicoPrecoCombustivelRepository.findAll().size();

        // Create the HistoricoPrecoCombustivel
        HistoricoPrecoCombustivelDTO historicoPrecoCombustivelDTO = historicoPrecoCombustivelMapper.toDto(historicoPrecoCombustivel);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHistoricoPrecoCombustivelMockMvc.perform(put("/api/historico-preco-combustivels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(historicoPrecoCombustivelDTO)))
            .andExpect(status().isBadRequest());

        // Validate the HistoricoPrecoCombustivel in the database
        List<HistoricoPrecoCombustivel> historicoPrecoCombustivelList = historicoPrecoCombustivelRepository.findAll();
        assertThat(historicoPrecoCombustivelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteHistoricoPrecoCombustivel() throws Exception {
        // Initialize the database
        historicoPrecoCombustivelRepository.saveAndFlush(historicoPrecoCombustivel);

        int databaseSizeBeforeDelete = historicoPrecoCombustivelRepository.findAll().size();

        // Delete the historicoPrecoCombustivel
        restHistoricoPrecoCombustivelMockMvc.perform(delete("/api/historico-preco-combustivels/{id}", historicoPrecoCombustivel.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<HistoricoPrecoCombustivel> historicoPrecoCombustivelList = historicoPrecoCombustivelRepository.findAll();
        assertThat(historicoPrecoCombustivelList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
