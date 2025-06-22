package opinion;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import usuario.Usuario;
import usuario.UsuarioVerificado;

class OpinionTest {
	private Usuario usuario;
	private Usuario usuario2;
	
	private Date date;
	private LocalDate localDate;
	
	private Opinion opinion;
	private Opinion opinion2;
	
	@BeforeEach
	public void setup() {
		 	localDate = LocalDate.now();
		    date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		    
		    usuario = Mockito.spy(new Usuario("Jose"));
		    usuario2 = Mockito.spy(new UsuarioVerificado("Pepe"));
		    
		    opinion = new Opinion(usuario, TipoOpinion.CHINCHE_PHTIA, date);
		    
		    opinion2 = new Opinion(usuario2, TipoOpinion.CHINCHE_PHTIA, date);
	    
	}
	
	//TEST GETTERS
	
	@Test
	void getUsuarioTest() {
		assertEquals(usuario, opinion.getUsuario());
	}
	
	@Test
	void getTipoEspecieTest() {
		assertEquals(TipoOpinion.CHINCHE_PHTIA, opinion.getTipoEspecie());
	}
	
	@Test
	void getFechaOpinionTest() {
		assertEquals(date, opinion.getFechaOpinion());
	}
	
	@Test
	void esOpinionVerificadaTest() {
		assertFalse(usuario.esExperto()); //un usuario no experto no hace opiniones verificadas
		assertFalse(opinion.esOpinionVerificada());
		
		assertTrue(usuario2.esExperto()); //un usuario verificado hace opiniones verificadas
		assertTrue(opinion2.esOpinionVerificada());
	}

}
