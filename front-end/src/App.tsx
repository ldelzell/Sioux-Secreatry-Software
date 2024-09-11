import React, { useState } from "react";
import { registerLicense } from "@syncfusion/ej2-base";
import Calendar from "./components/Calendar";
import SideBar from "./components/SideBar";
import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";

registerLicense(
  "Ngo9BigBOggjHTQxAR8/V1NBaF1cW2hPYVF/WmFZfVpgcl9GZFZVQWY/P1ZhSXxXdkBgWX5WcHBQRmBbV0w="
);

const App: React.FC = () => {
  const [selectedEmployee, setSelectedEmployee] = useState<{
    id: number;
    name: string;
  } | null>(null);
  const [refreshTrigger, setRefreshTrigger] = useState(false);

  const handleAppointmentCreated = () => {
    setRefreshTrigger((prev) => !prev);
  };

  return (
    <div style={{ display: "flex", height: "100vh" }}>
      <SideBar
        onSelectEmployee={(employee) => setSelectedEmployee(employee)}
        selectedEmployee={selectedEmployee}
        onAppointmentCreated={handleAppointmentCreated}
      />
      <div className="calendar-container" style={{ flexGrow: 1 }}>
        <Calendar
          selectedEmployee={selectedEmployee}
          refreshTrigger={refreshTrigger}
        />
      </div>
    </div>
  );
};

export default App;
