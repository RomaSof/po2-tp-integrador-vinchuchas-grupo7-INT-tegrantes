package muestra;

public class EstadoMuestraVerificada extends EstadoVerificacionMuestra{

	@Override
	public void agregarOpinion(Muestra muestra, Opinion opinion) {} //no longer allows to add ops
	
	
	@Override
	public TipoOpinion getResultadoActual(Muestra muestra) {
		return muestra.getOpinionQueCoincidenExpertos();
	}
	
	@Override
	public boolean esVerificada() {
		return true;
	}
	
}
