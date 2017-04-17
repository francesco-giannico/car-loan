package presentation.mvp.view;


import utility.Finestra;
import utility.ParametriFXML;
import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application{

	private Presenter presenter;
	private ParametriFXML parameter;
	
	@Override
	public void start(Stage stage) {
		parameter= new ParametriFXML("Login",false,false);
		presenter= new Presenter();
		initRootLayout();
	}
	
	public void initRootLayout(){
			Finestra.visualizzaFinestra(presenter,parameter,null,"MostraLogin",Modality.WINDOW_MODAL);
	}	
	public static void main(String[] args) {
		launch(args);
	}
}
