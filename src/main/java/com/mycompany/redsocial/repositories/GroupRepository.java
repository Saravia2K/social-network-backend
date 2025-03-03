package com.mycompany.redsocial.repositories;

import com.mycompany.redsocial.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Integer> {

    Optional<Group> findById(Integer integer);


}