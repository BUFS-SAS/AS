import java.util.*;

class Pair {
    public int x;
    public int count; // 연산 횟수

    public Pair(int x, int count) {
        this.x = x;
        this.count = count;
    }
}

class Solution {
    public int solution(int x, int y, int n) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(y, 0));

        while (!q.isEmpty()) {
            var cur = q.poll();

            if (cur.x == x) return cur.count;
            if (x > cur.x) continue;

            q.add(new Pair(cur.x - n, cur.count + 1));
            if (cur.x % 2 == 0) q.add(new Pair(cur.x / 2, cur.count + 1));
            if (cur.x % 3 == 0) q.add(new Pair(cur.x / 3, cur.count + 1));
        }

        return -1;
    }
}