package requete;

import java.sql.*;


/**
 * Gestion de la connexion vers la base de données
 * @author JonathanGRZ
 *
 */
public class Connexion{
	private String DBPath;
	private Connection connection;
	
	/**
	 * Nous créons les paramètres de la connexion pour la base de données : son emplacement est déjà mis par défaut
	 */
	public Connexion(){
		DBPath = config.Parametres.getAdresseBdd();
		connection = null;
	}

	/**
	 * Nous ouvrons une connexion à la base de données
	 */
	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DBPath, config.Parametres.getLogin(), config.Parametres.getPassword());
			System.out.println("-----> Connexion a " + DBPath + " avec succès");
		} catch (ClassNotFoundException notFoundException) {
			notFoundException.printStackTrace();
			System.out.println("Erreur de connexion");
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			System.out.println("Erreur de connexion");
		}
	}

	/**
	 * Nous fermons la connexion à la base de données
	 */
	public void close() {
		try {
			connection.close();
			System.out.println("-----> La connection a été fermée avec succès");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection(){
		return connection;
	}
	

}