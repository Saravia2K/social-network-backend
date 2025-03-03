package com.mycompany.redsocial.repositories;

import com.mycompany.redsocial.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Integer> {
}