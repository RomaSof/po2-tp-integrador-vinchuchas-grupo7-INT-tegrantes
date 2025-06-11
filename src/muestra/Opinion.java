package muestra;

import java.util.ArrayList;
import java.util.List;

import usuario.Usuario;

public class Opinion {
	private tipoOpinion tipo;
	private List<Usuario> votos = new ArrayList<Usuario>();
	
	public tipoOpinion getTipoOpinion() {
		return this.tipo;
	}
	
	public int getValor() {
		return votos.size();
	}

	public boolean esExperto() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	
}
