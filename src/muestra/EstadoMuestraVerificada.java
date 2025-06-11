package muestra;

public class EstadoMuestraVerificada extends EstadoVerificacionMuestra{
	private Opinion opinionFinal;
	
	@Override
	public void agregarOpinion(Muestra muestra, Opinion opinion) {} //no longer allows to add ops
	
	@Override
	public void getResultadoActual(Muestra muestra) {
		//return this.opinionFinal;?
	}
	
	@Override
	public boolean esVerificada() {
		return true;
	}
	
}
