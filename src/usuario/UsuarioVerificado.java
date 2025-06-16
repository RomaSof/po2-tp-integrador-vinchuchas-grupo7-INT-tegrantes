package usuario;

import usuarioEstado.EstadoUsuarioVerificado;

public class UsuarioVerificado extends Usuario{

	public UsuarioVerificado(String nombre) {
		super(nombre);
		this.setEstado(new EstadoUsuarioVerificado());
	}
	
}
