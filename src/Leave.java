
import java.time.LocalDate;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author pCd
 */
public class Leave {

    private Employee employee;
    private LocalDate startDate;
    private int daysOfLeave;

    
    public Leave(Employee employee, LocalDate startDate, int daysOfLeave) {
        this.employee = employee;
        this.startDate = startDate;
        this.daysOfLeave = daysOfLeave;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getStartDate() {
        return startDate;
        }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getDaysOfLeave() {
        return daysOfLeave;
    }

    public void setDaysOfLeave(int daysOfLeave) {
        this.daysOfLeave = daysOfLeave;
    }

    public String displayLeaveDetails() {
        String x ="Leave Records for "+employee.getName()+":\n" +
                  "Start Date   Days of Leave  \n"+
                  startDate+"   "+daysOfLeave+"             \n";
        return x;
    }
}