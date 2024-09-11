package sioux.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sioux.business.DeleteAppointmentUseCase;
import sioux.persistence.AppointmentRepository;

@Service
@AllArgsConstructor
public class DeleteAppointmentUseCaseImpl implements DeleteAppointmentUseCase {
    private final AppointmentRepository appointmentRepository;
    @Override
    public void deleteAppointment(long appointmentId){
        this.appointmentRepository.deleteById(appointmentId);
    }
}
