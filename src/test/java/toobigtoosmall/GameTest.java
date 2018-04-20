package toobigtoosmall;

import org.junit.Test;
import toobigtosmall.Game;
import toobigtosmall.GameOverException;
import toobigtosmall.GuessResult;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void whenGuessTooBigReturnsTooBig() {
        Game game = new Game(100);
        assertEquals(GuessResult.TOOBIG, game.guess(102));
    }

    @Test
    public void whenGuessTooBigGameContinues() {
        Game game = new Game(1000);
        game.guess(3000);
        assertTrue(game.isGameOn() );
    }

    @Test
    public void whenGuessTooSmallReturnsTooSmall() {
        Game game = new Game(200);
        assertEquals(GuessResult.TOOSMALL, game.guess(99));
    }

    @Test
    public void whenGuessTooSmallGameContinues() {
        Game game = new Game(2137);
        game.guess(1);
        assertTrue(game.isGameOn());
    }

    @Test
    public void whenGuessEqualReturnsEqual() {
        Game game = new Game(50);
        assertEquals(GuessResult.EQUAL, game.guess(50));
    }

    @Test
    public void whenGuessEqualGameStops() {
        Game game = new Game(50);
        game.guess(50);
        assertFalse(game.isGameOn());
    }

    @Test(expected= GameOverException.class)
    public void whenGameIsOverCantGuessNoMore() {
        Game game = new Game(300);
        game.guess(300);
        game.guess(200);
    }
}