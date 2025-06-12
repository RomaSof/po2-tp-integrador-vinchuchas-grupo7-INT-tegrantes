package muestra;

import usuario.Usuario;

public class Opinion {
	private TipoOpinion tipo;
	private Usuario usuario;
	
	//constructor
	public Opinion(TipoOpinion tipo, Usuario usuario) {
		this.tipo = tipo;
		this.usuario = usuario;
	}
	
	public TipoOpinion getTipoOpinion() {
		return this.tipo;
	}

	public boolean esExperto() {
		// TODO Auto-generated method stub
		return true;
	}

	public String getEspecie() {
		// TODO Auto-generated method stub
		return tipo.especie;
	}

	public Usuario getUsuario() {
		// TODO Auto-generated method stub
		return this.usuario;
	}
	
	
	
}
