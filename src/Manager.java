
import java.time.LocalDate;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author pCd
 */
public class Manager extends Employee implements Approval{

    public Manager(String name, int id, double salary) {
        super(name, id, salary);
        
    }
    public double calculateBonus() {
       
        return salary * 0.15; 
    }
    @Override
    public void markEmployeeAsWorking(Employee employee) {
        
        System.out.println("Employee "+employee.getName() + " is now marked as working.");
    }


    @Override
    public void approveLeave(Employee employee, LocalDate startDate, int daysOfLeave) {
        Leave leave = new Leave(employee, startDate, daysOfLeave);
        employee.addLeaveRecord(leave);
        employee.setStateOnLeave();
    }
}


