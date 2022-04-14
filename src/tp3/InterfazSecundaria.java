package tp3;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class InterfazSecundaria {

	private JFrame frmDistribucionGolosaV;
	private JTable tablaCentros;
	private InterfazMapa mapa;
	private JTextField txtCosto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazSecundaria window = new InterfazSecundaria();
					window.frmDistribucionGolosaV.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfazSecundaria() {
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
		frmDistribucionGolosaV = new JFrame();
		frmDistribucionGolosaV.setBounds(100, 100, 704, 508);
		frmDistribucionGolosaV.setTitle("Distribucion Golosa V.1.0");
		frmDistribucionGolosaV.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmDistribucionGolosaV.setLocationRelativeTo(null);
		frmDistribucionGolosaV.setResizable(false);
		frmDistribucionGolosaV.getContentPane().setLayout(null);
		frmDistribucionGolosaV.setVisible(true);
		
		JScrollPane scrollPaneCentros = new JScrollPane();
		scrollPaneCentros.setBounds(10, 63, 423, 356);
		frmDistribucionGolosaV.getContentPane().add(scrollPaneCentros);
		
		tablaCentros = new JTable();
		tablaCentros.setRowHeight(25);
		tablaCentros.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tablaCentros.setBorder(new LineBorder(new Color(0, 0, 0)));
		tablaCentros.setDefaultEditor(Object.class, null);
		tablaCentros.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Numero", "Longitud", "Latitud", "Distancias Promedio"
			}
		));
		scrollPaneCentros.setViewportView(tablaCentros);
		
		JLabel lblCentros = new JLabel("Centros a Abrir");
		lblCentros.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCentros.setBounds(147, 27, 143, 14);
		frmDistribucionGolosaV.getContentPane().add(lblCentros);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmDistribucionGolosaV.dispose();
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVolver.setBounds(443, 357, 232, 62);
		frmDistribucionGolosaV.getContentPane().add(btnVolver);
		
		JButton btnVerEnMapa = new JButton("Ver Resultado en Mapa");
		btnVerEnMapa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mapa = new InterfazMapa();
			}
		});
		btnVerEnMapa.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVerEnMapa.setBounds(443, 277, 232, 48);
		frmDistribucionGolosaV.getContentPane().add(btnVerEnMapa);
		
		JLabel lblNewLabel = new JLabel("Costo Total:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(466, 63, 100, 20);
		frmDistribucionGolosaV.getContentPane().add(lblNewLabel);
		
		txtCosto = new JTextField();
		txtCosto.setHorizontalAlignment(SwingConstants.CENTER);
		txtCosto.setBounds(576, 63, 86, 20);
		frmDistribucionGolosaV.getContentPane().add(txtCosto);
		txtCosto.setColumns(10);
		txtCosto.setEditable(false);
		
	}
	
	public JTable getTable() {
		return tablaCentros;
	}
	
	public InterfazMapa getMapa() {
		return mapa;
	}

	public JTextField getTxtCosto() {
		return txtCosto;
	}

	public void setTxtCosto(String str) {
		txtCosto.setText(str);
	}
}
