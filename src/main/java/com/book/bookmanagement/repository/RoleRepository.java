package com.book.bookmanagement.repository;

import com.book.bookmanagement.entity.Role;
import com.book.bookmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

    Role findByName(String name);
}
