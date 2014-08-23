package cliente.GestionarRegistro;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import cliente.imprimir.ExportarExcel;
import cliente.utils.Constantes;

import com.toedter.calendar.JDateChooser;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.util.Vector;

public class GuiFiltroRegistro extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private MediadorRegistro mediador;
	private JTable tableConsulta;
	private DefaultTableModel modelo;
	private Vector<String> nombreColumnas; 
	private Vector<Vector<String>> datos;
	String[] comboVacio = {Constantes.comboVacio};
	String[] comboTipo  = {Constantes.comboVacio,"Agrupar Por Persona","Agrupar Por Dia","Agrupar Por Mes"};
	JScrollPane scrollPane = new JScrollPane();
	JCheckBox chckbxElim = new JCheckBox("");
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox comboBoxTipo = new JComboBox(comboTipo);
	@SuppressWarnings({ "unchecked", "rawtypes" })
	JComboBox comboBoxLugar = new JComboBox(comboVacio);
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox comboBoxPersona = new JComboBox(comboVacio);
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox comboBoxOrganizacion = new JComboBox(comboVacio);
	JDateChooser dateChooserHasta = new JDateChooser();
	JDateChooser dateChooserDesde = new JDateChooser();
	private final JButton btnConsultar = new JButton("Consultar");
	private final JLabel lblOmitirAbiertos = new JLabel("Omitir Abiertos");
	private final JCheckBox chckbxOmitirabiertos = new JCheckBox("");
	private final JButton btnImprmir = new JButton("Imprmir");
	private final JButton btnExcel = new JButton("Excel");
	private final JButton btnVolver = new JButton("Volver");
	
	public GuiFiltroRegistro(MediadorRegistro mediador) throws Exception {
		setTitle("CONSULTAR");
		this.mediador = mediador;
		initialize();
		
	}
	@SuppressWarnings("serial")
	public void initialize(){

		setSize(800,600);
		setLocationRelativeTo(null);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 122, 30, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{22, 0, 0, 0, 0, 0, 0, 309, 57, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblTipoDeConsulta = new JLabel("tipo de consulta");
		GridBagConstraints gbc_lblTipoDeConsulta = new GridBagConstraints();
		gbc_lblTipoDeConsulta.anchor = GridBagConstraints.EAST;
		gbc_lblTipoDeConsulta.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipoDeConsulta.gridx = 1;
		gbc_lblTipoDeConsulta.gridy = 1;
		getContentPane().add(lblTipoDeConsulta, gbc_lblTipoDeConsulta);
		
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 2;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 1;
		getContentPane().add(comboBoxTipo, gbc_comboBox);
		
		JLabel lblOrganizacin = new JLabel("Organización");
		GridBagConstraints gbc_lblOrganizacin = new GridBagConstraints();
		gbc_lblOrganizacin.anchor = GridBagConstraints.EAST;
		gbc_lblOrganizacin.insets = new Insets(0, 0, 5, 5);
		gbc_lblOrganizacin.gridx = 4;
		gbc_lblOrganizacin.gridy = 1;
		getContentPane().add(lblOrganizacin, gbc_lblOrganizacin);
		
		GridBagConstraints gbc_comboBoxOrganizacion = new GridBagConstraints();
		gbc_comboBoxOrganizacion.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxOrganizacion.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxOrganizacion.gridx = 5;
		gbc_comboBoxOrganizacion.gridy = 1;
		getContentPane().add(comboBoxOrganizacion, gbc_comboBoxOrganizacion);
		
		JLabel lblDesde = new JLabel("Desde");
		GridBagConstraints gbc_lblDesde = new GridBagConstraints();
		gbc_lblDesde.anchor = GridBagConstraints.EAST;
		gbc_lblDesde.insets = new Insets(0, 0, 5, 5);
		gbc_lblDesde.gridx = 1;
		gbc_lblDesde.gridy = 2;
		getContentPane().add(lblDesde, gbc_lblDesde);
		
		GridBagConstraints gbc_dateChooserDesde = new GridBagConstraints();
		gbc_dateChooserDesde.gridwidth = 2;
		gbc_dateChooserDesde.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooserDesde.fill = GridBagConstraints.BOTH;
		gbc_dateChooserDesde.gridx = 2;
		gbc_dateChooserDesde.gridy = 2;
		getContentPane().add(dateChooserDesde, gbc_dateChooserDesde);
		
		JLabel lblPersona = new JLabel("Persona");
		GridBagConstraints gbc_lblPersona = new GridBagConstraints();
		gbc_lblPersona.anchor = GridBagConstraints.EAST;
		gbc_lblPersona.insets = new Insets(0, 0, 5, 5);
		gbc_lblPersona.gridx = 4;
		gbc_lblPersona.gridy = 2;
		getContentPane().add(lblPersona, gbc_lblPersona);
		
		GridBagConstraints gbc_comboBoxPersona = new GridBagConstraints();
		gbc_comboBoxPersona.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxPersona.fill = GridBagConstraints.BOTH;
		gbc_comboBoxPersona.gridx = 5;
		gbc_comboBoxPersona.gridy = 2;
		getContentPane().add(comboBoxPersona, gbc_comboBoxPersona);
		
		JLabel lblHasta = new JLabel("Hasta");
		GridBagConstraints gbc_lblHasta = new GridBagConstraints();
		gbc_lblHasta.anchor = GridBagConstraints.EAST;
		gbc_lblHasta.insets = new Insets(0, 0, 5, 5);
		gbc_lblHasta.gridx = 1;
		gbc_lblHasta.gridy = 3;
		getContentPane().add(lblHasta, gbc_lblHasta);
		
		GridBagConstraints gbc_dateChooserHasta = new GridBagConstraints();
		gbc_dateChooserHasta.gridwidth = 2;
		gbc_dateChooserHasta.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooserHasta.fill = GridBagConstraints.BOTH;
		gbc_dateChooserHasta.gridx = 2;
		gbc_dateChooserHasta.gridy = 3;
		getContentPane().add(dateChooserHasta, gbc_dateChooserHasta);
		
		JLabel lblLugar = new JLabel("Lugar");
		GridBagConstraints gbc_lblLugar = new GridBagConstraints();
		gbc_lblLugar.anchor = GridBagConstraints.EAST;
		gbc_lblLugar.insets = new Insets(0, 0, 5, 5);
		gbc_lblLugar.gridx = 4;
		gbc_lblLugar.gridy = 3;
		getContentPane().add(lblLugar, gbc_lblLugar);
		
		GridBagConstraints gbc_comboBoxLugar = new GridBagConstraints();
		gbc_comboBoxLugar.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxLugar.fill = GridBagConstraints.BOTH;
		gbc_comboBoxLugar.gridx = 5;
		gbc_comboBoxLugar.gridy = 3;
		getContentPane().add(comboBoxLugar, gbc_comboBoxLugar);
		
		JLabel lblIncluirEliminados = new JLabel("Incluir Eliminados");
		GridBagConstraints gbc_lblIncluirEliminados = new GridBagConstraints();
		gbc_lblIncluirEliminados.anchor = GridBagConstraints.EAST;
		gbc_lblIncluirEliminados.insets = new Insets(0, 0, 5, 5);
		gbc_lblIncluirEliminados.gridx = 1;
		gbc_lblIncluirEliminados.gridy = 4;
		getContentPane().add(lblIncluirEliminados, gbc_lblIncluirEliminados);
		
		GridBagConstraints gbc_chckbxElim = new GridBagConstraints();
		gbc_chckbxElim.anchor = GridBagConstraints.WEST;
		gbc_chckbxElim.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxElim.gridx = 2;
		gbc_chckbxElim.gridy = 4;
		getContentPane().add(chckbxElim, gbc_chckbxElim);
		
		GridBagConstraints gbc_btnConsultar = new GridBagConstraints();
		gbc_btnConsultar.gridheight = 2;
		gbc_btnConsultar.fill = GridBagConstraints.BOTH;
		gbc_btnConsultar.gridwidth = 2;
		gbc_btnConsultar.insets = new Insets(0, 0, 5, 5);
		gbc_btnConsultar.gridx = 4;
		gbc_btnConsultar.gridy = 4;
		btnConsultar.setIcon(new ImageIcon(GuiFiltroRegistro.class.getResource(mediador.getMediadorPrincipal().getApariencia().getConsultar())));
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					consultar();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		GridBagConstraints gbc_lblOmitirAbiertos = new GridBagConstraints();
		gbc_lblOmitirAbiertos.anchor = GridBagConstraints.EAST;
		gbc_lblOmitirAbiertos.insets = new Insets(0, 0, 5, 5);
		gbc_lblOmitirAbiertos.gridx = 1;
		gbc_lblOmitirAbiertos.gridy = 5;
		getContentPane().add(lblOmitirAbiertos, gbc_lblOmitirAbiertos);
		
		GridBagConstraints gbc_chckbxOmitirabiertos = new GridBagConstraints();
		gbc_chckbxOmitirabiertos.anchor = GridBagConstraints.WEST;
		gbc_chckbxOmitirabiertos.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxOmitirabiertos.gridx = 2;
		gbc_chckbxOmitirabiertos.gridy = 5;
		getContentPane().add(chckbxOmitirabiertos, gbc_chckbxOmitirabiertos);
		getContentPane().add(btnConsultar, gbc_btnConsultar);
		
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 7;
		getContentPane().add(scrollPane, gbc_scrollPane);
		modelo = new DefaultTableModel(datos, nombreColumnas);
		tableConsulta= new JTable(modelo) {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
			// Agregamos el ordenador para las tablas de los usuarios
			TableRowSorter<TableModel> ordenador = new TableRowSorter<TableModel>(modelo);
			tableConsulta.setRowSorter(ordenador);
			//
			tableConsulta.getTableHeader().setReorderingAllowed(false);
			tableConsulta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tableConsulta);
		
		GridBagConstraints gbc_btnImprmir = new GridBagConstraints();
		gbc_btnImprmir.anchor = GridBagConstraints.WEST;
		gbc_btnImprmir.insets = new Insets(0, 0, 0, 5);
		gbc_btnImprmir.gridx = 2;
		gbc_btnImprmir.gridy = 8;
		btnImprmir.setIcon(new ImageIcon(GuiFiltroRegistro.class.getResource(mediador.getMediadorPrincipal().getApariencia().getImprimir())));
		btnImprmir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					tableConsulta.print();
				} catch (PrinterException e) {
					e.printStackTrace();
				}
			}
		});
		getContentPane().add(btnImprmir, gbc_btnImprmir);
		
		GridBagConstraints gbc_btnExcel = new GridBagConstraints();
		gbc_btnExcel.gridwidth = 2;
		gbc_btnExcel.insets = new Insets(0, 0, 0, 5);
		gbc_btnExcel.gridx = 3;
		gbc_btnExcel.gridy = 8;

		btnExcel.setIcon(new ImageIcon(GuiFiltroRegistro.class.getResource(mediador.getMediadorPrincipal().getApariencia().getExcel())));
		btnExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exportarExcel();
			}
		});
		getContentPane().add(btnExcel, gbc_btnExcel);
		
		GridBagConstraints gbc_btnVolver = new GridBagConstraints();
		gbc_btnVolver.anchor = GridBagConstraints.WEST;
		gbc_btnVolver.insets = new Insets(0, 0, 0, 5);
		gbc_btnVolver.gridx = 5;
		gbc_btnVolver.gridy = 8;

		btnVolver.setIcon(new ImageIcon(GuiFiltroRegistro.class.getResource(mediador.getMediadorPrincipal().getApariencia().getVolver())));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mediador.mediadorPrincipal.getGui_menu_Principal().setVisible(true);
				dispose();
			}
		});
		getContentPane().add(btnVolver, gbc_btnVolver);
		

		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				mediador.mediadorPrincipal.getGui_menu_Principal().setVisible(true);
				dispose();
			}
		});
	}

	@SuppressWarnings("deprecation")
	protected void exportarExcel() {
		String nombreArchivo = "consulta_sistema_registro";
		if (dateChooserDesde!=null && dateChooserDesde.getDate()!=null && !dateChooserDesde.getDate().toString().equals("")) 
			nombreArchivo+="_"+(dateChooserDesde.getDate().getDay()+10)+"-"+(dateChooserDesde.getDate().getMonth()+1)+"-"+(dateChooserDesde.getDate().getYear()+1900);
		if (dateChooserHasta!=null && dateChooserHasta.getDate()!=null && !dateChooserHasta.getDate().toString().equals("")) 
			nombreArchivo+="_"+(dateChooserHasta.getDate().getDay()+10)+"-"+(dateChooserHasta.getDate().getMonth()+1)+"-"+(dateChooserHasta.getDate().getYear()+1900);
		try {
			ExportarExcel.exportarUnaTabla(tableConsulta, nombreArchivo);
			JOptionPane.showMessageDialog(this,"se exporto exitosamente el archivo.","Exportado Exitosos",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GuiFiltroRegistro.class.getResource(mediador.getMediadorPrincipal().getApariencia().getOk())));
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,"Error al Agregar. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GuiFiltroRegistro.class.getResource(mediador.getMediadorPrincipal().getApariencia().getError())));
		}
		
	}
	public void actualizarPantalla(){ 
		modelo.fireTableStructureChanged();
	}
	
	protected void consultar() throws Exception {
		if (comboBoxTipo.getSelectedIndex()==0){	//Filtro Libre
			filtroLibre();
		}else{
			if (comboBoxTipo.getSelectedIndex()==1){ //Filtro Agrupado por Persona
				filtroXPersona();
			}else{
				if (comboBoxTipo.getSelectedIndex()==2){ //Filtro Agrupado por Dias
					filtroXDia();
				}else{// Filtro Agrupado por Mes
					filtroXMes();
				}
			}
		}
		
	}
	
	@SuppressWarnings("serial")
	public void filtroLibre() throws Exception{
		nombreColumnas = new Vector<String>();
		nombreColumnas.add("Persona");
		nombreColumnas.add("DNI");
		nombreColumnas.add("Entrada");
		nombreColumnas.add("Salida");
		nombreColumnas.add("Horas Trabajadas");
		nombreColumnas.add("Descripción");
		datos = mediador.resultadoFiltroLibre(	dateChooserDesde.getDate(), dateChooserHasta.getDate(), 
																		comboBoxOrganizacion.getSelectedItem().toString(), 
																		comboBoxPersona.getSelectedItem().toString(), 
																		comboBoxLugar.getSelectedItem().toString(), 
																		chckbxElim.isSelected(),
																		chckbxOmitirabiertos.isSelected());
		tableConsulta = new JTable(modelo) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		modelo.setDataVector(datos, nombreColumnas);		
		actualizarPantalla();
	}
	
	@SuppressWarnings("serial")
	public void filtroXPersona() throws Exception{
		nombreColumnas = new Vector<String>();
		nombreColumnas.add("Persona");
		nombreColumnas.add("DNI");
		nombreColumnas.add("Horas Trabajadas");
		datos = mediador.resultadoFiltroXPersona(dateChooserDesde.getDate(), dateChooserHasta.getDate(), 
																		comboBoxOrganizacion.getSelectedItem().toString(), 
																		comboBoxPersona.getSelectedItem().toString(), 
																		comboBoxLugar.getSelectedItem().toString(), 
																		chckbxElim.isSelected(),
																		chckbxOmitirabiertos.isSelected());
		tableConsulta = new JTable(modelo) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		modelo.setDataVector(datos, nombreColumnas);		
		actualizarPantalla();
	}
	
	@SuppressWarnings("serial")
	public void filtroXDia() throws Exception{
		nombreColumnas = new Vector<String>();
		nombreColumnas.add("Dia");
		nombreColumnas.add("Horas Trabajadas");
		datos = mediador.resultadoFiltroXDia(dateChooserDesde.getDate(), dateChooserHasta.getDate(), 
																		comboBoxOrganizacion.getSelectedItem().toString(), 
																		comboBoxPersona.getSelectedItem().toString(), 
																		comboBoxLugar.getSelectedItem().toString(), 
																		chckbxElim.isSelected(),
																		chckbxOmitirabiertos.isSelected());
		tableConsulta = new JTable(modelo) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		modelo.setDataVector(datos, nombreColumnas);		
		actualizarPantalla();
	}
	
	@SuppressWarnings("serial")
	public void filtroXMes() throws Exception{
		nombreColumnas = new Vector<String>();
		nombreColumnas.add("Mes");
		nombreColumnas.add("Horas Trabajadas");
		datos = mediador.resultadoFiltroXMes(dateChooserDesde.getDate(), dateChooserHasta.getDate(), 
																		comboBoxOrganizacion.getSelectedItem().toString(), 
																		comboBoxPersona.getSelectedItem().toString(), 
																		comboBoxLugar.getSelectedItem().toString(), 
																		chckbxElim.isSelected(),
																		chckbxOmitirabiertos.isSelected());
		tableConsulta = new JTable(modelo) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		modelo.setDataVector(datos, nombreColumnas);		
		actualizarPantalla();
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void cargarDatos() throws Exception{
		comboBoxOrganizacion.setModel(new DefaultComboBoxModel(mediador.cargarOrganizaciones()));
		comboBoxPersona.setModel(new DefaultComboBoxModel(mediador.cargarPersonas()));
		comboBoxLugar.setModel(new DefaultComboBoxModel(mediador.cargarLugares()));
		comboBoxOrganizacion.repaint();
		comboBoxPersona.repaint();
		comboBoxLugar.repaint();
	}
	
}
