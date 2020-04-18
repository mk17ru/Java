package operations;

import exceptions.DivisionByZeroException;

import java.math.BigInteger;

public class BigIntegerOperation implements Operation<BigInteger> {

    public BigInteger min(BigInteger a, BigInteger b) {
        return a.min(b);
    }

    public BigInteger sub(BigInteger x, BigInteger y) {
        return x.subtract(y);
    }

    public BigInteger mul(BigInteger x, BigInteger y) {
        return x.multiply(y);
    }

    public BigInteger max(BigInteger x, BigInteger y) {
        return x.max(y);
    }

    public BigInteger div(BigInteger x, BigInteger y) {
        if (y.equals(BigInteger.ZERO)) {
            throw new DivisionByZeroException(x + " / " + y);
        }
        return x.divide(y);
    }

    public BigInteger add(BigInteger a, BigInteger b) {
        return a.add(b);
    }

    public BigInteger neg(BigInteger a) {
        return a.negate();
    }

    public BigInteger abs(BigInteger a) {return a.abs();}

    public BigInteger parseValue(String str) {
        return new BigInteger(str);
    }

    public BigInteger count(BigInteger a) {
        return new BigInteger(Integer.toString(a.bitCount()));
    }
}
