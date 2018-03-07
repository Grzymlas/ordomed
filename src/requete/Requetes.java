package requete;

import interfaces.req_cat;
import interfaces.req_prod;
import interfaces.req_four;
import interfaces.req_prod_aff;

import java.sql.*;
import java.util.ArrayList;

import donnees.catalogue_fournisseur;
import donnees.fournisseur;
import donnees.produit;

public class Requetes implements req_prod, req_cat, req_four, req_prod_aff {
	private Connexion co_bdd;
	
	public Requetes(){
		co_bdd = new Connexion();
	}
	
	///////////////////////////
	///// INITIALISATION /////
	/////////////////////////
	public boolean createBDD(){
		PreparedStatement ps = null;
		
		boolean resultat = false;

		String requete = "";		
		requete = "CREATE DATABASE lpdi_stock_2018;";
		try {
			co_bdd.connect();
			ps = co_bdd.getConnection().prepareStatement(requete);
			ps.execute(requete);
			resultat = true;
		} catch (SQLException e) {
			if(e.getMessage().compareTo("Ne peut créer la base 'lpdi_stock_2018'; elle existe déjà")==0){
				System.out.println("La base de données existe!");
			}
			else{
				e.printStackTrace();				
			}
		}finally {
			//fermeture du preparedStatement et de la connection
			try {if (ps != null)ps.close();} catch (Exception t) {}
			co_bdd.close();
		}		
		return resultat;
	}
	
	public boolean createTables(){
		try{
			createTableProduit();
			createTableFournisseur();
			createTableCatalogueFournisseur();
			return true;
		}
		catch(Exception e){
			return false;
		}
	}

	private void createTableProduit() throws Exception{
		PreparedStatement ps = null;
		

		String requete = "";		
		requete = "CREATE TABLE produit("
				+ "id_produit integer primary key AUTO_INCREMENT,"
				+ "libelle varchar(200),"
				+ "prix_de_vente float,"
				+ "quantite_en_stock integer,"
				+ "quantite_minimal integer"
				+ ");";
		try {
			co_bdd.connect();
			ps = co_bdd.getConnection().prepareStatement(requete);
			ps.execute(requete);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Problème lors de la création de la table produit!");
		}finally {
			//fermeture du preparedStatement et de la connection
			try {if (ps != null)ps.close();} catch (Exception t) {}
			co_bdd.close();
		}		
	}

	
	//création de la table fournisseurs	
	private void createTableFournisseur() throws Exception{
		PreparedStatement ps = null;
		

		String requete = "";		
		requete = "CREATE TABLE fournisseur("
				+ "id_fournisseur integer primary key AUTO_INCREMENT,"
				+ "raison_social varchar(255),"
				+ "siret varchar(255),"
				+ "adresse varchar(255),"
				+ "cp varchar(255),"
				+ "ville varchar (255),"
				+ "email varchar (255),"
				+ "tel varchar (255)"
				+ ");";
		try {
			co_bdd.connect();
			ps = co_bdd.getConnection().prepareStatement(requete);
			ps.execute(requete);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Problème lors de la création de la table fournisseur!");
		}finally {
			//fermeture du preparedStatement et de la connection
			try {if (ps != null)ps.close();} catch (Exception t) {}
			co_bdd.close();
		}		
	}
	
	private void createTableCatalogueFournisseur() throws Exception{
		PreparedStatement ps = null;
		

		String requete = "";		
		requete = "CREATE TABLE catalogue_fournisseur("
				+ "id_produit integer REFERENCES produit,"
				+ "id_fournisseur integer REFERENCES fournisseur,"
				+ "prix_u_ht float,"
				+ "reapprovisionnable boolean,"
				+ "primary key(id_produit, id_fournisseur)"
				+ ");";
		try {
			co_bdd.connect();
			ps = co_bdd.getConnection().prepareStatement(requete);
			ps.execute(requete);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Problème lors de la création de la table catalogue_fournisseur!");
		}finally {
			//fermeture du preparedStatement et de la connection
			try {if (ps != null)ps.close();} catch (Exception t) {}
			co_bdd.close();
		}		
	}
	///////////////////////////
	///////// PRODUIT ////////
	////////////////////////
	
	public boolean prodUpdate(produit prod){
		PreparedStatement ps = null;
		int nombre_maj = 0;

		String requete = "";		
		requete = ("UPDATE produit SET libelle = ? " +
				", prix_de_vente = ? " + 
				", quantite_en_stock = ? " +
				", quantite_minimal = ? " + 
				" WHERE id_produit = ? ;");
		try {
			co_bdd.connect();
			ps = co_bdd.getConnection().prepareStatement(requete);
			ps.setString(1, prod.getLibelle());
			ps.setDouble(2, prod.getPrixVente());
			ps.setInt(3, prod.getQtStock());
			ps.setInt(4, prod.getQtMini());
			ps.setInt(5, prod.getId());
			nombre_maj = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//fermeture du preparedStatement et de la connection
			try {if (ps != null)ps.close();} catch (Exception t) {}
			co_bdd.close();
		}
		
		if(nombre_maj==0){
			System.out.println("Il n'y a pas eu de mise à jour à réaliser dans la base de données!" );
			return false;
		}
		else{
			System.out.println("Il y a eu : " + nombre_maj + " mise à jour réussie dans la base de données!" );
			return true;
		}
	}
	
	public boolean prodDelete(int id_prod){
		PreparedStatement ps = null;
		int nombre_maj = 0;

		String requete = "";	
		//on supprimer d'abord au niveau du catalogue
		catDelete(-1, id_prod);
		requete = "DELETE FROM produit WHERE id_produit = ? ;";
		try {
			co_bdd.connect();
			ps = co_bdd.getConnection().prepareStatement(requete);
			ps.setInt(1,id_prod);
			nombre_maj = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//fermeture du preparedStatement et de la connection
			try {if (ps != null)ps.close();} catch (Exception t) {}
			co_bdd.close();
		}
		
		if(nombre_maj==0){
			System.out.println("Il n'y a pas eu de suppression à réaliser dans la base de données!" );
			return false;
		}
		else{
			System.out.println("Il y a eu : " + nombre_maj + " suppression(s) réussie(s) dans la base de données!" );
			return true;
		}
	}

	public boolean prodAdd(produit prod){
		PreparedStatement ps = null;
		int nombre_maj = 0;

		String requete = "";		
		requete = ("INSERT INTO produit (libelle, prix_de_vente, quantite_en_stock, quantite_minimal) " +
				"VALUES (?, ?, ?, ?);");
		try {
			co_bdd.connect();
			ps = co_bdd.getConnection().prepareStatement(requete);
			ps.setString(1, prod.getLibelle());
			ps.setDouble(2, prod.getPrixVente());
			ps.setInt(3, prod.getQtStock());
			ps.setInt(4, prod.getQtMini());
			nombre_maj = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//fermeture du preparedStatement et de la connection
			try {if (ps != null)ps.close();} catch (Exception t) {}
			co_bdd.close();
		}
		
		if(nombre_maj==0){
			System.out.println("Il n'y a pas eu d'ajout à réaliser dans la base de données!" );
			return false;
		}
		else{
			System.out.println("Il y a eu : " + nombre_maj + " ajout réussi dans la base de données!" );
			return true;
		}
	}
	

	public ArrayList<produit> prodRechercherLibelle(String libelle){
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<produit> liste_produit = new ArrayList<produit>();

		String requete = "";	
		if(libelle.length() == 0){
			requete = ("SELECT * FROM produit;");			
		}
		else{
			requete = ("SELECT * FROM produit WHERE libelle LIKE ? ;");
		}
		try {
			co_bdd.connect();
			ps = co_bdd.getConnection().prepareStatement(requete);
			if(libelle.length() != 0){
				ps.setString(1, "%"+libelle+"%");				
			}
			rs = ps.executeQuery();
			
			//on ajoute la liste
			while(rs.next()){
				liste_produit.add( new produit(rs.getInt("id_produit"), 
						rs.getString("libelle"),
						rs.getDouble("prix_de_vente"),
						rs.getInt("quantite_en_stock"),
						rs.getInt("quantite_minimal")
						) 
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//fermeture du preparedStatement et de la connection
			try {if (ps != null)ps.close();} catch (Exception t) {}
			co_bdd.close();
		}

		System.out.println("Il y a eu : " + liste_produit.size() + " résultat(s) trouvé(s) dans la base de données!" );
		return liste_produit;
	}


	///////////////////////////
	////////// FOURNISSEUR ///
	/////////////////////////
	
	public boolean fourUpdate(fournisseur four){
		PreparedStatement ps = null;
		int nombre_maj = 0;

		String requete = "";		
		requete = ("UPDATE fournisseur SET raison_social = ? " +
				", siret = ? " + 
				", adresse = ? " +
				", cp = ? " +
				", email = ?" +
				", ville = ? "+
				", tel = ? " +
				" WHERE id_fournisseur = ? ;");
		try {
			co_bdd.connect();
			ps = co_bdd.getConnection().prepareStatement(requete);
			ps.setString(1, four.getRaison_social());
			ps.setString(2, four.getSiret());
			ps.setString(3, four.getAdresse());
			ps.setString(4, four.getCp());
			ps.setString(5, four.getEmail());
			ps.setString(6, four.getVille());
			ps.setString(7, four.getTel());
			ps.setInt(8, four.getId());
			nombre_maj = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//fermeture du preparedStatement et de la connection
			try {if (ps != null)ps.close();} catch (Exception t) {}
			co_bdd.close();
		}
		
		if(nombre_maj==0){
			System.out.println("Il n'y a pas eu de mise à jour à réaliser dans la base de données!" );
			return false;
		}
		else{
			System.out.println("Il y a eu : " + nombre_maj + " mise à jour réussie dans la base de données!" );
			return true;
		}
	}
	
	public boolean fourDelete(int id_four){
		PreparedStatement ps = null;
		int nombre_maj = 0;

		String requete = "";
		catDelete(id_four, -1);		
		requete = "DELETE FROM fournisseur WHERE id_fournisseur = ?";
		try {
			co_bdd.connect();
			ps = co_bdd.getConnection().prepareStatement(requete);
			ps.setInt(1,id_four);
			nombre_maj = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//fermeture du preparedStatement et de la connection
			try {if (ps != null)ps.close();} catch (Exception t) {}
			co_bdd.close();
		}
		
		if(nombre_maj==0){
			System.out.println("Il n'y a pas eu de suppression à réaliser dans la base de données!" );
			return false;
		}
		else{
			System.out.println("Il y a eu : " + nombre_maj + " suppression(s) réussie(s) dans la base de données!" );
			return true;
		}
	}

	public boolean fourAdd(fournisseur four){
		PreparedStatement ps = null;
		int nombre_maj = 0;

		String requete = "";		
		requete = ("INSERT INTO fournisseur (id_fournisseur, raison_social, siret, adresse, cp, ville, email, tel) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
		try {
			co_bdd.connect();
			ps = co_bdd.getConnection().prepareStatement(requete);
			ps.setInt(1, four.getId());
			ps.setString(2, four.getRaison_social());
			ps.setString(3, four.getSiret());
			ps.setString(4, four.getAdresse());
			ps.setString(5, four.getCp());
			ps.setString(6, four.getVille());
			ps.setString(7, four.getEmail());
			ps.setString(8, four.getTel());
			nombre_maj = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//fermeture du preparedStatement et de la connection
			try {if (ps != null)ps.close();} catch (Exception t) {}
			co_bdd.close();
		}
		
		if(nombre_maj==0){
			System.out.println("Il n'y a pas eu d'ajout à réaliser dans la base de données!" );
			return false;
		}
		else{
			System.out.println("Il y a eu : " + nombre_maj + " ajout réussi dans la base de données!" );
			return true;
		}
	}
	

	public ArrayList<fournisseur> fourRechercherLibelle(String libelle){
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<fournisseur> liste_fournisseur = new ArrayList<fournisseur>();

		String requete = "";	
		if(libelle.length() == 0){
			requete = ("SELECT * FROM fournisseur;");			
		}
		else{
			requete = ("SELECT * FROM fournisseur WHERE raison_social LIKE ? ;");
		}
		try {
			co_bdd.connect();
			ps = co_bdd.getConnection().prepareStatement(requete);
			if(libelle.length() != 0){
				ps.setString(1, "%"+libelle+"%");				
			}
			rs = ps.executeQuery();
			
			//on ajoute la liste des fournisseurs
			while(rs.next()){
				liste_fournisseur.add( new fournisseur(rs.getInt("id_fournisseur"), 
						rs.getString("raison_social"),
						rs.getString("siret"),
						rs.getString("adresse"),
						rs.getString("cp"),
						rs.getString("ville"),
						rs.getString("email"),
						rs.getString("tel")
						) 
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//fermeture du preparedStatement et de la connection
			try {if (ps != null)ps.close();} catch (Exception t) {}
			co_bdd.close();
		}

		System.out.println("Il y a eu : " + liste_fournisseur.size() + " résultat(s) trouvé(s) dans la base de données!" );
		return liste_fournisseur;
	}

	//////////////////////////////
	/// CATALOGUE FOURNISSEUR ///
	////////////////////////////
	
	public boolean catUpdate(catalogue_fournisseur cat, int new_id_produit, int new_id_fournisseur){
		PreparedStatement ps = null;
		int nombre_maj = 0;

		String requete = "";		
		requete = ("UPDATE catalogue_fournisseur SET prix_u_ht = ? " +
				", reapprovisionnable = ? " +
				", id_produit = ? " +
				", id_fournisseur = ? " +				
				" WHERE id_produit = ? AND id_fournisseur = ? ;");
		try {
			co_bdd.connect();
			ps = co_bdd.getConnection().prepareStatement(requete);
			ps.setDouble(1, cat.getPrix());
			ps.setBoolean(2, cat.getReap());
			ps.setInt(3, new_id_produit);
			ps.setInt(4, new_id_fournisseur);
			ps.setInt(5, cat.getProduit().getId());
			ps.setInt(6, cat.getFournisseur().getId());
			nombre_maj = ps.executeUpdate();
		} catch (SQLException e) {
			if(e.getErrorCode()==1062){
				System.out.println("Le catalogue existe déjà !");
			}
			else{
				e.printStackTrace();
			}
		}finally {
			//fermeture du preparedStatement et de la connection
			try {if (ps != null)ps.close();} catch (Exception t) {}
			co_bdd.close();
		}
		
		if(nombre_maj==0){
			System.out.println("Il n'y a pas eu de mise à jour à réaliser dans la base de données!" );
			return false;
		}
		else{
			System.out.println("Il y a eu : " + nombre_maj + " mise à jour réussie dans la base de données!" );
			return true;
		}
	}
	
	public boolean catDelete(int id_four, int id_prod){
		PreparedStatement ps = null;
		int nombre_maj = 0;

		String requete = "";
		if(id_four==-1){
			requete = "DELETE FROM catalogue_fournisseur WHERE id_produit = ? ";			
		}
		else{
			if(id_prod==-1){
				requete = "DELETE FROM catalogue_fournisseur WHERE id_fournisseur = ? ";
			}
			else{
				requete = "DELETE FROM catalogue_fournisseur WHERE id_fournisseur = ? AND id_produit = ? ";
			}
		}
		try {
			co_bdd.connect();
			ps = co_bdd.getConnection().prepareStatement(requete);

			if(id_four==-1){
				ps.setInt(1,id_prod);		
			}
			else{
				if(id_prod==-1){
					ps.setInt(1,id_four);
				}
				else{
					ps.setInt(1,id_four);
					ps.setInt(2,id_prod);
				}
			}
			nombre_maj = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//fermeture du preparedStatement et de la connection
			try {if (ps != null)ps.close();} catch (Exception t) {}
			co_bdd.close();
		}
		
		if(nombre_maj==0){
			System.out.println("Il n'y a pas eu de suppression à réaliser dans la base de données!" );
			return false;
		}
		else{
			System.out.println("Il y a eu : " + nombre_maj + " suppression(s) réussie(s) dans la base de données!" );
			return true;
		}
	}

	public boolean catAdd(catalogue_fournisseur cat, int id_produit, int id_fournisseur){
		PreparedStatement ps = null;
		int nombre_maj = 0;

		String requete = "";		
		requete = ("INSERT INTO catalogue_fournisseur (prix_u_ht, reapprovisionnable, id_produit, id_fournisseur) " +
				"VALUES (?, ?, ?, ?);");
		try {
			co_bdd.connect();
			ps = co_bdd.getConnection().prepareStatement(requete);
			ps.setDouble(1, cat.getPrix());
			ps.setBoolean(2, cat.getReap());
			ps.setInt(3, id_produit);
			ps.setInt(4, id_fournisseur);
			nombre_maj = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//fermeture du preparedStatement et de la connection
			try {if (ps != null)ps.close();} catch (Exception t) {}
			co_bdd.close();
		}
		
		if(nombre_maj==0){
			System.out.println("Il n'y a pas eu d'ajout à réaliser dans la base de données!" );
			return false;
		}
		else{
			System.out.println("Il y a eu : " + nombre_maj + " ajout réussi dans la base de données!" );
			return true;
		}
	}
	
	/**
	 * Il a été convenu qu'il y a qu'une recherche soit par le nom du fournisseur soit par le nom du produit
	 * @param lib_four : String : nom du fournisseur
	 * @param lib_prod : String : nom du produit
	 * @return : ArrayList<catalogue_fournisseur> : Liste du catalogue de fournisseur
	 */
	public ArrayList<catalogue_fournisseur> catRechercherLibelle(String lib_four, String lib_prod){
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<catalogue_fournisseur> liste_catalogue = new ArrayList<catalogue_fournisseur>();

		String requete = "";	
		//début
		if(lib_four.length() == 0 && lib_prod.length()==0  ){
			requete = ("SELECT * FROM catalogue_fournisseur "
						+ "INNER JOIN fournisseur ON catalogue_fournisseur.id_fournisseur = fournisseur.id_fournisseur "
						+ "INNER JOIN produit ON catalogue_fournisseur.id_produit = produit.id_produit;");			
		}
		else{
			if(lib_four.length() == 0){
				requete = ("SELECT * FROM catalogue_fournisseur "
						+ "INNER JOIN fournisseur ON catalogue_fournisseur.id_fournisseur = fournisseur.id_fournisseur "
						+ "INNER JOIN produit ON catalogue_fournisseur.id_produit = produit.id_produit "
						+ "WHERE libelle LIKE ? "
						+ ";");				
			}
			else{
				requete = ("SELECT * FROM catalogue_fournisseur "
						+ "INNER JOIN fournisseur ON catalogue_fournisseur.id_fournisseur = fournisseur.id_fournisseur "
						+ "INNER JOIN produit ON catalogue_fournisseur.id_produit = produit.id_produit "
						+ "WHERE raison_social LIKE ? "
						+ ";");						
			}
		}
		try {
			co_bdd.connect();
			ps = co_bdd.getConnection().prepareStatement(requete);
			if(lib_four.length() != 0){
				ps.setString(1, "%"+lib_four+"%");				
			}
			else{
				if(lib_prod.length() != 0){
					ps.setString(1, "%"+lib_prod+"%");		
				}
			}
			rs = ps.executeQuery();

			//on ajoute la liste
			while(rs.next()){
				liste_catalogue.add( new catalogue_fournisseur(
							rs.getDouble("prix_u_ht"),
							rs.getBoolean("reapprovisionnable"),
							new fournisseur(
									rs.getInt("id_fournisseur"),
									rs.getString("raison_social"),
									rs.getString("siret"),
									rs.getString("adresse"),
									rs.getString("cp"),
									rs.getString("ville"),
									rs.getString("email"),
									rs.getString("tel")	
							),
							new produit(
									rs.getInt("id_produit"),
									rs.getString("libelle"),
									rs.getDouble("prix_de_vente"),
									rs.getInt("quantite_en_stock"),
									rs.getInt("quantite_minimal")
									)
						)
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//fermeture du preparedStatement et de la connection
			try {if (ps != null)ps.close();} catch (Exception t) {}
			co_bdd.close();
		}

		System.out.println("Il y a eu : " + liste_catalogue.size() + " résultat(s) trouvé(s) dans la base de données!" );
		return liste_catalogue;
	}
	
}
