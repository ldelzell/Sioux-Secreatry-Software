package sioux.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sioux.business.LicensePlateUseCase;
import sioux.domain.CompareLicensePlates;
import sioux.domain.SerialReader;
import sioux.persistence.AppointmentRepository;
import sioux.service.EmailService;

import java.util.List;

@Service
@AllArgsConstructor
public class LicensePlateUseCaseImpl implements LicensePlateUseCase {
    private final AppointmentRepository appointmentRepository;
    private SerialReader serialReader;
    private final EmailService emailService;


    @Override
    public boolean checkStringSimilarity(String stringToCheck) {
        List<String> licensePlatesForToday = getAllLicensePlatesForToday();
        String licensePlateFake = "KH 7347 BH";

        for (String licensePlate : licensePlatesForToday) {
            double similarity = CompareLicensePlates.calculateSimilarity(licensePlate, stringToCheck);
            if (similarity > 0.5) {
                if(serialReader.CheckSpace()){
                    emailService.sendAppointmentNotification("stasnikolov03@gmail.com", "Parking Space", "All spots on the main parking are taken. Please go to the further parking!");
                    emailService.sendAppointmentNotification("naskots@hotmail.com", "Appointment soon!", "The client will be late 10 minutes, since he is going to the other parking!");
                }
                return true;
            }
        }
        return false;
    }


    public List<String> getAllLicensePlatesForToday() {
        return appointmentRepository.findLicensePlatesForCurrentDate();
    }
}
