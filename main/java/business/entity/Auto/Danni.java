package business.entity.Auto;

public class Danni {
	private String danniFutili;
	private String danniGravi;
	
	public Danni(String danniFutili, String danniGravi) {
		super();
		this.danniFutili = danniFutili;
		this.danniGravi = danniGravi;
	}
	public String getDanniFutili() {
		return danniFutili;
	}

	public void setDanniFutili(String danniFutili) {
		this.danniFutili = danniFutili;
	}

	public String getDanniGravi() {
		return danniGravi;
	}

	public void setDanniGravi(String danniGravi) {
		this.danniGravi = danniGravi;
	}
}
