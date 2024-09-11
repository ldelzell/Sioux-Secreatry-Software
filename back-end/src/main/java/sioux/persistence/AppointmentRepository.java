package sioux.persistence;

import org.springframework.data.jpa.repository.Query;
import sioux.persistence.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
    List<AppointmentEntity> findByEmployee_Id(Long employeeId);
    List<AppointmentEntity> findByStartTime(Date date);
    @Query("SELECT a.licensePlate FROM AppointmentEntity a WHERE DATE(a.startTime) = CURRENT_DATE")
    List<String> findLicensePlatesForCurrentDate();
}
