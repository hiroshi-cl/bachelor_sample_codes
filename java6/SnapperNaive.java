public class SnapperNaive {
    private static enum Snapper {
        OFF, ON
    }
    public static String solve(int N, int K) {
        if(chk(sim(N, K)))
            return "ON";
        else
            return "OFF";
    }
    private static boolean chk(Snapper[] chain) {
        for(Snapper s : chain)
            if(s != Snapper.ON)
                return false;
        return true;
    }
    private static Snapper[] sim(int N, int K) {
        Snapper[] chain = new Snapper[N];
        for(int i = 0; i < N; i++)
            chain[i] = Snapper.OFF;
        for(int i = 0; i < K; i++)
            for(int j = 0; j < N; j++)
                if(chain[j] == Snapper.ON) {
                    chain[j] = Snapper.OFF;
                }
                else {
                    chain[j] = Snapper.ON;
                    break;
                }
        return chain;
    }
}
