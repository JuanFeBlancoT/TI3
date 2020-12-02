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
		
		//position=Position.valueOf(positionP);
	}
}