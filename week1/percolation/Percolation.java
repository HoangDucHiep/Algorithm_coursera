/* *****************************************************************************
 *  Name:              Hoang Hiep
 *  Last modified:     January 10 2024
 *  Grade: 94/100
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {

    private int n;  // n-by-n grid
    private boolean[] sites;    //(row - 1)*n + (col)
    private final int top;      // virtual sites for most-top-site, connect to all opened top sites
    private final int bottom;
    // virtual sites for most-bottom-site, connect to all opened bottom sites
    private int openedSites;    // number of opened sites
    private WeightedQuickUnionUF uf;    // union-...


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("size of grid must be larger than 0");

        this.n = n;
        sites = new boolean[n * n + 1];
        top = 0;
        bottom = n * n + 1;
        openedSites = 0;
        uf = new WeightedQuickUnionUF(n * n + 2); // a grid, top and bottom;
    }

    // check if the (row, col) is in grid
    private void checkSpot(int row, int col) {
        if (row < 1 || col < 1 || row > n || col > n)
            throw new IllegalArgumentException("Out of grid");
    }

    // convert 2d index to 1d index
    private int getIdx(int row, int col) {
        checkSpot(row, col);
        return (row - 1) * n + (col);
    }


    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        checkSpot(row, col);

        if (!isOpen(row, col)) {
            int currIndex = getIdx(row, col);
            sites[currIndex] = true;
            openedSites++;

            // union neighbor-sites

            // union with TOP and BOTTOM
            if (row == 1) {  // top-row of grid (not TOP)
                uf.union(top, currIndex);
            }
            if (row == n)
                uf.union(bottom, currIndex);

            // union with opened neighbor
            if (row - 1 >= 1 && isOpen(row - 1, col)) {
                assert (currIndex > n);
                uf.union(currIndex, currIndex - n);
            }

            if (row + 1 <= n && isOpen(row + 1, col)) {
                assert (currIndex + n < n * n);
                uf.union(currIndex, currIndex + n);
            }

            if (col - 1 >= 1 && isOpen(row, col - 1)) {
                uf.union(currIndex, currIndex - 1);
            }
            if (col + 1 <= n && isOpen(row, col + 1)) {
                uf.union(currIndex, currIndex + 1);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkSpot(row, col);
        return sites[getIdx(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        checkSpot(row, col);
        if (!isOpen(row, col)) {
            return false;
        }
        return uf.find(top) == uf.find(getIdx(row, col));
    }


    // returns the number of open sites
    public int numberOfOpenSites() {
        return openedSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(top) == uf.find(bottom);
    }

    // test client (optional)
    public static void main(
            String[] args) {    // take main from https://github.com/bpasieka/coursera-algorithms-part1/blob/master/week_1/Percolation.java (Thank u :>)
        Percolation percolation = new Percolation(2);

        StdOut.println("percolates = " + percolation.percolates());

        StdOut.println("isOpen(1, 2) = " + percolation.isOpen(1, 2));
        StdOut.println("isFull(1, 2) = " + percolation.isFull(1, 2));
        StdOut.println("open(1, 2)");
        percolation.open(1, 2);
        StdOut.println("isOpen(1, 2) = " + percolation.isOpen(1, 2));
        StdOut.println("isFull(1, 2) = " + percolation.isFull(1, 2));
        StdOut.println("numberOfOpenSites() = " + percolation.numberOfOpenSites());
        StdOut.println("percolates() = " + percolation.percolates());

        StdOut.println("isOpen(2, 1) = " + percolation.isOpen(2, 1));
        StdOut.println("isFull(2, 1) = " + percolation.isFull(2, 1));
        StdOut.println("open(2, 1)");
        percolation.open(2, 1);
        StdOut.println("isOpen(2, 1) = " + percolation.isOpen(2, 1));
        StdOut.println("isFull(2, 1) = " + percolation.isFull(2, 1));
        StdOut.println("numberOfOpenSites() = " + percolation.numberOfOpenSites());
        StdOut.println("percolates() = " + percolation.percolates());

        StdOut.println("isOpen(1, 1) = " + percolation.isOpen(1, 1));
        StdOut.println("isFull(1, 1) = " + percolation.isFull(1, 1));
        StdOut.println("open(1, 1)");
        percolation.open(1, 1);
        StdOut.println("isOpen(1, 1) = " + percolation.isOpen(1, 1));
        StdOut.println("isFull(1, 1) = " + percolation.isFull(1, 1));
        StdOut.println("numberOfOpenSites() = " + percolation.numberOfOpenSites());
        StdOut.println("percolates() = " + percolation.percolates());
    }
}
