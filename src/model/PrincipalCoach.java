package model;

public class PrincipalCoach extends Coach implements Calculations{

	//atributes
	private int numberTeams;
	private String[] championships;
	private int nChampionships;
	
	public PrincipalCoach(String name, String id, int salary, boolean state, int yearsXp, int numberTeams, String[] championships){
		super(name, id, salary, state, yearsXp);
		this.numberTeams = numberTeams;
		this.championships = championships;
		nChampionships = championships.length;
	}
	
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
	
	public int getWonChampionships(){
		return nChampionships;
	}
	
	public double calculateMarketPrice(){
		double price = 0;
		price = (getSalary()*10)+(getYearsXp()*100)+(getWonChampionships()*50);
		
		return price;
	} 
	
	public void setNumberTeams(int numberTeams){
		this.numberTeams = numberTeams;
	}
		
	public double calculateStarLevel(){
		double levelS = 0;
		levelS = 5 + ((double)getWonChampionships()/10);
		return levelS;
	}
	
}