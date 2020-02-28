package search;

public class BinarySearch {

    // Let be a[-1] == +inf && a[a.length] == -inf
    // PRE: for i = -1..a.length : a[i] >= a[i + 1]
    // POST: R = r: a[r] <= x && a[r - 1] > x && элементы массива a не изменяются
    public static int binaryIterativeSearch(int x, int[] a) {
        int l = -1, r = a.length;
        // l == -1 && r == a.length
        // INV: -1 <= l < r <= a.length && a[r] <= x < a[l] && r' - l' < r - l
        while (r - l > 1) {
            // -1 <= l + 1 < r <= a.length && a[r] <= x < a[l] && r' - l' < r - l
            int m = (l + r) / 2;
            // -1 <= l < r <= a.length -> l < (r + l) / 2 == m < r
            // -1 < m < a.length -> a[m] exist
            if (a[m] <= x) {
                // a[m] <= x && a[r] <= x < a[l] && -1 <= l < m < r <= a.length -> a[r] <= a[m] <= x < a[l]
                // l' == l && r' == m -> r' < r && l' == l -> r' - l' < r - l
                r = m;
                //-1 <= l < r <= a.length && a[r] <= x < a[l] && r' - l' < r - l
            } else {
                // x < a[m] && a[r] <= x < a[l] && -1 <= l < m < r <= a.length -> a[r] <= x < a[m] <= a[l]
                // l' == m && r' == r -> r' == r && l' > l -> r' - l' < r - l
                l = m;
                //-1 <= l < r <= a.length && a[r] <= x < a[l] && r' - l' < r - l
            }
        }
        // l + 1 >= r  && l < r ->  l + 1 == r
        //-1 <= l < r <= a.length && a[r] <= x < a[l] && r' - l' < r - l && l + 1 == r ->
        // a[r] <= x && a[r - 1] > x
        return r;
    }

    public static int binaryRecursiveSearch(int x, int[] a) {
        //l >= -1 && l < r && r <= a.length
        return binaryRecursiveSearch(-1, a.length, x, a);
    }

    // Let be a[-1] == +inf && a[a.length] == -inf
    // PRE: (for i = l..r - 1 : a[i] >= a[i + 1]) && (-1 <= l < r <= a.length)
    // POST: R = r: a[r] <= x && a[r - 1] > x && элементы массива a не изменяются
    // INV: -1 <= l < r <= a.length && a[r] <= x < a[l] && r' - l' < r - l
    public static int binaryRecursiveSearch(int l, int r, int x, int[] a) {
        if (r - l == 1) {
            // r - l == 1 && a[r] <= x && a[l] > x -> (a[r] <= x && a[r - 1] > x)
            return r;
        }
        // INV && r > l + 1
        int m = (r + l) / 2;
        // INV && r > l + 1 -> l < (r + l) / 2 == m < r
        // -1 < m < a.length -> a[m] exist
        if (a[m] <= x) {
            // a[m] <= x && a[r] <= x < a[l] && -1 <= l < m < r <= a.length -> a[r] <= a[m] <= x < a[l]
            // l' == l && r' == m -> r' < r && l' == l -> r' - l' < r - l
            // -1 <= l < m <= a.length && a[m] <= x < a[l] && r' - l' < r - l
            return binaryRecursiveSearch(l, m, x, a);
        } else {
            // x < a[m] && a[r] <= x < a[l] && -1 <= l < m < r <= a.length -> a[r] <= x < a[m] <= a[l]
            // l' == m && r' == r -> r' == r && l' > l -> r' - l' < r - l
            // -1 <= m < r <= a.length && a[r] <= x < a[m] && r' - l' < r - l
            return binaryRecursiveSearch(m, r, x, a);
        }
    }

    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int[] a = new int[args.length - 1];
        for (int i = 1; i < args.length; ++i) {
            a[i - 1] = Integer.parseInt(args[i]);
        }
        System.out.println(binaryIterativeSearch(x, a));
    }

}
