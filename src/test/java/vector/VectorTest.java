package vector;

import org.junit.Test;

import static org.junit.Assert.*;

public class VectorTest {

    @Test
    public void whenCorrectIndexPassedReturnsComponent() {
        Vector v = new Vector(4, -3, 4);
        assertEquals(4, v.get(0));
        assertEquals(-3, v.get(1));
        assertEquals(4, v.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenNegativeIndexPassedThrowsException() {
        Vector v = new Vector(1, 0, 2);
        v.get(-3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenIndexLargerThan2ThrowsException() {
        Vector v = new Vector(7, 21, 16);
        v.get(3);
    }

    @Test
    public void zeroVectorHasZeroLength() {
        Vector v = new Vector(0, 0, 0);
        assertEquals(0.0, v.length(), 1e-20);
    }

    @Test
    public void nonZeroVectorHasCorrectLength() {
        Vector v = new Vector(2,3, 7);
        assertEquals(Math.sqrt(62), v.length(), 1e-20);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNullIsAddedThrowsException() {
        Vector v = new Vector(6,1,-2);
        v.add(null);
    }

    @Test
    public void whenTwoVectorsAddedResultIsCorrect() {
        Vector v1 = new Vector(3,8,-3), v2 = new Vector(0, -4, 5);
        Vector v3 = v1.add(v2);
        assertEquals(3, v3.get(0));
        assertEquals(4, v3.get(1));
        assertEquals(2, v3.get(2));
    }

    @Test
    public void whenAddingVectorsOriginalVectorsAreUnchanged() {
        Vector v1 = new Vector(-1, 3, -8), v2 = new Vector(1,2,3);
        v1.add(v2);
        assertEquals(-1, v1.get(0));
        assertEquals(3, v1.get(1));
        assertEquals(-8, v1.get(2));
        assertEquals(1, v2.get(0));
        assertEquals(2, v2.get(1));
        assertEquals(3, v2.get(2));
    }

    @Test
    public void whenComparedToNullReturnsFalse() {
        Vector v1 = new Vector(5,-5,1);
        assertFalse(v1.equals(null));
    }

    @Test
    public void whenComparedToNonVectorReturnsFalse() {
        Vector v1 = new Vector(5, -6, 2);
        assertFalse(v1.equals("test"));
        assertFalse(v1.equals(new Integer(2)));
        assertFalse(v1.equals(new Float(5.0)));
    }

    @Test
    public void whenComparedToDifferentVectorReturnsFalse() {
        Vector v1 = new Vector(5,2,-1), v2 = new Vector(0, 3, 4);
        assertFalse(v1.equals(v2));
    }

    @Test
    public void whenComparedToEqualVectorReturnsTrue() {
        Vector v1 = new Vector(-2,0, 7), v2 = new Vector(-2, 0, 7);
        assertTrue(v1.equals(v2));
    }

    @Test
    public void toArrayCreatesCopy() {
        Vector v = new Vector(1, 3, 0);
        int[] arr = v.toArray();
        arr[0] = 10;
        arr[1] = 20;
        arr[2] = 30;
        assertEquals(1, v.get(0));
        assertEquals( 3, v.get(1));
        assertEquals(0, v.get(2));
    }

    @Test
    public void toArrayHasCorrectElements() {
        Vector v = new Vector(2, 6, -7);
        int[] arr = v.toArray();
        assertArrayEquals(new int[] {2,6, -7}, arr);
    }
}