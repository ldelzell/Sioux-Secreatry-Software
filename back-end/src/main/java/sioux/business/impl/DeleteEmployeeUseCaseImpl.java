package sioux.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sioux.business.DeleteEmployeeUseCase;
import sioux.persistence.EmployeeRepository;

@Service
@AllArgsConstructor
public class DeleteEmployeeUseCaseImpl implements DeleteEmployeeUseCase {
    private EmployeeRepository employeeRepository;

    @Override
    public void deleteEmployee(long employeeId) {
        this.employeeRepository.deleteById(employeeId);
    }
}
