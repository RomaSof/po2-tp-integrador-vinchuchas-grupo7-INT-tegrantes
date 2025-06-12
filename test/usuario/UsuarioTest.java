package usuario;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import muestra.Muestra;
import opinion.Opinion;
import opinion.TipoOpinion;


class UsuarioTest {
	
	private Usuario usuario;
	private Opinion opinion;
	private Muestra muestra;

	@BeforeEach
	public void setup() {
		usuario = new Usuario("Juan", false);
		opinion = new Opinion(usuario, TipoOpinion.VINCHUCA_INFESTAN , muestra);
		
		
	}


	@Test
	void test() {
		
	}
	
}
