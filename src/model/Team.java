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
//	private Employee[] members;
	
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
	
	public String getName(){
		return name;
	}
	
	public boolean findPlayer(String playerId){
		boolean found = false;
		for(int i=0;i<template.length && !found;i++){
			if(template[i]!=null && template[i].getId().equalsIgnoreCase(playerId)){
				found = true;
			}
		}
		return found;
	}
	
	public void addPrincipalCoach(Employee employeeX){
		
		String message = "";
		if(principalCoach == null){
			principalCoach = (PrincipalCoach)employeeX;
		}
	}
	
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
	
	public void addAlignment(String dateA, String tactic){
		Alignment alignmentX = new Alignment(dateA, tactic);
		alignments.add(alignmentX);		
	}
	
	public String toString(){
		String message = ("\n *** EQUIPO *** " +
		"\n ** Name: "+ name);
		if(principalCoach!=null){
			message+=("\n ** Coach principal: " +  principalCoach.getName());
		}else{
			message+="\n ** Coach principal: PENDING";
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
	
	public boolean findAlignment(String dateA){
		boolean found = false;
		for(int i=0;i<alignments.size() && !found ;i++){
			if(alignments.get(i).getDate().equalsIgnoreCase(dateA)){
				found = true;
			}
		}
		return found;
	}
	
	public void addFormation(String dateA, String formationX){
		boolean found = false;
		for(int i=0;i<alignments.size() && !found ;i++){
			if(alignments.get(i).getDate().equalsIgnoreCase(dateA)){
				found = true;
				alignments.get(i).addFormation(formationX);	
			}
		}
	}
	
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