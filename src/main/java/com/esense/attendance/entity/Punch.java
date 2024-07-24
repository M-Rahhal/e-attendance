package com.esense.attendance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Punch {

    @Id
    @SequenceGenerator(
            name = "punch_seq",
            sequenceName = "employee_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            generator = "punch_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "employee_id", referencedColumnName = "employee_id"),
            @JoinColumn(name = "attendance_date", referencedColumnName = "attendance_date")
    })
    private Attendance attendance;

    @Column(name = "start_time")
    private Timestamp start;

    @Column(name = "end_time")
    private Timestamp end;
}
