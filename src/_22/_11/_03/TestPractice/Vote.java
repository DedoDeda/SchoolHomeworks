package _22._11._03.TestPractice;

public class Vote {

    public static void theWinner(Vote[] votes) {
        int[] results = new int[numSongs];
        for (Vote vote : votes) {
            // Invalid votes are not included.
            int songNum = vote.getFirst();
            if (isValidSongNum(songNum)) {
                results[songNum - 1] += firstPts;
            }
            songNum = vote.getSecond();
            if (isValidSongNum(songNum)) {
                results[songNum - 1] += secondPts;
            }
            songNum = vote.getThird();
            if (isValidSongNum(songNum)) {
                results[songNum - 1] += thirdPts;
            }
        }

        System.out.println(max(results));
    }

    private static boolean isValidSongNum(int num) {
        return num >= 1 && num <= numSongs;
    }
    private static int max(int[] arr) {
        int max = arr[0];

        for (int val : arr) {
            if (val > max) {
                max = val;
            }
        }

        return max;
    }

    public static final int numSongs = 40;
    public static final int firstPts = 7;
    public static final int secondPts = 5;
    public static final int thirdPts = 1;

    private int first; // 7 pts
    private int second; // 5 pts
    private int third; // 1 pts

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getThird() {
        return third;
    }

    public void setThird(int third) {
        this.third = third;
    }
}
