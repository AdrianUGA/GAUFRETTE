package Gaufrette.Model;

public class Gaufre {

	private Case[][] cases;
	private int largeur;
	private int hauteur;
	
	public Gaufre(int l, int h){
		this.cases = new Case[h][l];
		this.largeur = l;
		this.hauteur = h;
		for (int i = 0; i < h; i++){
			for(int j = 0; j < l; j++){
				this.cases[i][j] = Case.MANGEABLE;
			}
		}
		this.cases[0][0] = Case.POISON;
	}

	public boolean peutManger(Coordonnees c){
		return c != null && c.x >= 0 && c.x < largeur && c.y >= 0 && c.y < hauteur && this.cases[c.y][c.x].estMangeable();
	}

	public boolean toutMangees(){
		return this.cases[0][1].estMangee() && this.cases[1][0].estMangee();
	}

	public void manger(Coordonnees c){
		for (int i = c.y; i < this.hauteur; i++){
			for (int j = c.x; j < this.largeur; j++){
				this.cases[i][j] = Case.MANGEE;
			}
		}
	}

	public String toString(){
	    String string = "";
        for (int i = 0; i < this.hauteur; i++){
            for(int j = 0; j < this.largeur; j++){
                string += this.cases[i][j] + "\t";
            }
            string += "\n";
        }
        return string;
    }

    public Case[][] getCases(){
    	return this.cases;
    }

    public int getLargeur(){
    	return this.largeur;
	}

	public int getHauteur(){
    	return this.hauteur;
	}

}
