package cliente.GestionarLugar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JCheckBox;

import comun.DTO.LugarDTO;

public class GUILugar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MediadorLugar mediador;
	private JCheckBox chckbxReactivar = new JCheckBox("");
	private JTextField tFNombre;
	LugarDTO lugar;
	private JTextField tfDireccion;

	/**
	 * @wbp.parser.constructor
	 */
	public GUILugar(final MediadorLugar medidador, LugarDTO lugar) {
		this.mediador = medidador;		
		initialize();
		this.lugar = lugar;
		cargarDatos(lugar);
		setVisible(true);
	}
	

	public GUILugar(final MediadorLugar medidador) {
		this.mediador = medidador;
		initialize();
		setVisible(true);
	}
	
	private void initialize() {
		//setIconImage(Toolkit.getDefaultToolkit().getImage(GUIUsuario.class.getResource("/cliente/imagenes/add_user.ico")));
		setTitle("LUGAR");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setSize(350, 175);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{131, 17, 133};
		gbl_contentPane.rowHeights = new int[]{26, 0, 26, 40, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		contentPane.setVisible(true);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					mediador.cerrarFormulario();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
				
		JLabel lblNombre = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 0;
		contentPane.add(lblNombre, gbc_lblNombre);
				
		tFNombre = new JTextField();
		GridBagConstraints gbc_tFNombre = new GridBagConstraints();
		gbc_tFNombre.insets = new Insets(0, 0, 5, 0);
		gbc_tFNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_tFNombre.gridx = 2;
		gbc_tFNombre.gridy = 0;
		contentPane.add(tFNombre, gbc_tFNombre);
		tFNombre.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Direccion");
		GridBagConstraints gbc_lblDireccion = new GridBagConstraints();
		gbc_lblDireccion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDireccion.gridx = 0;
		gbc_lblDireccion.gridy = 1;
		contentPane.add(lblDireccion, gbc_lblDireccion);
		
		tfDireccion = new JTextField();
		GridBagConstraints gbc_tfDireccion = new GridBagConstraints();
		gbc_tfDireccion.insets = new Insets(0, 0, 5, 0);
		gbc_tfDireccion.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfDireccion.gridx = 2;
		gbc_tfDireccion.gridy = 1;
		contentPane.add(tfDireccion, gbc_tfDireccion);
		tfDireccion.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Reactivar");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 2;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		chckbxReactivar.setEnabled(false);
		GridBagConstraints gbc_chckbxReactivar = new GridBagConstraints();
		gbc_chckbxReactivar.anchor = GridBagConstraints.WEST;
		gbc_chckbxReactivar.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxReactivar.gridx = 2;
		gbc_chckbxReactivar.gridy = 2;
		contentPane.add(chckbxReactivar, gbc_chckbxReactivar);
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.fill = GridBagConstraints.VERTICAL;
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.gridwidth = 2;
		gbc_btnCancelar.gridx = 0;
		gbc_btnCancelar.gridy = 3;
		contentPane.add(btnCancelar, gbc_btnCancelar);
		
		JButton btnAceptarUsuario = new JButton("Aceptar");
		btnAceptarUsuario.setIcon(new ImageIcon(GUILugar.class.getResource(mediador.getMediadorPrincipal().getApariencia().getAceptar())));
		btnAceptarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					aceptar();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnCrearUsuario = new GridBagConstraints();
		gbc_btnCrearUsuario.anchor = GridBagConstraints.WEST;
		gbc_btnCrearUsuario.fill = GridBagConstraints.VERTICAL;
		gbc_btnCrearUsuario.gridx = 2;
		gbc_btnCrearUsuario.gridy = 3;
		contentPane.add(btnAceptarUsuario, gbc_btnCrearUsuario);

		btnCancelar.setIcon(new ImageIcon(GUILugar.class.getResource(mediador.getMediadorPrincipal().getApariencia().getCancelar())));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					mediador.cerrarFormulario();
					dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				try {
					mediador.cerrarFormulario();
				} catch (Exception e) {
					e.printStackTrace();
				}
				dispose();
			}
		});
		setVisible(true);
		
	}
	

	private void cargarDatos(LugarDTO p) {
		tFNombre.setText(p.getNombre());
		tfDireccion.setText(p.getDireccion());
		if (p.getEliminado())chckbxReactivar.setEnabled(true);		
	}
	
	public LugarDTO usuarioDesdeDatos() throws Exception {
		LugarDTO l= new LugarDTO(tFNombre.getText().toUpperCase(),tfDireccion.getText().toUpperCase());
		if (lugar!=null){
			l.setId(lugar.getId());
			if (lugar.getEliminado() && chckbxReactivar.isSelected()) l.setEliminado(false);
		}
		return l;
	}
	public LugarDTO getLugar() {
		return lugar;
	}

	public void setLugar(LugarDTO l) {
		this.lugar = l;
	}

	public void aceptar() throws Exception{
		if (tFNombre.getText().trim().isEmpty()){
			JOptionPane.showMessageDialog(contentPane,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUILugar.class.getResource(mediador.getMediadorPrincipal().getApariencia().getInfo())));
		}else{
			if (lugar==null){ //si es un nuevo lugar
				LugarDTO p = usuarioDesdeDatos();
				if (mediador.nuevoLugar(p)){
					JOptionPane.showMessageDialog(contentPane,"Lugar Agregado.","Notificacion",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUILugar.class.getResource(mediador.getMediadorPrincipal().getApariencia().getOk())));
					mediador.cerrarFormulario();
					dispose();
				}else{
					JOptionPane.showMessageDialog(contentPane,"Error al Agregar. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUILugar.class.getResource(mediador.getMediadorPrincipal().getApariencia().getError())));
				}
			}else{
				boolean continuar= true;
				if(lugar.getEliminado()){
					if (chckbxReactivar.isSelected()) 
						if (JOptionPane.showConfirmDialog(null, "Desea Reactivar el Lugar", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon(GUILugar.class.getResource(mediador.getMediadorPrincipal().getApariencia().getAyuda()))) == JOptionPane.NO_OPTION)
							continuar = false;
				}
				if (continuar){
					LugarDTO user = usuarioDesdeDatos();
					if (mediador.modificar(user)){
//								if (user.getId().equals(medidador.mediadorPrincipal.getUsuario().getId())){
//									medidador.mediadorPrincipal.setLugar(user);
//									medidador.mediadorPrincipal.getGui_menu_Principal().cargar_menu();
//								}
						JOptionPane.showMessageDialog(contentPane,"Usuario Modificado.","Notificacion",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUILugar.class.getResource(mediador.getMediadorPrincipal().getApariencia().getOk())));
						mediador.cerrarFormulario();
						dispose();
					}else{
						JOptionPane.showMessageDialog(contentPane,"Error al Modificar. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUILugar.class.getResource(mediador.getMediadorPrincipal().getApariencia().getError())));
					}
				}
			}
		}
	}
}
