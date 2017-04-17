package utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javafx.scene.control.Alert.AlertType;
import MessaggiFinestra.AlertView;

public class Crittografia {
	public final static String CriptaPassword(String password) {
		 MessageDigest md;
		 byte[] output = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes());
		      output= md.digest();
		    
		} catch (NoSuchAlgorithmException e) {
			AlertView.getAlertView("L'utility per la crittografia è corrotta", AlertType.ERROR);
		}
		 return (bytesToHex(output)); 
	}
	private  static String bytesToHex(byte[] b) {
	    char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7',
	                       '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
	    StringBuffer buf = new StringBuffer();
	    for (int j=0; j<b.length; j++) {
	       buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
	       buf.append(hexDigit[b[j] & 0x0f]);
	    }
	    return buf.toString();
	 }
	private Crittografia(){
		
	}
}

