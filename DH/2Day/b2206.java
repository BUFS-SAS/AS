package Test;

import java.io.*;
import java.util.*;

public class b2206 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());

        // 시작지점과 도착지점이 같을 경우!
        if(row-1 == 0 && row-1 == 0){
            System.out.print(1);
            System.exit(0);
        }

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        char[][] miro = new char[row][col];
        int[][] dist = new int[row][col];
        boolean[][][] isUsed = new boolean[2][row][col];
        Queue<int[]> bfs = new LinkedList<>();

        for (int i = 0; i < row; i++) {
            String s = bf.readLine();
            for (int j = 0; j < col; j++) {
                miro[i][j] = s.charAt(j);
            }
        }

        // 시작점 세팅 (x좌표, y좌표, crash 여부)
        bfs.offer(new int[]{0, 0, 0});

        while (!bfs.isEmpty()) {
            int[] cur = bfs.poll();  // 현재 위치 뽑기

            // 상,하,좌,우 탐색
            for(int i=0; i<4; i++){
                int nX = cur[0] + dx[i];
                int nY = cur[1] + dy[i];

                if (nX < 0 || nX >= row || nY < 0 || nY >= col) {
                    continue;
                }
                if (miro[nX][nY] == '1') {
                    if(cur[2] == 0 && !isUsed[1][nX][nY]){
                        isUsed[cur[2]][nX][nY] = true;
                        dist[nX][nY] = dist[cur[0]][cur[1]] + 1;
                        bfs.offer(new int[]{nX, nY, 1});
                    }
                }
                else{
                    if(!isUsed[cur[2]][nX][nY]){
                        isUsed[cur[2]][nX][nY] = true;
                        dist[nX][nY] = dist[cur[0]][cur[1]] + 1;
                        bfs.offer(new int[]{nX, nY, cur[2]});
                    }
                }
                if(nX == row-1 && nY == col-1){
                    System.out.print(dist[nX][nY] + 1);
                    System.exit(0);
                }
            }
        }
        System.out.print(-1);
    }
}
/* 2차
static class myPos{
        int _row;
        int _col;
        int _distance;
        int _crashed;
        public myPos(int row,int col,int distance,int crashed){
            this._row = row;
            this._col = col;
            this._distance = distance;
            this._crashed = crashed;
        }
    }

    public static void main(String[] args)throws IOException {
        int row;
        int col;
        boolean passed = false;
        int distance = 0;
        int[][] map;
        boolean[][][] isUsed;
        int[][] dist;
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        map = new int[row][col];
        isUsed = new boolean[2][row][col];
        dist = new int[row][col];
        Queue<myPos> bfs = new LinkedList<>();
        bfs.add(new myPos(0,0,1, 0));
        isUsed[0][0][0] = true;

        for(int i = 0; i < row; i++) {
            String buffer = br.readLine();
            String[] bufferArr = buffer.split("");
            for(int j = 0; j < col; j++) {
                map[i][j] = Integer.parseInt(bufferArr[j]);
            }
        }

        while (!bfs.isEmpty()){
            myPos now = bfs.poll();
            //마지막 검증
            if(now._row == row -1 && now._col == col-1){
                System.out.println(now._distance);
                return;
            }
            for (int d = 0; d < dx.length; d++){
                int nextR = now._row + dx[d];
                int nextC = now._col + dy[d];
                if(nextR<0 || nextR>=row || nextC<0 || nextC>= col) continue;

                int nextDistance = now._distance++;

                if(map[nextR][nextC] == 1){
                    if(now._crashed == 0 && !isUsed[1][nextR][nextC]){
                        isUsed[now._crashed][nextR][nextC] = true;
                        dist[nextR][nextC] = dist[now._row][now._col] + 1;
                        bfs.add(new myPos(nextR,nextC,nextDistance,1));
                    }
                } else {
                    if(!isUsed[now._crashed][nextR][nextC]) {
                        isUsed[now._crashed][nextR][nextC] = true;
                        dist[nextR][nextC] = dist[now._row][now._col] + 1;
                        bfs.add(new myPos(nextR, nextC, nextDistance, now._crashed));
                    }
                }
            }
        }
        System.out.println(-1);
    }*/
/*if(map[nextR][nextC] == 0 && !isUsed[now._crashed][nextR][nextC]){
                    bfs.add(new myPos(nextR,nextC,nextDistance,0));
                    isUsed[now._crashed][nextR][nextC] = true;
                    //
                    map[nextR][nextC] = now._distance;
                    for(int k = 0; k < row; k++){
                        for(int l = 0; l < col; l++){
                            System.out.print(map[k][l]+ "  ");
                        }
                        System.out.println("");
                    }
                    System.out.println("++++++++++++++++++++");
                } else if (map[nextR][nextC] == 1 && passed == false) {
                    bfs.add(new myPos(nextR,nextC,nextDistance));
                    isUsed[nextR][nextC] = true;
                    passed = true;
                    //
                    map[nextR][nextC] = now._distance;
                    for(int k = 0; k < row; k++){
                        for(int l = 0; l < col; l++){
                            System.out.print(map[k][l]+ "  ");
                        }
                        System.out.println("");
                    }
                    System.out.println("++++++++++++++++++++");
                }*/
/*1차
public static class Pos {
    int pos_x;
    int pos_y;

    Pos(){
        pos_x= pos_y = 0;
    }

    Pos(int x, int y) {
        pos_x = x;
        pos_y = y;
    }
}
    public static void main(String[] args) throws IOException{
        int row;
        int col;
        int nextX;
        int nextY;
        int overX;
        int overY;
        boolean passable = true;
        int distance = 0;
        b2206.Pos tmpPos = null;
        b2206.Pos startPos = new b2206.Pos(0,0);
        int[][] map;
        boolean[][] isUsed;
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        Queue<b2206.Pos> bfs = new LinkedList<b2206.Pos>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        map = new int[row][col];
        isUsed = new boolean[row][col];

        for(int i = 0; i < row; i++) {
            String buffer = br.readLine();
            String[] bufferArr = buffer.split("");
            for(int j = 0; j < col; j++) {
                map[i][j] = Integer.parseInt(bufferArr[j]);
            }
        }
        if(map[0][0] == 1 || map[row-1][col-1] ==1){
            System.out.println(-1);
            return;
        }

        bfs.add(startPos);
        distance++;
        isUsed[0][0] = true;

        while (!bfs.isEmpty()){
            tmpPos = bfs.poll();
            for(int i = 0; i < dx.length; i++) {
                nextX = tmpPos.pos_x + dx[i];
                nextY = tmpPos.pos_y + dy[i];
                if (-1 < nextX && nextX < row && -1 < nextY && nextY < col) {
                    if (map[nextX][nextY] == 0 && !isUsed[nextX][nextY]) {
                        tmpPos.pos_x = nextX;
                        tmpPos.pos_y = nextY;
                        bfs.add(tmpPos);
                        distance++;
                        isUsed[nextX][nextY] = true;
                        //확인용
                        map[nextX][nextY] = 2;
                        for(int k = 0; k < row; k++){
                            for(int l = 0; l < col; l++){
                                System.out.print(map[k][l]);
                            }
                            System.out.println("");
                        }
                        System.out.println("++++++++++++++++++++");
                    }
                    if(map[nextX][nextY] == 1 && !isUsed[nextX][nextY] && passable){
                        for(int j =0; j < dx.length; j++){
                            overX = nextX + dx[j];
                            overY = nextY + dy[i];
                            if (-1 < overX && overX < row && -1 < overY && overY < col){
                                if(map[overX][overY] == 0 && !isUsed[overX][overY]){
                                    tmpPos.pos_x = nextX;
                                    tmpPos.pos_y = nextY;
                                    bfs.add(tmpPos);
                                    distance++;
                                    isUsed[nextX][nextY] = true;
                                    map[nextX][nextY] = 2;
                                    passable = false;
                                }
                            }
                        }
                        for(int k = 0; k < row; k++){
                            for(int l = 0; l < col; l++){
                                System.out.print(map[k][l]);
                            }
                            System.out.println("");
                        }
                        System.out.println("++++++++++++++++++++");
                    }
                }
            }
        }

        if(!isUsed[row - 1][col - 1]){
            distance = -1;
        }
        System.out.println(distance);
//벽뚫고 이동
    }*/
