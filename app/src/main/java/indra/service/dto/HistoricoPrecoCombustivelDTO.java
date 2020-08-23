package indra.service.dto;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * A DTO for the {@link indra.domain.HistoricoPrecoCombustivel} entity.
 */
public class HistoricoPrecoCombustivelDTO implements Serializable {
    
    private Long id;

    private String regiaoSigla;

    private String estadoSigla;

    private String municipio;

    private String revenda;

    private String cnpj;

    private String produto;

    private LocalDate dataColeta;

    private Double valorVenda;

    private Double valorCompra;

    private String unidade;

    private String bandeira;


    private Long userId;

    private String userLogin;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegiaoSigla() {
        return regiaoSigla;
    }

    public void setRegiaoSigla(String regiaoSigla) {
        this.regiaoSigla = regiaoSigla;
    }

    public String getEstadoSigla() {
        return estadoSigla;
    }

    public void setEstadoSigla(String estadoSigla) {
        this.estadoSigla = estadoSigla;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getRevenda() {
        return revenda;
    }

    public void setRevenda(String revenda) {
        this.revenda = revenda;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public LocalDate getDataColeta() {
        return dataColeta;
    }

    public void setDataColeta(LocalDate dataColeta) {
        this.dataColeta = dataColeta;
    }

    public Double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(Double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public Double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(Double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HistoricoPrecoCombustivelDTO)) {
            return false;
        }

        return id != null && id.equals(((HistoricoPrecoCombustivelDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HistoricoPrecoCombustivelDTO{" +
            "id=" + getId() +
            ", regiaoSigla='" + getRegiaoSigla() + "'" +
            ", estadoSigla='" + getEstadoSigla() + "'" +
            ", municipio='" + getMunicipio() + "'" +
            ", revenda='" + getRevenda() + "'" +
            ", cnpj='" + getCnpj() + "'" +
            ", produto='" + getProduto() + "'" +
            ", dataColeta='" + getDataColeta() + "'" +
            ", valorVenda=" + getValorVenda() +
            ", valorCompra=" + getValorCompra() +
            ", unidade='" + getUnidade() + "'" +
            ", bandeira='" + getBandeira() + "'" +
            ", userId=" + getUserId() +
            ", userLogin='" + getUserLogin() + "'" +
            "}";
    }
}
