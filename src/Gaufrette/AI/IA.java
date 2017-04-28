import java.util.concurrent.ThreadLocalRandom;

public class IA implements Joueur{

	// Our IA's first name is Jean and is second name is Sebastien, please be careful when you call it.
	
	final static int DIFFICULTY_EASY = 1;
	final static int DIFFICULTY_MEDIUM = 2;
	final static int DIFFICULTY_F_CKING_HARD = 3;
	
	public int difficulty;
	
	public IA(){
		difficulty = DIFFICULTY_EASY;
	}
	
	public IA(int d){
		difficulty=d;	
	}
	
	@Override
	public Coordinate eatAPieceOfWaffle() {
		switch(this.difficulty){
			case DIFFICULTY_EASY:
				return this.playAsNoob();
			case DIFFICULTY_MEDIUM:
				return this.playAsAveragePlayer();
			case DIFFICULTY_F_CKING_HARD:
				return this.playAsAkys();
			default:
				return this.playAsAdemage();
		}
		
	}

	private Coordinate playAsAdemage() {
		return playAsNoob();
		
	}

	private Coordinate playAsAkys() { // HARD
		// TODO Auto-generated method stub
		return new Coordinate(0,0);
	}

	private Coordinate playAsAveragePlayer() { // MEDIUM
		
		Integer [][] waffle = createWaffle();
		
		// Medium AI will play randomly, but won't play a losing move:
		// X O O O O		In this situation 
		// O O <----------- AI won't eat this piece
		// O
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
		
		return new Coordinate(x,y);
		
	}

	private Coordinate playAsNoob() { // EASY
		
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
		
		return new Coordinate(x,y);
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
	
}
