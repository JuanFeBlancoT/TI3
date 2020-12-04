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
	
	public void setActive(boolean activedP){
		this.activedP = activedP;
	}
	
	public void setExpertises(String[] listExpert){
		for(int i=0;i<listExpertise.length && listExpert[i]!=null;i++){
			listExpertise[i]= Expertise.valueOf(listExpert[i]);
		}
	}
	
	public String toString(){
		
		String message=super.toString();
		if(activedP){
			message+="\n ** Fue un jugador activo: Si";		
		}else{
			message+="\n ** Fue un jugador activo: No";		
		}
		
		
		message+="\n ** Experticias: ";
		for(int i=0; i<listExpertise.length && listExpertise[i]!=null;i++){
			message+="\n	* " + listExpertise[i];
		}
		message+="\n";
		
		return message;
	}
}