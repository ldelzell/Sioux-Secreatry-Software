package sioux.domain;
import com.fazecast.jSerialComm.SerialPort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
@Component
public class SerialReader {
    private static final Logger logger = Logger.getLogger(SerialReader.class.getName());
    public boolean CheckSpace() {
        SerialPort comPort = SerialPort.getCommPort("COM7"); // Use the first available port
        comPort.setBaudRate(9600);
        comPort.openPort();
        boolean isSpaceTaken = false;
        // Configure logger to log to console
//        ConsoleHandler handler = new ConsoleHandler();
//        handler.setLevel(Level.INFO);
//        logger.addHandler(handler);
//        logger.setLevel(Level.INFO);

        logger.info("Serial port opened: " + comPort.getSystemPortName());

        try {
                if (comPort.bytesAvailable() > 0) {
                    InputStream in = comPort.getInputStream();
                    byte[] buffer = new byte[2048];
                    int len = in.read(buffer);

                    if (len > 0) {
                        String data = new String(buffer, 0, len, StandardCharsets.UTF_8).trim();
                        logger.info("Received: " + data);
                        String[] dataList = data.split("\n");
                        if (dataList[dataList.length - 1].equals("true")){
                            logger.info("Both spots taken condition met, sending POST request...");
                            isSpaceTaken = true;
                            logger.info("Hello: " + isSpaceTaken);

                        }
                    }
                    in.close();
                }
                Thread.sleep(100); // Small delay to prevent high CPU usage

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error reading from serial port", e);
        } finally {
            comPort.closePort();
            logger.info("Serial port closed.");
        }
        return isSpaceTaken;
    }


    private static void sendPostRequest() {
        try {
            URL url = new URL("http://localhost:8080/parking/update");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String jsonInputString = "{\"spot1\": true, \"spot2\": true}";
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            logger.info("POST Response Code :: " + responseCode);

            // Read response from server
            InputStream responseStream = conn.getInputStream();
            byte[] responseBuffer = new byte[1024];
            int responseLen = responseStream.read(responseBuffer);
            String response = new String(responseBuffer, 0, responseLen, StandardCharsets.UTF_8);
            logger.info("Response from server: " + response);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error sending POST request", e);
        }
    }
}
