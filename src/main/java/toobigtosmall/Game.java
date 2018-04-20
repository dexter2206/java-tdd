package toobigtosmall;

public class Game {

    private int numberToGuess;
    private boolean gameOn;

    public Game(int numberToGuess) {
        this.numberToGuess = numberToGuess;
        this.gameOn = true;
    }

    public GuessResult guess(int nextGuess) {
        if(!gameOn) {
            throw new GameOverException("Game is already finished.");
        }
        if(nextGuess > this.numberToGuess) {
            return GuessResult.TOOBIG;
        }
        if(nextGuess < this.numberToGuess) {
            return GuessResult.TOOSMALL;
        }
        this.gameOn = false;
        return GuessResult.EQUAL;
    }

    public int getNumberToGuess() {
        return numberToGuess;
    }

    public void setNumberToGuess(int numberToGuess) {
        this.numberToGuess = numberToGuess;
    }

    public boolean isGameOn() {
        return gameOn;
    }

    public void setGameOn(boolean gameOn) {
        this.gameOn = gameOn;
    }
}
