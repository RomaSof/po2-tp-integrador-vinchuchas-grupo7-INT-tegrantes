package organizacion;

import java.util.ArrayList;
import java.util.List;

import muestra.Muestra;
import ubicacion.Ubicacion;
import usuario.Usuario;
import zonaCobertura.ObservadorZona;
import zonaCobertura.ZonaDeCobertura; 

public class Organizacion implements ObservadorZona{
	private TipoOrganicacion TipoOrganicacion;
	private Ubicacion ubicacion;
	private FuncionalidadExterna fCargaNuevaMuestra;
	private FuncionalidadExterna fValidacionNuevaMuestra;
	private List<Usuario> empleados = new ArrayList<Usuario>();
	
	//constructor
	public Organizacion(TipoOrganicacion tipo, Ubicacion ubicacion,FuncionalidadExterna fCarga, FuncionalidadExterna fValida) {
		this.ubicacion = ubicacion;
		this.fCargaNuevaMuestra = fCarga;
		this.fValidacionNuevaMuestra = fValida;
		this.TipoOrganicacion = tipo;
	}
	
	//getters
	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	
	public TipoOrganicacion getTipoOrganizacion() {
		return this.TipoOrganicacion;
	}
	
	public int getCantTrabajadores() {
		return this.empleados.size();
	}
	
	public List<Usuario> getEmpleados(){
		return this.empleados;
	}
	
	public FuncionalidadExterna getFuncionalidadExternaCarga() {
		return this.fCargaNuevaMuestra;
	}
	
	public FuncionalidadExterna getFuncionalidadExternaValidacion() {
		return this.fValidacionNuevaMuestra;
	}
	
	//setters
	
	/*
	 * las funcionalidades externas son cómo la organización configura responder a las diferentes alertas dentro de una zona de covertura 
	 * a la que esten registrados para saber avisos.
	 * Hay una para los avisos de que se registraron muestras nuevas y otro para los avisos que se validó alguna muestra.
	 * a lo largo de la vida de la organización estas funcionalidades puede cambiar, y pueden ser diferentes o iguales para los
	 * diferentes tipos de avisos
	 * */
	
	public void setFuncionalidadExternaCarga(FuncionalidadExterna newFCarga) {
		this.fCargaNuevaMuestra = newFCarga;
	}
	
	public void setFuncionalidadExternaValidacion(FuncionalidadExterna newFValidacion) {
		this.fValidacionNuevaMuestra = newFValidacion;
	}
	
	
	//methods
	public void agregarEmpleado(Usuario u) {
		empleados.add(u);
	}

	@Override
	public void nuevaMuestra(ZonaDeCobertura zona, Muestra muestra) {
		this.fCargaNuevaMuestra.nuevoEvento(this, zona, muestra);
		
	}

	@Override
	public void muestraValidada(ZonaDeCobertura zona, Muestra muestra) {
		this.fValidacionNuevaMuestra.nuevoEvento(this, zona, muestra);
		
	}

}
