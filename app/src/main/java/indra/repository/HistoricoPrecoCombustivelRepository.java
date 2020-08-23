package indra.repository;

import indra.domain.HistoricoPrecoCombustivel;
import indra.service.dto.HistoricoPrecoCombustivelDTO;
import indra.service.dto.HistoricoPrecoCombustivelVendaCompraDTO;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the HistoricoPrecoCombustivel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HistoricoPrecoCombustivelRepository extends JpaRepository<HistoricoPrecoCombustivel, Long> {

    @Query("select historicoPrecoCombustivel from HistoricoPrecoCombustivel historicoPrecoCombustivel where historicoPrecoCombustivel.user.login = ?#{principal.username}")
    List<HistoricoPrecoCombustivel> findByUserIsCurrentUser();
    
    
    @Query(value = " SELECT avg(hpc.valor_COMPRA ) FROM HISTORICO_PRECO_COMBUSTIVEL hpc where hpc.MUNICIPIO  = trim(?1)", 	      
    	       nativeQuery = true)
	Double findAllPrecoMedioCombustivel(String municipio);

    @Query(value = " SELECT * FROM HISTORICO_PRECO_COMBUSTIVEL hpc where hpc.REGIAO_SIGLA  = trim( ?1 ) ",  	      
 	       nativeQuery = true)
    List<HistoricoPrecoCombustivel> findAllRegiao(String regiao);
    
    @Query(value = " SELECT * FROM HISTORICO_PRECO_COMBUSTIVEL hpc ORDER BY hpc.revenda ",   	      
  	       nativeQuery = true)
    List<HistoricoPrecoCombustivel> findAllAgrupadoDistribuidora();

    @Query(value = " SELECT * FROM HISTORICO_PRECO_COMBUSTIVEL hpc ORDER BY hpc.data_coleta ",  	      
  	       nativeQuery = true)
	List<HistoricoPrecoCombustivel> findAllAgrupadoDataColeta();
    
    @Query(value = " SELECT avg(hpc.valor_VENDA) as valorVenda  ,avg(hpc.valor_COMPRA) as  valorCompra  FROM HISTORICO_PRECO_COMBUSTIVEL hpc where hpc.MUNICIPIO = trim( ?1 ) ", 	      
  	       nativeQuery = true)
	HistoricoPrecoCombustivelVendaCompraDTO findVendaCompraMunicipio(String municipio);

    @Query(value = " SELECT avg(hpc.valor_VENDA) as valorVenda  ,avg(hpc.valor_COMPRA) as  valorCompra  FROM HISTORICO_PRECO_COMBUSTIVEL hpc where hpc.BANDEIRA  = trim( ?1 ) ", 	      
  	       nativeQuery = true)
    HistoricoPrecoCombustivelVendaCompraDTO findVendaCompraBndeira(String bandeira);


	
}
