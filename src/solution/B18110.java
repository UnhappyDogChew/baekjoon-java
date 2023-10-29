package solution;

import java.io.*;
import java.util.*;

/**
 * <h1>solved.ac</h1>
 * <ul>
 *     <li>분류: 수학, 구현, 정렬</li>
 *     <li>난이도: 실버 4</li>
 * </ul>
 * <h3>풀이</h3>
 * <p>
 *     리스트를 정렬한 뒤 일정 영역의 값들만 가지고 계산하는 문제.
 * </p>
 * <h3>메모</h3>
 * <p>
 *
 * </p>
 */
public class B18110 implements Solution {
    @Override
    public void solve() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = readNext(reader);
        int[] opinions = new int [n];

        for (int i = 0; i < n; i++) {
            opinions[i] = readNext(reader);
        }

        Arrays.sort(opinions);
        int sum = 0;
        int x = (int) Math.round(n * 0.15);
        for (int i = x; i < n - x; i++) {
            sum += opinions[i];
        }
        System.out.println(Math.round((float)sum / (n - 2 * x)));
    }

    private static int readNext(BufferedReader reader) {
        try {
            return Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            return -1;
        }
    }
}
