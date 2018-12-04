package br.com.connekt.aplicacao.service.mapper;

import br.com.connekt.aplicacao.domain.*;
import br.com.connekt.aplicacao.service.dto.CustomizationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Customization and its DTO CustomizationDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CustomizationMapper extends EntityMapper<CustomizationDTO, Customization> {



    default Customization fromId(Long id) {
        if (id == null) {
            return null;
        }
        Customization customization = new Customization();
        customization.setId(id);
        return customization;
    }
}
