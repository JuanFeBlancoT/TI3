package ui;

import model.Club;
import java.util.Scanner;

public class Main{
	
	public static Scanner sc =new Scanner(System.in);
	
	public static void main (String[] args){
		
		//atributes
		boolean menu = true; 		//Determines if the menu keeps running
		int numEmployees = 0; 		//The amount of employees created
		int numTeams = 0;			//The amount of teams crated
		
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
			"\n 8. Mostrar organizacion de los entrenadores en oficinas"+
			"\n 9. Mostrar un empleado"+
			"\n 10. Mostrar un equipo"+
			"\n 11. Mostrar empleados"+
			"\n 12. Mostrar equipos"+
			"\n 13. Mostrar formacion"+
			"\n 14. Actualizar equipo"+
			"\n 15. Actualizar empleado"+
			"\n 16. Mostrar todo"+
			"\n 17. Salir");
			
			int option = sc.nextInt(); sc.nextLine();
			
			switch(option){
			case 1:
					contractEmployee(club1);
					numEmployees++;
			break;
			case 2:
					fireEmployee(club1, numTeams);
					numEmployees--;
			break;
			case 3:
					boolean done;
					if(numTeams<2){
						done = createTeam(club1);
						if(done){
							numTeams++;
						}
					}else{
						System.out.println("	** CANT ADD MORE TEAMS. LIMIT REACHED **");
					}
					
			break;
			case 4:
					addPlayerToTeam(club1, numTeams);
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
					organizeCoaches(club1);
			break;
			case 9:
					showEmployee(club1);
			break;
			case 10:
					showTeam(club1);
			break;
			case 11:
					showEmployees(club1, numEmployees);
			break;
			case 12:
					showTeams(club1, numTeams);
			break;
			case 13:
					showFormation(club1);
			break;
			case 14:
					updateTeam(club1);
			break;
			case 15:
					updateEmployee(club1);
			break;
			case 16:
					showAll(club1, numEmployees, numTeams);
			break;
			case 17:
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
	
	/**
	* contractEmployee: It creates an employee that can be one out of 3 types <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param clubx is the Club object
	*/
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
		takenId = clubx.findId(id);
		while(takenId){
			System.out.print("\n **** ID TOMADO ****\n");
			System.out.print("Inserta el id del empleado: ");
			id = sc.nextLine();
			takenId = clubx.findId(id);
		}
		
		System.out.print("Inserta el salario del empleado: ");
		int salary;
		salary = sc.nextInt(); sc.nextLine();
		//checks that the number is positive
		while(salary<0){
			System.out.print("\nNo se deben ingresar numeros negativos en este campo\n ");
			System.out.print("Inserta el salario del empleado: ");
			salary = sc.nextInt(); sc.nextLine();
		}
		
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
				//checks that the number is positive
				while(yearsXp<0){
					System.out.print("\nNo se deben ingresar numeros negativos en este campo\n ");
					System.out.print("Inserta los anios de experiencia del entrenador: ");
					yearsXp = sc.nextInt(); sc.nextLine();
				}
				
				int numberTeams;
				System.out.print("Inserta el numero de equipos que ha tenido a su cargo: ");
				numberTeams = sc.nextInt(); sc.nextLine();
				//checks that the number is positive
				while(numberTeams<0){
					System.out.print("\nNo se deben ingresar numeros negativos en este campo\n ");
					System.out.print("Inserta el numero de equipos que ha tenido a su cargo: ");
					numberTeams = sc.nextInt(); sc.nextLine();
				}
				
				int numChamps;
				System.out.print("Inserta el numero de campeonatos conseguidos: ");
				numChamps = sc.nextInt(); sc.nextLine();
				//checks that the number is positive
				while(numChamps<0){
					System.out.print("\nNo se deben ingresar numeros negativos en este campo\n ");
					System.out.print("Inserta el numero de campeonatos conseguidos: ");
					numChamps = sc.nextInt(); sc.nextLine();
				}
				
				//create an array of Strings based on the number of won championships and fill it
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
				//checks that the number is positive
				while(yearsXp<0){
					System.out.print("\nNo se deben ingresar numeros negativos en este campo\n ");
					System.out.print("Inserta los anios de experiencia del entrenador: ");
					yearsXp = sc.nextInt(); sc.nextLine();
				}
				
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
				//option verification
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
	
	/**
	* fireEmployee: It fires an employee from the employee arraylist and from any team team that has it <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param clubx is the Club object
	* @param numTeams is the number of teams created
	*/
	public static void fireEmployee(Club clubx, int numTeams){
		System.out.print("Inserta el Id del empleado que deseas despedir: ");
		String firedId = sc.nextLine();
		boolean existingId = clubx.findId(firedId);
		if(existingId){
			clubx.fireEmployee(firedId, numTeams);
		}else{
			System.out.print(" ** ESTE ID NO EXISTE **\n");
		}
	}
	
	/**
	* confirmSelection: It determines if a selection is valid from a set of options <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param selection is the current selection, an int
	* @param options An array of options
	* @return choosen It determines wether or not the selection is valid or not
	*/
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
	
	/**
	* createTeam: It creates a team <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param clubx Is the Club object
	* @return done Determines wether or not the team was created or not
	*/
	public static boolean createTeam(Club clubx){
		boolean done = false;
		System.out.print("Inserta el nombre del equipo: ");
		String teamName = sc.nextLine();
		boolean takenTeamName = clubx.findTeam(teamName);
		if(takenTeamName){
			System.out.print(" Taken team name");
			
		}else{
			clubx.createTeam(teamName);
			done=true;
		}
		return done;
		
	}
	
	/**
	* addPlayerToTeam: It creates a team <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param clubx Is the Club object
	* @param numTeams Is the amount of teams created up to that moment
	*/
	public static void addPlayerToTeam(Club clubx, int numTeams){
		
		boolean alreadyInTeam = false;
		System.out.print("Inserta el nombre del equipo: ");
		String teamName = sc.nextLine();
		//Verify that the team exists
		boolean existingTeam = clubx.findTeam(teamName);
		if(existingTeam){
				System.out.print("Inserta el id del empleado al que quieres agregar: ");
				String playerId = sc.nextLine();
				//verify that the id exists
				boolean existingId = clubx.findId(playerId);
				if(existingId){
					//Determines if it is necesary to check the array of employees from other team or not before adding by considering the amount of teams created
					if(numTeams==2){
						alreadyInTeam = clubx.findPlayerOnTeam(playerId, teamName);
					}					
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
	
	/**
	* showEmployees: It displays the information from all the employees <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param clubx Is the Club object
	* @param numEmployees Is the amount of employees created
	*/
	public static void showEmployees(Club clubx, int numEmployees){
		String message = "";
		for(int i=0; i<numEmployees;i++){
			message = clubx.showEmployee(i);
			System.out.print("\n"+message);
		}
	}
	
	/**
	* showTeams: It displays the information of all the teams created <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param clubx Is the Club object
	* @param numTeams Is the amount of teams created
	*/
	public static void showTeams(Club clubx, int numTeams){
		String message = "";
		for(int i=0; i<numTeams;i++){
			message = clubx.showTeam(i);
			System.out.print("\n"+message+"\n");
		}
	}	
	
	/**
	* addAlignment: It adds a new alignment to a team <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param clubx Is the Club object
	*/
	public static void addAlignment(Club clubx){
		
		System.out.print("Inserta el nombre del equipo al que deseas agregar una alineacion: ");
		String teamName = sc.nextLine();
		//verify that the team exists
		boolean existingTeam = clubx.findTeam(teamName);
		if(existingTeam){
			System.out.print("Inserta la fecha de la alineacion: ");
			String dateA = sc.nextLine();
			boolean existingDate = clubx.findAlignment(teamName, dateA);
			//Verify that the date isnt already taken by another alignment
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
				//option verification
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
	
	/**
	* addFormation: It adds a formation to an alignment of a team <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param clubx Is the Club object
	*/
	public static void addFormation(Club clubx){
		String message="";
		
		System.out.print("Inserta en nombre del equipo al que quieres agregar una formacion: ");
		String teamName = sc.nextLine();
		//verifies that the team exists
		boolean existingTeam = clubx.findTeam(teamName);
		if(existingTeam){
			System.out.print("Inserta la fecha de la alineacion: ");
			String dateA = sc.nextLine();
			//verifies that the alignment exists
			boolean existingAlign = clubx.findAlignment(teamName, dateA);
			if(existingAlign){
				System.out.print("Inserta la alineacion de la siguiente forma: n-n-n. Recuerda que la suma de los numeros insertados debe dar 10. ");
				String formationX = sc.nextLine();
				//validates that the string has only 3 parts, that non of them is greater that 7 and that their addition is 10
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
	
	/**
	* showTeams: It displays the information of a single formation <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param clubx Is the Club object
	*/
	public static void showFormation(Club clubx){
		String message="";
		
		System.out.print("Inserta en nombre del equipo del cual deseas ver una formacion: ");
		String teamName = sc.nextLine();
		
		//Validates that the team exists
		boolean existingTeam = clubx.findTeam(teamName);
		if(existingTeam){
			System.out.print("Inserta la fecha de la alineacion: ");
			String dateA = sc.nextLine();
			
			//validates that the alignment exists
			boolean existingAlign = clubx.findAlignment(teamName, dateA);
			if(existingAlign){
				System.out.print("Inserta la alineacion de la siguiente forma: n-n-n de la misma forma en la que fue registrada ");
				String formationX = sc.nextLine();
				
				//validates that the formation is equal to an pre-existing formation
				boolean validFormation = clubx.findFormation(teamName, dateA, formationX);
				
				if(validFormation){
					message = clubx.showFormation(teamName, dateA, formationX);
				}else{
					System.out.print("Formacion no registrada ");
				}
			}else{
				System.out.print("No se hallan alineaciones atribuidas a esta fecha");
			}
		}else{
			System.out.print("El equipo no existe");
		}
		
		System.out.print(message);
	}
	
	/**
	* organizePlayers: It organizes the players of the team selected in their corresponding changeroom and displays it <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param clubx Is the Club object
	*/
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
	
	/**
	* organizeCoaches: It organizes the coaches of the team selected in their corresponding office and displays it  <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param clubx Is the Club object
	*/
	public static void organizeCoaches(Club clubx){
		String message=clubx.organizeCoaches();
		System.out.print(message);
	}
	
	/**
	* showEmployee: It shows the information about a single employee <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param clubx Is the Club object
	*/
	public static void showEmployee(Club clubx){
		String message="";
		System.out.print("Inserta el id del jugador que quieres ver: ");
		String playerId = sc.nextLine();
		
		boolean exist = clubx.findId(playerId);
		if(exist){
			int index = clubx.detectEmployee(playerId);
			message = clubx.showEmployee(index);
		}else{
			System.out.print("El empleado no existe");
		}
		System.out.print(message);
	}
	
	/**
	* showTeam: It displays the information of a single team  <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param clubx Is the Club object
	*/
	public static void showTeam(Club clubx){
		String message="";
		System.out.print("Inserta el nombre del equipo que quieres ver: ");
		String teamName = sc.nextLine();
		boolean exist = clubx.findTeam(teamName);
		
		if(exist){
			int index = clubx.detectTeam(teamName);
			message = clubx.showTeam(index);
		}else{
			System.out.print("El equipo no existe");
		}
		System.out.print(message);
	}
	
	/**
	* updateTeam: It updates the name of a team  <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param clubx Is the Club object
	*/
	public static void updateTeam(Club clubx){
		
		System.out.print("Inserta el nombre del equipo que quieres actualizar: ");
		String teamName = sc.nextLine();
		
		boolean existingTeam = clubx.findTeam(teamName);
		if(existingTeam){
			System.out.print("Inserta el nuevo nombre del equipo: ");
			String newTeamName = sc.nextLine();
			
			boolean exist = clubx.findTeam(newTeamName);
			if(exist){
				System.out.print("Nombre tomado. Elige otro ");
			}else{
				clubx.updateTeam(teamName, newTeamName);
			}
		}else{
			System.out.print("Este equipo no existe");
		}
	}
	
	/**
	* showTeam: It updates the information selected of an employee <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param clubx Is the Club object
	*/
	public static void updateEmployee(Club clubx){
		
		int idType=0;
		System.out.print("Inserta el id del empleado que quieres actualizar: ");
		String id = sc.nextLine();
		
		boolean existingId = clubx.findId(id);
		if(existingId){
			int index = clubx.detectEmployee(id);
			String userInfo = clubx.showEmployee(index);
			System.out.print(userInfo);
			System.out.print("\n");
			
			System.out.println("\nQue deseas cambiar?"+
				"\n ** 1. Nombre"+
				"\n ** 2. Identficador"+
				"\n ** 3. Salario"+
				"\n ** 4. Estado"+
				"\n ** 5. Otro\n");
			int option;
			option = sc.nextInt(); sc.nextLine();
			
			while(option<1 || option>5){
				System.out.println("\nOpcion invalida"+
					"\nQue deseas cambiar?"+
					"\n ** 1. Nombre"+
					"\n ** 2. Identficador"+
					"\n ** 3. Salario"+
					"\n ** 4. Estado"+
					"\n ** 5. Otro\n");
					option = sc.nextInt(); sc.nextLine();
			}
			
			switch(option){
				case 1:
					System.out.print("Inserta el nuevo nombre del empleado: ");
					String newName = sc.nextLine();
					clubx.updateName(id, newName);
				break;
				case 2:
					System.out.print("Inserta el nuevo id del empleado: ");
					String newId = sc.nextLine();
					boolean existingIdentifier = clubx.findId(newId);
					if(existingIdentifier){
						System.out.print("Este id ya esta tomado");
					}else{
						clubx.updateId(id, newId);
					}
				break;
				case 3:
					System.out.print("Inserta el nuevo salario del empleado: ");
					int newSalary = sc.nextInt(); sc.nextLine();
					clubx.updateSalary(id, newSalary);
				break;
				case 4:
					System.out.print("Inserta el nuevo estado del empleado, es activo? si o no: ");
					String newState = sc.nextLine();
					boolean newStateP;
					if(newState.equalsIgnoreCase("si")){
						newStateP = true;
					}else{
						newStateP = false;
					}
					clubx.updateState(id, newStateP);
				break;
				case 5:
				
					idType = clubx.getEmployeeType(id);
					int optionT;
					int yearsXp;
					switch(idType){
						case 1:
							System.out.println("Que deseas cambiar?"+
							"\n ** 1. Anios de experiencia"+
							"\n ** 2. Equipos a su cargo"+
							"\n ** 2. Numero de campeonatos ganados");
							optionT = sc.nextInt(); sc.nextLine();
							
							while(optionT<1 || optionT>3){
								System.out.print("\nOpcion no valida\n");
								System.out.println("Que deseas cambiar?"+
								"\n ** 1. Anios de experiencia"+
								"\n ** 2. Equipos a su cargo"+
								"\n ** 3. Numero de campeonatos ganados");
								optionT = sc.nextInt(); sc.nextLine();
							}
							switch(optionT){
								case 1:
									System.out.print("Inserta el nuevo numero de años de experiencia: ");
									yearsXp = sc.nextInt(); sc.nextLine();
									while(yearsXp<0){
										System.out.print("No se puede agregar un numero negativo");
										System.out.print("Inserta el nuevo numero de años de experiencia: ");
										yearsXp = sc.nextInt(); sc.nextLine();
									}
									clubx.updateYearsXp(id, yearsXp);
								break;
								case 2:
									int newNumTeams;
									System.out.print("Inserta el nuevo numero de equipos que ha tenido a su cargo: ");
									newNumTeams = sc.nextInt(); sc.nextLine();
									while(newNumTeams<0){
										System.out.print("No se puede agregar un numero negativo");
										System.out.print("Inserta el nuevo numero de equipos que ha tenido a su cargo: ");
										newNumTeams = sc.nextInt(); sc.nextLine();
									}
									
									clubx.updateNumTeams(id, newNumTeams);
									
								break;
								case 3:
									int newWonChamps;
									System.out.print("Inserta el nuevo numero de campeonatos ganados: ");
									newWonChamps = sc.nextInt(); sc.nextLine();
									while(newWonChamps<0){
										System.out.print("No se puede agregar un numero negativo");
										System.out.print("Inserta el nuevo numero de campeonatos ganados: ");
										newWonChamps = sc.nextInt(); sc.nextLine();
									}
									String[] newChampNames = new String[newWonChamps];
									for(int i=0;i<newWonChamps;i++){
										System.out.print("Inserta el nombre del "+(i+1)+" campeonato ganado: ");
										newChampNames[i]= sc.nextLine();
									}
									
									clubx.updateWonChamps(id, newChampNames);
								break;
							}
							
						break;
						case 2:
							System.out.println("Que deseas cambiar?"+
							"\n ** 1. Anios de experiencia"+
							"\n ** 2. Ha sido un jugador activo"+
							"\n ** 3. Experticias");
							optionT = sc.nextInt(); sc.nextLine();
							
							while(optionT<1 || optionT>4){
								System.out.print("\nOpcion no valida\n");
								System.out.println("Que deseas cambiar?"+
								"\n ** 1. Anios de experiencia"+
								"\n ** 2. Ha sido un jugador activo"+
								"\n ** 3. Experticias");
								optionT = sc.nextInt(); sc.nextLine();
							}
							
							switch(optionT){
								case 1:
									System.out.print("Inserta el nuevo numero de anios de experiencia: ");
									yearsXp = sc.nextInt(); sc.nextLine();
									while(yearsXp<0){
										System.out.print("No se puede agregar un numero negativo");
										System.out.print("Inserta el nuevo numero de años de experiencia: ");
										yearsXp = sc.nextInt(); sc.nextLine();
									}
									clubx.updateYearsXp(id, yearsXp);
								break;
								case 2:
									System.out.print("Inserta si el asistente fue un jugador activo en el pasado. si o no: ");
									String wasActvie = sc.nextLine();
									boolean newActive;
									if(wasActvie.equalsIgnoreCase("si")){
										newActive = true;
									}else{
										newActive = false;
									}
									
									clubx.updateActive(id, newActive);
								break;
								case 3:
								
									String[] expertises = new String[6];
									int[] numExpertises = new int[6];
									boolean choosen = false;
									
									System.out.print("Tiene alguna experticia? si o no: ");
									String hasExpert = sc.nextLine();
									
									boolean keepAsking =false;
									if(hasExpert.equalsIgnoreCase("si")){
										keepAsking = true;
									}else{
										for(int j=0;j<expertises.length;j++){
											expertises[j] = null;
										}
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
									
									clubx.updateExpertises(id, expertises);
								break;
							}//end internal switch of Technical asistent 
						break;
						case 3:
							System.out.println("Que deseas cambiar?"+
							"\n ** 1. Numero de camiseta"+
							"\n ** 2. Goles marcados"+
							"\n ** 3. Evaluacion promedio"+
							"\n ** 4. Posicion\n");
							optionT = sc.nextInt(); sc.nextLine();
							
							while(optionT<1 || optionT>4){
								System.out.print("\nOpcion no valida\n");
								System.out.println("\nQue deseas cambiar?"+
								"\n ** 1. Numero de camiseta"+
								"\n ** 2. Goles marcados"+
								"\n ** 3. Evaluacion promedio"+
								"\n ** 4. Posicion\n");
							optionT = sc.nextInt(); sc.nextLine();
							}
							
							switch(optionT){
								case 1:
									System.out.print("Inserta el nuevo numero de camiseta del jugador: ");
									String newShirtN = sc.nextLine();
									int numS = Integer.parseInt(newShirtN);
									if(numS>0){
										clubx.updateShirtN(id, newShirtN);
									}else{
										System.out.print("No se puede agregar un numero negativo");
									}
									
								break;
								case 2:
									System.out.print("Inserta el nuevo numero de goles marcados del jugador : ");
									int newGoals = sc.nextInt(); sc.nextLine();
									if(newGoals>0){
										clubx.updateGoals(id, newGoals);
									}else{
										System.out.print("No se puede agregar un numero negativo");
									}
								break;
								case 3:
									System.out.print("Inserta la nueva evaluacion promedio del jugador: ");
									double newEvaAve = sc.nextDouble(); sc.nextLine();
									if(newEvaAve>0){
										clubx.updateEvaAve(id, newEvaAve);
									}else{
										System.out.print("No se puede agregar un numero negativo");
									}
								break;
								case 4:
									System.out.println("\nInserta la nueva posicion del jugador: "+ 
									"\n 1. Delantero"+
									"\n 2. Volante"+
									"\n 3. Defensor"+
									"\n 4. Portero");
									int optionPos;
									optionPos = sc.nextInt(); sc.nextLine();
									String newPosition="";
									
									while(optionPos<1 || optionPos>4){
										System.out.print("\nOpcion no valida\n");
										System.out.println("\nInserta la nueva posicion del jugador: "+ 
										"\n 1. Delantero"+
										"\n 2. Volante"+
										"\n 3. Defensor"+
										"\n 4. Portero");
										optionPos = sc.nextInt(); sc.nextLine();
									}
									switch(optionPos){
										case 1:
											newPosition = "DELANTERO";
										break;
										case 2:
											newPosition = "VOLANTE";
										break;	
										case 3:
											newPosition = "DEFENSOR";
										break;
										case 4:
											newPosition = "PORTERO";
										break;
									}
									
									clubx.updatePosition(id, newPosition);
									
								break;
								
							}
							
						break;
					}
			}
		}else{
			System.out.print("Id no encontrado");
		}
	}//end updateEmployee
	
	/**
	* showAll: Shows the information of the club <br>
	* <b> pre </b> the index must be within the size of the payroll arraylist<br>
	* <b> pos </b> <br>
	* @param clubx The club
	* @param numEmployees current number of employees
	* @param numTeams current number of teams	
	*/
	public static void showAll(Club clubx, int numEmployees, int numTeams){
		String message="";
		
		System.out.println("\n ********** EMPLEADOS **********\n");
		showEmployees(clubx, numEmployees);
		System.out.println("\n ********** EQUIPOS **********\n");
		showTeams(clubx, numTeams);
		System.out.println("\n ********** OFICINAS **********\n");
		organizeCoaches(clubx);
		System.out.println("\n ********** VESTIDORES **********\n");
		if(numTeams==1){
			message = clubx.showChangeroom(0);
			System.out.println(message);
		}else if(numTeams==2){
			message = clubx.showChangeroom(0);
			System.out.println(message);
			System.out.println("\n"+"\n");
			message = clubx.showChangeroom(1);
			System.out.println(message);
		}
	}
	
}