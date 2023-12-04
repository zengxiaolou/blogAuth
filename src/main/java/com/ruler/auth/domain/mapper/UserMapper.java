package com.ruler.auth.domain.mapper;

import com.ruler.auth.domain.model.RegisterBO;
import com.ruler.auth.entity.User;
import com.ruler.auth.presentation.dto.UserCreateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    RegisterBO userDtoToRegisterBO(UserCreateDto userCreateDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "provider", ignore = true)
    @Mapping(target = "providerId", ignore = true)
    User RegisterBOToUser(RegisterBO registerBO);
}
