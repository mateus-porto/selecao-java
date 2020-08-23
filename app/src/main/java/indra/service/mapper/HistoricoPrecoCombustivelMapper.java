package indra.service.mapper;


import indra.domain.*;
import indra.service.dto.HistoricoPrecoCombustivelDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link HistoricoPrecoCombustivel} and its DTO {@link HistoricoPrecoCombustivelDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface HistoricoPrecoCombustivelMapper extends EntityMapper<HistoricoPrecoCombustivelDTO, HistoricoPrecoCombustivel> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    HistoricoPrecoCombustivelDTO toDto(HistoricoPrecoCombustivel historicoPrecoCombustivel);

    @Mapping(source = "userId", target = "user")
    HistoricoPrecoCombustivel toEntity(HistoricoPrecoCombustivelDTO historicoPrecoCombustivelDTO);

    default HistoricoPrecoCombustivel fromId(Long id) {
        if (id == null) {
            return null;
        }
        HistoricoPrecoCombustivel historicoPrecoCombustivel = new HistoricoPrecoCombustivel();
        historicoPrecoCombustivel.setId(id);
        return historicoPrecoCombustivel;
    }
}
