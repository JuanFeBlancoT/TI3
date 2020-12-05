package model;	
import java.util.*;

public class Alignment{
	//constants
	private final int ROWS = 10;				//Rows of the formation matrix
	private final int COLS = 7;					//Columns of the formation matrix
	
	//atributes
	private String date;						//The date of the alignment
	private ArrayList <String> formation;		//arraylist with the String that contains the formation
	private int[][] formationMatrix;			//Is the matrix of the formation
	//relations
	private Tactic tactic;
	
	/**
	* Alignment: Constructor of Team <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param date Is the date of the alignment
	* @param tactics Is the String with the tactic	
	*/
	public Alignment(String date, String tactics){
		this.date = date;
		tactic = Tactic.valueOf(tactics);
		formation = new ArrayList <String>();
		formationMatrix = new int [ROWS][COLS];
		//position=Position.valueOf(positionP);
	}
	
	/**
	* getDate: Gets the date of the alignment <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return date Is the date of the alignment
	*/
	public String getDate(){
		return date;
	}
	
	/**
	* addFormation: Adds a new formation to the String  arraylist formation <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param formationX Is the String with the formation
	*/
	public void addFormation(String formationX){
		formation.add(formationX);
	}
	
	/**
	* findFormation: Search for a given formation within the arraylist formation <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param formationX Is the String with the formation
	* @return found The boolean that determines if it matched or not
	*/
	public boolean findFormation(String formationX){
		boolean found = false;
		for(int i=0;i<formation.size() && !found;i++){
			if(formation.get(i).equalsIgnoreCase(formationX)){
				found = true;
			}
		}
		return found;
	}
	
	/**
	* selectFormation: Prepares the formation String to be conerted into a matrix <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param formationX Is the String with the formation
	* @return message Is the matrix of the formation
	*/
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
	
	/**
	* selectFormation: Prepares the formation String to be conerted into a matrix <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param index Is the int that determines the position of the formation that its going to be converted into a matrix
	* @return message Is the matrix of the formation
	*/
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
		message+=("\n"+
		"\n ** Delanteros: "+ numsFormation[2] +
		"\n ** Volantes: "+ numsFormation[1] +
		"\n ** Defensores: "+ numsFormation[0]);
		
		message+= "\n\n ** Tactica: " + tactic;
		
		return message;	
		
	}//end addFormation
	
	/**
	* showFormation: Actually cretaes the String matrix <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param fMatrix Is the matrix that will be converted into a String
	* @return message Is the matrix of the formation
	*/
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
	
	/**
	* binaryIntterpreter: takes the amount of numbers in a row of the mtrix and the index of the current position in the columns and return if in that tile there must be an 0 or 1 <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param playersRow Is the amount of player in a row
	* @param index Is the position in the column of the matrix
	* @return binaryNum the integer that represents if in that position there is a player or not
	*/
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