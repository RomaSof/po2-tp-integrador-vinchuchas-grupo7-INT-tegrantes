package organizacion;

import java.util.ArrayList;
import java.util.List;

import ubicacion.Ubicacion;
import usuario.Usuario;

public class Organizacion {
	private Ubicacion ubicacion;
	private List<Usuario> usuarios = new ArrayList();
	
	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	
	public tipoOrganicacion getTipoOrganizacion() {
		return null;
	}
	
	public int getCantTrabajadores() {
		return 0;
	}
	
	public void agregarUsuario(Usuario u) {
		
	}
	
	public List<Usuario> getUsuarios(){
		return this.usuarios;
	}

}
