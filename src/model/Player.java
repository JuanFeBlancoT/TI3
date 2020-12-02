package model;

public class Player extends Employee{
	
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
	
	public String toString(){
		
		String message=super.toString();

		message+="\n ** Numero de camiseta: "+ shirtNumber +
		"\n ** Cantidad de goles: "+ goals +
		"\n ** Calificacion promedio: "+ evaAvearage +
		"\n ** Posicion: "+ position+"\n";
		
		return message;
	}
}