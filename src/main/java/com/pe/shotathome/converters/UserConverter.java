package com.pe.shotathome.converters;

import com.pe.shotathome.dto.UserDto;
import com.pe.shotathome.entity.User;

public class UserConverter {
    public static User DtoToEntity(UserDto usr) {
        User user=new User();
        user.setUserName(usr.getUserName());
        user.setEmail(usr.getEmail());
        return user;
    }
    public static UserDto EntityToDto(User usr) {
        UserDto userDto=new UserDto();
        userDto.setUserName(usr.getUserName());
        userDto.setEmail(usr.getEmail());
        return userDto;
    }
}
