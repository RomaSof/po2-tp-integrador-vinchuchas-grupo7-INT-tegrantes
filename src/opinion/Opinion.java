package opinion;
import java.time.LocalDate;

import usuario.Usuario;


public class Opinion {
	private Usuario usuario;
	private TipoOpinion tipoOpinion;
	private LocalDate fechaOpinion;

	private boolean esOpinionVerificada;
	
	//constructor
	public Opinion(Usuario usuario, TipoOpinion tipoOpinion, LocalDate fechaOpinion) {
	    this.usuario = usuario;
	    this.tipoOpinion = tipoOpinion;
	    this.fechaOpinion = fechaOpinion; // Usa la fecha proporcionada
	    this.esOpinionVerificada = usuario.esExperto();
	}
	
	//getters
	public Usuario getUsuario() {
		return this.usuario;
	}
	
	public TipoOpinion getTipoEspecie() {
		return this.tipoOpinion;
	}
	
	public LocalDate getFechaOpinion() {
		return fechaOpinion;
	}
	
	//methods
	public boolean esOpinionVerificada() {
		return this.esOpinionVerificada;
	}
}
