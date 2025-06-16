package criterio;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FiltroPorFechaMayorTest {
	private FiltroPorFechaMayor filtroPorFechaMayor;
	private Date fecha1;
	private Date fecha2;
	
	@BeforeEach
	public void setUp() {
		filtroPorFechaMayor = new FiltroPorFechaMayor();
		fecha1 = new Date(2022-12-30);
		fecha2 = new Date(2029-12-31);
	}
	
	@Test
	public void testFiltroPorFechaMayorFuncionaCorrectamente() {
		assertTrue(filtroPorFechaMayor.comparar(fecha2, fecha1));
	}
	
	@Test
	public void testFiltroPorFechaMayorNoPasaElFiltro() {
		assertFalse(filtroPorFechaMayor.comparar(fecha1, fecha2));
	}
	
}
