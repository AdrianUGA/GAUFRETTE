package Gaufrette.Model;

public class Jeu {

	private Gaufre gaufre;
	private Joueur[] joueurs;
    private int joueurCourant;

	public Jeu(){
		this.joueurs = new Joueur[2];
	}
	
	public void nouveauJeu(int largeurGaufre, int hauteurGaufre){
		this.gaufre = new Gaufre(largeurGaufre, hauteurGaufre);
        this.joueurCourant = 0;
    }

	public void setJoueur1(String type, String nom){
	    if (type.equals("humain")){
	        this.joueurs[0] = new Humain(nom);
        }else{
	        this.joueurs[0] = new IA(this);
        }
    }

    public void setJoueur2(String type, String nom){
        if (type.equals("humain")){
            this.joueurs[1] = new Humain(nom);
        }else{
            this.joueurs[1] = new IA(this);
        }
    }

	public boolean coupEstValide(Coordonnees c){
        return this.gaufre.peutManger(c);
    }

    public void jouer(Coordonnees c){
	    if (this.coupEstValide(c)){
            this.gaufre.manger(c);
            this.nextJoueur();
        }
	}

    public void nextJoueur(){
        this.joueurCourant = (this.joueurCourant + 1) % 2;
    }

    public boolean partieTerminee(){
		return this.gaufre.toutMangees();
	}

    public Joueur getJoueurCourant(){
	    return this.joueurs[this.joueurCourant];
    }

    public Gaufre getGaufre(){
        return this.gaufre;
    }
}
