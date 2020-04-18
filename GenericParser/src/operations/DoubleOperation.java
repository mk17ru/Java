package operations;


public class DoubleOperation implements Operation<Double> {

    public Double add(Double x, Double y) {
        return x + y;
    }

    public Double sub(Double x, Double y) {
        return x - y;
    }

    public Double mul(Double x, Double y) {
        return x * y;
    }

    public Double div(Double x, Double y) {
        return x / y;
    }

    public Double neg(Double x) {
        return -x;
    }

    public Double abs(Double x) {
        return Math.abs(x);
    }

    public Double min(Double a, Double b) { return Double.min(a, b); }

    public Double max(Double a, Double b) { return Double.max(a, b); }

    public Double parseValue(String str) { return Double.parseDouble(str); }

    public Double count(Double a) {
        return (double) Long.bitCount(Double.doubleToLongBits(a));    }
}
