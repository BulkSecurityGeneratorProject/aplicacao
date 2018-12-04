package br.com.connekt.aplicacao.service.mapper;

import br.com.connekt.aplicacao.domain.*;
import br.com.connekt.aplicacao.service.dto.RequestsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Requests and its DTO RequestsDTO.
 */
@Mapper(componentModel = "spring", uses = {OpportunitiesMapper.class})
public interface RequestsMapper extends EntityMapper<RequestsDTO, Requests> {

    @Mapping(source = "opportunities.id", target = "opportunitiesId")
    RequestsDTO toDto(Requests requests);

    @Mapping(source = "opportunitiesId", target = "opportunities")
    Requests toEntity(RequestsDTO requestsDTO);

    default Requests fromId(Long id) {
        if (id == null) {
            return null;
        }
        Requests requests = new Requests();
        requests.setId(id);
        return requests;
    }
}
