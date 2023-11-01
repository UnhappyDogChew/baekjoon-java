package solution;

import java.io.*;
import java.util.*;

/**
 * <h1>Four Squares</h1>
 * <ul>
 *     <li>분류: 완전탐색</li>
 *     <li>난이도: 실버 3</li>
 *     <li>푼 날짜: 11/1/23</li>
 * </ul>
 * <h3>풀이</h3>
 * <p>
 *      재곱수를 노드로 하여 완전탐색하는 문제
 * </p>
 * <h3>메모</h3>
 * <p>
 *
 * </p>
 */
public class B17626 implements Solution {

    static int result = 4;
    @Override
    public void solve() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int max = (int)Math.sqrt(n);
        for (int i = max; i > 0; i--) {
            recursive(n, i, 1);
        }
        System.out.println(result);
    }

    private static void recursive(int n, int x, int d) {
        if (d == 4 || x * x > n) return;
        if (x * x == n) {
            result = Math.min(result, d);
            return;
        }
        for (int i = x; i > 0; i--) {
            recursive(n - x * x, i, d + 1);
        }
    }

}
