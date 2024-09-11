package sioux.business.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sioux.business.CreateEmployeeUseCase;
import sioux.business.exception.EmailAlreadyExistsException;
import sioux.domain.CreateEmployeeRequest;
import sioux.domain.CreateEmployeeResponse;
import sioux.persistence.EmployeeRepository;
import sioux.persistence.entity.EmployeeEntity;

@Service
@AllArgsConstructor
public class CreateEmployeeUseCaseImpl implements CreateEmployeeUseCase {
    private final EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public CreateEmployeeResponse createEmployee(CreateEmployeeRequest request) {
        if (employeeRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        EmployeeEntity employeeEntity = EmployeeEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .build();

        EmployeeEntity savedEmployee = employeeRepository.save(employeeEntity);

        return CreateEmployeeResponse.builder()
                .employeeId(savedEmployee.getId())
                .build();
    }
}
