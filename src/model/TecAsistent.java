package model;

public class TecAsistent extends Coach{

	//atributes
	private boolean activedP;
	//relations
	private Expertise[] listExpertise;
	
	public TecAsistent(String name, String id, int salary, boolean state, int yearsXp, boolean activedP, String[] listExper){
		super(name, id, salary, state, yearsXp);
		this.activedP = activedP;
		listExpertise = new Expertise[6];
		
		for(int i=0;i<listExpertise.length && listExper[i]!=null;i++){
			listExpertise[i]= Expertise.valueOf(listExper[i]);
		}
		
	}
	
	public String toString(){
		
		String message=super.toString();

		message+="\n ** Fue un jugador activo: "+ activedP;
		
		message+="\n ** Expertises: ";
		for(int i=0; i<listExpertise.length && listExpertise[i]!=null;i++){
			message+="\n	* " + listExpertise[i];
		}
		message+="\n";
		
		return message;
	}
}