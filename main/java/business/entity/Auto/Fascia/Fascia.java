package business.entity.Auto.Fascia;

import business.entity.Entity;

public abstract  class Fascia extends Entity{
	private int IDFascia;
	private float prezzo;
	private String nome;
	private String Descrizione;
	private float costo_kilometrico;
	public Fascia(int iDFascia, float prezzo,  String descrizione,String nome,float f) {
		super();
		IDFascia = iDFascia;
		this.prezzo = prezzo;
		Descrizione = descrizione;
		this.setNome(nome);
		this.costo_kilometrico=f;
	}
	public int getIDFascia() {
		return IDFascia;
	}
	public void setIDFascia(int iDFascia) {
		IDFascia = iDFascia;
	}
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public String getDescrizione() {
		return Descrizione;
	}
	public void setDescrizione(String descrizione) {
		Descrizione = descrizione;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getCosto_kilometrico() {
		return costo_kilometrico;
	}
	public void setCosto_kilometrico(float costo_kilometrico) {
		this.costo_kilometrico = costo_kilometrico;
	}
	@Override
	public String toString(){
		return nome;
	}
	
}
