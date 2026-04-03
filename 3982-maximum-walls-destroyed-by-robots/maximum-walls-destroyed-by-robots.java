import java.util.*;

class Solution {
    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;

        // Sort robots by position, keeping distance paired
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        Arrays.sort(idx, (a, b) -> robots[a] - robots[b]);

        int[] pos = new int[n];
        int[] dist = new int[n];
        for (int i = 0; i < n; i++) {
            pos[i] = robots[idx[i]];
            dist[i] = distance[idx[i]];
        }

        // Sort walls
        int[] w = walls.clone();
        Arrays.sort(w);
        int W = w.length;

        // Prefix sums on walls for range count queries
        // wallCount(l, r) = number of walls in [l, r]
        // Use binary search since walls are sorted

        // For each robot i, compute:
        //   leftReach[i]  = walls in [pos[i]-dist[i], pos[i]]
        //                   but blocked by robot i-1, so left bound = pos[i-1]+1 if exists
        //   rightReach[i] = walls in [pos[i], pos[i]+dist[i]]
        //                   but blocked by robot i+1, so right bound = pos[i+1]-1 if exists

        // Count walls in [l, r] using binary search
        // Returns count of walls w[j] with l <= w[j] <= r

        // DP:
        // dp[i] = max walls destroyed by robots 0..i, given robot i fires LEFT
        // For robot i firing right, the right segment walls go to the gap [pos[i]+1, pos[i+1]-1]
        // overlapping with left-firing of robot i+1.
        //
        // State: after processing robot i, we track whether robot i fired left or right,
        // because it affects what robot i+1 can claim in the gap.
        //
        // Let dpL[i] = max walls with robots 0..i, robot i fires LEFT
        //     dpR[i] = max walls with robots 0..i, robot i fires RIGHT
        //
        // Transition from i to i+1:
        //   wallsAtI_right  = walls robot i can hit firing right (segment: pos[i] to min(pos[i]+dist[i], pos[i+1]-1))
        //   wallsAt(i+1)_left = walls robot i+1 can hit firing left (segment: max(pos[i]+1, pos[i+1]-dist[i+1]) to pos[i+1])
        //   gapWalls = walls in gap (pos[i]+1 .. pos[i+1]-1) reachable by i-right ∩ (i+1)-left
        //              — these are contested; we pick one
        //   alsoAtI_right_exclusive = i fires right and reaches walls i+1 can't (beyond i+1's left range)
        //   etc.
        //
        // Simpler: define segments carefully.
        // Let gap_i = (pos[i], pos[i+1])  (exclusive endpoints, walls strictly between)
        //
        // Robot i right-range in gap_i: walls in (pos[i], min(pos[i]+dist[i], pos[i+1]-1)]
        // Robot i+1 left-range in gap_i: walls in [max(pos[i]+1, pos[i+1]-dist[i+1]), pos[i+1])
        //
        // These two ranges may overlap inside gap_i.
        // Let A = walls only reachable by i (right), B = contested, C = walls only reachable by i+1 (left)
        //
        // Also each robot i has "own walls" at its position: walls at pos[i] exactly (reachable by both directions)
        // Plus walls reachable firing left into gap_{i-1} not reachable by i-1 firing right, etc.
        //
        // Full approach:
        // For robot i define:
        //   selfWalls[i]    = walls at exactly pos[i]  (always destroyed regardless of direction)
        //   leftExtra[i]    = walls in [max(pos[i-1]+1, pos[i]-dist[i]), pos[i]-1]
        //                     that robot i-1 CANNOT reach firing right
        //                     (i.e., pos[i-1]+dist[i-1] < their position)
        //   rightExtra[i]   = walls in [pos[i]+1, min(pos[i+1]-1, pos[i]+dist[i])]
        //                     that robot i+1 CANNOT reach firing left
        //   contested_left[i]  = walls in gap_{i-1} reachable by BOTH i-1 firing right AND i firing left
        //   contested_right[i] = walls in gap_i    reachable by BOTH i firing right AND i+1 firing left
        //
        // dpL[i] = max walls 0..i, i fires left
        //        = selfWalls[i] + leftExtra[i] +
        //          max(dpL[i-1] + rightExtra[i-1],          // i-1 fired right, got its rightExtra
        //              dpR[i-1] + contested_left[i])         // wait, this isn't right either
        //
        // Let me redefine cleanly using "gap" perspective.

        // For each gap g = (i, i+1):
        //   Let R_i   = rightmost wall pos <= pos[i]+dist[i]  and < pos[i+1]  (i fires right into gap)
        //   Let L_i1  = leftmost  wall pos >= pos[i+1]-dist[i+1] and > pos[i] (i+1 fires left into gap)
        //   wallsOnlyI   = walls in (pos[i], min(R_i, L_i1-1)]   -- only i-right reaches
        //   wallsBoth    = walls in [max(L_i1, pos[i]+1), min(R_i, pos[i+1]-1)]  -- both reach
        //   wallsOnlyI1  = walls in [max(L_i1, R_i+1), pos[i+1])  -- only i+1-left reaches
        //   (some of these may be empty)
        //
        // selfWalls[i] = walls exactly at pos[i]

        // dp:
        // dpL[i] = best total if robot i fires LEFT
        // dpR[i] = best total if robot i fires RIGHT
        //
        // Base (i=0):
        //   dpL[0] = selfWalls[0] + walls in [pos[0]-dist[0], pos[0]-1]  (no left neighbor)
        //   dpR[0] = selfWalls[0]  (right contribution handled at gap transition)
        //           + 0 for now (right walls counted at gap)
        //   Actually let's count gap walls at the transition.
        //
        // Let's define:
        //   ownLeft[i]  = walls strictly in [pos[i]-dist[i], pos[i]-1]  clipped to (pos[i-1], pos[i])
        //   ownRight[i] = walls strictly in [pos[i]+1, pos[i]+dist[i]]  clipped to (pos[i], pos[i+1])
        //   selfW[i]    = walls at pos[i]
        //
        // For gap g between i and i+1:
        //   iRight  = ownRight[i]  (walls in gap reachable by i firing right)
        //   i1Left  = ownLeft[i+1] (walls in gap reachable by i+1 firing left)
        //   only_i  = iRight - i1Left  (count in iRight not in i1Left)
        //   only_i1 = i1Left - iRight
        //   both    = iRight ∩ i1Left
        //
        // dpL[i+1] = selfW[i+1] + only_i1 +
        //            max(dpR[i] + only_i + both,   // i fired right: got only_i + both from gap, i+1 fires left gets only_i1
        //                dpL[i] + only_i1 ... wait
        //
        // If i fires RIGHT: i gets (only_i + both) walls from gap; i+1 fires left gets only_i1
        // If i fires RIGHT and i+1 fires LEFT: gap walls = only_i + both + only_i1 = all gap walls
        // If i fires RIGHT and i+1 fires RIGHT: gap walls = only_i + both (i+1 doesn't get only_i1)
        // If i fires LEFT  and i+1 fires LEFT:  gap walls = only_i1 + both (i didn't fire right)
        //   wait: if i fires left, i contributes 0 from this gap; i+1 fires left gets only_i1+both
        // If i fires LEFT  and i+1 fires RIGHT: gap walls = 0 from i, 0 for i+1-left = 0? No:
        //   i+1 fires right so doesn't fire left → gap gets nothing from i+1-left either → gap walls=0
        //   But i fires left so 0 from this gap.
        //
        // So:
        // dpL[i+1] = selfW[i+1] + max(
        //              dpR[i] + only_i + both + only_i1,  // i RIGHT, i+1 LEFT → all gap walls
        //              dpL[i] + both + only_i1             // i LEFT,  i+1 LEFT → i+1-left ∪ both
        //            )  ... but only_i1 and both are from i+1-left perspective
        //   = selfW[i+1] + max(
        //              dpR[i] + |iRight ∪ i1Left|,
        //              dpL[i] + |i1Left|
        //            )
        //
        // dpR[i+1] = selfW[i+1] + max(
        //              dpR[i] + |iRight|,    // i RIGHT, i+1 RIGHT → i gets iRight, i+1 fires right (no left)
        //              dpL[i] + 0            // i LEFT,  i+1 RIGHT → gap gets nothing
        //            )
        //   = selfW[i+1] + max(dpR[i] + |iRight|, dpL[i])
        //
        // Final answer = max(dpL[n-1] + ownRight contributed by last firing right... 
        //   Hmm, ownRight[n-1] isn't counted yet for firing right with no right neighbor.
        //
        // Let's handle boundary robots:
        // Robot 0 has no left neighbor → ownLeft[0] = walls in [pos[0]-dist[0], pos[0]-1] (unbounded left)
        // Robot n-1 has no right neighbor → ownRight[n-1] = walls in [pos[n-1]+1, pos[n-1]+dist[n-1]] (unbounded right)
        //
        // At boundaries these are "free" (no contention).
        // dpL[0] = selfW[0] + ownLeft[0]
        // dpR[0] = selfW[0]  (ownRight[0] is contested with robot 1, handled at gap)
        //
        // Final:
        // answer = max(dpL[n-1], dpR[n-1] + ownRight[n-1])
        // where ownRight[n-1] = walls in (pos[n-1], pos[n-1]+dist[n-1]] (no right neighbor)

        // Count walls in [l, r] (inclusive)
        // w is sorted
        long[] prefix = new long[W + 1];
        for (int i = 0; i < W; i++) prefix[i + 1] = prefix[i] + 1;

        // wallsInRange(l, r): count of walls in [l, r]
        // = (first index > r) - (first index >= l)

        // dpL[i], dpR[i]
        long dpL, dpR;

        // selfW[0]
        long selfW0 = countWalls(w, pos[0], pos[0]);
        // ownLeft[0]: walls in [pos[0]-dist[0], pos[0]-1], no left neighbor
        long ownL0 = countWalls(w, pos[0] - dist[0], pos[0] - 1);

        dpL = selfW0 + ownL0;
        dpR = selfW0;

        for (int i = 0; i < n - 1; i++) {
            long selfW1 = countWalls(w, pos[i + 1], pos[i + 1]);

            // iRight: walls in (pos[i], min(pos[i]+dist[i], pos[i+1]-1)]
            int iRightHi = Math.min(pos[i] + dist[i], pos[i + 1] - 1);
            long iRight = countWalls(w, pos[i] + 1, iRightHi);

            // i1Left: walls in [max(pos[i]+1, pos[i+1]-dist[i+1]), pos[i+1]-1)
            int i1LeftLo = Math.max(pos[i] + 1, pos[i + 1] - dist[i + 1]);
            long i1Left = countWalls(w, i1LeftLo, pos[i + 1] - 1);

            // union = |iRight ∪ i1Left|
            // iRight covers (pos[i], iRightHi], i1Left covers [i1LeftLo, pos[i+1]-1)
            // both are subsets of (pos[i], pos[i+1]-1]
            // union = walls in [pos[i]+1, pos[i+1]-1] that are in either range
            //       = walls in [pos[i]+1, iRightHi] ∪ [i1LeftLo, pos[i+1]-1]
            long gapUnion;
            if (iRightHi < i1LeftLo) {
                // disjoint
                gapUnion = iRight + i1Left;
            } else {
                // overlapping: union = walls in [pos[i]+1, pos[i+1]-1] covered by either
                // = walls in [min(pos[i]+1, i1LeftLo), max(iRightHi, pos[i+1]-1)]
                // but both are already subsets of (pos[i], pos[i+1]), so:
                gapUnion = countWalls(w, Math.min(pos[i] + 1, i1LeftLo),
                                         Math.max(iRightHi, pos[i + 1] - 1));
            }

            // Also need ownLeft[i+1] for dpL transition (that's i1Left above, which covers gap walls)
            // But ownLeft[i+1] also includes pos[i+1] itself → selfW1 (already separate)

            long newDpL = selfW1 + Math.max(dpR + gapUnion, dpL + i1Left);
            long newDpR = selfW1 + Math.max(dpR + iRight, dpL);

            dpL = newDpL;
            dpR = newDpR;
        }

        // Last robot's right (no right neighbor): free walls
        long lastRight = countWalls(w, pos[n - 1] + 1, pos[n - 1] + dist[n - 1]);
        long ans = Math.max(dpL, dpR + lastRight);

        return (int) ans;
    }

    // Count walls in sorted array w that fall in [lo, hi]
    private long countWalls(int[] w, int lo, int hi) {
        if (lo > hi) return 0;
        int left  = lowerBound(w, lo);
        int right = upperBound(w, hi);
        return Math.max(0, right - left);
    }

    // First index with w[i] >= val
    private int lowerBound(int[] w, int val) {
        int lo = 0, hi = w.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (w[mid] < val) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    // First index with w[i] > val
    private int upperBound(int[] w, int val) {
        int lo = 0, hi = w.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (w[mid] <= val) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
}