package com.yapp.api.domain.family.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yapp.api.domain.family.persistence.entity.Family;

public interface FamilyRepository extends JpaRepository<Family, Long> {}
