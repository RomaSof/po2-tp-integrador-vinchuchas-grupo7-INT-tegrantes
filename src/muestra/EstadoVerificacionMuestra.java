package muestra;
import opinion.*;
public class EstadoVerificacionMuestra {
	
	public void agregarOpinion(Muestra muestra, Opinion opinion) {
		muestra.addOpinion(opinion);
		if(opinion.getUsuario().esExperto()) {
			muestra.setEstadoMuestra(new EstadoMuestraVerificandose());
		}
	}
	
	//NEEDS OPINION CLASS
	public TipoOpinion getResultadoActual(Muestra muestra) {
		return muestra.getOpinionMasPopular();
	}
	
	public boolean esVerificada() {
		return false;
	}

}
