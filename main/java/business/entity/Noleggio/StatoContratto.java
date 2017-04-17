package business.entity.Noleggio;

import java.util.ArrayList;
import java.util.List;

public enum StatoContratto { 
	Aperto,Chiuso,Annullato;
	
	public static List<StatoContratto> getAllStates(){
		List<StatoContratto> stati= new ArrayList<StatoContratto>();
		stati.add(Aperto);
		stati.add(Chiuso);
		stati.add(Annullato);
		return stati;
	}
	public static StatoContratto toStato(String s){
		if (s.equals(Aperto.toString())){
			return Aperto;
		}
		else if(s.equals(Chiuso.toString())){
			return Chiuso;
		}
		else 
			return Annullato;
	}
}
