package usuario;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import muestra.Muestra;
import opinion.Opinion;
import opinion.TipoOpinion;
import ubicacion.Ubicacion;
import usuarioEstado.EstadoUsuarioExperto;


class UsuarioTest {
	
	private Usuario usuario1;
	private Usuario usuario2; // los tests se basan en este usuario, el primero es para instanciar una muestra
	private Usuario usuario3;// Mock de usuario
	private Usuario usuario4;// usuario spy verificado
	private Opinion opinion;
	private Opinion opinion2;
	private Muestra muestra;
	private Date date;
	private LocalDate localDate;
	private TipoOpinion vinchuca;
	private Ubicacion ubicacion;

	@BeforeEach
	public void setup() {
		//localDate = LocalDate.now().minusDays(1);
		//date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		localDate = LocalDate.now();
		date = new Date();
	    vinchuca = TipoOpinion.VINCHUCA_INFESTAN; 
		usuario1 = new Usuario("Juan");
		usuario2 = new Usuario("Pepe");
		ubicacion = mock(Ubicacion.class);
		
		//instancia de un mock usuario llamado Jose(usuario basico)
		
		usuario3 = Mockito.spy(new Usuario("Jose"));
		
		//instancia espia Verificado
		
		usuario4 = Mockito.spy(new UsuarioVerificado("Alberto"));
	    
	    opinion = new Opinion(usuario1, vinchuca , localDate);
	   
	    muestra = new Muestra(usuario1, localDate , ubicacion, "image", vinchuca);
	    
		
	}

	// se deberia mockear para testear mejor
	//@Test
	void testCrearMuestra() {
		assertTrue(muestra.getRecolectorMuestra().equals(usuario1));
		assertEquals(muestra.getEspecie(), TipoOpinion.VINCHUCA_INFESTAN.getEspecie());
		
		// Verifica que la muestra fue registrada en el usuario
	    assertEquals(1, usuario1.getMuestras().size());
	    assertEquals(1, usuario1.getCantidadDeEnviosUltimos30Dias());
	    assertEquals(1, usuario1.getCantidadDeRevisionesUltimos30Dias());
	    
	}
	
	@Test
	void testGetterNombre() {
		assertTrue(usuario1.getNombre().equals("Juan"));
	}
	
	// se deberia mockear para testear mejor
	//@Test
	void testEstadoUsuarioBasico() {
		assertFalse(usuario2.esExperto());
		opinion2 = new Opinion(usuario2, TipoOpinion.CHINCHE_FOLIADA, localDate);
		usuario2.opinar(muestra, vinchuca);
		//opinion2.enviarOpinion(muestra);
		assertEquals(usuario2.getOpiniones().size() , (1));
		assertEquals(muestra.getHistorialDeOpiniones().size() , (2)); // se agregó correctamente la opinion.
		usuario2.actualizarEstado();// no debe actualizar el estado porque no cumple con requisitos
		assertFalse(usuario2.esExperto());
	}
	
	@Test
	void testPasajeDeUsuarioBasicoAExperto() {
		assertFalse(usuario3.esExperto()); //inicia como usuario basico
		when(usuario3.getCantidadDeEnviosUltimos30Dias()).thenReturn(50);
		when(usuario3.getCantidadDeRevisionesUltimos30Dias()).thenReturn(70);
		//si se consulta su estado debe de estar en basico ya que no se llamo al metodo para actualizar su estado
		assertFalse(usuario3.esExperto());// sigue en estado de UsuarioBasico
		usuario3.actualizarEstado();
		assertTrue(usuario3.esExperto());// se actualiza su estado a experto ya que cumple con los requisitos (10 envios, 20 revisiones)
	}
	
	@Test
	void testPasajeDeUsuarioExpertoABasico() {
		when(usuario3.getCantidadDeEnviosUltimos30Dias()).thenReturn(50); //
		when(usuario3.getCantidadDeRevisionesUltimos30Dias()).thenReturn(70); //
		usuario3.actualizarEstado(); // agregamos los datos necesarios para hacerlo experto
		assertTrue(usuario3.esExperto()); // inicia como usuario experto
		when(usuario3.getCantidadDeEnviosUltimos30Dias()).thenReturn(8);
		when(usuario3.getCantidadDeRevisionesUltimos30Dias()).thenReturn(11);
		usuario3.actualizarEstado();
		assertFalse(usuario3.esExperto());// estado actualizado a basico ya que no cumple con los requisitos para seguir siendo experto
	}
	
	@Test
	void testActualizarEstadoDeExpertoQueSigueCumpliendoLosRequisitos() {
		when(usuario3.getCantidadDeEnviosUltimos30Dias()).thenReturn(50); //
		when(usuario3.getCantidadDeRevisionesUltimos30Dias()).thenReturn(70); //
		usuario3.actualizarEstado(); // agregamos los datos necesarios para hacerlo experto
		assertTrue(usuario3.esExperto());
		when(usuario3.getCantidadDeEnviosUltimos30Dias()).thenReturn(20); // sigue cumpliendo
		when(usuario3.getCantidadDeRevisionesUltimos30Dias()).thenReturn(30); // sigue cumpliendo
		usuario3.actualizarEstado();
		assertTrue(usuario3.esExperto());// sigue siendo experto aunque haya bajado la cantidad de revisiones y envios
		
	}
	
	@Test
	void testearEstadoDeUsuarioVerificado() {
		assertTrue( usuario4.esExperto() ); //es expertoDesdeElPrincipio
		when(usuario4.getCantidadDeEnviosUltimos30Dias()).thenReturn(2);
		when(usuario4.getCantidadDeRevisionesUltimos30Dias()).thenReturn(2);
		usuario4.actualizarEstado();
		//no debe actualizar el estado a basico
		assertTrue(usuario4.esExperto());
		assertFalse(usuario4.getEstadoUsuario().verificarCambioDeEstado(usuario4)); // siempre devuelve false
	}
	
	@Test
	void testOpinionFueraDePeriodo30Dias() {
	    // Crear un usuario NUEVO para este test (evita contaminación del setup)
	    Usuario usuarioTest = new Usuario("UsuarioTest");
	    
	    LocalDate fechaAntigua = LocalDate.now().minusDays(31);
	    //Date fechaOpinionAntigua = Date.from(fechaAntigua.atStartOfDay(ZoneId.systemDefault()).toInstant());
	    
	    Opinion opinionAntigua = new Opinion(usuarioTest, TipoOpinion.VINCHUCA_INFESTAN, fechaAntigua);
	    
	    usuarioTest.addOpinion(opinionAntigua);
	    
	    assertEquals(1, usuarioTest.getOpiniones().size()); // Solo la opinión antigua
	    assertEquals(0, usuarioTest.getCantidadDeRevisionesUltimos30Dias());
	}
	

	//@Test
	void testMuestraFueraDePeriodo30Dias() {
	    Usuario usuarioTest = new Usuario("UsuarioMuestraTest");
	    
	    LocalDate fechaAntigua = LocalDate.now().minusDays(35);
	    Date fechaMuestraAntigua = Date.from(fechaAntigua.atStartOfDay(ZoneId.systemDefault()).toInstant());
	    
	    new Muestra(
	        usuarioTest,
	        fechaAntigua,
	        ubicacion,
	        "imagen_antigua",
	        vinchuca
	        //fechaMuestraAntigua, 
	        
	        //new Opinion(usuarioTest, TipoOpinion.CHINCHE_FOLIADA, fechaMuestraAntigua)
	    );
	    
	    assertEquals(1, usuarioTest.getMuestras().size()); // Ahora pasará
	    assertEquals(0, usuarioTest.getCantidadDeEnviosUltimos30Dias());
	}
	
	@Test
	void testOpinionVerificadaALahoraDeOpinarPeroDespuesElUsuarioPasaAEstadoBasico() {
	    // 1. Configurar usuario como experto inicialmente
	    Usuario usuarioExperto = new Usuario("Carlos");
	    usuarioExperto.setEstado(new EstadoUsuarioExperto());
	    assertTrue(usuarioExperto.esExperto());
	    
	    // 2. Crear opinión (debería ser verificada automáticamente por ser experto)
	    
	    LocalDate now = LocalDate.now();
	    
	    Opinion opinion = new Opinion(usuarioExperto, TipoOpinion.VINCHUCA_INFESTAN, now);
	    assertTrue(opinion.esOpinionVerificada());
	    
	    // 3. Cambiar el usuario a básico (ya que no cumple requisitos)
	    usuarioExperto.actualizarEstado();
	    assertFalse(usuarioExperto.esExperto());
	    
	    // 4. Verificar que la opinión sigue siendo verificada
	    assertTrue(opinion.esOpinionVerificada(), 
	        "La opinión debe mantenerse verificada aunque el usuario ya no sea experto");
	    
	    // 5. Verificación adicional: crear nueva opinión (ahora debería ser no verificada)
	    Opinion nuevaOpinion = new Opinion(usuarioExperto, TipoOpinion.CHINCHE_FOLIADA, now);
	    assertFalse(nuevaOpinion.esOpinionVerificada(),
	        "Las nuevas opiniones no deberían ser verificadas si el usuario es básico");
	}
	
}
