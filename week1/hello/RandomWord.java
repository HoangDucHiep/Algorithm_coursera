/* *****************************************************************************
 *  Name:              Hoang Hiep
 *  Last modified:     January 09, 2024
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {

    /**
     * Reads a sequence of words from standard input and prints one of those words uniformly at
     * random
     *
     * @param args sequence of words
     */
    public static void main(String[] args) {
        int index = 0;
        String ranWord = "";

        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            boolean accept = StdRandom.bernoulli(1 / (index + 1.0));
            if (accept) {
                ranWord = word;
            }
            index++;
        }
        StdOut.println(ranWord);
    }
}
