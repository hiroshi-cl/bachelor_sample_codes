public class ThemePark {
    private static class Coaster implements S<Coaster> {
        final int k;
        final int[] g;

        private Coaster(int k, int[] g) {
            this.k = k;
            this.g = g;
        }

        @Override
        public Coaster next() {
            return new Coaster(k, rot(ride()));
        }

        @Override
        public int prop() {
            int ret = 0;
            int r = ride();
            for (int i = 0; i < r; i++)
                ret += g[i];
            return ret;
        }

        int ride() {
            for (int i = 0, s = 0; i < g.length; i++) {
                s += g[i];
                if (s > k)
                    return i;
            }
            return g.length;
        }

        int[] rot(int r) {
            int n = g.length;
            int[] ret = new int[n];
            for (int i = 0; i < n; i++)
                ret[i] = g[(i + r) % n];
            return ret;
        }

        @Override
        public boolean equals(Object obj) {
            Coaster c = (Coaster) obj;
            return k == c.k && java.util.Arrays.equals(g, c.g);
        }
    }

    public static int solve(int R, int k, int N, int[] g) {
        return Rules.psum2(R, new Coaster(k, g));
    }
}
