package avisoOrganizaciones;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

import muestra.*;
import opinion.TipoOpinion;
import organizacion.FuncionalidadExterna;
import organizacion.Organizacion;
import organizacion.TipoOrganicacion;
import ubicacion.Ubicacion;
import usuario.Usuario;
import zonaCobertura.ZonaDeCobertura;

class observadorMuestraTest {

    private ObservadorMuestra obsMuestra;
    private FuncionalidadExterna funcionalidadMock;
    
    @Spy private Muestra muestraDentroZona1;
    @Spy private Muestra muestraDentroZona2;
    @Spy private Muestra muestraDentroZona3;
    @Spy private Muestra muestraFueraDeZona1;
    
    private Usuario usuarioMock;
    @Spy private ZonaDeCobertura zona1;
    @Spy private ZonaDeCobertura zona3;

    @BeforeEach
    void setUp() {
    	
        funcionalidadMock = mock(FuncionalidadExterna.class);
        
        usuarioMock = mock(Usuario.class);
        
        obsMuestra = new ObservadorMuestra();

        Organizacion orga1 = new Organizacion(TipoOrganicacion.SALUD, 
            new Ubicacion(-34.6037, -58.3816), funcionalidadMock, funcionalidadMock);
        Organizacion orga3 = new Organizacion(TipoOrganicacion.CULTURAL, 
            new Ubicacion(-31.4167, -64.1833), funcionalidadMock, funcionalidadMock);

        zona1 = spy(new ZonaDeCobertura("Zona CABA", new Ubicacion(-34.6037, -58.3816), 50, new ArrayList<>()));
        
        zona3 = spy(new ZonaDeCobertura("Zona Córdoba", new Ubicacion(-31.4167, -64.1833), 60, new ArrayList<>()));
        
        obsMuestra.addZonaDeCobertura(zona1);
        obsMuestra.addZonaDeCobertura(zona3);
        
        zona1.agregarObservador(orga1);
        zona3.agregarObservador(orga1);
        zona3.agregarObservador(orga3);

        muestraDentroZona1 = spy(new Muestra(usuarioMock, new Date(), 
            new Ubicacion(-34.6083, -58.3712), "foto1.jpg", TipoOpinion.NO_DEFINIDA));
        muestraDentroZona2 = spy(new Muestra(usuarioMock, new Date(),
            new Ubicacion(-34.8591, -58.0137), "foto3.jpg", TipoOpinion.NO_DEFINIDA));
        muestraDentroZona3 = spy(new Muestra(usuarioMock, new Date(),
            new Ubicacion(-31.4208, -64.4992), "foto5.jpg", TipoOpinion.NO_DEFINIDA));
        muestraFueraDeZona1 = spy(new Muestra(usuarioMock, new Date(), 
        		new Ubicacion(-54.8019, -68.3030), "foto1.jpg", TipoOpinion.NO_DEFINIDA));
    }

    @Test
    void testCantidadDeZonasSubscritasAObserver() {
        assertEquals(2, obsMuestra.getZonasDeCoberturasSubscritas().size());
    }
    
    @Test
    void testAgregarZonaDeCoberturaAObserver(){
        ZonaDeCobertura zonaMock = mock(ZonaDeCobertura.class);
        obsMuestra.addZonaDeCobertura(zonaMock);
        assertEquals(3, obsMuestra.getZonasDeCoberturasSubscritas().size());
    }
    
    @Test
    void testNoSePuedeAgregar2VecesLaMismaZonaDeCobertura() {
        assertEquals(2, obsMuestra.getZonasDeCoberturasSubscritas().size());
        obsMuestra.addZonaDeCobertura(zona3);
        assertEquals(2, obsMuestra.getZonasDeCoberturasSubscritas().size());
    }
    
    @Test
    void testEliminarUnaZonaDeCovertura() {
        assertTrue(obsMuestra.getZonasDeCoberturasSubscritas().contains(zona3));
        obsMuestra.eliminarZonaDeCobertura(zona3);
        assertFalse(obsMuestra.getZonasDeCoberturasSubscritas().contains(zona3));
    }
    
    @Test
    void testNotificarMuestraRecienCreada() {
        ZonaDeCobertura zonaMock = mock(ZonaDeCobertura.class);
        when(zonaMock.estaDentroDeLaZona(any())).thenReturn(true);
        
        ObservadorMuestra obsLocal = new ObservadorMuestra();
        obsLocal.addZonaDeCobertura(zonaMock);
        
        muestraDentroZona1.setObservador(obsLocal); // <-- Agregado para que Muestra tenga un ObservadorMuestra (inicialmente es null)

        muestraDentroZona1.notificarMuestra();
        
        verify(zonaMock).notificarNuevaMuestra(muestraDentroZona1);
    }
    
    @Test
    void testNotificarMuestraValidada() {
        ZonaDeCobertura zonaMock = mock(ZonaDeCobertura.class); //crea una nueva zona de cobertura
        when(zonaMock.estaDentroDeLaZona(any())).thenReturn(true);
        
        ObservadorMuestra obsLocal = new ObservadorMuestra(); //la agrega a un observador
        obsLocal.addZonaDeCobertura(zonaMock);

        
        Muestra muestraValidada = mock(Muestra.class);
        when(muestraValidada.getUbicacion()).thenReturn(new Ubicacion(-34.6083, -58.3712));
        

        // Una muestra tiene un ObservadorMuestra inicialmente null
        muestraValidada.setObservador(obsLocal); // <-- Se setea el observador necesario para que la muestra pueda notificar su estado
        
        
        //notifica una muestra validada
        obsLocal.notificarMuestraValidada(muestraValidada);
        
        //se llama a la notificacion de muestra validada no de muestra nueva
        verify(zonaMock).notificarMuestraValida(muestraValidada); 
        verify(zonaMock, never()).notificarNuevaMuestra(any()); 
    }
    
    @Test
    void testNotificarMuestraValidadaQueNoEstaEnZona() {
    	obsMuestra.notificarMuestraValidada(muestraFueraDeZona1);
    	
    	verify(zona1, never()).notificarMuestraValida(muestraFueraDeZona1); 
        
        verify(zona3, never()).notificarMuestraValida(muestraFueraDeZona1); 
    }

}