package business.model.Exception;

import MessaggiFinestra.AlertView;
import javafx.scene.control.Alert.AlertType;


@SuppressWarnings("serial")
public class CommonException extends Exception{
	private final String message;
	public CommonException(String message){
		this.message= message;
	}
	@Override
	public String getMessage(){
		return message;
	}
   public void showMessage(){
	   AlertView.getAlertView(message,AlertType.ERROR);
   }
   
   
}
