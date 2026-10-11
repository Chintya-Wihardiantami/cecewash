package com.cecewash.repository;

import com.cecewash.model.Laundry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LaundryRepository extends JpaRepository<Laundry, Long> {

    List<Laundry> findAllByOrderByIdDesc();

    List<Laundry> findByNamaPelangganContainingIgnoreCaseOrNomorHpContainingOrderByIdDesc(
            String namaPelanggan,
            String nomorHp
    );
}
