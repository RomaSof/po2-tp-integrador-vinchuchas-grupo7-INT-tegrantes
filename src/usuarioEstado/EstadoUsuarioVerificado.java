package usuarioEstado;

import java.util.List;

import muestra.Muestra;
import usuario.Usuario;

public class EstadoUsuarioVerificado implements EstadoUsuario{

	public boolean esExperto() {
		return true;
	}

	public void actualizarEstado(Usuario usuario , List<Muestra> muestrasEnSistema) {
		
	}


	public boolean verificarCambioDeEstado(Usuario usuario, List<Muestra> muestrasEnSistema) {return false;};

}
