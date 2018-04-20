package toobigtoosmall;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import toobigtosmall.Game;
import toobigtosmall.GameFactory;

import java.util.Random;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class GameFactoryTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private Random random;

    @Test(expected=IllegalArgumentException.class)
    public void whenInvalidBoundsGivenNewGameThrows() {
        GameFactory factory = new GameFactory(random);
        factory.newGame(300, 200);
    }

    @Test
    public void whenCreatingNewGameChoosesNumberAtRandom() {
        GameFactory factory = new GameFactory(random);
        when(random.nextInt(100)).thenReturn(50);
        Game game = factory.newGame(100, 200);
        assertEquals(150, game.getNumberToGuess());
        verify(random, times(1)).nextInt(100);
    }

    @Test
    @Parameters({"200,230,15", "1,7,4" })
    public void parameterizedWhenCreatingNewGameChoosesNumberAtRandom(int lower, int upper, int randNum) {
        GameFactory factory = new GameFactory(random);
        when(random.nextInt(upper-lower)).thenReturn(randNum);
        Game game = factory.newGame(lower, upper);
        assertEquals(lower+randNum, game.getNumberToGuess());
        verify(random, times(1)).nextInt(upper-lower);
    }
}