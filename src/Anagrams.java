import java.util.*;

/**
 * Write your comments here
 */
public class Anagrams {
    List<String> dictionary;
    Map<String, LetterInventory> map;
    private ArrayList<String> pruned;

    /**
     * This constructor initializes a new Anagrams object that will use dictionary
     * @param dictionary is the given List that is initialized into the Anagram object
     */
    public Anagrams(List<String> dictionary) {
        this.dictionary = new ArrayList<>(dictionary);
        map = new HashMap<>();
        for (String word : dictionary) {
            map.put(word, new LetterInventory(word));
        }
    }

    /**
     * prints out all the different anagrams found by using the object we created
     * LetterInventory with text.
     * @param text is the String that is being used for creating the anagrams
     * @param max is the max amount of anagrams that can be created
     */
    public void print(String text, int max) {
        if (max < 0) {
            throw new IllegalArgumentException();
        }
        LetterInventory textInv = new LetterInventory(text);
        pruned = prune(textInv);
        Stack<String> s = new Stack<>();
        print(textInv, max, s);
    }

    /**
     * a helper method to print each anagram found, filters out the anagrams that dont
     * work with the helper method of prune
     * @param textInv is the LetterInventory of the String created
     * @param max is the max number of Anagrams allowed to be created
     * @param s is the Stack used to help with pruning and printing the correct anagrams
     */
    private void print(LetterInventory textInv, int max, Stack<String> s) {
        if (textInv.size() == 0) {
            System.out.println(s);
        } else if (s.size() == max && s.size() != 0) {
            return;
        } else {
            for (String word : pruned) {
                LetterInventory temp = textInv.subtract(map.get(word));
                if (temp != null) {
                    s.push(word);
                    print(temp, max, s);
                    s.pop();
                }
            }
        }
    }

    /**
     * filters out the useless anagrams
     * @param textInv the LetterInventory of the word for the anagram
     * @return the ArrayList of the allowed anagrams
     */
    private ArrayList<String> prune(LetterInventory textInv) {
        ArrayList<String> temp = new ArrayList<>(dictionary);
        for (String word : dictionary) {
            if (textInv.subtract(map.get(word)) == null) {
                temp.remove(word);
            }
        }
        return temp;
    }

}
