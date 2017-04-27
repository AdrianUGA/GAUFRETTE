package Model;

public class Gaufre {

	private Case[][] cases;
	
	public Gaufre(int l, int h){
		this.cases = new Case[l][h];
		for (int i = 0; i < l; i++){
			for(int j = 0; j < h; j++){
				this.cases[i][j] = new Case(Case.MANGEABLE);
			}
		}
		this.cases[0][0] = new Case(Case.POISON);
	}
}
