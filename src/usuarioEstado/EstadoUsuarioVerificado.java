package usuarioEstado;

import usuario.Usuario;

public class EstadoUsuarioVerificado implements EstadoUsuario{

	public boolean esExperto() {
		return true;
	}

	public void actualizarEstado(Usuario usuario) {
		
	}


	public boolean verificarCambioDeEstado(Usuario usuario) {return true;};

}
