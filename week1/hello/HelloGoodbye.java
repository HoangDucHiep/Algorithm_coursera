import edu.princeton.cs.algs4.StdOut;

/**
 * Write a description of HelloGoodbye here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class HelloGoodbye {
    public static void main(String[] args) {
        String name1 = args[0];
        String name2 = args[1];

        StdOut.println("Hello " + name1 + " and " + name2 + ".");
        StdOut.println("Goodbye " + name2 + " and " + name1 + ".");
    }
}

