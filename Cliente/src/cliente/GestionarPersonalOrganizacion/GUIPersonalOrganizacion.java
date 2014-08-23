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
package cliente.GestionarPersonalOrganizacion;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import cliente.utils.TransparentPanel;

import java.awt.Insets;

import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;

import comun.DTO.OrganizacionDTO;
import comun.DTO.PersonaDTO;
import comun.DTO.PersonalOrganizacionDTO;

import javax.swing.JTextArea;

public class GUIPersonalOrganizacion extends JFrame{

	private static final long serialVersionUID = 1L;
	private MediadorPersonalOrganizacion mediador;
	private JPanel contentPane;
	private JPanel panel_buttons;
	private JDateChooser dateChooserSalida;
	private JDateChooser dateChooserEntrada = new JDateChooser();
	private JTextArea textArea;
	private JLabel lblMotivo;
	private JLabel lblSalida;
	private JLabel lblEntrada;
	private JButton btnAceptar;
	private PersonalOrganizacionDTO asig;
	private OrganizacionDTO org = null;
	private PersonaDTO pers = null;

	//usuario	
	
	//Constructor para Agregar
	/**
	 * @wbp.parser.constructor
	 */
	public GUIPersonalOrganizacion(MediadorPersonalOrganizacion mediador, OrganizacionDTO org, PersonaDTO pers) throws Exception {
		setResizable(false);
		this.mediador= mediador;	
		initialize();
		this.org = org;
		this.pers = pers;
		dateChooserEntrada.setEnabled(true);
		dateChooserSalida.setEnabled(false);
		textArea.setEnabled(false);
		textArea.setEditable(false);
	}

	//Constructor para modif/Elim
	public GUIPersonalOrganizacion(MediadorPersonalOrganizacion mediador, PersonalOrganizacionDTO asig, boolean elim) throws Exception {
		setResizable(false);
		this.mediador= mediador;
		this.asig = asig;
		initialize();
		cargarDatos();
		if (elim){
			dateChooserEntrada.setEnabled(false);
			dateChooserSalida.setEnabled(true);
			textArea.setEnabled(true);
			textArea.setEditable(true);
		}else{
			dateChooserEntrada.setEnabled(true);
			dateChooserSalida.setEnabled(false);
			textArea.setEnabled(false);
			textArea.setEditable(false);
		}	
		repaint();
	}


	private void cargarDatos() {
		if(asig.getIngreso()!=null) dateChooserEntrada.setDate(new java.util.Date(asig.getIngreso().getTime()));
		if(asig.getSalida()!=null) dateChooserSalida.setDate(new java.util.Date(asig.getIngreso().getTime()));
		if(asig.getMotivo()!=null) textArea.setText(asig.getMotivo());
	}

	@SuppressWarnings("deprecation")
	private void initialize() {
		setSize(325, 270);
		setLocationRelativeTo(null);
		setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);

		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				int eleccion = JOptionPane.showConfirmDialog(null, "Desea salir?","Salir",0,0,new ImageIcon(GUIPersonalOrganizacion.class.getResource("/cliente/Recursos/Iconos/exit_login.png")));
				if ( eleccion == 0) {
					System.exit(0);
				}	
			}
		});
		
		
		contentPane = new JPanel();// JPanel_Whit_Image("/cliente/Recursos/Imagenes/fondo.jpg");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel_buttons = new TransparentPanel();
		contentPane.add(panel_buttons, BorderLayout.CENTER);
		GridBagLayout gbl_panel_buttons = new GridBagLayout();
		gbl_panel_buttons.columnWidths = new int[] {124, 173};
		gbl_panel_buttons.rowHeights = new int[] {39, 34, 82, 0, 45};
		gbl_panel_buttons.columnWeights = new double[]{1.0, 1.0};
		gbl_panel_buttons.rowWeights = new double[]{1.0, 1.0, 1.0, 0.0, 0.0};
		panel_buttons.setLayout(gbl_panel_buttons);
		
		lblEntrada = new JLabel("Entrada");
		GridBagConstraints gbc_lblEntrada = new GridBagConstraints();
		gbc_lblEntrada.insets = new Insets(0, 0, 5, 5);
		gbc_lblEntrada.gridx = 0;
		gbc_lblEntrada.gridy = 0;
		panel_buttons.add(lblEntrada, gbc_lblEntrada);
		
		GridBagConstraints gbc_dateChooserEntrada = new GridBagConstraints();
		gbc_dateChooserEntrada.insets = new Insets(0, 0, 5, 0);
		gbc_dateChooserEntrada.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateChooserEntrada.gridx = 1;
		gbc_dateChooserEntrada.gridy = 0;
		panel_buttons.add(dateChooserEntrada, gbc_dateChooserEntrada);
		
		lblSalida = new JLabel("Salida");
		GridBagConstraints gbc_lblSalida = new GridBagConstraints();
		gbc_lblSalida.insets = new Insets(0, 0, 5, 5);
		gbc_lblSalida.gridx = 0;
		gbc_lblSalida.gridy = 1;
		panel_buttons.add(lblSalida, gbc_lblSalida);
		
		dateChooserSalida = new JDateChooser();
		GridBagConstraints gbc_dateChooserSalida = new GridBagConstraints();
		gbc_dateChooserSalida.insets = new Insets(0, 0, 5, 0);
		gbc_dateChooserSalida.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateChooserSalida.gridx = 1;
		gbc_dateChooserSalida.gridy = 1;
		panel_buttons.add(dateChooserSalida, gbc_dateChooserSalida);
		
		lblMotivo = new JLabel("Motivo");
		GridBagConstraints gbc_lblMotivo = new GridBagConstraints();
		gbc_lblMotivo.insets = new Insets(0, 0, 5, 5);
		gbc_lblMotivo.gridx = 0;
		gbc_lblMotivo.gridy = 2;
		panel_buttons.add(lblMotivo, gbc_lblMotivo);
		
		textArea = new JTextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.insets = new Insets(0, 0, 5, 0);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 2;
		panel_buttons.add(textArea, gbc_textArea);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setIcon(new ImageIcon(GUIPersonalOrganizacion.class.getResource(mediador.getMediadorPrincipal().getApariencia().getAceptar())));
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					aceptar();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.gridheight = 2;
		gbc_btnAceptar.insets = new Insets(0, 0, 5, 0);
		gbc_btnAceptar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAceptar.gridwidth = 2;
		gbc_btnAceptar.gridx = 0;
		gbc_btnAceptar.gridy = 3;
		panel_buttons.add(btnAceptar, gbc_btnAceptar);
		

		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				mediador.guiGestionPersOrg.setVisible(true);
				dispose();
			}
		});
		
		contentPane.show();
		
	}

	protected void aceptar() throws Exception {
		boolean res= false;
		if (asig != null){
			res= mediador.modificar_elim(obtenerAsig());
		}else{
			res= mediador.nuevoAsig(obtenerAsig());
		}	
		if (res){
			JOptionPane.showMessageDialog(contentPane,"Se a concluido con exito la tarea.","Proceso Exitoso",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIPersonalOrganizacion.class.getResource(mediador.getMediadorPrincipal().getApariencia().getOk())));
			mediador.guiGestionPersOrg.setVisible(true);
			dispose();
		}else{
			JOptionPane.showMessageDialog(contentPane,"A ocurrido un error, por favor corrobore los datos e intentelo nuevamente.","Error",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIPersonalOrganizacion.class.getResource(mediador.getMediadorPrincipal().getApariencia().getError())));
		}
	}
	
	public PersonalOrganizacionDTO obtenerAsig(){
		PersonalOrganizacionDTO po;
		if (asig !=null){
			po = new PersonalOrganizacionDTO(asig.getPersona(), asig.getOrg(),new java.sql.Timestamp(dateChooserEntrada.getDate().getTime())) ;
			po.setId(asig.getId());
			if (dateChooserSalida.isEnabled()){ 
				po.setSalida(new java.sql.Timestamp(dateChooserSalida.getDate().getTime()));
				po.setMotivo(textArea.getText());
				po.setEliminado(true);
			}
		}else{
			po = new PersonalOrganizacionDTO(pers, org,new java.sql.Timestamp(dateChooserEntrada.getDate().getTime())) ;
		}
		return po;
	}

	public MediadorPersonalOrganizacion getMediadorPrincipal() {
		return mediador;
	}

	public void setMediadorPrincipal(MediadorPersonalOrganizacion mediadorPrincipal) {
		this.mediador = mediadorPrincipal;
	}

	
	
}
