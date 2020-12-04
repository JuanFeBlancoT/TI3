package model;

import java.util.*;

public class Club{
	//constants
	public final int MAX_TEAMS = 2;
	public final int ROWSA = 7;
	public final int COLSB = 6;
	public final int OFFICE_ROW = 6;
	//Atributes
	private String name;
	private String fundationD;
	private String nit;
	private Player[][] changeRoomA;
	private Player[][] changeRoomB;
	private Employee[][] offices;
	
	//Relations
	ArrayList <Employee> payroll;
	private Team[] teams;
	
	/**
	* Club: Its the constructor of the class Club <br>
	* <b> pre </b> <br>
	* <b> pos </b> It generates an array of employees<br>
	*/
	public Club(String name, String fundationD, String nit){
		this.name = name;
		this.fundationD = fundationD;
		this.nit = nit;
		payroll = new ArrayList <Employee>();
		teams = new Team[MAX_TEAMS];
		changeRoomA= new Player[ROWSA][ROWSA];
		changeRoomB= new Player[ROWSA][COLSB];
		offices=new Employee[OFFICE_ROW][OFFICE_ROW];
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
	
	public boolean findAlignment(String teamName, String dateA){
		boolean existingAl = false;
		boolean found = false;
		for(int i=0;i<teams.length && !found;i++){
			if(teams[i]!=null && teams[i].getName().equalsIgnoreCase(teamName)){
				found = true;
				existingAl = teams[i].findAlignment(dateA);
			}
		}
		return existingAl;
	}
	
	public boolean findPlayerOnTeam(String playerId, String teamName){
		boolean found = false;
		
		for(int i=0;i<teams.length;i++){
			if(teams[i].getName().equalsIgnoreCase(teamName) && i==0){
				found = teams[1].findPlayer(playerId);
			}else if(teams[i].getName().equalsIgnoreCase(teamName) && i==1){
				found = teams[0].findPlayer(playerId);
			}
		}
		return found;
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
	
	public void fireEmployee(String id, int numberTeams){
		boolean found = false;
		for(int i=0;i<payroll.size() && !found;i++){
			if(payroll.get(i).getId().equalsIgnoreCase(id)){
				found = true;
				payroll.remove(i);
			}
		}
		for(int j=0;j<teams.length;j++){
			if(numberTeams==2){
				teams[j].fireEmployee(id);
			}else{
				teams[0].fireEmployee(id);
			}
			
		}
	}
	
	public void createTeam(String teamName){
		
		boolean done = false;
		for(int i=0; i<teams.length && !done;i++){
			if(teams[i]==null){
				done = true;
				if(i==0){
					teams[i]= new Team(teamName, i, ROWSA, ROWSA);
				}else{
					teams[i]= new Team(teamName, i, ROWSA, COLSB);
				}
				
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
	
	public void addFormation(String teamName, String dateA, String formationX){
		boolean found = false;
		for(int i=0;i<teams.length && !found;i++){
			if(teams[i]!=null && teams[i].getName().equalsIgnoreCase(teamName)){
				found = true;
				teams[i].addFormation(dateA, formationX);
			}
		}
	}
	
	public boolean validateFormation(String formationX){
		String[] formText = formationX.split("-");
		int sumFormation = 0;
		boolean validFormation = false;
		boolean invalid = false;
		//int[] formNums = new int[formText.length];
		if(formText.length==3){
			
			for(int i=0;i<formText.length && !invalid;i++){
				//formNums[i] = Integer.parseInt(formText[i]);
				if(Integer.parseInt(formText[i])>8){
					invalid = true;
				}else{
				sumFormation += Integer.parseInt(formText[i]);
				}
			}
			if(sumFormation==10){
				validFormation = true;
			}
		}
		return validFormation;
	}//end validFormation
	
	public String organizePlayers(String teamName){
		String message="";
		
		boolean found = false;
		for(int i=0;i<teams.length && !found;i++){
			if(teams[i]!=null && teams[i].getName().equalsIgnoreCase(teamName)){
				found = true;
				if(i==0){
					message = teams[i].organizePlayers(ROWSA,ROWSA);
				}else{
					message = teams[i].organizePlayers(ROWSA,COLSB);
				}
				
			}
		}
		return message;
	}
	
	public String organizeCoaches(){
		String message="";
		boolean empty = false;
		int payrollIndex =0;

		if(payroll.size()>0){
			for(int i=0;i<OFFICE_ROW;i++){
				for(int j=0;j<OFFICE_ROW && payrollIndex<payroll.size();j++){
					
					if(!empty && payroll.get(payrollIndex) instanceof Coach){	
						
						offices[i][j] = payroll.get(payrollIndex);
						payrollIndex++;
						}
					empty=!empty;
				}
				empty=!empty;
			}
			
			message = showOffice(offices);
		}
		return message;
	}
	
	public String showOffice(Employee[][] coaches){
		String message="";
		
		 for(int i=0;i<OFFICE_ROW;i++){
			for(int j=0;j<OFFICE_ROW;j++){
					if(coaches[i][j]!=null){
					message+="["+coaches[i][j].getName()+"]\t";
					}else{
						message+="[ ]\t";
					}
			}
			message+="\n";
		}
		
		return message;
	}
	
	public String showEmployee(int index){
		
		String messagex="";
		messagex=payroll.get(index).toString();
		return messagex;		
	}
	
	public int detectEmployee(String id){
		int index=0;
		boolean found = false;
		
		for(int i=0;i<payroll.size() && !found;i++){
			if(payroll.get(i).getId().equalsIgnoreCase(id)){
				found = true;
				index=i;
			}
		}
		
		return index;
	}
	
	public int detectTeam(String teamName){
		int index=0;
		boolean found = false;
		for(int i=0;i<teams.length && !found;i++){
			if(teams[i]!=null && teams[i].getName().equalsIgnoreCase(teamName)){
				found = true;
				index=i;
			}
		}
		return index;
	}
	
	public String showTeam(int index){
		
		String messagex="";
		messagex=teams[index].toString();
		return messagex;		
	}
	
	public String showFormation(String teamName, String dateA, String formationX){
		boolean found = false;
		String message="";
		
		for(int i=0;i<teams.length && !found;i++){
			if(teams[i]!=null && teams[i].getName().equalsIgnoreCase(teamName)){
				found = true;
				message = teams[i].showFormation(dateA, formationX);
			}
		}
		return message;
	}
	
	public void updateTeam(String teamName, String newTeamName){
		boolean found = false;
		for(int i=0;i<teams.length && !found;i++){
			if(teams[i]!=null && teams[i].getName().equalsIgnoreCase(teamName)){
				found = true;
				teams[i].setName(newTeamName);
			}
		}
	}
	
	public int getEmployeeType(String id){
		boolean found = false;
		int type =0;
		for(int i=0;i<payroll.size() && !found;i++){
			if(payroll.get(i).getId().equalsIgnoreCase(id)){
				found = true;
				if(payroll.get(i) instanceof PrincipalCoach){
					type = 1;
				}else if(payroll.get(i) instanceof TecAsistent){
					type = 2;
				}else if(payroll.get(i) instanceof Player){
					type = 3;
				}
			}
		}
		return type;
	}
	
	public void updateName(String id, String newId){
		boolean found = false;
		for(int i=0;i<payroll.size() && !found;i++){
			if(payroll.get(i).getId().equalsIgnoreCase(id)){
				found = true;
				payroll.get(i).setName(newId);
			}
		}
	}
	
	public void updateId(String id, String newId){
		boolean found = false;
		for(int i=0;i<payroll.size() && !found;i++){
			if(payroll.get(i).getId().equalsIgnoreCase(id)){
				found = true;
				payroll.get(i).setId(newId);
			}
		}
	}
	
	public void updateSalary(String id, int newSalary){
		boolean found = false;
		for(int i=0;i<payroll.size() && !found;i++){
			if(payroll.get(i).getId().equalsIgnoreCase(id)){
				found = true;
				payroll.get(i).setSalary(newSalary);
			}
		}
	}
	
	public void updateState(String id, boolean newState){
		boolean found = false;
		for(int i=0;i<payroll.size() && !found;i++){
			if(payroll.get(i).getId().equalsIgnoreCase(id)){
				found = true;
				payroll.get(i).setState(newState);
			}
		}
	}
	
	public void updateShirtN(String id, String newShirtN){
		boolean found = false;
		for(int i=0;i<payroll.size() && !found;i++){
			if(payroll.get(i).getId().equalsIgnoreCase(id)){
				found = true;
				((Player)payroll.get(i)).setShirtNumber(newShirtN);
			}
		}
	}
	
	public void updateGoals(String id, int newGoals){
		boolean found = false;
		for(int i=0;i<payroll.size() && !found;i++){
			if(payroll.get(i).getId().equalsIgnoreCase(id)){
				found = true;
				((Player)payroll.get(i)).setGoals(newGoals);
			}
		}
	}
	
	public void updateEvaAve(String id, double newEvaAve){
		boolean found = false;
		for(int i=0;i<payroll.size() && !found;i++){
			if(payroll.get(i).getId().equalsIgnoreCase(id)){
				found = true;
				((Player)payroll.get(i)).setEvaAve(newEvaAve);
			}
		}
	}
	
	public void updatePosition(String id, String newPosition){
		boolean found = false;
		for(int i=0;i<payroll.size() && !found;i++){
			if(payroll.get(i).getId().equalsIgnoreCase(id)){
				found = true;
				((Player)payroll.get(i)).setPosition(newPosition);
			}
		}
	}
	
	public void updateYearsXp(String id, int newYearsXp){
		boolean found = false;
		for(int i=0;i<payroll.size() && !found;i++){
			if(payroll.get(i).getId().equalsIgnoreCase(id)){
				found = true;
				((Coach)payroll.get(i)).setYearsXp(newYearsXp);
			}
		}
	}
	
	public void updateWonChamps(String id, String[] newChampsNames){
		boolean found = false;
		for(int i=0;i<payroll.size() && !found;i++){
			if(payroll.get(i).getId().equalsIgnoreCase(id)){
				found = true;
				((PrincipalCoach)payroll.get(i)).setChampionships(newChampsNames);
			}
		}
	}
	
	public void updateNumTeams(String id, int newNumTeams){
		boolean found = false;
		for(int i=0;i<payroll.size() && !found;i++){
			if(payroll.get(i).getId().equalsIgnoreCase(id)){
				found = true;
				((PrincipalCoach)payroll.get(i)).setNumTeams(newNumTeams);
			}
		}
	}
	
	public void updateActive(String id, boolean newActive){
		boolean found = false;
		for(int i=0;i<payroll.size() && !found;i++){
			if(payroll.get(i).getId().equalsIgnoreCase(id)){
				found = true;
				((TecAsistent)payroll.get(i)).setActive(newActive);
			}
		}
	}
	
	public void updateExpertises(String id, String[] newExper){
		boolean found = false;
		for(int i=0;i<payroll.size() && !found;i++){
			if(payroll.get(i).getId().equalsIgnoreCase(id)){
				found = true;
				((TecAsistent)payroll.get(i)).setExpertises(newExper);
			}
		}
	}
}	