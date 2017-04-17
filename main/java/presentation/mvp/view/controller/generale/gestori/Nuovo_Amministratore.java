package presentation.mvp.view.controller.generale.gestori;


import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import business.entity.Login;
import business.entity.UtenteCorrente;
import business.entity.Gestori.Amministratore;
import business.model.Exception.CommonException;
import MessaggiFinestra.AlertView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.Schermata;
import presentation.mvp.view.controller.generale.SchermataGenerale;

public class Nuovo_Amministratore extends Schermata{
	@FXML
	protected TextField nome;
	@FXML
	protected TextField cognome;
	@FXML
	protected TextField indirizzo;
	@FXML
	protected TextField codfis;
	@FXML
	protected TextField nfisso;
	@FXML
	protected TextField ncell;
	@FXML
	protected TextField username;
	@FXML
	protected PasswordField password;
	@FXML
	protected RadioButton radio_m;
	@FXML
	protected RadioButton radio_f;
	@FXML
	protected DatePicker datanas;
	protected ToggleGroup tog;
	protected TableView<Amministratore> tw;
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		tog=new ToggleGroup();
		radio_f.setToggleGroup(tog);
		radio_m.setToggleGroup(tog);
		radio_m.setSelected(true);
		radio_f.setSelected(false);
		
	}
	@SuppressWarnings("unchecked")
	@FXML
	public void btnconferma(ActionEvent e){
		SchermataGenerale<Amministratore> schermataGenerale = (SchermataGenerale<Amministratore>)this.getChiamante();
		tw= ((SchermataGenerale<Amministratore>)schermataGenerale).getTable("Amministratore");
		try {
			Amministratore a=prendiDatiDaView();
			Login login=prendiDatiPerLogIn();
			//Verifico se l'username non è stato scelto già
			presenter.processRequest("VerificaCredenziali",login);
			presenter.processRequest("InserisciAmministratore", a);
			a=(Amministratore) presenter.processRequest("leggiAmministratoreByCodiceFiscale", a.getCodiceFiscale());
			login.setAmministratore(String.valueOf(a.getIdUtente()));
			presenter.processRequest("InserisciCredenziali", login);
			schermataGenerale.caricaTabella((List<Amministratore>)presenter.processRequest("getAllAmministratori", null), tw);
			chiudiFinestra();
			
			}
				catch (CommonException e1) {
					// TODO Auto-generated catch block
					e1.showMessage();
				}
		catch(InvocationTargetException e1){
			new CommonException(e1.getTargetException().getMessage()).showMessage();
		}
			 catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | NoSuchMethodException
					| SecurityException | IllegalArgumentException
				 e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} 
	
	private Login prendiDatiPerLogIn() throws CommonException {
		String user_name=username.getText();
		if(user_name==null || user_name.isEmpty() || user_name.length()<4)
			throw new CommonException("Username non valido");
		String passw=password.getText();
		if(passw==null || passw.isEmpty() || passw.length()<4)
			throw new CommonException("Password non valida");
			
		return new Login(user_name, passw);
	}
	protected Amministratore prendiDatiDaView() throws CommonException {
		Amministratore a=new Amministratore();
		String n=nome.getText();
		if(n==null || n.isEmpty())
			throw new CommonException("Il nome è vuoto");
		a.setNome(n);
		n=cognome.getText();
		if(n==null || n.isEmpty())
			throw new CommonException("Il cognome è vuoto");
		a.setCognome(n);
		if(radio_m.isSelected())
			a.setSesso("Maschio");
		else
			a.setSesso("Femmina");
		LocalDate datanasc=datanas.getValue();
		if(datanasc==null)
			throw new CommonException("Data nascita vuota");
		if(datanasc.isAfter(LocalDate.now()))
			throw new CommonException("Data nascita nel futuro");
		a.setDataNascita(datanasc);
		n=indirizzo.getText();
		if(n==null)
			n="";
		a.setIndirizzo(n);
		n=codfis.getText();
		if(n==null || n.isEmpty())
			throw new CommonException("Codice fiscale vuoto");
		if(n.length()!=16)
			throw new CommonException("Codice fiscale non valido(deve essere di 16 caratteri)");
		a.setCodiceFiscale(n);
		n=nfisso.getText();
		if(n==null)
			n="";
		a.setNumFisso(n);
		n=ncell.getText();
		if(n==null)
			n="";
			a.setNumCell(n);
		Amministratore current_admin=(Amministratore)UtenteCorrente.getUtente();
		a.setIDDitta(current_admin.getIDDitta());//Il nuovo amministratore avrà lo stesso idditta dell'amministratore che lo sta aggiungendo
		
		return  a;
	}
	@FXML
	public void btnannulla(ActionEvent e){
		Optional<ButtonType> result= AlertView.getAlertView("Sicuro di voler uscire?" + "\n" + "Perderai tutti i dati inseriti ",AlertType.CONFIRMATION);
		if(result.isPresent() && result.get() == ButtonType.OK)
			this.chiudiFinestra();
	}
}
