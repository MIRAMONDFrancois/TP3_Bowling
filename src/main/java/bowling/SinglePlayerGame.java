package bowling;

import java.util.ArrayList;

/**
 * Cette classe a pour but d'enregistrer le nombre de quilles abattues lors des
 * lancés successifs d'<b>un seul et même</b> joueur, et de calculer le score
 * final de ce joueur
 */
public class SinglePlayerGame {

	/**
	 * Constructeur
	 */
        private ArrayList<Integer> listeScore;
        private ArrayList<String> listeScoreEtat;
        private int TURN_MAX;
        private int TURN_N;
        private int QUILLES_MAX;
        private int QUILLES;
        private int ROUND = 1;
    
	public SinglePlayerGame() {
            TURN_MAX = 10;
            TURN_N = 0;
            QUILLES_MAX = 10;
            QUILLES = QUILLES_MAX;
            ROUND = 1;
            listeScore = new ArrayList<Integer>();
            listeScoreEtat = new ArrayList<String>();
	}
        
        private void NextTurn() {
            TURN_N++;
            ROUND = 1;
            QUILLES = QUILLES_MAX;
        }

	/**
	 * Cette méthode doit être appelée à chaque lancé de boule
	 *
	 * @param nombreDeQuillesAbattues le nombre de quilles abattues lors de
	 * ce lancé
	 */
	public void lancer(int nombreDeQuillesAbattues) {
            listeScore.add(nombreDeQuillesAbattues);
            if (nombreDeQuillesAbattues == 10) {
                // STRIKE
                listeScoreEtat.add("STRIKE");    
            } else {
                if (nombreDeQuillesAbattues + QUILLES == 10) {
                    // SPARE
                    listeScoreEtat.add("SPARE");
                } else {
                    listeScoreEtat.add("x");
                }
            }
            QUILLES -= nombreDeQuillesAbattues;
            if (TURN_N == TURN_MAX-1) {
                if (listeScoreEtat.get(TURN_MAX-1).equals("STRIKE")) {
                    if (ROUND < 3) {
                        ROUND++;
                    } else {
                        NextTurn();
                    }
                }
            }
            if (ROUND < 2) {
                ROUND++;
            } else {
                NextTurn();
            }
            
	}

	/**
	 * Cette méthode donne le score du joueur
	 *
	 * @return Le score du joueur
	 */
	public int score() {
            int score = 0;
            int taille = listeScore.size();
            for (int i = 0; i < taille; i++) {
                if (i < taille - 2) {
                    if (listeScoreEtat.get(i).equals("SPARE")) {
                        listeScore.set(i+1, 2 * listeScore.get(i+1));
                    }
                    if (listeScoreEtat.get(i).equals("STRIKE")) {
                        listeScore.set(i+1, 2 * listeScore.get(i+1));
                        listeScore.set(i+2, 2 * listeScore.get(i+2));
                    }
                }
                score += listeScore.get(i);
            }
            return score;
	}
}
