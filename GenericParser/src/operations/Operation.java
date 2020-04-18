package operations;

public interface Operation<T> {
    T add(T x, T y);
    T sub(T a, T b);
    T mul(T a, T b);
    T div(T a, T b);
    T min(T a, T b);
    T max(T a, T b);
    T neg(T a);
    T abs(T a);
    T parseValue(String a);
    T count(T a);
}
