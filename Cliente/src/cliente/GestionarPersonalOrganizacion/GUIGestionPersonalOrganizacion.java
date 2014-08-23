package cliente.GestionarPersonalOrganizacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import cliente.utils.Constantes;
import java.awt.Component;
import javax.swing.JComboBox;

public class GUIGestionPersonalOrganizacion extends JFrame {
	


	private static final long serialVersionUID = 1L;
	
	private MediadorPersonalOrganizacion mediador;
	
	private JPanel contentPane;
	
	
	private JButton btnModificar; 
	private JButton btnEliminar; 
	private JButton btnAgregar; 
	private JButton btnImprimir; 
	private JButton btnVolver; 
	private JButton btnActualizar; 
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	
	//Tabla
	private JTable tableAsignados;
	private DefaultTableModel modeloAsignadas;
	private Vector<Vector<String>> datosTablaAsignadas;
	private Vector<String> nombreColAsignadas; 
	private JTable tableSinAsignar;
	private DefaultTableModel modeloSinAsignar;
	private Vector<Vector<String>> datosTablaSinAsignar;
	private Vector<String> nombreColSinAsignar; 

	public GUIGestionPersonalOrganizacion(MediadorPersonalOrganizacion mediador) {
		this.mediador = mediador;
		comboBox =new JComboBox<String>(mediador.cargarOrganizaciones());
		cargarDatos();
		initialize();
	}
	
	@SuppressWarnings("serial")
	public void initialize(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		setTitle("GESTIONAR RRHH - ORGANIZACION");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setIcon(new ImageIcon(GUIGestionPersonalOrganizacion.class.getResource(mediador.getMediadorPrincipal().getApariencia().getEditar())));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
			}
		});
		btnModificar.setBounds(495, 83, 220, 41);
		contentPane.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(GUIGestionPersonalOrganizacion.class.getResource(mediador.getMediadorPrincipal().getApariencia().getEliminar())));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					eliminar();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnEliminar.setBounds(495, 133, 220, 41);
		contentPane.add(btnEliminar);
		
		
		modeloAsignadas = new DefaultTableModel(datosTablaAsignadas, nombreColAsignadas);
		
		tableAsignados = new JTable(modeloAsignadas) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		// Agregamos el ordenador para las tablas de los usuarios
		TableRowSorter<TableModel> ordenador = new TableRowSorter<TableModel>(modeloAsignadas);
		tableAsignados.setRowSorter(ordenador);
		//
		tableAsignados.getTableHeader().setReorderingAllowed(false);
		tableAsignados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableAsignados.setBounds(0, 0, 765, 320);
		
		JScrollPane scrollPaneTabla = new JScrollPane(tableAsignados);

		scrollPaneTabla.setBounds(12, 83, 471, 183);
		contentPane.add(scrollPaneTabla);
		 
		btnAgregar = new JButton("Agregar");
		btnAgregar.setIcon(new ImageIcon(GUIGestionPersonalOrganizacion.class.getResource(mediador.getMediadorPrincipal().getApariencia().getAgregar())));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!comboBox.getSelectedItem().toString().equals(Constantes.comboVacio)){
					int row = tableSinAsignar.getSelectedRow();
					if (row >= 0) {
						int aux = tableSinAsignar.convertRowIndexToModel(row);
						Long id = new Long (tableSinAsignar.getValueAt(aux, 0).toString());
						if (JOptionPane.showConfirmDialog(null, "¿Agregar a la Organizacion la persona [ID:"+id+"]?", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon(GUIGestionPersonalOrganizacion.class.getResource(mediador.getMediadorPrincipal().getApariencia().getAyuda()))) == JOptionPane.YES_OPTION){ 
							Long idOrg =mediador.getIdOrg(comboBox.getSelectedItem().toString());
							try {
								mediador.altaAsig(idOrg,id);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}else{
						JOptionPane.showMessageDialog(contentPane,"Seleccione una persona primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIGestionPersonalOrganizacion.class.getResource(mediador.getMediadorPrincipal().getApariencia().getInfo())));
					}
				}else{
					JOptionPane.showMessageDialog(contentPane,"No tiene Organizaciones asignadas\npara poder hacer esta operacion.","Advertencia",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIGestionPersonalOrganizacion.class.getResource(mediador.getMediadorPrincipal().getApariencia().getInfo())));
				}
			}
		});
		btnAgregar.setBounds(495, 316, 220, 41);
		contentPane.add(btnAgregar);
		
		btnImprimir = new JButton("Imprimir");
		btnImprimir.setIcon(new ImageIcon(GUIGestionPersonalOrganizacion.class.getResource(mediador.getMediadorPrincipal().getApariencia().getImprimir())));
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					tableAsignados.print();
				} catch (PrinterException e) {
					e.printStackTrace();
				}
			}
		});
		btnImprimir.setBounds(237, 508, 150, 43);
		contentPane.add(btnImprimir);
		
		btnVolver = new JButton("Volver");
		btnVolver.setIcon(new ImageIcon(GUIGestionPersonalOrganizacion.class.getResource(mediador.getMediadorPrincipal().getApariencia().getVolver())));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediador.mediadorPrincipal.getGui_menu_Principal().setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(397, 508, 150, 43);
		contentPane.add(btnVolver);
		
		btnActualizar = new JButton("BUSCAR");
		btnActualizar.setIcon(new ImageIcon(GUIGestionPersonalOrganizacion.class.getResource(mediador.getMediadorPrincipal().getApariencia().getBuscar())));
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarDatos();
			}
		});
		btnActualizar.setBounds(565, 12, 150, 41);
		contentPane.add(btnActualizar);
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setBounds(12, 313, 471, 183);
		contentPane.add(scrollPane);
		
		scrollPane.setViewportView(tableSinAsignar);
		
		comboBox.setBounds(15, 22, 532, 32);
		contentPane.add(comboBox);

		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				mediador.mediadorPrincipal.getGui_menu_Principal().setVisible(true);
				dispose();
			}
		});
	}
	//Gestion
	public void modificar(){
		int row = tableAsignados.getSelectedRow();
		if (row >= 0) {
			int aux = tableAsignados.convertRowIndexToModel(row);
			Long id = new Long (tableAsignados.getValueAt(aux, 0).toString());
			if (JOptionPane.showConfirmDialog(null, "¿Modificar Asociacion [ID:"+id+"]?", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon(GUIGestionPersonalOrganizacion.class.getResource(mediador.getMediadorPrincipal().getApariencia().getAyuda()))) == JOptionPane.YES_OPTION){ 
				mediador.modifAsig(id);
			}
		}else{
			JOptionPane.showMessageDialog(contentPane,"Seleccione una persona primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIGestionPersonalOrganizacion.class.getResource(mediador.getMediadorPrincipal().getApariencia().getInfo())));
		}
	}
	
	public void eliminar() throws Exception{
		int row = tableAsignados.getSelectedRow();
		if (row >= 0) {
			int aux = tableAsignados.convertRowIndexToModel(row);
			Long id = new Long (tableAsignados.getValueAt(aux, 0).toString());
			mediador.eliminarAsig(id);
		}else{
			JOptionPane.showMessageDialog(contentPane,"Seleccione un lugar primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIGestionPersonalOrganizacion.class.getResource(mediador.getMediadorPrincipal().getApariencia().getInfo())));
		}
	}
	
	public void cargarDatos(){
		
		modeloAsignadas = new DefaultTableModel();

		nombreColAsignadas = new Vector<String> ();
		nombreColAsignadas.add("ID");
		nombreColAsignadas.add("APELLIDO");
		nombreColAsignadas.add("NOMBRE");
		nombreColAsignadas.add("DNI");
		nombreColAsignadas.add("FECHA INGRESO");
		if(comboBox!=null && !comboBox.getSelectedItem().toString().equals(Constantes.comboVacio)){
			datosTablaAsignadas = mediador.tablaDeAsignadosOrg(mediador.getIdOrg(comboBox.getSelectedItem().toString()));
			modeloAsignadas.setDataVector(datosTablaAsignadas, nombreColAsignadas);
			tableAsignados = new JTable(modeloAsignadas) {
				private static final long serialVersionUID = 1L;
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		
			modeloSinAsignar = new DefaultTableModel();
	
			nombreColSinAsignar = new Vector<String> ();
			nombreColSinAsignar.add("ID");
			nombreColSinAsignar.add("APELLIDO");
			nombreColSinAsignar.add("NOMBRE");
			nombreColSinAsignar.add("DNI");
			
			datosTablaSinAsignar = mediador.tablaDeSinAsignarOrg(mediador.getIdOrg(comboBox.getSelectedItem().toString()));
			modeloSinAsignar.setDataVector(datosTablaSinAsignar, nombreColSinAsignar);
			tableSinAsignar = new JTable(modeloSinAsignar) {
				private static final long serialVersionUID = 1L;
				boolean[] columnEditables = new boolean[] {
					false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		}
	}
	
	public void actualizarPantalla(){ 
		modeloAsignadas.fireTableStructureChanged();
		modeloSinAsignar.fireTableStructureChanged();
	}
	

	public void actualizarDatos() {
		if (!comboBox.getSelectedItem().toString().equals(Constantes.comboVacio)){
			datosTablaAsignadas = new Vector<Vector<String>>();
			datosTablaAsignadas = mediador.tablaDeAsignadosOrg(mediador.getIdOrg(comboBox.getSelectedItem().toString()));
			modeloAsignadas.setDataVector(datosTablaAsignadas, nombreColAsignadas);
			datosTablaSinAsignar= new Vector<Vector<String>>();
			datosTablaSinAsignar = mediador.tablaDeSinAsignarOrg(mediador.getIdOrg(comboBox.getSelectedItem().toString()));
			modeloSinAsignar.setDataVector(datosTablaSinAsignar, nombreColSinAsignar);
			actualizarPantalla();
		}		
	}
	
}
