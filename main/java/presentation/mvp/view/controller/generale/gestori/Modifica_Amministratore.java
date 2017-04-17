package presentation.mvp.view.controller.generale.gestori;



import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import business.entity.Entity;
import business.entity.Login;
import business.entity.UtenteCorrente;
import business.entity.Gestori.Amministratore;
import business.model.Exception.CommonException;
import MessaggiFinestra.AlertView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.generale.SchermataGenerale;

public class Modifica_Amministratore extends Nuovo_Amministratore{
	private Amministratore amministratore_coinvolto;
	@FXML
	CheckBox assunto;
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		tog=new ToggleGroup();
		radio_f.setToggleGroup(tog);
		radio_m.setToggleGroup(tog);
		radio_m.setSelected(true);
		radio_f.setSelected(false);
	}
	
	
public void initData(Entity x){
		this.amministratore_coinvolto=(Amministratore)x;
		nome.setText(amministratore_coinvolto.getNome());
		cognome.setText(amministratore_coinvolto.getCognome());
		String sesso=amministratore_coinvolto.getSesso();
		if(sesso.equals("Maschio"))
			radio_m.setSelected(true);
		else
			radio_f.setSelected(true);
		LocalDate da=amministratore_coinvolto.getDataNascita();
		datanas.setValue(da);
		indirizzo.setText(amministratore_coinvolto.getIndirizzo());
		codfis.setText(amministratore_coinvolto.getCodiceFiscale());
		nfisso.setText(amministratore_coinvolto.getNumFisso());
		ncell.setText(amministratore_coinvolto.getNumCell());
		String username1="";
		try {
			 username1=(String) presenter.processRequest("getUsername", this.amministratore_coinvolto);
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException | CommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		username.setText(username1);
		if(this.amministratore_coinvolto.isAssunto())
			assunto.setSelected(true);
	}
	@FXML
	public void btnconferma(ActionEvent e){
		SchermataGenerale<Amministratore> schermataGenerale = (SchermataGenerale<Amministratore>)this.getChiamante();
		tw= ((SchermataGenerale<Amministratore>)schermataGenerale).getTable("Amministratore");
		try {
			Amministratore a=prendiDatiDaView();
			Login login=prendiDatiPerLogIn();
			
			String current_username=(String) presenter.processRequest("getUsername", this.amministratore_coinvolto);
			
			if(!current_username.equals(login.getUsername()))
			presenter.processRequest("VerificaCredenziali",login);//Verifico se l'username non è stato scelto già
			
			presenter.processRequest("ModificaAmministratore", a);
			login.setAmministratore(String.valueOf(a.getIdUtente()));
			presenter.processRequest("ModificaCredenziali", login);
			schermataGenerale.caricaTabella((List<Amministratore>)presenter.processRequest("getAllAmministratori", null), tw);
			chiudiFinestra();
			
			}
				catch (CommonException e1) {
					// TODO Auto-generated catch block
					e1.showMessage();
				}
			 catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | NoSuchMethodException
					| SecurityException | IllegalArgumentException
					| InvocationTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} 
	private Login prendiDatiPerLogIn() throws CommonException {
		String user_name=username.getText();
		if(user_name==null || user_name.isEmpty() || user_name.length()<4)
			throw new CommonException("Username non valido");
		String passw=password.getText();
		
		return new Login(user_name, passw);
	}
	@FXML
	public void btnannulla(ActionEvent e){
		Optional<ButtonType> result= AlertView.getAlertView("Sicuro di voler uscire?" + "\n" + "Perderai tutti i dati inseriti ",AlertType.CONFIRMATION);
		if(result.isPresent() && result.get() == ButtonType.OK)
			this.chiudiFinestra();
	}
	
	
	protected Amministratore prendiDatiDaView() throws CommonException{
		Amministratore a=super.prendiDatiDaView();
		if(assunto.isSelected())
			a.setAssunto(true);
		else
			a.setAssunto(false);
		return a;
	}
	
}
