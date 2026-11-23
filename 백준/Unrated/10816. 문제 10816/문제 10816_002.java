import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        Map<Integer, Integer> cards = new HashMap<>();

        for(int i = 0; i < N; i++) {
            int card = Integer.parseInt(st.nextToken());
            cards.put(card, cards.getOrDefault(card, 0) + 1);
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int[] answer = new int[M];

        for(int i = 0; i < M; i++) {
            int found = Integer.parseInt(st.nextToken());

            if(cards.containsKey(found)) {
                answer[i] = cards.get(found);
            } else {
                answer[i] = 0;
            }
        }

        for(int i = 0; i < M; i++) {
            System.out.print(answer[i] + " ");
        }
        System.out.println();
    }
}
