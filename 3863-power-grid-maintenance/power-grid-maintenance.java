import java.util.*;

class Solution {
    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        int[] parent = new int[c + 1];
        for (int i = 1; i <= c; i++) parent[i] = i;

        for (int[] e : connections) {
            union(parent, e[0], e[1]);
        }

        Map<Integer, TreeSet<Integer>> map = new HashMap<>();
        for (int i = 1; i <= c; i++) {
            int p = find(parent, i);
            map.computeIfAbsent(p, k -> new TreeSet<>()).add(i);
        }

        boolean[] online = new boolean[c + 1];
        Arrays.fill(online, true);

        List<Integer> ans = new ArrayList<>();

        for (int[] q : queries) {
            int type = q[0], x = q[1];

            if (type == 1) {
                if (online[x]) {
                    ans.add(x);
                } else {
                    int p = find(parent, x);
                    TreeSet<Integer> set = map.get(p);

                    while (!set.isEmpty() && !online[set.first()]) {
                        set.pollFirst();
                    }

                    ans.add(set.isEmpty() ? -1 : set.first());
                }
            } else {
                if (online[x]) {
                    online[x] = false;
                    int p = find(parent, x);
                    map.get(p).remove(x);
                }
            }
        }

        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) res[i] = ans.get(i);
        return res;
    }

    private int find(int[] parent, int x) {
        if (parent[x] != x) parent[x] = find(parent, parent[x]);
        return parent[x];
    }

    private void union(int[] parent, int a, int b) {
        int pa = find(parent, a);
        int pb = find(parent, b);
        if (pa != pb) parent[pa] = pb;
    }
}