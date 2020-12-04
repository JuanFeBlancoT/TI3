package model;

public class Coach extends Employee{
	
	//atributes
	private int yearsXp;
	
	public Coach(String name, String id, int salary, boolean state, int yearsXp){
		super(name, id, salary, state);
		this.yearsXp = yearsXp;
	}
	
	public int getYearsXp(){
		return yearsXp;
	}
	
	public void setYearsXp(int yearsXp){
		this.yearsXp = yearsXp;
	}
	
	public String toString(){
		
		String message=super.toString();

		message+="\n ** Anios de experiencia: "+ yearsXp;
		
		return message;
	}
}