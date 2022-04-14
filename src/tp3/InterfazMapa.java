package tp3;

import java.awt.Color;
import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

public class InterfazMapa {

	private JFrame frmMapa;
	private JMapViewer mapa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazMapa window = new InterfazMapa();
					window.frmMapa.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfazMapa() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMapa = new JFrame();
		frmMapa.setTitle("Distribucion Golosa : Mapa ");
		frmMapa.setBounds(100, 100, 713, 499);
		frmMapa.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmMapa.setLocationRelativeTo(null);
		frmMapa.setResizable(false);
		frmMapa.setVisible(true);
		
		mapa = new JMapViewer();
		
		try {
			double cord1long = 0;
			double cord2lat = 0;
			FileInputStream fis = new FileInputStream("Clientes.txt");
			Scanner scanner = new Scanner(fis);
			while(scanner.hasNextLine()) {
				String linea1= scanner.nextLine();
				Scanner contenidoLinea1 = new Scanner(linea1);
				while (contenidoLinea1.hasNextDouble()) {
					contenidoLinea1.nextInt();
					cord1long = contenidoLinea1.nextDouble();
					cord2lat = contenidoLinea1.nextDouble();
                }
				setCordenadaPosicion(cord2lat,cord1long);
				setCordenadaMarcadorCliente(cord2lat,cord1long);
				contenidoLinea1.close();
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			double cord1long = 0;
			double cord2lat = 0;
			FileInputStream fis = new FileInputStream("Resultado.txt");
			Scanner scanner = new Scanner(fis);
			
			while(scanner.hasNextLine()) {
				String linea= scanner.nextLine();
				Scanner contenidoLinea = new Scanner(linea);
				while (contenidoLinea.hasNextDouble()) {
					contenidoLinea.nextInt();
					cord1long = contenidoLinea.nextDouble();
					cord2lat = contenidoLinea.nextDouble();
					contenidoLinea.next();
                }
				setCordenadaMarcadorCentro(cord2lat,cord1long);
				contenidoLinea.close();
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		frmMapa.getContentPane().add(mapa);
	}
	
	public void setCordenadaPosicion(double x, double y) {
		Coordinate c = new Coordinate(x,y);
		mapa.setDisplayPosition(c, 15);
	}
	
	public void setCordenadaMarcadorCliente(double x, double y) {
		Coordinate c = new Coordinate(x,y);
		MapMarker m = new MapMarkerDot("Cliente", c);
		m.getStyle().setBackColor(Color.red);
		m.getStyle().setColor(Color.orange);
		mapa.addMapMarker(m);
	}

	public void setCordenadaMarcadorCentro(double x, double y) {
		Coordinate c = new Coordinate(x,y);
		MapMarker m = new MapMarkerDot("Centro", c);
		m.getStyle().setBackColor(Color.red);
		m.getStyle().setColor(Color.blue);
		mapa.addMapMarker(m);
	}
}
