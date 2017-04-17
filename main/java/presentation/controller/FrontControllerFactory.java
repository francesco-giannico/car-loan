package presentation.controller;

public class FrontControllerFactory {
	private static CarLoanFC carloanFC= null;
   
	private FrontControllerFactory() {
    }

    public static FrontController getFrontController() {
    	if(carloanFC==null){
   		 carloanFC= new CarLoanFC();
	   	}
	   	return carloanFC;
    }
}
