/**
 * By Ryan Miller
 * Help from Andrew Cantrell
 * This class keeps track of the letters of the alphabet with an array.
 */
public class LetterInventory {
    public int[] inv;
    private static final int numLetter = 26;
    private String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private int totalCount;

    /**
     * creates an empty Letter Inventory setting all counts to zero
     */
    public LetterInventory() {
        inv = new int[numLetter];
        for (int i = 0; i < numLetter; i++) {
            inv[i] = 0;
        }
    }

    /**
     *
     * @param data passes in the string of characters to put into the inventory
     * Creates a letter inventory while using the string variable data.
     */
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

    /**
     * throws an illegal argument exception if a non-alphabetic character is passed in
     * @param letter the character that the user wants to find how many of them exist
     * @return returns the number of times a specific letter appears in
     * the current inventory
     */
    public int get(char letter) {
        if (!Character.isAlphabetic(letter)) {
            throw new IllegalArgumentException();
        }
        return inv[alphabet.indexOf(Character.toLowerCase(letter))];
    }

    /**
     * Sets the count of the given letter to the given value. Case does not matter
     * @param letter the letter in the inventory that the user wants to change quantity
     * @param value the quantity the user wants the letter to change to
     */
    public void set(char letter, int value) {
        if (!Character.isAlphabetic(letter)) {
            throw new IllegalArgumentException();
        }
        int i = alphabet.indexOf(Character.toLowerCase(letter));
        totalCount -= inv[i];
        inv[i] = value;
        totalCount += inv[i];
    }

    /**
     *
     * @return returns the sum of all the counts in the current inventory
     */
    public int size() {
        return totalCount;
    }

    /**
     * Checks if the letter inventory is empty(all counts = 0)
     * @return returns true or false based on the totalCount value
     */
    public boolean isEmpty() {
        return totalCount == 0;
    }

    /**
     * uses a string builder to convert the number of each letter into a string of
     * everything combined
     * @return returns the created string
     */
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

    /**
     * Constructs and returns a new LetterInventory object that represents
     * the sum of the current LetterInventory and the other LetterInventory
     * @param other is the LetterInventory to be added
     * @return new letter inventory of added inventories.
     */
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

    /**
     * @param letter is the character the user wants to find the percentage of
     * @return double representing the percentage letter makes up of the LetterInventory
     */
    public double getLetterPercentage(char letter) {
        if (!Character.isAlphabetic(letter)) {
            throw new IllegalArgumentException("That character is not in the alphabet.");
        }
        int i = alphabet.indexOf(letter);
        return ((double) inv[i]) / totalCount;
    }
}