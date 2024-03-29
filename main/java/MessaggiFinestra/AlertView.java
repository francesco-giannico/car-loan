package MessaggiFinestra;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * <p>Modella la generica finestra popUp che mostra il  messaggio di errore</p>
 */
public class AlertView {
	/**
	 * <p>Mostra finestra pop-up</p>
	 * @param message Messaggio da visualizzare nella finestra
	 * @param type Tipo di alert da visualizzare
	 */
	public static Optional<ButtonType> getAlertView(String message,Alert.AlertType type){
		Alert alert = new Alert(type);
		alert.setTitle("Notifica");
		alert.setContentText(message);
		return alert.showAndWait();
	}
	private AlertView(){
		
	}
}
