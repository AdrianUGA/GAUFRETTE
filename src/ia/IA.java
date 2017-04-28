package ia;
import java.util.concurrent.ThreadLocalRandom;

import Gaufrette.Model.Coordonnees;
import Gaufrette.Model.Joueur;

public class IA implements Joueur{

	// Our IA's first name is Jean and is second name is Sebastien, please be careful when you call it.
	
	private static final String NOM_PAR_DÉFAUT = "Michel Ocello";
	
	
	public Difficulté Difficulté;
	private String nom;
	
	public IA(){
		this.init(Difficulté.facile, NOM_PAR_DÉFAUT);
	}
	
	public IA(Difficulté Difficulté){
		this.init(Difficulté, NOM_PAR_DÉFAUT);
	}
	
	public IA(String nom){
		this.init(Difficulté.facile, nom);
	}
	
	public IA(Difficulté Difficulté, String nom){
		this.init(Difficulté, nom);
	}
	
	private void init(Difficulté Difficulté, String nom){
		this.Difficulté = Difficulté;
		this.nom = nom;
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
		Integer [][] waffle = createWaffle();
		
		int minHeight=0;
		int maxHeight=0;
		int minWidth=0;
		int maxWidth=0;
		
		if(waffle[1][0] != 3){ // We check if we have at least 2 lines not eaten
			minHeight = 1;
		}
		if(waffle[0][1] != 3){ // We check if we have at least 2 columns not eaten
			minWidth = 1;
		}
		
		for(int i=0; i<waffle[0].length;i++){ // Calculating the amount of columns not eaten yet
			if(waffle[0][i] != 3){
				maxWidth = i;
			}
		}
		
		for(int i=0; i<waffle.length;i++){ // Calculating the amount of lines not eaten yet
			if(waffle[i][0] != 3){
				maxHeight = i;
			}
		}
		
		if(maxHeight >= 2 && maxWidth >= 3){
			if(waffle[1][2] != 3){
				return new Coordonnees(2,1);
			}
			else if(maxHeight >= 3){
				if(waffle[1][2] != 3){
					return new Coordonnees(1,2);
				}
				else{
					// Aléatoire
					return jouerCommeUnNul(); 
				}
			}
		}
		else if(maxHeight >= 3 && maxWidth >= 2){
			if(waffle[2][1] != 3){
				return new Coordonnees(1,2);
			}
			else if(maxWidth>=3){
				if(waffle[1][2] != 3){
					return new Coordonnees(2,1);
				}
				else{
					// Aléatoire
					return jouerCommeUnNul();
				}
			}
		}
		else if(maxHeight == 2 && maxWidth == 2){
			if(waffle[1][1] != 3){
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
		// La fonction doit être terminée ici, si la ligne suivante s'affiche l'algo est bugué
		System.out.println("Problème algo difficile");
		return jouerCommeUnNul();
	}
		

	private Coordonnees jouerCommeUnMecLambda() { // MEDIUM
		
		Integer [][] waffle = createWaffle();
		
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
		
		if(waffle[1][0] != 3){ // We check if we have at least 2 lines not eaten
			minHeight = 1;
		}
		if(waffle[0][1] != 3){ // We check if we have at least 2 columns not eaten
			minWidth = 1;
		}
		
		for(int i=0; i<waffle[0].length;i++){ // Calculating the amount of columns not eaten yet
			if(waffle[0][i] != 3){
				maxWidth = i;
			}
		}
		
		for(int i=0; i<waffle.length;i++){ // Calculating the amount of lines not eaten yet
			if(waffle[i][0] != 3){
				maxHeight = i;
			}
		}
		
		x = ThreadLocalRandom.current().nextInt(minHeight, maxHeight); // Choosing the line
		
		for(int i=0; i<waffle[x].length;i++){ // Calculating the amount of columns not eaten in our line
			if(waffle[x][i] != 3){
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
		
		Integer [][] waffle = createWaffle();
		
		// Easy AI will play randomly, but won't eat the poisoned piece, except if it has no choice
		
		int minHeight=0;
		int maxHeight=0;
		int minWidth=0;
		int maxWidth=0;
		
		if(waffle[1][0] != 3){ // We check if we have at least 2 lines not eaten
			minHeight = 1;
		}
		if(waffle[0][1] != 3){ // We check if we have at least 2 columns not eaten
			minWidth = 1;
		}
		
		for(int i=0; i<waffle.length;i++){ // Calculating the amount of lines not eaten yet
			if(waffle[i][0] != 3){
				maxHeight = i;
			}
		}
		
		int x = ThreadLocalRandom.current().nextInt(minHeight, maxHeight); // Choosing the line
		
		for(int i=0; i<waffle[x].length;i++){ // Calculating the amount of columns not eaten in our line
			if(waffle[x][i] != 3){
				maxWidth = i;
			}
		}
		
		int y = ThreadLocalRandom.current().nextInt(minWidth, maxWidth);
		
		return new Coordonnees(x,y);
		// DONE
	}
	
	public Integer[][] createWaffle(){
		int height = ThreadLocalRandom.current().nextInt(2, 15 + 1);
		int width = ThreadLocalRandom.current().nextInt(2, 15 + 1);
		
		Integer [][] waffle = new Integer[height][width];

		for(int i=0; i<height; i++){ 
			for(int j=0; j<width; j++){
				waffle[i][j]=2;
			}
		}
		waffle[0][0] = 1;
		return waffle;
	}

	@Override
	public String getNom() {
		return this.nom;
	}
	
	public IA setNom(String nom){
		this.nom = nom;
		return this;
	}
	
}
