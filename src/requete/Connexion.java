package requete;

import java.sql.*;


/**
 * Gestion de la connexion vers la base de donn�es
 * @author JonathanGRZ
 *
 */
public class Connexion{
	private String DBPath;
	private Connection connection;
	
	/**
	 * Nous cr�ons les param�tres de la connexion pour la base de donn�es : son emplacement est d�j� mis par d�faut
	 */
	public Connexion(){
		DBPath = config.Parametres.getAdresseBdd();
		connection = null;
	}

	/**
	 * Nous ouvrons une connexion � la base de donn�es
	 */
	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DBPath, config.Parametres.getLogin(), config.Parametres.getPassword());
			System.out.println("-----> Connexion a " + DBPath + " avec succ�s");
		} catch (ClassNotFoundException notFoundException) {
			notFoundException.printStackTrace();
			System.out.println("Erreur de connexion");
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			System.out.println("Erreur de connexion");
		}
	}

	/**
	 * Nous fermons la connexion � la base de donn�es
	 */
	public void close() {
		try {
			connection.close();
			System.out.println("-----> La connection a �t� ferm�e avec succ�s");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection(){
		return connection;
	}
	

}