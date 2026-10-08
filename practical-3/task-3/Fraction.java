import java.util.Objects;

public class Fraction {
    private final int num;
    private final int den;

    public Fraction(int num, int den) {
        if (den == 0) {
            throw new IllegalArgumentException("Denominator cannot be zero.");
        }
        int g = gcd(Math.abs(num), Math.abs(den));
        int n = num / g;
        int d = den / g;

        if (d < 0) {
            n = -n;
            d = -d;
        }
        this.num = n;
        this.den = d;
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public int getNum() { return num; }
    public int getDen() { return den; }

    @Override
    public String toString() {
        return (den == 1) ? String.valueOf(num) : (num + "/" + den);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Fraction other = (Fraction) obj;
        return this.num == other.num && this.den == other.den;
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, den);
    }
}
