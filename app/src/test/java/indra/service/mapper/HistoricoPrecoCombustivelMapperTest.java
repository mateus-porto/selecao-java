package indra.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HistoricoPrecoCombustivelMapperTest {

    private HistoricoPrecoCombustivelMapper historicoPrecoCombustivelMapper;

    @BeforeEach
    public void setUp() {
        historicoPrecoCombustivelMapper = new HistoricoPrecoCombustivelMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(historicoPrecoCombustivelMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(historicoPrecoCombustivelMapper.fromId(null)).isNull();
    }
}
