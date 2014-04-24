public class SnapperFastS {
    private static enum Snapper implements S<Snapper> {
        OFF {
            @Override
            public Snapper next() {
                return ON;
            }

            @Override
            public int prop() {
                return 0;
            }
        },
        ON {
            @Override
            public Snapper next() {
                return OFF;
            }

            @Override
            public int prop() {
                return 1;
            }
        };
    }

    public static String solve(int N, int K) {
        if (chk(sim(N, K)))
            return "ON";
        else
            return "OFF";
    }

    private static boolean chk(Snapper[] chain) {
        for (Snapper s : chain)
            if (s != Snapper.ON)
                return false;
        return true;
    }

    private static Snapper[] sim(int N, int K) {
        Snapper[] chain = new Snapper[N];
        for (int i = 0; i < N; i++)
            chain[i] = Snapper.OFF;
        Rules.sitr2(K, chain);
        return chain;
    }
}
