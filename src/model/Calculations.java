package model;

public interface Calculations{
	
	/**
	* calculateMarketPrice: Calculates the market price of a certain employee based on specific info about the employee <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return price Is the market price of the employee
	*/
	public double calculateMarketPrice();
	
	/**
	* calculateStarLevel: Calculates the level of a certain employee based on specific info about the employee <br>
	* <b> pre </b> <br>
	* <b> pos </b> <br>
	* @return levelS Is the level of the employee
	*/
	public double calculateStarLevel();
}