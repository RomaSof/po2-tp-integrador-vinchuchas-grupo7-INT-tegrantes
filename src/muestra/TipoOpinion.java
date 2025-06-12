package muestra;

public enum TipoOpinion {
	VINCHUCA_INFESTAN("infestan"), VINCHUCA_SORDIDA("sordida"), VINCHUCA_GUASAYANA("guayana"),
	CHINCHE_FOLIADA("folia"), CHINCHE_PHTIA("phtia"),
	IMAGEN_POCO_CLARA("especie indefinida"), NO_DEFINIDA("especie indefinida");
	
	String especie;
	
	//constructor
	TipoOpinion(String especie) {
		this.especie = especie;
	}
	
	//getters
	public String getEspecie() {
		return this.especie;
	}
	
	
}
