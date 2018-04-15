package vector;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Vector {

    private int[] components;

    public Vector(int x, int y, int z) {
        components = new int[] {x, y, z};
    }

    public int get(int index) {
        if(index < 0) {
            throw new IndexOutOfBoundsException("Only nonnegative indexes are allowed.");
        }
        if(index > 2) {
            throw new IndexOutOfBoundsException("Vector has only 3 components!");
        }
        return components[index];
    }

    public double length() {
        return Math.sqrt(Arrays.stream(components).map(x -> x * x).sum());
    }

    public Vector add(Vector v2) {
        if(v2 == null) {
            throw new IllegalArgumentException("Cannot add null vector");
        }
        return new Vector(get(0) + v2.get(0), get(1) + v2.get(1),
                get(2) + v2.get(2));
    }

    public int[] toArray() {
        return new int[] {get(0), get(1), get(2)};
    }

    @Override
    public boolean equals(Object other) {
        if(other == null) {
            return false;
        }
        if(!other.getClass().equals(getClass())) {
            return false;
        }
        Vector v2 = (Vector) other;
        return IntStream.range(0, 3).allMatch(i -> get(i) == v2.get(i));
    }
}
