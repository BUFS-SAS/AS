import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Deque<Integer> dq = new LinkedList<>();

        for (int i = numbers.length - 1; i >= 0; --i) {
            while (!dq.isEmpty() && dq.peekFirst() <= numbers[i]) dq.pollFirst();

            answer[i] = (dq.isEmpty() ? -1 : dq.peekFirst());
            dq.push(numbers[i]);
        }

        return answer;
    }
}