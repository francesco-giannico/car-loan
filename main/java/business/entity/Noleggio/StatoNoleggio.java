package business.entity.Noleggio;

public enum StatoNoleggio {
	aperto, annullato, chiuso,vuoto;
	
	public static StatoNoleggio toStatoNoleggio(String stato){
		switch(stato){
			case  "aperto": 
				return StatoNoleggio.aperto;
			case "annullato":
				return StatoNoleggio.annullato;
			case "chiuso":
				return StatoNoleggio.chiuso;
			default:
				return null;
		}
		
	}
}
