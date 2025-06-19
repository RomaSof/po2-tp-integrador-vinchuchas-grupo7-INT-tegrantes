package muestra;

import java.util.Date;

import avisoOrganizaciones.ObservadorMuestra;
import opinion.TipoOpinion;
import ubicacion.Ubicacion;
import usuario.Usuario;

public class FabricaDeMuestras {
	
	private ObservadorMuestra observador;

	// Constructor que recibe el observador (inyectado desde afuera)
	public FabricaDeMuestras(ObservadorMuestra observador) {
		this.observador = observador;
	}
	
	public Muestra crearMuestra(Usuario usuario, Date fecha, Ubicacion ubicacion, String imagen, TipoOpinion opinionInicial) {
		return new Muestra(usuario, fecha, ubicacion, imagen, opinionInicial, this.observador);
	}
	
	public ObservadorMuestra getObservadorMuestra() {
		return this.observador;
	}
}
