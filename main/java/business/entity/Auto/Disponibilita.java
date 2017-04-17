package business.entity.Auto;

public enum Disponibilita {
	Disponibile,NonDisponibile,ManutenzioneOrdinaria,ManutenzioneStraordinaria,DaManutenere;
	
	
	public static Disponibilita toDisponibilita(String disp){
		switch(disp){
		case  "Disponibile":
			return Disponibile;
		case  "NonDisponibile":
			return NonDisponibile;
		case "ManutenzioneOrdinaria":
			return ManutenzioneOrdinaria;
		case "ManutenzioneStraordinaria" :
			return ManutenzioneStraordinaria;
		case "DaManutenere": 
			return DaManutenere;
		default :
				return null;
		}
	}
}
