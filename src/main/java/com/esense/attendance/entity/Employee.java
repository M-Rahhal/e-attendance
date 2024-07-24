package com.esense.attendance.entity;

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
public class Employee {

    @Id
    @SequenceGenerator(
            name = "employee_seq",
            sequenceName = "employee_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            generator = "employee_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;

    @Column(
            name = "first_name",
            nullable = false,
            length = 50
    )
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false,
            length = 50
    )
    private String lastName;

    @Column(
            name = "email",
            unique = true,
            nullable = false,
            length = 50
    )
    private String email;

    @Column(
            name = "password",
            nullable = false,
            length = 50
    )
    private String password;

    @Column(
            name = "phone_number",
            unique = true,
            nullable = false,
            length = 10
    )
    private String phoneNumber;

    @Column(
            name = "gender",
            nullable = false,
            length = 50
    )
    private String gender;

    @Column(
            name = "role",
            nullable = false,
            length = 50
    )
    private String role;

    @Column(
            name = "joining_date",
            nullable = false
    )
    private Date joiningDate;

    @Column(
            name = "date_of_birth",
            nullable = false
    )
    private Date dateOfBirth;

    @OneToMany(
            mappedBy = "employee",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Attendance> attendances;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "active_punch_id", referencedColumnName = "id")
    private Punch activePunch;

}
