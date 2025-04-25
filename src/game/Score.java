package game;

// Score class
public class Score {

        private int score;

        // Constructor to create a score
        public Score() {
            this.score = 0;
        }

        // Add points to the score
        public void addPoints(int points) {
            score += points;
            System.out.println("Score: " + score);
        }

        // Return the score
        public int getScore() {
            return score;
        }

}

