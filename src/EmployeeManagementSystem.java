
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author pCd
 */
public class EmployeeManagementSystem {
    // List to store employees
    private ArrayList<Employee> employees;

    // Constructor to initialize the employee list
    public EmployeeManagementSystem() {
        employees = new ArrayList<>();
    }

    // Method to add an employee to the system
    public String addEmployee(Employee employee) {
        // Adding the employee to the list
        employees.add(employee);
        // Returning a success message
        return "Employee " + employee.getName() + " added successfully.";
    }

    // Method to remove an employee by ID
    public String removeEmployee(int id) {
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            if (employee.getId() == id) {
                iterator.remove();
                return "Employee removed successfully.";
            }
        }
        return "Employee with ID " + id + " not found.";
    }

    // Method to print all employees in the system
    public String printAllEmployees() {
        // StringBuilder to construct the employee list string
        StringBuilder builder = new StringBuilder();
        // Adding a header with column names
        builder.append(String.format("%-15s %-5s %-10s %-10s", "Name", "ID", "Salary", "Bonus"));

        // Checking if the employee list is not empty before iterating
        if (!employees.isEmpty()) {
            // Adding a newline after the header
            builder.append("\n");
        }

        // Looping through each employee to add their details to the string
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            // Adding a newline before each line except the first
            if (i > 0) {
                builder.append("\n");
            }
            // Adding the employee's details including the bonus
            builder.append(String.format("%-15s %-5d %-10.2f %-10.2f", 
                employee.getName(), employee.getId(), employee.getSalary(), employee.calculateBonus()));
        }
        // Returning the constructed string
        return builder.toString();
    }

    // Method to approve leave for an employee
    public String approveLeave(int managerId, int employeeId, LocalDate startDate, int days) {
        // Finding the manager and employee by their IDs
        Employee manager = findEmployeeById(managerId);
        Employee employee = findEmployeeById(employeeId);

        // Checking if the manager is an instance of Approver and the employee exists
        if (manager instanceof Approval && employee != null) {
            // Checking if the employee is not already on leave
            if (!employee.getState().equals("on leave")) {
                // Approving the leave through the manager
                ((Approval) manager).approveLeave(employee, startDate, days);
                // Returning a success message
                return "Leave approved for " + employee.getName() + ".";
            } else {
                // Returning a message indicating the employee is already on leave
                return "Cannot approve leave for " + employee.getName() + " as they are already on leave.";
            }
        } else {
            // Returning a failure message if manager or employee IDs are incorrect
            return "Leave approval failed. Either manager ID is incorrect or employee ID does not exist.";
        }
    }

    // Method to mark an employee as working
    public String markEmployeeAsWorking(int managerId, int employeeId) {
        // Finding the manager and employee by their IDs
        Employee manager = findEmployeeById(managerId);
        Employee employee = findEmployeeById(employeeId);

        // Checking if both the manager and employee exist and the manager is a valid manager
        if (manager != null && employee != null && manager instanceof Manager) {
            // Marking the employee as working
            employee.setStateWorking();
            // Returning a success message
            return "Employee " + employee.getName() + " is now marked as working.";
        } else {
            // Returning a failure message if the operation failed
            return "Operation failed. Either the manager or employee does not exist, or the manager ID does not correspond to a manager.";
        }
    }

    // Helper method to find an employee by ID
    private Employee findEmployeeById(int id) {
        // Looping through the employee list to find the matching ID
        for (Employee employee : employees) {
            // Returning the employee if a match is found
            if (employee.getId() == id) {
                return employee;
            }
        }
        // Returning null if no match is found
        return null;
    }

    // Method to print the leave records of an employee
    public String printEmployeeLeaveRecords(int employeeId) {
        // Finding the employee by ID
        Employee employee = findEmployeeById(employeeId);

        // Checking if the employee exists
        if (employee != null) {
            // StringBuilder to construct the leave records string
            StringBuilder builder = new StringBuilder();
            // Adding a header with column names
            builder.append("Leave Records for ").append(employee.getName()).append(":\n");
            builder.append(String.format("%-12s %-15s\n", "Start Date", "Days of Leave"));

            // Looping through each leave record to add its details to the string
            for (Leave leave : employee.getLeaveRecord()) {
                // Adding each leave's details
                builder.append(String.format("%-12s %-15d\n", leave.getStartDate().toString(), leave.getDaysOfLeave()));
            }
            // Returning the constructed string
            return builder.toString();
        } else {
            // Returning a message if the employee is not found
            return "Employee with ID " + employeeId + " not found.";
        }
}


public static void main(String[] args) {
        // Creating an instance of the EmployeeManagementSystem
        EmployeeManagementSystem system = new EmployeeManagementSystem();

        // Using try-with-resources to ensure resources are closed after use
        try (Scanner scanner = new Scanner(new File("C:\\Users\\pCd\\Desktop\\input(1).txt"));
             PrintWriter writer = new PrintWriter("output.txt")) {

            // Reading input line by line
            while (scanner.hasNextLine()) {
                // Reading and trimming each line to remove leading and trailing spaces
                String line = scanner.nextLine().trim();
                // Splitting the line into commands using comma as delimiter
                String[] commands = line.split(",");
                
                // Trimming each command part
                for (int i = 0; i < commands.length; i++) {
                    commands[i] = commands[i].trim();
                }

                // Processing each command
                switch (commands[0]) {
                    // Adding a manager
                    case "Add_Manager":
                        system.addEmployee(new Manager(commands[1], 
                                Integer.parseInt(commands[2]), 
                                Double.parseDouble(commands[3])));
                        writer.println("Manager " + commands[1] + " added.");
                        break;

                    // Adding a developer
                    case "Add_Developer":
                        system.addEmployee(new Developer(commands[1], 
                                Integer.parseInt(commands[2]), 
                                Double.parseDouble(commands[3])));
                        writer.println("Developer " + commands[1] + " added.");
                        break;

                    // Adding a designer
                    case "Add_Designer":
                        system.addEmployee(new Designer(commands[1], 
                                Integer.parseInt(commands[2]), 
                                Double.parseDouble(commands[3])));
                        writer.println("Designer " + commands[1] + " added.");
                        break;

                    // Giving leave to an employee
                    case "Give_Leave":
                        int managerId = Integer.parseInt(commands[1]);
                        int employeeId = Integer.parseInt(commands[2]);
                        LocalDate startDate = LocalDate.parse(commands[3]);
                        int days = Integer.parseInt(commands[4]);
                        String leaveResult = system.approveLeave(managerId, employeeId, startDate, days);
                        writer.println(leaveResult);
                        break;

                    // Changing the state of an employee to working
                    case "Change_State":
                        managerId = Integer.parseInt(commands[1]);
                        employeeId = Integer.parseInt(commands[2]);
                        String stateResult = system.markEmployeeAsWorking(managerId, employeeId);
                        writer.println(stateResult);
                        break;

                    // Removing an employee
                    case "del_Employee":
                        String removeResult = system.removeEmployee(Integer.parseInt(commands[1]));
                        writer.println(removeResult);
                        break;

                    // Printing all employees
                    case "printAllEmployees":
                        String allEmployees = system.printAllEmployees();
                        writer.println(allEmployees);
                        break;

                    // Printing leave records of an employee
                    case "print_Leave_Records":
                        String leaveRecords = system.printEmployeeLeaveRecords(Integer.parseInt(commands[1]));
                        writer.println(leaveRecords);
                        break;
                }
            }

        } catch (IOException e) {
            // Handling exceptions and printing the stack trace
            e.printStackTrace();
        }
    }













}
