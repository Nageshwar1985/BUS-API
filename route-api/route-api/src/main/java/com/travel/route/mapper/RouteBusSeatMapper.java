package com.travel.route.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.travel.route.dto.CartDTO;
import com.travel.route.dto.RouteBusSeatDTO;
import com.travel.route.model.Cart;
import com.travel.route.model.RouteBusSeat;


@Mapper(componentModel = "spring")
public interface RouteBusSeatMapper {

    static RouteBusSeatMapper getInstance() {
        return Mappers.getMapper(RouteBusSeatMapper.class);
    }

    RouteBusSeat fromDto(RouteBusSeatDTO dto);

    
//    @Mappings({
//       @Mapping(target="passenger", source="entity.passenger")      
//        
//      })    
    RouteBusSeatDTO toDto(RouteBusSeat entity);

//    Authority fromDto(AuthorityDto dto);
//
//    AuthorityDto toDto(GrantedAuthority entity);
}
