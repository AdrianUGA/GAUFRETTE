package Model;

public class Jeu {

	private Gaufre gaufre;
	private Joueur j1, j2, joueurCourant;
	
	public Jeu(){
		
	}
	
	public void nouveauJeu(String typeJ1, String typeJ2, int largeurGaufre, int hauteurGaufre){
		this.j1 = new Humain("j1");
		this.j2 = new Humain("j2");
		this.joueurCourant = j1;
		this.gaufre = new Gaufre(largeurGaufre, hauteurGaufre);
	}
}
