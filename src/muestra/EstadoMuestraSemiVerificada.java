package muestra;

public class EstadoMuestraSemiVerificada extends EstadoVerificacionMuestra{
	
	
	@Override
	public void agregarOpinion(Muestra muestra, Opinion opinion) {
		//NEEDS CLASS OPINION
		if( true ) { //opinion.getUsuario().esExperto()) {
			muestra.addOpinion(opinion);
			this.actualizarEstado(muestra);
		}
	}
	
	@Override 
	public void getResultadoActual(Muestra muestra) {
		//return muestra.getOpinionesDeExpertos().get(0); ??
		//better : return Nothing -> hardcoreo del indefinido enum?
	}
	
	@Override
	public void actualizarEstado(Muestra muestra) { 
		//if(muestra.getOpinionesDeExpertos.streams().allMatch()){
			muestra.setEstadoMuestra(new EstadoMuestraVerificada());
		//} else {
			muestra.setEstadoMuestra(new EstadoMuestraVotando());
		//}
		
		//else its voting
	}
}
