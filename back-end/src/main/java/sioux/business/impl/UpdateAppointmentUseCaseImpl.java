package sioux.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sioux.business.UpdateAppointmentUseCase;
import sioux.business.exception.InvalidUserException;
import sioux.domain.UpdateAppointmentRequest;
import sioux.persistence.AppointmentRepository;
import sioux.persistence.EmployeeRepository;
import sioux.persistence.entity.AppointmentEntity;
import sioux.persistence.entity.EmployeeEntity;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateAppointmentUseCaseImpl implements UpdateAppointmentUseCase {
    private final AppointmentRepository appointmentRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public void updateAppointment(UpdateAppointmentRequest request) {
        Optional<AppointmentEntity> appointmentOptional = appointmentRepository.findById(request.getId());
        if (appointmentOptional.isEmpty()) {
            throw new InvalidUserException("INVALID_USER");
        }

        AppointmentEntity appointment = appointmentOptional.get();
        updateFields(request, appointment);
    }
    private void updateFields(UpdateAppointmentRequest request, AppointmentEntity appointment) {
        Optional<EmployeeEntity> employeeEntityOptional = employeeRepository.findById(request.getEmployeeId());
        EmployeeEntity employeeEntity = employeeEntityOptional.orElseThrow(() -> new IllegalArgumentException("Invalid employee ID"));

        appointment.setSubject(request.getSubject());
        appointment.setStartTime(request.getStartTime());
        appointment.setEndTime(request.getEndTime());
        appointment.setIsAllDay(request.getIsAllDay());
        appointment.setEmployee(employeeEntity);
        appointment.setClientName(request.getClientName());
        appointment.setClientEmail(request.getClientEmail());
        appointment.setLicensePlate(request.getLicensePlate());
        appointmentRepository.save(appointment);
    }
}
