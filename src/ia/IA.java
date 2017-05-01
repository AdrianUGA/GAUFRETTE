package ia;
import java.util.concurrent.ThreadLocalRandom;

import Gaufrette.Model.Case;
import Gaufrette.Model.Coordonnees;
import Gaufrette.Model.Jeu;
import Gaufrette.Model.Joueur;
import Gaufrette.Vue.App;

public class IA implements Joueur{

	// Our IA's first name is Jean and is second name is Sebastien, please be careful when you call it.
	
	private static final String NOM_PAR_DÉFAUT = "Michel Ocello";
	
	
	public Difficulté Difficulté;
	private String nom;
	private Jeu jeu;
	
	public IA(Jeu jeu){
		this.jeu = jeu;
		this.Difficulté = Difficulté.facile;
		this.nom = NOM_PAR_DÉFAUT;
	}
	
	@Override
	public Coordonnees jouer() {
		switch(this.Difficulté){
			case facile:
				return this.jouerCommeUnNul();
			case moyen:
				return this.jouerCommeUnMecLambda();
			case p_tain_de_difficile:
				return this.jouerCommeAkys();
			default:
				return this.jouerCommeAdemage();
		}
		
	}

	private Coordonnees jouerCommeAdemage() {
		return this.jouerCommeUnNul();
		
	}

	private Coordonnees jouerCommeAkys() { // HARD
		Case [][] waffle = this.jeu.getGaufre().getCases();
		
		int minHeight=0;
		int maxHeight=0;
		int minWidth=0;
		int maxWidth=0;
		
		if(waffle[1][0] != Case.MANGEE){ // We check if we have at least 2 lines not eaten
			minHeight = 1;
		}
		if(waffle[0][1] != Case.MANGEE){ // We check if we have at least 2 columns not eaten
			minWidth = 1;
		}
		
		for(int i=0; i<waffle[0].length;i++){ // Calculating the amount of columns not eaten yet
			if(waffle[0][i] != Case.MANGEE){
				maxWidth = i;
			}
		}
		
		for(int i=0; i<waffle.length;i++){ // Calculating the amount of lines not eaten yet
			if(waffle[i][0] != Case.MANGEE){
				maxHeight = i;
			}
		}
		
		if(maxHeight >= 2 && maxWidth >= 3){
			if(waffle[1][2] != Case.MANGEE){
				return new Coordonnees(2,1);
			}
			else if(maxHeight >= 3){
				if(waffle[1][2] != Case.MANGEE){
					return new Coordonnees(1,2);
				}
				else{
					// Aléatoire
					return jouerCommeUnNul(); 
				}
			}
		}
		else if(maxHeight >= 3 && maxWidth >= 2){
			if(waffle[2][1] != Case.MANGEE){
				return new Coordonnees(1,2);
			}
			else if(maxWidth>=3){
				if(waffle[1][2] != Case.MANGEE){
					return new Coordonnees(2,1);
				}
				else{
					// Aléatoire
					return jouerCommeUnNul();
				}
			}
		}
		else if(maxHeight == 2 && maxWidth == 2){
			if(waffle[1][1] != Case.MANGEE){
				return new Coordonnees(1,1);
			}
			else{
				// Aléatoire
				return jouerCommeUnNul();
			}
		}
		else if(maxHeight > maxWidth){
			return new Coordonnees(maxHeight-maxWidth-1, 0);
		}
		else if(maxWidth > maxHeight){
			return new Coordonnees(0, maxWidth-maxHeight-1);
		}
		else{
			// Aléatoire
			return jouerCommeUnNul();
		}
		// La fonction doit étre terminée ici, si la ligne suivante s'affiche l'algo est bugué
		System.out.println("Probléme algo difficile");
		return jouerCommeUnNul();
	}
		

	private Coordonnees jouerCommeUnMecLambda() { // MEDIUM
		
		Case [][] waffle = this.jeu.getGaufre().getCases();
		
		// Medium AI will play randomly, but won't play a losing move:
		// X O O O O		In this situation 
		// O O 
		// O O <----------- AI won't eat this piece
		// O
		
		int minHeight=0;
		int maxHeight=0;
		int minWidth=0;
		int maxWidth=0;
		int maxWidthForSpecificHeight=0;
		int x = 0;
		int y = 0;
		
		if(waffle[1][0] != Case.MANGEE){ // We check if we have at least 2 lines not eaten
			minHeight = 1;
		}
		if(waffle[0][1] != Case.MANGEE){ // We check if we have at least 2 columns not eaten
			minWidth = 1;
		}
		
		for(int i=0; i<waffle[0].length;i++){ // Calculating the amount of columns not eaten yet
			if(waffle[0][i] != Case.MANGEE){
				maxWidth = i;
			}
		}
		
		for(int i=0; i<waffle.length;i++){ // Calculating the amount of lines not eaten yet
			if(waffle[i][0] != Case.MANGEE){
				maxHeight = i;
			}
		}
		
		x = ThreadLocalRandom.current().nextInt(minHeight, maxHeight); // Choosing the line
		
		for(int i=0; i<waffle[x].length;i++){ // Calculating the amount of columns not eaten in our line
			if(waffle[x][i] != Case.MANGEE){
				maxWidthForSpecificHeight = i;
			}
		}
		
		if(x == 1 && (maxHeight == maxWidth + 1 || maxHeight == maxWidth - 1)){
			do{
				y = ThreadLocalRandom.current().nextInt(minWidth, maxWidthForSpecificHeight);
			}while(y==1);
		}
		
		return new Coordonnees(x,y);
		
	}

	private Coordonnees jouerCommeUnNul() { // EASY
		
		Case [][] waffle = this.jeu.getGaufre().getCases();
		
		// Easy AI will play randomly, but won't eat the poisoned piece, except if it has no choice
		
		int minHeight=0;
		int maxHeight=0;
		int minWidth=0;
		int maxWidth=0;
		
		if(waffle[1][0] != Case.MANGEE){ // We check if we have at least 2 lines not eaten
			minHeight = 1;
		}
		if(waffle[0][1] != Case.MANGEE){ // We check if we have at least 2 columns not eaten
			minWidth = 1;
		}
		
		for(int i=0; i<waffle.length;i++){ // Calculating the amount of lines not eaten yet
			if(waffle[i][0] != Case.MANGEE){
				maxHeight = i;
			}
		}
		
		int x = ThreadLocalRandom.current().nextInt(minHeight, maxHeight); // Choosing the line
		
		for(int i=0; i<waffle[x].length;i++){ // Calculating the amount of columns not eaten in our line
			if(waffle[x][i] != Case.MANGEE){
				maxWidth = i;
			}
		}
		
		int y = ThreadLocalRandom.current().nextInt(minWidth, maxWidth);
		
		return new Coordonnees(x,y);
		// DONE
	}
	
	
	@Override
	public String getNom() {
		return this.nom;
	}
	
	public IA setNom(String nom){
		this.nom = nom;
		return this;
	}
	
	public IA setDifficulté(Difficulté difficulté){
		this.Difficulté = difficulté;
		return this;
	}
	
}
