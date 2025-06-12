package usuarioEstado;

import usuario.Usuario;

public class EstadoUsuarioExperto implements EstadoUsuario{

	public void actualizarEstado(Usuario usuario, EstadoUsuario nuevoEstado) {
		if(this.verificarCambioDeEstado(usuario)) {
			usuario.setEstado(new EstadoUsuarioBasico());
		}
	}
	
	public boolean esExperto() {
		return true;
	}

	public boolean verificarCambioDeEstado(Usuario usuario) {
		if(!(usuario.getCantidadDeEnviosUltimos30Dias() > 10 && usuario.getCantidadDeRevisionesUltimos30Dias() > 20)) {
			return true;			
		}else {
			return false;
		}
	}
	
}
