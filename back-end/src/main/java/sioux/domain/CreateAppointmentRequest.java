package sioux.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sioux.persistence.entity.EmployeeEntity;

import java.sql.Time;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAppointmentRequest {
    private String subject;
    private Date startTime;
    private Date endTime;
    private Boolean isAllDay;
    private EmployeeEntity employee;
    private String clientName;
    private String clientEmail;
    private String licensePlate;
}
