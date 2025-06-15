package criterio;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muestra.Muestra;
import opinion.TipoOpinion;

public class OperadorOrTest {
	private OperadorOr operadorOr;
	private Muestra muestra;
	private List<CriterioBusqueda> criterios;
	
	private TipoOpinion tipoOpinion;
	private CriterioTipoInsecto criterio1;
	
	private CriterioFechaCreacion criterio2;
	private Date filtroDeFecha;
	private FiltroPorFechaMayor filtroFechaMayor;
	
	@BeforeEach
	public void setUp() {
		muestra = mock(Muestra.class);
		
		tipoOpinion = TipoOpinion.VINCHUCA_INFESTAN;
		criterio1 = new CriterioTipoInsecto(tipoOpinion);
		
		filtroDeFecha = new Date(2000-12-31);
		filtroFechaMayor = new FiltroPorFechaMayor();
		criterio2 = new CriterioFechaCreacion(filtroDeFecha, filtroFechaMayor);
		
		criterios = List.of(criterio1, criterio2);
		operadorOr = new OperadorOr();
	}
	
	// Cumple al menos un criterio: cumple con la fecha de creacion
	@Test
	public void testOperadorAndComparaCorrectamente() {
		when(muestra.getFechaCreacion()).thenReturn(new Date(2001-00-01));
		when(muestra.getResultadoActual()).thenReturn(TipoOpinion.VINCHUCA_GUASAYANA);
		assertTrue(operadorOr.comparar(muestra, criterios));
		assertTrue(criterio2.cumple(muestra));
		assertFalse(criterio1.cumple(muestra));
	}
	
	// No cumple ningun criterio
	@Test
	public void testOperadorAndNoComparaCorrectamente() {
		when(muestra.getFechaCreacion()).thenReturn(new Date(2000-12-31));
		when(muestra.getResultadoActual()).thenReturn(TipoOpinion.VINCHUCA_SORDIDA);
		assertFalse(operadorOr.comparar(muestra, criterios));
		assertFalse(criterio1.cumple(muestra));
		assertFalse(criterio2.cumple(muestra));
	}
	
}
