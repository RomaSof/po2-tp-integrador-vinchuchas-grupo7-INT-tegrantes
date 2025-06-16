package opinion;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import muestra.Muestra;
import usuario.Usuario;

class OpinionTest {
	private Usuario usuario;
	private Date date;
	private LocalDate localDate;
	private Opinion opinion;
	private Muestra muestra;
	
	@BeforeEach
	public void setup() {
	    localDate = LocalDate.now();
	    date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	    
	    usuario = Mockito.spy(new Usuario("Jose", false));
	    opinion = Mockito.spy(new Opinion(usuario, TipoOpinion.VINCHUCA_SORDIDA, new Date()));
	    muestra = Mockito.spy(new Muestra(usuario, new Date(), "imagen.jpg" , opinion));
	    opinion.enviarOpinion(muestra);
	    
	    
	    
	}
	
	@Test
	void testCreacionMuestra() {
		assertTrue(muestra.getRecolectorMuestra().equals(usuario));
		assertEquals(muestra.getHistorialDeOpiniones().size(), 1);
		assertTrue(muestra.getEspecie().equals(TipoOpinion.VINCHUCA_SORDIDA.getEspecie()));
	}
	
	@Test
	void testEnviarOpinion() {
		Usuario usuario2 = Mockito.spy(new Usuario("Raul", false));
		Opinion op2 = Mockito.spy(new Opinion(usuario2, TipoOpinion.CHINCHE_FOLIADA , new Date()));
		op2.enviarOpinion(muestra); // envio la opinion
		assertEquals(muestra.getHistorialDeOpiniones().size(), 2);
		
	}

}
