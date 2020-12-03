package model;	
import java.util.*;

public class Alignment{
	//constants
	private final int ROWS = 10;
	private final int COLS = 7;
	
	//atributes
	private String date;
	private ArrayList <String> formation;
	private int[][] formationMatrix;
	//relations
	private Tactic tactic;
	
	public Alignment(String date, String tactics){
		this.date = date;
		tactic = Tactic.valueOf(tactics);
		formation = new ArrayList <String>();
		formationMatrix = new int [ROWS][COLS];
		//position=Position.valueOf(positionP);
	}
	
	public String getDate(){
		return date;
	}
	
	public void addFormation(String formationX){
		formation.add(formationX);
	}
	
	public String selectFormation(String formationX){
		String message="";
		boolean found = false;
		
		for(int i=0;i<formation.size() && !found;i++){
			if(formation.get(i).equalsIgnoreCase(formationX)){
				found = true;
				message = matrixFormation(i);
			}
		}
		return message;
	}
	
	public String matrixFormation(int index){
		String message ="";
		String[] formText = formation.get(index).split("-");
		int[] numsFormation = new int[formText.length];
		
		for(int i=0;i<formText.length;i++){
			numsFormation[i] = Integer.parseInt(formText[i]);
		}
		for(int j=0;j<COLS;j++){
			formationMatrix[7][j]=binaryIntterpreter(numsFormation[0], j);
			formationMatrix[4][j]=binaryIntterpreter(numsFormation[1], j);
			formationMatrix[1][j]=binaryIntterpreter(numsFormation[2], j);
		}
		
		message=showFormation(formationMatrix);
		return message;	
		
	}//end addFormation
	
	public String showFormation(int[][] fMatrix){
		String message="\n *** FORMACION ***\n"; 
		for(int i=0;i<ROWS;i++){
			for(int j=0;j<COLS;j++){
				message+="["+fMatrix[i][j]+"] ";
			}
			message+="\n";
		}
		return message;
	} 
	
	
	public int binaryIntterpreter(int playersRow, int index){
			int binaryNum = 0;
		
			switch(playersRow){
				case 0:
					binaryNum=0;
				break;
				case 1:
					if(index==3){
						binaryNum=1;
					}else{
					binaryNum=0;
					}
				break;
				case 2:
					if(index==1 || index==5){
						binaryNum=1;
					}else{
					binaryNum=0;
					}
				break;
				case 3:
					if(index%2==0){
						binaryNum=0;
					}else{
					binaryNum=1;
					}
				break;
				case 4:
					if(index==0 || index==3 || index==6){
						binaryNum=0;
					}else{
					binaryNum=1;
					}
				break;
				case 5:
					if(index==2 || index==4){
						binaryNum=0;
					}else{
					binaryNum=1;
					}
				break;
				case 6:
					if(index==3){
						binaryNum=0;
					}else{
					binaryNum=1;
					}
				break;
				case 7:
					binaryNum=1;
				break;
			}
		
		return binaryNum;
		
	}//end b
}