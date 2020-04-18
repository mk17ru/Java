package operations;

import exceptions.DivisionByZeroException;

public class LongOperation implements Operation<Long> {

    public Long add(Long x, Long y) {
        return x + y;
    }

    public Long sub(Long x, Long y) {
        return x - y;
    }

    public Long mul(Long x, Long y) {
        return x * y;
    }

    public Long div(Long x, Long y) {
        if (y == 0L) {
            throw new DivisionByZeroException(x + " / " + y);
        }
        return x / y;
    }

    public Long neg(Long x) {
        return -x;
    }

    public Long abs(Long x) {
        return Math.abs(x);
    }

    public Long min(Long a, Long b) { return Long.min(a, b); }

    public Long max(Long a, Long b) { return Long.max(a, b); }

    public Long parseValue(String str) { return Long.parseLong(str); }

    public Long count(Long a) {
        return (long) Long.bitCount(a);
    }
}