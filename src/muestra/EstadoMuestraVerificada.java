package muestra;

public class EstadoMuestraVerificada extends EstadoVerificacionMuestra{

	@Override
	public void agregarOpinion(Muestra muestra, Opinion opinion) {} //no longer allows to add ops
	
	//NEEDS OPINION CLASS
	//@Override
	//public Opinion getResultadoActual(Muestra muestra) {
		//return this.getOpinionQueCoicidenExpertos();
	//}
	
	@Override
	public boolean esVerificada() {
		return true;
	}
	
}
