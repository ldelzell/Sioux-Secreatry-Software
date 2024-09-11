package sioux.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sioux.business.GetEmployeeUseCase;
import sioux.domain.Employee;
import sioux.persistence.EmployeeRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetEmployeeUseCaseImpl implements GetEmployeeUseCase {

    private EmployeeRepository employeeRepository;

    @Override
    public Optional<Employee> getEmployee(long employeeId) {
        return employeeRepository.findById(employeeId)
                .map(EmployeeConverter::convertToDomain);
    }
}
