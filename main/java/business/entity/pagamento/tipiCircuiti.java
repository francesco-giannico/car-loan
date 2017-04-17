package business.entity.pagamento;

import java.util.ArrayList;
import java.util.List;

public enum tipiCircuiti {
	MasterCard,Visa;
	
	public static List<tipiCircuiti> getAllCircuiti(){
		List<tipiCircuiti> stati= new ArrayList<tipiCircuiti>();
		stati.add(MasterCard);
		stati.add(Visa);
		return stati;
	}
	public static tipiCircuiti toCircuito(String s){
		if(s.equals(tipiCircuiti.MasterCard.toString())){
			return MasterCard;
		}
		else 
			return Visa;
	}
	
}
