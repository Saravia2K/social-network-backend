package com.mycompany.redsocial.repositories;

import com.mycompany.redsocial.models.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupRepository extends JpaRepository<UserGroup, Integer> {
}