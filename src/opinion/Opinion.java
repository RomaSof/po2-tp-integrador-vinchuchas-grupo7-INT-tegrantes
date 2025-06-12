package opinion;
import java.time.LocalDate;

import muestra.Muestra;
import usuario.Usuario;

public class Opinion {
	private Usuario usuario;
	private TipoOpinion tipoOpinion;
	private LocalDate fechaOpinion;
	private Muestra muestra;
	public Muestra getMuestra() {
		return muestra;
	}

	private boolean esOpinionVerificada;
	
	//test en espa;ol
	
	public Opinion(Usuario usuario, TipoOpinion tipoOpinion, Muestra muestra) {
			this.usuario = usuario;
			this.tipoOpinion = tipoOpinion;
			this.muestra = muestra;
			this.fechaOpinion = LocalDate.now();
			this.esOpinionVerificada = usuario.esExperto();
	}
	
	public void enviarOpinion() {
		if(!this.getMuestra().getOpiniones().contains(this.getUsuario())) {
			this.getMuestra().addOpinionUsuario(this);
			this.getUsuario().addOpinion(this);
		}
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}
	
	public TipoOpinion getTipoEspecie() {
		return this.tipoOpinion;
	}
	
	public boolean esOpinionVerificada() {
		return this.esOpinionVerificada;
	}
	
	public LocalDate getFechaOpinion() {
		return fechaOpinion;
	}
}
