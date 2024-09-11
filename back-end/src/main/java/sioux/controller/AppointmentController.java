package sioux.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sioux.business.*;
import sioux.domain.*;

@RestController
@RequestMapping("/appointments")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AppointmentController {
    private final CreateAppointmentUseCase createAppointmentUseCase;
    private final GetAppointmentUseCase getAppointmentUseCase;
    private final DeleteAppointmentUseCase deleteAppointmentUseCase;
    private final UpdateAppointmentUseCase updateAppointmentUseCase;
    private final GetAllAppointmentsUseCase getAppointmentsUseCase;
    private final GetAppointmentsByEmployeeUseCase getAppointmentsByEmployeeUseCase;

    @PostMapping()
    public ResponseEntity<CreateAppointmentResponse> createAppointment(@RequestBody CreateAppointmentRequest request) {
        CreateAppointmentResponse response = createAppointmentUseCase.createAppointment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<Appointment> getAppointment(@PathVariable(value = "id") final long id) {
        return getAppointmentUseCase.getAppointment(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable("id") long id) {
        deleteAppointmentUseCase.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateAppointment(@PathVariable("id") long id,
                                                  @RequestBody @Valid UpdateAppointmentRequest request) {
        request.setId(id);
        updateAppointmentUseCase.updateAppointment(request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<GetAllAppointmentsResponse> getAppointments() {
        GetAllAppointmentsResponse response = getAppointmentsUseCase.getAppointments();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<GetAllAppointmentsResponse> getAppointmentsByEmployeeId(@PathVariable Long employeeId) {
        GetAllAppointmentsResponse response = getAppointmentsByEmployeeUseCase.getAppointmentsByEmployeeId(employeeId);
        return ResponseEntity.ok(response);
    }
}
