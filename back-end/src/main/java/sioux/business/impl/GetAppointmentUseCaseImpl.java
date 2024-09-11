package sioux.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sioux.business.GetAppointmentUseCase;
import sioux.domain.Appointment;
import sioux.persistence.AppointmentRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetAppointmentUseCaseImpl implements GetAppointmentUseCase {

    private AppointmentRepository appointmentRepository;

    @Override
    public Optional<Appointment> getAppointment(long appointmentId) {
        return appointmentRepository.findById(appointmentId)
                .map(AppointmentConverter::convertToDomain);
    }
}
