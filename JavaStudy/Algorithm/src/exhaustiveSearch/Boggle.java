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
		// ���� ��� 1: ���� ��ġ�� ���� ���̸� ������ ����
		if (!inRange(y,x)) return false;
		// ���� ��� 2: ù ���ڰ� ��ġ���� ������ ����
		if (!board[y][x].equals(word.substring(0,1))) return false;
		// ���� ��� 3: �ܾ� ���̰� 1�̸� ����
		if(word.length()==1) return true;
		
		// ������ ���� ĭ�� �˻��Ѵ�.
		for (int i = 0; i < 8; i++) {
			int nextY = y + dy[i];
			int nextX = x + dx[i];
			// ���� ĭ�� ���� �ȿ� �ִ���, ù ���ڴ� ��ġ�ϴ��� Ȯ���� �ʿ䰡 ����.
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
