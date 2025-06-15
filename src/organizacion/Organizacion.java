package organizacion;

import java.util.ArrayList;
import java.util.List;

import ubicacion.Ubicacion;
import usuario.Usuario;

public class Organizacion {
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

}
