package com.mycompany.redsocial.repositories;

import com.mycompany.redsocial.models.Group;
import com.mycompany.redsocial.models.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserGroupRepository extends JpaRepository<UserGroup, Integer> {
    @Query("SELECT ug.idGroup FROM UserGroup ug WHERE ug.idUsuario.id = :userId")
    List<Group> findGroupsByUserId(@Param("userId") Integer userId);

    // Buscar UserGroup por idGroup y ordenar por idGroup
    @Query("SELECT ug FROM UserGroup ug WHERE ug.idGroup.id = :idGroup ORDER BY ug.idGroup.id")
    List<UserGroup> findUserGroupsByGroupIdOrdered(@Param("idGroup") Integer idGroup);
}