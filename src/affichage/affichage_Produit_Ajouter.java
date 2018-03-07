package affichage;


import interfaces.req_prod_aff;


import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import config.Parametres;
import donnees.produit;

/**
 * @author MAIRECH
 * @param Ajouter un produit
 *
 */

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class affichage_Produit_Ajouter {
	
	private JButton bouton = new JButton();
	private JPanel conteneur = new JPanel();
	private JTextField texte= new JTextField();
	private JLabel label = new JLabel();


	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
				//TO DO instansier une Jframe
				
				JFrame ajouter_produit = new JFrame();
				
				// le titre de la fenetre
				ajouter_produit.setTitle("Ajouter un produit");
				//la taille de la fênetre
				ajouter_produit.setSize(500, 600);
				//centrer la fenetre % à l'écran de l'ordinateur
				ajouter_produit.setLocationRelativeTo(null);
					
				// TO DO afficher la Jframe
				
				ajouter_produit.setVisible(true);

	}

}
