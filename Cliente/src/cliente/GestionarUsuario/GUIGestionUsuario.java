package cliente.GestionarUsuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import comun.DTO.UsuarioDTO;

public class GUIGestionUsuario extends JFrame {

	

	private static final long serialVersionUID = 1L;
	
	private MediadorUsuario mediador;
	
	private JPanel contentPane;
	
	private JButton btnModificar; 
	private JButton btnEliminar; 
	private JButton btnAgregar; 
	private JButton btnImprimir; 
	private JButton btnVolver; 
	private JButton btnBuscar; 
	
	//Tabla
	private DefaultTableModel modelo;
	private JTable tableUsuarios;
	private Vector<Vector<String>> datosTabla;
	private Vector<String> nombreColumnas; 
	private JLabel lblNombreDeUsuario;
	private JTextField tFUser;

	public GUIGestionUsuario(MediadorUsuario mediador) {
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
		setTitle("GESTIONAR USUARIOS");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setIcon(new ImageIcon(GUIGestionUsuario.class.getResource(mediador.getMediadorPrincipal().getApariencia().getEditar())));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
			}
		});
		btnModificar.setBounds(493, 64, 220, 38);
		contentPane.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(GUIGestionUsuario.class.getResource(mediador.getMediadorPrincipal().getApariencia().getEliminar())));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
			}
		});
		
		btnEliminar.setBounds(493, 114, 220, 38);
		contentPane.add(btnEliminar);
		
		
		modelo = new DefaultTableModel(datosTabla, nombreColumnas);
		
		tableUsuarios = new JTable(modelo) {
			@SuppressWarnings("unused")
			boolean[] columnEditables = new boolean[] {
				false, false, false, false,false, false, false, false,false
			};
			/*public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}*/
		};
		// Agregamos el ordenador para las tablas de los usuarios
		TableRowSorter<TableModel> ordenador = new TableRowSorter<TableModel>(modelo);
		tableUsuarios.setRowSorter(ordenador);
		//
		tableUsuarios.getTableHeader().setReorderingAllowed(false);
		tableUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableUsuarios.setBounds(0, 0, 765, 320);
		
		JScrollPane scrollPaneTabla = new JScrollPane(tableUsuarios);

		scrollPaneTabla.setBounds(12, 177, 764, 271);
		contentPane.add(scrollPaneTabla);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setIcon(new ImageIcon(GUIGestionUsuario.class.getResource(mediador.getMediadorPrincipal().getApariencia().getAgregar())));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediador.altaUsuario();
			}
		});
		btnAgregar.setBounds(493, 14, 220, 38);
		contentPane.add(btnAgregar);
		
		btnImprimir = new JButton("Imprimir");
		btnImprimir.setIcon(new ImageIcon(GUIGestionUsuario.class.getResource(mediador.getMediadorPrincipal().getApariencia().getImprimir())));
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					tableUsuarios.print();
				} catch (PrinterException e) {
					e.printStackTrace();
				}
			}
		});
		btnImprimir.setBounds(225, 460, 150, 38);
		contentPane.add(btnImprimir);
		
		btnVolver = new JButton("Volver");
		btnVolver.setIcon(new ImageIcon(GUIGestionUsuario.class.getResource(mediador.getMediadorPrincipal().getApariencia().getVolver())));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediador.mediadorPrincipal.getGui_menu_Principal().setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(398, 460, 150, 38);
		contentPane.add(btnVolver);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setIcon(new ImageIcon(GUIGestionUsuario.class.getResource(mediador.getMediadorPrincipal().getApariencia().getBuscar())));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarDatos();
			}
		});
		btnBuscar.setBounds(225, 64, 201, 38);
		contentPane.add(btnBuscar);
		
		lblNombreDeUsuario = new JLabel("Nombre de Usuario");
		lblNombreDeUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreDeUsuario.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblNombreDeUsuario.setBounds(36, 32, 141, 24);
		contentPane.add(lblNombreDeUsuario);
		
		tFUser = new JTextField();
		tFUser.setColumns(10);
		tFUser.setBounds(196, 35, 230, 20);
		contentPane.add(tFUser);
		

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
			if (JOptionPane.showConfirmDialog(null, "¿Modificar Usuario [ID:"+id+"]?", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon(GUIGestionUsuario.class.getResource(mediador.getMediadorPrincipal().getApariencia().getAyuda()))) == JOptionPane.YES_OPTION){ 
				mediador.modifUsuario(id);
				actualizarDatos();
			}
		}else{
			JOptionPane.showMessageDialog(contentPane,"Seleccione un Usuario primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIGestionUsuario.class.getResource(mediador.getMediadorPrincipal().getApariencia().getInfo())));
		}
	}
	public void eliminar(){
		int row = tableUsuarios.getSelectedRow();
		if (row >= 0) {
			int aux = tableUsuarios.convertRowIndexToModel(row);
			Long id = new Long (tableUsuarios.getValueAt(aux, 0).toString());
			if (id!=1){
				if (JOptionPane.showConfirmDialog(null, "�Eliminar Usuario [ID:"+id+"]?", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon(GUIGestionUsuario.class.getResource(mediador.getMediadorPrincipal().getApariencia().getAyuda()))) == JOptionPane.YES_OPTION){ 
					if (mediador.eliminarUsuario(id)){
						JOptionPane.showMessageDialog(contentPane,"Usuario eliminado.","Advertencia",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIGestionUsuario.class.getResource(mediador.getMediadorPrincipal().getApariencia().getOk())));
						actualizarDatos();
					}
				}
			}
			else
				JOptionPane.showMessageDialog(contentPane,"No puede eliminar al Administrador Maestro.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIGestionUsuario.class.getResource(mediador.getMediadorPrincipal().getApariencia().getError())));	
		}else{
			JOptionPane.showMessageDialog(contentPane,"Seleccione un usuario primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIGestionUsuario.class.getResource(mediador.getMediadorPrincipal().getApariencia().getInfo())));
		}
	}
	
	
	public void cargarDatos(){
		
		modelo = new DefaultTableModel();

		nombreColumnas = new Vector<String> ();
		nombreColumnas.add("ID");
		nombreColumnas.add("NOMBRE DE USUARIO");

		datosTabla = new Vector<Vector<String>>();
		Vector<UsuarioDTO> usuarios = mediador.obtenerUsuarios(getFiltro());
		for (int i=0; i< usuarios.size();i++){
			Vector<String> row = new Vector<String> ();
			
			row.add(usuarios.elementAt(i).getId().toString());
			row.add(usuarios.elementAt(i).getNombre_usuario());
						
			datosTabla.add(row);
		};
		modelo.setDataVector(datosTabla, nombreColumnas);
		tableUsuarios = new JTable(modelo) {
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("unused")
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			/*public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}*/
		};
	}
	public void actualizarPantalla(){ 
		modelo.fireTableStructureChanged();
	}
	public void actualizarDatos(){
		datosTabla = new Vector<Vector<String>>();
		Vector<UsuarioDTO> usuarios = mediador.obtenerUsuarios(getFiltro());
		for (int i=0; i< usuarios.size();i++){
			Vector<String> row = new Vector<String> ();
			
			row.add(usuarios.elementAt(i).getId().toString());
			row.add(usuarios.elementAt(i).getNombre_usuario());
						
			datosTabla.add(row);
		};
		modelo.setDataVector(datosTabla, nombreColumnas);
		actualizarPantalla();
	}
	

	private String getFiltro(){		
		String filtro = "";
		boolean filtro_armado = false;
		if (tFUser!=null && !tFUser.getText().trim().isEmpty()) {
			if (filtro_armado) filtro+=" && ";
			filtro+="nombre_usuario.toUpperCase().indexOf(\""+tFUser.getText().toUpperCase()+"\")>=0 ";
			filtro_armado=true;
		}
		return filtro;
	}
}
