package organizacion;

import java.util.ArrayList;
import java.util.List;

import muestra.Muestra;
import ubicacion.Ubicacion;
import usuario.Usuario;
import zonaCobertura.ObservadorZona;
import zonaCobertura.ZonaDeCobertura;

public class Organizacion implements ObservadorZona{
	private Ubicacion ubicacion;
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	private FuncionalidadExterna fCargaNuevaMuestra;
	private FuncionalidadExterna fValidacionNuevaMuestra;
	
	//constructor
	public Organizacion(Ubicacion ubicacion,FuncionalidadExterna fCarga, FuncionalidadExterna fValida) {
		this.ubicacion = ubicacion;
		this.fCargaNuevaMuestra = fCarga;
		this.fValidacionNuevaMuestra = fValida;
	}
	
	//getters
	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	
	public tipoOrganicacion getTipoOrganizacion() {
		return null;
	}
	
	public int getCantTrabajadores() {
		return 0;
	}
	
	public List<Usuario> getEmpleados(){
		return this.usuarios;
	}
	
	public FuncionalidadExterna getFuncionalidadExternaCarga() {
		return this.fCargaNuevaMuestra;
	}
	
	public FuncionalidadExterna getFuncionalidadExternaValidacion() {
		return this.fValidacionNuevaMuestra;
	}
	
	//setters
	public void setFuncionalidadExternaCarga(FuncionalidadExterna newFCarga) {
		this.fCargaNuevaMuestra = newFCarga;
	}
	
	public void setFuncionalidadExternaValidacion(FuncionalidadExterna newFValidacion) {
		this.fCargaNuevaMuestra = newFValidacion;
	}
	
	
	//methods
	public void agregarUsuario(Usuario u) {
		usuarios.add(u);
	}

	@Override
	public void nuevaMuestra(ZonaDeCobertura zona, Muestra muestra) {
		this.fCargaNuevaMuestra.nuevoEvento(this, zona, muestra);
		
	}

	@Override
	public void muestraValidada(ZonaDeCobertura zona, Muestra muestra) {
		this.fCargaNuevaMuestra.nuevoEvento(this, zona, muestra);
		
	}

}
