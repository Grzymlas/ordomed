package config;

import requete.Requetes;

public class Parametres {
	static private Requetes req;
	static private String adresse_bdd;
	static private String login_bdd;
	static private String password_bdd;
	
	static public boolean Initialisation(){
		System.out.println("--> Initalisation en cours ...");
		return InitialistionBDD();
	}
	
	static private boolean InitialistionBDD(){
		login_bdd = "root";
		password_bdd = "";	
		
		adresse_bdd = "jdbc:mysql://localhost/?useSSL=false";
		req = new Requetes();
		//si une création de la BDD était nécessaire
		if(req.createBDD()){
			System.out.println("création de la table OK");
			adresse_bdd = "jdbc:mysql://localhost/lpdi_stock_2018?useSSL=false";	
			req = new Requetes();
			return req.createTables();
		}
		else{	
			adresse_bdd = "jdbc:mysql://localhost/lpdi_stock_2018?useSSL=false";	
			req = new Requetes();		
			System.out.println("Création de la table non nécessaire");
			return false;
		}
	}
	
	static public Requetes getReq(){
		return req;
	}
	
	static public String getLogin(){
		return login_bdd;
	}
	
	static public boolean setLogin(String log){
		login_bdd = log;
		return true;
	}
	
	static public String getAdresseBdd(){
		return adresse_bdd;
	}
	
	static public boolean setAdresseBdd(String adresse){
		adresse_bdd = adresse;
		return true;
	}
	
	static public String getPassword(){
		return password_bdd;
	}
	
	static public boolean setPassword(String pass){
		password_bdd = pass;
		return true;
	}
		
}
