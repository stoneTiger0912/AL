import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] drinkList;
    static List<Node>[] saleList;

    static class Node {
        int num;
        int price;

        Node(int num, int price) {
            this.num = num;
            this.price = price;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "num=" + num +
                    ", price=" + price +
                    '}';
        }
    }

    static int[] selected;

    static void perm(int idx, boolean[] visited) {

        if(idx==N) {
//            System.out.println(Arrays.toString(selected));

            cal(selected);

            return;
        }

        for(int i=0; i<N; i++) {
            if(visited[i]) continue;
            selected[idx] = i;
            visited[i] = true;
            perm(idx+1, visited);
            visited[i] = false;

        }

    }

    static int min = Integer.MAX_VALUE;

    static void cal(int[] selected) {
        int sum = 0;
        int[] arr = new int[N];
        System.arraycopy(drinkList, 0, arr, 0, N);
        for(int i=0; i<N; i++) {
            int num = selected[i];
            sum += arr[num];
            for (Node node : saleList[num]) {
                int n = node.num-1;
                int p = node.price;

                arr[n] = arr[n] - p <= 0 ? 1 : arr[n] - p;
            }

        }

        min = Math.min(min, sum);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        drinkList = new int[N];
        saleList = new List[N];
        selected = new int[N];
        for(int i=0; i<N;i++) {
            saleList[i] = new LinkedList<>();
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N;i++) {
            drinkList[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            for(int m=0; m<M; m++) {
                st = new StringTokenizer(br.readLine());
                int next = Integer.parseInt(st.nextToken());
                int salePrice = Integer.parseInt(st.nextToken());

                Node node = new Node(next, salePrice);
                saleList[i].add(node);
            }
        }

        perm(0, new boolean[N]);

        System.out.println(min);
//        for (int i : drinkList) {
//            System.out.print(i+" ");
//        }
//        System.out.println();
//        for (List<Node> nodes : saleList) {
//            System.out.println(nodes);
//        }

    }
}