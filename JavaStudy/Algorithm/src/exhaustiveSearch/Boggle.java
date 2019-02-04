package exhaustiveSearch;

public class Boggle {

	String[][] board = {{"N", "N", "N", "N", "S"},
			{"N", "E", "E", "E", "N"},
			{"N", "E", "Y", "E", "N"},
			{"N", "E", "E", "E", "N"},
			{"N", "N", "N", "N", "N"}};
	
	final int[] dx = {-1, -1, -1, 1, 1, 1, 0, 0};
	final int[] dy = {-1, 0, 1, -1, 0, 1, -1, 1};
	
	
	boolean hasWord(int y, int x, String word) {
		// 기저 사례 1: 시작 위치가 범위 밖이면 무조건 실패
		if (!inRange(y,x)) return false;
		// 기저 사례 2: 첫 글자가 일치하지 않으면 실패
		if (!board[y][x].equals(word.substring(0,1))) return false;
		// 기저 사례 3: 단어 길이가 1이면 성공
		if(word.length()==1) return true;
		
		// 인접한 여덟 칸을 검사한다.
		for (int i = 0; i < 8; i++) {
			int nextY = y + dy[i];
			int nextX = x + dx[i];
			// 다음 칸이 범위 안에 있는지, 첫 글자는 일치하는지 확인할 필요가 없다.
			if (hasWord(nextY, nextX, word.substring(1))) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean inRange(int y, int x) {
		if(y<0 || y>= board.length) return false;
		if(x<0 || x>= board.length) return false;
		return true;
	}

	public static void main(String[] args) {
		Boggle boggle = new Boggle();
		System.out.println(boggle.hasWord(2, 2, "YES"));
	}

}
