package muestra;

import java.util.ArrayList;
import java.util.List;

import usuario.Usuario;

public class Opinion {
	private TipoOpinion tipo;
	private List<Usuario> votos = new ArrayList<Usuario>();
	
	public TipoOpinion getTipoOpinion() {
		return this.tipo;
	}
	
	public int getValor() {
		return votos.size();
	}

	public boolean esExperto() {
		// TODO Auto-generated method stub
		return true;
	}

	public String getEspecie() {
		// TODO Auto-generated method stub
		return tipo.especie;
	}

	public Opinion getUsuario() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
