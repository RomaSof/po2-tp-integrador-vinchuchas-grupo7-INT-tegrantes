package usuarioEstado;

import usuario.Usuario;

public interface EstadoUsuario {
	public boolean esExperto();
	public void actualizarEstado(Usuario usuario);
	public boolean verificarCambioDeEstado(Usuario usuario);
}
