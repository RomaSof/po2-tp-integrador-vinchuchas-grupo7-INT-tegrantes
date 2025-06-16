package criterio;

import muestra.Muestra;
import opinion.TipoOpinion;

public class CriterioTipoInsecto implements CriterioBusqueda {
	private TipoOpinion tipoOpinion; // El tipo de insecto esta relacionado con el tipoOpinion según el resultado actual de la muestra.
	
	public CriterioTipoInsecto(TipoOpinion tipoOpinion) {
		this.tipoOpinion = tipoOpinion;
	}
	
	public TipoOpinion getTipoInsecto() {
		return this.tipoOpinion;
	}
	
	@Override
	public boolean cumple(Muestra muestra) {
		return (muestra.getResultadoActual() == this.getTipoInsecto());
	}
}
