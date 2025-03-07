package game;

public class Score {

        private int score;

        public Score() {
            this.score = 0;
        }

        public void addPoints(int points) {
            score += points;
            System.out.println("Score: " + score);
        }

        public int getScore() {
            return score;
        }

}

