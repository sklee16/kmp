public class KMP {
    private String pat;
    private int[][] dfa;

    public KMP(String pat) {
        this.pat = pat;
        int M = pat.length();
        int R = 256;
        dfa = new int[R][M];

        dfa[pat.charAt(0)][0] = 1;

        for (int X = 0, j  = 1; j < M; j++) {
            for (int c = 0; c < R; c++) {
                dfa[c][j] = dfa[c][X];
            }
            dfa[pat.charAt(j)][j] = j + 1;
            X = dfa[pat.charAt(j)][X];
        }

//        for (int i = 0; i < dfa.length; i++) {
//            for (int j = 0; j < dfa[i].length; j++) {
        System.out.println("M: " + M);
        for (int j = 0; j < M; j++) {
            for (int x = 0; x < M; x++) {
                System.out.println("At j: " + j + " x: " + x + ": " + dfa[pat.charAt(j)][x]);
            }
        }
    }

    public int search(String txt) {
        int i, j, N = txt.length();
        int M = pat.length();

        for (i = 0, j = 0; i < N && j < M; i++) {
            j = dfa[txt.charAt(i)][j];
        }
        if (j == M) {
            return i - M;
        } else {
            return N;
        }
    }

    public static void main(String[] args) {
        String pat = args[0];
        String txt = args[1];

        KMP kmp = new KMP(pat);
        System.out.println("text: " + txt);
        int offset = kmp.search(txt);
        System.out.println("pattern: " );
        for (int i = 0; i < offset; i ++) {
            System.out.println(" ");
        }
        System.out.println(pat);

    }

}
