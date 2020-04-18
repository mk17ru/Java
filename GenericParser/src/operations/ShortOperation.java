package operations;

import exceptions.DivisionByZeroException;

public class ShortOperation implements Operation<Short> {

    public Short add(Short x, Short y) {
        return (short) (x + y);
    }

    public Short sub(Short x, Short y) {
        return (short) (x - y);
    }

    public Short mul(Short x, Short y) {
        return (short) (x * y);
    }

    public Short div(Short x, Short y) {
        if (y == 0) {
            throw new DivisionByZeroException(x + " / " + y);
        }
        return (short)(x / y);
    }

    public Short neg(Short x) {
        return (short)-x;
    }

    public Short abs(Short x) {
        return (short)Math.abs(x);
    }

    public Short min(Short a, Short b) { return (short)Math.min(a, b); }

    public Short max(Short a, Short b) { return (short)Math.max(a, b); }

    public Short parseValue(String str) { return (short)Integer.parseInt(str); }

    public Short count(Short a) {
        return (short) Integer.bitCount(a & 0xFFFF);
    }

}