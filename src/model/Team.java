package model;
import java.util.*;

public class Team{
	//constants
	private final int MAX_TEC_ASIS = 3;
	private final int MAX_PLAYERS = 25;
	//atributes
	private String name;
	private int[][] changeRoomLayout;
	private int asignedChangeR;
	//relations
	private PrincipalCoach principalCoach;
	private TecAsistent[] tecAsistents;
	private Player[] template;
	private ArrayList <Alignment> alignments;
	//private Employee[] members;
	
	/**
	* Team: Constructor of Team <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param name Is the name of the team
	* @param changeRType Is the number that represents if they get the big or small changeroom
	* @param row Is the row size of the changeroom
	* @param col Is the column size of the changeroom	
	*/
	public Team(String name, int changeRType, int row, int col){
		this.name = name;
		asignedChangeR = changeRType;
		principalCoach = null;
		tecAsistents = new TecAsistent[MAX_TEC_ASIS];
		template = new Player[MAX_PLAYERS];
		alignments = new ArrayList<Alignment>();
		for(int i=0;i<template.length;i++){
			template[i]=null;
		}
		
		changeRoomLayout = new int[row][col];
	}
	
	/**
	* getName: Gets the team name <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return name Is the name of the team
	*/
	public String getName(){
		return name;
	}
	
	/**
	* setName: Sets the team name <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param name The new name of the team
	*/
	public void setName(String name){
		this.name = name;
	}
	
	/**
	* findPlayer: find a player id within the array of players <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param playerId The id that will be compared
	* @return found determines if the player is already in the team or not
	*/
	public boolean findPlayer(String playerId){
		boolean found = false;
		for(int i=0;i<template.length && !found;i++){
			if(template[i]!=null && template[i].getId().equalsIgnoreCase(playerId)){
				found = true;
			}
		}
		return found;
	}
	
	/**
	* addPrincipalCoach: adds a principal coach to the team <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param employeeX Is the object employee that will be asing to the principalCoach of the team
	*/
	public void addPrincipalCoach(Employee employeeX){
		
		String message = "";
		if(principalCoach == null){
			principalCoach = (PrincipalCoach)employeeX;
		}
	}
	
	/**
	* addTecAsis: Adds a techincal assistent to the array of these ones<br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param employeeX Is the object employee that will be added to the array of techincal assistents of the team
	*/
	public void addTecAsis(Employee employeeX){
		boolean stop = false;
		boolean stopSearching = false;
		boolean alreadyIn = false;
		
		for(int j=0; j<tecAsistents.length && !stopSearching;j++){
			if(tecAsistents[j]!=null && tecAsistents[j].getId().equalsIgnoreCase(employeeX.getId())){
				stopSearching =true;
			}
		}
			for(int i=0; i<tecAsistents.length && !stop; i++){
				if(tecAsistents[i]==null && stopSearching == false){
					stop = true;
					tecAsistents[i] = (TecAsistent)employeeX;
				}
			}
		
		
	}
	
	/**
	* addPlayer: adds a player to the array of players <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param employeeX Is the object employee that will be added to the players array of the team
	*/
	public void addPlayer(Employee employeeX){
		boolean stop = false;
		boolean stopSearching = false;
		boolean alreadyIn = false;
		
		for(int j=0; j<template.length && !stopSearching;j++){
			if(template[j]!=null && template[j].getId().equalsIgnoreCase(employeeX.getId())){
				stopSearching =true;
			}
		}
		
		for(int i=0; i<template.length && !stop; i++){
			if(template[i]==null && stopSearching == false){
				stop = true;
				template[i] = (Player)employeeX;
			}
		}
	}
	
	/**
	* fireEmployee: removes an employee from the team <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param id Is the id of the employee that is going to get fired
	*/
	public void fireEmployee(String id){
		if(principalCoach!=null && principalCoach.getId().equalsIgnoreCase(id)){
			principalCoach=null;
		}
		boolean stop = false;
		for(int i=0;i<tecAsistents.length && !false;i++){
			if(tecAsistents[i]!=null && tecAsistents[i].getId().equalsIgnoreCase(id)){
				stop = true;
				tecAsistents[i]=null;
			}
		}
		boolean stop2 = false;
		for(int j=0;j<tecAsistents.length && !stop2;j++){
			if(template[j]!=null && template[j].getId().equalsIgnoreCase(id)){
				stop2 = true;
				template[j]=null;
			}
		}
	}
	
	/**
	* addAlignment: adds an alignment to the team <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param dateA Is the date of the alignment
	* @param tactic Is the String that determines the tactic 
	*/
	public void addAlignment(String dateA, String tactic){
		Alignment alignmentX = new Alignment(dateA, tactic);
		alignments.add(alignmentX);		
	}
	
	/**
	* toString: Shows the information of the team <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return String Is the String with all the information of the team
	*/
	public String toString(){
		String message = ("\n *** EQUIPO *** " +
		"\n ** Name: "+ name);
		if(principalCoach!=null){
			message+=("\n ** Entrenador principal: " +  principalCoach.getName());
		}else{
			message+="\n ** Entrenador principal: PENDING";
		}
		
		for(int i=0; i<tecAsistents.length; i++){
			if(tecAsistents[i]!=null){
				message+=("\n ** Asistente tecnico: " +  tecAsistents[i].getName());
			}
		}
		
		for(int j=0; j<template.length; j++){
			if(template[j]!=null){
				message+=("\n ** Jugador: " +  template[j].getName());
			}
		}
		
		return message;
	}// end toString
	
	/**
	* findAlignment: Finds an alingnment <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param dateA Is the date of the alignment
	* @return found Determines if the alignment exists or not
	*/
	public boolean findAlignment(String dateA){
		boolean found = false;
		for(int i=0;i<alignments.size() && !found ;i++){
			if(alignments.get(i).getDate().equalsIgnoreCase(dateA)){
				found = true;
			}
		}
		return found;
	}
	
	/**
	* findFormation: Finds a formation <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param dateA Is the date associated with an alignment
	* @param formationX Is the String of the formation
	* @return exist Determines if the formation is registered or not
	*/
	public boolean findFormation(String dateA, String formationX){
		boolean found = false;
		boolean exist = false;
		
		for(int i=0;i<alignments.size() && !found ;i++){
			if(alignments.get(i).getDate().equalsIgnoreCase(dateA)){
				found = true;
				exist = alignments.get(i).findFormation(formationX);
			}
		}
		return exist;
	}
	
	/**
	* addFormation: adds a formation <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param dateA Is the date associated with an alignment
	* @param formationX Is the String of the formation
	*/
	public void addFormation(String dateA, String formationX){
		boolean found = false;
		for(int i=0;i<alignments.size() && !found ;i++){
			if(alignments.get(i).getDate().equalsIgnoreCase(dateA)){
				found = true;
				alignments.get(i).addFormation(formationX);	
			}
		}
	}
	
	/**
	* organizePlayers: organize players in their changeroom and returns the matrx showing their positions <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param row Is the row of the matrix
	* @param col Is the column of the matrix
	* @return message The string showing the matrix
	*/
	public String organizePlayers(int row, int col){
	
		String message ="";
		int playerIndex=0;
		boolean empty = false;
		
		/*List<Player> playersTemplate = Arrays.asList(template);
		Collections.shuffle(playersTemplate);
		playersTemplate.toArray(template);*/
		
		
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				
				if(empty==false && template[playerIndex]!=null){
					changeRoomLayout[i][j] = Integer.parseInt(template[playerIndex].getShirt());
					playerIndex++;
				}
				
				empty=!empty;
			}
		}
		
		message=showChangeRoom(row, col, changeRoomLayout);
			
		return message;
	}
	
	/**
	* organizePlayers: preapres the String with the changeroom matrix <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param row Is the row of the matrix
	* @param col Is the column of the matrix
	* @param matrixCR Is the matrix of the players in the changeroom
	* @return message The string showing the matrix
	*/
	public String showChangeRoom(int row, int col, int[][] matrixCR){
		String message="";
		
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				if(matrixCR[i][j]<10){
					message+="[0"+matrixCR[i][j]+"] ";
				}else{
				message+="["+matrixCR[i][j]+"] ";
				}
			}
			message+="\n";
		}
		
		return message;
	}
	
	/**
	* showFormation: shows a given formation <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param dateA Is the date of the alignment
	* @param formationX Is tthe String with the formation to show
	* @return message The string showing the formation
	*/
	public String showFormation(String dateA, String formationX){
		boolean found = false;
		String message="";
		
		for(int i=0;i<alignments.size() && !found ;i++){
			if(alignments.get(i).getDate().equalsIgnoreCase(dateA)){
				found = true;
				message = alignments.get(i).selectFormation(formationX);	
			}
		}
		return message;
	}
	
}