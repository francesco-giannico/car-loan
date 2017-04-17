package business.entity;

public class UtenteCorrente {
	static Utente utente;
	
	public static Utente getUtente(){
		return utente;
	}
	public static void setUtente(Utente entity){
		utente= entity;
	}
	private UtenteCorrente(){
		
	}
}
