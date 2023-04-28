import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class back_13023 {
    static ArrayList<Integer>[] A;
    static boolean[] visited;
    static boolean arrive;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        A = new ArrayList[N];
        visited = new boolean[N];
        arrive = false;
        for(int i = 0 ; i < N; i ++){
            A[i] = new ArrayList<Integer>();
        }

        for(int i = 0; i < S; i++){
            st= new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            A[n].add(s);
            A[s].add(n);
        }

        for(int i = 0; i < N; i ++){
            DFS(i,1);

            if(arrive)
                break;
        }
        if(arrive)
            System.out.println("1");
        else
            System.out.println("0");
    }

    public static void DFS(int now , int depth){
        if(depth == 5 || arrive){
            arrive = true;
            return;
        }
        visited[now] = true;
        for(int i : A[now]){
            if(!visited[i]){
                DFS(i,depth+1);
            }
        }
        visited[now] = false;
    }
}
