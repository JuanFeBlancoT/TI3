package model;

public class Coach extends Employee{
	
	//atributes
	private int yearsXp;
	
	public Coach(String name, String id, int salary, boolean state, int yearsXp){
		super(name, id, salary, state);
		this.yearsXp = yearsXp;
	}
	
	public String toString(){
		
		String message=super.toString();

		message+="\n ** years of experience: "+ yearsXp;
		
		return message;
	}
}