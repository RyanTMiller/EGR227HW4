import java.util.*;

/**
 * Write your comments here
 */
public class Anagrams {
    List<String> dictionary;
    Map<String, LetterInventory> map;
    private ArrayList<String> pruned;

    public Anagrams(List<String> dictionary) {
        this.dictionary = new ArrayList<>(dictionary);
        map = new HashMap<>();
        for (String word : dictionary) {
            map.put(word, new LetterInventory(word));
        }
    }

    public void print(String text, int max) {
        if (max < 0) {
            throw new IllegalArgumentException();
        }
        LetterInventory textInv = new LetterInventory(text);
        pruned = prune(textInv);
        Stack<String> s = new Stack<>();
        print(textInv, max, s);
    }

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
