package queue;

import java.util.function.Function;
import java.util.function.Predicate;

//
// Let be a = sequence of queue elements
// n = a.length
//
//INV: n >= 0 && for i = 0..n - 1: a[i] != null
// First in - Last out

public interface Queue {

    // Pre: elem != null
    // Post: Q' == {a1, a2, ..., an, element} && |Q|' == |Q| + 1
    void enqueue(Object element);

    // Pre: |Q| > 0
    // Post: R = a1, Q' = {a2, ... ,an} && |Q'| == |Q| - 1
    Object dequeue();

    // Pre: |Q| > 0
    // Post: R = a1, Q == Q'
    Object element();

    // Pre: true
    // Post: R = |Q|, Q' == Q
    int size();

    // Pre: true
    // Post: R = (|Q| == 0), Q == Q'
    boolean isEmpty();

    // Pre: true
    // Post: |Q| == 0, Q == Q'
    void clear();

    // Pre: predicate != null
    // Post: R = (Q_new ⊆ Q, Q_new = {a_i1, a_i2, .. a_ik}, 0<= i1 < i2 <...<ik < n
    // for all i 0 <= i < n: i in (i1, i2, ..., ik) <=> predicate(a_i)
    // for all j not in (i1, .., ik) && 0 <= j < n: predicate(a_j) = false,
    // Q' == Q, n == n'
    Queue filter(Predicate<Object> predicate);

    // Pre: function != null && results of func != null
    // Post: R = (Q_new = {func(a1), func(a2), .. func(an)}) && Q' == Q && n == n'
    Queue map(Function<Object, Object> function);
}
