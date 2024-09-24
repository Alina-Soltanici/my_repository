package com.example.user.model.dto;

import com.example.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String username;
    private String password;

    public UserDto(User user){
        this.username = user.getUsername ();
        this.password = user.getPassword ();
    }
}
