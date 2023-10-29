package solution;

import java.io.*;
import java.util.*;

/**
 * <h1>쉬운 최단거리</h1>
 * <ul>
 *     <li>분류: 완전탐색, 구현</li>
 *     <li>난이도: 실버 1</li>
 * </ul>
 * <h3>풀이</h3>
 * <p>
 *     도착점에서 시작하여 모든 위치까지의 최단거리를 구하는 문제
 * </p>
 * <h3>메모</h3>
 * <p>
 *
 * </p>
 */
public class B14940 implements Solution {
    static final int REACHABLE = Integer.MIN_VALUE;
    static final int START = Integer.MIN_VALUE + 1;
    static final int WALL = Integer.MIN_VALUE + 2;

    public void solve() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        int[][] map = new int[n][m];

        initializeMap(n, m, map, reader);

        traverseMap(n, m, map);

        checkUnvisited(n, m, map);

        printMap(n, m, map);
    }

    private static void checkUnvisited(int n, int m, int[][] map) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                switch (map[i][j]) {
                    case REACHABLE: map[i][j] = -1; break;
                    case WALL: map[i][j] = 0; break;
                    case START: map[i][j] = 0; break;
                }
            }
        }
    }

    private static void traverseMap(int n, int m, int[][] map) {
        Queue<Node> queue = new LinkedList<>();
        Node firstNode = new Node();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == START) {
                    firstNode.x = j;
                    firstNode.y = i;
                }
            }
        }
        // visit first node
        map[firstNode.y][firstNode.x] = 0;
        queue.add(firstNode);

        final int[] dx = new int[] {1, 0, -1, 0};
        final int[] dy = new int[] {0, 1, 0, -1};

        while (!queue.isEmpty()) {
            Node currentNode = queue.remove();
            for (int i = 0; i < 4; i++) {
                try {
                    int nextX = currentNode.x + dx[i];
                    int nextY = currentNode.y + dy[i];
                    int nextDistance = currentNode.distance + 1;
                    int nextValue = map[nextY][nextX];
                    if (nextValue != REACHABLE) continue;

                    map[nextY][nextX] = nextDistance;
                    queue.add(new Node(nextX, nextY, nextDistance));
                } catch (IndexOutOfBoundsException ignored) {}
            }
        }
    }

    private static class Node {
        public int x;
        public int y;
        public int distance;

        public Node() {
            x = 0;
            y = 0;
            distance = 0;
        }

        public Node(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    private static void printMap(int n, int m, int[][] map) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                writer.write(String.valueOf(map[i][j]));
                writer.write(" ");
            }
            writer.newLine();
        }
        writer.flush();
    }

    private static void initializeMap(int n, int m, int[][] map, BufferedReader reader) throws IOException {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                switch (reader.read()) {
                    case '0': map[i][j] = WALL; break;
                    case '1': map[i][j] = REACHABLE; break;
                    case '2': map[i][j] = START; break;
                }
                reader.skip(1);
            }
        }
    }
}
