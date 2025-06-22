package muestra;
import opinion.*;

public class EstadoMuestraVerificada extends EstadoVerificacionMuestra{

	//cuando la muestra se verifica ya no se pueden agrgar opiniones
	public void agregarOpinion(Muestra muestra, Opinion opinion) {} 
	
	@Override
	public TipoOpinion getResultadoActual(Muestra muestra) {
		return muestra.getOpinionQueCoincidenExpertos();
	}
	
	@Override
	public boolean esVerificada() {
		return true; 
	}
	
}
