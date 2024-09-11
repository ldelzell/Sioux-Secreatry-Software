// src/types/Appointment.ts
export interface Appointment {
  id: number;
  subject: string;
  startTime: string; // ISO 8601 string
  endTime: string; // ISO 8601 string
  isAllDay: boolean;
  employeeId: number;
}
