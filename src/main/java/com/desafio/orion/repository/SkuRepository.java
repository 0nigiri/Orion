package com.desafio.orion.repository;

import com.desafio.orion.models.Sku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkuRepository extends JpaRepository<Sku, Long> {
    Optional<Sku> findBySkuString(String sku);
}
