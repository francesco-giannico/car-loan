package presentation.mvp.view.controller.generale.gestori;



import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import business.entity.Entity;
import business.entity.Login;
import business.entity.Gestori.SupervisoreAgenzia;
import business.entity.Luoghi.Agenzia;
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

public class Modifica_SupervisoreAgenzia extends Nuovo_SupervisoreAgenzia{
	private SupervisoreAgenzia sup_coinvolto;
	@FXML
	CheckBox assunto;
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		tog=new ToggleGroup();
		radio_f.setToggleGroup(tog);
		radio_m.setToggleGroup(tog);
		radio_m.setSelected(true);
		radio_f.setSelected(false);
		agenzia=table_agenzia.getColumns();
			bindValues();
	}

	@Override
	public void initData(Entity x){
		sup_coinvolto=(SupervisoreAgenzia)x;
		
			initTableAgenzie();
		
		
		nome.setText(sup_coinvolto.getNome());
		cognome.setText(sup_coinvolto.getCognome());
		if(sup_coinvolto.getSesso().equals("Maschio"))
			radio_m.setSelected(true);
		else
			radio_f.setSelected(true);
		datanas.setValue(sup_coinvolto.getDataNascita());
		indirizzo.setText(sup_coinvolto.getIndirizzo());
		codfis.setText(sup_coinvolto.getCodiceFiscale());
		nfisso.setText(sup_coinvolto.getNumFisso());
		ncell.setText(sup_coinvolto.getNumCell());
		assunto.setSelected(sup_coinvolto.isAssunto());
		
		String username1="";
		try {
			 username1=(String) presenter.processRequest("getUsername", this.sup_coinvolto);
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException | CommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		username.setText(username1);
		if(this.sup_coinvolto.isAssunto())
			assunto.setSelected(true);
		
	}

	protected void initTableAgenzie(){
		super.initTableAgenzie();
		try {
			Agenzia a=(Agenzia)presenter.processRequest("leggiAgenzia", this.sup_coinvolto.getIDAgenzia());
			this.table_agenzia.getSelectionModel().select(a);
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException | CommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	@FXML
	public void btnconferma(ActionEvent e){
		SchermataGenerale<SupervisoreAgenzia> schermataGenerale = (SchermataGenerale<SupervisoreAgenzia>)this.getChiamante();
		tw= ((SchermataGenerale<SupervisoreAgenzia>)schermataGenerale).getTable("SupervisoreAgenzia");
		try {
			SupervisoreAgenzia a=prendiDatiDaView();
			Login login=prendiDatiPerLogIn();
			
			String current_username=(String) presenter.processRequest("getUsername", this.sup_coinvolto);
			if(!current_username.equals(login.getUsername()))//Verifico se l'username non è stato scelto già
			presenter.processRequest("VerificaCredenziali",login);//Verifico se l'username non è stato scelto già
			
			
			presenter.processRequest("ModificaSupervisoreAgenzia", a);
			a=(SupervisoreAgenzia) presenter.processRequest("leggiSupervisoreAgenziaByCodiceFiscale", a.getCodiceFiscale());
			login.setSupA(String.valueOf(a.getIdUtente()));
			presenter.processRequest("ModificaCredenziali", login);
			/*Aggiorna la tabella nella schermata generale*/
			schermataGenerale.caricaTabella((List<SupervisoreAgenzia>)presenter.processRequest("getAllSupervisoriAgenzia",null), tw);
				
			/**/
			
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
		return new Login(user_name, passw);
	}
	protected SupervisoreAgenzia prendiDatiDaView() throws CommonException {
		SupervisoreAgenzia a=new SupervisoreAgenzia();
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
			a.setIDAgenzia(table_agenzia.getSelectionModel().getSelectedItem().getIDAgenzia());
		
		if(assunto.isSelected())
			a.setAssunto(true);
		else
			a.setAssunto(false);
		a.setIdUtente(sup_coinvolto.getIdUtente());
		return  a;
	}
	@FXML
	public void btnannulla(ActionEvent e){
		Optional<ButtonType> result= AlertView.getAlertView("Sicuro di voler uscire?" + "\n" + "Perderai tutti i dati inseriti ",AlertType.CONFIRMATION);
		if(result.isPresent() && result.get() == ButtonType.OK)
			this.chiudiFinestra();
	}
}
