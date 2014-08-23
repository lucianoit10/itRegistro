package cliente.GestionarUsuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import comun.DTO.UsuarioDTO;
import comun.utils.GeneracionDeClaves;

public class GUIUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tFnombre_usuario;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldConfirm;
	private MediadorUsuario mediador;
	private JComboBox<String> comboBox;
	private String[] tiposUsuarios= new String[] {"Usuario", "Administrativo", "Maestro"};
	private int limite = 35;
	UsuarioDTO user;

	/**
	 * @wbp.parser.constructor
	 */
	public GUIUsuario(final MediadorUsuario medidador, UsuarioDTO user) {
		this.mediador = medidador;		
		initialize();
		this.user = user;
		cargarDatos(user);
		setVisible(true);
	}
	

	public GUIUsuario(final MediadorUsuario medidador) {
		this.mediador = medidador;
		initialize();
		setVisible(true);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		//setIconImage(Toolkit.getDefaultToolkit().getImage(GUIUsuario.class.getResource("/cliente/imagenes/add_user.ico")));
		setTitle("USUARIO");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setSize(484, 241);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{131, 26, 162, 118};
		gbl_contentPane.rowHeights = new int[]{20, 0, 20, 20, 26, 43, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		contentPane.setVisible(true);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(GUIUsuario.class.getResource(mediador.getMediadorPrincipal().getApariencia().getCancelar())));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediador.cerrarFormulario();
			}
		});
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre De Usuario");
		lblNombreDeUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNombreDeUsuario = new GridBagConstraints();
		gbc_lblNombreDeUsuario.fill = GridBagConstraints.BOTH;
		gbc_lblNombreDeUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreDeUsuario.gridx = 0;
		gbc_lblNombreDeUsuario.gridy = 0;
		contentPane.add(lblNombreDeUsuario, gbc_lblNombreDeUsuario);
		
		tFnombre_usuario = new JTextField();
		tFnombre_usuario.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if (tFnombre_usuario.getText().length()>=limite){
					e.consume();					
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
					try {
						aceptar();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		GridBagConstraints gbc_tFnombre_usuario = new GridBagConstraints();
		gbc_tFnombre_usuario.fill = GridBagConstraints.BOTH;
		gbc_tFnombre_usuario.insets = new Insets(0, 0, 5, 0);
		gbc_tFnombre_usuario.gridwidth = 2;
		gbc_tFnombre_usuario.gridx = 2;
		gbc_tFnombre_usuario.gridy = 0;
		contentPane.add(tFnombre_usuario, gbc_tFnombre_usuario);
		tFnombre_usuario.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblContrasea = new GridBagConstraints();
		gbc_lblContrasea.fill = GridBagConstraints.BOTH;
		gbc_lblContrasea.insets = new Insets(0, 0, 5, 5);
		gbc_lblContrasea.gridx = 0;
		gbc_lblContrasea.gridy = 1;
		contentPane.add(lblContrasea, gbc_lblContrasea);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyListener() {
			@SuppressWarnings("deprecation")
			public void keyTyped(KeyEvent e) {
				if (passwordField.getText().length()>=limite){
					e.consume();					
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.BOTH;
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.gridwidth = 2;
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 1;
		contentPane.add(passwordField, gbc_passwordField);
		
		JLabel lblConfirmar = new JLabel("Confirmar Contraseña");
		lblConfirmar.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblConfirmar = new GridBagConstraints();
		gbc_lblConfirmar.fill = GridBagConstraints.BOTH;
		gbc_lblConfirmar.insets = new Insets(0, 0, 5, 5);
		gbc_lblConfirmar.gridx = 0;
		gbc_lblConfirmar.gridy = 2;
		contentPane.add(lblConfirmar, gbc_lblConfirmar);
		
		passwordFieldConfirm = new JPasswordField();
		passwordFieldConfirm.addKeyListener(new KeyListener() {
			@SuppressWarnings("deprecation")
			public void keyTyped(KeyEvent e) {
				if (passwordFieldConfirm.getText().length()>=limite){
					e.consume();					
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
//				if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
//					try {
//						aceptar();
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		GridBagConstraints gbc_passwordFieldConfirm = new GridBagConstraints();
		gbc_passwordFieldConfirm.fill = GridBagConstraints.BOTH;
		gbc_passwordFieldConfirm.insets = new Insets(0, 0, 5, 0);
		gbc_passwordFieldConfirm.gridwidth = 2;
		gbc_passwordFieldConfirm.gridx = 2;
		gbc_passwordFieldConfirm.gridy = 2;
		contentPane.add(passwordFieldConfirm, gbc_passwordFieldConfirm);

		JLabel lblTipoUsuario = new JLabel("Tipo Usuario");
		lblTipoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblTipoUsuario = new GridBagConstraints();
		gbc_lblTipoUsuario.fill = GridBagConstraints.BOTH;
		gbc_lblTipoUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipoUsuario.gridx = 0;
		gbc_lblTipoUsuario.gridy = 3;
		contentPane.add(lblTipoUsuario, gbc_lblTipoUsuario);

		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel(tiposUsuarios));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.BOTH;
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.gridwidth = 2;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 3;
		contentPane.add(comboBox, gbc_comboBox);
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.fill = GridBagConstraints.VERTICAL;
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.gridwidth = 2;
		gbc_btnCancelar.gridx = 0;
		gbc_btnCancelar.gridy = 5;
		contentPane.add(btnCancelar, gbc_btnCancelar);
		
		JButton btnAceptarUsuario = new JButton("Aceptar");
		btnAceptarUsuario.setIcon(new ImageIcon(GUIUsuario.class.getResource(mediador.getMediadorPrincipal().getApariencia().getAceptar())));
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
		gbc_btnCrearUsuario.insets = new Insets(0, 0, 0, 5);
		gbc_btnCrearUsuario.fill = GridBagConstraints.BOTH;
		gbc_btnCrearUsuario.gridx = 2;
		gbc_btnCrearUsuario.gridy = 5;
		contentPane.add(btnAceptarUsuario, gbc_btnCrearUsuario);
		
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
				mediador.cerrarFormulario();
				dispose();
			}
		});
		setVisible(true);
		
	}
		
	@SuppressWarnings("deprecation")
	private boolean chequearContrasenia() {
		return (passwordField.getText().equals(passwordFieldConfirm.getText()));
	}

	public String[] getTiposUsuarios() {
		return tiposUsuarios;
	}

	public void setTiposUsuarios(String[] tiposUsuarios) {
		this.tiposUsuarios = tiposUsuarios;
	}
	

	private void cargarDatos(UsuarioDTO user) {
		tFnombre_usuario.setText(user.getNombre_usuario().trim());
		comboBox.setSelectedIndex(user.getPermiso());
		if (user.getId().byteValue()==mediador.getMediadorPrincipal().getUsuario().getId().byteValue() || user.getId()==1) comboBox.setEnabled(false);		
	}
	
	@SuppressWarnings("deprecation")
	public UsuarioDTO usuarioDesdeDatos() throws Exception {
		String pass ="";
		if (!passwordField.getText().isEmpty())pass=GeneracionDeClaves.MD5(passwordField.getText());
		UsuarioDTO u= new UsuarioDTO(tFnombre_usuario.getText().trim(),	pass);
		u.setPermiso(comboBox.getSelectedIndex());
		if (user!=null){
			u.setId(user.getId());
		}
		return u;
	}
	public UsuarioDTO getUser() {
		return user;
	}

	public void setUser(UsuarioDTO user) {
		this.user = user;
	}

	public void aceptar() throws Exception{
		if (tFnombre_usuario.getText().trim().isEmpty()){
			JOptionPane.showMessageDialog(contentPane,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIUsuario.class.getResource(mediador.getMediadorPrincipal().getApariencia().getInfo())));
		}else{
			if (chequearContrasenia()){
				if (user==null){ //si es un nuevo usuario
					if (!mediador.existeMismoNombreDeUsuario(null, tFnombre_usuario.getText().trim())){
						UsuarioDTO user = usuarioDesdeDatos();
						if (mediador.nuevoUsuario(user)){
							JOptionPane.showMessageDialog(contentPane,"Usuario Agregado.","Notificacion",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIUsuario.class.getResource(mediador.getMediadorPrincipal().getApariencia().getOk())));
							mediador.cerrarFormulario();
							dispose();
						}else{
							JOptionPane.showMessageDialog(contentPane,"Error al Agregar. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIUsuario.class.getResource(mediador.getMediadorPrincipal().getApariencia().getError())));
						}	
					}else{
						JOptionPane.showMessageDialog(contentPane,"El nombre de usuario ya existe. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIUsuario.class.getResource(mediador.getMediadorPrincipal().getApariencia().getError())));
					}
				}else{
					if (!mediador.existeMismoNombreDeUsuario(user.getId(), tFnombre_usuario.getText().trim())){
						UsuarioDTO user = usuarioDesdeDatos();
						if (mediador.modificar(user)){
							if (user.getId().equals(mediador.mediadorPrincipal.getUsuario().getId())){
								mediador.mediadorPrincipal.setUsuario(user);
								mediador.mediadorPrincipal.getGui_menu_Principal().cargar_menu();
							}
							JOptionPane.showMessageDialog(contentPane,"Usuario Modificado.","Notificacion",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIUsuario.class.getResource(mediador.getMediadorPrincipal().getApariencia().getOk())));
							mediador.cerrarFormulario();
							dispose();
						}else{
							JOptionPane.showMessageDialog(contentPane,"Error al Modificar. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIUsuario.class.getResource(mediador.getMediadorPrincipal().getApariencia().getError())));
						}
					}else{
						JOptionPane.showMessageDialog(contentPane,"El nombre de usuario ya existe. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIUsuario.class.getResource(mediador.getMediadorPrincipal().getApariencia().getError())));
					}
				}
				
			}else{
				JOptionPane.showMessageDialog(contentPane,"Las contraseñas no coinciden.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIUsuario.class.getResource(mediador.getMediadorPrincipal().getApariencia().getError())));
			}
		}
	}
}
