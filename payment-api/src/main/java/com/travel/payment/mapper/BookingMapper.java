package com.travel.payment.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.travel.payment.dto.BookingDTO;
import com.travel.payment.model.Booking;


@Mapper(componentModel = "spring")
public interface BookingMapper {

    static BookingMapper getInstance() {
        return Mappers.getMapper(BookingMapper.class);
    }

    Booking fromDto(BookingDTO dto);
    
 
    BookingDTO toDto(Booking entity);

//    Authority fromDto(AuthorityDto dto);
//
//    AuthorityDto toDto(GrantedAuthority entity);
}
