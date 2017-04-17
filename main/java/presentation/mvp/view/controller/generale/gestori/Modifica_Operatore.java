package presentation.mvp.view.controller.generale.gestori;


import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import business.entity.Entity;
import business.entity.Login;
import business.entity.UtenteCorrente;
import business.entity.Gestori.Amministratore;
import business.entity.Gestori.Operatore;
import business.entity.Gestori.SupervisoreAgenzia;
import business.entity.Gestori.SupervisoreSede;
import business.entity.Luoghi.Sede;
import business.model.Exception.CommonException;
import MessaggiFinestra.AlertView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.generale.SchermataGenerale;

public class Modifica_Operatore extends Nuovo_Operatore{
	private Operatore op_coinvolto;
	@FXML
	private CheckBox assunto;
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		tog=new ToggleGroup();
		radio_f.setToggleGroup(tog);
		radio_m.setToggleGroup(tog);
		radio_m.setSelected(true);
		radio_f.setSelected(false);
		sede=tb_sedi.getColumns();
		this.u=UtenteCorrente.getUtente();
		if(u instanceof Amministratore){
			informazioni.setText("Di seguito sono riportate tutte le sedi di tutte le agenzie");
			
		}
		else if(u instanceof SupervisoreAgenzia){
			informazioni.setText("Di seguito sono riportate tutte le sedi sotto la sua agenzia");
			
		}
		else{
			informazioni.setText("L'operatore sarà aggiunto come supervisore della sua sede");
			tb_sedi.setVisible(false);
			labelsedi.setVisible(false);
		}
		
	}
	@Override
	public void initData(Entity x){
		op_coinvolto=(Operatore)x;
		nome.setText(op_coinvolto.getNome());
		cognome.setText(op_coinvolto.getCognome());
		if(op_coinvolto.getSesso().equals("Maschio"))
			radio_m.setSelected(true);
		else
			radio_f.setSelected(true);
		datanas.setValue(op_coinvolto.getDataNascita());
		indirizzo.setText(op_coinvolto.getIndirizzo());
		codfis.setText(op_coinvolto.getCodiceFiscale());
		nfisso.setText(op_coinvolto.getNumFisso());
		ncell.setText(op_coinvolto.getNumCell());
		String username1="";
		try {
			 username1=(String) presenter.processRequest("getUsername", this.op_coinvolto);
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException | CommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		username.setText(username1);
		if(this.op_coinvolto.isAssunto())
			assunto.setSelected(true);
		
		if(u instanceof Amministratore || u instanceof SupervisoreAgenzia)
			initTableSedi();
	}
	@Override
	protected void initTableSedi(){
		bindValues();
		List<Sede> list=DownloadSedi();
		ObservableList<Sede> obsList= FXCollections.observableList(list);
		tb_sedi.setItems(obsList);
		try {
			Sede s=(Sede)presenter.processRequest("leggiSede", op_coinvolto.getIDSede());
			tb_sedi.getSelectionModel().select(s);
			
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
		SchermataGenerale<Operatore> schermataGenerale = (SchermataGenerale<Operatore>)this.getChiamante();
		tw= ((SchermataGenerale<Operatore>)schermataGenerale).getTable("Operatore");
		try {
			Operatore a=prendiDatiDaView();
			Login login=prendiDatiPerLogIn();
			
			String current_username=(String) presenter.processRequest("getUsername", this.op_coinvolto);
			if(!current_username.equals(login.getUsername()))//Verifico se l'username non è stato scelto già
			presenter.processRequest("VerificaCredenziali",login);
			
			
			presenter.processRequest("ModificaOperatore", a);
			login.setOperatore(String.valueOf(a.getIdUtente()));
			presenter.processRequest("ModificaCredenziali", login);
			/*Aggiorna la tabella nella schermata generale*/
			if(u instanceof Amministratore)
			schermataGenerale.caricaTabella((List<Operatore>)presenter.processRequest("getAllOperatori",null), tw);
			else if (u instanceof SupervisoreAgenzia){
				List<Sede> ls=(List<Sede>)presenter.processRequest("getAllSediByAgenzia", ((SupervisoreAgenzia)u).getIDAgenzia());
				List<Operatore> l=new LinkedList<Operatore>();
				for(Sede s:ls)
					l.addAll((Collection<? extends Operatore>) presenter.processRequest("getAllOperatoriBySede",s.getIDSede()));
				schermataGenerale.caricaTabella(l, tw);
			}
			else // SupervisoreSede
			schermataGenerale.caricaTabella((List<Operatore>) presenter.processRequest("getAllOperatoriBySede",((SupervisoreSede)u).getIDSede()), tw);
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
	
	@FXML
	public void btnannulla(ActionEvent e){
		Optional<ButtonType> result= AlertView.getAlertView("Sicuro di voler uscire?" + "\n" + "Perderai tutti i dati inseriti ",AlertType.CONFIRMATION);
		if(result.isPresent() && result.get() == ButtonType.OK)
			this.chiudiFinestra();
	}
	
	protected Operatore prendiDatiDaView() throws CommonException {
		Operatore a=new Operatore();
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
		if(u instanceof Amministratore || u instanceof SupervisoreAgenzia)
			a.setIDSede(tb_sedi.getSelectionModel().getSelectedItem().getIDSede());
		else
			a.setIDSede(((SupervisoreSede)u).getIDSede());
		if(assunto.isSelected())
			a.setAssunto(true);
		else
			a.setAssunto(false);
		
		a.setIdUtente(op_coinvolto.getIdUtente());
		return  a;
	}
}
