package com.esense.attendance.repository;

import com.esense.attendance.entity.Punch;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PunchRepository extends JpaRepository<Punch, Long> {
}
