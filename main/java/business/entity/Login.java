package business.entity;

public class Login extends Entity{
	
	private String username;
	private String password; 
	private String operatore;
	private String SupS;
	private String SupA;
	private String Amministratore;
	

	
	public Login(String username, String password) {
		this.username = username;
		this.password=password;
	}

	public String getSupS() {
		return SupS;
	}

	public void setSupS(String supS) {
		SupS = supS;
	}

	public String getSupA() {
		return SupA;
	}

	public void setSupA(String supA) {
		SupA = supA;
	}

	public String getAmministratore() {
		return Amministratore;
	}

	public void setAmministratore(String amministratore) {
		Amministratore = amministratore;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getOperatore() {
		return operatore;
	}

	public void setOperatore(String operatore) {
		this.operatore = operatore;
	}
	
}
