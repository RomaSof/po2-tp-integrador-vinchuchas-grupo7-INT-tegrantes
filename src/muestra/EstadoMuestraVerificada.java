package muestra;
import opinion.*;

public class EstadoMuestraVerificada extends EstadoVerificacionMuestra{


	public void agregarOpinion(Muestra muestra, Opinion opinion) {} //no longer allows to add ops
	
	
	public TipoOpinion getResultadoActual(Muestra muestra) {
		return muestra.getOpinionQueCoincidenExpertos();
	}
	

	public boolean esVerificada() {
		return true;
	}
	
}
