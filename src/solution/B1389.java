package solution;

import java.io.*;
import java.util.*;

/**
 * <h1>케빈 베이컨의 6단계 법칙</h1>
 * <ul>
 *     <li>분류: 구현, 그래프 탐색</li>
 *     <li>난이도: 실버 1</li>
 *     <li>푼 날짜: 10/29/23</li>
 * </ul>
 * <h3>풀이</h3>
 * <p>
 *     너비우선 탐색을 하며 거리를 누적시킨다.
 * </p>
 * <h3>메모</h3>
 * <p>
 *
 * </p>
 */
public class B1389 implements Solution {
    final int NOT_VISITED = Integer.MIN_VALUE;
    @Override
    public void solve() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i+1);
        }
        for (int i = 0; i < m; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            nodes[from-1].addEdge(nodes[to-1]);
            nodes[to-1].addEdge(nodes[from-1]);
        }

        int min_bacon = Integer.MAX_VALUE;
        int result = 0;
        for (int i = 0; i < n; i++) {
            int[] visit = new int[n];
            Arrays.fill(visit, NOT_VISITED);
            Queue<Node> queue = new LinkedList<>();
            int bacon = 0;
            visit[i] = 0;
            queue.add(nodes[i]);

            while (!queue.isEmpty()) {
                Node currentNode = queue.remove();
                int dist = visit[currentNode.getNumber()-1];

                for (Node node : currentNode.getEdges()) {
                    if (visit[node.getNumber()-1] != NOT_VISITED) {
                        continue;
                    }

                    bacon += dist + 1;
                    visit[node.getNumber()-1] = dist + 1;
                    queue.add(node);
                }
            }

            if (min_bacon > bacon) {
                min_bacon = bacon;
                result = i + 1;
            }
        }

        System.out.println(result);
    }

    static class Node {
        final private int number;
        final private List<Node> edges;

        public Node(int number) {
            this.number = number;
            this.edges = new ArrayList<>();
        }

        public int getNumber() {
            return this.number;
        }

        public List<Node> getEdges() {
            return this.edges;
        }

        public void addEdge(Node other) {
            this.edges.add(other);
        }
    }
}
