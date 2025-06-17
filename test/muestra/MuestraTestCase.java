package muestra;

import org.junit.jupiter.api.BeforeEach;

import usuario.Usuario;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import opinion.Opinion;
import opinion.TipoOpinion;

class MuestraTestCase {
	//SUT
	public Muestra muestra;
	
	//DOCS
	public Opinion op;
	public Opinion op1;
	public Opinion op2;
	public Opinion op3;
	public Opinion op4;
	public Opinion op5;
	public Opinion op6;
	
	public Usuario user;
	public Usuario normal1;
	public Usuario normal2;
	public Usuario normal3;
	public Usuario experto1;
	public Usuario experto2;
	public Usuario experto3;
	
	LocalDate hoy = LocalDate.now();
	
	@BeforeEach
	public void setUp() {
		//users
		user = mock(Usuario.class);
		normal1 = mock(Usuario.class);
		normal2 = mock(Usuario.class);
		normal3 = mock(Usuario.class);
		experto1 = mock(Usuario.class);
		experto2 = mock(Usuario.class);
		experto3 = mock(Usuario.class);
		//esExperto
		when(user.esExperto()).thenReturn(false);
		when(normal1.esExperto()).thenReturn(false);
		when(normal2.esExperto()).thenReturn(false);
		when(normal3.esExperto()).thenReturn(false);
		when(experto1.esExperto()).thenReturn(true);
		when(experto2.esExperto()).thenReturn(true);
		when(experto3.esExperto()).thenReturn(true);
		
		
		//opinions
		op = mock(Opinion.class);
		op1 = mock(Opinion.class);
		op2 = mock(Opinion.class);
		op3 = mock(Opinion.class);
		op4 = mock(Opinion.class);
		op5 = mock(Opinion.class);
		op6 = mock(Opinion.class);
		//getTipoOpinion
		when(op.getTipoEspecie()).thenReturn(TipoOpinion.IMAGEN_POCO_CLARA); 
		when(op1.getTipoEspecie()).thenReturn(TipoOpinion.VINCHUCA_SORDIDA);
		when(op2.getTipoEspecie()).thenReturn(TipoOpinion.CHINCHE_FOLIADA);
		when(op3.getTipoEspecie()).thenReturn(TipoOpinion.CHINCHE_FOLIADA);
		when(op4.getTipoEspecie()).thenReturn(TipoOpinion.VINCHUCA_SORDIDA);
		when(op5.getTipoEspecie()).thenReturn(TipoOpinion.CHINCHE_FOLIADA);
		when(op6.getTipoEspecie()).thenReturn(TipoOpinion.CHINCHE_FOLIADA);
		//esExperto
		when(op.esOpinionVerificada()).thenReturn(false);
		when(op1.esOpinionVerificada()).thenReturn(false);
		when(op2.esOpinionVerificada()).thenReturn(false);
		when(op3.esOpinionVerificada()).thenReturn(false);
		when(op4.esOpinionVerificada()).thenReturn(true);
		when(op5.esOpinionVerificada()).thenReturn(true);
		when(op6.esOpinionVerificada()).thenReturn(true);
		//getUsuario
		when(op.getUsuario()).thenReturn(user);
		when(op1.getUsuario()).thenReturn(normal1);
		when(op2.getUsuario()).thenReturn(normal2);
		when(op3.getUsuario()).thenReturn(normal3);
		when(op4.getUsuario()).thenReturn(experto1);
		when(op5.getUsuario()).thenReturn(experto2);
		when(op6.getUsuario()).thenReturn(experto3);
		
		muestra = new Muestra(user, hoy, null, "image", TipoOpinion.IMAGEN_POCO_CLARA);
	}

	@Test
	public void TestEstadoVerificacionMuestra() {
		assertTrue(muestra.getEstadoMuestra() instanceof EstadoVerificacionMuestra);
		
		//
		muestra.agregarOpinion(op1);
		muestra.agregarOpinion(op2);
		muestra.agregarOpinion(op3);
		
		//
		assertEquals(4, muestra.getHistorialDeOpiniones().size());
		assertTrue(muestra.getEstadoMuestra() instanceof EstadoVerificacionMuestra);
		assertFalse(muestra.esVerificada());
		assertFalse(muestra.coincidenExpertos());
		assertTrue(muestra.getOpinionesDeExpertos().isEmpty());
		assertEquals(TipoOpinion.CHINCHE_FOLIADA.getEspecie(), muestra.getEspecie());
		
	}
	
	@Test
	public void TestEstadoMuestraVerificandose() {
		assertTrue(muestra.getEstadoMuestra() instanceof EstadoVerificacionMuestra);
		
		//
		muestra.agregarOpinion(op1);
		muestra.agregarOpinion(op2);
		
		assertTrue(muestra.getEstadoMuestra() instanceof EstadoVerificacionMuestra);
		
		//
		muestra.agregarOpinion(op4);
		
		//
		assertEquals(4, muestra.getHistorialDeOpiniones().size());
		assertTrue(muestra.getEstadoMuestra() instanceof EstadoMuestraVerificandose);
		assertFalse(muestra.esVerificada());
		assertFalse(muestra.coincidenExpertos());
		assertEquals(1, muestra.getOpinionesDeExpertos().size());
		assertEquals(TipoOpinion.NO_DEFINIDA.getEspecie(), muestra.getEspecie());
		
	}
	
	@Test
	public void TestEstadoMuestraVerificada() {
		assertTrue(muestra.getEstadoMuestra() instanceof EstadoVerificacionMuestra);
		muestra.agregarOpinion(op1);
		muestra.agregarOpinion(op2);
		muestra.agregarOpinion(op4);
		assertTrue(muestra.getEstadoMuestra() instanceof EstadoVerificacionMuestra);
		assertEquals(TipoOpinion.NO_DEFINIDA.getEspecie(), muestra.getEspecie());
		
		//
		muestra.agregarOpinion(op5);
		
		assertTrue(muestra.getEstadoMuestra() instanceof EstadoVerificacionMuestra);
		assertFalse(muestra.esVerificada());
		assertFalse(muestra.coincidenExpertos());
		assertEquals(2, muestra.getOpinionesDeExpertos().size());
		assertEquals(TipoOpinion.NO_DEFINIDA.getEspecie(), muestra.getEspecie());
		
		//
		muestra.agregarOpinion(op5);
		
		assertEquals(6, muestra.getHistorialDeOpiniones().size());
		assertTrue(muestra.getEstadoMuestra() instanceof EstadoMuestraVerificada);
		assertTrue(muestra.esVerificada());
		assertTrue(muestra.coincidenExpertos());
		assertEquals(3, muestra.getOpinionesDeExpertos().size());
		assertEquals(TipoOpinion.CHINCHE_FOLIADA.getEspecie(), muestra.getEspecie());
	}
  
}
