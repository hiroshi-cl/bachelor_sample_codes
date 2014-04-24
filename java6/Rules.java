public class Rules {
    public static <T extends S<T>> T nitr(int N, T a) {
        for (int i = 0; i < N; i++)
            a = a.next();
        return a;
    }

    public static <T extends S<T>> int psum(int N, T a) {
        int ret = 0;
        for (int i = 0; i < N; i++)
            ret += nitr(i, a).prop();
        return ret;
    }

    public static <T extends S<T>> T nitr2(int N, T a) {
        int r = r(a);
        int p = p(a);
        if (N < r)
            return nitr(N, a);
        else
            return nitr((N - r) % p + r, a);
    }

    public static <T extends S<T>> int psum2(int N, T a) {
        int r = r(a);
        int p = p(a);
        T a2 = nitr(r, a);
        if (N < r)
            return psum(N, a);
        else
            return (N - r) / p * psum(p, a2)
                + psum((N - r) % p, a2) + psum(r, a);
    }

    private static <T extends S<T>> int r(T a) {
        T b = nitr(n(a), a);
        for (int i = 0;; i++)
            if (nitr(i, a).equals(b))
                return i;
    }

    private static <T extends S<T>> int p(T a) {
        return n(a) - r(a);
    }

    private static <T extends S<T>> int n(T a) {
        for (int m = 1;; m++) {
            T b = nitr(m, a);
            for (int i = 0; i < m; i++)
                if (nitr(i, a).equals(b))
                    return m;
        }
    }

    public static <T extends S<T>> void sitr2(int N, T[] x) {
        for (int i = 0; i < x.length; i++) {
            int p = psum2(N, x[i]);
            x[i] = nitr2(N, x[i]);
            N = p;
        }
    }
}
