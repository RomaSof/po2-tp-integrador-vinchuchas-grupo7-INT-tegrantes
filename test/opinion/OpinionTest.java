package opinion;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import muestra.Muestra;
import ubicacion.Ubicacion;
import usuario.Usuario;
import usuario.UsuarioVerificado;

class OpinionTest {
	private Usuario usuario;
	private Date date;
	private Ubicacion ubicacion;
	private LocalDate localDate;
	private Opinion opinion;
	private Muestra muestra;
	
	@BeforeEach
	public void setup() {
		 localDate = LocalDate.now();
		    date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		    
		    usuario = Mockito.spy(new Usuario("Jose"));
		    muestra = Mockito.spy( usuario.enviarMuestra( ubicacion, "imagen.jpg" , TipoOpinion.VINCHUCA_SORDIDA));
		    
	    
	}
	//test n°1
	@Test
	void testCreacionMuestra() {
		assertTrue(muestra.getRecolectorMuestra().equals(usuario));
		assertEquals(muestra.getHistorialDeOpiniones().size(), 0);
		assertTrue(muestra.getEspecie().equals(TipoOpinion.VINCHUCA_SORDIDA.getEspecie()));
	}
	//test n°2
	
	@Test
	void testEnviarOpinion() {
		Usuario usuario2 = Mockito.spy(new Usuario("Raul"));
		Opinion op2 = Mockito.spy(new Opinion(usuario2, TipoOpinion.CHINCHE_FOLIADA , new Date()));
		muestra.agregarOpinion(op2);
		assertEquals(muestra.getHistorialDeOpiniones().size(), 1);
		
	}
	//test n°3
	@Test
	void testUsuarioNoPuedeEnviar2OpinionesAUnaMuestra() {
		Usuario usuario2 = Mockito.spy(new Usuario("Raul"));
		Opinion op2 = Mockito.spy(new Opinion(usuario2, TipoOpinion.CHINCHE_FOLIADA , new Date()));
		muestra.agregarOpinion(op2); // envio la opinion
		assertEquals(muestra.getHistorialDeOpiniones().size(), 1);
		Opinion op3 = Mockito.spy(new Opinion(usuario2, TipoOpinion.CHINCHE_PHTIA, new Date()));
		muestra.agregarOpinion(op3);
		assertEquals(muestra.getHistorialDeOpiniones().size(), 1);
	}/*
	//test n°4
	@Test
	void testUsuarioEnviaOpionesAMasDeUnaMuestra() {
		Usuario usuario2 = Mockito.spy(new Usuario("Raul"));
		Opinion op2 = Mockito.spy(new Opinion(usuario2, TipoOpinion.CHINCHE_FOLIADA , new Date()));
		op2.enviarOpinion(muestra); // envio la opinion
		assertEquals(muestra.getHistorialDeOpiniones().size(), 2);
		
		// muestra 2
	    Usuario usuario3 = Mockito.spy(new Usuario("Juan"));
	    Opinion opinionUser3 = Mockito.spy(new Opinion(usuario3, TipoOpinion.VINCHUCA_GUASAYANA, new Date()));
	    Muestra muestra2 = Mockito.spy(new Muestra(usuario3, new Date(), "imagen.jpg" , opinionUser3));
	    
	    assertEquals(muestra2.getHistorialDeOpiniones().size() , 1);
	    
	    Opinion op3User2 = Mockito.spy(new Opinion(usuario2, TipoOpinion.VINCHUCA_GUASAYANA, new Date()));
	    op3User2.enviarOpinion(muestra2);
	    
	    assertEquals(muestra2.getHistorialDeOpiniones().size(), 2);
	    assertEquals(usuario2.getCantidadDeRevisionesUltimos30Dias() , 2);
	    assertEquals(usuario2.getCantidadDeEnviosUltimos30Dias() , 0);
	    assertEquals(usuario2.getOpiniones().size(), 2);
	    assertEquals(usuario2.getMuestras().size(), 0);
		
	}
	//test n°5
	@Test
	void testearSiEsOpinionVerificada() {
		assertFalse(opinion.esOpinionVerificada());
		Usuario usuario2 = Mockito.spy(new UsuarioVerificado("Juan"));
		Opinion op2 = new Opinion(usuario2, TipoOpinion.VINCHUCA_SORDIDA, new Date());
		assertTrue(op2.esOpinionVerificada());
	}
	*/
}
