import java.io.*;
import java.util.*;

class Pair {
    public int x;
    public int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    private static final int len = 100;

    public static int[] dx = { 0, 0, 1, -1 };
    public static int[] dy = { 1, -1, 0, 0 };

    public static void main(String args[]) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        int[][] board = new int[len][len];

        int n = Integer.parseInt(br.readLine());
        int landIndex = 1;

        Queue<Pair> q = new LinkedList<>();

        for (int i = 0; i < n; ++i) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray(); // String array to Integer array.
        }

        for (int i = 0; i < n * n; ++i) {
            int x = i / n;
            int y = i % n;

            if (board[x][y] == 1) {
                landIndex++;

                q.add(new Pair(x, y));
                board[x][y] = landIndex;
            }

            while (!q.isEmpty()) {
                var cur = q.poll();

                for (int j = 0; j < 4; ++j) {
                    int nx = dx[j] + cur.x;
                    int ny = dy[j] + cur.y;

                    if (0 > nx || n <= nx || 0 > ny || n <= ny || board[nx][ny] != 1) continue;

                    q.add(new Pair(nx, ny));
                    board[nx][ny] = landIndex;
                }
            }
        }

        var dis = new int[len][len];
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < n * n; ++i) {
            int x = i / n;
            int y = i % n;

            if (board[x][y] != 0) q.add(new Pair(x, y));

            dis = new int[len][len];

            while (!q.isEmpty()) {
                var cur = q.poll();

                for (int j = 0; j < 4; ++j) {
                    int nx = dx[j] + cur.x;
                    int ny = dy[j] + cur.y;

                    if (0 > nx || n <= nx || 0 > ny || n <= ny) continue;

                    if (board[nx][ny] != 0 && board[nx][ny] != board[x][y]) { // 다른 섬에 도착 했을 경우.
                        min = Math.min(min, dis[cur.x][cur.y]);

                        while (!q.isEmpty()) q.poll();
                        break;
                    }

                    if (board[nx][ny] == 0 && dis[nx][ny] == 0) {
                        q.add(new Pair(nx, ny));

                        dis[nx][ny] = dis[cur.x][cur.y] + 1;
                    }
                }
            }
        }

        System.out.println(min);
    }
}
