package solution;

import java.io.*;
import java.util.*;

/**
 * <h1>비밀번호 찾기</h1>
 * <ul>
 *     <li>분류: 입출력, 해시맵</li>
 *     <li>난이도: 실버 4</li>
 *     <li>푼 날짜: 11/3/23</li>
 * </ul>
 * <h3>풀이</h3>
 * <p>
 *      버퍼드 리더, 라이터로 입출력 시간을 단축하고 해시맵을 활용하는 문제.
 * </p>
 * <h3>메모</h3>
 * <p>
 *
 * </p>
 */
public class B17219 implements Solution {
    @Override
    public void solve() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, String> sites = new HashMap<>();
        String[] split = reader.readLine().split(" ");
        int n = Integer.parseInt(split[0]), m = Integer.parseInt(split[1]);
        for (int i = 0; i < n; i++) {
            split = reader.readLine().split(" ");
            sites.put(split[0], split[1]);
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < m; i++) {
            String site = reader.readLine();
            writer.write(sites.get(site) + "\n");
        }

        writer.flush();
    }
}
