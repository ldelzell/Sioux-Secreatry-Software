const int sensorPin = A0;
const int sensorPin1 = A1; 
const int ledPin = 13; 
const int ledPin1 = 8; 
const int idPin = 2; 

int sensorValue = 0;
int sensorValue1 = 0; 
int threshold = 400;
int arduinoID = 0; 

void setup() {
    pinMode(ledPin, OUTPUT); 
    pinMode(ledPin1, OUTPUT); 
    pinMode(idPin, INPUT_PULLUP);
    Serial.begin(9600);

    // Determine the Arduino ID
    if (digitalRead(idPin) == HIGH) {
        arduinoID = 1;
    } else {
        arduinoID = 2;
    }

    Serial.print("Arduino ");
    Serial.print(arduinoID);
    Serial.println(" setup complete"); 
}

void loop() {
    sensorValue = analogRead(sensorPin);
    sensorValue1 = analogRead(sensorPin1); 

    bool spot1Taken = sensorValue < threshold;
    bool spot2Taken = sensorValue1 < threshold;

    if (spot1Taken) {
        digitalWrite(ledPin, HIGH); 
    } else {
        digitalWrite(ledPin, LOW);
    }

    if (spot2Taken) {
        digitalWrite(ledPin1, HIGH); 
    } else {
        digitalWrite(ledPin1, LOW);
    }

    if (spot1Taken && spot2Taken) {
        Serial.println("true");
    }
    else{
      Serial.println("false");
    }

    delay(3000); 
}