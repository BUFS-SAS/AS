import java.io.*;
import java.util.*;

class Pair {
    public int x;
    public int y;
    public boolean hasBrokenWall;

    public Pair(int x, int y, boolean hasBrokenWall) {
        this.x = x;
        this.y = y;

        this.hasBrokenWall = hasBrokenWall;
    }
}

public class Main {
    static final int len = 1000;

    public static void main(String args[]) throws IOException {
        int[] dx = { 0, 0, 1, -1 };
        int[] dy = { 1, -1, 0, 0 };

        var br = new BufferedReader(new InputStreamReader(System.in));

        var input = br.readLine().split(" ");

        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        Queue<Pair> q = new LinkedList<Pair>();

        var board = new char[len][len];
        var dis = new int[len][len][2]; // 0 - wall is not broken, 1 - wall is broken.

        for (int i = 0; i < n; ++i) {
            String s = br.readLine();

            for (int j = 0; j < m; ++j) {
                board[i][j] = s.charAt(j);
            }
        }

        q.add(new Pair(0, 0, false));
        dis[0][0][0] = 1;

        while (!q.isEmpty()) {
            var cur = q.poll();
            int index = (cur.hasBrokenWall ? 1 : 0);

            if (n - 1 == cur.x && m - 1 == cur.y) {
                System.out.println(dis[cur.x][cur.y][index]);

                return;
            }

            for (int i = 0; i < 4; ++i) {
                int nx = dx[i] + cur.x;
                int ny = dy[i] + cur.y;

                if (0 > nx || n <= nx || 0 > ny || m <= ny) continue;
                if (dis[nx][ny][index] != 0) continue;

                if (board[nx][ny] == '1' && !cur.hasBrokenWall) {
                    q.add(new Pair(nx, ny, true));

                    dis[nx][ny][1] = dis[cur.x][cur.y][0] + 1;
                }
                else if (board[nx][ny] == '0') {
                    q.add(new Pair(nx, ny, cur.hasBrokenWall));

                    dis[nx][ny][index] = dis[cur.x][cur.y][index] + 1;
                }
            }
        }

        System.out.println(-1);
    }
}
