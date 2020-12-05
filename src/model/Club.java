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
	
	//Relations
	private Player[][] changeRoomA;
	private Player[][] changeRoomB;
	private Employee[][] offices;
	private Team[] teams;
	ArrayList <Employee> payroll;

	
	/**
	* Club: Its the constructor of the class Club <br>
	* <b> pre </b> <br>
	* <b> pos </b> It generates an array of employees, teams and some matrix of changerooms and offices<br>
	* @param name Is the name of the club
	* @param fundationD Is the date of fundation of the club
	* @param nit Is the Nit of the club
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
	
	/**
	* findId: Search the payroll and verifies if the given id matches the id of one of the employees and returns a boolean depending on wether thers a match or not <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param id Is the given id which will be compared with the rest
	* @return found It is the value that determines if the id matched or not
	*/
	public boolean findId(String id){
		boolean found = false;
		for(int i=0;i<payroll.size() && !found;i++){
			if(payroll.get(i).getId().equalsIgnoreCase(id)){
				found = true;
			}
		}
		return found;
	}
	
	/**
	* findTeam: Search the teams array and verifies if the given name matches the name of one of the teams and returns a boolean depending on wether not there is a match <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param teamName Is the given name which will be compared with the rest
	* @return found It is the value that determines if the name matched or not
	*/
	public boolean findTeam(String teamName){
		boolean found = false;
		for(int i=0;i<teams.length && !found;i++){
			if(teams[i]!=null && teams[i].getName().equalsIgnoreCase(teamName)){
				found = true;
			}
		}
		return found;
	}
	
	/**
	* findAlignment: Search in the alignments arraylist of the given team and verifies if the given date matches the date of one of any alignmnet and returns a boolean depending on wether there is a match or not <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param teamName the given name of the team 
	* @param dateA Is the given date which will be compared with the rest
	* @return existingAl It is the value that determines if the date matched or not
	*/
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
	
	/**
	* findPlayerOnTeam: Search the players array of a given team to find if the given id is already registered ther and returns a boolean depending on wether there is a match or not <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param playerId Is the given id which will be compared with the rest
	* @param teamName Is the name of hte team where the player is going to be added
	* @return found It is the value that determines if the id matched or not
	*/
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
	
	/**
	* findFormation: Search the array of formations of an alignmnet and verifies if the given formation is already registered and returns if it is or not there <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param teamName the name of the team where it will search
	* @param dateA the date asociated with the alignment where it will search
	* @param formationX the string of forma that will be compared
	* @return exist It determines if the formation is registered or not
	*/
	public boolean findFormation(String teamName, String dateA, String formationX){
		boolean found = false;
		boolean exist = false;
		
		for(int i=0;i<teams.length && !found;i++){
			if(teams[i]!=null && teams[i].getName().equalsIgnoreCase(teamName)){
				found = true;
				exist = teams[i].findFormation(dateA, formationX);
			}
		}
		return exist;
	}
	
	/**
	* addEmployeeToTeam: Search the team by the given name and add to the corresponfing array of teams a new Player object <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param teamName Is the name of the team where the player will be added
	* @param playerId Is the id of the player that will be created
	*/
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
	
	/**
	* addEmployee: Creates a new principal coach and adds it to the payroll arraylist <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param name The name of the employee
	* @param id The id of the employee that cant be possesed by another employee
	* @param salary The salary of the employee that cant be a negative number
	* @param state The state of the employee, a boolean that determines if it is an active or inactive employee
	* @param yearsXp Is the amount of years of experience of the coach, it can be a negative number
	* @param numberTeams Is the amount of teams that the coach has trained, cant be a negative number
	* @param championships Is a string array with size of the number of won championships of the coach and has in it the names of all the championships he won
	*/
	//add principal coach
	public void addEmployee(String name, String id, int salary, boolean state, int yearsXp, int numberTeams, String[] championships){
		Employee employeeX = new PrincipalCoach (name, id, salary, state, yearsXp, numberTeams, championships);
		payroll.add(employeeX);
		
	}
	
	/**
	* addEmployee: Creates a new technical asistent and adds it to the payroll arraylist <br>
	* <b> pre </b> The strings in the strign array must be one of the constants in the Expertise enumeration <br>
	* <b> pos </b> <br>
	* @param name The name of the employee
	* @param id The id of the employee that cant be possesed by another employee
	* @param salary The salary of the employee that cant be a negative number
	* @param state The state of the employee, a boolean that determines if it is an active or inactive employee
	* @param yearsXp Is the amount of years of experience of the coach, it can be a negative number
	* @param activedP Is a boolean that determines if the technical asistent was or not an active player
	* @param listExper Is a string array that will be converted to an array of enums with the expertises of the employee
	*/
	//add technical assistent
	public void addEmployee(String name, String id, int salary, boolean state, int yearsXp, boolean activedP, String[] listExper){
		Employee employeeX = new TecAsistent (name, id, salary, state, yearsXp, activedP, listExper);
		payroll.add(employeeX);
		
	}
	
	/**
	* addEmployee: Creates a new player and adds it to the payroll arraylist <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param name The name of the employee
	* @param id The id of the employee that cant be possesed by another employee
	* @param salary The salary of the employee that cant be a negative number
	* @param state The state of the employee, a boolean that determines if it is an active or inactive employee
	* @param shirtNumber Is the shirt number of the player that shoudl be a positive number
	* @param goals Is the number of goals that the player has make in the club, it must be a positive number
	* @param evaAvearage Is the avearage evaluation of the player and must be a positive number
	* @param positionP Is an String that will determine the position of the player within a couple of options in the Position enumeration
	*/
	//add player
	public void addEmployee(String name, String id, int salary, boolean state, String shirtNumber, int goals, double evaAvearage, String positionP){
		Employee employeeX = new Player (name, id, salary, state, shirtNumber, goals, evaAvearage, positionP);
		payroll.add(employeeX);
		
	}
	
	public void fireEmployee(String id, int numberTeams){
		boolean found = false;
		int index = detectEmployee(id);
		payroll.remove(index);

		for(int j=0;j<teams.length;j++){
			if(numberTeams==2){
				teams[j].fireEmployee(id);
			}else{
				teams[0].fireEmployee(id);
			}
			
		}
	}
	
	/**
	* createTeam: It creates a new team object and adds it to the teams array <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param teamName Is the name of the team
	*/
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
	
	/**
	* addAlignment: Search the team by the given name and adds an alignment <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param teamName Is the name of the team where the alignment will be added
	* @param dateA Is the date of the alignment
	* @param tactic Is a String that corresponds to one of the four options from the Tactic enumeration
	*/
	public void addAlignment(String teamName,String dateA,String tactic){
		boolean found = false;
		int index = detectTeam(teamName);
		teams[index].addAlignment(dateA, tactic);
	}
	
	/**
	* addFormation: Search the team by the given name and the alignment by the given date, then adds an new formation to it <br>
	* <b> pre </b> formationX must have 3 parts, separated by a - any part can be a number greater than 7 and the sum of everything must give 10<br>
	* <b> pos </b> <br>
	* @param teamName Is the name of the team where the alignment will be added
	* @param dateA Is the date of the alignment
	* @param formationX Is a String that determines the way the matrix will be created
	*/
	public void addFormation(String teamName, String dateA, String formationX){
		boolean found = false;
		for(int i=0;i<teams.length && !found;i++){
			if(teams[i]!=null && teams[i].getName().equalsIgnoreCase(teamName)){
				found = true;
				teams[i].addFormation(dateA, formationX);
			}
		}
	}
	
	/**
	* validFormation: validates that a given formation is adecuate <br>
	* <b> pre </b> formationX must have 3 parts, separated by a - any part can be a number greater than 7 and the sum of everything must give 10<br>
	* <b> pos </b> <br>
	* @param formationX Is a String that determines the way the matrix will be created
	* @return validFormation Determines if the formation string is valid or not
	*/
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
	
	/**
	* showChangeroom: Shows the changerooms <br>
	* <b> pre </b> the index must be within the size of the payroll arraylist<br>
	* <b> pos </b> <br>
	* @param teamIndex team index that will show its changeroom
	* @return message The string with the information
	*/
	public String showChangeroom(int teamIndex){
		String message="";
		if(teamIndex==0){
			message = teams[teamIndex].organizePlayers(ROWSA,ROWSA);
		}else{
			message = teams[teamIndex].organizePlayers(ROWSA,COLSB);
		}
		
		return message;
	}
	
	/**
	* organizePlayers: organizes players in a matrix leaving a space between them and displays it <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param teamName Is a String that determines which team will be organized
	* @return message The string with the matrix
	*/
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
	
	/**
	* organizePlayers: organizes coaches in a matrix leaving a space between them and displays it <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return message The string with the matrix
	*/
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
	
	/**
	* showOffice: Show the information of office <br>
	* <b> pre </b> the index must be within the size of the payroll arraylist<br>
	* <b> pos </b> <br>
	* @param coaches matric
	* @return message The string with the information
	*/
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
	
	/**
	* showEmployee: Show the information of the employee with the corresponding id <br>
	* <b> pre </b> the index must be within the size of the payroll arraylist<br>
	* <b> pos </b> <br>
	* @param index Is the number that specifies the position within the payroll of the employee
	* @return message The string with the information
	*/
	public String showEmployee(int index){
		
		String messagex="";
		messagex=payroll.get(index).toString();
		return messagex;		
	}
	
	/**
	* detectEmployee: It takes an id and searches if any id within the payroll match with it, then returns the position of that id, if found<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param id Is id that will be compared
	* @return index Is the position of the given id in the payroll
	*/
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
	
	/**
	* detectTeam: It takes an name and searches if any name within the teams array match with it, then returns the position of that team, if found<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param teamName Is name that will be compared
	* @return index Is the position of the given team in the teams array
	*/
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
	
	/**
	* showTeam: Show the information of the team with the corresponding name <br>
	* <b> pre </b> the index must be within the size of the teams array size <br>
	* <b> pos </b> <br>
	* @param index Is the number that specifies the position within the teams array of the team
	* @return message The string with the information
	*/
	public String showTeam(int index){
		
		String messagex="";
		messagex=teams[index].toString();
		return messagex;		
	}
	
	/**
	* showFormation: Show one formation <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param teamName Is the name of the team
	* @param dateA Is the date of the alignment
	* @param formationX Is the string that determines the way the matrix will be constructed
	* @return message The string with the information
	*/
	public String showFormation(String teamName, String dateA, String formationX){
		boolean found = false;
		String message="";
		int index = detectTeam(teamName);
		message = teams[index].showFormation(dateA, formationX);

		return message;
	}
	
	/**
	* updateTeam: updates the name of a team <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param teamName Is the old name of the team
	* @param newTeamName Is the new name of the team
	*/
	public void updateTeam(String teamName, String newTeamName){
		boolean found = false;
		int index = detectTeam(teamName);
		teams[index].setName(newTeamName);
	}
	
	/**
	* getEmployeeType: get the type of the employee <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param id The id of the employee
	* @return type A number that determines the type of the employee
	*/
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
	
	/**
	* updateName: updates the name of the employee <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param id Is the id of the employee
	* @param newName Is the new name of the employee
	*/
	public void updateName(String id, String newName){
		boolean found = false;
		int index = detectEmployee(id);
		payroll.get(index).setName(newName);
	}
	
	/**
	* updateId: updates the id of the employee <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param id Is the id of the employee
	* @param newId Is the new id of the employee
	*/
	public void updateId(String id, String newId){
		boolean found = false;
		int index = detectEmployee(id);
		payroll.get(index).setId(newId);
	}
	
	/**
	* updateSalary: updates the salary of the employee <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param id Is the id of the employee
	* @param newSalary Is the new salary of the employee
	*/
	public void updateSalary(String id, int newSalary){
		boolean found = false;
		int index = detectEmployee(id);
		payroll.get(index).setSalary(newSalary);
	}
	
	/**
	* updateState: updates the state of the employee <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param id Is the old id of the employee
	* @param newState Is the new state of the employee
	*/
	public void updateState(String id, boolean newState){
		boolean found = false;
		int index = detectEmployee(id);
		payroll.get(index).setState(newState);
	}
	
	/**
	* updateShirtN: updates the shirt number of the player <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param id Is the id of the employee
	* @param newShirtN Is the new shirt number of the employee
	*/
	public void updateShirtN(String id, String newShirtN){
		boolean found = false;
		int index = detectEmployee(id);
		((Player)payroll.get(index)).setShirtNumber(newShirtN);
	}
	
	/**
	* updateGoals: updates the goals of the player <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param id Is the id of the employee
	* @param newGoals Is the new number of goals of the employee
	*/
	public void updateGoals(String id, int newGoals){
		boolean found = false;
		int index = detectEmployee(id);
		((Player)payroll.get(index)).setGoals(newGoals);
	}
	
	/**
	* updateEvaAve: updates the avearage evaluation of the player <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param id Is the id of the employee
	* @param newEvaAve Is the new avearage evaluation of the employee
	*/
	public void updateEvaAve(String id, double newEvaAve){
		boolean found = false;
		int index = detectEmployee(id);
		((Player)payroll.get(index)).setEvaAve(newEvaAve);
	}
	
	/**
	* updatePosition: updates the position of the player <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param id Is the id of the employee
	* @param newPosition Is the new position of the employee
	*/
	public void updatePosition(String id, String newPosition){
		boolean found = false;
		int index = detectEmployee(id);
		((Player)payroll.get(index)).setPosition(newPosition);
	}
	
	/**
	* updateYearsXp: updates the years of experience of the coach <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param id Is the id of the employee
	* @param newYearsXp Is the new number of years of experience of the employee
	*/
	public void updateYearsXp(String id, int newYearsXp){
		boolean found = false;
		int index = detectEmployee(id);
		((Coach)payroll.get(index)).setYearsXp(newYearsXp);
	}
	
	/**
	* updateWonChamps: updates the won championships of the principal coach <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param id Is the id of the employee
	* @param newChampsNames Is the new list of won championships names
	*/
	public void updateWonChamps(String id, String[] newChampsNames){
		boolean found = false;
		int index = detectEmployee(id);
		((PrincipalCoach)payroll.get(index)).setChampionships(newChampsNames);
	}
	
	/**
	* updateNumTeams: updates the number of teams on hte charge of the coach <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param id Is the id of the employee
	* @param newNumTeams Is the new number of teams on his charge
	*/
	public void updateNumTeams(String id, int newNumTeams){
		boolean found = false;
		int index = detectEmployee(id);
		((PrincipalCoach)payroll.get(index)).setNumTeams(newNumTeams);
	}
	
	/**
	* updateActive: updates the activity of technical assistent <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param id Is the id of the employee
	* @param newActive Is the new activity status of the employee
	*/
	public void updateActive(String id, boolean newActive){
		boolean found = false;
		int index = detectEmployee(id);
		((TecAsistent)payroll.get(index)).setActive(newActive);
	}
	
	/**
	* updateExpertises: updates the expertise list of the technical assistent <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param id Is the id of the employee
	* @param newExper Is the new String array that contains the expertises of the employee
	*/
	public void updateExpertises(String id, String[] newExper){
		boolean found = false;
		int index = detectEmployee(id);
		((TecAsistent)payroll.get(index)).setExpertises(newExper);
		
	}
	
}	