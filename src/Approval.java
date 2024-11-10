
import java.time.LocalDate;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author pCd
 */
public interface Approval {
    void approveLeave(Employee employee, LocalDate startDate, int daysOfLeave);
    void markEmployeeAsWorking(Employee employee);
}

