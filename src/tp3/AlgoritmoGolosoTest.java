package tp3;

import static org.junit.Assert.*;

import org.junit.Test;

public class AlgoritmoGolosoTest {

	@Test(expected = IllegalArgumentException.class)
	public void testNegativoAlgoritmo() {
		AlgoritmoGoloso a = new AlgoritmoGoloso();
		a.algoritmoDistribucion(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAgregarClienteLatitudSupera() {
		AlgoritmoGoloso a = new AlgoritmoGoloso();
		a.agregarCliente(20,91);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAgregarPuntoLatitudSupera() {
		AlgoritmoGoloso a = new AlgoritmoGoloso();
		a.agregarPunto(21,91);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAgregarClienteLongitudSupera() {
		AlgoritmoGoloso a = new AlgoritmoGoloso();
		a.agregarCliente(190,50);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAgregarPuntoLongitudSupera() {
		AlgoritmoGoloso a = new AlgoritmoGoloso();
		a.agregarPunto(50,190);
	}
	
	@Test
	public void testAlgoritmo() {
		AlgoritmoGoloso a = new AlgoritmoGoloso();
		a.algoritmoDistribucion(2);
	}
	
	@Test
	public void testAgregarCliente() {
		AlgoritmoGoloso a = new AlgoritmoGoloso();
		a.agregarCliente(21, 45);
	}
	
	@Test
	public void testAgregarPunto() {
		AlgoritmoGoloso a = new AlgoritmoGoloso();
		a.agregarPunto(78, 12);
	}

}
