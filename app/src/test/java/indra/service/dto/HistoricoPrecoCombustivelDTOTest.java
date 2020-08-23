package indra.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import indra.web.rest.TestUtil;

public class HistoricoPrecoCombustivelDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HistoricoPrecoCombustivelDTO.class);
        HistoricoPrecoCombustivelDTO historicoPrecoCombustivelDTO1 = new HistoricoPrecoCombustivelDTO();
        historicoPrecoCombustivelDTO1.setId(1L);
        HistoricoPrecoCombustivelDTO historicoPrecoCombustivelDTO2 = new HistoricoPrecoCombustivelDTO();
        assertThat(historicoPrecoCombustivelDTO1).isNotEqualTo(historicoPrecoCombustivelDTO2);
        historicoPrecoCombustivelDTO2.setId(historicoPrecoCombustivelDTO1.getId());
        assertThat(historicoPrecoCombustivelDTO1).isEqualTo(historicoPrecoCombustivelDTO2);
        historicoPrecoCombustivelDTO2.setId(2L);
        assertThat(historicoPrecoCombustivelDTO1).isNotEqualTo(historicoPrecoCombustivelDTO2);
        historicoPrecoCombustivelDTO1.setId(null);
        assertThat(historicoPrecoCombustivelDTO1).isNotEqualTo(historicoPrecoCombustivelDTO2);
    }
}
