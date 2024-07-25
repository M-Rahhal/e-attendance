package com.esense.attendance.entity;

import com.esense.attendance.entity.key.AttendanceId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Attendance {

    @EmbeddedId
    private AttendanceId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", referencedColumnName = "id" ,nullable = false , insertable = false, updatable = false)
    private Employee employee;

    @OneToMany(mappedBy = "attendance", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Punch> punches;

    public void addPunch(Punch punch) {
        if (punch != null)
            punches.add(punch);
    }

}
