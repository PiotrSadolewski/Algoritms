import java.io.*;
import java.util.Scanner;

public class s21009_zad2 {
    public static void main(String[] args) throws FileNotFoundException {
        createTree(args[0]);
    }

    public static void createTree(String path) throws FileNotFoundException{
        Scanner sc = new Scanner(new File(path));
        Tree tree = new Tree();
        while (sc.hasNext()){
            tree.add(sc.nextLine());
        }
        System.out.println(tree.getWord());
    }
}

class Node {
    private char value;
    private Node left;
    private Node right;

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}

class Tree {
    private final Node root;
    private int Length = 0;
    private char[] output;

    public Tree() {
        this.root = new Node();
    }

    public void add(String string) {
        char value = string.charAt(0);

        if (string.length() <= 2){
            root.setValue(value);
            return;
        }
        createNode(value,string.substring(2), root);
        if(string.substring(2).length() + 1> Length) Length = string.substring(2).length() + 1;
    }


    private void createNode(char value, String children, Node current){
        for (int i = 0; i < children.length(); i++){
            if (children.charAt(i) == 'L') {
                if (current.getLeft() == null) {
                    current.setLeft(new Node());
                }
                if (children.length() - 1 == i){
                    current.getLeft().setValue(value);
                }
                current = current.getLeft();
            }
            else{
                if (current.getRight() == null) {
                    current.setRight(new Node());
                }
                if (children.length() - 1 == i){
                    current.getRight().setValue(value);
                }
                current = current.getRight();
            }
        }
    }

    public String getWord() {
        output = new char[Length];
        char[] tmp = new char[Length];
        findOutput(tmp, root,0);

        output = delete(output);
        output = reverse(output);
        return String.valueOf(output);
    }

    public void findOutput(char[] arrTmp, Node tmp, int counter) {
        if (tmp == null) return;

        arrTmp[counter] = tmp.getValue();
        counter++;

        if (tmp.getLeft() == null && tmp.getRight() == null) {
            if(counter <= Length)
                for (int i = counter; i < Length; i++)
                    arrTmp[i] = 0;
            if (compareArrays(output, arrTmp)) output = arrTmp.clone();
        }

        findOutput(arrTmp,tmp.getLeft(),counter);
        findOutput(arrTmp,tmp.getRight(),counter);
    }

    public boolean compareArrays(char[] lword, char[] arrTmp) {
        int newLength = 0;
        int tmpLength = 0;

        for (int i = 0; i < lword.length; i++){
            if(lword[i] != 0) newLength++;
            if(arrTmp[i] != 0) tmpLength++;
        }

        char [] newLongAr = new char[newLength];
        copy(lword, newLongAr);
        newLongAr = reverse(newLongAr);

        char [] newTmpAr = new char[tmpLength];
        copy(arrTmp, newTmpAr);
        newTmpAr = reverse(newTmpAr);

        for (int i = 0, j = 0; i < newLength && j < tmpLength; i++, j++){
            if(newLongAr[i] < newTmpAr[i])
                return true;
            if(newLongAr[i] > newTmpAr[i])
                return false;
        }
        return newLength < tmpLength;
    }

    public char[] reverse(char[] c){
        int counter = 0;
        char[] tmp = c.clone();

        for (int i = c.length - 1; i >= 0; i--){
            c[i] = tmp[counter];
            counter++;
        }
        return c;
    }

    public void copy(char[] tmp1, char[] tmp2){
        for (int i = 0; i < tmp2.length; i++){
            tmp2[i] = tmp1[i];
        }
    }

    public char[] delete(char[] word){
        int Length = 0;
        for (int i = 0; i < word.length; i++){
            if(word[i] != 0) Length++;
        }
        char [] newWord = new char[Length];
        copy(word,newWord);
        return newWord;
    }
}
