package com.esense.attendance.repository;

import com.esense.attendance.entity.Attendance;
import com.esense.attendance.entity.key.AttendanceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, AttendanceId> {
}
