class Solution {
    public int[] solution(String[] park, String[] routes) {
        int H = park.length;
        int W = park[0].length();

        // 시작 위치 찾기
        int x = 0, y = 0;
        for (int i = 0; i < H; i++) {
            int idx = park[i].indexOf('S');
            if (idx != -1) {
                x = i;
                y = idx;
                break;
            }
        }

        for (String r : routes) {
            String[] parts = r.split(" ");
            char dir = parts[0].charAt(0);
            int dist = Integer.parseInt(parts[1]);

            int dx = 0, dy = 0;
            if (dir == 'N') dx = -1;
            else if (dir == 'S') dx = 1;
            else if (dir == 'W') dy = -1;
            else if (dir == 'E') dy = 1;

            int nx = x;
            int ny = y;
            boolean ok = true;

            // 한 칸씩 이동하며 경계/장애물 검사
            for (int step = 0; step < dist; step++) {
                nx += dx;
                ny += dy;

                if (nx < 0 || ny < 0 || nx >= H || ny >= W) {
                    ok = false;
                    break;
                }
                if (park[nx].charAt(ny) == 'X') {
                    ok = false;
                    break;
                }
            }

            // 문제 없으면 위치 갱신
            if (ok) {
                x = nx;
                y = ny;
            }
        }

        return new int[]{x, y};
    }
}
