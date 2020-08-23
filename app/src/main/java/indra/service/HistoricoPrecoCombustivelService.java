package indra.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import indra.domain.HistoricoPrecoCombustivel;
import indra.repository.HistoricoPrecoCombustivelRepository;
import indra.service.dto.HistoricoPrecoCombustivelDTO;
import indra.service.dto.HistoricoPrecoCombustivelVendaCompraDTO;
import indra.service.mapper.HistoricoPrecoCombustivelMapper;

/**
 * Service Implementation for managing {@link HistoricoPrecoCombustivel}.
 */
@Service
@Transactional
public class HistoricoPrecoCombustivelService {

    private final Logger log = LoggerFactory.getLogger(HistoricoPrecoCombustivelService.class);

    private final HistoricoPrecoCombustivelRepository historicoPrecoCombustivelRepository;

    private final HistoricoPrecoCombustivelMapper historicoPrecoCombustivelMapper;
    
    
    String line="";

    public HistoricoPrecoCombustivelService(HistoricoPrecoCombustivelRepository historicoPrecoCombustivelRepository, HistoricoPrecoCombustivelMapper historicoPrecoCombustivelMapper) {
        this.historicoPrecoCombustivelRepository = historicoPrecoCombustivelRepository;
        this.historicoPrecoCombustivelMapper = historicoPrecoCombustivelMapper;
    }

    /**
     * Save a historicoPrecoCombustivel.
     *
     * @param historicoPrecoCombustivelDTO the entity to save.
     * @return the persisted entity.
     */
    public HistoricoPrecoCombustivelDTO save(HistoricoPrecoCombustivelDTO historicoPrecoCombustivelDTO) {
        log.debug("Request to save HistoricoPrecoCombustivel : {}", historicoPrecoCombustivelDTO);
        HistoricoPrecoCombustivel historicoPrecoCombustivel = historicoPrecoCombustivelMapper.toEntity(historicoPrecoCombustivelDTO);
        historicoPrecoCombustivel = historicoPrecoCombustivelRepository.save(historicoPrecoCombustivel);
        return historicoPrecoCombustivelMapper.toDto(historicoPrecoCombustivel);
    }

    /**
     * Get all the historicoPrecoCombustivels.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<HistoricoPrecoCombustivelDTO> findAll(Pageable pageable) {
        log.debug("Request to get all HistoricoPrecoCombustivels");
        return historicoPrecoCombustivelRepository.findAll(pageable)
            .map(historicoPrecoCombustivelMapper::toDto);
    }


    /**
     * Get one historicoPrecoCombustivel by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<HistoricoPrecoCombustivelDTO> findOne(Long id) {
        log.debug("Request to get HistoricoPrecoCombustivel : {}", id);
        return historicoPrecoCombustivelRepository.findById(id)
            .map(historicoPrecoCombustivelMapper::toDto);
    }

    /**
     * Delete the historicoPrecoCombustivel by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete HistoricoPrecoCombustivel : {}", id);
        historicoPrecoCombustivelRepository.deleteById(id);
    }
    
    @Async
    @Transactional
    public void importaCsv() {
    	
    	try {
			BufferedReader br= new BufferedReader(new InputStreamReader (HistoricoPrecoCombustivelService.class.getResourceAsStream("/csvIndra.csv")));
			
			List<HistoricoPrecoCombustivel> historicoPrecoCombustivel = new LinkedList<HistoricoPrecoCombustivel>();
			while((line=br.readLine())!=null) {
				
				String [] data=line.split(";");
				
				 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				 
				HistoricoPrecoCombustivel csvImportado = new HistoricoPrecoCombustivel();
				csvImportado.setRegiaoSigla(data[0]);
				csvImportado.setEstadoSigla(data[1]);
				csvImportado.setMunicipio(data[2]);
				csvImportado.setRevenda(data[3]);
				csvImportado.setCnpj(data[4]);
				csvImportado.setProduto(data[5]);
				converteDataColetada(data, formatter, csvImportado);
				converteValorVenda(data,csvImportado);
				converteValorCompra(data,csvImportado);
				csvImportado.setUnidade(data[9]);
				csvImportado.setBandeira(data[10]);
				historicoPrecoCombustivel.add(csvImportado);	
			}
			historicoPrecoCombustivelRepository.saveAll(historicoPrecoCombustivel);
		} catch (IOException e) {
					e.printStackTrace();
		}
    }

	private void converteDataColetada(String[] data, DateTimeFormatter formatter,
			HistoricoPrecoCombustivel csvImportado) {
		if(!data[6].isEmpty() && data[6] != null) {
			csvImportado.setDataColeta(LocalDate.parse (data[6],formatter));
		}
	}

	private void converteValorVenda(String[] data, HistoricoPrecoCombustivel csvImportado) {
		if(!data[7].isEmpty() && data[7] != null) {
			csvImportado.setValorVenda(Double.parseDouble(data[7].replace(",",".")));
		}
	}

	private void converteValorCompra(String[] data, HistoricoPrecoCombustivel csvImportado) {
		if(!data[8].isEmpty() && data[8] != null) {
			csvImportado.setValorCompra(Double.parseDouble(data[8].replace(",",".")));
		}
	}

	public Double findAllPrecoMedioCombustivel(String municipio) {
		return historicoPrecoCombustivelRepository.findAllPrecoMedioCombustivel(municipio);
	}

	public List<HistoricoPrecoCombustivel> findAllRegiao(String regiao) {
		return historicoPrecoCombustivelRepository.findAllRegiao(regiao);
	}

	public HistoricoPrecoCombustivelVendaCompraDTO findVendaCompraMunicipio(String municipio) {
		return historicoPrecoCombustivelRepository.findVendaCompraMunicipio(municipio);
	}

	public HistoricoPrecoCombustivelVendaCompraDTO findVendaCompraBndeira(String bandeira) {
		return historicoPrecoCombustivelRepository.findVendaCompraBndeira(bandeira);
	}

	public List<HistoricoPrecoCombustivel> findAllAgrupadoDistribuidora() {
		return historicoPrecoCombustivelRepository.findAllAgrupadoDistribuidora();
	}

	public List<HistoricoPrecoCombustivel> findAllAgrupadoDataColeta() {
		return historicoPrecoCombustivelRepository.findAllAgrupadoDataColeta();
	}
	
}
