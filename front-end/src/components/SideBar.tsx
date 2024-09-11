import React, { useState, useEffect } from "react";
import axios from "axios";
import { Employee } from "../types/Employee";

interface SideBarProps {
  onSelectEmployee: (employee: { id: number; name: string }) => void;
  selectedEmployee: { id: number; name: string } | null;
  onAppointmentCreated: () => void;
}

const SideBar: React.FC<SideBarProps> = ({
  onSelectEmployee,
  selectedEmployee,
  onAppointmentCreated,
}) => {
  const [searchTerm, setSearchTerm] = useState("");
  const [employees, setEmployees] = useState<Employee[]>([]);
  const [searchResults, setSearchResults] = useState<Employee[]>([]);
  const [appointmentData, setAppointmentData] = useState({
    subject: "",
    startTime: "",
    endTime: "",
    isAllDay: false,
    clientName: "",
    clientEmail: "",
    licensePlate: "",
  });

  useEffect(() => {
    axios
      .get<{ employees: Employee[] }>("http://localhost:8080/employees")
      .then((response) => {
        setEmployees(response.data.employees);
      })
      .catch((error) => {
        console.error("There was an error fetching the employees!", error);
      });
  }, []);

  const handleSearch = () => {
    const results = employees.filter((employee) =>
      `${employee.firstName} ${employee.lastName}`
        .toLowerCase()
        .includes(searchTerm.toLowerCase())
    );
    setSearchResults(results);
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value, type, checked } = e.target;
    setAppointmentData((prevData) => ({
      ...prevData,
      [name]: type === "checkbox" ? checked : value,
    }));
  };

  const subtractTwoHours = (dateTime: string) => {
    const date = new Date(dateTime);
    date.setHours(date.getHours() - 0);
    return date.toISOString();
  };

  const handleSubmit = () => {
    if (!selectedEmployee) {
      console.error("No employee selected!");
      return;
    }

    const newAppointment = {
      ...appointmentData,
      startTime: subtractTwoHours(appointmentData.startTime),
      endTime: subtractTwoHours(appointmentData.endTime),
      employee: { id: selectedEmployee.id },
    };

    axios
      .post("http://localhost:8080/appointments", newAppointment)
      .then((response) => {
        console.log("Appointment created successfully:", response.data);
        onAppointmentCreated(); // Trigger the refresh
      })
      .catch((error) => {
        console.error("There was an error creating the appointment!", error);
      });
  };

  return (
    <div className="sidebar p-3">
      <h2>Employee Search</h2>
      <div className="mb-3">
        <input
          type="text"
          placeholder="Search..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          className="form-control mb-2"
        />
        <button onClick={handleSearch} className="btn btn-primary">
          Search
        </button>
      </div>
      <ul className="list-group mb-3">
        {searchResults.map((employee) => (
          <li key={employee.id} className="list-group-item pl-2">
            <button
              className="btn btn-link"
              onClick={() =>
                onSelectEmployee({
                  id: employee.id,
                  name: `${employee.firstName} ${employee.lastName}`,
                })
              }
            >
              {employee.firstName} {employee.lastName}
            </button>
          </li>
        ))}
      </ul>
      <div>
        <h3>Create Appointment</h3>
        <div className="mb-3">
          <input
            type="text"
            name="subject"
            placeholder="Subject"
            value={appointmentData.subject}
            onChange={handleChange}
            className="form-control mb-2"
          />
          <input
            type="datetime-local"
            name="startTime"
            placeholder="Start Time"
            value={appointmentData.startTime}
            onChange={handleChange}
            className="form-control mb-2"
          />
          <input
            type="datetime-local"
            name="endTime"
            placeholder="End Time"
            value={appointmentData.endTime}
            onChange={handleChange}
            className="form-control mb-2"
          />
          <div className="form-check mb-2">
            <input
              type="checkbox"
              name="isAllDay"
              checked={appointmentData.isAllDay}
              onChange={handleChange}
              className="form-check-input"
            />
            <label className="form-check-label">All Day</label>
          </div>
          <input
            type="text"
            name="clientName"
            placeholder="Client Name"
            value={appointmentData.clientName}
            onChange={handleChange}
            className="form-control mb-2"
          />
          <input
            type="email"
            name="clientEmail"
            placeholder="Client Email"
            value={appointmentData.clientEmail}
            onChange={handleChange}
            className="form-control mb-2"
          />
          <input
            type="text"
            name="licensePlate"
            placeholder="License Plate"
            value={appointmentData.licensePlate}
            onChange={handleChange}
            className="form-control mb-2"
          />
          <button onClick={handleSubmit} className="btn btn-success">
            Create Appointment
          </button>
        </div>
      </div>
    </div>
  );
};

export default SideBar;
