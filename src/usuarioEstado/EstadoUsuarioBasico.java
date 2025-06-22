package usuarioEstado;

import java.util.List;

import muestra.Muestra;
import usuario.*;

public class EstadoUsuarioBasico implements EstadoUsuario{

	public boolean esExperto() {
		return false;
	}

	public void actualizarEstado(Usuario usuario , List<Muestra> muestrasEnSistema) {
		if(this.verificarCambioDeEstado(usuario , muestrasEnSistema)) {
			usuario.setEstado(new EstadoUsuarioExperto());
		}
	}
	public boolean verificarCambioDeEstado(Usuario usuario , List<Muestra> muestrasEnSistema) {
		return(usuario.getCantidadDeEnviosUltimos30Dias(muestrasEnSistema) > 10 && usuario.getCantidadDeRevisionesUltimos30Dias(muestrasEnSistema) > 20);

	}
}