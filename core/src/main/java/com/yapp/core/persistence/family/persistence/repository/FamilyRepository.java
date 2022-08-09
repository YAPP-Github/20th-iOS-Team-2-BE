package com.yapp.core.persistence.family.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yapp.core.persistence.family.persistence.entity.Family;

public interface FamilyRepository extends JpaRepository<Family, Long> {
	Optional<Family> findByCode(String code);
}
