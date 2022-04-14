package tp3;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AlgoritmoGoloso {
	
	private List<Cliente> clientes = new ArrayList<Cliente>();
	private List<PuntoEnMapa> puntos = new ArrayList<PuntoEnMapa>();
	private List<PuntoEnMapa> distribucionResultado = new ArrayList<PuntoEnMapa>();
	
	public void algoritmoDistribucion(int k) {
		
		verificarEntero(k);
		
		lecturaDatos();
		
		int sumatoria = 0;
		int[] sumas = new int[puntos.size()];
		for(int i=0;i<puntos.size();i++) {
			sumatoria = 0;
			for(int j=0;j<clientes.size();j++) {
				sumatoria+=calcularDistancia(clientes.get(j).longitud,clientes.get(j).latitud,puntos.get(i).longitud,puntos.get(i).latitud);
			}
			puntos.get(i).sumatoriaDistancias = sumatoria;
			sumas[i] = sumatoria;
		}
		Arrays.sort(sumas);
		
		for(int h=0;h<puntos.size();h++) {
			for(int l=0;l<puntos.size();l++) {
				if(puntos.get(l).sumatoriaDistancias == sumas[h]) {
					distribucionResultado.add(puntos.get(l));
				}
			}
		}
		escrituraResultado(k);
	}
	
	 private static int calcularDistancia(double lon1, double lat1,double lon2, double lat2) { // formula haversine

			 double radioTierra = 6371; // kilometros

			 lat1 = Math.toRadians(lat1);
			 lon1 = Math.toRadians(lon1);
			 lat2 = Math.toRadians(lat2);
			 lon2 = Math.toRadians(lon2);

			 double distlongitud = (lon2 - lon1);
			 double distlatitud = (lat2 - lat1);

			 double senlatitud = Math.sin(distlatitud/2);
			 double senlongitud = Math.sin(distlongitud/2);

			 double x = (senlatitud * senlatitud) + Math.cos(lat1)*Math.cos(lat2)*(senlongitud * senlongitud);
			 double y = 2 * Math.asin(Math.min(1.0, Math.sqrt(x)));

			 double distanciaEnMetros = radioTierra * y * 1000;

			 return (int)distanciaEnMetros;
	}
	 
	private void lecturaDatos() { //lee archivos: Clientes y Puntos
		try {
			FileInputStream fis = new FileInputStream("Clientes.txt");
			FileInputStream fis2 = new FileInputStream("Puntos.txt");
			Scanner scanner = new Scanner(fis);
			Scanner scanner2 = new Scanner(fis2);
			
			while(scanner.hasNextLine()) {
				boolean añadido = false;
				String linea= scanner.nextLine();
				Scanner contenidoLinea = new Scanner(linea);
				Cliente c = new Cliente();
				while (contenidoLinea.hasNextDouble()) {
					c.numCliente = contenidoLinea.nextInt();
					c.longitud = contenidoLinea.nextDouble();
					c.latitud = contenidoLinea.nextDouble();
					añadido = true;
                }
				if(añadido) {
					clientes.add(c);
				}
				contenidoLinea.close();
			}
			while(scanner2.hasNextLine()) {
				boolean añadido = false;
				String linea= scanner2.nextLine();
				Scanner contenidoLinea = new Scanner(linea);
				PuntoEnMapa p = new PuntoEnMapa();
				while (contenidoLinea.hasNextDouble()) {
					p.numCentro = contenidoLinea.nextInt();
					p.longitud = contenidoLinea.nextDouble();
					p.latitud = contenidoLinea.nextDouble();
					añadido = true;
                }
				if(añadido) {
					puntos.add(p);
				}
				contenidoLinea.close();
			}
			scanner.close();
			scanner2.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void escrituraResultado(int k) { //escribe resultado en archivo de texto
		try {
			FileOutputStream fos = new FileOutputStream("Resultado.txt");
			OutputStreamWriter out = new OutputStreamWriter(fos);
			
			out.write("Centros mas optimos: (Numero de Centro/longitud/latitud/Promedio Distancias) \n");
			for(int t=0;t<k;t++) {
				double lon = distribucionResultado.get(t).longitud;
				double lat = distribucionResultado.get(t).latitud;
				String str = String.valueOf(lon);
				String str2 = String.valueOf(lat);
				str = str.replace(".", ",");
				str2 = str2.replace(".", ",");
				out.write(distribucionResultado.get(t).numCentro + "  " + str + "  " + str2 + "  " + distribucionResultado.get(t).sumatoriaDistancias + " m " + " \n");
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void verificarEntero(int i){
		if( i < 0 )
			throw new IllegalArgumentException("k no puede ser negativo: " + i);
	}
	
	private void verificarLongitud(double i){
		if( i >= 180 || i <= -180 )
			throw new IllegalArgumentException("longitud debe ser entre 180 y - 180: " + i);
	}
	
	private void verificarLatitud(double i){
		if(i >= 90 || i <= -90 )
			throw new IllegalArgumentException("Latitud debe ser entre 90 y -90: " + i);
	}
	
	public void agregarPunto(double lon,double lat) {
		PuntoEnMapa p = new PuntoEnMapa();
		verificarLongitud(lon);
		verificarLatitud(lat);
		puntos.add(p);
	}
	
	public void agregarCliente(double lon,double lat) {
		Cliente c = new Cliente();
		verificarLongitud(lon);
		verificarLatitud(lat);
		clientes.add(c);
	}

}
