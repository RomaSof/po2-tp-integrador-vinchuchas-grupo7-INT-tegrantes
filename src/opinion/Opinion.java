package opinion;
import java.util.Date;

import usuario.Usuario;


public class Opinion {
	private Usuario usuario;
	private TipoOpinion tipoOpinion;
	private Date fechaOpinion;

	private boolean esOpinionVerificada;
	
	//constructor
	public Opinion(Usuario usuario, TipoOpinion tipoOpinion, Date fechaOpinion) {
	    this.usuario = usuario;
	    this.tipoOpinion = tipoOpinion;
	    this.fechaOpinion = fechaOpinion; 
	    this.esOpinionVerificada = usuario.esExperto();
	}
	
	//getters
	public Usuario getUsuario() {
		return this.usuario;
	}
	 
	public TipoOpinion getTipoEspecie() {
		return this.tipoOpinion;
	}
	
	public Date getFechaOpinion() {
		return fechaOpinion;
	}
	
	//methods
	public boolean esOpinionVerificada() {
		return this.esOpinionVerificada;
	}
	
	
}
