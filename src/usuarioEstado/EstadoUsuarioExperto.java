package usuarioEstado;

import java.util.List;

import muestra.Muestra;
import usuario.Usuario;

public class EstadoUsuarioExperto implements EstadoUsuario{

	public void actualizarEstado(Usuario usuario, List<Muestra> muestrasEnSistema) {
		if(this.verificarCambioDeEstado(usuario , muestrasEnSistema)) {
			usuario.setEstado(new EstadoUsuarioBasico());
		}
	}
	
	public boolean esExperto() {
		return true;
	}

	public boolean verificarCambioDeEstado(Usuario usuario ,List<Muestra> muestrasEnSistema) {
		return!((usuario.getCantidadDeEnviosUltimos30Dias(muestrasEnSistema) > 10 && usuario.getCantidadDeRevisionesUltimos30Dias(muestrasEnSistema) > 20));
	}
	
}
