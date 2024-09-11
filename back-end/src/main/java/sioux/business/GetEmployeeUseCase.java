package sioux.business;

import sioux.domain.Employee;

import java.util.Optional;

public interface GetEmployeeUseCase {
    Optional<Employee> getEmployee(long employeeId);
}
