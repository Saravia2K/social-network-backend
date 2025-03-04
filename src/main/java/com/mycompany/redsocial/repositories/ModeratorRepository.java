package com.mycompany.redsocial.repositories;

import com.mycompany.redsocial.models.Moderator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ModeratorRepository extends JpaRepository<Moderator, Integer> {
    @Query("SELECT m FROM Moderator m WHERE m.idGroup.id = :idGroup")
    List<Moderator> findModeratorsByGroupId(@Param("idGroup") Integer idGroup);
}