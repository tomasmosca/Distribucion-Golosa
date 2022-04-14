package tp3;

import java.awt.Color;


import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class InterfazPrincipal {

	private JFrame frmDistribucionGolosa;
	private JTable tablaClientes;
	private JTable tablaPuntos;
	private JTextField txtFieldLatitud;
	private JTextField txtFieldLongitud;
	private InterfazSecundaria frame2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazPrincipal window = new InterfazPrincipal();
					window.frmDistribucionGolosa.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfazPrincipal() {
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
		System.out.println("Distribucion Golosa V1.0 - Tomas Moscarelli");
		frmDistribucionGolosa = new JFrame();
		frmDistribucionGolosa.setTitle("Distribucion Golosa V.1.0");
		frmDistribucionGolosa.setBounds(100, 100, 912, 573);
		frmDistribucionGolosa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDistribucionGolosa.setLocationRelativeTo(null);
		frmDistribucionGolosa.getContentPane().setLayout(null);
		frmDistribucionGolosa.setResizable(false);
		
		JSpinner spnCentrosAbrir = new JSpinner();
		spnCentrosAbrir.setModel(new SpinnerNumberModel(1,  1, null, 1));
		spnCentrosAbrir.setBounds(794, 128, 67, 20);
		frmDistribucionGolosa.getContentPane().add(spnCentrosAbrir);
		
		JButton btnBuscarCentrosPara = new JButton("Buscar Centros para Abrir");
		btnBuscarCentrosPara.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fila = tablaClientes.getRowCount();
				int fila2 = tablaPuntos.getRowCount();
				int kCentros = (int) spnCentrosAbrir.getValue();
				if(fila >= 1 && fila2 >=2) {
					if(kCentros <= fila2) {
						//obtener datos de la tabla y ponerlos en un archivo
						try
						{
							FileOutputStream fos = new FileOutputStream("Clientes.txt");
							FileOutputStream fos1 = new FileOutputStream("Puntos.txt");
							OutputStreamWriter out = new OutputStreamWriter(fos);
							OutputStreamWriter out1 = new OutputStreamWriter(fos1);

							out.write("Numero Longitud Latitud \n");
							for(int i=0;i<fila;i++) {
								out.write(tablaClientes.getValueAt(i, 0) + " ");
								out.write(tablaClientes.getValueAt(i, 1) + " ");
								out.write(tablaClientes.getValueAt(i, 2) + " \n");
							}
							out1.write("Numero Longitud Latitud \n");
							for(int i=0;i<fila2;i++) {
								out1.write(tablaPuntos.getValueAt(i, 0) + " ");
								out1.write(tablaPuntos.getValueAt(i, 1) + " ");
								out1.write(tablaPuntos.getValueAt(i, 2) + " \n");
							}
							out.close();
							out1.close();
						}
						 catch(Exception e) { 
							 e.printStackTrace();
						 }
						AlgoritmoGoloso a = new AlgoritmoGoloso();
						//realizar algoritmo
						a.algoritmoDistribucion(kCentros);
						frame2 = new InterfazSecundaria();
						DefaultTableModel res = new DefaultTableModel(
								new Object[][] {
								},
								new String[] {
										"Numero", "Longitud", "Latitud", "Distancias Promedio"
								}
							);
						frame2.getTable().setModel(res);
						//devuelve centros a abrir en nueva interfaz
						int suma = 0;
						try {
							FileInputStream fis = new FileInputStream("Resultado.txt");
							Scanner scanner = new Scanner(fis);
							while(scanner.hasNextLine()) {
								String linea= scanner.nextLine();
								Scanner contenidoLinea = new Scanner(linea);
								while (contenidoLinea.hasNextDouble()) {
									Object[] filas = new Object[4];
									filas[0] = contenidoLinea.nextInt();
									filas[1] = contenidoLinea.next();
									filas[2] = contenidoLinea.next();
									filas[3] = contenidoLinea.next();
									res.addRow(filas);
				                }
								contenidoLinea.close();
							}
							scanner.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
						for(int i=0;i<res.getRowCount();i++) {
							suma+=Integer.parseInt((String) res.getValueAt(i, 3));
						}
						frame2.setTxtCosto(String.valueOf(suma) + "m");
					}else {
						JOptionPane.showMessageDialog(frmDistribucionGolosa, "La cantidad de centros de distribucion no puede ser mayor que la cantidad de puntos", "Distribucion Golosa", JOptionPane.INFORMATION_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(frmDistribucionGolosa, "Debe haber al menos 1 cliente y 2 puntos en las tablas", "Distribucion Golosa", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnBuscarCentrosPara.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnBuscarCentrosPara.setBounds(606, 383, 268, 81);
		frmDistribucionGolosa.getContentPane().add(btnBuscarCentrosPara);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmDistribucionGolosa = new JFrame("Salir");
				if(JOptionPane.showConfirmDialog(frmDistribucionGolosa, "¿Desea salir del programa?", "Distribucion Golosa", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSalir.setBounds(606, 277, 120, 66);
		frmDistribucionGolosa.getContentPane().add(btnSalir);
		
		JLabel lblLatitud = new JLabel("Latitud:");
		lblLatitud.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLatitud.setBounds(682, 30, 80, 33);
		frmDistribucionGolosa.getContentPane().add(lblLatitud);
		
		JLabel lblLongitud = new JLabel("Longitud:");
		lblLongitud.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLongitud.setBounds(682, 74, 80, 33);
		frmDistribucionGolosa.getContentPane().add(lblLongitud);
		
		JScrollPane scrollPaneClientes = new JScrollPane();
		scrollPaneClientes.setBounds(10, 63, 258, 442);
		frmDistribucionGolosa.getContentPane().add(scrollPaneClientes);
		
		tablaClientes = new JTable();
		tablaClientes.setRowHeight(25);
		tablaClientes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tablaClientes.setBorder(new LineBorder(new Color(0, 0, 0)));
		tablaClientes.setDefaultEditor(Object.class, null);
		DefaultTableModel model;
		tablaClientes.setModel(model = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Numero", "Longitud", "Latitud"
			}
		));
		scrollPaneClientes.setViewportView(tablaClientes);
		
		JScrollPane scrollPanePuntos = new JScrollPane();
		scrollPanePuntos.setBounds(278, 63, 258, 442);
		frmDistribucionGolosa.getContentPane().add(scrollPanePuntos);
		
		tablaPuntos = new JTable();
		tablaPuntos.setRowHeight(25);
		tablaPuntos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tablaPuntos.setBorder(new LineBorder(new Color(0, 0, 0)));
		tablaPuntos.setDefaultEditor(Object.class, null);
		DefaultTableModel model2;
		tablaPuntos.setModel(model2 = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Numero", "Longitud", "Latitud"
			}
		));
		scrollPanePuntos.setViewportView(tablaPuntos);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = tablaClientes.getSelectedRow();
				int j = tablaPuntos.getSelectedRow();
				if (i >= 0) {
					model.removeRow(i);
				}else if(j >= 0) {
					model2.removeRow(j);
				}
			}
		});
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEliminar.setBounds(752, 277, 120, 66);
		frmDistribucionGolosa.getContentPane().add(btnEliminar);
		
		JButton btnAgregarCliente = new JButton("Agregar Cliente");
		btnAgregarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fila = tablaClientes.getRowCount();
				Object[] filas = new Object[3];
				filas[0] = fila;
				filas[1] = txtFieldLongitud.getText().replace(".",",");
				filas[2] = txtFieldLatitud.getText().replace(".",",");
				if(!txtFieldLatitud.getText().isEmpty() && !txtFieldLongitud.getText().isEmpty()) {
					try {
						double i=Double.parseDouble(txtFieldLatitud.getText());
						double j=Double.parseDouble(txtFieldLongitud.getText());
						if((i <= 90 && i >= -90) && (j <= 180 && j >= -180)) {
							model.addRow(filas);
						}else {
							JOptionPane.showMessageDialog(frmDistribucionGolosa, "Ingrese una latitud y longitud valida. Ejemplo: 45.23532", "Distribucion Golosa", JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(frmDistribucionGolosa, "Latitud y Longitud debe ser numerico", "Distribucion Golosa", JOptionPane.INFORMATION_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(frmDistribucionGolosa, "Ingrese una latitud y longitud", "Distribucion Golosa", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnAgregarCliente.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAgregarCliente.setBounds(664, 173, 161, 33);
		frmDistribucionGolosa.getContentPane().add(btnAgregarCliente);
		
		JButton btnAgregarPunto = new JButton("Agregar Punto");
		btnAgregarPunto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fila = tablaPuntos.getRowCount();
				Object[] filas = new Object[3];
				filas[0] = fila;
				filas[1] = txtFieldLongitud.getText().replace(".",",");
				filas[2] = txtFieldLatitud.getText().replace(".",",");
				if(!txtFieldLatitud.getText().isEmpty() && !txtFieldLongitud.getText().isEmpty()) {
					try {
						double i=Double.parseDouble(txtFieldLatitud.getText());
						double j=Double.parseDouble(txtFieldLongitud.getText());
						if((i <= 90 && i >= -90) && (j <= 180 && j >= -180)) {
							model2.addRow(filas);
						}else {
							JOptionPane.showMessageDialog(frmDistribucionGolosa, "Ingrese una latitud y longitud valida. Ejemplo: 45.23532", "Distribucion Golosa", JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(frmDistribucionGolosa, "Latitud y Longitud debe ser numerico", "Distribucion Golosa", JOptionPane.INFORMATION_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(frmDistribucionGolosa, "Ingrese una latitud y longitud", "Distribucion Golosa", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnAgregarPunto.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAgregarPunto.setBounds(664, 222, 161, 33);
		frmDistribucionGolosa.getContentPane().add(btnAgregarPunto);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCliente.setBounds(105, 30, 67, 14);
		frmDistribucionGolosa.getContentPane().add(lblCliente);
		
		JLabel lblPunto = new JLabel("Punto");
		lblPunto.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPunto.setBounds(378, 30, 59, 14);
		frmDistribucionGolosa.getContentPane().add(lblPunto);
		
		JLabel lblCantidadMaximaDe = new JLabel("Centros de Distribucion a Abrir");
		lblCantidadMaximaDe.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCantidadMaximaDe.setBounds(596, 113, 229, 49);
		frmDistribucionGolosa.getContentPane().add(lblCantidadMaximaDe);
		
		txtFieldLatitud = new JTextField();
		txtFieldLatitud.setBounds(788, 38, 86, 20);
		frmDistribucionGolosa.getContentPane().add(txtFieldLatitud);
		txtFieldLatitud.setColumns(10);
		
		txtFieldLongitud = new JTextField();
		txtFieldLongitud.setBounds(788, 82, 86, 20);
		frmDistribucionGolosa.getContentPane().add(txtFieldLongitud);
		txtFieldLongitud.setColumns(10);
		
		((DefaultEditor) spnCentrosAbrir.getEditor()).getTextField().setEditable(false);
	}
}
