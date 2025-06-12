package usuario;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import muestra.Muestra;
import opinion.Opinion;
import opinion.TipoOpinion;


class UsuarioTest {
	
	private Usuario usuario1;
	private Usuario usuario2;
	private Opinion opinion;
	private Muestra muestra;
	private Date date;
	private LocalDate localDate;

	@BeforeEach
	public void setup() {
	    localDate = LocalDate.of(2025, 5, 3); // 3 de Mayo 2025
	    date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	    
		usuario1 = new Usuario("Juan", false);
		usuario2 = new Usuario("Pepe", false);
	
		opinion = new Opinion(usuario1, TipoOpinion.VINCHUCA_INFESTAN , muestra);
		
		
	}


	@Test
	void testCrearMuestra() {
		muestra = new Muestra(usuario1,date,"image",opinion);
		assertTrue(muestra.getRecolectorMuestra().equals(usuario1));
	}
	
}
