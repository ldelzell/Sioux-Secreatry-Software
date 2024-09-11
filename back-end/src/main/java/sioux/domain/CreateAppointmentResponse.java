package sioux.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateAppointmentResponse {
    private Long appointmentId;
}
