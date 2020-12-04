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
		this.state = state;	
	}
	
	public String getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public int getSalary(){
		return salary;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public void setSalary(int salary){
		this.salary = salary;
	}
	
	public void setState(boolean state){
		this.state = state;
	}
	
	public String toString(){
		String message = ("\n**** Empleado ****"+
		"\n ** Nombre: "+ name+ 
		"\n ** Id: "+ id+
		"\n ** Salario: "+ salary+
		"\n ** Stado: "+ state);
		
		return message;
	}
}