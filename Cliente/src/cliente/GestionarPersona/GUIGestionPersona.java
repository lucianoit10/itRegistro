package cliente.GestionarPersona;

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

import comun.DTO.PersonaDTO;
import javax.swing.JCheckBox;

public class GUIGestionPersona extends JFrame {

	

	private static final long serialVersionUID = 1L;
	
	private MediadorPersona mediador;
	
	private JPanel contentPane;
	
	private int limite = 35;
	private JTextField tFnombre;
	private JTextField tFApellido;
	private JButton btnModificar; 
	private JButton btnEliminar; 
	private JButton btnAgregar; 
	private JButton btnImprimir; 
	private JButton btnVolver; 
	private JButton btnBuscar; 
	private JLabel labelTipoUsuario;
	
	//Tabla
	private DefaultTableModel modelo;
	private JTable tableUsuarios;
	private Vector<Vector<String>> datosTabla;
	private Vector<String> nombreColumnas; 
	private JTextField tFDni;
	private JCheckBox chckbxElim = new JCheckBox("");

	public GUIGestionPersona(MediadorPersona mediador) {
		this.mediador = mediador;
		cargarDatos();
		initialize();
	}
	
	@SuppressWarnings("serial")
	public void initialize(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setSize(800,600);
		setLocationRelativeTo(null);
		//setIconImage(Toolkit.getDefaultToolkit().getImage(GUIGestionUsuario.class.getResource("/cliente/imagenes/find_user.ico")));
		setTitle("GESTIONAR PERSONAS");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		labelTipoUsuario = new JLabel("DNI-CUIT-CUIL");
		labelTipoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		labelTipoUsuario.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		labelTipoUsuario.setBounds(31, 83, 141, 24);
		contentPane.add(labelTipoUsuario);
		
		
		tFnombre = new JTextField();
		tFnombre.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				//filtrarPorNombre();
			}
		});
		tFnombre.setBounds(192, 14, 230, 20);
		tFnombre.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if (tFnombre.getText().length()>=limite){
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
		contentPane.add(tFnombre);
		tFnombre.setColumns(10);
		
		tFApellido = new JTextField();
		tFApellido.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				//filtrarPorEmail();
			}
		});
		tFApellido.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if (tFApellido.getText().length()>=limite){
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
		tFApellido.setBounds(192, 49, 230, 20);
		contentPane.add(tFApellido);
		tFApellido.setColumns(10);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setIcon(new ImageIcon(GUIGestionPersona.class.getResource(mediador.getMediadorPrincipal().getApariencia().getEditar())));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
			}
		});
		btnModificar.setBounds(493, 64, 220, 38);
		contentPane.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(GUIGestionPersona.class.getResource(mediador.getMediadorPrincipal().getApariencia().getEliminar())));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
			}
		});
		
		btnEliminar.setBounds(493, 116, 220, 38);
		contentPane.add(btnEliminar);
		
		JLabel lbl_nombre_usuario = new JLabel("Nombre");
		lbl_nombre_usuario.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lbl_nombre_usuario.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_nombre_usuario.setBounds(31, 12, 141, 24);
		contentPane.add(lbl_nombre_usuario);
		
		JLabel lbl_email = new JLabel("Apellido");
		lbl_email.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lbl_email.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_email.setBounds(31, 47, 141, 24);
		contentPane.add(lbl_email);
		
		
		modelo = new DefaultTableModel(datosTabla, nombreColumnas);
		
		tableUsuarios = new JTable(modelo) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false,false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		// Agregamos el ordenador para las tablas de los usuarios
		TableRowSorter<TableModel> ordenador = new TableRowSorter<TableModel>(modelo);
		tableUsuarios.setRowSorter(ordenador);
		//
		tableUsuarios.getTableHeader().setReorderingAllowed(false);
		tableUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableUsuarios.setBounds(0, 0, 765, 320);
		
		JScrollPane scrollPaneTabla = new JScrollPane(tableUsuarios);

		scrollPaneTabla.setBounds(10, 182, 764, 318);
		contentPane.add(scrollPaneTabla);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setIcon(new ImageIcon(GUIGestionPersona.class.getResource(mediador.getMediadorPrincipal().getApariencia().getAgregar())));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediador.altaPersona();
			}
		});
		btnAgregar.setBounds(493, 14, 220, 38);
		contentPane.add(btnAgregar);
		
		btnImprimir = new JButton("Imprimir");
		btnImprimir.setIcon(new ImageIcon(GUIGestionPersona.class.getResource(mediador.getMediadorPrincipal().getApariencia().getImprimir())));
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					tableUsuarios.print();
				} catch (PrinterException e) {
					e.printStackTrace();
				}
			}
		});
		btnImprimir.setBounds(237, 528, 150, 40);
		contentPane.add(btnImprimir);
		
		btnVolver = new JButton("Volver");
		btnVolver.setIcon(new ImageIcon(GUIGestionPersona.class.getResource(mediador.getMediadorPrincipal().getApariencia().getVolver())));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediador.mediadorPrincipal.getGui_menu_Principal().setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(397, 528, 150, 40);
		contentPane.add(btnVolver);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setIcon(new ImageIcon(GUIGestionPersona.class.getResource(mediador.getMediadorPrincipal().getApariencia().getBuscar())));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarDatos();
			}
		});
		btnBuscar.setBounds(222, 132, 200, 38);
		contentPane.add(btnBuscar);
		
		chckbxElim.setBounds(192, 116, 129, 23);
		contentPane.add(chckbxElim);
		
		JLabel label = new JLabel("Incluir Eliminados");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		label.setBounds(31, 119, 141, 24);
		contentPane.add(label);
		
		tFDni = new JTextField();
		tFDni.setColumns(10);
		tFDni.setBounds(192, 81, 230, 20);
		contentPane.add(tFDni);
		

		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				mediador.mediadorPrincipal.getGui_menu_Principal().setVisible(true);
				dispose();
			}
		});
	}
	//Gestion
	public void modificar(){
		int row = tableUsuarios.getSelectedRow();
		if (row >= 0) {
			int aux = tableUsuarios.convertRowIndexToModel(row);
			Long id = new Long (tableUsuarios.getValueAt(aux, 0).toString());
			if (JOptionPane.showConfirmDialog(null, "¿Modificar la persona [ID:"+id+"]?", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon(GUIGestionPersona.class.getResource(mediador.getMediadorPrincipal().getApariencia().getAyuda()))) == JOptionPane.YES_OPTION){ 
				mediador.modifPersona(id);
				actualizarDatos();
			}
		}else{
			JOptionPane.showMessageDialog(contentPane,"Seleccione una persona primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIGestionPersona.class.getResource(mediador.getMediadorPrincipal().getApariencia().getInfo())));
		}
	}
	public void eliminar(){
		int row = tableUsuarios.getSelectedRow();
		if (row >= 0) {
			int aux = tableUsuarios.convertRowIndexToModel(row);
			Long id = new Long (tableUsuarios.getValueAt(aux, 0).toString());
			if(id!=1){
				if (JOptionPane.showConfirmDialog(null, "¿Eliminar la persona [ID:"+id+"]?", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon(GUIGestionPersona.class.getResource(mediador.getMediadorPrincipal().getApariencia().getAyuda()))) == JOptionPane.YES_OPTION){ 
					if (mediador.eliminarPersona(id)){
						JOptionPane.showMessageDialog(contentPane,"Persona eliminada.","Advertencia",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIGestionPersona.class.getResource(mediador.getMediadorPrincipal().getApariencia().getOk())));
						actualizarDatos();
					}
				}
			}else
				JOptionPane.showMessageDialog(contentPane,"No puede eliminar al Admin Maestro.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIGestionPersona.class.getResource(mediador.getMediadorPrincipal().getApariencia().getError())));
		}else{
			JOptionPane.showMessageDialog(contentPane,"Seleccione una persona primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIGestionPersona.class.getResource(mediador.getMediadorPrincipal().getApariencia().getInfo())));
		}
	}
	
	
	public void cargarDatos(){
		
		modelo = new DefaultTableModel();

		nombreColumnas = new Vector<String> ();
		nombreColumnas.add("ID");
		nombreColumnas.add("NOMBRE");
		nombreColumnas.add("APELLIDO");
		nombreColumnas.add("DNI");
		nombreColumnas.add("DIRECCION");
		nombreColumnas.add("TELEFONO");
		nombreColumnas.add("REGISTRO");
		nombreColumnas.add("ELIMINADO");

		datosTabla = new Vector<Vector<String>>();
		Vector<PersonaDTO> usuarios = mediador.obtenerPersonas(getFiltro());
		for (int i=0; i< usuarios.size();i++){
			Vector<String> row = new Vector<String> ();
			
			row.add(usuarios.elementAt(i).getId().toString());
			row.add(usuarios.elementAt(i).getNombre());
			row.add(usuarios.elementAt(i).getApellido());
			row.add(usuarios.elementAt(i).getDni_cuil_cuit());
			row.add(usuarios.elementAt(i).getDireccion());
			row.add(usuarios.elementAt(i).getTel_contacto());
			row.add(usuarios.elementAt(i).getRegistro());
			if (usuarios.elementAt(i).getEliminado())row.add("SI");
			else row.add("NO");		
						
			datosTabla.add(row);
		};
		modelo.setDataVector(datosTabla, nombreColumnas);
		tableUsuarios = new JTable(modelo) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, false,false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
	}
	public void actualizarPantalla(){ 
		modelo.fireTableStructureChanged();
	}
	public void actualizarDatos(){
		datosTabla = new Vector<Vector<String>>();
		Vector<PersonaDTO> usuarios = mediador.obtenerPersonas(getFiltro());
		for (int i=0; i< usuarios.size();i++){
			Vector<String> row = new Vector<String> ();
			
			row.add(usuarios.elementAt(i).getId().toString());
			row.add(usuarios.elementAt(i).getNombre());
			row.add(usuarios.elementAt(i).getApellido());
			row.add(usuarios.elementAt(i).getDni_cuil_cuit());
			row.add(usuarios.elementAt(i).getDireccion());
			row.add(usuarios.elementAt(i).getTel_contacto());
			row.add(usuarios.elementAt(i).getRegistro());
			if (usuarios.elementAt(i).getEliminado())row.add("SI");
			else row.add("NO");		
						
			datosTabla.add(row);
		};
		modelo.setDataVector(datosTabla, nombreColumnas);
		actualizarPantalla();
	}
	

	private String getFiltro(){		
		String filtro = "";
		boolean filtro_armado = false;
		if (mediador.mediadorPrincipal.getUsuario().getPermiso()<2) filtro = ""; //TODO agregar filtro de q solo modifique elementos de la organizacion de la q pertenece el usuario
		
		if (tFnombre!=null && !tFnombre.getText().trim().isEmpty()) {
			if (filtro_armado) filtro+=" && ";
			filtro+="nombre.toUpperCase().indexOf(\""+tFnombre.getText().toUpperCase()+"\")>=0 ";
			filtro_armado=true;
		}
		if (tFApellido!=null && !tFApellido.getText().trim().isEmpty()) {
			if (filtro_armado) filtro+=" && ";
			filtro+="apellido.toUpperCase().indexOf(\""+tFApellido.getText().toUpperCase()+"\")>=0 ";
			filtro_armado=true;
		}
		if (tFDni!=null && !tFDni.getText().trim().isEmpty()) {
			if (filtro_armado) filtro+=" && ";
			filtro+="dni_cuil_cuit.toUpperCase().indexOf(\""+tFDni.getText().toUpperCase()+"\")>=0 ";
			filtro_armado=true;
		}
		if (!chckbxElim.isSelected()) {
			if (filtro_armado) filtro+=" && ";
			filtro+="eliminado==false";
		}
		return filtro;
	}
}
