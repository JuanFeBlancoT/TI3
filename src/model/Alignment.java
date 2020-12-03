package model;	
import java.util.*;

public class Alignment{
	
	//atributes
	private String date;
	private ArrayList <String> formation;
	//relations
	private Tactic tactic;
	
	public Alignment(String date, String tactics){
		this.date = date;
		tactic = Tactic.valueOf(tactics);
		formation = new ArrayList <String>();
		
		//position=Position.valueOf(positionP);
	}
	
	public String getDate(){
		return date;
	}
	
	public void addFormation(String formationX){
		formation.add(formationX);
	}
}