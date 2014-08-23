package cliente.GestionarPersona;

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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JCheckBox;

import comun.DTO.PersonaDTO;

public class GUIPersona extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MediadorPersona mediador;
	private JCheckBox chckbxReactivar = new JCheckBox("");
	private JTextField tFDni;
	private JTextField tFRegistro;
	private JTextField tFApellido;
	private JTextField tFNombre;
	private JTextField tFTel;
	PersonaDTO pers;
	private JTextField tfDireccion;

	/**
	 * @wbp.parser.constructor
	 */
	public GUIPersona(final MediadorPersona medidador, PersonaDTO pers) {
		this.mediador = medidador;		
		initialize();
		this.pers = pers;
		cargarDatos(pers);
		setVisible(true);
	}
	

	public GUIPersona(final MediadorPersona medidador) {
		this.mediador = medidador;
		initialize();
		setVisible(true);
	}
	
	private void initialize() {
		//setIconImage(Toolkit.getDefaultToolkit().getImage(GUIUsuario.class.getResource("/cliente/imagenes/add_user.ico")));
		setTitle("PERSONA");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setSize(350, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{131, 17, 133};
		gbl_contentPane.rowHeights = new int[]{26, 26, 26, 20, 20, 0, 26, 49, 16, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		contentPane.setVisible(true);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediador.cerrarFormulario();
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
		
		JLabel lblApellido = new JLabel("Apellido");
		GridBagConstraints gbc_lblApellido = new GridBagConstraints();
		gbc_lblApellido.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellido.gridx = 0;
		gbc_lblApellido.gridy = 1;
		contentPane.add(lblApellido, gbc_lblApellido);
		
		tFApellido = new JTextField();
		GridBagConstraints gbc_tFApellido = new GridBagConstraints();
		gbc_tFApellido.insets = new Insets(0, 0, 5, 0);
		gbc_tFApellido.fill = GridBagConstraints.HORIZONTAL;
		gbc_tFApellido.gridx = 2;
		gbc_tFApellido.gridy = 1;
		contentPane.add(tFApellido, gbc_tFApellido);
		tFApellido.setColumns(10);
		
		JLabel lblRegistro = new JLabel("Registro");
		GridBagConstraints gbc_lblRegistro = new GridBagConstraints();
		gbc_lblRegistro.insets = new Insets(0, 0, 5, 5);
		gbc_lblRegistro.gridx = 0;
		gbc_lblRegistro.gridy = 2;
		contentPane.add(lblRegistro, gbc_lblRegistro);
		
		tFRegistro = new JTextField();
		GridBagConstraints gbc_tFRegistro = new GridBagConstraints();
		gbc_tFRegistro.insets = new Insets(0, 0, 5, 0);
		gbc_tFRegistro.fill = GridBagConstraints.HORIZONTAL;
		gbc_tFRegistro.gridx = 2;
		gbc_tFRegistro.gridy = 2;
		contentPane.add(tFRegistro, gbc_tFRegistro);
		tFRegistro.setColumns(10);
		
		JLabel lblDni = new JLabel("DNI");
		GridBagConstraints gbc_lblDni = new GridBagConstraints();
		gbc_lblDni.insets = new Insets(0, 0, 5, 5);
		gbc_lblDni.gridx = 0;
		gbc_lblDni.gridy = 3;
		contentPane.add(lblDni, gbc_lblDni);
		
		tFDni = new JTextField();
		GridBagConstraints gbc_tFDni = new GridBagConstraints();
		gbc_tFDni.insets = new Insets(0, 0, 5, 0);
		gbc_tFDni.fill = GridBagConstraints.HORIZONTAL;
		gbc_tFDni.gridx = 2;
		gbc_tFDni.gridy = 3;
		contentPane.add(tFDni, gbc_tFDni);
		tFDni.setColumns(10);
		
		JLabel lblEmail = new JLabel("Telefono");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.fill = GridBagConstraints.BOTH;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 4;
		contentPane.add(lblEmail, gbc_lblEmail);
		
		tFTel = new JTextField();
		tFTel.setColumns(10);
		GridBagConstraints gbc_tFTel = new GridBagConstraints();
		gbc_tFTel.insets = new Insets(0, 0, 5, 0);
		gbc_tFTel.fill = GridBagConstraints.HORIZONTAL;
		gbc_tFTel.gridx = 2;
		gbc_tFTel.gridy = 4;
		contentPane.add(tFTel, gbc_tFTel);
		
		JLabel lblDireccion = new JLabel("Direccion");
		GridBagConstraints gbc_lblDireccion = new GridBagConstraints();
		gbc_lblDireccion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDireccion.gridx = 0;
		gbc_lblDireccion.gridy = 5;
		contentPane.add(lblDireccion, gbc_lblDireccion);
		
		tfDireccion = new JTextField();
		GridBagConstraints gbc_tfDireccion = new GridBagConstraints();
		gbc_tfDireccion.insets = new Insets(0, 0, 5, 0);
		gbc_tfDireccion.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfDireccion.gridx = 2;
		gbc_tfDireccion.gridy = 5;
		contentPane.add(tfDireccion, gbc_tfDireccion);
		tfDireccion.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Reactivar");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 6;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		chckbxReactivar.setEnabled(false);
		GridBagConstraints gbc_chckbxReactivar = new GridBagConstraints();
		gbc_chckbxReactivar.anchor = GridBagConstraints.WEST;
		gbc_chckbxReactivar.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxReactivar.gridx = 2;
		gbc_chckbxReactivar.gridy = 6;
		contentPane.add(chckbxReactivar, gbc_chckbxReactivar);
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.fill = GridBagConstraints.VERTICAL;
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelar.gridwidth = 2;
		gbc_btnCancelar.gridx = 0;
		gbc_btnCancelar.gridy = 7;
		contentPane.add(btnCancelar, gbc_btnCancelar);
		
		JButton btnAceptarUsuario = new JButton("Aceptar");
		btnAceptarUsuario.setIcon(new ImageIcon(GUIPersona.class.getResource(mediador.getMediadorPrincipal().getApariencia().getAceptar())));
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
		gbc_btnCrearUsuario.insets = new Insets(0, 0, 5, 0);
		gbc_btnCrearUsuario.anchor = GridBagConstraints.WEST;
		gbc_btnCrearUsuario.fill = GridBagConstraints.VERTICAL;
		gbc_btnCrearUsuario.gridx = 2;
		gbc_btnCrearUsuario.gridy = 7;
		contentPane.add(btnAceptarUsuario, gbc_btnCrearUsuario);

		btnCancelar.setIcon(new ImageIcon(GUIPersona.class.getResource(mediador.getMediadorPrincipal().getApariencia().getCancelar())));
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
	

	private void cargarDatos(PersonaDTO p) {
		tFNombre.setText(p.getNombre().toUpperCase());
		tFApellido.setText(p.getApellido().toUpperCase());
		tFRegistro.setText(p.getRegistro().toUpperCase());
		tFTel.setText(p.getTel_contacto().toUpperCase());
		tfDireccion.setText(p.getDireccion().toUpperCase());
		tFDni.setText(p.getDni_cuil_cuit().toUpperCase());
		if (p.getEliminado())chckbxReactivar.setEnabled(true);		
	}
	
	public PersonaDTO personaDesdeDatos() throws Exception {
		PersonaDTO u= new PersonaDTO(	tFNombre.getText().toUpperCase(),tFApellido.getText().toUpperCase(),tFRegistro.getText().toUpperCase(),tFDni.getText().toUpperCase(),
										tFTel.getText().toUpperCase(),tfDireccion.getText().toUpperCase());
		if (pers!=null){
			u.setId(pers.getId());
			if (pers.getEliminado() && chckbxReactivar.isSelected()) u.setEliminado(false);
		}
		return u;
	}
	public PersonaDTO getPers() {
		return pers;
	}

	public void setPers(PersonaDTO p) {
		this.pers = p;
	}

	public void aceptar() throws Exception{
		if (tFNombre.getText().trim().isEmpty() ||tFApellido.getText().trim().isEmpty() || tFDni.getText().trim().isEmpty()){
			JOptionPane.showMessageDialog(contentPane,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIPersona.class.getResource(mediador.getMediadorPrincipal().getApariencia().getInfo())));
		}else{
			if (pers==null){ //si es un nuevo usuario
				if (!mediador.existeMismoDNI(null, tFDni.getText().toUpperCase())){
					PersonaDTO p = personaDesdeDatos();
					if (mediador.nuevoPersona(p)){
						JOptionPane.showMessageDialog(contentPane,"Persona Agregada.","Notificacion",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIPersona.class.getResource(mediador.getMediadorPrincipal().getApariencia().getOk())));
						mediador.cerrarFormulario();
						dispose();
					}else{
						JOptionPane.showMessageDialog(contentPane,"Error al Agregar. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIPersona.class.getResource(mediador.getMediadorPrincipal().getApariencia().getError())));
					}
				}else{
					JOptionPane.showMessageDialog(contentPane,"El dni ya existe. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIPersona.class.getResource(mediador.getMediadorPrincipal().getApariencia().getError())));
				}
			}else{
				if (!mediador.existeMismoDNI(pers.getId(), tFDni.getText().toUpperCase())){
					boolean continuar= true;
					if(pers.getEliminado()){
						if (chckbxReactivar.isSelected()) 
							if (JOptionPane.showConfirmDialog(null, "Desea Reactivar el Persona", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon(GUIPersona.class.getResource(mediador.getMediadorPrincipal().getApariencia().getAyuda()))) == JOptionPane.NO_OPTION)
								continuar = false;
					}
					if (continuar){
						PersonaDTO user = personaDesdeDatos();
						if (mediador.modificar(user)){
//								if (user.getId().equals(medidador.mediadorPrincipal.getUsuario().getId())){
//									medidador.mediadorPrincipal.setPersona(user);
//									medidador.mediadorPrincipal.getGui_menu_Principal().cargar_menu();
//								}
							JOptionPane.showMessageDialog(contentPane,"Usuario Modificado.","Notificacion",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIPersona.class.getResource(mediador.getMediadorPrincipal().getApariencia().getOk())));
							mediador.cerrarFormulario();
							dispose();
						}else{
							JOptionPane.showMessageDialog(contentPane,"Error al Modificar. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIPersona.class.getResource(mediador.getMediadorPrincipal().getApariencia().getError())));
						}
					}
				}else{
					JOptionPane.showMessageDialog(contentPane,"El nombre de usuario ya existe. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIPersona.class.getResource(mediador.getMediadorPrincipal().getApariencia().getError())));
				}
			}
		}
	}
}
