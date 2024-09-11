import React, { useState, useEffect } from "react";
import axios from "axios";
import {
  ScheduleComponent,
  Day,
  Week,
  WorkWeek,
  Month,
  Agenda,
  Inject,
  EventSettingsModel,
} from "@syncfusion/ej2-react-schedule";
import { Appointment } from "../types/Appointment";
import { CreateAppointmentResponse } from "../types/CreateAppointmentResponse";

interface CalendarProps {
  selectedEmployee: { id: number; name: string } | null;
  refreshTrigger: boolean;
}

const Calendar: React.FC<CalendarProps> = ({
  selectedEmployee,
  refreshTrigger,
}) => {
  const [appointments, setAppointments] = useState<any[]>([]);

  useEffect(() => {
    if (selectedEmployee !== null) {
      axios
        .get<{ appointments: Appointment[] }>(
          `http://localhost:8080/appointments/employee/${selectedEmployee.id}`
        )
        .then((response) => {
          const appointments = response.data.appointments.map(
            (appointment) => ({
              Id: appointment.id,
              Subject: appointment.subject,
              StartTime: new Date(appointment.startTime),
              EndTime: new Date(appointment.endTime),
              IsAllDay: appointment.isAllDay,
            })
          );
          setAppointments(appointments);
        })
        .catch((error) => {
          console.error("There was an error fetching the appointments!", error);
        });
    }
  }, [selectedEmployee, refreshTrigger]);

  const eventSettings: EventSettingsModel = { dataSource: appointments };

  return (
    <div>
      {selectedEmployee && <h2>{selectedEmployee.name}'s Appointments</h2>}
      <ScheduleComponent eventSettings={eventSettings}>
        <Inject services={[Day, Week, WorkWeek, Month, Agenda]} />
      </ScheduleComponent>
    </div>
  );
};

export default Calendar;
