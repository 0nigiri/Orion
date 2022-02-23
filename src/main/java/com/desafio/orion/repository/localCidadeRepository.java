package com.desafio.orion.repository;

import com.desafio.orion.models.LocalCidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface localCidadeRepository extends JpaRepository<LocalCidade, Long> {
    Optional<LocalCidade> findByUnixtime(int unixtime);


}
