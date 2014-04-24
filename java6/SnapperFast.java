public class SnapperFast {
    private static enum Snapper {
        OFF, ON
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
        int[] bits = new int[N];
        for (int i = 0; i < N; i++) {
            bits[i] = K % 2;
            K /= 2;
        }
        Snapper[] chain = new Snapper[N];
        for (int i = 0; i < N; i++)
            if (bits[i] == 0)
                chain[i] = Snapper.OFF;
            else
                chain[i] = Snapper.ON;
        return chain;
    }
}
