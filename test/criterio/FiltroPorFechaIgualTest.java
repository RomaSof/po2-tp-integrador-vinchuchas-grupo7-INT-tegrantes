package criterio;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FiltroPorFechaIgualTest {
	private FiltroPorFechaIgual filtroPorFechaIgual;
	private Date fecha1;
	private Date fecha2;
	private Date fecha3;
	
	@BeforeEach
	public void setUp() {
		filtroPorFechaIgual = new FiltroPorFechaIgual();
		fecha1 = new Date(2022-12-31);
		fecha2 = new Date(2022-12-31);
		fecha3 = new Date(2022-12-30);
	}
	
	@Test
	public void testFiltroPorFechaIgualFuncionaCorrectamente() {
		assertTrue(filtroPorFechaIgual.comparar(fecha1, fecha2));
	}
	
	@Test
	public void testFiltroPorFechaIgualNoPasaElFiltro() {
		assertFalse(filtroPorFechaIgual.comparar(fecha1, fecha3));
	}
	
}
