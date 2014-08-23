/********************************************************
  This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *********************************************************/
package cliente.MenuPrincipal;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import cliente.utils.Constantes;
import cliente.utils.JPanel_Whit_Image;
import cliente.utils.TransparentPanel;

import java.awt.Insets;
import java.util.Calendar;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.Font;

public class GUIMenu_Principal extends JFrame{

	private static final long serialVersionUID = 1L;
	private MediadorPrincipal mediadorPrincipal;
	private JPanel contentPane;
	private JPanel panel_buttons;
	JTextField txtIngreseElDni;
	
	private java.util.Calendar calendario; 
	
	int dia, mes, año, hora, minutos, segundos;
	
	//menu
	JMenuBar menuBar;
	JMenu mnInicio;
	JMenuItem mntmLogin;
	JMenuItem mntmDesconectar;
	JMenuItem mntmSalir;
	JMenu mnUsuarios;
	JMenuItem mntmPerfil;
	JMenuItem mntmAltaUsuario;
	JMenuItem mntmGestionUsuario;
	JMenu mnRRHH;
	JMenuItem mntmAltaRRHH;
	JMenuItem mntmGestionRRHH;
	JMenuItem mntmGestionRRHHOrg;
	JMenu mnOrg;
	JMenuItem mntmAltaOrg;
	JMenuItem mntmGestionOrg;
	JMenuItem mntmGestionUserOrg;
	JMenu mnLugar;
	JMenuItem mntmAltaLugar;
	JMenuItem mntmGestionLugar;
	JMenu mnRegistro;
	JMenuItem mntmConsultar;
	JMenu mnAyuda;
	JMenuItem mntmCambiarSkin;
	JMenuItem mntmAcercaDe;
	JLabel lblReloj;
	

	JButton btnNuevaSolicitud;

	DefaultComboBoxModel<String> comboOrg = new DefaultComboBoxModel<String>();
	DefaultComboBoxModel<String> comboLugar = new DefaultComboBoxModel<String>();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox comboBox_lugar = new JComboBox(comboLugar);
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox comboBox_Org = new JComboBox(comboOrg);
	JTextPane txtpnDatosExtra = new JTextPane();

	//usuario	
	
	public GUIMenu_Principal(MediadorPrincipal mediadorPrincipal) throws Exception {
		this.mediadorPrincipal= mediadorPrincipal;		
		initialize();
	}

	@SuppressWarnings("deprecation")
	private void initialize() {
		setIconImage (new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Recursos/Iconos/favicon.ico")).getImage());
		
		
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);

		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				int eleccion = JOptionPane.showConfirmDialog(null, "Desea salir?","Salir",0,0,new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Recursos/Iconos/exit_login.png")));
				if ( eleccion == 0) {
					System.exit(0);
				}	
			}
		});
		
		menuBar = new JMenuBar();
		mnInicio = new JMenu("Inicio");
		mntmLogin = new JMenuItem("Login");
		mntmDesconectar = new JMenuItem("Desconectar");
		mntmSalir = new JMenuItem("Salir");
		mnUsuarios = new JMenu("Usuarios");
		mntmPerfil = new JMenuItem("Modificar Mis Datos");
		mntmAltaUsuario = new JMenuItem("Alta Usuario");
		mntmGestionUsuario = new JMenuItem("Gestionar Usuarios");
		mnRRHH = new JMenu("RRHH");
		mntmAltaRRHH = new JMenuItem("Alta RRHH");
		mntmGestionRRHH = new JMenuItem("Gestionar RRHH");
		mntmGestionRRHHOrg = new JMenuItem("Asignar a Org/Empresa");
		mnOrg = new JMenu("Organización/Empresa");
		mntmAltaOrg = new JMenuItem("Alta Org/Empresa");
		mntmGestionOrg = new JMenuItem("Gestionar Org/Empresas");
		mntmGestionUserOrg = new JMenuItem("Asignar Usuarios");
		mnLugar = new JMenu("Lugar");
		mntmAltaLugar = new JMenuItem("Alta Lugar");
		mntmGestionLugar = new JMenuItem("Gestionar Lugares");
		mnRegistro = new JMenu("Registro");
		mntmConsultar = new JMenuItem("Consultar");
		mnAyuda = new JMenu("Ayuda");
		mntmCambiarSkin= new JMenuItem("Cambiar Apariencia");
		mntmAcercaDe = new JMenuItem("Acerca de..");
		
		setJMenuBar(menuBar);
		menuBar.add(mnInicio);
		mnInicio.add(mntmLogin);
		mnInicio.add(mntmDesconectar);
		mnInicio.add(mntmSalir);
		menuBar.add(mnUsuarios);
		mnUsuarios.add(mntmPerfil);
		mnUsuarios.add(mntmAltaUsuario);
		mnUsuarios.add(mntmGestionUsuario);
		menuBar.add(mnRRHH);
		mnRRHH.add(mntmAltaRRHH);
		mnRRHH.add(mntmGestionRRHH);
		mnRRHH.add(mntmGestionRRHHOrg);
		menuBar.add(mnOrg);
		mnOrg.add(mntmAltaOrg);
		mnOrg.add(mntmGestionOrg);
		mnOrg.add(mntmGestionUserOrg);
		menuBar.add(mnLugar);
		mnLugar.add(mntmAltaLugar);
		mnLugar.add(mntmGestionLugar);
		menuBar.add(mnRegistro);
		mnRegistro.add(mntmConsultar);
		menuBar.add(mnAyuda);
		mnAyuda.add(mntmCambiarSkin);
		mnAyuda.add(mntmAcercaDe);
		
		
		mntmDesconectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				mntmLogin.setVisible(true);
				mediadorPrincipal.reiniciar();
			}
		});
		
		mntmLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.lanzar_login();
			}
		});
		
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				mediadorPrincipal.salir();
			}
		});
		// MENU USUARIOS //
		mntmPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.modificarPerfil();
			}
		});
		
		mntmAltaUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.altaUsuario();
			}
		});
		
		mntmGestionUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.gestionUsuario();
			}
		});
		
		// MENU REGISTRANTES //
		
		mntmAltaRRHH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				mediadorPrincipal.altaPersona();
			}
		});
		
		mntmGestionRRHH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				mediadorPrincipal.gestionPersona();
			}
		});
		
		mntmGestionRRHHOrg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				mediadorPrincipal.gestionPersonalOrg();
			}
		});
		
		mntmAltaOrg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				mediadorPrincipal.altaOrg();
			}
		});
		
		mntmGestionOrg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				mediadorPrincipal.gestionOrganizacion();
			}
		});

		mntmGestionUserOrg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				mediadorPrincipal.gestionUsuarioOrganizacion();
			}
		});
		
		mntmAltaLugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				mediadorPrincipal.altaLugar();
			}
		});
		
		mntmGestionLugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.gestionarLugar();
			}
		});
		
		mntmConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.consultarRegistro();
			}
		});
			

		mntmCambiarSkin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.cambiarApariencia();
			}
		});
		
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,"IT10 Cooperativa - www.it10coop.com.ar","Acerca de..",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/it10.png")));
			}
		});
		
		contentPane = new JPanel_Whit_Image("/cliente/Recursos/Imagenes/fondo.jpg");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel_buttons = new TransparentPanel();
		contentPane.add(panel_buttons, BorderLayout.CENTER);
		GridBagLayout gbl_panel_buttons = new GridBagLayout();
		gbl_panel_buttons.columnWidths = new int[] {76, 257, 288, 0};
		gbl_panel_buttons.rowHeights = new int[] {22, 37, 40, 40, 188, 59, 0, 0};
		gbl_panel_buttons.columnWeights = new double[]{0.0, 0.0, 1.0};
		gbl_panel_buttons.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0};
		panel_buttons.setLayout(gbl_panel_buttons);
		
		JLabel lblOrganizacionempresa = new JLabel("Organización/Empresa *");
		GridBagConstraints gbc_lblOrganizacionempresa = new GridBagConstraints();
		gbc_lblOrganizacionempresa.gridwidth = 2;
		gbc_lblOrganizacionempresa.fill = GridBagConstraints.VERTICAL;
		gbc_lblOrganizacionempresa.insets = new Insets(0, 0, 5, 5);
		gbc_lblOrganizacionempresa.anchor = GridBagConstraints.EAST;
		gbc_lblOrganizacionempresa.gridx = 0;
		gbc_lblOrganizacionempresa.gridy = 1;
		panel_buttons.add(lblOrganizacionempresa, gbc_lblOrganizacionempresa);
		
		GridBagConstraints gbc_comboBox_Org = new GridBagConstraints();
		gbc_comboBox_Org.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_Org.fill = GridBagConstraints.BOTH;
		gbc_comboBox_Org.gridx = 2;
		gbc_comboBox_Org.gridy = 1;
		panel_buttons.add(comboBox_Org, gbc_comboBox_Org);
		
		JLabel lblLugarDondeDesempea = new JLabel("Lugar donde desempeña la tarea *");
		GridBagConstraints gbc_lblLugarDondeDesempea = new GridBagConstraints();
		gbc_lblLugarDondeDesempea.gridwidth = 2;
		gbc_lblLugarDondeDesempea.fill = GridBagConstraints.VERTICAL;
		gbc_lblLugarDondeDesempea.insets = new Insets(0, 0, 5, 5);
		gbc_lblLugarDondeDesempea.anchor = GridBagConstraints.EAST;
		gbc_lblLugarDondeDesempea.gridx = 0;
		gbc_lblLugarDondeDesempea.gridy = 2;
		panel_buttons.add(lblLugarDondeDesempea, gbc_lblLugarDondeDesempea);
		
		GridBagConstraints gbc_comboBox_lugar = new GridBagConstraints();
		gbc_comboBox_lugar.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_lugar.fill = GridBagConstraints.BOTH;
		gbc_comboBox_lugar.gridx = 2;
		gbc_comboBox_lugar.gridy = 2;
		panel_buttons.add(comboBox_lugar, gbc_comboBox_lugar);
		
		JLabel lblDni = new JLabel("DNI *");
		GridBagConstraints gbc_lblDni = new GridBagConstraints();
		gbc_lblDni.gridwidth = 2;
		gbc_lblDni.fill = GridBagConstraints.VERTICAL;
		gbc_lblDni.insets = new Insets(0, 0, 5, 5);
		gbc_lblDni.anchor = GridBagConstraints.EAST;
		gbc_lblDni.gridx = 0;
		gbc_lblDni.gridy = 3;
		panel_buttons.add(lblDni, gbc_lblDni);
		
		txtIngreseElDni = new JTextField();
		GridBagConstraints gbc_txtIngreseElDni = new GridBagConstraints();
		gbc_txtIngreseElDni.insets = new Insets(0, 0, 5, 5);
		gbc_txtIngreseElDni.fill = GridBagConstraints.BOTH;
		gbc_txtIngreseElDni.gridx = 2;
		gbc_txtIngreseElDni.gridy = 3;
		panel_buttons.add(txtIngreseElDni, gbc_txtIngreseElDni);
		txtIngreseElDni.setColumns(10);
		
		JLabel lblInformacinExtra = new JLabel("Información Extra");
		GridBagConstraints gbc_lblInformacinExtra = new GridBagConstraints();
		gbc_lblInformacinExtra.gridwidth = 2;
		gbc_lblInformacinExtra.fill = GridBagConstraints.VERTICAL;
		gbc_lblInformacinExtra.anchor = GridBagConstraints.EAST;
		gbc_lblInformacinExtra.insets = new Insets(0, 0, 5, 5);
		gbc_lblInformacinExtra.gridx = 0;
		gbc_lblInformacinExtra.gridy = 4;
		panel_buttons.add(lblInformacinExtra, gbc_lblInformacinExtra);
		
		GridBagConstraints gbc_txtpnDatosExtra = new GridBagConstraints();
		gbc_txtpnDatosExtra.insets = new Insets(0, 0, 5, 5);
		gbc_txtpnDatosExtra.fill = GridBagConstraints.BOTH;
		gbc_txtpnDatosExtra.gridx = 2;
		gbc_txtpnDatosExtra.gridy = 4;
		panel_buttons.add(txtpnDatosExtra, gbc_txtpnDatosExtra);
		
		btnNuevaSolicitud = new JButton("FICHAR");
		btnNuevaSolicitud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!comboBox_Org.getSelectedItem().toString().equals(Constantes.comboVacio) &&
					!comboBox_lugar.getSelectedItem().toString().equals(Constantes.comboVacio)
					){
					String msj ="¿Esta seguro que quiere fichar el dni '"+txtIngreseElDni.getText()+"' en la ubicacion:'"+comboBox_lugar.getSelectedItem().toString()+"'\n para la organizacion '"+comboBox_Org.getSelectedItem().toString()+"'?";
					if (JOptionPane.showConfirmDialog(null,msj, "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon(GUIMenu_Principal.class.getResource(mediadorPrincipal.getApariencia().getAyuda()))) == JOptionPane.YES_OPTION){
						mediadorPrincipal.fichar(	comboBox_Org.getSelectedItem().toString(),
													comboBox_lugar.getSelectedItem().toString(),
													txtIngreseElDni.getText(),txtpnDatosExtra.getText());
					}
				}
			}
		});
		
		lblReloj = new JLabel("");
		lblReloj.setFont(new Font("Dialog", Font.BOLD, 24));
		GridBagConstraints gbc_lblReloj = new GridBagConstraints();
		gbc_lblReloj.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblReloj.insets = new Insets(0, 0, 5, 5);
		gbc_lblReloj.gridx = 1;
		gbc_lblReloj.gridy = 5;
		panel_buttons.add(lblReloj, gbc_lblReloj);
		GridBagConstraints gbc_btnNuevaSolicitud = new GridBagConstraints();
		gbc_btnNuevaSolicitud.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNuevaSolicitud.insets = new Insets(0, 0, 5, 5);
		gbc_btnNuevaSolicitud.gridx = 2;
		gbc_btnNuevaSolicitud.gridy = 5;
		panel_buttons.add(btnNuevaSolicitud, gbc_btnNuevaSolicitud);
		cargar_menu();
		reloj();
		contentPane.show();
		
	}

	public MediadorPrincipal getMediadorPrincipal() {
		return mediadorPrincipal;
	}

	public void setMediadorPrincipal(MediadorPrincipal mediadorPrincipal) {
		this.mediadorPrincipal = mediadorPrincipal;
	}

	
	public void cargar_menu(){
		int permiso=cargarTitulo();
		if (permiso==0) {
			vistaPermiso_0();
		} else {
			if (permiso==1) {
				vistaPermiso_1();
			} else {
				if (permiso==2) {
					vistaPermiso_2();
				} else {
					vistaDeslogeado();
				}
			}
		}
		menuBar.repaint();
	}
	
	public int cargarTitulo(){
		try {
			String titulo = "Sistema de Registro";
			int permiso=-1;
			if (mediadorPrincipal.getUsuario()!=null){
				titulo+=" - USUARIO: "+mediadorPrincipal.getUsuario().getNombre_usuario().toString() +" [ID: "+mediadorPrincipal.getUsuario().getId().toString()+" ]";
				permiso = mediadorPrincipal.getUsuario().getPermiso();
			}
			setTitle(titulo);
			return permiso;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return-1;
	}
	
	public void vistaDeslogeado(){
		menuBar.setVisible(true);
		mnInicio.setVisible(true);
		mntmLogin.setVisible(true);
		mntmDesconectar.setVisible(false);
		mntmSalir.setVisible(true);
		mnUsuarios.setVisible(false);
		mntmPerfil.setVisible(false);
		mntmAltaUsuario.setVisible(false);
		mntmGestionUsuario.setVisible(false);
		mnRRHH.setVisible(false);
		mntmAltaRRHH.setVisible(false);
		mntmGestionRRHH.setVisible(false);
		mntmGestionRRHHOrg.setVisible(false);
		mnOrg.setVisible(false);
		mntmAltaOrg.setVisible(false);
		mntmGestionOrg.setVisible(false);
		mntmGestionUserOrg.setVisible(false);
		mnLugar.setVisible(false);
		mntmAltaLugar.setVisible(false);
		mntmGestionLugar.setVisible(false);
		mnRegistro.setVisible(false);
		mntmConsultar.setVisible(false);
		mnAyuda.setVisible(true);
		mntmCambiarSkin.setVisible(false);
		mntmAcercaDe.setVisible(true);
	}
	
	public void vistaPermiso_0(){
		menuBar.setVisible(true);
		mnInicio.setVisible(true);
		mntmLogin.setVisible(false);
		mntmDesconectar.setVisible(true);
		mntmSalir.setVisible(true);
		mnUsuarios.setVisible(true);
		mntmPerfil.setVisible(true);
		mntmAltaUsuario.setVisible(false);
		mntmGestionUsuario.setVisible(false);
		mnRRHH.setVisible(false);
		mntmAltaRRHH.setVisible(false);
		mntmGestionRRHH.setVisible(false);
		mntmGestionRRHHOrg.setVisible(false);
		mnOrg.setVisible(false);
		mntmAltaOrg.setVisible(false);
		mntmGestionOrg.setVisible(false);
		mntmGestionUserOrg.setVisible(false);
		mnLugar.setVisible(false);
		mntmAltaLugar.setVisible(false);
		mntmGestionLugar.setVisible(false);
		mnRegistro.setVisible(true);
		mntmConsultar.setVisible(true);
		mnAyuda.setVisible(true);
		mntmCambiarSkin.setVisible(false);
		mntmAcercaDe.setVisible(true);
	}

	public void vistaPermiso_1(){
		menuBar.setVisible(true);
		mnInicio.setVisible(true);
		mntmLogin.setVisible(false);
		mntmDesconectar.setVisible(true);
		mntmSalir.setVisible(true);
		mnUsuarios.setVisible(true);
		mntmPerfil.setVisible(true);
		mntmAltaUsuario.setVisible(false);
		mntmGestionUsuario.setVisible(false);
		mnRRHH.setVisible(true);
		mntmAltaRRHH.setVisible(true);
		mntmGestionRRHH.setVisible(true);
		mntmGestionRRHHOrg.setVisible(true);
		mnOrg.setVisible(false);
		mntmAltaOrg.setVisible(false);
		mntmGestionOrg.setVisible(false);
		mntmGestionUserOrg.setVisible(false);
		mnLugar.setVisible(true);
		mntmAltaLugar.setVisible(true);
		mntmGestionLugar.setVisible(true);
		mnRegistro.setVisible(true);
		mntmConsultar.setVisible(true);
		mnAyuda.setVisible(true);
		mntmCambiarSkin.setVisible(false);
		mntmAcercaDe.setVisible(true);
	}

	public void vistaPermiso_2(){
		menuBar.setVisible(true);
		mnInicio.setVisible(true);
		mntmLogin.setVisible(false);
		mntmDesconectar.setVisible(true);
		mntmSalir.setVisible(true);
		mntmPerfil.setVisible(true);
		mnUsuarios.setVisible(true);
		mntmAltaUsuario.setVisible(true);
		mntmGestionUsuario.setVisible(true);
		mnRRHH.setVisible(true);
		mntmAltaRRHH.setVisible(true);
		mntmGestionRRHH.setVisible(true);
		mntmGestionRRHHOrg.setVisible(true);
		mnOrg.setVisible(true);
		mntmAltaOrg.setVisible(true);
		mntmGestionOrg.setVisible(true);
		mntmGestionUserOrg.setVisible(true);
		mnLugar.setVisible(true);
		mntmAltaLugar.setVisible(true);
		mntmGestionLugar.setVisible(true);
		mnRegistro.setVisible(true);
		mntmConsultar.setVisible(true);
		mnAyuda.setVisible(true);
		mntmCambiarSkin.setVisible(true);
		mntmAcercaDe.setVisible(true);
	}

	private void reloj() { 
		calendario = new java.util.GregorianCalendar(); 
		segundos = calendario.get(Calendar.SECOND); 
		javax.swing.Timer timer = new javax.swing.Timer(1000, new java.awt.event.ActionListener() { 
			@ Override 
			public void actionPerformed(java.awt.event.ActionEvent ae) { 
				java.util.Date actual = new java.util.Date(); 
				calendario.setTime(actual); 
				dia = calendario.get(Calendar.DAY_OF_MONTH); 
				mes = (calendario.get(Calendar.MONTH) + 1); 
				año = calendario.get(Calendar.YEAR); 
				hora = calendario.get(Calendar.HOUR_OF_DAY); 
				minutos = calendario.get(Calendar.MINUTE); 
				segundos = calendario.get(Calendar.SECOND); 
				String hour = String.format("%02d : %02d : %02d", hora, minutos, segundos); 
				String date = String.format("%02d / %02d / %02d", dia, mes, año); 
				lblReloj.setText("<html><center>" + hour + "<br>" + date+"</center></html>"); 
			} 
		}); 
		timer.start(); 
	} 

	public void cargarImagenes(){
		btnNuevaSolicitud.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource(mediadorPrincipal.getApariencia().getFichar())));
	}
	
	public void lanzarJdialog (int nroMsj, String msj){
		if (nroMsj==0)JOptionPane.showMessageDialog(this,"HORA DE LLEGADA :"+msj,"Se Ficho Correctamente",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIMenu_Principal.class.getResource(mediadorPrincipal.getApariencia().getOk())));
		if (nroMsj==1)JOptionPane.showMessageDialog(this,"HORA DE SALIDA :"+msj,"Se Ficho Correctamente",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIMenu_Principal.class.getResource(mediadorPrincipal.getApariencia().getOk())));
		if (nroMsj==2)JOptionPane.showMessageDialog(this,"La persona no esta registrada en la organizacion, revise los datos e intentelo nuevamente.","Error al Fichar",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIMenu_Principal.class.getResource(mediadorPrincipal.getApariencia().getError())));
		if (nroMsj==3)JOptionPane.showMessageDialog(this,"la persona no esta registrada en el sistema, revise los datos e intentelo nuevamente.","Error al Fichar",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIMenu_Principal.class.getResource(mediadorPrincipal.getApariencia().getError())));
		if (nroMsj==4)JOptionPane.showMessageDialog(this,"lugar no valido, revise los datos e intentelo nuevamente.","Error al Fichar",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIMenu_Principal.class.getResource(mediadorPrincipal.getApariencia().getError())));
		if (nroMsj==5)JOptionPane.showMessageDialog(this,"Ocurrio un error la fichar, revise los datos e intentelo nuevamente.","Error al Fichar",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIMenu_Principal.class.getResource(mediadorPrincipal.getApariencia().getError())));
	}
}
