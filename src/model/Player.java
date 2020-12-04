package model;

public class Player extends Employee implements Calculations{
	
	//atributes
	private String shirtNumber;
	private int goals;
	private double evaAvearage;
	
	//relations
	private Position position;
	
	public Player(String name, String id, int salary, boolean state, String shirtNumber, int goals, double evaAvearage, String positionP){
		super(name, id, salary, state);
		this.shirtNumber = shirtNumber;
		this.goals = goals;
		this.evaAvearage = evaAvearage;
		position=Position.valueOf(positionP);
	}
	
	public String getShirt(){
		return shirtNumber;
	}
	
	public int getGoals(){
		return goals;
	}
	
	public double getEvaAvearage(){
		return evaAvearage;
	}
	
	public void setShirtNumber(String shirtNumber){
		this.shirtNumber = shirtNumber;
	}
	
	public void setGoals(int goals){
		this.goals = goals;
	}
	
	public void setEvaAve(double evaAvearage){
		this.evaAvearage = evaAvearage;
	}
	
	public void setPosition(String positionX){
		position=Position.valueOf(positionX);
	}
	
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