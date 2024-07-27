package com.esense.attendance.repository;

import com.esense.attendance.entity.Attendance;
import com.esense.attendance.entity.key.AttendanceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, AttendanceId> {

    @Query("SELECT MAX(T.id.attendanceDate) FROM Attendance T WHERE T.id.employeeId =:id ")
    Date findNewestDate(@Param("id") Long id);
}
