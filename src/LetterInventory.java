/**
 * Write your comments here
 */
public class LetterInventory {
    public int[] inv;
    private static final int numLetter = 26;
    private String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private int totalCount;

    public LetterInventory() {
        inv = new int[numLetter];
        for (int i = 0; i < numLetter; i++) {
            inv[i] = 0;
        }
    }

    public LetterInventory(String data) {
        inv = new int[numLetter];
        for (int i = 0; i < numLetter; i++) {
            inv[i] = 0;
        }
        for (char c : data.toCharArray()) {
            for (int j = 0; j < numLetter; j++) {
                if (Character.toLowerCase(c) == alphabet.charAt(j)) {
                    inv[j]++;
                    totalCount++;
                }
            }
        }
    }

    public int get(char letter) {
        if (!Character.isAlphabetic(letter)) {
            throw new IllegalArgumentException();
        }
        return inv[alphabet.indexOf(Character.toLowerCase(letter))];
    }

    public void set(char letter, int value) {
        if (!Character.isAlphabetic(letter)) {
            throw new IllegalArgumentException();
        }
        int i = alphabet.indexOf(Character.toLowerCase(letter));
        totalCount -= inv[i];
        inv[i] = value;
        totalCount += inv[i];
    }

    public int size() {
        return totalCount;
    }

    public boolean isEmpty() {
        return totalCount == 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < numLetter; i++) {
            for (int j = 0; j < inv[i]; j++) {
                sb.append(alphabet.charAt(i));
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public LetterInventory add(LetterInventory other) {
        LetterInventory temp = new LetterInventory();
        for (int i = 0; i < numLetter; i++) {
            temp.inv[i] = other.inv[i] + inv[i];
        }
        temp.totalCount = totalCount + other.totalCount;
        return temp;
    }

    /**
     * @param other is the LetterInventory to be subtracted
     * @return new letter inventory of subtracted inventories.
     */
    public LetterInventory subtract(LetterInventory other) {
        LetterInventory temp = new LetterInventory();
        for (int i = 0; i < numLetter; i++) {
            temp.inv[i] = inv[i] - other.inv[i];
            if (temp.inv[i] < 0) {
                return null;
            }
        }
        temp.totalCount = totalCount - other.totalCount;
        return temp;
    }

    public double getLetterPercentage(char letter) {
        if (!Character.isAlphabetic(letter)) {
            throw new IllegalArgumentException("That character is not in the alphabet.");
        }
        int i = alphabet.indexOf(letter);
        return ((double) inv[i]) / totalCount;
    }
}