package presentation.mvp.view.controller.generale.noleggio;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import presentation.mvp.view.controller.generale.SchermataGenerale;
import utility.Finestra;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import MessaggiFinestra.AlertView;
import business.entity.Cliente;
import business.entity.Entity;
import business.entity.Auto.Autoveicolo;
import business.entity.Auto.Disponibilita;
import business.entity.Luoghi.Sede;
import business.entity.Noleggio.Contratto;
import business.entity.Noleggio.Noleggio;
import business.entity.Noleggio.StatoNoleggio;
import business.entity.Noleggio.Optional.ChilometraggioIllimitato;
import business.entity.Noleggio.Optional.Guidatore;
import business.entity.Noleggio.Optional.GuidatoreAggiuntivo;
import business.entity.Noleggio.Optional.Optional;
import business.entity.Noleggio.Optional.Seggiolino;
import business.entity.pagamento.CartaDiCredito;
import business.entity.pagamento.Contanti;
import business.entity.pagamento.Pagamento;
import business.model.Exception.CommonException;

public class NuovoNoleggio extends ImpostaNoleggio<Entity>{
	private Noleggio noleggio=new Noleggio();
	
	@FXML
	public void btnAggiungiOptionalNoleggio(ActionEvent e){
		if(tbOptionalNoleggio.getSelectionModel().getSelectedIndex()<0 ){
			AlertView.getAlertView("Nessun elemento selezionato", AlertType.WARNING);
		}
		else{
			ObservableList<Entity> listItem= tbOptionalScelti.getItems();
			Optional itemSelected = (Optional) tbOptionalNoleggio.getSelectionModel().getSelectedItem();
			int numScelto = choiceGuidatori.getSelectionModel().getSelectedItem();
			if(itemSelected instanceof GuidatoreAggiuntivo  && !listItem.contains(itemSelected)){
				
				for(int i=0;i<guidatoriAggiuntivi.size();i++){
					if(guidatoriAggiuntivi.get(i).getNumero_guidatori()==numScelto){
						guidatore= guidatoriAggiuntivi.get(i);
						listItem.add(guidatore);//metto nell'altra tabella quello con l'elemento scelto.
						break;
					}
				 }
				controllaGuidatoreAggiuntivo();
			}
			else if(!listItem.contains(itemSelected)){
				tbOptionalScelti.getItems().add(itemSelected);
				controllaChilLimitato();
				}
			else 
				AlertView.getAlertView("Hai già aggiunto quest'optional", AlertType.WARNING);
		}
	}
	@FXML
	public void btnAggiungiOptionalAuto(ActionEvent e){
		if(tbOptionalAuto.getSelectionModel().getSelectedIndex()<0 ){
			AlertView.getAlertView("Nessun elemento selezionato", AlertType.WARNING);
		}
		else{
			ObservableList<Entity> listItem= tbOptionalScelti.getItems();
			Optional itemSelected = (Optional) tbOptionalAuto.getSelectionModel().getSelectedItem();
			int numScelto = choiceSeggiolini.getSelectionModel().getSelectedItem();
			if(itemSelected instanceof Seggiolino  && !listItem.contains(itemSelected)){
				for(int i=0;i<seggiolini.size();i++){
					if(seggiolini.get(i).getnumero()==numScelto){
						listItem.add(seggiolini.get(i));//metto nell'altra tabella quello con l'elemento scelto.
						break;
					}
				 }
			}
			else if(!listItem.contains(itemSelected)){
				listItem.add(itemSelected);
			}
			else 
				AlertView.getAlertView("Hai già aggiunto quest'optional", AlertType.WARNING);
		}
	}
	/**
	 * <p>Setta ad abilitato i valori di guidatore aggiuntivo</p>
	 */
	private void impostaTrueGuidatoreAggiuntivo(){
		txtNome.setDisable(false);
		txtCognome.setDisable(false);
		txtIndirizzo.setDisable(false);
		txtCodFiscale.setDisable(false);
		txtPatente.setDisable(false);
		btnRimuovi.setVisible(true);
		tbGuidatori.setVisible(true);
		if(tbGuidatori.getItems()!=null)
			tbGuidatori.getItems().clear();
		campiDisattivi=false;
		//guidatore=null;
	}
	
	/**
	 * <p>Setta ad abilitato i valori di guidatore aggiuntivo</p>
	 */
	private void controllaGuidatoreAggiuntivo(){
		ObservableList<Entity> listItem=  tbOptionalScelti.getItems();
		for(Entity e: listItem){
			if((Optional)e instanceof GuidatoreAggiuntivo)
			{
				impostaTrueGuidatoreAggiuntivo();
				return;
			}
		}
		impostaFalsoTxtGuidatore();
	}
	/**
	 * <p>Disabilita la scelta del limite di copertura</p>
	 */
	private void controllaChilLimitato(){
		ObservableList<Entity> listItem=  tbOptionalScelti.getItems();
		for(Entity e: listItem){
			if((Optional)e instanceof ChilometraggioIllimitato)
			{
				choiceLimite.setDisable(true);
				lblCostoKm.setDisable(true);
				return;
			}
		}
		choiceLimite.setDisable(false);
		lblCostoKm.setDisable(false);
	}
	
	private Set<Guidatore> guidatori= new HashSet<Guidatore>();//elimino i duplicati 
	ObservableList<Entity> listItem = FXCollections.observableArrayList(guidatori);
	@FXML
	public void btnAggiungiGuidatore(ActionEvent e){
		try{
			//se i campi del giudatore non sono stati disattivati
		 if(!campiDisattivi){
			 caricaTabella( listItem,tbGuidatori);
			 //se la tabella non contiene già tutti gli elementi massimi da inserire in base a quello scelto
			 
			 if(tbGuidatori.getItems()!=null || (tbGuidatori.getItems().size()<guidatore.getNumero_guidatori())){
				if(txtNome.getText().isEmpty() || txtCognome.getText().isEmpty() || txtIndirizzo.getText().isEmpty() || 
						txtCodFiscale.getText().isEmpty() || txtPatente.getText().isEmpty())
					throw new CommonException("Compilare tutti i campi prima di procedere con l'aggiunta");	
				else{
				   try{
			        	Integer.parseInt(txtPatente.getText());
			        	Guidatore guidatore = new Guidatore(txtNome.getText(),txtCognome.getText(),txtIndirizzo.getText(),
			        								txtCodFiscale.getText(),txtPatente.getText(),ottieniIDNoleggio());
						try {
							presenter.processRequest("VerificaGuidatore", guidatore);
							//se l'elemento viene aggiunto, in quanto è un hashset, se trova un duplicato su codice fiscaale non l'aggiungerà
							if(guidatori.add(guidatore)) {
								listItem= FXCollections.observableArrayList(guidatori);
								tbGuidatori.setItems(listItem);
							}
							else {
								throw new CommonException("C'è già qualche guidatore con Patente e/o cod.Fiscale uguale a quelle inserite");
							}
						}	
						catch(CommonException e1){
							e1.showMessage();
						}
						catch(InvocationTargetException e1){
							new CommonException(e1.getTargetException().getMessage()).showMessage();
						}
						catch (InstantiationException | IllegalAccessException
								| ClassNotFoundException | NoSuchMethodException
								| SecurityException | IllegalArgumentException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					catch(SecurityException | IllegalArgumentException e2){
						throw new CommonException("La patente deve essere costituita da soli numeri");					
					}
					}
			 }
			 else 
				 throw new CommonException("Hai già impostato tutti i guidatori");
		}
		else 
			throw new CommonException("Se vuoi aggiungere un guidatore seleziona e aggiungi come optional il guidatore Aggiuntivo");
		}
		catch(CommonException e1){
			e1.showMessage();}
	}
	
	private int ottieniIDNoleggio() {
		try {
			return ((int)presenter.processRequest("numNoleggi", null))+1;
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException | CommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	@FXML
	public void	btnRimuoviGuidatore(ActionEvent e){
		if(tbGuidatori.getSelectionModel().getSelectedIndex()<0 ){
			AlertView.getAlertView("Nessun elemento selezionato", AlertType.WARNING);
		}
		else{
			guidatori.remove(tbGuidatori.getSelectionModel().getSelectedItem());
			tbGuidatori.getItems().remove(tbGuidatori.getSelectionModel().getSelectedItem());
			controllaGuidatoreAggiuntivo();
			}
	}

	@FXML
	public void btnRimuoviOptional(ActionEvent e){
		if(tbOptionalScelti.getSelectionModel().getSelectedIndex()<0){
			AlertView.getAlertView("Nessun elemento selezionato", AlertType.WARNING);
		}
		else{
				tbOptionalScelti.getItems().remove(tbOptionalScelti.getSelectionModel().getSelectedItem());
				controllaChilLimitato();
				controllaGuidatoreAggiuntivo();
			}
	}
	
	@FXML
	public void btnAggiungiCartaCredito(ActionEvent e){
		if(cliente==null){
			AlertView.getAlertView("Selezionare prima un contratto", AlertType.WARNING);
		}
		else {	
			FXMLParameter.setRidimensionabile(false);
			FXMLParameter.setTitolo("Nuova Carta Di Credito");
			FXMLParameter.setHand(false);
			Finestra.visualizzaFinestra(presenter, FXMLParameter, this, "MostraSchermataInserimentoCartaCredito",Modality.APPLICATION_MODAL);
		 }
		}
	
	
	public Cliente getEntitaElementoSelezionato(){
		return cliente;
	}
	@FXML
	public void btnCancella(ActionEvent e){
	 java.util.Optional<ButtonType> result= AlertView.getAlertView("Sicuro di voler uscire?" + "\n" + "Perderai tutti i dati inseriti ",AlertType.CONFIRMATION);
			if(result.isPresent() && result.get() == ButtonType.OK)
				this.chiudiFinestra();
	}

	
	@FXML
	public void dRitiroAction(ActionEvent e){
		final int MASSIMO_INIZIO= 3;
		try {
			LocalDate lDate= dRitiro.getValue();
			if(lDate!=null){
				if(lDate.isBefore(LocalDate.now())){
					impostaDate(LocalDate.now());
				}
				else if (lDate.isAfter(LocalDate.now().plusDays(MASSIMO_INIZIO))){
					impostaDate(LocalDate.now().plusDays(MASSIMO_INIZIO));		
				}
				else 
					impostaDate(lDate);
			
			}
			else 
				throw new CommonException("Data di Ritiro non impostata");
		} catch (CommonException e1) {
			e1.showMessage();
		}
	}
	private float oldPrezzo=0;
	@FXML
	public void btnCalcolaPrezzo(ActionEvent e){
		try {
			oldPrezzo= calcolaPrezzo();
		} catch (CommonException e1) {
			// TODO Auto-generated catch block
			e1.showMessage();
		}
		
	}
	float acconto = 0;
	float costoChilometrico=0;
	float cauzione=0;
	private float calcolaPrezzo() throws CommonException{
		 acconto = 0;
		 costoChilometrico=0;
		cauzione=0;
		//somma tutti i valori : 
	
		//sommo il costo degli optional
		ObservableList<Entity> optional= tbOptionalScelti.getItems();
		for(Entity op: optional){
			acconto+=((Optional)op).getPrezzo();
		}
		//costo auto + costo della fascia.
		if(tbAutoveicolo.getSelectionModel().getSelectedItem()!=null)
			acconto+= ((Autoveicolo)tbAutoveicolo.getSelectionModel().getSelectedItem()).getPrezzo()+choiceFascia.getSelectionModel().getSelectedItem().getPrezzo();
		else
			throw new CommonException("Scegliere almeno un autoveicolo");
		//moltiplico il costo al kilometro della fascia per il limite di chilometri.
		if(!choiceLimite.isDisable())
			costoChilometrico= choiceFascia.getSelectionModel().getSelectedItem().getCosto_kilometrico() * choiceLimite.getSelectionModel().getSelectedItem();
		
		
		//costo di solo le settimane
		int numeroSettimane= choiceSettimane.getSelectionModel().getSelectedItem();
		float costoOriginaleInGiorni= acconto*(7*numeroSettimane);
		float scontoSettimanale = (costoOriginaleInGiorni*10)/100;
		float costoPerSettimane= costoOriginaleInGiorni-scontoSettimanale;
		
		//+ il costo dei giorni se scelti.
		int numeroGiorni= choiceGiorni.getSelectionModel().getSelectedItem();
		float costoPerGiorni = acconto*numeroGiorni;
		
		acconto= costoPerSettimane+ costoPerGiorni+costoChilometrico;
		
		txtAcconto.setText(String.valueOf(acconto));
		//la cauzione è il 20% del totale 
		cauzione= (acconto*30)/100;
		txtCauzione.setText(String.valueOf(cauzione));
		
		
		txtTotale.setText(String.valueOf(acconto+cauzione));
	
		return acconto+cauzione;
	}
	private Pagamento pagamento ;
	ObservableList<Entity> listItemGuidatori=null;
	@FXML
	public void btnConferma(ActionEvent e){
		float nuovoPrezzo;

		try {
			nuovoPrezzo = calcolaPrezzo();
			if(nuovoPrezzo==0 || oldPrezzo!=nuovoPrezzo){
					oldPrezzo=nuovoPrezzo;
					throw new CommonException("E' stato ricalcolato il prezzo in quanto sono state apportate modifiche al noleggio, cliccare su conferma per procedere");
				
			}	
			else {
				if(tbRestituzione.getSelectionModel().getSelectedItem()==null){
		    		throw new CommonException ("Selezionare almeno una sede per la restituzione!");
		    	}

		    	if(tbContratto.getSelectionModel().getSelectedItem()==null){
		    		throw new CommonException ("Selezionare almeno un contratto di riferimento!");
		    	}
		    	if(nuovoPrezzo==0){
		    		throw new CommonException ("E' stato ricalcolato il prezzo in quanto non si è premuto il pulsante calcola");
		    	}
		    	listItemGuidatori= tbGuidatori.getItems();
		    	if(guidatore!=null){
		    		if(listItemGuidatori.size()==guidatore.getNumero_guidatori()){
				    	//PAGAMENTO
				    	aggiungiPagamento();
						//Noleggio
				    	aggiungiNoleggio();
				    	//AUTO set disponibilità 
				    	modificaDisponibilitaAuto();
				    	//GUIDATORI
				    	if(tbGuidatori.isVisible()){
				    		aggiungiGuidatori();
				    	}
						AlertView.getAlertView("Noleggio inserito con successo",AlertType.INFORMATION);
						chiudiFinestra();
						}
					else{
						throw new CommonException("Devi inserire in tutto " + guidatore.getNumero_guidatori() + " Guidatore/i");
					}
		    	}
		    	else{
		    		//PAGAMENTO
			    	aggiungiPagamento();
					//Noleggio
			    	aggiungiNoleggio();
			    	//AUTO set disponibilità 
			    	modificaDisponibilitaAuto();
			    	AlertView.getAlertView("Noleggio inserito con successo",AlertType.INFORMATION);
					chiudiFinestra();
		    	}
		    	}
		} catch (CommonException e1) {
			e1.showMessage();
		}
			
	}
	
	private void modificaDisponibilitaAuto(){
		Autoveicolo auto= ((Autoveicolo)tbAutoveicolo.getSelectionModel().getSelectedItem());
		auto.setDisponibilita(Disponibilita.NonDisponibile);
		try {
			presenter.processRequest("AggiornamentoAutoveicolo",auto);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException | CommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void aggiungiNoleggio(){
		try {
			prendiDatiDaView();
			presenter.processRequest("InserimentoNoleggio",noleggio);
			TableView<Contratto> tw=  ((SchermataGenerale)this.getChiamante()).getTable("Noleggio");
			((SchermataGenerale)this.getChiamante()).caricaTabella((List<Cliente>)presenter.processRequest("getAllNoleggi",null), tw);
		} catch (CommonException e) {
			// TODO Auto-generated catch block
			e.showMessage();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void aggiungiGuidatori() throws CommonException{
		
	
			for(Entity el: listItem){
				Guidatore guidatore = (Guidatore)el;
				try {
					presenter.processRequest("InserimentoGuidatore", guidatore);
				} catch (InstantiationException | IllegalAccessException
						| ClassNotFoundException | NoSuchMethodException
						| SecurityException | IllegalArgumentException
						| InvocationTargetException | CommonException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
	
	}
	private void aggiungiPagamento() throws CommonException{
		try {
			if(rdDenaro.isSelected()){
				pagamento= new Contanti();
				prendiDatiPagamentoDaView();
				presenter.processRequest("InserimentoPagamento", pagamento);
			}
			else {
				if(tbCartaCredito.getSelectionModel().getSelectedItem()!=null){
					pagamento= new CartaDiCredito();
					prendiDatiPagamentoDaView();
					presenter.processRequest("InserimentoPagamento", pagamento);
				}
				else 
					throw new CommonException("Seleziona una carta");
			}
	
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalArgumentException
				| InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		}
	}

	/**
	 * <p>In ogni caso setta cauzion e acconto, ma nel caso è una carta di credito allora mette anche l'id della carta di riferimento</p>
	 */
	
	private void prendiDatiPagamentoDaView(){
		pagamento.setDepositoCauzinale(cauzione);
		pagamento.setAcconto(acconto);	
		if(rdCartaCredito.isSelected()){
			((CartaDiCredito)pagamento).setIdCarta(((CartaDiCredito)tbCartaCredito.getSelectionModel().getSelectedItem()).getIdCarta());}
	}
	
    private void prendiDatiDaView() throws CommonException{
    	 
    	noleggio.setIdPagamento(ottieniIDNoleggio());
    	
    	noleggio.setRitiro(dRitiro.getValue());
    	noleggio.setInizioNoleggio(LocalDate.parse(lblDataInizio.getText(),dtf));
    	noleggio.setRientro(LocalDate.parse(lblDataFineNoleggio.getText(),dtf));
    
    	noleggio.setSedeRestituzione(((Sede)tbRestituzione.getSelectionModel().getSelectedItem()).getIDSede());
    	
    	noleggio.setNumeroGiorni(choiceGiorni.getSelectionModel().getSelectedItem());
    	noleggio.setNumeroSettimane(choiceSettimane.getSelectionModel().getSelectedItem());

    	List<Integer> optionalScelti = new ArrayList<Integer>();
    	
		ObservableList<Entity> optional= tbOptionalScelti.getItems();
		for(Entity op: optional){
			optionalScelti.add(((Optional)op).getId());
		}
    			
    	noleggio.setOptional(optionalScelti);
    	
    	
    	Autoveicolo auto= ((Autoveicolo)tbAutoveicolo.getSelectionModel().getSelectedItem());
    	noleggio.setIdAuto(auto.getIDauto());
    	noleggio.setKmBase(auto.getUltimoKm());
    	
    	noleggio.setNumeroChilometri(choiceLimite.getSelectionModel().getSelectedItem());
    	
    	noleggio.setIdcontratto(((Contratto)tbContratto.getSelectionModel().getSelectedItem()).getIDContratto());

    	noleggio.setStato(StatoNoleggio.aperto);

    }
}
