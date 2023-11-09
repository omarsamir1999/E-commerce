package com.jobhacker.productservice.model.repository;

import com.jobhacker.productservice.model.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    Optional<Brand> findByBrandNameEqualsIgnoreCase(String brandName);
}