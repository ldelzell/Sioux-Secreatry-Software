package sioux.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sioux.business.CreateAppointmentUseCase;
import sioux.domain.CreateAppointmentRequest;
import sioux.domain.CreateAppointmentResponse;
import sioux.persistence.AppointmentRepository;
import sioux.persistence.EmployeeRepository;
import sioux.persistence.entity.AppointmentEntity;
import sioux.persistence.entity.EmployeeEntity;
import sioux.service.EmailService;

@Service
@AllArgsConstructor
public class CreateAppointmentUseCaseImpl implements CreateAppointmentUseCase {
    private final AppointmentRepository appointmentRepository;
    private final EmployeeRepository employeeRepository;
    private final EmailService emailService;

    @Override
    public CreateAppointmentResponse createAppointment(CreateAppointmentRequest request) {
        EmployeeEntity employeeEntity = employeeRepository.findById(request.getEmployee().getId())
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        AppointmentEntity appointmentEntity = AppointmentEntity.builder()
                .subject(request.getSubject())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .isAllDay(request.getIsAllDay())
                .employee(employeeEntity)
                .clientName(request.getClientName())
                .clientEmail(request.getClientEmail())
                .licensePlate(request.getLicensePlate())
                .build();

        AppointmentEntity savedAppointment = appointmentRepository.save(appointmentEntity);

        String employeeEmail = savedAppointment.getEmployee().getEmail();
        if (employeeEmail != null && !employeeEmail.isEmpty()) {
            String subject = "New Appointment Created";
            String text = String.format("Dear %s,%n%nAn appointment has been scheduled for you on %s.",
                    savedAppointment.getEmployee().getFirstName(),
                    savedAppointment.getStartTime());

            emailService.sendAppointmentNotification(employeeEmail, subject, text);
        } else {
            throw new IllegalArgumentException("Employee email is null or empty");
        }

        return CreateAppointmentResponse.builder()
                .appointmentId(savedAppointment.getId())
                .build();
    }
}
