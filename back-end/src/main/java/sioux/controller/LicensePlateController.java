package sioux.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sioux.business.LicensePlateUseCase;
import sioux.domain.CompareLicensePlateRequest;
import sioux.domain.CreateAppointmentRequest;
import sioux.domain.CreateAppointmentResponse;

@RestController
@RequestMapping("/license-plate")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class LicensePlateController {
    private final LicensePlateUseCase licensePlateUseCase;
    @PostMapping("/license-plate")
    public ResponseEntity<String> checkString(@RequestBody CompareLicensePlateRequest request) {
        boolean isSimilar = licensePlateUseCase.checkStringSimilarity(request.getStringToCheck());
        if (isSimilar) {
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failure", HttpStatus.OK);
        }
    }
}
