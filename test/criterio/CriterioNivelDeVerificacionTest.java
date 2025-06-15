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

public class CriterioNivelDeVerificacionTest {
	private String nivelVerificacion;
	private CriterioNivelDeVerificacion criterioNivelVerificacion;
	private Muestra muestra;
	
	@BeforeEach
	public void setUp() {
		nivelVerificacion = "Verificada";
		criterioNivelVerificacion = new CriterioNivelDeVerificacion(nivelVerificacion);
		muestra = mock(Muestra.class);
	}
	
	@Test
	public void testCriterioNivelDeVerificacionSeInicializaCorrectamente() {
		assertEquals("Verificada", criterioNivelVerificacion.getEstadoMuestra());
	}
	
	@Test
	public void testLaMuestraRecibidaCumpleConElCriterioNivelDeVerificacion() {
		when(muestra.getEstadoMuestra().getNombreEstado()).thenReturn("Verificada"); // Agregar getNombreEstado() en los estados de las muestras para correr el test.
		assertTrue(criterioNivelVerificacion.cumple(muestra));
	}
	
	@Test
	public void testLaMuestraRecibidaNoCumpleConElCriterioNivelDeVerificacion() {
		when(muestra.getResultadoActual().getNombreEstado()).thenReturn("Verificandose");
		assertFalse(criterioNivelVerificacion.cumple(muestra));
	}
}

