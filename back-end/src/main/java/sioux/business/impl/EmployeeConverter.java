package sioux.business.impl;

import sioux.domain.Employee;
import sioux.persistence.entity.EmployeeEntity;

public class EmployeeConverter {
    private EmployeeConverter(){
    }

    public static Employee convertToDomain(EmployeeEntity employeeEntity) {
        return Employee.builder()
                .id(employeeEntity.getId())
                .firstName(employeeEntity.getFirstName())
                .lastName(employeeEntity.getLastName())
                .phoneNumber(employeeEntity.getPhoneNumber())
                .email(employeeEntity.getEmail())
                .build();
    }
}
