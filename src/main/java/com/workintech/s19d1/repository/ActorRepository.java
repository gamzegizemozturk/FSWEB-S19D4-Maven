package com.workintech.s19d1.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.workintech.s19d1.entity.Actor;

public interface ActorRepository extends JpaRepository<Actor,Long>{
}