package sioux.business.impl;

import sioux.domain.Appointment;
import sioux.persistence.entity.AppointmentEntity;

public class AppointmentConverter {
    public static Appointment convertToDomain(AppointmentEntity entity) {
        return Appointment.builder()
                .id(entity.getId())
                .subject(entity.getSubject())
                .startTime(entity.getStartTime())
                .endTime(entity.getEndTime())
                .isAllDay(entity.getIsAllDay())
                .employeeId(entity.getEmployee().getId())
                .build();
    }
}
