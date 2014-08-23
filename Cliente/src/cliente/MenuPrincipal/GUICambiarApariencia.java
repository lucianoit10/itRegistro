package cliente.MenuPrincipal;

import java.awt.HeadlessException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import javax.swing.JButton;

import cliente.utils.Constantes;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

@SuppressWarnings("serial")
public class GUICambiarApariencia extends JFrame {

	private MediadorPrincipal mediador;
	private JComboBox<String> comboBoxSkin;
	public GUICambiarApariencia(MediadorPrincipal mediador)	throws HeadlessException {
		super();
		setTitle("CAMBIAR APARIENCIA");
		this.mediador = mediador;
		initialize();
	}

	private void initialize() {

		setSize(300, 200);
		setLocationRelativeTo(null);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 46, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		comboBoxSkin = new JComboBox<String>(Constantes.listApariencias);
		GridBagConstraints gbc_comboBoxSkin = new GridBagConstraints();
		gbc_comboBoxSkin.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxSkin.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxSkin.gridx = 1;
		gbc_comboBoxSkin.gridy = 0;
		getContentPane().add(comboBoxSkin, gbc_comboBoxSkin);
		comboBoxSkin.setSelectedIndex(mediador.aparienciaSeleccionada);
		
		JButton btnGuardarConfiguracin = new JButton("Guardar Configuración");
		btnGuardarConfiguracin.setIcon(new ImageIcon(GUICambiarApariencia.class.getResource(mediador.getApariencia().getAceptar())));
		btnGuardarConfiguracin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
			}
		});
		GridBagConstraints gbc_btnGuardarConfiguracin = new GridBagConstraints();
		gbc_btnGuardarConfiguracin.fill = GridBagConstraints.BOTH;
		gbc_btnGuardarConfiguracin.insets = new Insets(0, 0, 5, 5);
		gbc_btnGuardarConfiguracin.gridx = 1;
		gbc_btnGuardarConfiguracin.gridy = 2;
		getContentPane().add(btnGuardarConfiguracin, gbc_btnGuardarConfiguracin);
		

		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				mediador.getGui_menu_Principal().setVisible(true);
				dispose();
			}
		});
	}

	protected void modificar() {
		if (mediador.modifApariencia(comboBoxSkin.getSelectedIndex())){
			JOptionPane.showMessageDialog(this,"Apariencia cambiada, para ver los cambios, reinicie el programa","Cambio de Apariencia Exitoso",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUICambiarApariencia.class.getResource(mediador.getApariencia().getOk())));
			mediador.getGui_menu_Principal().setVisible(true);
			dispose();
		}
		else 
			JOptionPane.showMessageDialog(this,"Ocurrio un error la cambiar la apariencia, intentelo nuevamente.","Error al Cambiar Apariencia",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUICambiarApariencia.class.getResource(mediador.getApariencia().getError())));
	}
	
	
}
