package sioux.business;

import sioux.domain.Appointment;

import java.util.Optional;

public interface GetAppointmentUseCase {
    Optional<Appointment> getAppointment(long appointmentId);
}
