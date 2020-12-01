package model;

import java.util.*;

public class Club{
	//Atributes
	private String name;
	private String fundationD;
	//Relations
	ArrayList <Employee> payroll;
	
	
	/**
	* Club: Its the constructor of the class Club <br>
	* <b> pre </b> <br>
	* <b> pos </b> It generates an array of employees<br>
	*/
	public Club(String name, String fundationD){
		this.name = name;
		this.fundationD = fundationD;
		payroll = new ArrayList <Employee>();
		
	}
	
	public boolean findId(String id){
		boolean found = false;
		for(int i=0;i<payroll.size() && !found;i++){
			if(payroll.get(i).getId().equalsIgnoreCase(id)){
				found = true;
			}
		}
		return found;
	}
	
	//add principal coach
	public void addEmployee(String name, String id, int salary, boolean state, int yearsXp, int numberTeams, String[] championships){
		Employee employeeX = new PrincipalCoach (name, id, salary, state, yearsXp, numberTeams, championships);
		payroll.add(employeeX);
		
	}
	//add technical assistent
	public void addEmployee(String name, String id, int salary, boolean state, int yearsXp, boolean activedP, String[] listExper){
		Employee employeeX = new TecAsistent (name, id, salary, state, yearsXp, activedP, listExper);
		payroll.add(employeeX);
		
	}
	//add player
	public void addEmployee(String name, String id, int salary, boolean state, String shirtNumber, int goals, double evaAvearage, String positionP){
		Employee employeeX = new Player (name, id, salary, state, shirtNumber, goals, evaAvearage, positionP);
		payroll.add(employeeX);
		
	}
	
	public void fireEmployee(String id){
		boolean found = false;
		for(int i=0;i<payroll.size() && !found;i++){
			if(payroll.get(i).getId().equalsIgnoreCase(id)){
				found = true;
				payroll.remove(i);
			}
		}
	}
	
	public String showEmployee(int index){
		
		String messagex="";
		messagex=payroll.get(index).toString();
		return messagex;		
	}
	
}