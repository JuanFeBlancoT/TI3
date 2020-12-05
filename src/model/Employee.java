package model;

public abstract class Employee{
	
	//atributes
	private String name;			//The name of the employee
	private String id;				//The id of the employee
	private int salary;				//The salary of the employee
	private boolean state;			//The state of the employee
	
	/**
	* Employee: Constructor of Employee <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param name Is the name of the employee
	* @param id Is the id of the employee
	* @param salary Is the salary of the employee
	* @param state Is the state of the employee
	*/
	public Employee(String name, String id, int salary, boolean state){
		this.name = name;
		this.id = id;
		this.salary = salary;
		this.state = state;	
	}
	
	/**
	* getId: Gets the id of the employee <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return id Is the id of the employee
	*/
	public String getId(){
		return id;
	}
	
	/**
	* getName: Gets the name of the employee <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return name Is the id of the employee
	*/
	public String getName(){
		return name;
	}
	
	/**
	* getSalary: Gets the salary of the employee <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return salary Is the salary of the employee
	*/
	public int getSalary(){
		return salary;
	}
	
	/**
	* setName: Sets the name of the employee <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param name Is the new name of the employee
	*/
	public void setName(String name){
		this.name = name;
	}
	
	/**
	* setId: Sets the id of the employee <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param id Is the new id of the employee
	*/
	public void setId(String id){
		this.id = id;
	}
	
	/**
	* setSalary: Sets the salary of the employee <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param salary Is the new salary of the employee
	*/
	public void setSalary(int salary){
		this.salary = salary;
	}
	
	/**
	* setState: Sets the state of the employee <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @param state Is the new state of the employee
	*/
	public void setState(boolean state){
		this.state = state;
	}
	
	/**
	* toString: Shows the information of the employee <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return message Is the String with all the information
	*/
	public String toString(){
		String message = ("\n**** Empleado ****"+
		"\n ** Nombre: "+ name+ 
		"\n ** Id: "+ id+
		"\n ** Salario: "+ salary);
		if(state){
			message+="\n ** Estado: Empleado activo";
		}else{
			message+="\n ** Estado: Empleado inactivo";
		}
		
		return message;
	}
}