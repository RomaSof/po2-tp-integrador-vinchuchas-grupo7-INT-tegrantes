package muestra;

public class EstadoMuestraSemiVerificada extends EstadoVerificacionMuestra{
	
	public void agregarOpinion(Muestra muestra, Opinion opinion) {
		//NEEDS CLASS OPINION
		if( true ) { //opinion.getUsuario().esExperto()) {
			muestra.addOpinion(opinion);
			this.actualizarEstado(muestra);
		}
	}
	
	public void actualizarEstado(Muestra muestra) {
		//if()
			//if all match then 
		muestra.setEstadoMuestra(new EstadoMuestraVerificada());
		//else its voting
	}
}
