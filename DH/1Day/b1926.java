package Test;

import java.util.*;
import java.io.*;
public class b1926 {//그림
    public static class Pos {
        int pos_x;
        int pos_y;
        Pos(int x, int y) {
            pos_x = x;
            pos_y = y;
        }
    }
    public static void main(String[] args) throws Exception{
        int numOfPaint = 0;
        int bigPaint = 0;
        int paintSizeCount =1;
        int x;
        int y;
        int paint_R;
        int paint_C;
        Pos tmpPos;
        Queue<Pos> queue= new LinkedList<Pos>();
        int[] dx = {0,1,0,-1};
        int[] dy = {1,0,-1,0};
        String buffer;
        Pos posBuffer;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        buffer = br.readLine();
        StringTokenizer st = new StringTokenizer(buffer," ");
        paint_R= Integer.parseInt(st.nextToken());
        paint_C = Integer.parseInt(st.nextToken());

        int[][] matrix = new int[paint_R][paint_C];
        boolean[][] isUsed = new boolean[paint_R][paint_C];

        for(int i = 0; i < paint_R; i++){
            buffer = br.readLine();
            st = new StringTokenizer(buffer," ");
            for(int j = 0; j < paint_C; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
                if (matrix[i][j] == 0) {
                    isUsed[i][j] = true;
                }
            }
        }
        for(int i = 0; i < paint_R; i++){
            for(int j = 0; j < paint_C; j++){
                if(matrix[i][j] == 1 && !isUsed[i][j]){
                    posBuffer = new Pos(i,j);
                    queue.add(posBuffer);
                    numOfPaint++;
                    paintSizeCount =1;
                    isUsed[i][j] = true;
                    while(!queue.isEmpty()) {
                        tmpPos = queue.poll();
                        for (int k = 0; k < dx.length; k++) {
                            x = tmpPos.pos_x + dx[k];
                            y = tmpPos.pos_y + dy[k];
                            if (-1 < x && x < paint_R && -1 < y && y < paint_C) {
                                if (matrix[x][y] == 1 && !isUsed[x][y]){
                                    posBuffer = new Pos(x,y);
                                    queue.add(posBuffer);
                                    paintSizeCount++;
                                    isUsed[x][y] = true;
                                }
                            }
                        }
                    }
                    bigPaint = Math.max(bigPaint,paintSizeCount);
                }
            }
        }

        System.out.println(numOfPaint);
        System.out.println(bigPaint);
    }
}