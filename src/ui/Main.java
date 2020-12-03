package ui;

import model.Club;
import java.util.Scanner;

public class Main{
	
	public static Scanner sc =new Scanner(System.in);
	
	public static void main (String[] args){
		
		//atributes
		boolean menu = true;
		int numEmployees = 0;
		int numTeams = 0;
		
		//Initialize Club
		Club club1 = createClub();
		
		//option menu
		
		while(menu){
			System.out.println("\n");
			System.out.println("******** MENU ********"+
			"\n 1. Contratar empleados"+
			"\n 2. Despedir empleados"+
			"\n 3. Crear equipo"+
			"\n 4. Agregar jugadores a los equipos"+
			"\n 5. Agregar alineacion a un equipo"+
			"\n 6. Agregar formacion a la alineacion de un equipo"+
			"\n 7. Mostrar organizacion de los jugadores en un vestidor"+
			"\n 8. Mostrar empleados"+
			"\n 9. Mostrar equipos"+
			"\n 10. Salir");
			
			int option = sc.nextInt(); sc.nextLine();
			
			switch(option){
				case 1:
					contractEmployee(club1);
					numEmployees++;
			break;
			case 2:
					fireEmployee(club1);
					numEmployees--;
			break;
			case 3:
					if(numTeams<2){
						createTeam(club1);
					numTeams++;
					}else{
						System.out.println("	** CANT ADD MORE TEAMS. LIMIT REACHED **");
					}
					
			break;
			case 4:
					addPlayerToTeam(club1);
			break;
			case 5:
					addAlignment(club1);
			break;
			case 6:
					addFormation(club1);
			break;
			case 7:
					organizePlayers(club1);
			break;
			case 8:
					showEmployees(club1, numEmployees);
			break;
			case 9:
					showTeams(club1, numTeams);
			break;
			case 10:
					menu = false;
			break;

			default:
				System.out.println("\nInserta una opcion valida\n");
			break;
			}
		}
		
	}//end main
	
	/**
	* createClub: It creates a Club with a name and a fundation date <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return clubx Is the Club
	*/
	public static Club createClub(){
		
		System.out.print("\nInserta el nombre del Club: ");
		String name = sc.nextLine();
		
		System.out.print("\nInserta la fecha de fundacion del club: ");
		String fundationD = sc.nextLine();
		
		System.out.print("\nInserta el NIT del club: ");
		String nit = sc.nextLine();
		
		Club clubx = new Club(name, fundationD, nit);
		return clubx;
	}//end createClub
	
	public static void contractEmployee(Club clubx){
		
		boolean activityPlayer = false;
		
		System.out.print("Inserta el nombre del empleado: ");
		String name = sc.nextLine();
		//Ask for Id
		System.out.print("Inserta el id del empleado: ");
		String id = "";
		id = sc.nextLine();
		//Confirm that the Id isnt already asigned to another employee
		boolean takenId = false;
		takenId = findId(clubx, id);
		while(takenId){
			System.out.print("\n **** ID TOMADO ****\n");
			System.out.print("Inserta el id del empleado: ");
			id = sc.nextLine();
			takenId = findId(clubx, id);
		}
		
		System.out.print("Inserta el salario del empleado: ");
		int salary = sc.nextInt(); sc.nextLine();
		System.out.println("Es un empleado activo? si o no: ");
		String activity = sc.nextLine();
		boolean activityP = false;
		if(activity.equalsIgnoreCase("si")){
			activityP = true;
		}
		
		
		System.out.println("\nInserta el numero del tipo de empleado que agregaras "+
		"\n 1. Entrenador principal"+
		"\n 2. Asistente tecnico"+
		"\n 3. Jugador");
		
		int option;
		option = sc.nextInt(); sc.nextLine();
		//Option verification
		while(option<1 || option>3){
			System.out.print("\n **** OPCION NO VALIDA ****\n");
			System.out.println("\nInserta el numero del tipo de empleado que agregaras "+
			"\n 1. Entrenador principal"+
			"\n 2. Asistente tecnico"+
			"\n 3. Jugador");
			
			option = sc.nextInt(); sc.nextLine();
		}
		
		
		int yearsXp = 0;
		switch(option){
			case 1:
				System.out.print("Inserta los anios de experiencia del entrenador: ");
				yearsXp = sc.nextInt(); sc.nextLine();
				System.out.print("Inserta el numero de equipos que ha tenido a su cargo: ");
				int numberTeams = sc.nextInt(); sc.nextLine();
				
				System.out.print("Inserta el numero de campeonatos conseguidos: ");
				int numChamps = sc.nextInt(); sc.nextLine();
				
				String[] championships = new String[numChamps];
				for(int i=0; i<numChamps; i++){
					System.out.print("Inserta el nombre del campeonato "+ (i+1) +": ");
					championships[i] = sc.nextLine();					
				}
				clubx.addEmployee(name, id, salary, activityP, yearsXp, numberTeams, championships);
				
			break;
			case 2:
				System.out.print("Inserta los anios de experiencia del entrenador: ");
				yearsXp = sc.nextInt(); sc.nextLine();
				
				System.out.print("fue un jugador activo? si o no: ");
				String pActivity = sc.nextLine();
				
				if(pActivity.equalsIgnoreCase("si")){
					activityPlayer = true;
				}
				
				//ask for expertise
				String[] expertises = new String[6];
				int[] numExpertises = new int[6];
				boolean choosen = false;
				
				System.out.print("Tiene alguna experticia? si o no: ");
					String hasExpert = sc.nextLine();
					
					boolean keepAsking =false;
					if(hasExpert.equalsIgnoreCase("si")){
						keepAsking = true;
					}
					
				for(int i=0; i<expertises.length && keepAsking ;i++){
					
					
						System.out.println("Que experiencia tiene ?:"+
						"\n1. Ofensiva"+
						"\n2. Defensiva"+
						"\n3. Posesion"+
						"\n4. Jugadas de laboratorio"+
						"\n5. Arco"+
						"\n6. Pases");			
						int numExpertise;
						numExpertise = sc.nextInt(); sc.nextLine();
						
						//Option verification
						while(numExpertise<1 || numExpertise>6){
							System.out.print("\n **** OPCION NO VALIDA ****\n");
							System.out.println("Que experiencia tiene ?:"+
							"\n1. Ofensiva"+
							"\n2. Defensiva"+
							"\n3. Posesion"+
							"\n4. Jugadas de laboratorio"+
							"\n5. Arco"+
							"\n6. Pases");			
							
							numExpertise = sc.nextInt(); sc.nextLine();
						}
						
						choosen = confirmSelection(numExpertise, numExpertises);
						if(!choosen){
							numExpertises[i]=numExpertise;
						
						
							switch(numExpertise){
								case 1:
									expertises[i] = "OFENSIVA";
								break;
								case 2:
									expertises[i] = "DEFENSIVA";
								break;
								case 3:
									expertises[i] = "POSESION";
								break;
								case 4:
									expertises[i] = "JUGADAS_LABORATORIO";
								break;
								case 5:
									expertises[i] = "ARCO";
								break;
								case 6:
									expertises[i] = "PASES";
								break;
							}
						}else{
							System.out.print("Ya habias seleccionado esta opcion...\n");
							i--;
						}
					
					System.out.print("Tiene otra experiencia ? si o no: ");
					String hasOtherE = sc.nextLine();
					
					if(hasOtherE.equalsIgnoreCase("no")){
						keepAsking = false;
					}
					
				}
				
				clubx.addEmployee(name, id, salary, activityP, yearsXp, activityPlayer, expertises);
			break;
			case 3:
				System.out.print("Inserta el numero de camiseta del jugador: ");
				String numShirt = sc.nextLine();
				System.out.print("Inserta la cantidad de goles marcados en el club: ");
				int goals = sc.nextInt();
				System.out.print("Inserte su calificacion promedio: ");
				double evaAve = sc.nextDouble(); sc.nextLine();
				
				System.out.println("Inserta la posicion"+
				"\n 1. Delantero"+
				"\n 2. Volante"+
				"\n 3. Defensor"+
				"\n 4. Portero");
				
				String position = "";
				int pos;
				pos = sc.nextInt(); sc.nextLine();
				
					while(pos<1 || pos>4){
						System.out.print("\n **** OPCION NO VALIDA ****\n");
						System.out.println("Inserta la posicion"+
						"\n 1. Delantero"+
						"\n 2. Volante"+
						"\n 3. Defensor"+
						"\n 4. Portero");
						
						pos = sc.nextInt(); sc.nextLine();
					}
					
				switch(pos){
					case 1:
						position = "DELANTERO";
					break;
					case 2:
						position = "VOLANTE";
					break;
					case 3:
						position = "DEFENSOR";
					break;
					case 4:
						position = "PORTERO";
					break;
				}
				
				clubx.addEmployee(name, id, salary, activityPlayer, numShirt, goals, evaAve, position);
				
			break;
		}
	}//end contractEmployee
	
	
	public static void fireEmployee(Club clubx){
		System.out.print("Inserta el Id del empleado que deseas despedir: ");
		String firedId = sc.nextLine();
		boolean existingId = clubx.findId(firedId);
		if(existingId){
			clubx.fireEmployee(firedId);
		}else{
			System.out.print(" ** ESTE ID NO EXISTE **\n");
		}
	}
	
	public static boolean confirmSelection(int selection, int[] options){
		
		boolean stop=false;
		boolean choosen = false;
		for(int i=0;i<options.length && !stop;i++){
			if(options[i]==selection){
				choosen = true;
			}
		}
		return choosen;
	}
	
	public static void createTeam(Club clubx){
		System.out.print("Inserta el nombre del equipo: ");
		String teamName = sc.nextLine();
		boolean takenTeamName = clubx.findTeam(teamName);
		if(takenTeamName){
			System.out.print(" Taken team name");
		}else{
			clubx.createTeam(teamName);
		}
		
	}
	
	public static void addPlayerToTeam(Club clubx){
		
		System.out.print("Inserta el nombre del equipo: ");
		String teamName = sc.nextLine();
		boolean existingTeam = clubx.findTeam(teamName);
		if(existingTeam){
				System.out.print("Inserta el id del empleado al que quieres agregar: ");
				String playerId = sc.nextLine();
				boolean existingId = clubx.findId(playerId);
				if(existingId){
					boolean alreadyInTeam = clubx.findPlayerOnTeam(playerId, teamName);
					if(alreadyInTeam){
						System.out.print("Este jugador ya pertenece a un equipo. No puede ser agregado a otro");
					}else{
						clubx.addEmployeeToTeam(teamName, playerId);
					}
					
				}else{
					System.out.print("El empleado no se encuentra registrado");
				}
		}else{
			
			System.out.print("El equipo no existe");
		}
		
	}
	
	public static void showEmployees(Club clubx, int numEmployees){
		String message = "";
		for(int i=0; i<numEmployees;i++){
			message = clubx.showEmployee(i);
			System.out.print("\n"+message);
		}
	}
	
	public static void showTeams(Club clubx, int numTeams){
		String message = "";
		for(int i=0; i<numTeams;i++){
			message = clubx.showTeam(i);
			System.out.print("\n"+message+"\n");
		}
	}
	
	public static boolean findId(Club clubx, String id){
		boolean takenId = clubx.findId(id);
		return takenId;
	}
	
	public static boolean findTeam(Club clubx, String teamName){
		boolean takenName = clubx.findTeam(teamName);
		return takenName;
	}
	
	public static boolean findEmployeeOnTeam(Club clubx, String teamName, String playerId){
		boolean alreadyIn = clubx.findEmployeeOnTeam(teamName, playerId);
		return alreadyIn;
	}
	
	public static void addAlignment(Club clubx){
		
		System.out.print("Inserta el nombre del equipo al que deseas agregar una alineacion: ");
		String teamName = sc.nextLine();
		
		boolean existingTeam = clubx.findTeam(teamName);
		if(existingTeam){
			System.out.print("Inserta la fecha de la alineacion: ");
			String dateA = sc.nextLine();
			boolean existingDate = clubx.findAlignment(teamName, dateA);
			
			if(existingDate){
				System.out.print("Esta fecha ya ha sido seleccionada. Escoge otra ");
			}else{
				String tactic="";
				
				System.out.println("Inserta la tactica: "+
				"\n 1. Posesion"+
				"\n 2. Contraataque"+
				"\n 3. Presion alta"+
				"\n 4. Por defecto");
				
				int option;
				option = sc.nextInt(); sc.nextLine();
				
				while(option>4 || option<1){
				System.out.println("Inserta la tactica: "+
				"\n 1. Posesion"+
				"\n 2. Contraataque"+
				"\n 3. Presion alta"+
				"\n 4. Por defecto");
				
				option = sc.nextInt();sc.nextLine();
				}
				
				switch(option){
					case 1:
						tactic="POSESION";
					break;
					case 2:
						tactic="CONTRAATAQUE";
					break;
					case 3:
						tactic="PRESION_ALTA";
					break;
					case 4:
						tactic="POR_DEFECTO";
					break;
				}
				
				clubx.addAlignment(teamName, dateA, tactic);
			}
		}else{
			System.out.print(" El equipo no existe ");
		}
		
	}//end addAlignment
	
	public static void addFormation(Club clubx){
		String message="";
		
		System.out.print("Inserta en nombre del equipo al que quieres agregar una formacion: ");
		String teamName = sc.nextLine();
		
		boolean existingTeam = clubx.findTeam(teamName);
		if(existingTeam){
			System.out.print("Inserta la fecha de la alineacion: ");
			String dateA = sc.nextLine();
			
			boolean existingAlign = clubx.findAlignment(teamName, dateA);
			if(existingAlign){
				System.out.print("Inserta la alineacion de la siguiente forma: n-n-n. Recuerda que la suma de los numeros insertados debe dar 10. ");
				String formationX = sc.nextLine();
				boolean validFormation = clubx.validateFormation(formationX);
				
				if(validFormation){
					clubx.addFormation(teamName, dateA, formationX);
					message = clubx.showFormation(teamName, dateA, formationX);
				}else{
					System.out.print("Formacion invalida ");
				}
			}else{
				System.out.print("No se hallan alineaciones atribuidas a esta fecha");
			}
		}else{
			System.out.print("El equipo no existe");
		}
		System.out.print(message);
	}
	
	public static void showFormation(Club clubx){
		System.out.print("Inserta en nombre del equipo del cual deseas ver una formacion: ");
		String teamName = sc.nextLine();
		
		boolean existingTeam = clubx.findTeam(teamName);
		if(existingTeam){
			System.out.print("Inserta la fecha de la alineacion: ");
			String dateA = sc.nextLine();
			
			boolean existingAlign = clubx.findAlignment(teamName, dateA);
			if(existingAlign){
				System.out.print("Inserta la alineacion de la siguiente forma: n-n-n de la misma forma en la que fue registrada ");
				String formationX = sc.nextLine();
				boolean validFormation = clubx.validateFormation(formationX);
				
				if(validFormation){
					clubx.showFormation(teamName, dateA, formationX);
				}else{
					System.out.print("Formacion invalida ");
				}
			}else{
				System.out.print("No se hallan alineaciones atribuidas a esta fecha");
			}
		}else{
			System.out.print("El equipo no existe");
		}
		
	}
	
	public static void organizePlayers(Club clubx){
		String message="";
		
		System.out.print("Inserta el nombre del equipo para ver como esta organizado su vestidor asignado: ");
		String teamName = sc.nextLine();
		
		boolean existingTeam = clubx.findTeam(teamName);
		if(existingTeam){
			message = clubx.organizePlayers(teamName);
			
		}else{
			System.out.print("El equipo no existe");
		}
		
		System.out.print(message);
	}
	
}