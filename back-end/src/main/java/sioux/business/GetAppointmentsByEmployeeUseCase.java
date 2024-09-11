package sioux.business;

import sioux.domain.GetAllAppointmentsResponse;

public interface GetAppointmentsByEmployeeUseCase {
    GetAllAppointmentsResponse getAppointmentsByEmployeeId(Long employeeId);
}
