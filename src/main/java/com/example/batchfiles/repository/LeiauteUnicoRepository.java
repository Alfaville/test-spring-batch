package com.example.batchfiles.repository;

import com.example.batchfiles.model.target.LeiauteUnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeiauteUnicoRepository extends JpaRepository<LeiauteUnico, String> {
}
