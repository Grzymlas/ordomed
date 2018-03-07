package affichage;

import interfaces.req_prod_aff;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import config.Parametres;
import donnees.produit;

public class affichage_produit {
    private JFrame frame;
	private JTextField libelle;
	private JPanel panneau_centre;
	private static req_prod_aff req_bdd;
	
	static{
		req_bdd = Parametres.getReq();
	}
	
    public affichage_produit() {
    	frame = new JFrame("Gestion des stocks : produits");
    	

    	///////////////////////////////
    	///////// MENU GAUCHE ////////
    	/////////////////////////////
    	JPanel menu_gauche = new JPanel();
    	menu_gauche.setLayout(new GridLayout(0, 1));
    	
    	JButton b_produits = new JButton("Produits");
    	JButton b_fournisseurs = new JButton("Fournisseur");
    	JButton b_catalogues = new JButton("Catalogues fournisseurs");
    	JButton b_clients = new JButton("Clients");
    	JButton b_commandes = new JButton("Commandes clients");
    	
    	///////////////////////////////
    	/// ACTION DU MENU GAUCHE ////
    	/////////////////////////////
    	b_produits.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame.getContentPane(), "Vous êtes déjà dans la partie produits !", "Information : développement", JOptionPane.INFORMATION_MESSAGE);
            	
            }
        });
    	b_fournisseurs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame.getContentPane(), "En cours de programmation!", "Information : développement", JOptionPane.INFORMATION_MESSAGE);
            	
            }
        });
    	b_catalogues.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame.getContentPane(), "En cours de programmation!", "Information : développement", JOptionPane.INFORMATION_MESSAGE);
            	
            }
        });
    	b_clients.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame.getContentPane(), "En cours de programmation!", "Information : développement", JOptionPane.INFORMATION_MESSAGE);
            	
            }
        });
    	b_commandes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame.getContentPane(), "En cours de programmation!", "Information : développement", JOptionPane.INFORMATION_MESSAGE);
            	
            }
        });
    	
    	////////////////////////////////
    	/// COULEUR DU MENU GAUCHE ////
    	//////////////////////////////
    	b_produits.setForeground(Color.BLACK);
    	b_produits.setBackground(new Color(150, 150, 255));
    	b_fournisseurs.setForeground(Color.BLACK);
    	b_fournisseurs.setBackground(Color.LIGHT_GRAY);
    	b_catalogues.setForeground(Color.BLACK);
    	b_catalogues.setBackground(Color.LIGHT_GRAY);
    	b_clients.setForeground(Color.BLACK);
    	b_clients.setBackground(Color.LIGHT_GRAY);
    	b_commandes.setForeground(Color.BLACK);
    	b_commandes.setBackground(Color.LIGHT_GRAY);
    	
    	////////////////////////////////
    	///// AJOUT DU MENU GAUCHE ////
    	//////////////////////////////
    	menu_gauche.add(b_produits);
    	menu_gauche.add(b_fournisseurs);
    	menu_gauche.add(b_catalogues);
    	menu_gauche.add(b_clients);
    	menu_gauche.add(b_commandes);
    	menu_gauche.setPreferredSize(new Dimension(200, 500));
        frame.getContentPane().add(menu_gauche, BorderLayout.WEST);

    	////////////////////////////////////////////
    	/////////      CENTRE CONTENU      ////////
    	/////////    FONCTION RECHERCHE   ////////
    	/////////////////////////////////////////
    	JPanel centre_contenu_fonction_recherche = new JPanel();
    	centre_contenu_fonction_recherche.setLayout(new FlowLayout());
    	JLabel lab_libelle = new JLabel("Libellé");
    	libelle = new JTextField(40);
    	JButton b_rechercher = new JButton("Rechercher");
    	//fonction
    	b_rechercher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	boutonRechercher();
            }
        });
    	//couleur
    	centre_contenu_fonction_recherche.setBackground(new Color(230,230,230));
    	centre_contenu_fonction_recherche.add(lab_libelle);
    	centre_contenu_fonction_recherche.add(libelle);
    	centre_contenu_fonction_recherche.add(b_rechercher);
    	centre_contenu_fonction_recherche.setPreferredSize(new Dimension(700, 40));  

    	///////////////////////////////////
    	///////// CENTRE CONTENU /////////
    	/////////    RECHERCHE   ////////
    	////////////////////////////////
    	JPanel centre_contenu_recherche = new JPanel();
    	centre_contenu_recherche.setLayout(new GridLayout(2, 1));
    	JLabel recherche = new JLabel("RECHERCHE");
    	centre_contenu_recherche.add(recherche);
    	centre_contenu_recherche.add(centre_contenu_fonction_recherche);
    	
    	//////////////////////////////////////
    	///////// CENTRE 1ere ligne /////////
    	///////// RECHERCHE + AJOUT ////////
    	///////////////////////////////////
    	JPanel centre_premiere_ligne = new JPanel();
    	centre_premiere_ligne.setLayout(new FlowLayout()); 
    	JButton b_ajouter_prod = new JButton("Ajouter un produit");
    	//fonction
    	b_ajouter_prod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	boutonAjouterProduit();
            }
        });
    	//visuel
    	b_ajouter_prod.setPreferredSize(new Dimension(150, 50)); 
    	centre_premiere_ligne.add(centre_contenu_recherche);
    	centre_premiere_ligne.add(b_ajouter_prod);
    	centre_premiere_ligne.setPreferredSize(new Dimension(900, 80));  
        

    	/////////////////////////////////////
    	///////// PRINCIPALE CENTRE ////////
    	///////////////////////////////////
    	panneau_centre = new JPanel();
    	panneau_centre.setLayout(new BorderLayout());
    	panneau_centre.add(centre_premiere_ligne, BorderLayout.NORTH); 
    	panneau_centre.add(new JLabel("remplissage"), BorderLayout.CENTER);   
        frame.getContentPane().add(panneau_centre, BorderLayout.CENTER);
        
    	////////////////////////////////
    	/////// INITIALISATION   //////
    	//////////////////////////////
        //on lance la fonction recherche pour actualiser la liste
        boutonRechercher();

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, (int)dim.height/2-frame.getSize().height/2);
        	
    }
    
    private void boutonRechercher(){
    	BorderLayout layout = (BorderLayout) panneau_centre.getLayout();
    	panneau_centre.remove(layout.getLayoutComponent(BorderLayout.CENTER));
    	//taille en largeur : 900
    	//le panneau du tableau
		JPanel tableau = new JPanel();
		tableau.setLayout(new BoxLayout(tableau, BoxLayout.Y_AXIS));
		
		
    	//création des titres
    	//Colonne : ref, libelle, prix, qt stock, qt mini, gestion stock, action
    	JLabel lab_ref = new JLabel("Réf produit");
    	JLabel lab_lib = new JLabel("Libellé");
    	JLabel lab_prix = new JLabel("Prix de vente");
    	JLabel lab_qt_stock = new JLabel("Qté en stock");
    	JLabel lab_qt_mini = new JLabel("Qté minimal");
    	JLabel lab_modif_qt = new JLabel("Modifier Qté");
    	JLabel lab_actions = new JLabel("Actions");
    	
    	//visuel
    	lab_ref.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    	lab_ref.setHorizontalAlignment(SwingConstants.CENTER);
    	lab_ref.setPreferredSize(new Dimension(80, 40)); 
    	
    	lab_lib.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    	lab_lib.setHorizontalAlignment(SwingConstants.CENTER);
    	lab_lib.setPreferredSize(new Dimension(270, 40)); 
    	
    	lab_prix.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    	lab_prix.setHorizontalAlignment(SwingConstants.CENTER);
    	lab_prix.setPreferredSize(new Dimension(100, 40)); 
    	
    	lab_qt_stock.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    	lab_qt_stock.setHorizontalAlignment(SwingConstants.CENTER);
    	lab_qt_stock.setPreferredSize(new Dimension(100, 40)); 
    	
    	lab_qt_mini.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    	lab_qt_mini.setHorizontalAlignment(SwingConstants.CENTER);
    	lab_qt_mini.setPreferredSize(new Dimension(100, 40)); 
    	
    	lab_modif_qt.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    	lab_modif_qt.setHorizontalAlignment(SwingConstants.CENTER);
    	lab_modif_qt.setPreferredSize(new Dimension(150, 40)); 
    	
    	lab_actions.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    	lab_actions.setHorizontalAlignment(SwingConstants.CENTER);
    	lab_actions.setPreferredSize(new Dimension(100, 40)); 
    	
    	//le panneau titre
		JPanel titre = new JPanel();
		titre.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		titre.add(lab_ref);
		titre.add(lab_lib);
		titre.add(lab_prix);
		titre.add(lab_qt_stock);
		titre.add(lab_qt_mini);
		titre.add(lab_modif_qt);
		titre.add(lab_actions);
		titre.setBackground(Color.LIGHT_GRAY);

		//on complète le tableau
		tableau.add(titre);
		
		//couleurs
		Color c1 = new Color(225,225,250);
		Color c2 = new Color(200,200,250);
		Color couleur;
		boolean pair = true;
		
    	ArrayList<produit> liste_produit = req_bdd.prodRechercherLibelle(this.libelle.getText());
		for(produit produit : liste_produit){			
			//ajout d'une ligne
			JPanel ligne = new JPanel();
			ligne.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
			
			//couleur ligne 
			if(pair){
				couleur = c1;				
			}
			else{
				couleur = c2;	
			}
			ligne.setBackground(couleur);	
			
			pair = !pair;
			
			JLabel id = new JLabel(Integer.toString(produit.getId()));
			id.setPreferredSize(new Dimension(80, 80)); 
			id.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			ligne.add(id);
			

			JLabel lib = new JLabel(produit.getLibelle());
			lib.setPreferredSize(new Dimension(270, 80)); 
			lib.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			ligne.add(lib);
			
			JLabel prix = new JLabel(Double.toString(produit.getPrixVente()));
			prix.setPreferredSize(new Dimension(100, 80)); 
			prix.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			ligne.add(prix);
			
			JLabel stock = new JLabel(Integer.toString(produit.getQtStock()));
			stock.setPreferredSize(new Dimension(100, 80)); 
			stock.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			ligne.add(stock);

			JLabel mini = new JLabel(Integer.toString(produit.getQtMini()));
			mini.setPreferredSize(new Dimension(100, 80)); 
			mini.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			ligne.add(mini);
			
				//panneau modifier quantite
				JPanel pan_modif_qt = new JPanel();
				pan_modif_qt.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
				JTextField valeur_changement = new JTextField(6);
				valeur_changement.setPreferredSize(new Dimension(75, 80)); 
				
				//panneau modifier quantite boutons
				JPanel pan_modif_qt_boutons = new JPanel();
				pan_modif_qt_boutons.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
				pan_modif_qt_boutons.setPreferredSize(new Dimension(75, 80)); 
				JButton b_entree = new JButton("Entrée");
				b_entree.setPreferredSize(new Dimension(75, 40));
				b_entree.setBackground(couleur);
				JButton b_sortie = new JButton("Sortie"); 
				b_sortie.setPreferredSize(new Dimension(75, 40)); 
				b_sortie.setBackground(couleur);
		    	//fonction
				b_entree.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	int valeur_champ = 0;
		            	String champ = valeur_changement.getText();
		            	for(int i=0;i<champ.length(); i++){
		            		valeur_champ *= 10;
		            		valeur_champ += (champ.charAt(i)-'0');
		            	}
		            	if(produit.modifierQT(valeur_champ)){
		            		stock.setText(Integer.toString(produit.getQtStock()));
		            	}
		            	else{
			            	System.out.println("BUG QUANTITE MODIF!!!");
		            	}
		            	System.out.println("valeur = " + valeur_champ);
		            }
		        });
				b_sortie.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	int valeur_champ = 0;
		            	String champ = valeur_changement.getText();
		            	for(int i=0;i<champ.length(); i++){
		            		valeur_champ *= 10;
		            		valeur_champ += (champ.charAt(i)-'0');
		            	}
		            	if(produit.modifierQT(valeur_champ*-1)){
		            		stock.setText(Integer.toString(produit.getQtStock()));
		            	}
		            	else{
			            	System.out.println("BUG QUANTITE MODIF!!!");
		            	}
		            	System.out.println("valeur = " + valeur_champ*-1);
		            }
		        });
				pan_modif_qt_boutons.add(b_entree);
				pan_modif_qt_boutons.add(b_sortie);
				//on complete la case
				pan_modif_qt.add(valeur_changement);
				pan_modif_qt.add(pan_modif_qt_boutons);
				pan_modif_qt.setPreferredSize(new Dimension(150, 80)); 
				pan_modif_qt.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
				
			ligne.add(pan_modif_qt);

				//panneau actions boutons
				JPanel pan_action_boutons = new JPanel();
				pan_action_boutons.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
	
				JButton b_modifier = new JButton("Modifier");
				b_modifier.setPreferredSize(new Dimension(100, 40)); 
				b_modifier.setBackground(couleur);
				JButton b_supprimer = new JButton("Supprimer"); 
				b_supprimer.setPreferredSize(new Dimension(100, 40)); 
				b_supprimer.setBackground(couleur);
		    	//fonction
				b_modifier.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	//affichage du menu modifier
		            	System.out.println("Modifier le produit id : " + produit.getId());
		            }
		        });
				b_supprimer.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	produit.Delete();
		            	boutonRechercher();
		            	System.out.println("Supprimer le produit id : " + produit.getId());
		            }
		        });
				pan_action_boutons.add(b_modifier);
				pan_action_boutons.add(b_supprimer);
				pan_action_boutons.setPreferredSize(new Dimension(100, 80)); 
				pan_action_boutons.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			ligne.add(pan_action_boutons);
			
			ligne.setPreferredSize(new Dimension(900, 80));
			tableau.add(ligne);
		}	
		

		tableau.setMinimumSize(new Dimension(900, 400));
		//on complete pour éviter que cela soit trop moche à cause du grid
		//on ajoute des lignes
		for(int i=liste_produit.size(); i<5;i++){
			JPanel ligne = new JPanel();
			ligne.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
			ligne.setPreferredSize(new Dimension(900, 80));
			ligne.setBackground(Color.WHITE);
			tableau.add(ligne);		
		}
		JScrollPane jsp = new JScrollPane(tableau);
		jsp.setPreferredSize(new Dimension(920,420));
		jsp.getVerticalScrollBar().setUnitIncrement(10);
		panneau_centre.add(jsp, BorderLayout.CENTER);
        frame.pack();
		
		//JOptionPane.showMessageDialog(frame.getContentPane(), "Lancement recherche !", "Information : développement", JOptionPane.INFORMATION_MESSAGE);
    }    

    private void boutonAjouterProduit(){
		JOptionPane.showMessageDialog(frame.getContentPane(), "Ajouter un produit !", "Information : développement", JOptionPane.INFORMATION_MESSAGE);
    }

}
