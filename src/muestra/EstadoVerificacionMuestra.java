package muestra;
import opinion.*;
public class EstadoVerificacionMuestra {
	
	//al agregar una opinion el estado cambia solo cuando opina un usuario experto 
	public void agregarOpinion(Muestra muestra, Opinion opinion) {
		muestra.addOpinion(opinion);
		if(opinion.getUsuario().esExperto()) {
			muestra.setEstadoMuestra(new EstadoMuestraVerificandose());
		}
	}

	public TipoOpinion getResultadoActual(Muestra muestra) {
		return muestra.getOpinionMasPopular(); 
	}
	
	public boolean esVerificada() { 
		return false;
	}
	
}
