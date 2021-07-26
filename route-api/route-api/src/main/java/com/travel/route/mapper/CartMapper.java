package com.travel.route.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.travel.route.dto.CartDTO;
import com.travel.route.model.Cart;


@Mapper(componentModel = "spring")
public interface CartMapper {

    static CartMapper getInstance() {
        return Mappers.getMapper(CartMapper.class);
    }

    Cart fromDto(CartDTO dto);

    
    @Mappings({
        @Mapping(target="cartId", source="entity.id"), @Mapping(target="seats", source="entity.routeBusSeats")      
        
      })    
    CartDTO toDto(Cart entity);

//    Authority fromDto(AuthorityDto dto);
//
//    AuthorityDto toDto(GrantedAuthority entity);
}
