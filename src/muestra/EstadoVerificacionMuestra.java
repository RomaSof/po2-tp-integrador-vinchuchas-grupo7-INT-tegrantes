package muestra;

public class EstadoVerificacionMuestra {
	
	public void agregarOpinion(Muestra muestra, Opinion opinion) {
		muestra.addOpinion(opinion);
		//NEEDS CLASS OPINION
		if( true ) { // -> (opinion.getUsuario().esExperto())
			muestra.setEstadoMuestra(new EstadoMuestraVerificandose());
		}
	}
	
	public void getResultadoActual(Muestra muestra) {
		//return muestra.getOpinionMasPopular();
	}
	
	public boolean esVerificada() {
		return false;
	}

}
