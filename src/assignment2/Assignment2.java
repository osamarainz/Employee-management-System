/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package assignment2;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;
public class Assignment2 {

    /**
     * @param args the command line arguments
     */
 

    // Main method to demonstrate functionality
    public static void main(String[] args) {
        // Creating an instance of the EmployeeManagementSystem
        EmployeeManagementSystem system = new EmployeeManagementSystem();

        // Using try-with-resources to ensure resources are closed after use
        try (Scanner scanner = new Scanner(new File("input.txt"));
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
