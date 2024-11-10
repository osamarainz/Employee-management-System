/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author pCd
 */
public class Developer extends Employee {
   public Developer(String name, int id, double salary) {
        super(name, id, salary);
    }

    @Override
    public double calculateBonus(){
        
        return salary * 0.12; 
    } 
}