package sioux.business;

import sioux.domain.CreateAppointmentRequest;
import sioux.domain.CreateAppointmentResponse;

public interface CreateAppointmentUseCase {
    CreateAppointmentResponse createAppointment(CreateAppointmentRequest request);
}
