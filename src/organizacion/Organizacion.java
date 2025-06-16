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
	private List<Usuario> usuarios = new ArrayList();
	
	
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
	
	//methods
	public void agregarUsuario(Usuario u) {
		usuarios.add(u);
	}

	@Override
	public void nuevaMuestra(ZonaDeCobertura zona, Muestra muestra) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void muestraValidada(ZonaDeCobertura zona, Muestra muestra) {
		// TODO Auto-generated method stub
		
	}

}
