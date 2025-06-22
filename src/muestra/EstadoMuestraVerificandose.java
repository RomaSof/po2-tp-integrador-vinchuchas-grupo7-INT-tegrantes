package muestra;
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
			muestra.notificarMuestraValidada(); //cuando una muestra pasa a ser validada se notifica
		}
	}

	@Override
	public TipoOpinion getResultadoActual(Muestra muestra) {
		return TipoOpinion.NO_DEFINIDA;  
	}
	
}
