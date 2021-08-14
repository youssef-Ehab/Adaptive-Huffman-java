package Tree;

import java.util.Objects;
import java.util.TreeSet;

public class node {
    protected char symbol;
    protected Integer freq;

    node() {
        symbol = ' ';
        freq = 0;
    }

    @Override
    public String toString() {
        return symbol + " " + freq;
    }

    node(char s, int coun) {

        symbol = s;
        freq = coun;
    }

    node(char s) {
        symbol = s;
        freq = 1;
    }

    public node createNode(char c) {
        node n = new node();
        n.symbol = c;
        n.freq = 1;
        return n;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        node n = (node) obj;
        return Objects.equals(symbol, n.symbol) && Objects.equals(freq, n.freq);
    }

    int compareTo(node t1) {
        if (symbol == t1.symbol && freq.equals(t1.freq)) return 0;
        if (freq > t1.freq) return -1;
        if (t1.freq > freq) return 1;
        return 1;
    }
}
