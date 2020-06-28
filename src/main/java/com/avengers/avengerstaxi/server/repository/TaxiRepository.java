package com.avengers.avengerstaxi.server.repository;

import com.avengers.avengerstaxi.server.model.TaxiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxiRepository extends JpaRepository<TaxiEntity, Long> {

}
