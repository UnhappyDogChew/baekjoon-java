package solution;

import java.io.*;
import java.util.*;

/**
 * <h1>제목</h1>
 * <ul>
 *     <li>분류: 구현</li>
 *     <li>난이도: 골드 3</li>
 *     <li>푼 날짜: 11/3/23</li>
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
public class B11727 implements Solution {

    @Override
    public void solve() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if (n == 1) {
            System.out.println(1);
            return;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 3;
        for (int i = 2; i < n; i++) {
            dp[i] = (dp[i-1] + dp[i-2] * 2) % 10007;
        }

        System.out.println(dp[n-1]);
    }
}
