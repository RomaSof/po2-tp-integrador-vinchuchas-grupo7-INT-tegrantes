package opinion;
import java.util.Date;

import muestra.Muestra;
import usuario.Usuario;


public class Opinion {
	private Usuario usuario;
	private TipoOpinion tipoOpinion;
	private Date fechaOpinion;
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
			this.fechaOpinion = new Date();
			this.esOpinionVerificada = usuario.esExperto();
	}
	
	public void enviarOpinion() {
		if(!this.getMuestra().getHistorialDeOpiniones().contains(this.getUsuario())) {
			this.getMuestra().agregarOpinion(this);
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
	
	public Date getFechaOpinion() {
		return fechaOpinion;
	}
}
