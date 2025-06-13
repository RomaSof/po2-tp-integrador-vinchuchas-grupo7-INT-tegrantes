package opinion;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
	    localDate = LocalDate.of(2025, 5, 3); // 3 de Mayo 2025
	    date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	    usuario = new Usuario("Juan", false);
	    opinion = new Opinion(usuario, TipoOpinion.VINCHUCA_GUASAYANA , date);
	
	}
	
	@Test
	void test() {
		assertTrue(true);
	}

}
