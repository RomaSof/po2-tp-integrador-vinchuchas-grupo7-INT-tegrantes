package muestra;

import java.util.ArrayList;
import java.util.List;

public class EstadoMuestraVerificandose extends EstadoVerificacionMuestra{
	
	@Override
	public void agregarOpinion(Muestra muestra, Opinion opinion) {
		//NEEDS CLASS OPINION
		if( true ) { //-> (opinion.getUsuario().esExperto())
			muestra.addOpinion(opinion);
			if(true) { //-> (muestra.coincidenExpertos())
				//muestra.setEstadoMuestra(new EstadoMuestraVerificada()); 
			}
		}
	}

	@Override
	public TipoOpinion getResultadoActual(Muestra muestra) {
		return new OpinionVacia().getTipoOpinion();  
	}

}
