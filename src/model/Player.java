package model;

public class Player extends Employee implements Calculations{
	
	//atributes
	private String shirtNumber;			//Shirt number of the player
	private int goals;					//The amount of goals the player has made in the club
	private double evaAvearage;			//The avearage evaluation of the player
	
	//relations
	private Position position;			//The player position
	
	/**
	* Player: Constrctor of Player <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param name Is the name of the employee
	* @param id Is the id of the employee
	* @param salary Is the salary of the employee
	* @param state Is the state of the employee
	* @param shirtNumber Is the shirt number of the player
	* @param goals Are the goals of the player
	* @param evaAvearage Is the avearage evaluation of the player
	* @param positionP Is the position of the player
	*/
	public Player(String name, String id, int salary, boolean state, String shirtNumber, int goals, double evaAvearage, String positionP){
		super(name, id, salary, state);
		this.shirtNumber = shirtNumber;
		this.goals = goals;
		this.evaAvearage = evaAvearage;
		position=Position.valueOf(positionP);
	}
	
	/**
	* getShirt: Gets the shirt number of the player <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return shirtNumber Is the shirt number of the player
	*/
	public String getShirt(){
		return shirtNumber;
	}
	
	/**
	* getGoals: Gets the goals number of the player <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return goals Is the goals number of the player
	*/
	public int getGoals(){
		return goals;
	}
	
	/**
	* getEvaAvearage: Gets the avearage evaluation of the player <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return evaAvearage Is the avearage evaluation of the player
	*/
	public double getEvaAvearage(){
		return evaAvearage;
	}
	
	/**
	* setShirtNumber: Sets the shirt number of the player <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param shirtNumber Is the new shirt number of the player
	*/
	public void setShirtNumber(String shirtNumber){
		this.shirtNumber = shirtNumber;
	}
	
	/**
	* setGoals: Sets the goals number of the player <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param goals Is the new goals number of the player
	*/
	public void setGoals(int goals){
		this.goals = goals;
	}
	
	/**
	* setEvaAve: Sets the avearage evaluation of the player <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param evaAvearage Is the new avearage evaluation of the player
	*/
	public void setEvaAve(double evaAvearage){
		this.evaAvearage = evaAvearage;
	}
	
	/**
	* setPosition: Sets the position of the player <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param positionX Is the new position of the player
	*/
	public void setPosition(String positionX){
		position=Position.valueOf(positionX);
	}
	
	@Override
	public double calculateMarketPrice(){
		double price = 0;
		
		switch(position){
			case PORTERO:
				price = (getSalary()*12)+(getEvaAvearage()*150);
			break;
			case DEFENSOR:
				price = (getSalary()*13)+(getEvaAvearage()*125)+(getGoals()*100);
			break;
			case VOLANTE:
				price = (getSalary()*14)+(getEvaAvearage()*135)+(getGoals()*125);
			break;
			case DELANTERO:
				price = (getSalary()*15)+(getEvaAvearage()*145)+(getGoals()*150);
			break;
		}
		
		return price;
	} 
	
	@Override
	public double calculateStarLevel(){
		double levelS = 0;
		
		switch(position){
			case PORTERO:
				levelS = getEvaAvearage()*0.9;
			break;
			case DEFENSOR:
				levelS = (getEvaAvearage()*0.9)+((double)getGoals()/100);
			break;
			case VOLANTE:
				levelS = (getEvaAvearage()*0.9)+((double)getGoals()/90);
			break;
			case DELANTERO:
				levelS = (getEvaAvearage()*0.9)+((double)getGoals()/80);
			break;
		}
		
		return levelS;
	}
	
	/**
	* toString: Shows the information of the player <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return message Is the String with all the information
	*/
	@Override 
	public String toString(){
		
		String message=super.toString();

		message+="\n ** Numero de camiseta: "+ shirtNumber +
		"\n ** Cantidad de goles: "+ goals +
		"\n ** Calificacion promedio: "+ evaAvearage +
		"\n ** Posicion: "+ position+
		"\n ** Precio mercado: "+ calculateMarketPrice()+
		"\n ** Nivel como estrella: "+ calculateStarLevel()+"\n";
		
		return message;
	}
}