/* TP3  
 * execute par Melnic Rodica MELR10517107
 *        
 */



import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class TP3<ComboBox> extends WindowAdapter implements ActionListener {
   
   /************************
    * CONSTANTES
    ************************/
   //chemin du fichier d'enregistrement de la configuration de l'editeur
   public final static String CONFIG_CHEMIN_FIC = "./config.txt";
   
   //dimensions pour la fenetre principale
   public final static int LARGEUR_FENETRE = 600;
   public final static int HAUTEUR_FENETRE = 540;
   
   //largeur de l'ecran de l'ordinateur pour centrer l'application
   public final static int LARG_ECRAN = 
           Toolkit.getDefaultToolkit().getScreenSize().width;
   //hauteur de l'ecran de l'ordinateur pour centrer l'application
   public final static int HAUT_ECRAN = 
           Toolkit.getDefaultToolkit().getScreenSize().height;
   
   
   //couleurs
   public final static Color NOIR = Color.BLACK;
   public final static Color BLANC = Color.WHITE;
   public final static Color JAUNE = new Color(253, 246, 63);    
   public final static Color ROUGE = new Color(201, 0, 0); 
   public final static Color BLEU = new Color(40, 79, 206);
   public final static Color BLEU_PALE = new Color(131, 197, 253);
   public final static Color VERT = new Color(44, 155, 1); 
   public final static Color VERT_PALE = new Color(175, 248, 1); 
   public final static Color ORANGE = new Color(246, 122, 0); 
   public final static Color ROSE = new Color(255, 180, 208);
   public final static Color GRIS = Color.LIGHT_GRAY;
   
   public final static String[] COURIER = {"Arial","Courier","Lucida Grande","Times"};
   public final static Color[] COULEURS = {NOIR, BLANC, JAUNE, ROUGE, BLEU,
                         BLEU_PALE, VERT, VERT_PALE, ORANGE, ROSE, GRIS};
   public final static String[] NOM_COULEURS = {"Noir", "Blanc", "Jaune", "Rouge", "Bleu",
   "Bleu_pale", "Vert", "Vert_Pale", "Orange", "Rose", "Gris"};
   
   //Titre de la fenetre de l'editeur
   public final static String TITRE_FENETRE = "MINI ÉDITEUR";
   public final static String TITRE_FENETRE_CONFIG = "CONFIGURATION";
   
   public String fichier = "Nouveau";
   
   public int taillePolice = 12;
   public String nomPolice = "Courier";
   public String couleurPolice = "Noir";
   public String typePolice = "Normal";
   public String couleurFond = "Blanc";
   public String couleurTexteSelect = "Noir";
   public String couleurSelection = "Jaune";
   public String couleurCurseur = "Noir";
   public boolean retourLigne = true; 
   public int longTab = 3; 
   
  
  /************************
    * ATTRIBUTS D'INSTANCE pour text
    ************************/
 
   
   //Ecouteur
   private ActionListener ecouteur;

   /************************
    * ATTRIBUTS D'INSTANCE 
    ************************/
   
   private JFrame fenetre;
   private JFrame fenetreConfig;
   
   
   //panneau POUR FENETRE PRINCIPALE
  /*********************************************************/
   private JPanel panneauBoutons;
   private JPanel panneauText;
   
 
   //ensemble de 4 boutons
   private JButton boutonsNouveau ;
   private JButton boutonsOuvrir ;
   private JButton boutonsSauvegarder ;
   private JButton boutonsConfig ;
  
 //zone de texte et panneau de defilement pour cette zone
   private JTextArea zoneTexte;
   private JScrollPane defilZone;
   
   private JLabel etiquetteFichier;
     
   /********************************************************
    * panneau POUR FENETRE CONFIG
    *********************************************************/
   private JPanel panneauPolice;
   private JPanel panneauOptions;
   
   //etiquettes panneauPolice
   /*********************************************************/
   private JLabel policeDeCaracteres;
   private JLabel etiquetteNom;
   private JComboBox listeCourier;
   private JButton btnCourier;
   
   private JLabel etiquetteTaille;
   private JTextField champTaille;
   
   private JLabel etiquetteCouleur;
   private JComboBox listeCouleurs;
    
   private JRadioButton btnNormal;
   private JRadioButton btnGras;
   private JRadioButton btnItalique;
   
 //groupe pour les boutons radio
   private ButtonGroup groupeBtnRadio;
   
 //etiquettes panneauOptions
   /*********************************************************/
   private JLabel etiquetteOptions;
   private JLabel etiquetteCoul;
   
   private JLabel etiquetteFond;
   private JComboBox listeFond;
   
   private JLabel etiquetteTexteSelect;
   private JComboBox listeTexteSelect;
   
   private JLabel etiquetteSelectionTexte;
   private JComboBox listeSelectionTexte;
   
   private JLabel etiquetteCourseur;
   private JComboBox listeCurseur;
   
   private JLabel etiquetteAutres;
   
   private JCheckBox boiteRetourALigne ;
   
   private JLabel etiquetteLongTab;
   private JTextField champLongTab;

   
   /**
    * Constructeur sans argument qui initialise tous les composants graphiques.
    */
   public TP3() {
      //initialisation de l'interface graphique
      init();
   }
   
   /**
    * Initialisation de l'interface graphique.
    */
   private void init() {
	   
	 //  chargerConfiguration();
      
      //FRAME FENETRE ET FENETRE CONFIG
      fenetre = new JFrame(TITRE_FENETRE);
      //centrer la fenetre principale de l'editeur
      fenetre.setBounds(LARG_ECRAN / 2 - LARGEUR_FENETRE / 2, 
              HAUT_ECRAN / 2 - HAUTEUR_FENETRE / 2, 
              LARGEUR_FENETRE, HAUTEUR_FENETRE);
      fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
      //assigner le gestionnaire de disposition FlowLayout
      fenetre.setLayout(null);
          
      fenetre.setResizable(false);
      
     
      
      /*********************************************************
       * INIT BOUTONS POUR FENETRE PRINCIPALE
       *********************************************************/
      boutonsNouveau  = new JButton("Nouveau");
      boutonsOuvrir  = new JButton("Ouvrir");
      boutonsSauvegarder = new JButton("Sauvgarder");
      boutonsConfig = new JButton("Configuration...");
      
      /*********************************************************
       * INIT PANNEAU BOUTONS FENETRE PRINCIPALE
       *********************************************************/
      
      //Par defaut, un JPanel possede un FlowLayout comme gestionnaire
      //de disposition. Donc, inutile de le signaler explicitement.
      panneauBoutons = new JPanel(new FlowLayout(FlowLayout.CENTER));
      
      //positionner le panneau dans le haut du JFrame
      panneauBoutons.setBounds(20, 20, 
              fenetre.getWidth() - 40,   35);
      
      //espace entre les boutons = 30
      ((FlowLayout)panneauBoutons.getLayout()).setHgap(15);
       
       
       //ajouter les boutons au panneau
       panneauBoutons.add(boutonsNouveau);
       panneauBoutons.add(boutonsOuvrir);
       panneauBoutons.add(boutonsSauvegarder);
       panneauBoutons.add(boutonsConfig);
       
      
       /***************************************************************/
       
       
       panneauText = new JPanel(null);  
    
       panneauText.setBounds(10, panneauBoutons.getY() + panneauBoutons.getHeight() + 10,
    		   LARGEUR_FENETRE -30, HAUTEUR_FENETRE -150);
       
       panneauText.setBackground(Color.WHITE);
       panneauText.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
       panneauText.setVisible(true);
/***********************************************************/
      
      //Creation et initialisation de zoneText - AVEC BARRES DE DEFILEMENT
      //HORIZIZONTALE ET VERTICALE
      zoneTexte = new JTextArea();
      zoneTexte.setBounds(panneauBoutons.getX()+10, panneauBoutons.getY() +10, 
    		  panneauText.getWidth() - 20, 
    		  panneauText.getHeight() - 20);
      zoneTexte.setEditable(true);
      
      /******************************************************
       * 
       ********************************************************/
      zoneTexte.setFont( new Font(nomPolice, Font.PLAIN, taillePolice) );
      zoneTexte.setForeground(NOIR);
      zoneTexte.setSelectedTextColor(NOIR);
      zoneTexte.setSelectionColor(JAUNE);
      zoneTexte.setCaretColor(NOIR);
      zoneTexte.setWrapStyleWord(true);
      zoneTexte.setLineWrap(zoneTexte.getWrapStyleWord());
      zoneTexte.setTabSize(longTab);
      
      //Creer un JScrollPane pour le JTextArea zoneTexte : pour avoir des 
      //barres de défilement.
      defilZone = new JScrollPane(zoneTexte);
      defilZone.setBounds(10, 10, 
    		  panneauText.getWidth()-20,  panneauText.getHeight()-20);
      
      defilZone.setBorder(null);
      
      //Barre de defilement horizontale : au besoin seulement
      defilZone.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      
      //Barre de defilement verticale : au besoin seulement
      defilZone.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
      
      panneauText.add(defilZone);
      
      /************************************************/
     etiquetteFichier = new  JLabel("Fichier:  " + fichier ) ;
     etiquetteFichier.setBounds(10, 
    		 panneauText.getY() + panneauText.getHeight()+10,
    		 100, 20);
      /*************************************************
       * AJOUT DES COMPOSANTS AU CONTENT PANE DU FENETRE PRINCIPALE
       *************************************************/
      
       
      //ajouter le panneau au JFrame
     fenetre.getContentPane().add(panneauBoutons);
     fenetre.getContentPane().add(panneauText);
     fenetre.getContentPane().add(etiquetteFichier); 
     
     /*********************************************************
      * INIT PANNEAU FENETRE Config
      *********************************************************/
    
     
     fenetreConfig = new JFrame(TITRE_FENETRE_CONFIG);
     fenetreConfig.setLayout(null);
     fenetreConfig.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
     fenetreConfig.setBounds(fenetre.getX() + fenetre.getWidth(), 
             fenetre.getY(), 300, fenetre.getHeight());
     fenetreConfig.setResizable(false);
     
     
     /*********************************************************
      * INIT PANNEAU panneauPolice FENETRE PRINCIPALE
      *********************************************************/
     panneauPolice = new JPanel(null);
     panneauPolice.setBounds(10, 10, fenetreConfig.getWidth() - 20,
    		 180); 
     
     panneauPolice.setBorder(BorderFactory.createEtchedBorder());
     
     panneauPolice.setVisible(true);
    
     
     policeDeCaracteres = new JLabel("POLICE DE CARACTERES");
     policeDeCaracteres.setBounds(10, 10, 140, 20 );
     policeDeCaracteres.setHorizontalAlignment(JLabel.LEFT);
     
     etiquetteNom = new JLabel("Nom");
     etiquetteNom.setHorizontalAlignment(JLabel.LEFT);
     etiquetteNom.setBounds(20, policeDeCaracteres.getHeight() +15,
    		                100, 20);
     
     
     btnCourier= new JButton ("");
     btnCourier.setEnabled(false);
     
     listeCourier = new JComboBox();
     listeCourier.setBounds(etiquetteNom.getX() + etiquetteNom.getWidth() + 30,
    		 etiquetteNom.getY(),
    		 120, 20);
    
         
     initComboBox(COURIER, listeCourier);
     listeCourier.setSelectedItem(COURIER[1]);
     /**********************************************************************************/
     etiquetteTaille = new JLabel("Taille");
     etiquetteTaille.setBounds(etiquetteNom.getX(),
    		 listeCourier.getY()+ listeCourier.getHeight() + 10,
    		  100, 20);
     
     
     champTaille = new JTextField("12");
     champTaille.setBounds(etiquetteTaille.getX() + etiquetteTaille.getWidth() + 30,
    		 etiquetteTaille.getY(),
    		 120, 20);
     champTaille.setEditable (true);
     
     /*******************************************************************************/
     etiquetteCouleur = new JLabel("Couleurs");
     etiquetteCouleur.setBounds(etiquetteNom.getX(),
    		 etiquetteTaille.getY()+ etiquetteTaille.getHeight() + 10,
    		  100, 20);
     listeCouleurs = new JComboBox ();
     listeCouleurs.setBounds(etiquetteCouleur.getX() + etiquetteCouleur.getWidth() + 30,
    		 etiquetteCouleur.getY(),
    		 120, 20);
    
    initComboBox(NOM_COULEURS, listeCouleurs);
    listeCouleurs.setSelectedItem(NOM_COULEURS[0]);
    
    /***************************************************************************/ 
    groupeBtnRadio = new ButtonGroup();
    
    btnNormal = new JRadioButton();
    btnNormal.setText("Normal");
    btnNormal.setBounds(20, etiquetteCouleur.getY() + etiquetteCouleur.getHeight() + 20
    		     , 80, 20); 
    
    //Selectionne le bouton btnRadio1
    btnNormal.setSelected(true);
    
    /***********************************************************/
    
    btnGras = new JRadioButton();
    btnGras.setText("Gras");
    btnGras.setBounds(btnNormal.getX() + btnNormal.getWidth(), 
    		btnNormal.getY(), 80, 20); 
    
    /***********************************************************/
    
    btnItalique = new JRadioButton();
    btnItalique.setText("Italique");
    btnItalique.setBounds(btnGras.getX() + btnGras.getWidth(), 
    		btnGras.getY(), 80, 20); 
    
    /***********************************************************/
    
    //ajouter les boutons radio au meme groupe pour qu'un seul 
    //ne puisse etre selectionne a la fois
    groupeBtnRadio.add(btnNormal);
    groupeBtnRadio.add(btnGras);
    groupeBtnRadio.add(btnItalique);
    
    
    /*************************************************
     * 
     */
    
     panneauPolice.add(policeDeCaracteres);
     panneauPolice.add(etiquetteNom);
     panneauPolice.add(listeCourier);
     panneauPolice.add(etiquetteTaille);
     panneauPolice.add(champTaille);
     panneauPolice.add(etiquetteCouleur);
     panneauPolice.add(listeCouleurs);
     panneauPolice.add(btnNormal);
     panneauPolice.add(btnGras);
     panneauPolice.add(btnItalique);
    
    
    
    /*********************************************************
     * INIT PANNEAU OPTIONS
     *********************************************************/
    panneauOptions = new JPanel(null);
    panneauOptions.setBounds(10, panneauPolice.getHeight() + 20,
    		fenetreConfig.getWidth() - 20,
   		fenetreConfig.getHeight() -panneauPolice.getHeight()-60); 
    
    panneauOptions.setBorder(BorderFactory.createEtchedBorder());
    
    panneauOptions.setVisible(true);
   
    /*********************************************************/
    etiquetteOptions = new JLabel("OPTIONS");
    etiquetteOptions.setBounds(10, 10, 140, 20 );
    etiquetteOptions.setHorizontalAlignment(JLabel.LEFT);
    
    /*********************************************************/
    etiquetteCoul= new JLabel("Couleurs" );
    etiquetteCoul.setBounds(10, etiquetteOptions.getHeight() +15,
    		panneauOptions.getWidth()-30, 20);
    etiquetteCoul.setOpaque(true);
    etiquetteCoul.setBackground(Color.WHITE);
      
    /*********************************************************/
    etiquetteFond= new JLabel("Fond");
    etiquetteFond.setBounds(20, 
    		etiquetteCoul.getY()+ etiquetteCoul.getHeight() +10,
            100, 20);
  
    /************************************************************/
    listeFond = new JComboBox();
    listeFond.setBounds(etiquetteFond.getX() +etiquetteFond.getWidth() + 20, 
    		etiquetteFond.getY(), 120, 20);
    
    initComboBox(NOM_COULEURS, listeFond);
    listeFond.setSelectedItem(NOM_COULEURS[1]);
    /*********************************************************/
    etiquetteTexteSelect = new JLabel("Texte selectionne");
    etiquetteTexteSelect.setBounds(20,
    		etiquetteFond.getY() + etiquetteFond.getHeight() +10,
    		100, 20);
    
    listeTexteSelect= new JComboBox();
    listeTexteSelect.setBounds(etiquetteTexteSelect.getX() +etiquetteTexteSelect.getWidth() + 20, 
    		etiquetteTexteSelect.getY(), 120, 20);
    
    initComboBox(NOM_COULEURS, listeTexteSelect);
    listeTexteSelect.setSelectedItem(NOM_COULEURS[0]);
    /**********************************************************/
    etiquetteSelectionTexte = new JLabel("Selection texte");
    etiquetteSelectionTexte.setBounds(20,
    		etiquetteTexteSelect.getY() + etiquetteTexteSelect.getHeight() +10,
    		100, 20);
    
    listeSelectionTexte= new JComboBox();
    listeSelectionTexte.setBounds(etiquetteSelectionTexte.getX() +etiquetteSelectionTexte.getWidth() + 20, 
    		etiquetteSelectionTexte.getY(), 120, 20);
    
    initComboBox(NOM_COULEURS, listeSelectionTexte);
    listeSelectionTexte.setSelectedItem(NOM_COULEURS[2]);
    /*********************************************************/
    etiquetteCourseur = new JLabel("Curseur");
    etiquetteCourseur.setBounds(20,
    		etiquetteSelectionTexte.getY() + etiquetteSelectionTexte.getHeight() +10,
    		100, 20);
    
    listeCurseur= new JComboBox ();
    listeCurseur.setBounds(etiquetteCourseur.getX() +etiquetteCourseur.getWidth() + 20, 
    		etiquetteCourseur.getY(), 120, 20);
    
    initComboBox(NOM_COULEURS, listeCurseur);
    listeCurseur.setSelectedItem(NOM_COULEURS[0]);
    /*********************************************************/
    etiquetteAutres = new JLabel("Autres");
    etiquetteAutres.setBounds(10, etiquetteCourseur.getY() + etiquetteCourseur.getHeight() +15,
    		panneauOptions.getWidth()-30, 20);
    etiquetteAutres.setOpaque(true);
    etiquetteAutres.setBackground(Color.WHITE);
   
    /*********************************************************/
    boiteRetourALigne = new JCheckBox("Retour a la ligne"); 
    boiteRetourALigne.setBounds(20,
    		etiquetteAutres.getY() + etiquetteAutres.getHeight() +10,
    		100, 20);
    boiteRetourALigne.setSelected(true);
    /**********************************************************/
    etiquetteLongTab = new  JLabel("Long. tabulation");
    etiquetteLongTab.setBounds(20, 
 		   boiteRetourALigne.getY() + boiteRetourALigne.getHeight() +10,
     		100, 20);
    
    champLongTab = new JTextField("3") ;
     champLongTab.setBounds(etiquetteLongTab.getX() + etiquetteLongTab.getWidth() + 30,
    		 etiquetteLongTab.getY(),
    		 120, 20);
     champLongTab.setEditable (true);
    
     
    /*********************************************************/
    panneauOptions.add( etiquetteOptions);
    panneauOptions.add( etiquetteCoul);
    panneauOptions.add( etiquetteFond);
    panneauOptions.add( listeFond);
    panneauOptions.add( etiquetteTexteSelect );
    panneauOptions.add( listeTexteSelect );
    panneauOptions.add( etiquetteSelectionTexte );
    panneauOptions.add( listeSelectionTexte );
    panneauOptions.add( etiquetteCourseur);
    panneauOptions.add( listeCurseur );
    panneauOptions.add( etiquetteAutres );
    panneauOptions.add( boiteRetourALigne);
    panneauOptions.add( etiquetteLongTab );
    panneauOptions.add( champLongTab );

      
     /*****PANEAU CONFIG************/
    
     fenetreConfig.getContentPane().add(panneauPolice);
     fenetreConfig.getContentPane().add( panneauOptions);
   
     
     
     /***********************
       * AJOUT DES ECOUTEURS
       ***********************/
      
      //ajout d'un ecouteur a la fenetre 
      //(voir la redefinition de la methode windowClosing)
      fenetre.addWindowListener(this); 
      fenetreConfig.addWindowListener(this); 
      //A COMPLETER...
      
      boutonsNouveau.addActionListener(this);
      boutonsOuvrir.addActionListener(this);
      boutonsSauvegarder.addActionListener(this);
      boutonsConfig.addActionListener(this);
      /************************************************/
      
      listeCourier.addActionListener(this);
      listeCouleurs.addActionListener(this);
      champTaille.addActionListener(this);
      champTaille.addActionListener(this);
      btnNormal.addActionListener(this);
      btnGras.addActionListener(this);
      btnItalique.addActionListener(this);
      listeFond.addActionListener(this);
      listeTexteSelect.addActionListener(this);
      listeSelectionTexte.addActionListener(this);
      listeCurseur.addActionListener(this);
      boiteRetourALigne.addActionListener(this);
      champLongTab.addActionListener(this);
      /************************************************
       * RENDRE LA FENETRE PRINCIPALE VISIBLE
       ************************************************/
     
      fenetreConfig.setVisible(false); 
      
      
      
      //
      fenetre.setVisible(true);
      
      zoneTexte.requestFocus();
     
   
   }  
   
    
  
   /************************************************
    * IMPLEMENTATION DE L'INTERFACE ActionListener
    ************************************************/
   /**
    * Gestion des evenements ActionEvent.
    * @param e l'evenement intercepte.
    */
   public void actionPerformed(ActionEvent e) {
	      
	   if(e.getSource() == boutonsNouveau){
		   nouveauTexte();
	  
	   }else if(e.getSource() == boutonsOuvrir){
		   ouvrirTexte(); 
	   }else if(e.getSource() == boutonsSauvegarder){
		   sauvegarderTexte();   
	   }else if(e.getSource() == boutonsConfig){
			   suivreFenetrePrincipale();
	  
	   }else if(e.getSource() == listeCourier){
		   changeImageCourier();
			   
	   }else if(e.getSource() == champTaille){
			   changeTalle_police();
			   
	   }else if(e.getSource() == listeCouleurs){
			   changeCouleurText();
			   
	   }else if(e.getSource() == btnNormal){
			   changeTypePolice1();
			   
	   }else if(e.getSource() == btnGras){
		   changeTypePolice2();
		   
	   }else if(e.getSource() == btnItalique){
		   changeTypePolice3();
		   
	   }else if(e.getSource() == listeFond){
		   changeFondText();
		   
	   }else if(e.getSource() == listeTexteSelect){
		   changerCouleurTextSelect();
			   
	   }else if(e.getSource() == listeSelectionTexte){
		   changerCouleurFontSelect();   
			   
	   }else if(e.getSource() == listeCurseur){
		   changerCouleurCurseur();   
		   
	   } else if(e.getSource() == boiteRetourALigne){
		   retourALaLigne();
			   
	   }else if(e.getSource() == champLongTab){
		   changerLonguerTabul();
	   }   
		   
	   }
   
    /****************************
    * METHODES PRIVEES 
    ****************************/
 
   private void nouveauTexte(){
	   zoneTexte.setText(""); 
	   fichier = "Nouveau.txt";
	  // zoneTexte.requestFocus();
	   etiquetteFichier.setText("Fichier:  " + fichier);
   }
   
   private void  chargerConfiguration(){
	   final String MSG_ERR0 = "Erreur inattendue.\n " +
	 	  "Chargement de la configuration de l'éditeur impossible.";

	  // String ff="";
	   String text ="";
	   JFrame fenErreur= new JFrame("ERREUR");
	   fenErreur.setBounds(400,500,300,100);
	   fenErreur.setLayout(null);
	  
	   UtilitairesTP3 u= new UtilitairesTP3();
		  try {
			  
				  text = u.lireFichier("./config.txt"); 
			  
		  } catch (IOException e) {
				JOptionPane.showMessageDialog(fenErreur, MSG_ERR0, "ERREUR",
		   	    		  JOptionPane.ERROR_MESSAGE);
			}
		  while(text != null && text.length()!=0){
			  
			  nomPolice = text.substring(text.indexOf(':' +1), text.indexOf('\n')-1);
			//  taillePolice = parseInt(text.substring(text.indexOf(':' +1), text.indexOf('\n')-1) );
		  }
		  
   }
   
   
   private void ouvrirTexte(){
	   final String MSG_ERR2 = "Erreur inattendue.\n "
				 	 		+ " Ouverture du fichier impossible.";
	   
	   JFrame fenErreur= new JFrame("ERREUR");
	   fenErreur.setBounds(400,500,300,100);
	   fenErreur.setLayout(null);
	   
	   String ff = "";
	   String text = "";
	   UtilitairesTP3 u= new UtilitairesTP3();
		  try {
			  ff = u.selectionnerFichier("/Users/mel/Desktop/exempleFic.txt");
			
			  
			  if(ff != null)  {
				  fichier = ff.substring(ff.lastIndexOf(92) +1);
				  etiquetteFichier.setText("Fichier:  " + fichier);
				  text = u.lireFichier(ff);
				  zoneTexte.setText(text);
			  } 
			} catch (IOException e) {
				JOptionPane.showMessageDialog(fenErreur, MSG_ERR2, "ERREUR",
		   	    		  JOptionPane.ERROR_MESSAGE);
			}
		  
		
   }
   /**
    * Sauvegarde la configuration de l'editeur dans le fichier CONFIG_CHEMIN_FIC.
    */
   private void sauvegarderConfig() {
	   
	   final String MSG_ERR3 = "Erreur inattendue.\n "
	 	 		+ " Enregistrement du fichier impossible.";
	   JFrame fenErreur= new JFrame("ERREUR");
	   fenErreur.setBounds(400,500,300,100);
	   fenErreur.setLayout(null);
	        
	  String config = "nom_police:" + nomPolice + "\n" +
			    "taille_police:" + taillePolice + "\n" +
			    "couleur_police:" + couleurPolice + "\n" +
			    "type_police:" + typePolice + "\n" +
			     "couleur_fond:" + couleurFond + "\n" +
			    "couleur_text_select:" + couleurTexteSelect + "\n" +
			     "couleur_selection:" + couleurSelection + "\n" +
			    "couleur_curseur:" + couleurCurseur + "\n" +
			     "retour_ligne:" + retourLigne + "\n" +
			    "long_tab:" + longTab;
	  
	  UtilitairesTP3 u1= new UtilitairesTP3();
	  try {
			u1.sauvegarder(config, "./config.txt");
		} catch (IOException e) {
			 JOptionPane.showMessageDialog(fenErreur, MSG_ERR3, "ERREUR",
   	    		  JOptionPane.ERROR_MESSAGE);
		}
   }
   
   
   private void sauvegarderTexte() {
	  final String MSG_ERR3 = "Erreur inattendue.\n "
	 	 		+ " Enregistrement du fichier impossible.";
	   
	   JFrame fenErreur= new JFrame("ERREUR");
	   fenErreur.setBounds(400,500,300,100);
	   fenErreur.setLayout(null);
		  
		   UtilitairesTP3 u= new UtilitairesTP3();
		   try {
			 fichier = u.sauvegarder(zoneTexte.getText(), fichier);
		      
		} catch (IOException e) {
			JOptionPane.showMessageDialog(fenErreur, MSG_ERR3, "ERREUR",
	   	    		  JOptionPane.ERROR_MESSAGE);
			
		}
		 if(fichier != null)  {
			 fichier = fichier.substring(fichier.lastIndexOf(92) +1);	 
		     etiquetteFichier.setText("Fichier:  " + fichier );
		 }
   }
   
   /********************************************/ 
   private void suivreFenetrePrincipale(){
	   fenetreConfig.setVisible(true);
	   fenetreConfig.setLocation(fenetre.getX() + fenetre.getWidth(), 
       fenetre.getY());
  }
   
  /******************************************/ 
   private void initComboBox(Object[] c, JComboBox box) {
	        
	      for (int i = 0 ; i < c.length ; i++) {
	       	  box.addItem( c[i] );
	      } 
	}
 
   /****************************************/   
   private void changeImageCourier(){
    	
       	 nomPolice = (String) listeCourier.getSelectedItem();
    	 zoneTexte.setFont(new Font(nomPolice, Font.PLAIN, taillePolice) ); 
      }
    
   /*********CHANGER TAILLE POLICE **************************/
     private void changeTalle_police(){
     	
    	 final String MSG_ERR = "Taille de police invalide! \n "
    	 		+ "Doit etre un entier entre 10 et 100 inclus ";
    	 
    	   JFrame fenErreur= new JFrame("ERREUR");
    	   fenErreur.setBounds(400,500,300,100);
    	   fenErreur.setLayout(null);
    	   
    	   try {
    	      taillePolice = Integer.parseInt(champTaille.getText().trim());
    	      if ( taillePolice < 10 || taillePolice > 100) {
    	           throw new NumberFormatException();
    	      } else {
    	        
    	       zoneTexte.setFont(new Font(nomPolice, Font.PLAIN, taillePolice) );
    	       
    				}
    	    } catch (NumberFormatException nfe) {
    				//afficher msg erreur
    	      JOptionPane.showMessageDialog(fenErreur, MSG_ERR, "ERREUR",
    	    		  JOptionPane.ERROR_MESSAGE);
    			}
    	  	        
    	  }

     /*********CHANGER COULEUR TEXTE **************************/
     private void changeCouleurText(){
    	couleurPolice = NOM_COULEURS[listeCouleurs.getSelectedIndex()];
    	 
        zoneTexte.setForeground(COULEURS[listeCouleurs.getSelectedIndex()]);
     }
     
     
    /******************CHANGER TYPE POLICE ********************************/
     
     private void changeTypePolice1(){	 
    	 typePolice = "Normal"; 
    	zoneTexte.setFont(new Font(nomPolice, Font.PLAIN, taillePolice) );
     }
     
     private void changeTypePolice2(){	
    	 typePolice = "Gras";  
    	zoneTexte.setFont(new Font(nomPolice, Font.BOLD, taillePolice) );
     }
     
     private void changeTypePolice3(){	 
    	 typePolice = "Italic"; 
      	 zoneTexte.setFont(new Font(nomPolice, Font.ITALIC, taillePolice) );
     }
     

     /******************CHANGER COULEUR FOND**********************************/
    
     private void changeFondText(){
    	 couleurFond = NOM_COULEURS[listeFond.getSelectedIndex()];
    	 zoneTexte.setBackground(COULEURS[listeFond.getSelectedIndex()]);
     }
     
     /******************CHANGER COULEUR TEXTE SELECTE ************************/    
     private void changerCouleurTextSelect(){
    	 couleurTexteSelect = NOM_COULEURS[listeTexteSelect.getSelectedIndex()]; 
        zoneTexte.setSelectedTextColor(COULEURS[listeTexteSelect.getSelectedIndex()]);
     }
     
     /******************CHANGER COULEUR SELECTION TEXTE ***********************/
     private void changerCouleurFontSelect(){
    	 couleurSelection =  NOM_COULEURS[listeSelectionTexte.getSelectedIndex()]; 
         zoneTexte.setSelectionColor( COULEURS[listeSelectionTexte.getSelectedIndex()]);
     }
     
     /******************CHANGER COULEUR SELECTION TEXTE ***********************/
     private void changerCouleurCurseur(){
    	 couleurCurseur =  NOM_COULEURS[listeCurseur.getSelectedIndex()];
    	 zoneTexte.setCaretColor(COULEURS[listeCurseur.getSelectedIndex()]); 
     }
     
     /******************CHANGER COULEUR SELECTION TEXTE ***********************/
     private void retourALaLigne(){
    	          
    	 if (zoneTexte.getLineWrap()) {
    		 zoneTexte.setLineWrap(false);
    		
    	}else {
    		 zoneTexte.setWrapStyleWord(true);
      		zoneTexte.setLineWrap(true);
    	  		
    	}
    	 retourLigne = zoneTexte.getLineWrap();
     }
     
     /*****************CHANGER LONGUER DE TABULATION *************************/
   private void changerLonguerTabul(){
    	 
     final String MSG_ERR2 = "Long. tabulation invalide! \n "
  	 		+ "Doit etre un entier entre 2 et 20 inclus.";
  	 
  	   JFrame frmErreur= new JFrame("ERREUR");
  	   frmErreur.setBounds(400,500,300,100);
  	   frmErreur.setLayout(null);
  	   
  	   try {
  		   longTab = Integer.parseInt(champLongTab.getText().trim());
  	       if ( longTab < 2 || longTab > 20) {
  	           throw new NumberFormatException();
  	       } else {
  	              	zoneTexte.setTabSize(longTab);	
  	         }
  	    } catch (NumberFormatException nfe) {
  				//afficher msg erreur
  	      JOptionPane.showMessageDialog(frmErreur, MSG_ERR2, "ERREUR",
  	    		  JOptionPane.ERROR_MESSAGE);
  		}
  	    	      	    
 	  } 

     
   /**********************************************************************
    * REDEFINITION DE LA METHODE windowClosing DE LA CLASSE WindowAdapter
    **********************************************************************/
   /**
    * A la fermeture de la fenetre (avec le [x]) : enregistrement de la 
    * configuration de l'editeur dans le fichier CONFIG_CHEMIN_FIC.
    * 
    * @param e l'evenement intercepte (fermeture fenetre).
    */
   @Override
   public void windowClosing(WindowEvent e) {
      sauvegarderConfig();
   }
   


   /**
    * Initialisation de l'application.
    */
   public static void main (String [] args) {
      new TP3();
   }
   
}

