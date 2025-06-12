package muestra;

public class EstadoMuestraVerificandose extends EstadoVerificacionMuestra{
	
	@Override
	public void agregarOpinion(Muestra muestra, Opinion opinion) {
		if(opinion.getUsuario().esExperto()) {
			muestra.addOpinion(opinion);
			if(muestra.coincidenExpertos()) {
				muestra.setEstadoMuestra(new EstadoMuestraVerificada()); 
			}
		}
	}

	@Override
	public TipoOpinion getResultadoActual(Muestra muestra) {
		return TipoOpinion.NO_DEFINIDA;  
	}

}
