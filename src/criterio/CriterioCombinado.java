package criterio;

import java.util.ArrayList;
import java.util.List;

import muestra.Muestra;

public class CriterioCombinado implements CriterioBusqueda{
	private OperadorLogico operadorLogico;
	private List<CriterioBusqueda> criteriosDeBusqueda;
	
	public CriterioCombinado(List<CriterioBusqueda> criteriosDeBusqueda, OperadorLogico operadorLogico) {
		this.operadorLogico = operadorLogico;
		this.criteriosDeBusqueda =  new ArrayList<>(criteriosDeBusqueda);
	}
	
	public List<CriterioBusqueda> getCriteriosDeBusqueda(){
		return this.criteriosDeBusqueda;
	}
	
	public OperadorLogico getOperadorLogico() {
		return this.operadorLogico;
	}
	
	public void agregarCriterio(CriterioBusqueda criterio) {
		this.getCriteriosDeBusqueda().add(criterio);
	}

	@Override
	public boolean cumple(Muestra muestra) {
		return this.getOperadorLogico().comparar(muestra, this.getCriteriosDeBusqueda());
	}

	public void setOperador(OperadorLogico otroOperador) {
		this.operadorLogico = otroOperador;
	}
}
