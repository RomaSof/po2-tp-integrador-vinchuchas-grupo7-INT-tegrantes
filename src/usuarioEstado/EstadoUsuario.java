package usuarioEstado;

import java.util.List;

import muestra.Muestra;
import usuario.Usuario;

public interface EstadoUsuario {
	public boolean esExperto();
	public void actualizarEstado(Usuario usuario, List<Muestra> muestrasEnSistema);
	public boolean verificarCambioDeEstado(Usuario usuario,List<Muestra> muestrasEnSistema);
}
