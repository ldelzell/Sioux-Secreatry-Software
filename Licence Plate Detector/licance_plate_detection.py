import cv2
import pytesseract
import numpy as np
import requests
import time

pytesseract.pytesseract.tesseract_cmd = r'C:\Program Files\Tesseract-OCR\tesseract.exe'

detected_texts = {}
start_time = time.time()
detection_active = True

def detect_license_plate(frame):
    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)

    gray = cv2.GaussianBlur(gray, (5, 5), 0)
    edges = cv2.Canny(gray, 100, 200)

    contours, _ = cv2.findContours(edges.copy(), cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)
    contours = sorted(contours, key=cv2.contourArea, reverse=True)[:10]

    license_plate = None
    for contour in contours:
        epsilon = 0.018 * cv2.arcLength(contour, True)
        approx = cv2.approxPolyDP(contour, epsilon, True)

        if len(approx) == 4:
            license_plate = approx
            break

    if license_plate is not None:
        mask = np.zeros(gray.shape, np.uint8)
        new_image = cv2.drawContours(mask, [license_plate], 0, 255, -1)
        new_image = cv2.bitwise_and(frame, frame, mask=mask)

        (x, y) = np.where(mask == 255)
        (topx, topy) = (np.min(x), np.min(y))
        (bottomx, bottomy) = (np.max(x), np.max(y))
        cropped = gray[topx:bottomx+1, topy:bottomy+1]
        return cropped
    else:
        return None

def extract_text(cropped_image):
    if cropped_image is not None:
        text = pytesseract.image_to_string(cropped_image, config='--psm 8')
        return text.strip()
    else:
        return ""

def send_license_plate(detected_text):
    url = "http://localhost:8080/license-plate/license-plate"
    data = {'stringToCheck': detected_text}
    headers = {
        "Content-Type": "application/json",
        "Origin": "http://localhost:5173"
    }

    try:
        response = requests.post(url, json=data, headers=headers)
        response.raise_for_status()
        print(f"Successfully sent data: {response.text}")
    except requests.exceptions.RequestException as e:
        print(f"Failed to send data: {e}")

def main():
    global detected_texts, start_time, detection_active

    cap = cv2.VideoCapture(0)

    if not cap.isOpened():
        print("Error: Could not open video stream or file")
        return

    while detection_active:
        ret, frame = cap.read()

        if not ret:
            print("Error: Failed to capture image")
            break

        cropped_image = detect_license_plate(frame)
        detected_text = extract_text(cropped_image)

        if detected_text:
            print("Detected License Plate Number:", detected_text)
            if detected_text in detected_texts:
                detected_texts[detected_text] += 1
            else:
                detected_texts[detected_text] = 1

        current_time = time.time()
        if current_time - start_time >= 5:
            if detected_texts:
                most_frequent_text = max(detected_texts, key=detected_texts.get)
                send_license_plate(most_frequent_text)
                detected_texts = {}  
                start_time = time.time()  
                detection_active = False  

        if cropped_image is not None:
            cv2.putText(frame, detected_text, (10, 30), cv2.FONT_HERSHEY_SIMPLEX, 1, (0, 255, 0), 2, cv2.LINE_AA)

        cv2.imshow('Frame', frame)

        if cv2.waitKey(1) & 0xFF == ord('q'):
            break

    cap.release()
    cv2.destroyAllWindows()

if __name__ == "__main__":
    main()
