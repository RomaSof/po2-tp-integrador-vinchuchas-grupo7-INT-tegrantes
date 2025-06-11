package muestra;

import java.util.List;
import java.util.ArrayList;

public class EstadoMuestraVotando extends EstadoVerificacionMuestra {
	private List<Opinion> votacion = new ArrayList<Opinion>();
	
	@Override
	public void agregarOpinion(Muestra muestra, Opinion opinion) {
		//NEEDS CLASS OPINION
		if( true ) { //opinion.getUsuario().esExperto()) {
			//muestra.addOpinion(opinion); -> not sure
			this.votacion.add(opinion);
			//this.actualizarEstado(muestra); -> cuando, por qué?
		}
	}
	
	@Override
	public void getResultadoActual(Muestra muestra) {
		//return ...
	}
	
	@Override
	public void actualizarEstado(Muestra muestra) {
		muestra.setEstadoMuestra(new EstadoMuestraVerificada());
	}
}
