package utility;

import business.entity.Entity;

public class ParametriFXML {
	private String titolo;
	private boolean ridimensionabile;
	private boolean handChiusura;
	private Entity entity;
	
	public Entity getEntity() {
		return entity;
	}


	public void setEntity(Entity entity) {
		this.entity = entity;
	}


	public ParametriFXML(String titolo, boolean ridimensionabile) {
		this.titolo = titolo;
		this.ridimensionabile = ridimensionabile;
	}


	public ParametriFXML(String titolo, boolean ridimensionabile,boolean handChiusura) {
		this.titolo = titolo;
		this.ridimensionabile = ridimensionabile;
		this.handChiusura= handChiusura;
	}
	
	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public boolean isRidimensionabile() {
		return ridimensionabile;
	}

	public void setRidimensionabile(boolean ridimensionabile) {
		this.ridimensionabile = ridimensionabile;
	}	
	public boolean getHandChiusura(){
		return handChiusura;
	}
	public void setHand(boolean hand){
		handChiusura=hand;
	}
}
