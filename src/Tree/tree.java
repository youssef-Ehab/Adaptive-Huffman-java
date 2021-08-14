package Tree;

import java.util.*;

class comp implements Comparator<node> {

    @Override
    public int compare(node t1, node t2) {
        return t1.compareTo(t2);
    }
}

public class tree {
    private Vector<Character> unique = new Vector<>();
    //private Vector<Character> unique2 = new Vector<>();
    private Vector<Character> ASCII = new Vector<>();
    private Vector<String> Encoded = new Vector<>();
    private Vector<String> Decode = new Vector<>();
    private HashMap<String, String> Path = new HashMap<>();
    private HashMap<String, String> ReverseOfPath = new HashMap<>();
    private HashMap<Character, Integer> Frequency = new HashMap<>();
    private HashMap<Character, node> there = new HashMap<>();
    private node root = new node(' ', 0);
    private String nyt = " ";
    private String nyt2 = "0";
    private TreeSet<node> set = new TreeSet<node>(new comp());
    private TreeSet<node> Deset = new TreeSet<node>(new comp());

    tree() {
        set.add(new node());
        Deset.add(new node());
    }

    public void AddtoASCII() {
        for (int i = 0; i < 128; i++) {
            ASCII.add((char) i);
        }
    }


    public void AddNode(char c) {

        if (!unique.contains(c)) {

            node n = new node(c, 0);
            unique.add(c);
            Encoded.add(String.valueOf(ASCII.indexOf(c)));
            Encoded.add(nyt + "0");
            Frequency.put(c, 1);
            set.add(n);
            //there.put(c, n);
            incrementCounters(n);
            set.add(new node(' ', 0));

            updateTree();
            nyt += "0";
            updateMap();

        } else {
            int count = Frequency.get(c);
            String data = c + " " + count;
            String path = Path.get(data);
            Encoded.add(path);
            node n = new node(c, count);
            Frequency.remove(c);
            Frequency.put(c, count + 1);
            SwapNodes(n);
            increment2(n);
            //set.remove(n);
            //n=new node(c,count);
            //set.add(n);
            // incrementCounters(n);
            updateTree();
            updateMap();
        }
    }

    public void SwapNodes(node n) {
        node temp = there.get(n.symbol);
        Iterator<node> itr = set.iterator();
        while (itr.hasNext()) {
            temp = itr.next();
            if (n.freq == temp.freq && temp.symbol != ' ' && n.symbol != temp.symbol) {
                itr.remove();
                set.remove(n);
                n.freq += 1;
                set.add(temp);
                set.add(n);

                break;
            }
            if (temp.symbol == n.symbol) {
                set.remove(n);
                n.freq += 1;
                set.add(n);
                break;
            }
    /*if (temp!=n){
        set.remove(n);
        set.add(new node(n.symbol,n.freq+1));
        set.add(temp);
    }
*/
        }
    }

    public void SwapNodesDecomp(node n) {
        node temp = there.get(n.symbol);
        Iterator<node> itr = Deset.iterator();
        while (itr.hasNext()) {
            temp = itr.next();
            if (n.freq == temp.freq && temp.symbol != ' ' && n.symbol != temp.symbol) {
                itr.remove();
                Deset.remove(n);
                n.freq += 1;
                Deset.add(temp);
                Deset.add(n);

                break;
            }
            if (temp.symbol == n.symbol) {
                set.remove(n);
                n.freq += 1;
                Deset.add(n);
                break;
            }
    /*if (temp!=n){
        set.remove(n);
        set.add(new node(n.symbol,n.freq+1));
        set.add(temp);
    }
*/
        }
    }

    public void updateTree() {
        TreeSet<node> temp = new TreeSet<>(new comp());
        temp.addAll(set);
        set = new TreeSet<>(temp);
    }

    public void updateTreedecomp() {
        TreeSet<node> temp = new TreeSet<>(new comp());
        temp.addAll(Deset);
        set = new TreeSet<>(temp);
    }

    public void updateMap() {
        Path = new HashMap<>();
        String counter = " ";
        String data = " ";
        Iterator<node> itr = set.iterator();
        node n = new node();
        n = itr.next();
        String temp = " ";
        String check = " ";
        while (itr.hasNext()) {
            check = counter;

            for (int i = 0; i < 2; i++) {
                temp = check;
                n = itr.next();
                data = (String.valueOf(n.symbol)) + " " + String.valueOf(n.freq);

                if (i == 0) {
                    Path.put(data, temp + "1");
                    temp = "1";

                } else {
                    Path.put(data, temp + "0");
                    temp = "0";
                }
                if (n.symbol == ' ') {
                    counter = counter + temp;

                }

            }

        }
    }

    public void updateMap2() {
        Path = new HashMap<>();
        ReverseOfPath = new HashMap<>();
        String counter = " ";
        String data = " ";
        Iterator<node> itr = Deset.iterator();
        node n = new node();
        n = itr.next();
        String temp = " ";
        String check = " ";
        while (itr.hasNext()) {
            check = counter;

            for (int i = 0; i < 2; i++) {
                temp = check;
                if (itr.hasNext()) {
                    n = itr.next();
                    data = (String.valueOf(n.symbol)) + " " + String.valueOf(n.freq);

                    if (i == 0) {
                        Path.put(data, temp + "1");
                        ReverseOfPath.put(temp + "1", data);
                        temp = "1";

                    } else {
                        Path.put(data, temp + "0");
                        ReverseOfPath.put(temp + "0", data);
                        temp = "0";
                    }
                    if (n.symbol == ' ') {
                        counter = counter + temp;

                    }

                } else {
                    break;
                }

            }

        }
    }

    public Vector GetEncoded() {
        return Encoded;
    }

    public TreeSet GetTreeSet() {
        return set;
    }

    public void incrementCounters(node n) {
        //if (set.last() == n){
        Iterator<node> itr = set.iterator();
        while (itr.hasNext()) {
            node tmp = new node();
            tmp = itr.next();

            char sym = tmp.symbol;
            if (sym == ' ' || sym == n.symbol) {
//            if (Path.get(n).length()==Path.get(tmp).length()){continue;}
                tmp.freq += 1;
                if (sym == n.symbol) break;
            }
        }
        //}
    }

    public void incrementCountersDecomp(node n) {
        //if (set.last() == n){
        Iterator<node> itr = Deset.iterator();
        while (itr.hasNext()) {
            node tmp = new node();
            tmp = itr.next();

            char sym = tmp.symbol;
            if (sym == ' ' || sym == n.symbol) {
//            if (Path.get(n).length()==Path.get(tmp).length()){continue;}
                tmp.freq += 1;
                if (sym == n.symbol) break;
            }
        }
        //}
    }

    public void increment2(node n) {
        int pos = set.headSet(n).size();
        Iterator<node> itr = set.iterator();
        int counter = 0;
        while (itr.hasNext()) {
            node tmp = new node();
            tmp = itr.next();

            char sym = tmp.symbol;
            if (sym == ' ' && counter < pos && counter % 2 == 0) {
                tmp.freq += 1;

            }
            counter++;
        }
    }

    public void increment2Decomp(node n) {
        int pos = Deset.headSet(n).size();
        Iterator<node> itr = Deset.iterator();
        int counter = 0;
        while (itr.hasNext()) {
            node tmp = new node();
            tmp = itr.next();

            char sym = tmp.symbol;
            if (sym == ' ' && counter < pos && counter % 2 == 0) {
                tmp.freq += 1;

            }
            counter++;
        }
    }

    public void removeNYT() {
        Encoded.remove(nyt);
    }


    public HashMap GetPath() {
        return Path;
    }

    public void decompression(Vector<String> vec) {
        String temp;
        String word;
        Path = new HashMap<>();
        Frequency = new HashMap<>();
        Character letter;
        for (int i = 0; i < vec.size(); i++) {
            temp = vec.elementAt(i);

            if (temp.charAt(0) == ' ') {
                temp = temp.substring(1, temp.length());
            }
            if (temp.equals(nyt2)) {
                nyt2 += "0";
                temp = vec.elementAt(i + 1);
                letter = ASCII.elementAt(Integer.parseInt(temp));
                node tmp = new node(letter, 0);
                Deset.add(new node(letter, 0));
                incrementCountersDecomp(tmp);
                Deset.add(new node(' ', 0));
                //   incrementCountersDecomp(new node(letter, 0));
                Decode.add(letter.toString());
                Frequency.put(letter, 1);
                updateTreedecomp();
                updateMap2();
                i++;
            } else if (i == 0) {
                letter = ASCII.elementAt(Integer.parseInt(temp));
                node n = new node(letter, 0);
                Decode.add(ASCII.elementAt(Integer.parseInt(temp)).toString());
                Deset.add(n);
                incrementCountersDecomp(n);
                Deset.add(new node(' ', 0));
                Frequency.put(letter, 1);

                updateTreedecomp();
                updateMap2();
            } else {
                word = ReverseOfPath.get(temp);
                Integer counter = Frequency.get(word);
                Frequency.remove(word);

                Frequency.put(word.charAt(0), counter + 1);
                Deset.remove(new node(word.charAt(0), counter));
                node n = new node(word.charAt(0), counter + 1);
                Deset.add(n);
                Decode.add(word);
                SwapNodesDecomp(n);
                updateTreedecomp();
                updateMap2();
            }
        }


    }
        /*if (unique.contains(ASCII.elementAt(Integer.parseInt(s)))){

        }
        else{

            Decode.add(ASCII.elementAt(Integer.parseInt(s)).toString());
            char asci=ASCII.elementAt(Integer.parseInt(s));
            node temp=new node(asci,1);
            Deset.add(temp);
            Deset.add(new node(' ',0));
            unique.add(asci);
            incrementCounters(temp);
            Decode.add(nyt2+'0');
            nyt2+='0';
            Frequency.put(asci,1);
            updateTree();
            updateMap();
        }*/

}


