import java.io.*;
import java.util.Scanner;

public class s21009_zad4{

    static int length = 0;
    static char[] CharArrray;
    static int[] IntArray;


    public static void main(String[] args) throws FileNotFoundException {
        s21009_zad4 s = new s21009_zad4();
        s.CreateList(args[0]);

        MinHeap minHeap = new MinHeap(getLength());

        for (int i = 0; i < getLength(); i++) {
            Node node = new Node();
            node.c = CharArrray[i];
            node.data = IntArray[i];
            node.left = null;
            node.right = null;
            minHeap.add(node);
        }

        Node root = null;
        while (minHeap.getCurrent() > 1) {
            Node x = minHeap.remove();
            Node y = minHeap.remove();

            Node tmp = new Node();
            tmp.data = x.data + y.data;
            tmp.c = '-';
            tmp.left = x;
            tmp.right = y;
            root = tmp;
            minHeap.add(tmp);
        }
        show(root, "");
    }

    public static void show(Node root, String s) {
        if (root.left == null && root.right == null && Character.isLetter(root.c)) {
            System.out.println(root.c + " " + s);
            return;
        }
        show(root.left, s + "0");
        show(root.right, s + "1");
    }

    public void CreateList(String path)throws FileNotFoundException {
        makeLength(path);
        addToArray(path);
    }

    public static int getLength(){
        return length;
    }

    public void makeLength(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path));
        while(scanner.hasNextLine()){
            length++;
            scanner.nextLine();
        }
        CharArrray = new char[length];
        IntArray = new int[length];
    }

    public void addToArray(String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path));
        int counter = 0;
        while(scanner.hasNext()){
            char tmp1 = scanner.next().charAt(0);
            int  tmp  = Integer.parseInt(scanner.next());
            IntArray[counter] = tmp;
            CharArrray[counter] = tmp1;
            counter++;
        }
    }
}

class Node {
    int data;
    char c;
    Node left;
    Node right;
}

class MinHeap {
    private int size;
    private int current;
    private Node[] heap;

    public MinHeap(int max) {
        size = max;
        heap = new Node[size + 1];
        current = 0;
    }

    public void add(Node value) {
        heap[++current] = value;
        shiftUp(current);
    }

    public Node remove() {
        Node min = heap[1];
        swap(1, current);
        current--;
        if (current != 0)
            shiftDown(1);
        return min;
    }

    private void shiftUp(int k) {
        while (k > 1 && heap[k / 2].data > heap[k].data) {
            swap(k / 2, k);
            k = k / 2;
        }
    }

    private void shiftDown(int k) {
        while (2 * k <= current) {
            int i = 2 * k;
            int j = i + 1;
            if (i < current && heap[i].data > heap[j].data) {
                i++;
            }
            if (heap[k].data < heap[i].data) {
                break;
            }
            swap(k, i);
            k = i;
        }
    }

    private void swap(int j, int d) {
        Node tmp = heap[j];
        heap[j] = heap[d];
        heap[d] = tmp;
    }

    public int getCurrent(){
        return current;
    }
}
