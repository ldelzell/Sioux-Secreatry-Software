package sioux.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sioux.business.GetAllAppointmentsUseCase;
import sioux.domain.Appointment;
import sioux.domain.GetAllAppointmentsResponse;
import sioux.persistence.AppointmentRepository;
import sioux.persistence.entity.AppointmentEntity;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllAppointmentsUseCaseImpl implements GetAllAppointmentsUseCase {
    private AppointmentRepository appointmentRepository;

    @Override
    public GetAllAppointmentsResponse getAppointments() {
        List<AppointmentEntity> results = appointmentRepository.findAll();

        List<Appointment> appointments = results
                .stream()
                .map(AppointmentConverter::convertToDomain)
                .toList();

        return new GetAllAppointmentsResponse(appointments);
    }
}
