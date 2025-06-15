package criterio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muestra.EstadoMuestraVerificada;
import muestra.EstadoMuestraVerificandose;
import muestra.Muestra;

public class CriterioNivelDeVerificacionTest {
	private EstadoMuestraVerificada nivelVerificacion1;
	private EstadoMuestraVerificandose nivelVerificacion2;
	private CriterioNivelDeVerificacion criterioNivelVerificacion1;
	private CriterioNivelDeVerificacion criterioNivelVerificacion2;
	private Muestra muestra;
	
	@BeforeEach
	public void setUp() {
		nivelVerificacion1 = new EstadoMuestraVerificada();
		nivelVerificacion2 = new EstadoMuestraVerificandose();
		criterioNivelVerificacion1 = new CriterioNivelDeVerificacion(nivelVerificacion1);
		criterioNivelVerificacion2 = new CriterioNivelDeVerificacion(nivelVerificacion2);
		muestra = mock(Muestra.class);
	}
	
	@Test
	public void testGetNivelVerificacionDevuelveElEstadoCorrecto() {
	    assertEquals(nivelVerificacion1, criterioNivelVerificacion1.getNivelVerificacion());
	}

	@Test
	public void testLaMuestraRecibidaCumpleConElCriterioNivelDeVerificacion() {
		when(muestra.getEstadoMuestra()).thenReturn(nivelVerificacion1);
		assertTrue(criterioNivelVerificacion1.cumple(muestra));
	}
	
	@Test
	public void testLaMuestraRecibidaNoCumpleConElCriterioNivelDeVerificacion() {
		when(muestra.getEstadoMuestra()).thenReturn(nivelVerificacion1);
		assertFalse(criterioNivelVerificacion2.cumple(muestra));
	}
}

