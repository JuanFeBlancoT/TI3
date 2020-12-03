package model;

public abstract class Employee{
	
	//atributes
	private String name;
	private String id;
	private int salary;
	private boolean state;
	
	public Employee(String name, String id, int salary, boolean state){
		this.name = name;
		this.id = id;
		this.salary = salary;
		this. state = state;
	}
	
	public String getId(){
		return id;
	}
	
	public String toString(){
		String message = ("\n**** Empleado ****"+
		"\n ** Nombre: "+ name+ 
		"\n ** Id: "+ id+
		"\n ** Salario: "+ salary+
		"\n ** Stado: "+ state);
		
		return message;
	}
	
	public String getName(){
		return name;
	}
}