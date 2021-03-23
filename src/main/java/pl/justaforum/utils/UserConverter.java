package pl.justaforum.utils;

import pl.justaforum.model.UserDto;
import pl.justaforum.model.UserRegistrationDto;
import pl.justaforum.persistence.entity.User;

public class UserConverter {

    private UserConverter() {}

    public static User createUser(UserRegistrationDto source){
        return User.builder()
                .username(source.getUsername())
                .email(source.getEmail())
                .password(source.getPassword())
                .build();
    }

    public static UserDto createUserDto(User source){
        return UserDto.builder()
        .id(source.getId())
        .username(source.getUsername())
        .email(source.getEmail())
        .posts(source.getPosts())
        .password(source.getPassword())
        .build();
    }


}
