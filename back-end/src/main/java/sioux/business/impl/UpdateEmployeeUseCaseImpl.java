package sioux.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sioux.business.UpdateEmployeeUseCase;
import sioux.business.exception.InvalidUserException;
import sioux.domain.UpdateEmployeeRequest;
import sioux.persistence.EmployeeRepository;
import sioux.persistence.entity.EmployeeEntity;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateEmployeeUseCaseImpl implements UpdateEmployeeUseCase {
    private EmployeeRepository employeeRepository;

    @Override
    public void updateEmployee(UpdateEmployeeRequest request) {
        Optional<EmployeeEntity> employeeOptional = employeeRepository.findById(request.getId());

        if (employeeOptional.isEmpty()) {
            throw new InvalidUserException("USER_INVALID");
        }

        EmployeeEntity user = employeeOptional.get();
        updateFields(request, user);
    }

    private void updateFields(UpdateEmployeeRequest request, EmployeeEntity employee) {
        employee.setEmail(request.getEmail());
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setPhoneNumber(request.getPhoneNumber());

        employeeRepository.save(employee);
    }
}
