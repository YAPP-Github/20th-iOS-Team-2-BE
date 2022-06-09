package com.yapp.api.domain.calendar.element.visibility.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yapp.api.domain.calendar.element.visibility.persistence.entity.Visibility;

public interface VisibilityRepository extends JpaRepository<Visibility, Long> {}
