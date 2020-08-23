package indra.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import indra.web.rest.TestUtil;

public class HistoricoPrecoCombustivelTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HistoricoPrecoCombustivel.class);
        HistoricoPrecoCombustivel historicoPrecoCombustivel1 = new HistoricoPrecoCombustivel();
        historicoPrecoCombustivel1.setId(1L);
        HistoricoPrecoCombustivel historicoPrecoCombustivel2 = new HistoricoPrecoCombustivel();
        historicoPrecoCombustivel2.setId(historicoPrecoCombustivel1.getId());
        assertThat(historicoPrecoCombustivel1).isEqualTo(historicoPrecoCombustivel2);
        historicoPrecoCombustivel2.setId(2L);
        assertThat(historicoPrecoCombustivel1).isNotEqualTo(historicoPrecoCombustivel2);
        historicoPrecoCombustivel1.setId(null);
        assertThat(historicoPrecoCombustivel1).isNotEqualTo(historicoPrecoCombustivel2);
    }
}
