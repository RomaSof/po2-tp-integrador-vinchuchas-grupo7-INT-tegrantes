package usuarioEstado;

import usuario.Usuario;

public interface EstadoUsuario {
	public boolean esExperto();
	public void actualizarEstado(Usuario usuario,EstadoUsuario nuevoEstado);
	public boolean verificarCambioDeEstado(Usuario usuario);
}
