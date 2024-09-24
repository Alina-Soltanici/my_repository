package com.example.user.repository;

import com.example.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> { // <User, Long> user is the entity with which i work, but Long is data type of Id in the entity.

}
