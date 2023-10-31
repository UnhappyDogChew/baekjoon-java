package solution;

import java.io.*;
import java.util.*;

/**
 * <h1>제목</h1>
 * <ul>
 *     <li>분류: 구현</li>
 *     <li>난이도: 골드 3</li>
 *     <li>푼 날짜: 11/1/23</li>
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
public class B20529 implements Solution {
    @Override
    public void solve() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(reader.readLine());

        outer: for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(reader.readLine());
            int[] mbtis = new int[16];
            StringTokenizer st = new StringTokenizer(reader.readLine());

            for (int j = 0; j < n; j++) {
                mbtis[getMbtiIndex(st.nextToken())] += 1;
            }

            // has 3 or more
            for (int j = 0; j < 16; j++) {
                if (mbtis[j] >= 3) {
                    System.out.println(0);
                    continue outer;
                }
            }

            int minDist = Integer.MAX_VALUE;
            // has 2
            for (int j = 0; j < 16; j++) {
                if (mbtis[j] == 2) {
                    for (int k = 0; k < 16; k++) {
                        if (mbtis[k] == 0 || k == j) continue;
                        minDist = Math.min(minDist, getDistance(k, j) * 2);
                    }
                }
            }

            // all different
            for (int j = 0; j < 14; j++) {
                if (mbtis[j] == 0) continue;
                for (int k = j + 1; k < 15; k++) {
                    if (mbtis[k] == 0) continue;
                    for (int v = k + 1; v < 16; v++) {
                        if (mbtis[v] == 0) continue;
                        int dist = getDistance(j, k) + getDistance(k, v) + getDistance(v, j);
                        minDist = Math.min(minDist, dist);
                    }
                }
            }

            System.out.println(minDist);
        }

    }

    private int getMbtiIndex(String s) {
        int result = 0;
        if (s.charAt(0) == 'E') result += 8;
        if (s.charAt(1) == 'N') result += 4;
        if (s.charAt(2) == 'T') result += 2;
        if (s.charAt(3) == 'J') result += 1;
        return result;
    }

    private int getDistance(int from, int to) {
        int diff = from ^ to;
        int result = 0;
        for (int i = 0; i < 4; i++) {
            result += (diff >> i) & 1;
        }

        return result;
    }
}
