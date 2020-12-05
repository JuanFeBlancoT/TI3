package model;

public abstract class Coach extends Employee{
	
	//atributes
	private int yearsXp;		//Years of experiencie of the coach
	
	/**
	* Coach: Constructor of Coach <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param name Is the name of the employee
	* @param id Is the id of the employee
	* @param salary Is the salary of the employee
	* @param state Is the state of the employee
	* @param yearsXp Is the years of experiencie of the coach
	*/
	public Coach(String name, String id, int salary, boolean state, int yearsXp){
		super(name, id, salary, state);
		this.yearsXp = yearsXp;
	}
	
	/**
	* getYearsXp: Gets the years of experiencie of the coach <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return yearsXp Is the years of experiencie of the coach
	*/
	public int getYearsXp(){
		return yearsXp;
	}
	
	/**
	* setYearsXp: Sets the years of experiencie of the coach <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param yearsXp Is the new years of experiencie of the coach
	*/
	public void setYearsXp(int yearsXp){
		this.yearsXp = yearsXp;
	}
	
	/**
	* toString: Shows the information of the coach <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return message Is the String with all the information
	*/
	@Override
	public String toString(){
		
		String message=super.toString();

		message+="\n ** Anios de experiencia: "+ yearsXp;
		
		return message;
	}
}