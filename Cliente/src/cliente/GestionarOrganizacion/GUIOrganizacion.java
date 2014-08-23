package cliente.GestionarOrganizacion;

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

import comun.DTO.OrganizacionDTO;

public class GUIOrganizacion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MediadorOrganizacion mediador;
	private JCheckBox chckbxReactivar = new JCheckBox("");
	private JTextField tFcuit;
	private JTextField tFNombre;
	OrganizacionDTO org;

	/**
	 * @wbp.parser.constructor
	 */
	public GUIOrganizacion(final MediadorOrganizacion medidador, OrganizacionDTO org) {
		this.mediador = medidador;		
		initialize();
		this.org = org;
		cargarDatos(org);
		setVisible(true);
	}
	

	public GUIOrganizacion(final MediadorOrganizacion medidador) {
		this.mediador = medidador;
		initialize();
		setVisible(true);
	}
	
	private void initialize() {
		//setIconImage(Toolkit.getDefaultToolkit().getImage(GUIUsuario.class.getResource("/cliente/imagenes/add_user.ico")));
		setTitle("Organizacion");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setSize(350, 200);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{131, 17, 133};
		gbl_contentPane.rowHeights = new int[]{26, 20, 26, 46, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		contentPane.setVisible(true);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(GUIOrganizacion.class.getResource(mediador.getMediadorPrincipal().getApariencia().getCancelar())));
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
		
		JLabel lblDni = new JLabel("CUIT");
		GridBagConstraints gbc_lblDni = new GridBagConstraints();
		gbc_lblDni.insets = new Insets(0, 0, 5, 5);
		gbc_lblDni.gridx = 0;
		gbc_lblDni.gridy = 1;
		contentPane.add(lblDni, gbc_lblDni);
		
		tFcuit = new JTextField();
		GridBagConstraints gbc_tFcuit = new GridBagConstraints();
		gbc_tFcuit.insets = new Insets(0, 0, 5, 0);
		gbc_tFcuit.fill = GridBagConstraints.HORIZONTAL;
		gbc_tFcuit.gridx = 2;
		gbc_tFcuit.gridy = 1;
		contentPane.add(tFcuit, gbc_tFcuit);
		tFcuit.setColumns(10);
		
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
		btnAceptarUsuario.setIcon(new ImageIcon(GUIOrganizacion.class.getResource(mediador.getMediadorPrincipal().getApariencia().getAceptar())));
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
	

	private void cargarDatos(OrganizacionDTO p) {
		tFNombre.setText(p.getNombre().toUpperCase());
		tFcuit.setText(p.getCuit().toUpperCase());
		if (p.isEliminado())chckbxReactivar.setEnabled(true);		
	}
	
	public OrganizacionDTO orgDesdeDatos() throws Exception {
		OrganizacionDTO u= new OrganizacionDTO(	tFNombre.getText().toUpperCase(),tFcuit.getText().toUpperCase());
		if (org!=null){
			u.setId(org.getId());
			if (org.isEliminado() && chckbxReactivar.isSelected()) u.setEliminado(false);
		}
		return u;
	}
	public OrganizacionDTO getOrg() {
		return org;
	}

	public void setOrg(OrganizacionDTO l) {
		this.org = l;
	}

	public void aceptar() throws Exception{
		if (tFNombre.getText().isEmpty() || tFcuit.getText().isEmpty()){
			JOptionPane.showMessageDialog(contentPane,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIOrganizacion.class.getResource(mediador.getMediadorPrincipal().getApariencia().getInfo())));
		}else{
			if (org==null){ //si es una Nueva Organizacion
				if (!mediador.existeMismoCUIT(null, tFcuit.getText().toUpperCase())){
					OrganizacionDTO o = orgDesdeDatos();
					if (mediador.nuevoOrganizacion(o)){
						JOptionPane.showMessageDialog(contentPane,"Organizacion Agregada.","Notificacion",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIOrganizacion.class.getResource(mediador.getMediadorPrincipal().getApariencia().getOk())));
						mediador.cerrarFormulario();
						dispose();
					}else{
						JOptionPane.showMessageDialog(contentPane,"Error al Agregar. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIOrganizacion.class.getResource(mediador.getMediadorPrincipal().getApariencia().getError())));
					}
				}else{
					JOptionPane.showMessageDialog(contentPane,"El dni ya existe. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIOrganizacion.class.getResource(mediador.getMediadorPrincipal().getApariencia().getError())));
				}
			}else{
				if (!mediador.existeMismoCUIT(org.getId(), tFcuit.getText().toUpperCase())){
					boolean continuar= true;
					if(org.isEliminado()){
						if (chckbxReactivar.isSelected()) 
							if (JOptionPane.showConfirmDialog(null, "Desea Reactivar el Organizacion", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon(GUIOrganizacion.class.getResource(mediador.getMediadorPrincipal().getApariencia().getAyuda()))) == JOptionPane.NO_OPTION)
								continuar = false;
					}
					if (continuar){
						OrganizacionDTO o = orgDesdeDatos();
						if (mediador.modificar(o)){
//								if (user.getId().equals(medidador.mediadorPrincipal.getUsuario().getId())){
//									medidador.mediadorPrincipal.setOrganizacion(user);
//									medidador.mediadorPrincipal.getGui_menu_Principal().cargar_menu();
//								}
							JOptionPane.showMessageDialog(contentPane,"Usuario Modificado.","Notificacion",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIOrganizacion.class.getResource(mediador.getMediadorPrincipal().getApariencia().getOk())));
							mediador.cerrarFormulario();
							dispose();
						}else{
							JOptionPane.showMessageDialog(contentPane,"Error al Modificar. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIOrganizacion.class.getResource(mediador.getMediadorPrincipal().getApariencia().getError())));
						}
					}
				}else{
					JOptionPane.showMessageDialog(contentPane,"El nombre de usuario ya existe. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIOrganizacion.class.getResource(mediador.getMediadorPrincipal().getApariencia().getError())));
				}
			}
		}
	}
}
