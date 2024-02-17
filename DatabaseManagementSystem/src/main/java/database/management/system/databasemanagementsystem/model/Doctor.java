package database.management.system.databasemanagementsystem.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long doctor_id;

    @Column(name = "full_name")
    private String full_name;

    @Column(name = "date_of_birth")
    private String date_of_birth;

    @Column(name = "nation")
    private String nation;

    @Column(name = "hospital")
    private String hospital;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "free_day")
    private Date free_time;
}
