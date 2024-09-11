package sioux.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sioux.business.GetAppointmentsByEmployeeUseCase;
import sioux.domain.Appointment;
import sioux.domain.GetAllAppointmentsResponse;
import sioux.persistence.AppointmentRepository;
import sioux.persistence.entity.AppointmentEntity;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAppointmentsByEmployeeUseCaseImpl implements GetAppointmentsByEmployeeUseCase {
    private final AppointmentRepository appointmentRepository;

    @Override
    public GetAllAppointmentsResponse getAppointmentsByEmployeeId(Long employeeId) {
        List<AppointmentEntity> results = appointmentRepository.findByEmployee_Id(employeeId);

        List<Appointment> appointments = results.stream()
                .map(AppointmentConverter::convertToDomain)
                .toList();

        return new GetAllAppointmentsResponse(appointments);
    }
}
