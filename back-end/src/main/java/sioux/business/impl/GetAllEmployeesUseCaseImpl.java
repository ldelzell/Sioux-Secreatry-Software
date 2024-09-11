package sioux.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sioux.business.GetAllEmployeesUseCase;
import sioux.domain.Employee;
import sioux.domain.GetAllEmployeesResponse;
import sioux.persistence.EmployeeRepository;
import sioux.persistence.entity.EmployeeEntity;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllEmployeesUseCaseImpl implements GetAllEmployeesUseCase {
    private EmployeeRepository employeeRepository;

    @Override
    public GetAllEmployeesResponse getAllEmployees() {
        List<EmployeeEntity> results;
        results = employeeRepository.findAll();

        final GetAllEmployeesResponse response = new GetAllEmployeesResponse();

        List<Employee> employees = results
                .stream()
                .map(EmployeeConverter::convertToDomain)
                .toList();
        response.setEmployees(employees);

        return response;
    }
}
