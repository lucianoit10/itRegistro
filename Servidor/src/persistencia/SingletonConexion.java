package persistencia;

import java.io.FileInputStream;
import java.util.Properties;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import comun.IniFile;

import persistencia.Singleton;


public class SingletonConexion {
	protected Singleton singleton;
	protected static PersistenceManagerFactory pmf;
	/**
	 * Constructor de la clase que nos garantiza que sea una unica instancia a
	 * traves del singleton.
	 */
	public SingletonConexion() {
		singleton = Singleton.getInstance();
		String db=new IniFile("conf.ini").getParameters("db");
		Properties p = new Properties();
		try{
			if (db.equals("MYSQL"))	p.load(new FileInputStream("MYSQL.properties"));
			else p.load(new FileInputStream("POSTGRES.properties"));
			pmf = JDOHelper.getPersistenceManagerFactory(p);
		}catch (Exception e){
			pmf = JDOHelper.getPersistenceManagerFactory(db);
		}
	}

	public static PersistenceManagerFactory getPmf() {
		return pmf;
	}

	public static void setPmf(PersistenceManagerFactory pmf) {
		SingletonConexion.pmf = pmf;
	}

}
