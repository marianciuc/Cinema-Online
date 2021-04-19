package com.crudexample.online.dto;

import com.crudexample.online.entity.Role;
import com.crudexample.online.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String backgroundImageUrl;
    private String mainPictureUrl;
    private List<Role> roles;

    public User toUser() {
        User user = new User();

        user.setId(id);
        user.setUsername(username);
        user.setEmail(email);
        user.setBackgroundImageUrl(backgroundImageUrl);
        user.setMainPictureUrl(mainPictureUrl);
        user.setRoles(roles);

        return user;
    }

    public static  UserDto fromUser(User user){
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setBackgroundImageUrl(user.getBackgroundImageUrl());
        userDto.setMainPictureUrl(user.getMainPictureUrl());
        userDto.setRoles(user.getRoles());

        return userDto;
    }
}
