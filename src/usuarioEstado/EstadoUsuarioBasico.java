package usuarioEstado;

import usuario.Usuario;

public class EstadoUsuarioBasico implements EstadoUsuario{

	public boolean esExperto() {
		return false;
	}

	public void actualizarEstado(Usuario usuario) {
		if(this.verificarCambioDeEstado(usuario)) {
			usuario.setEstado(new EstadoUsuarioExperto());
		}
	}

	public boolean verificarCambioDeEstado(Usuario usuario) {
		if(usuario.getCantidadDeEnviosUltimos30Dias() > 10 && usuario.getCantidadDeRevisionesUltimos30Dias() > 20) {
			return true;			
		}else {
			return false;
		}
	}

}
