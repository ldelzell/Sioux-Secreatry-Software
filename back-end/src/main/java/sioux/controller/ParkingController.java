package sioux.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sioux.domain.ParkingStatus;
import sioux.domain.SerialReader;
import sioux.service.EmailService;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    private static final Logger logger = LoggerFactory.getLogger(ParkingController.class);

    private final EmailService emailService;
    private boolean spot1Available = true;
    private boolean spot2Available = true;
    private SerialReader serialReader;

    public ParkingController(SerialReader serialReader, EmailService emailService){
        this.serialReader = serialReader;
        this.emailService = emailService;
    }
    @PostMapping()
    public void parking() {
        if(serialReader.CheckSpace()){
            emailService.sendAppointmentNotification("stasnikolov03@gmail.com", "Parking Space", "All spots on the main parking are taken. Please go to the further parking!");
            emailService.sendAppointmentNotification("naskots@hotmail.com", "Appointment soon!", "The client will be late 10 minutes, since he is going to the other parking!");
        }

    }
}
