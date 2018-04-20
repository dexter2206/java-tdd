package toobigtosmall;

import java.util.Random;

public class GameFactory {

    private Random random;

    public GameFactory(Random random) {
        this.random = random;
    }

    public Game newGame(int lower, int upper) {
        if(lower >= upper) {
            throw new IllegalArgumentException("Lower has to be < than upper");
        }
        return new Game(random.nextInt(upper-lower) + lower);
    }
}
