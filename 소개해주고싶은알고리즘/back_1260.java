import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class back_1260 {
    static ArrayList<Integer>[] A;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        A = new ArrayList[N+1];

        for(int i = 1; i<= N; i++){
            A[i] = new ArrayList<Integer>();
        }

        for(int i = 1; i <=S; i++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            A[n].add(s);
            A[s].add(n);
        }

        for(int i = 1 ; i <= N; i++){
            Collections.sort(A[i]);
        }
        visited = new boolean[N+1];
        DFS(T);
        System.out.println();
        visited = new boolean[N+1];
        BFS(T);
        System.out.println();



    }

    public static void DFS(int Node){
        System.out.print(Node + " ");
        visited[Node] = true;
        for(int i : A[Node]){
            if(!visited[i]){
                DFS(i);
            }
        }
    }

    public static void BFS(int Node){
        Queue<Integer> qu = new LinkedList<Integer>();
        qu.add(Node);
        visited[Node] = true;

        while(!qu.isEmpty()){
            int now_Node = qu.poll();
            System.out.print(now_Node+ " ");
            for(int i : A[now_Node]){
                if(!visited[i]){
                    visited[i] = true;
                    qu.add(i);
                }
            }

        }
    }
}
