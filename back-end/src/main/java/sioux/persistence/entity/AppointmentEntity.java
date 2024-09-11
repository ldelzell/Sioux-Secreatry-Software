package sioux.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "appointment")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "subject")
    private String subject;

    @NotNull
    @Column(name = "start_time")
    private Date startTime;

    @NotNull
    @Column(name = "end_time")
    private Date endTime;

    @NotNull
    @Column(name = "is_all_day")
    private Boolean isAllDay;

    @ManyToOne
    @JoinColumn(name = "emp_id", nullable = false)
    private EmployeeEntity employee;

    @NotNull
    @Column(name = "client_name")
    private String clientName;

    @NotNull
    @Column(name = "client_email")
    private String clientEmail;

    @NotNull
    @Column(name = "license_plate")
    private String licensePlate;
}
