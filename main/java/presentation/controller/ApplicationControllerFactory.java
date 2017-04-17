package presentation.controller;

public class ApplicationControllerFactory {
	private static CarLoanAC carloanAC= null;
	private ApplicationControllerFactory(){
		
	}
	public static ApplicationController getApplicationController(){
		if(carloanAC==null){
			 carloanAC= new CarLoanAC();
		}
		return carloanAC;
	}
}
