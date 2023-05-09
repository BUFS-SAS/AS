import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class back_1049 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] set = new int[M];  // 묶음 가격을 배열에 저장
        int[] one = new int[M];  // 낱개 가격을 배열에 저장

        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            set[i] = Integer.parseInt(st.nextToken());
            one[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(set); // 낮은 순 소팅
        Arrays.sort(one); // 낮은 순 소팅

        int minS = set[0]; // 가장 낮은 값 설정

        for(int s : one){
            if(minS > (s*6)){
                minS = s*6;
            }
        }

        int setPrice = ((N/6) + 1) * minS;
        int onePrice = (N/6) * minS + (N%6) * one[0];

        System.out.println(Math.min(setPrice, onePrice));


    }
}
