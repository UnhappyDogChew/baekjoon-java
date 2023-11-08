package solution;

import java.io.*;
import java.util.*;

/**
 * <h1>제목</h1>
 * <ul>
 *     <li>분류: 구현</li>
 *     <li>난이도: 골드 3</li>
 *     <li>푼 날짜: 11/8/23</li>
 * </ul>
 * <h3>풀이</h3>
 * <p>
 *
 * </p>
 * <h3>메모</h3>
 * <p>
 *
 * </p>
 */
public class B11403 implements Solution {
    @Override
    public void solve() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        boolean[][] graph = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = tokenizer.nextToken().equals("1");
            }
        }
        boolean[][] result = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            traverse(graph, result, n, i, i);
        }

        printGraph(result, n);
    }

    private void traverse(boolean[][] graph, boolean[][] result, int n, int r, int t) {
        for (int i = 0; i < n; i++) {
            if (!graph[r][i]) continue;
            if (result[t][i]) continue;
            result[t][i] = true;
            traverse(graph, result, n, i, t);
        }
    }

    private void printGraph(boolean[][] graph, int n) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                writer.write(graph[i][j] ? '1' : '0');
                writer.write(' ');
            }
            writer.newLine();
        }

        writer.flush();
    }
}
