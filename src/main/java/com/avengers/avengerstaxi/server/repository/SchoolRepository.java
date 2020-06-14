package com.avengers.avengerstaxi.server.repository;

import com.avengers.avengerstaxi.server.model.SchoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends JpaRepository<SchoolEntity, Long> {
}
