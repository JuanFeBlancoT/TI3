package model;

public class TecAsistent extends Coach{

	//atributes
	private boolean activedP;
	//relations
	private Expertise[] listExpertise;
	
	/**
	* TecAsistent: Constructor of TecAsistent <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param name Is the name of the employee
	* @param id Is the id of the employee
	* @param salary Is the salary of the employee
	* @param state Is the state of the employee
	* @param yearsXp Is the years of experience of the technical assistent
	* @param activedP Is the activity of the technical assistent
	* @param listExper Is the list of expertises of the technical assistent
	*/
	public TecAsistent(String name, String id, int salary, boolean state, int yearsXp, boolean activedP, String[] listExper){
		super(name, id, salary, state, yearsXp);
		this.activedP = activedP;
		listExpertise = new Expertise[6];
		
		for(int i=0;i<listExpertise.length && listExper[i]!=null;i++){
			listExpertise[i]= Expertise.valueOf(listExper[i]);
		}
		
	}
	
	/**
	* setActive: Sets the activity of the technical assistent <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param activedP Is the new activity of the technical assistent
	*/
	public void setActive(boolean activedP){
		this.activedP = activedP;
	}
	
	/**
	* setExpertises: Sets the array of expertises of the technical assistent <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param listExpert Is the new array of expertises of the technical assistent
	*/
	public void setExpertises(String[] listExpert){
		for(int i=0;i<listExpertise.length && listExpert[i]!=null;i++){
			listExpertise[i]= Expertise.valueOf(listExpert[i]);
		}
	}
	
	/**
	* toString: Shows the information of the technical assistent <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return message Is the String with all the information
	*/
	@Override
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