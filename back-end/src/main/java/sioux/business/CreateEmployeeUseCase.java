package sioux.business;

import sioux.domain.CreateEmployeeRequest;
import sioux.domain.CreateEmployeeResponse;

public interface CreateEmployeeUseCase {
    CreateEmployeeResponse createEmployee(CreateEmployeeRequest request);
}
