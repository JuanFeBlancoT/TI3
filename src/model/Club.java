package model;

import java.util.*;

public class Club{
	//constants
	public final int MAX_TEAMS = 2;
	//Atributes
	private String name;
	private String fundationD;
	//Relations
	ArrayList <Employee> payroll;
	private Team[] teams;
	
	/**
	* Club: Its the constructor of the class Club <br>
	* <b> pre </b> <br>
	* <b> pos </b> It generates an array of employees<br>
	*/
	public Club(String name, String fundationD){
		this.name = name;
		this.fundationD = fundationD;
		payroll = new ArrayList <Employee>();
		teams = new Team[MAX_TEAMS];
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
	
	public boolean findTeam(String teamName){
		boolean found = false;
		for(int i=0;i<teams.length && !found;i++){
			if(teams[i]!=null && teams[i].getName().equalsIgnoreCase(teamName)){
				found = true;
			}
		}
		return found;
	}
	
	public boolean findEmployeeOnTeam(String teamName, String playerId){
		boolean alreadyIn = false;
		
		return alreadyIn;		
	}
	
	public void addEmployeeToTeam(String teamName, String playerId){
		
		Employee employeeX = null;
		
		boolean foundP = false;
		for(int j=0;j<payroll.size() && !foundP;j++){
			if(payroll.get(j).getId().equalsIgnoreCase(playerId)){
				foundP = true;
				employeeX = payroll.get(j);
			}
		}
				
		boolean found = false;
		for(int i=0;i<teams.length && !found;i++){
			if(teams[i]!=null && teams[i].getName().equalsIgnoreCase(teamName)){
				found = true;
				if(employeeX instanceof PrincipalCoach){
					teams[i].addPrincipalCoach(employeeX);
				}else if(employeeX instanceof TecAsistent){
					teams[i].addTecAsis(employeeX);
				}else if(employeeX instanceof Player){
					teams[i].addPlayer(employeeX);
				}
			}
		}
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
	
	public void createTeam(String teamName){
		
		boolean done = false;
		for(int i=0; i<teams.length && !done;i++){
			if(teams[i]==null){
				done = true;
				teams[i]= new Team(teamName);
			}
		}
	}
	
	public void addAlignment(String teamName,String dateA,String tactic){
		boolean found = false;
		for(int i=0;i<teams.length && !found;i++){
			if(teams[i]!=null && teams[i].getName().equalsIgnoreCase(teamName)){
				found = true;
				teams[i].addAlignment(dateA, tactic);
			}
		}
	}
	
	public String showEmployee(int index){
		
		String messagex="";
		messagex=payroll.get(index).toString();
		return messagex;		
	}
	
	public String showTeam(int index){
		
		String messagex="";
		messagex=teams[index].toString();
		return messagex;		
	}
}