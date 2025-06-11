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
	
	//NEEDS OPINION CLASS
	//@Override
	//public Opinion getResultadoActual(Muestra muestra) {
	//	return Nothing; //harcodeo que no tiene un resultado actual pq se esta votando 
	//}

}
