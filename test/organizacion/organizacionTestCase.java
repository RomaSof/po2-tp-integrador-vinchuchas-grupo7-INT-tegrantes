package organizacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muestra.Muestra;
import ubicacion.Ubicacion;
import usuario.Usuario;
import zonaCobertura.ZonaDeCobertura;

class organizacionTestCase {
	//SUT
	Organizacion organizacion;
	
	//DOCS
	//ubicacion de la organizacion
	Ubicacion ubicacion;
	
	//zona a la que se suscribe la organizacion
	ZonaDeCobertura zona;

	//funcionanlidades de la organizacion para validacion y nuevas muestras
	FuncionalidadExterna funcionalidadCargas;
	FuncionalidadExterna funcionalidadValidaciones;
	
	//trabajadores de la organizacion
	Usuario u1;
	Usuario u2;
	
	Muestra m;

@BeforeEach
	public void setup() {
	//mock de ubicacion
	ubicacion = mock(Ubicacion.class);
	//mock de las funcionalidades
	funcionalidadCargas = mock(FuncionalidadExterna.class);
	funcionalidadValidaciones = mock(FuncionalidadExterna.class);
	//mock de los trabajadores de la empresa
	u1 = mock(Usuario.class);
	u2 = mock(Usuario.class);
	//mock de muestra
	m = mock(Muestra.class);
	
	//organizacion
	organizacion = new Organizacion(TipoOrganicacion.SALUD, ubicacion, funcionalidadCargas, funcionalidadValidaciones);
	
}
	//TEST GETTERS
	@Test //verifica que devuelva la ubicacion de la organizacion
	void getUbicacionTest() {
		assertEquals(ubicacion, organizacion.getUbicacion());
	}
	
	@Test //verifica que devuelva el tipo de organizacion
	void getTipoOrganizacionTest() {
		assertEquals(TipoOrganicacion.SALUD, organizacion.getTipoOrganizacion());
	}
	
	@Test //verifica que devuelva la cantidad total de trabajadores en la organizacion
	void getCantTrabajadoresTest() {
		assertEquals(0, organizacion.getCantTrabajadores());
		organizacion.agregarEmpleado(u1);
		organizacion.agregarEmpleado(u2);
		assertEquals(2, organizacion.getCantTrabajadores());
	}
	
	@Test //verifica que devuelva una lista de emppleados en la organizacion
	void getEmpleadosTest() {
		List<Usuario> emps = new ArrayList<Usuario>(Arrays.asList(u1, u2));
		//agrega dos empleados a la organizacion
		organizacion.agregarEmpleado(u1);
		organizacion.agregarEmpleado(u2);
		//y verifica que tenga esos dos empleados
		assertEquals(emps, organizacion.getEmpleados());
	}
	
	@Test //verifica que devuelva la funcionalidad externa para cuando se cargan nuevas muestras
	void getFuncionalidadExternaCargaTest() {
		assertEquals(funcionalidadCargas, organizacion.getFuncionalidadExternaCarga());
	}
	
	@Test //verifica que devuelva la funcionalidad externa para cuando se valida una muestra
	void getFuncionalidadExternaValidacionTest() {
		assertEquals(funcionalidadValidaciones, organizacion.getFuncionalidadExternaValidacion());
	}
	
	//TESTS SETTERS
	@Test //verifica que agregue bien una nueva funcionalidad externa para cuando se cargan nuevas muestras
	void setFuncionalidadExternaCargaTest() {
		FuncionalidadExterna funcionalidad3 = mock(FuncionalidadExterna.class);
		organizacion.setFuncionalidadExternaCarga(funcionalidad3);
		assertEquals(funcionalidad3, organizacion.getFuncionalidadExternaCarga());
	}
	@Test //verifica que agregue bien una nueva funcionalidad externa para cuando se valida una muestra
	void setFuncionalidadExternaValidacionTest() {
		FuncionalidadExterna funcionalidad4 = mock(FuncionalidadExterna.class);
		organizacion.setFuncionalidadExternaValidacion(funcionalidad4);
		assertEquals(funcionalidad4, organizacion.getFuncionalidadExternaValidacion());
	}
	
	//TEST METHODS
	@Test //verifica que agregue correctamente lso empleados a la organizacion
	void agregarEmpleadoTest() {
		assertTrue(organizacion.getEmpleados().isEmpty());
		organizacion.agregarEmpleado(u1);
		assertEquals(1, organizacion.getCantTrabajadores());
		assertTrue(organizacion.getEmpleados().contains(u1));
	}
	
	//verifica que la empresa accione con su funcionalidad correspondiente al resivir le aviso
	
	@Test 
	void nuevaMuestraTest() {
		assertEquals(funcionalidadCargas, organizacion.getFuncionalidadExternaCarga());
		organizacion.nuevaMuestra(zona, m);
		verify(funcionalidadCargas, times(1)).nuevoEvento(organizacion, zona, m);
	}
	
	@Test
	void muestraValidadaTest() {
		assertEquals(funcionalidadValidaciones, organizacion.getFuncionalidadExternaValidacion());
		organizacion.muestraValidada(zona, m);
		verify(funcionalidadValidaciones, times(1)).nuevoEvento(organizacion, zona, m);
	}

}