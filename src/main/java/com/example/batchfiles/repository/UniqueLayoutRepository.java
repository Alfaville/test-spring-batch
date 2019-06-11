package com.example.batchfiles.repository;

import com.example.batchfiles.model.target.UniqueLayout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniqueLayoutRepository extends JpaRepository<UniqueLayout, String> {
}
