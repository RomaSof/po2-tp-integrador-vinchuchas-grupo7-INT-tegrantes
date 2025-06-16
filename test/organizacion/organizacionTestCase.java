package organizacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muestra.Muestra;
import ubicacion.Ubicacion;
import usuario.Usuario;
import zonaCobertura.ZonaDeCobertura;

class organizacionTestCase {
	//SUT
	Organizacion organizacion;
	
	//DOCS
	//ubicacion de la organizacion
	Ubicacion u;

	//funcionanlidades de la organizacion para validacion y nuevas muestras
	FuncionalidadExterna funcionalidad1;
	FuncionalidadExterna funcionalidad2;
	
	//zona a la que se suscribe la organizacion
	ZonaDeCobertura zona;
	
	//trabajadores de la organizacion
	Usuario u1;
	Usuario u2;
	Usuario u3;
	
	Muestra m;

@BeforeEach
	public void setup() {
	u = mock(Ubicacion.class);
}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
