package criterio;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FiltroPorFechaMenorTest {
	private FiltroPorFechaMenor filtroPorFechaMenor;
	private Date fecha1;
	private Date fecha2;
	
	@BeforeEach
	public void setUp() {
		filtroPorFechaMenor = new FiltroPorFechaMenor();
		fecha1 = new Date(2022-12-30);
		fecha2 = new Date(2029-12-31);
	}
	
	@Test
	public void testFiltroPorFechaMenorFuncionaCorrectamente() {
		assertTrue(filtroPorFechaMenor.comparar(fecha1, fecha2));
	}
	
	@Test
	public void testFiltroPorFechaMenorNoPasaElFiltro() {
		assertFalse(filtroPorFechaMenor.comparar(fecha2, fecha1));
	}
	
}
