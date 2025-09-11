package com.example.demo.repository;

import com.example.demo.entity.Bodega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface BodegaRepository extends JpaRepository<Bodega, Long> {
    Optional<Bodega> findByCodigo(String codigo);
    List<Bodega> findByActivoTrue();
}