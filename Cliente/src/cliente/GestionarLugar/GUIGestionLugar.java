package cliente.GestionarLugar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import comun.DTO.LugarDTO;
import javax.swing.JCheckBox;

public class GUIGestionLugar extends JFrame {
	


	private static final long serialVersionUID = 1L;
	
	private MediadorLugar mediador;
	
	private JPanel contentPane;
	
	private int limite = 35;
	private JTextField tFnombre;
	private JTextField tFdireccion;
	private JCheckBox chckbxElim = new JCheckBox("");
	private JButton btnModificar; 
	private JButton btnEliminar; 
	private JButton btnAgregar; 
	private JButton btnImprimir; 
	private JButton btnVolver; 
	private JButton btnActualizar; 
	private JLabel labelTipoUsuario;
	
	//Tabla
	private DefaultTableModel modelo;
	private JTable tableLugares;
	private Vector<Vector<String>> datosTabla;
	private Vector<String> nombreColumnas; 

	public GUIGestionLugar(MediadorLugar mediador) {
		this.mediador = mediador;
		cargarDatos();
		initialize();
	}
	
	@SuppressWarnings("serial")
	public void initialize(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		//setIconImage(Toolkit.getDefaultToolkit().getImage(GUIGestionUsuario.class.getResource("/cliente/imagenes/find_user.ico")));
		setTitle("GESTIONAR LUGAR");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		labelTipoUsuario = new JLabel("Incluir Eliminados");
		labelTipoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		labelTipoUsuario.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		labelTipoUsuario.setBounds(31, 107, 141, 24);
		contentPane.add(labelTipoUsuario);
		
		
		tFnombre = new JTextField();
		tFnombre.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
			//	filtrarPorNombre();
			}
		});
		tFnombre.setBounds(192, 37, 230, 20);
		tFnombre.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				/*if (tFnombre_usuario.getText().length()>=limite){
					e.consume();					
				}*/
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
			/*	if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
				}*/
			}
			@Override
			public void keyReleased(KeyEvent arg0) {				
			}
		});
		contentPane.add(tFnombre);
		tFnombre.setColumns(10);
		
		tFdireccion = new JTextField();
		tFdireccion.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				//filtrarPorEmail();
			}
		});
		tFdireccion.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if (tFdireccion.getText().length()>=limite){
					e.consume();					
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {		
			}
		});
		tFdireccion.setBounds(192, 72, 230, 20);
		contentPane.add(tFdireccion);
		tFdireccion.setColumns(10);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setIcon(new ImageIcon(GUIGestionLugar.class.getResource(mediador.getMediadorPrincipal().getApariencia().getEditar())));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
			}
		});
		btnModificar.setBounds(493, 83, 220, 37);
		contentPane.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(GUIGestionLugar.class.getResource(mediador.getMediadorPrincipal().getApariencia().getEliminar())));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					eliminar();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnEliminar.setBounds(493, 132, 220, 37);
		contentPane.add(btnEliminar);
		
		JLabel lbl_nombre_usuario = new JLabel("Nombre");
		lbl_nombre_usuario.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lbl_nombre_usuario.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_nombre_usuario.setBounds(31, 35, 141, 24);
		contentPane.add(lbl_nombre_usuario);
		
		JLabel lbl_email = new JLabel("Direccion");
		lbl_email.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lbl_email.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_email.setBounds(31, 70, 141, 24);
		contentPane.add(lbl_email);
		
		
		modelo = new DefaultTableModel(datosTabla, nombreColumnas);
		
		tableLugares = new JTable(modelo) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		// Agregamos el ordenador para las tablas de los usuarios
		TableRowSorter<TableModel> ordenador = new TableRowSorter<TableModel>(modelo);
		tableLugares.setRowSorter(ordenador);
		//
		tableLugares.getTableHeader().setReorderingAllowed(false);
		tableLugares.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableLugares.setBounds(0, 0, 765, 320);
		
		JScrollPane scrollPaneTabla = new JScrollPane(tableLugares);

		scrollPaneTabla.setBounds(10, 182, 720, 318);
		contentPane.add(scrollPaneTabla);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setIcon(new ImageIcon(GUIGestionLugar.class.getResource(mediador.getMediadorPrincipal().getApariencia().getAgregar())));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediador.altaLugar();
			}
		});
		btnAgregar.setBounds(493, 34, 220, 37);
		contentPane.add(btnAgregar);
		
		btnImprimir = new JButton("Imprimir");
		btnImprimir.setIcon(new ImageIcon(GUIGestionLugar.class.getResource(mediador.getMediadorPrincipal().getApariencia().getImprimir())));
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					tableLugares.print();
				} catch (PrinterException e) {
					e.printStackTrace();
				}
			}
		});
		btnImprimir.setBounds(237, 512, 150, 39);
		contentPane.add(btnImprimir);
		
		btnVolver = new JButton("Volver");
		btnVolver.setIcon(new ImageIcon(GUIGestionLugar.class.getResource(mediador.getMediadorPrincipal().getApariencia().getVolver())));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediador.mediadorPrincipal.getGui_menu_Principal().setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(397, 512, 150, 39);
		contentPane.add(btnVolver);
		
		btnActualizar = new JButton("BUSCAR");
		btnActualizar.setIcon(new ImageIcon(GUIGestionLugar.class.getResource(mediador.getMediadorPrincipal().getApariencia().getBuscar())));
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarDatos();
			}
		});
		btnActualizar.setBounds(237, 131, 186, 37);
		contentPane.add(btnActualizar);
		
		chckbxElim.setBounds(186, 108, 129, 23);
		contentPane.add(chckbxElim);

		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				mediador.mediadorPrincipal.getGui_menu_Principal().setVisible(true);
				dispose();
			}
		});
	}
	//Gestion
	public void modificar(){
		int row = tableLugares.getSelectedRow();
		if (row >= 0) {
			int aux = tableLugares.convertRowIndexToModel(row);
			Long id = new Long (tableLugares.getValueAt(aux, 0).toString());
			if (JOptionPane.showConfirmDialog(null, "¿Modificar Lugar [ID:"+id+"]?", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){ 
				mediador.modifLugar(id);
			}
		}else{
			JOptionPane.showMessageDialog(contentPane,"Seleccione un Usuario primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIGestionLugar.class.getResource(mediador.getMediadorPrincipal().getApariencia().getInfo())));
		}
	}
	public void eliminar() throws Exception{
		int row = tableLugares.getSelectedRow();
		if (row >= 0) {
			int aux = tableLugares.convertRowIndexToModel(row);
			Long id = new Long (tableLugares.getValueAt(aux, 0).toString());
			if (JOptionPane.showConfirmDialog(null, "¿Eliminar Usuario [ID:"+id+"]?", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){ 
				if (mediador.eliminarLugar(id)){
					JOptionPane.showMessageDialog(contentPane,"Lugar eliminado.","Advertencia",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIGestionLugar.class.getResource(mediador.getMediadorPrincipal().getApariencia().getOk())));
					actualizarDatos();
				}
			}
		}else{
			JOptionPane.showMessageDialog(contentPane,"Seleccione un lugar primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIGestionLugar.class.getResource(mediador.getMediadorPrincipal().getApariencia().getInfo())));
		}
	}
	
	
	public void cargarDatos(){
		
		modelo = new DefaultTableModel();

		nombreColumnas = new Vector<String> ();
		nombreColumnas.add("ID");
		nombreColumnas.add("NOMBRE");
		nombreColumnas.add("DIRECCION");
		nombreColumnas.add("ELIMINADO");

		datosTabla = new Vector<Vector<String>>();
		Vector<LugarDTO> lugares = mediador.obtenerLugar(getFiltro());
		
		for (int i=0; i< lugares.size();i++){
			Vector<String> row = new Vector<String> ();
			
			row.add(lugares.elementAt(i).getId().toString());
			row.add(lugares.elementAt(i).getNombre());
			row.add(lugares.elementAt(i).getDireccion());
			if (lugares.elementAt(i).getEliminado())row.add("SI");
			else row.add("NO");
			
			datosTabla.add(row);
		};
		modelo.setDataVector(datosTabla, nombreColumnas);
		tableLugares = new JTable(modelo) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
	}
	
	public void actualizarPantalla(){ 
		modelo.fireTableStructureChanged();
	}
	

	public void actualizarDatos() {
		datosTabla = new Vector<Vector<String>>();
		Vector<LugarDTO> lugares = mediador.obtenerLugar(getFiltro());
		
		for (int i=0; i< lugares.size();i++){
			Vector<String> row = new Vector<String> ();
			
			row.add(lugares.elementAt(i).getId().toString());
			row.add(lugares.elementAt(i).getNombre());
			row.add(lugares.elementAt(i).getDireccion());
			if (lugares.elementAt(i).getEliminado())row.add("SI");
			else row.add("NO");
			
			datosTabla.add(row);
		};
		modelo.setDataVector(datosTabla, nombreColumnas);
		actualizarPantalla();		
	}
	
	private String getFiltro(){		
		String filtro = "";
		boolean filtro_armado = false;
		if (tFnombre!=null && !tFnombre.getText().trim().isEmpty()) {
			filtro+="nombre.toUpperCase().indexOf(\""+tFnombre.getText().toUpperCase()+"\")>=0 ";
			filtro_armado=true;
		}
		if (tFdireccion!=null && !tFdireccion.getText().trim().isEmpty()) {
			if (filtro_armado) filtro+=" && ";
			filtro+="direccion.toUpperCase().indexOf(\""+tFdireccion.getText().toUpperCase()+"\")>=0 ";
		}
		if (!chckbxElim.isSelected()) {
			if (filtro_armado) filtro+=" && ";
			filtro+="eliminado==false";
		}
		return filtro;
	}
}
