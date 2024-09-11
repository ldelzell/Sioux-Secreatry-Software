//package sioux.configuration.database;
//
//import jakarta.transaction.Transactional;
//import lombok.AllArgsConstructor;
//import sioux.persistence.entity.AppointmentEntity;
//import sioux.persistence.entity.EmployeeEntity;
//import sioux.persistence.AppointmentRepository;
//import sioux.persistence.EmployeeRepository;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.time.ZoneId;
//import java.util.Date;
//import java.util.Optional;
//
//@Component
//@AllArgsConstructor
//public class DatabaseDummyDataInitializer {
//    private EmployeeRepository employeeRepository;
//    private AppointmentRepository appointmentRepository;
//
//    @EventListener(ApplicationReadyEvent.class)
//    @Transactional
//    public void populateDatabaseInitialDummyData() {
//        if (isDatabaseEmpty()) {
//            insertEmployees();
//            insertAppointments();
//        }
//    }
//
//    private boolean isDatabaseEmpty() {
//        return employeeRepository.count() == 0;
//    }
//
//    private void insertEmployees() {
//        for (int i = 0; i < 10; i++) {
//            EmployeeEntity employee = EmployeeEntity.builder()
//                    .firstName("Employee" + i)
//                    .lastName("Lastname" + i)
//                    .phoneNumber(String.valueOf(123456789 + i))
//                    .email("employee" + i + "@example.com")
//                    .build();
//            employeeRepository.save(employee);
//        }
//    }
//
//    private void insertAppointments() {
//        LocalDate currentDate = LocalDate.now();
//        LocalTime appointmentTime = LocalTime.parse("09:00");
//
//        for (int i = 0; i < 10; i++) {
//            Optional<EmployeeEntity> employee = employeeRepository.findById((long) (i + 1));
//            if (employee.isPresent()) {
//                AppointmentEntity appointment = AppointmentEntity.builder()
//                        .date(convertToDateViaInstant(currentDate))
//                        .time(java.sql.Time.valueOf(appointmentTime))
//                        .employee(employee.get())
//                        .build();
//                appointmentRepository.save(appointment);
//            }
//        }
//    }
//
//    private Date convertToDateViaInstant(LocalDate dateToConvert) {
//        return Date.from(dateToConvert.atStartOfDay(ZoneId.systemDefault()).toInstant());
//    }
//}
