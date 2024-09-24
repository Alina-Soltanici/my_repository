package com.example.user.service;

import com.example.user.exceptions.UserNotFoundException;
import com.example.user.model.dto.UserDto;
import com.example.user.model.entity.User;
import com.example.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*
Spring nu creează un obiect direct pentru interfața UserRepository,
ci generează o clasă concretă la runtime care implementează această
interfață. Astfel, obții un obiect al acelei clase concrete, care
poate fi folosit în UserService.Când folosești @Autowired, Spring va
injecta această implementare în UserService.
 */

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User addUser(UserDto userDto){
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        return userRepository.save (user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById (id);
    }

    public User updateUser(Long id, UserDto userDto) throws UserNotFoundException {
        User user = userRepository.findById (id)
                .orElseThrow (() -> new UserNotFoundException ("User with given id: " + id + " doesnt exist."));
        user.setUsername (userDto.getUsername ());
        user.setPassword (userDto.getPassword ());
        return userRepository.save (user);
    }

    public UserDto getUser(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow (() -> new UserNotFoundException ("User with given id: " + id + " doesn't exist." ));
        return new UserDto (user);
    }

    /*
    getAllUsers() method:
    1.Get all users from the database.
    2.Turns each user into a UserDto.
    3.Returns a list of UserDto.
     */
    public List<UserDto> getAllUsers(){
        List<User> users = userRepository.findAll();
        return users.stream ()
                .map (user -> new UserDto (user.getUsername(), user.getPassword ()))
                .collect (Collectors.toList ());
    }
}
