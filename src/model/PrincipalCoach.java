package model;

public class PrincipalCoach extends Coach{

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
		message+="\n";
		
		return message;
	}
	
}