package indra.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A HistoricoPrecoCombustivel.
 */
@Entity
@Table(name = "historico_preco_combustivel")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class HistoricoPrecoCombustivel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "regiao_sigla")
    private String regiaoSigla;

    @Column(name = "estado_sigla")
    private String estadoSigla;

    @Column(name = "municipio")
    private String municipio;

    @Column(name = "revenda")
    private String revenda;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "produto")
    private String produto;

    @Column(name = "data_coleta")
    private LocalDate dataColeta;

    @Column(name = "valor_venda")
    private Double valorVenda;

    @Column(name = "valor_compra")
    private Double valorCompra;

    @Column(name = "unidade")
    private String unidade;

    @Column(name = "bandeira")
    private String bandeira;

    @ManyToOne
    @JsonIgnoreProperties(value = "historicoPrecoCombustivels", allowSetters = true)
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegiaoSigla() {
        return regiaoSigla;
    }

    public HistoricoPrecoCombustivel regiaoSigla(String regiaoSigla) {
        this.regiaoSigla = regiaoSigla;
        return this;
    }

    public void setRegiaoSigla(String regiaoSigla) {
        this.regiaoSigla = regiaoSigla;
    }

    public String getEstadoSigla() {
        return estadoSigla;
    }

    public HistoricoPrecoCombustivel estadoSigla(String estadoSigla) {
        this.estadoSigla = estadoSigla;
        return this;
    }

    public void setEstadoSigla(String estadoSigla) {
        this.estadoSigla = estadoSigla;
    }

    public String getMunicipio() {
        return municipio;
    }

    public HistoricoPrecoCombustivel municipio(String municipio) {
        this.municipio = municipio;
        return this;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getRevenda() {
        return revenda;
    }

    public HistoricoPrecoCombustivel revenda(String revenda) {
        this.revenda = revenda;
        return this;
    }

    public void setRevenda(String revenda) {
        this.revenda = revenda;
    }

    public String getCnpj() {
        return cnpj;
    }

    public HistoricoPrecoCombustivel cnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getProduto() {
        return produto;
    }

    public HistoricoPrecoCombustivel produto(String produto) {
        this.produto = produto;
        return this;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public LocalDate getDataColeta() {
        return dataColeta;
    }

    public HistoricoPrecoCombustivel dataColeta(LocalDate dataColeta) {
        this.dataColeta = dataColeta;
        return this;
    }

    public void setDataColeta(LocalDate dataColeta) {
        this.dataColeta = dataColeta;
    }

    public Double getValorVenda() {
        return valorVenda;
    }

    public HistoricoPrecoCombustivel valorVenda(Double valorVenda) {
        this.valorVenda = valorVenda;
        return this;
    }

    public void setValorVenda(Double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public Double getValorCompra() {
        return valorCompra;
    }

    public HistoricoPrecoCombustivel valorCompra(Double valorCompra) {
        this.valorCompra = valorCompra;
        return this;
    }

    public void setValorCompra(Double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public String getUnidade() {
        return unidade;
    }

    public HistoricoPrecoCombustivel unidade(String unidade) {
        this.unidade = unidade;
        return this;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getBandeira() {
        return bandeira;
    }

    public HistoricoPrecoCombustivel bandeira(String bandeira) {
        this.bandeira = bandeira;
        return this;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public User getUser() {
        return user;
    }

    public HistoricoPrecoCombustivel user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HistoricoPrecoCombustivel)) {
            return false;
        }
        return id != null && id.equals(((HistoricoPrecoCombustivel) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HistoricoPrecoCombustivel{" +
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
            "}";
    }
}
