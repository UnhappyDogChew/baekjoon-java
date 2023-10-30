package solution;

import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

/**
 * <h1>헌내기는 친구가 필요해</h1>
 * <ul>
 *     <li>분류: 그래프 탐색</li>
 *     <li>난이도: 실버 2</li>
 *     <li>푼 날짜: 10/30/23</li>
 * </ul>
 * <h3>풀이</h3>
 * <p>
 *     재귀적으로 완전탐색하여 품.
 * </p>
 * <h3>메모</h3>
 * <p>
 *     재귀함수는 다음 구조를 가진다.
 *     <ol>
 *         <li>탐색종료조건</li>
 *         <li>현재 노드 처리</li>
 *         <li>다음 노드 탐색</li>
 *     </ol>
 * </p>
 */
public class B21736 implements Solution {

    @Override
    public void solve() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] nums = reader.readLine().split(" ");
        final int N = Integer.parseInt(nums[0]);
        final int M = Integer.parseInt(nums[1]);

        char[][] map = new char[N][M];
        int startX = 0;
        int startY = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = (char)reader.read();
                if (map[i][j] == 'I') {
                    startX = j;
                    startY = i;
                }
            }
            reader.skip(1);
        }
        int result = traverse(startX, startY, map);

        if (result == 0) {
            System.out.println("TT");
        } else {
            System.out.println(result);
        }
    }

    private static int traverse(int x, int y, char[][] map) {
        if (x < 0 || x >= map[0].length || y < 0 || y >= map.length || map[y][x] == 'X')
            return 0;

        int count = 0;
        if (map[y][x] == 'P') count++;
        map[y][x] = 'X';
        count += traverse(x + 1, y, map);
        count += traverse(x - 1, y, map);
        count += traverse(x, y + 1, map);
        count += traverse(x, y - 1, map);

        return count;
    }

    private static void printMap(int N, int M, char[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}
