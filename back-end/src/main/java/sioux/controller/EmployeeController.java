package sioux.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sioux.business.*;
import sioux.domain.*;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class EmployeeController {
    private final CreateEmployeeUseCase createEmployeeUseCase;
    private final DeleteEmployeeUseCase deleteEmployeeUseCase;
    private final UpdateEmployeeUseCase updateEmployeeUseCase;
    private final GetAllEmployeesUseCase getAllEmployeesUseCase;
    private final GetEmployeeUseCase getEmployeeUseCase;

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable(value = "id") final Long id) {
        return getEmployeeUseCase.getEmployee(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<GetAllEmployeesResponse> getAllEmployees() {
        GetAllEmployeesResponse response = getAllEmployeesUseCase.getAllEmployees();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        deleteEmployeeUseCase.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<CreateEmployeeResponse> createEmployee(@RequestBody @Valid CreateEmployeeRequest request) {
        CreateEmployeeResponse response = createEmployeeUseCase.createEmployee(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEmployee(@PathVariable("id") Long id,
                                               @RequestBody @Valid UpdateEmployeeRequest request) {
        request.setId(id);
        updateEmployeeUseCase.updateEmployee(request);
        return ResponseEntity.noContent().build();
    }
}
