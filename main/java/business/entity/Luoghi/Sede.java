package business.entity.Luoghi;

import business.entity.Entity;

public class Sede extends Entity{
	private int IDSede;

	private String Indirizzo;
	private String NumeroTelefono;
	private String nome;
	private int IDAgenzia; 
	public Sede(int iDSede, String indirizzo, String numeroTelefono,String nome,int idagenzia) {
		super();
		IDSede = iDSede;
		Indirizzo = indirizzo;
		NumeroTelefono = numeroTelefono;
		this.nome=nome;
		setIDAgenzia(idagenzia);
	}
	public Sede( String indirizzo, String numeroTelefono,String nome,int idagenzia) {
		super();
		
		Indirizzo = indirizzo;
		NumeroTelefono = numeroTelefono;
		this.nome=nome;
		setIDAgenzia(idagenzia);
	}
	public int getIDSede() {
		return IDSede;
	}
	public void setIDSede(int iDSede) {
		IDSede = iDSede;
	}
	public String getIndirizzo() {
		return Indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		Indirizzo = indirizzo;
	}
	public String getNumeroTelefono() {
		return NumeroTelefono;
	}
	public void setNumeroTelefono(String numeroTelefono) {
		NumeroTelefono = numeroTelefono;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String  nome) {
		this.nome = nome;
	}
	public int getIDAgenzia() {
		return IDAgenzia;
	}
	public void setIDAgenzia(int idagenzia2) {
		IDAgenzia = idagenzia2;
	}
	
	
	public boolean equals(Object s){
		
			if((s instanceof Sede) && ((Sede) s).getIDSede()==this.IDSede)
				return true;
			else
		return false;
		
	}
	public int hashCode(){
		return super.hashCode();
	}
}
