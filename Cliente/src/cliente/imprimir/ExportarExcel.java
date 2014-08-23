package cliente.imprimir;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

import jxl.*;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExportarExcel {
	
	
	private static boolean exportar(File file,List<JTable> tablas,List<String> nom_files){
		try {
			//relaciona el stream de salida con el archivo creado
			DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
			//crea el libro para guardar el excel
			WritableWorkbook w = Workbook.createWorkbook(out);
			//itera sibre las tablas y crea una hoja por cada tabla
			for (int i = 0; i < tablas.size(); i++) {
				JTable tabla = tablas.get(i);
				//la hoja toma como parametro nombre y posicion
				WritableSheet s= w.createSheet(nom_files.get(i), i);
				//itera las columnas de la tabla actual
				for (int j = 0; j < tabla.getColumnCount(); j++) {
					//agrega Titulo en excel
					Object title = tabla.getModel().getColumnName(j);
					s.addCell(new Label(j,0,String.valueOf(title)));
					//itera las filas agregando todas las celdas de la colimna actual
					for (int k = 0; k < tabla.getRowCount(); k++) {
						Object celda = tabla.getValueAt(k, j);
						s.addCell(new Label(j,k+1,String.valueOf(celda)));
					}
				}
				
			}
			//escribe la hoja y la cierra
			w.write();
			w.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	
	/*Eejmplo de uso:
	 try {
		List<JTable> t = new ArrayList<JTable>();
		List<String> n = new ArrayList<String>();
		t.add(JTable1);
		t.add(JTable2);
		t.add(JTable2);
		n.add("nombre 1");
		n.add("nombre 2");
		n.add("nombre 3");
		ExportarExcel.exportarTablas(t, n);
	} catch (Exception e) {
		JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
	}
	  
	 */
	public static void exportarTablas (List <JTable>tab, List<String> nombres) throws Exception{
		//chequea que esten bien pasados los parametros (misma cantidad de tablas que de nombres)
		if(nombres.size()!=tab.size()){
			//si esta mal lanza una excepcion
			throw new Exception("Error en los parametros al exportar a excel");
		}
		//crea el selector de archivos para elegir el archivo en el cual se va a exportar
		JFileChooser chooser = new JFileChooser();
		//setea las propiedades de este
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de excel","xls");
		chooser.setFileFilter(filter);
		chooser.setDialogTitle("Guardar archivo");
		chooser.setMultiSelectionEnabled(false);
		chooser.setAcceptAllFileFilterUsed(false);
		//una vez elegido el archivo 
		if (chooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
			//seteo las variables a utilizar (tablas, nombres y archivo de destino)
			List <JTable>tablas = tab;
			List<String>nom_files =nombres;
			String file_nom = chooser.getSelectedFile().toString().concat(".xls");
			File file = new File(file_nom);
			try {
				//exporto si todo salio bien, es informado de ello, en caso contrario se informa que no fue posible realizarse
				if (exportar(file,tablas,nom_files)){
					JOptionPane.showMessageDialog(null,"El archivo se a exportado Correctamente.","Exportar",JOptionPane.INFORMATION_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null,"Ocurrio un error al querer exportar el archivo.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (Exception e) {
				//en caso de error lanzo un mensaje informandolo
				JOptionPane.showMessageDialog(null,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
			}			
		}		
	}
	
	
	/*Eejmplo de uso:
	 try {
		ExportarExcel.exportarUnaTabla(JTable1, "nombre");
	} catch (Exception e) {
		JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
	}
	  
	 */
	public static void exportarUnaTabla(JTable tabla, String nombre) throws Exception{
		//creo las listas de la tabla y nombre, y las inicializo
		List<JTable> tab = new ArrayList<JTable>();
		List<String> nom = new ArrayList<String>();
		tab.add(tabla);
		nom.add(nombre);
		//elxporto las tablas recien creadas
		exportarTablas(tab, nom);
	}
	
}
