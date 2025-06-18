package muestra;
import avisoOrganizaciones.ObservadorMuestra;
import opinion.*;
public class EstadoMuestraVerificandose extends EstadoVerificacionMuestra{
	
	//al agregar una opinion el estado solo cambia cuando un experto opina 
	//y solo los expertos pueden agregar una opinion ahora
	@Override
	public void agregarOpinion(Muestra muestra, Opinion opinion) { 
		if(opinion.getUsuario().esExperto()) {
			muestra.addOpinion(opinion);
			this.cambiarDeEstadoSiPosible(muestra); 
		}
	}
	
	//cambia el estado cuando un experto opina
	protected void cambiarDeEstadoSiPosible(Muestra muestra) {
		if(muestra.coincidenExpertos()) {
			muestra.setEstadoMuestra(new EstadoMuestraVerificada());
		}
	}

	@Override
	public TipoOpinion getResultadoActual(Muestra muestra) {
		return TipoOpinion.NO_DEFINIDA;  
	}
	
	@Override
	public void notify(Muestra muestra, ObservadorMuestra obsMuestra) {
		// se mantiene vacio! no debe notificar nada
	}
	
}
