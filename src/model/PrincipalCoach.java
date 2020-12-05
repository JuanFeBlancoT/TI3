package model;

public class PrincipalCoach extends Coach implements Calculations{

	//atributes
	private int numberTeams;
	private String[] championships;
	private int nChampionships;
	
	/**
	* Employee: Constructor of Employee <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param name Is the name of the employee
	* @param id Is the id of the employee
	* @param salary Is the salary of the employee
	* @param state Is the state of the employee
	* @param yearsXp Is the years of experience of the principal coach
	* @param numberTeams Is the number of theams of the principal coach
	* @param championships Is the String array with the name of all the won championships of the principal coach
	*/
	public PrincipalCoach(String name, String id, int salary, boolean state, int yearsXp, int numberTeams, String[] championships){
		super(name, id, salary, state, yearsXp);
		this.numberTeams = numberTeams;
		this.championships = championships;
		nChampionships = championships.length;
	}
	
	/**
	* getWonChampionships: Gets the number of won championships of the principal coach <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return nChampionships Is the number of won championships of the principal coach
	*/
	public int getWonChampionships(){
		return nChampionships;
	}
	
	/**
	* setNumTeams: Sets the number of teams of the principal coach <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param numberTeams Is the new number of teams of the principal coach
	*/
	public void setNumTeams(int numberTeams){
		this.numberTeams = numberTeams;
	}
	
	/**
	* setChampionships: Sets the number of won championships and their names, of the principal coach <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param championships Is the array of names of the won championships
	*/
	public void setChampionships(String[] championships){
		this.championships = championships;
		this.nChampionships = championships.length;
	}
	
	@Override
	public double calculateMarketPrice(){
		double price = 0;
		price = (getSalary()*10)+(getYearsXp()*100)+(getWonChampionships()*50);
		
		return price;
	} 
	
	@Override
	public double calculateStarLevel(){
		double levelS = 0;
		levelS = 5 + ((double)getWonChampionships()/10);
		return levelS;
	}
	
	/**
	* toString: Shows the information of the principal coach <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return message Is the String with all the information
	*/
	@Override
	public String toString(){
		
		String message=super.toString();
		
		message+="\n ** Numero de equipos: "+ numberTeams;
		
		message+="\n ** Campeonatos ganados: ";
		for(int i=0; i<nChampionships;i++){
			message+="\n	* " + championships[i];
		}
		message+="\n ** Precio mercado: "+calculateMarketPrice()+"\n";
		
		return message;
	}
	
}