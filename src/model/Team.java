package model;
import java.util.*;

public class Team{
	//constants
	private final int MAX_TEC_ASIS = 3;
	private final int MAX_PLAYERS = 25;
	private final int MAX_MEMBERS = 29;
	//atributes
	private String name;
	
	//relations
	private PrincipalCoach principalCoach;
	private TecAsistent[] tecAsistents;
	private Player[] template;
	private ArrayList <Alignment> alignments;
//	private Employee[] members;
	
	public Team(String name){
		this.name = name;
		principalCoach = null;
		tecAsistents = new TecAsistent[MAX_TEC_ASIS];
		template = new Player[MAX_PLAYERS];
		alignments = new ArrayList<Alignment>();
	}
	
	public String getName(){
		return name;
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
	
	public void addAlignment(String dateA, String tactic){
		Alignment alignmentX = new Alignment(dateA, tactic);
		alignments.add(alignmentX);		
	}
	
	public String toString(){
		String message = (" *** EQUIPO *** " +
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