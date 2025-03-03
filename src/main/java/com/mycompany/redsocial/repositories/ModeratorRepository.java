package com.mycompany.redsocial.repositories;

import com.mycompany.redsocial.models.Moderator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeratorRepository extends JpaRepository<Moderator, Integer> {
}