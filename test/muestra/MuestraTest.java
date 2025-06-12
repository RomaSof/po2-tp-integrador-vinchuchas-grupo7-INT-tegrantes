package muestra;

import org.junit.jupiter.api.BeforeEach;

import usuario.Usuario;

import static org.mockito.Mockito.*;

public class MuestraTest {
	//SUT
	public Muestra muestra;
	
	//DOCS
	public Opinion op;
	public Usuario user;
	
	@BeforeEach
	public void setUp() {
		op = mock(Opinion.class);
		user = mock(Usuario.class);
				
		muestra = new Muestra(null, null, null, op);
	}

}
