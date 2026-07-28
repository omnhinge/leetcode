import java.util.*;

public class BinaryStringScore {

    // ============================================================
    // 1. BRUTE FORCE
    // ============================================================

    public static long bruteForce(int n) {
        long count = 0;

        // Only practical for small n
        long total = 1L << n;

        for (long i = 0; i < total; i++) {
            String s = Long.toBinaryString(i);

            // Add leading zeroes
            while (s.length() < n) {
                s = "0" + s;
            }

            if (isValid(s)) {
                count++;
            }
        }

        return count;
    }

    private static boolean isValid(String s) {
        int score = 0;
        char prev = 'S';

        for (char ch : s.toCharArray()) {

            if (ch == '0') {
                score -= 1;
            } else {
                // "01" gives +3
                if (prev == '0') {
                    score += 3;
                } else {
                    score += 1;
                }
            }

            // Discard immediately
            if (score < 0) {
                return false;
            }

            prev = ch;
        }

        return true;
    }


    // ============================================================
    // 2. DP SOLUTION - O(n^2)
    // ============================================================

    public static long countDP(int n) {

        /*
         * Instead of Python:
         *
         * dp[prevBit][score]
         *
         * we use:
         *
         * dp0[score] = ways where previous bit is 0
         * dp1[score] = ways where previous bit is 1
         *
         * At the beginning there is no previous bit.
         */

        if (n == 0) {
            return 1;
        }

        Map<Integer, Long> dp0 = new HashMap<>();
        Map<Integer, Long> dp1 = new HashMap<>();

        // First character:
        // '0' is invalid because score becomes -1.
        // '1' makes score = 1.

        dp1.put(1, 1L);

        for (int step = 1; step < n; step++) {

            Map<Integer, Long> next0 = new HashMap<>();
            Map<Integer, Long> next1 = new HashMap<>();


            // ------------------------------------
            // Previous bit = 0
            // ------------------------------------

            for (Map.Entry<Integer, Long> entry : dp0.entrySet()) {

                int score = entry.getKey();
                long ways = entry.getValue();

                // Add '0'
                // score - 1

                if (score - 1 >= 0) {
                    next0.merge(
                            score - 1,
                            ways,
                            Long::sum
                    );
                }

                // Add '1'
                // Previous bit was 0 -> +3

                next1.merge(
                        score + 3,
                        ways,
                        Long::sum
                );
            }


            // ------------------------------------
            // Previous bit = 1
            // ------------------------------------

            for (Map.Entry<Integer, Long> entry : dp1.entrySet()) {

                int score = entry.getKey();
                long ways = entry.getValue();

                // Add '0'

                if (score - 1 >= 0) {
                    next0.merge(
                            score - 1,
                            ways,
                            Long::sum
                    );
                }

                // Add '1'
                // Previous bit was 1 -> +1

                next1.merge(
                        score + 1,
                        ways,
                        Long::sum
                );
            }

            dp0 = next0;
            dp1 = next1;
        }


        long answer = 0;

        for (long ways : dp0.values()) {
            answer += ways;
        }

        for (long ways : dp1.values()) {
            answer += ways;
        }

        return answer;
    }


    // ============================================================
    // 3. FAST VERSION
    // ============================================================

    public static long countFast(int n) {

        if (n == 0) {
            return 1;
        }

        Map<Integer, Long> dp0 = new HashMap<>();
        Map<Integer, Long> dp1 = new HashMap<>();

        // Initial state:
        // empty string
        Map<Integer, Long> start = new HashMap<>();
        start.put(0, 1L);

        long previousCount = 1;

        for (int step = 1; step <= n; step++) {

            Map<Integer, Long> next0 = new HashMap<>();
            Map<Integer, Long> next1 = new HashMap<>();


            // First step - START state
            if (step == 1) {

                // Starting with 0:
                // score = -1 -> invalid

                // Starting with 1:
                // score = 1

                next1.put(1, 1L);

            } else {

                // Previous = 0
                for (Map.Entry<Integer, Long> entry : dp0.entrySet()) {

                    int score = entry.getKey();
                    long ways = entry.getValue();

                    // Place 0
                    if (score - 1 >= 0) {
                        next0.merge(
                                score - 1,
                                ways,
                                Long::sum
                        );
                    }

                    // Place 1 -> +3
                    next1.merge(
                            score + 3,
                            ways,
                            Long::sum
                    );
                }


                // Previous = 1
                for (Map.Entry<Integer, Long> entry : dp1.entrySet()) {

                    int score = entry.getKey();
                    long ways = entry.getValue();

                    // Place 0
                    if (score - 1 >= 0) {
                        next0.merge(
                                score - 1,
                                ways,
                                Long::sum
                        );
                    }

                    // Place 1 -> +1
                    next1.merge(
                            score + 1,
                            ways,
                            Long::sum
                    );
                }
            }

            dp0 = next0;
            dp1 = next1;


            // Count all valid strings
            long currentCount = 0;

            for (long ways : dp0.values()) {
                currentCount += ways;
            }

            for (long ways : dp1.values()) {
                currentCount += ways;
            }


            // Identity:
            //
            // a(2k) = 2 * a(2k - 1)
            //
            if (step % 2 == 0) {
                currentCount = 2 * previousCount;
            }

            previousCount = currentCount;
        }

        return previousCount;
    }


    // ============================================================
    // TEST
    // ============================================================

    public static void main(String[] args) {

        System.out.printf(
                "%3s | %12s | %12s | %12s%n",
                "n",
                "bruteForce",
                "countDP",
                "countFast"
        );

        System.out.println("-".repeat(50));

        for (int n = 1; n <= 20; n++) {

            long bf = bruteForce(n);
            long dp = countDP(n);
            long fast = countFast(n);

            System.out.printf(
                    "%3d | %12d | %12d | %12d%n",
                    n,
                    bf,
                    dp,
                    fast
            );

            if (bf != dp) {
                throw new RuntimeException(
                        "Brute force mismatch at n = " + n
                );
            }

            if (dp != fast) {
                throw new RuntimeException(
                        "Fast mismatch at n = " + n
                );
            }
        }

        System.out.println("\nAll checks passed!");

        System.out.println(
                "countDP(50)   = " + countDP(50)
        );

        System.out.println(
                "countFast(50) = " + countFast(50)
        );
    }
}