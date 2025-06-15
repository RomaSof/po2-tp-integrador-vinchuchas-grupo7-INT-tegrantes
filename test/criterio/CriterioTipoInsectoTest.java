package criterio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muestra.Muestra;
import opinion.TipoOpinion;

public class CriterioTipoInsectoTest {
	private TipoOpinion tipoOpinion;
	private CriterioTipoInsecto criterioInsecto;
	private Muestra muestra;
	
	@BeforeEach
	public void setUp() {
		tipoOpinion = TipoOpinion.VINCHUCA_INFESTAN;
		criterioInsecto = new CriterioTipoInsecto(tipoOpinion);
		muestra = mock(Muestra.class);
	}
	
	@Test
	public void testCriterioTipoInsectoSeInicializaCorrectamente() {
		assertEquals(TipoOpinion.VINCHUCA_INFESTAN, criterioInsecto.getTipoInsecto());
	}
	
	@Test
	public void testLaMuestraRecibidaCumpleConElCriterioTipoInsecto() {
		when(muestra.getResultadoActual()).thenReturn(TipoOpinion.VINCHUCA_INFESTAN);
		assertTrue(criterioInsecto.cumple(muestra));
	}
	
	@Test
	public void testLaMuestraRecibidaNoCumpleConElCriterioTipoInsecto() {
		when(muestra.getResultadoActual()).thenReturn(TipoOpinion.VINCHUCA_GUASAYANA);
		assertFalse(criterioInsecto.cumple(muestra));
	}
}
