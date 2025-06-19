package usuario;

import muestra.FabricaDeMuestras;
import usuarioEstado.EstadoUsuarioVerificado;

public class UsuarioVerificado extends Usuario{

	public UsuarioVerificado(String nombre, FabricaDeMuestras fabrica) {
		super(nombre, fabrica);
		this.setEstado(new EstadoUsuarioVerificado());
	}
	
}
