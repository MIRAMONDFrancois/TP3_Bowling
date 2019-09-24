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
        private int NB_QUILLES;
        private int NB_TOURS;
        private ArrayList<Integer> listeScore;
        private ArrayList<String> listeScoreEtat;
        private int toursRestants;
        private int round;
        private int quillesRestantes;
    
	public SinglePlayerGame() {
            NB_TOURS = 10;
            toursRestants = NB_TOURS;
            round = 1;
            NB_QUILLES = 10;
            quillesRestantes = NB_QUILLES;
            listeScore = new ArrayList<Integer>(NB_TOURS+2);
            listeScoreEtat = new ArrayList<String>(NB_TOURS+2);
	}
        
        private void NextTurn() {
            System.out.println("Fin du tour. " + toursRestants + " tours restants.");
            quillesRestantes = NB_QUILLES;
            if (toursRestants == 0) {
                System.out.println("Fin, calcul du score.");
                score();
            } else {
                toursRestants--;                
            }
        }

	/**
	 * Cette méthode doit être appelée à chaque lancé de boule
	 *
	 * @param nombreDeQuillesAbattues le nombre de quilles abattues lors de
	 * ce lancé
	 */
	public void lancer(int nombreDeQuillesAbattues) {
            if (nombreDeQuillesAbattues >= 0 && nombreDeQuillesAbattues <= quillesRestantes) {
                quillesRestantes -= nombreDeQuillesAbattues;
                if (quillesRestantes == 0) {
                    if (toursRestants > 1) {
                        switch (round) {
                            case 1:
                                System.out.println("STRIKE");
                                listeScore.set(NB_TOURS-toursRestants, 10);
                                listeScoreEtat.set(NB_TOURS-toursRestants, "STRIKE");
                            case 2:
                                System.out.println("SPARE");
                                listeScore.set(NB_TOURS-toursRestants, 10);
                                listeScoreEtat.set(NB_TOURS-toursRestants, "SPARE");
                        }
                        NextTurn();
                    } else { //Dernier tour
                        switch (round) {
                            case 1:
                                System.out.println("STRIKE");
                                listeScore.set(NB_TOURS-toursRestants, 10);
                                listeScoreEtat.set(NB_TOURS-toursRestants, "STRIKE");
                                round = 2;
                                break;
                            case 2:
                                if (nombreDeQuillesAbattues == 10) {
                                    System.out.println("STRIKE");
                                    listeScore.set(NB_TOURS, 10);
                                    listeScoreEtat.set(NB_TOURS, "STRIKE");
                                } else {
                                    System.out.println("SPARE");
                                    listeScore.set(NB_TOURS, 10);
                                    listeScoreEtat.set(NB_TOURS, "SPARE");
                                }
                                round = 3;
                                break;
                            case 3:
                                System.out.println("STRIKE");
                                listeScore.set(NB_TOURS+1, 10);
                                listeScoreEtat.set(NB_TOURS+1, "STRIKE");
                                NextTurn();
                                break;
                        }
                    }
                } else {
                    if (toursRestants > 1) {
                        switch (round) {
                            case 1:
                                round = 2;
                                break;
                            case 2:
                                NextTurn();
                                listeScore.set(NB_TOURS-toursRestants, NB_QUILLES-quillesRestantes);
                                listeScoreEtat.set(NB_TOURS-toursRestants, "x");
                                break;
                        }
                    } else { //Dernier tour
                        switch (round) {
                            case 1:
                                round = 2;
                                break;
                            case 2:
                                NextTurn();
                                listeScore.set(NB_TOURS-toursRestants, NB_QUILLES-quillesRestantes);
                                listeScoreEtat.set(NB_TOURS-toursRestants, "x");
                                break;
                            case 3:
                                NextTurn();
                                listeScore.set(NB_TOURS, NB_QUILLES-quillesRestantes);
                                listeScoreEtat.set(NB_TOURS, "x");
                                break;
                        }
                    }
                }
            } else {
                System.err.println("do u even know how 2 bowling");
            }
	}

	/**
	 * Cette méthode donne le score du joueur
	 *
	 * @return Le score du joueur
	 */
	public int score() {
            int score = 0;
            for (int i = 0; i < NB_TOURS; i++) {
                if (listeScoreEtat.get(i).equals("x")) {
                    score += listeScore.get(i);
                }
                if (listeScoreEtat.get(i).equals("SPARE")) {
                    score += 10 + listeScore.get(i+1);
                }
                if (listeScoreEtat.get(i).equals("STRIKE")) {
                    score += 10 + listeScore.get(i+1) + listeScore.get(i+2);
                }
            }
            return score;
	}
}
