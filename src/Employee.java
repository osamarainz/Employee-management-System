
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author pCd
 */
public abstract class Employee {
    protected String name;
    protected int id ;
    protected double salary;
    private String state ;
    private ArrayList <Leave> leaveRecords;

    public Employee(String name, int id, double salary) {
        leaveRecords = new ArrayList<>();
        this.name = name;
        this.id = id;
        this.salary = salary;
        state = "";
    }

    
    
    public double calculateBonus(){
        return salary;
        
    }

    @Override
    public String toString() {
        return "Employee{" + "name=" + name + ", id=" + id + ", salary=" + salary + ", state=" + state + ", leaveRecords=" + leaveRecords + '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSalary() {
        return salary;
    }

    public String setStateOnLeave() {
        state = "on leave";
        return state;
        
    }

    public String getState() {
        return state;
    }

    public String setStateWorking() {
        state = "working";
        return state;
        
    }
    
     public void setSalary(double salary) {
        this.salary=salary;
    }
    
    public String addLeaveRecord(Leave leave) {
        leaveRecords.add(leave);
        return "Leave approved for "+name+".";
        
    }
    
    public ArrayList <Leave> getLeaveRecord() {
        return leaveRecords;
        
    }
    
}