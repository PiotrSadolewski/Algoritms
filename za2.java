import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class s21009 {

    static int p = 0;
    private static int counter = 0;
    static int how = 0;
    Node head;

    public class Node{
        Object data;
        Node next;

        public Node getNext() {
            return next;
        }

    }
    public void Insert(int date){
        Node node = new Node();
        node.data = date;
        node.next = null;
        if(head == null){
            head = node;
        }
        else{
            Node ntmp = head;
            while(ntmp.next != null) {
                ntmp = ntmp.next;
            }
            ntmp.next = node;
        }
        incrementCounter();
    }

    public void ADD(int x){
        Node node = new Node();
        node.data = x-1;
        node.next = null;
        Node n = head;
        for(int i=0; i < getP(); i++){
            n = n.next;
        }
        node.next = n.next;
        n.next = node;
        incrementCounter();
        addP(x);
        CheckP(getP());
    }

    public void DELETE() {
        int index = getP() + 1;
        index = CheckId(index);
        if (index == 0) {
            int x = (int)head.data;
            head=head.next;
            decrementCounter();
            addP(x - 1);
            CheckP(getP());
        } else {
            Node ntmp = head;
            Node nCurrent = null;
            for (int i = 0; i < index - 1; i++) {
                ntmp = ntmp.next;
            }
            nCurrent = ntmp.next;
            ntmp.next = nCurrent.next;
            int x = (int) nCurrent.data;
            decrementCounter();
            addP(x);
            CheckP(getP());
        }
    }

    public void CheckP(int index){
        if(index >= counter){
            do index = index-counter;
            while(index >= counter);
            p = index;
        }
    }

    public int CheckId(int id){
        if(id>counter-1) return 0;
        return id;
    }

    public void CreateList(s21009 list, String path)throws FileNotFoundException{
        Scanner sc = new Scanner(new File(path));
        how = sc.nextInt();
        while(sc.hasNextInt()){
            list.Insert(sc.nextInt());
        }
    }

    public void ChangeList(int k){
        for(int i=1;i<=k; i++){
            Node node=new Node();
            node.next=null;
            Node nCurent = head;
            for(int j=0;j<p;j++){
                nCurent = nCurent.next;
            }
            if((int)(nCurent.data)%2==0) {
                DELETE();
            }
            else
                ADD((int)nCurent.data);
        }
    }

    public void display(){
        Node node = new Node();
        node.next = null;
        Node nCurent = head;
        int[] tmp = new int[counter];
        for(int i=0;i<counter;i++){
            if(i >= p) {
                tmp[i - p] = (int) nCurent.data;
                nCurent = nCurent.next;
            }
            else{
                tmp[counter - p + i] = (int) nCurent.data;
                nCurent = nCurent.next;
            }
        }
        for (int i = 0; i < counter; i++) {
            System.out.print(tmp[i]+" ");
        }
    }

    private void addP(int add){
        p = p + add;
    }

    private void decrementCounter() {
        counter--;
    }

    private static void incrementCounter() {
        counter++;
    }

    private static int getP(){
        return p;
    }

    public void show() {
        if (head != null) {
            Node nCurrent = head.getNext();
            while (nCurrent != null) {
                System.out.print(nCurrent.data.toString() + " ");
                nCurrent = nCurrent.getNext();
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        s21009 s2 = new s21009();
        s2.CreateList(s2,args[0]);
        s2.ChangeList(how);
        s2.display();
    }
}
