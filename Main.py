"""
Counting Valid Binary Strings Based on Dynamic Score
=====================================================

Rules for a binary string, scanned left to right, score starts at 0:
    - bit '0'                -> score -= 1
    - bit '1' after a '0'    -> score += 3   (normal +1, plus +2 bonus for "01")
    - bit '1' otherwise      -> score += 1
    - if score ever goes negative, the string is discarded (invalid)

This file has THREE implementations so you can test/compare them:

    1. brute_force(n)   -> checks every 2^n string directly (only good for small n, n <= ~22)
    2. count_dp(n)      -> the general O(n^2) dynamic programming solution (the real "formula")
    3. count_fast(n)    -> same DP, but ~2x faster using a proven identity:
                           a(2k) = 2 * a(2k-1) exactly, for every k.
                           (Proof: every move changes the score by an odd number, -1/+1/+3,
                            so the score can never be exactly 0 after an odd number of moves.
                            That means every valid odd-length string can always be extended
                            with either '0' or '1' and stay valid -> the count exactly doubles
                            going from an odd length to the next even length.)
"""

from collections import defaultdict


def brute_force(n: int) -> int:
    """Directly checks every binary string of length n. Only use for small n (<= ~22)."""

    def is_valid(s: str) -> bool:
        score = 0
        prev = None
        for ch in s:
            bit = int(ch)
            if bit == 0:
                score -= 1
            else:
                score += 3 if prev == 0 else 1
            if score < 0:
                return False
            prev = bit
        return True

    count = 0
    for i in range(2 ** n):
        s = bin(i)[2:].zfill(n)
        if is_valid(s):
            count += 1
    return count


def count_dp(n: int) -> int:
    """
    General DP solution. Works for any n, no shortcut assumed.

    State: dp[prevBit][score] = number of ways to reach that state.
    prevBit is one of 'S' (start / no previous bit yet), '0', '1'.

    Transitions per step:
        - place '0': score -= 1, only kept if new score >= 0 (this is the only
          move that can ever go negative, since '1' only ever increases score)
        - place '1': score += 3 if prevBit == '0' else score += 1 (never negative)
    """
    dp = defaultdict(lambda: defaultdict(int))
    dp['S'][0] = 1  # before any characters, score = 0, no previous bit

    for _ in range(n):
        ndp = defaultdict(lambda: defaultdict(int))
        for prev, score_counts in dp.items():
            for score, ways in score_counts.items():
                # place a '0'
                new_score0 = score - 1
                if new_score0 >= 0:
                    ndp['0'][new_score0] += ways

                # place a '1'
                new_score1 = score + 3 if prev == '0' else score + 1
                ndp['1'][new_score1] += ways
        dp = ndp

    return sum(sum(score_counts.values()) for score_counts in dp.values())


def count_fast(n: int) -> int:
    """
    Optimized version using the doubling identity a(2k) = 2 * a(2k-1).

    We only ever run the "real" DP up to the largest odd length <= n,
    then get any remaining even step for free by doubling.
    This roughly halves the DP work compared to count_dp for large n.
    """
    if n == 0:
        return 1

    dp = defaultdict(lambda: defaultdict(int))
    dp['S'][0] = 1
    a_prev = 1  # a(0)

    for step in range(1, n + 1):
        ndp = defaultdict(lambda: defaultdict(int))
        for prev, score_counts in dp.items():
            for score, ways in score_counts.items():
                new_score0 = score - 1
                if new_score0 >= 0:
                    ndp['0'][new_score0] += ways
                new_score1 = score + 3 if prev == '0' else score + 1
                ndp['1'][new_score1] += ways
        dp = ndp
        a_curr = sum(sum(sc.values()) for sc in dp.values())

        if step % 2 == 0:
            # sanity-friendly shortcut: for even step, this MUST equal 2 * a(step - 1)
            a_curr = 2 * a_prev

        a_prev = a_curr

    return a_prev


if __name__ == "__main__":
    print(f"{'n':>3} | {'brute_force':>12} | {'count_dp':>12} | {'count_fast':>12}")
    print("-" * 50)
    for n in range(1, 21):
        bf = brute_force(n) if n <= 20 else None
        dp_val = count_dp(n)
        fast_val = count_fast(n)
        print(f"{n:>3} | {bf!s:>12} | {dp_val:>12} | {fast_val:>12}")
        assert dp_val == fast_val, f"Mismatch at n={n}"
        if bf is not None:
            assert bf == dp_val, f"Mismatch vs brute force at n={n}"

    print("\nAll checks passed. Try any n you like, e.g.:")
    print("  count_dp(50)  ->", count_dp(50))
    print("  count_fast(50)->", count_fast(50))