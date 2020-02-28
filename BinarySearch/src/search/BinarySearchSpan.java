package search;

public class BinarySearchSpan {

    // Let be a[-1] == +inf && a[a.length] == -inf
    // PRE: for i = -1..a.length - 1 : a[i] >= a[i + 1] && a[r] < x <= a[l]
    // POST: R: a[R] < x && a[R - 1] >= x && элементы массива a не изменяются
    public static int binaryRightIterativeSearch(int x, int[] a) {
        int l = -1, r = a.length;
        // l == -1 && r == a.length
        // INV: -1 <= l < r <= a.length && a[r] < x <= a[l] && r' - l' < r - l
        while (r - l > 1) {
            // -1 <= l < l + 1 < r <= a.length && a[r] < x <= a[l] && r' - l' < r - l
            int m = (l + r) / 2;
            // -1 <= l < l + 1 < r <= a.length -> l < (r + l) / 2 == m < r
            // -1 < m < a.length -> a[m] exist
            if (a[m] < x) {
                // a[m] < x && a[r] < x <= a[l] && -1 <= l < m < r <= a.length -> a[r] < a[m] < x <= a[l]
                // l' == l && r' == m -> r' < r && l' == l -> r' - l' < r - l
                r = m;
                //-1 <= l < r <= a.length && a[r] < x <= a[l] && r' - l' < r - l
            } else {
                // x <= a[m] && a[r] < x <= a[l] && -1 <= l < m < r <= a.length -> a[r] < x <= a[m] <= a[l]
                // l' == m && r' == r -> r' == r && l' > l -> r' - l' < r - l
                l = m;
                //-1 <= l < r <= a.length && a[r] < x <= a[l] && r' - l' < r - l
            }
        }
        // l + 1 >= r  && l < r ->  l + 1 == r
        //-1 <= l < r <= a.length && a[r] < x <= a[l] && l + 1 == r ->
        // a[r] < x && a[r - 1] >= x
        return r;
    }

    // Let be a[-1] == +inf && a[a.length] == -inf
    // PRE: (for i = -1..a.length - 1 : a[i] >= a[i + 1])
    // POST: a[R] <= x && a[R - 1] > x && элементы массива a не изменяются
    public static int binaryLeftRecursiveSearch(int x, int[] a) {
        //l >= -1 && l < r && r <= a.length && a[r] <= x < a[l]
        return binaryLeftRecursiveSearch(-1, a.length, x, a);
    }

    // Let be a[-1] == +inf && a[a.length] == -inf
    // PRE: (for i = l..r - 1: a[i] >= a[i + 1]) && (-1 <= l < r <= a.length) && a[r] <= x < a[l]
    // POST: a[R] <= x && a[R - 1] > x && элементы массива a не изменяются
    // r' - l' < r - l
    // binaryLeftRecursiveSearch(-1, 0, 1, [4, 3, 2, 1, 0]);
    public static int binaryLeftRecursiveSearch(int l, int r, int x, int[] a) {
        // -1 <= l < r <= a.length && a[r] <= x < a[l]
        if (r - l == 1) {
            // r - l == 1 && a[r] <= x && a[l] > x -> (a[r] <= x && a[r - 1] > x)
            // imagine l = r;
            // l' == l && r' == r -> l' && r > l -> l' > l && r' == r -> r' - l' < r - l
            return r;
        }
        // -1 <= l < r <= a.length && a[r] <= x < a[l] && r > l + 1
        int m = (r + l) / 2;
        // -1 <= l < r <= a.length && a[r] <= x < a[l] && r > l + 1
        // r > l + 1 -> l < (r + l) / 2 == m < r
        // -1 < m < a.length -> a[m] exist
        //
        if (a[m] <= x) {
            // a[m] <= x && a[r] <= x < a[l] && -1 <= l < m < r <= a.length -> a[r] <= a[m] <= x < a[l]
            // l' == l && r' == m -> r' < r && l' == l -> r' - l' < r - l
            // -1 <= l < m <= a.length && a[m] <= x < a[l] && r' - l' < r - l
            return binaryLeftRecursiveSearch(l, m, x, a);
        } else {
            // x < a[m] && a[r] <= x < a[l] && -1 <= l < m < r <= a.length -> a[r] <= x < a[m] <= a[l]
            // l' == m && r' == r -> r' == r && l' > l -> r' - l' < r - l
            // -1 <= m < r <= a.length && a[r] <= x < a[m] && r' - l' < r - l
            return binaryLeftRecursiveSearch(m, r, x, a);
        }
    }

    // Let be x = args[0]

    // PRE: args.length > 1, args - convert to int numbers
    // for i = 1..args.length - 1: (int)args[i] >= (int)args[i + 1]
    public static void main(String[] args) {
        // args[0].length > 1 -> args[0] exist
        int x = Integer.parseInt(args[0]);
        int[] a = new int[args.length - 1];
        // for i = 0 .. a.length - 1: a[i] = 0
        for (int i = 1; i < args.length; ++i) {
            a[i - 1] = Integer.parseInt(args[i]);
        }
        // x = args[0], a[0..args.length - 2] = args[1..args.length - 1]
        int left = binaryLeftRecursiveSearch(x, a);
        // (1) a[left] <= x && a[left - 1] > x
        int right = binaryRightIterativeSearch(x, a);
        // (2) a[right] < x && a[right - 1] >= x
        // (1) && (2) -> (right - left) - кол-во чисел равных x

        // l = left, r = right
        // l-5  l-4  l-3  l-2  l-1   l   l+1  l+2  l+3  l+4  l+5  l+6  l+7  l+8  l+9
        // r-10 r-9  r-8  r-7  r-6  r-5  r-4  r-3  r-2  r-1   r   r+1  r+2  r+3
        // >x   >x   >x   >x   >x   =x   =x   =x   =x   =x   <x   <x   <x   <x
        //
        // l-5  l-4  l-3  l-2  l-1   l   l+1  l+2  l+3
        // r-5  r-4  r-3  r-2  r-1   r   r+1  r+2  r+3
        // >x   >x   >x   >x   >x   <x   <x   <x   <x
        //
        System.out.println(left + " " + (right - left));
    }
    // Post: a[left] <= x && a[left - 1] > x && a[right] < x && a[right - 1] >= x &&
    // выводим место вставки x и кол-во чисел равных x
}
