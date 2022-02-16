import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class s21009 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        int k;
        String train;
        String test;
        boolean time = true;
        double[] x = new double[4];
        double[] y = new double[4];
        double Value;

        System.out.println("Podaj k: ");
        k = scan.nextInt();
        System.out.println("Podaj ścieżke do train-set: ");
        train = scan.next();
        System.out.println("Podaj ścieżkę do test-set: ");
        test = scan.next();

        kNN(train,test,k);



        ArrayList<Flower> list = new ArrayList<Flower>();
        while (time){
            System.out.println("Podaj 4 wektory :");
            Scanner trainSc = new Scanner(new File(train));
            for (int i = 0; i < x.length; i++){
                x[i] = scan.nextDouble();
            }

            while (trainSc.hasNextLine()){
                String[] tmp = trainSc.nextLine().split("[;]");
                y = getTable(tmp);
                Value = Math.sqrt((Math.pow((x[0]-y[0]),2) + Math.pow((x[1]-y[1]),2) + Math.pow((x[2]-y[2]),2) + Math.pow((x[3]-y[3]),2)));
                list.add(new Flower(Value,getType(tmp)));
            }
            Collections.sort(list);
            System.out.println(list.get(0).getName());

            System.out.println("Czy chcesz zakończyć działanie programy TAK/NIE");
            String tmp = scan.next();
            if (tmp.equals("TAK"))
                time = false;
        }
    }


    public static void kNN(String train, String test, int k) throws FileNotFoundException {
        ArrayList<Flower> list = new ArrayList<Flower>();
        double[] x;
        double[] y;
        double tmpValue;
        int counter = 0;
        int counter2 = 0;
        String[] tmp;

        Scanner testSc = new Scanner(new File(test));
        while(testSc.hasNextLine()){
            Scanner trainSc = new Scanner(new File(train));
            tmp = testSc.nextLine().split("[;]");

            x = getTable(tmp);
            String tmpStr = getType(tmp);


            while (trainSc.hasNextLine()){
                tmp = trainSc.nextLine().split("[;]");
                y = getTable(tmp);
                tmpValue = Math.sqrt((Math.pow((x[0]-y[0]),2) + Math.pow((x[1]-y[1]),2) + Math.pow((x[2]-y[2]),2) + Math.pow((x[3]-y[3]),2)));
                list.add(new Flower(tmpValue,getType(tmp)));
            }
            Collections.sort(list);
            if (checkList(list,k,tmpStr)){
                counter++;
            }
            list.clear();
            counter2++;
        }

        System.out.println("accuracy = " + counter/counter2);
    }

    public static double[] getTable(String[] line){
        double[] tab = new double[4];
        for (int i = 0; i < tab.length; i++) {
            tab[i] = Double.parseDouble(line[i]);
        }
        return tab;
    }

    public static String getType(String[] line){
        return line[4];
    }

    public static boolean checkList(ArrayList<Flower> lista, int k, String typeTest) {
        boolean returned = false;
        int counterS = 0;
        int counterVer = 0;
        int counterVir = 0;
        String tmp1 = null;

        for (int i = 0; i < k; i++) {
            String tmp = lista.get(i).getName();
            if (tmp.equals("Iris-virginica\t")) {
                counterVir++;
            } else if (tmp.equals("Iris-setosa\t")) {
                counterS++;
            } else if (tmp.equals("Iris-versicolor\t")) {
                counterVer++;
            }
        }

        if (counterS > counterVer && counterS > counterVir){
            tmp1 = "Iris-setosa\t";
        }
        else if (counterVer > counterS && counterVer > counterVir){
            tmp1 = "Iris-versicolor\t";
        }
        else if (counterVir > counterVer && counterVir > counterS){
            tmp1 = "Iris-virginica\t";
        }

        System.out.println(typeTest + "  " + tmp1);

        if (typeTest.equals(tmp1))
            returned = true;

        return returned;
    }



    static class Flower implements Comparable<Flower>{
        private double aDouble;
        private String name;
        public Flower(double aDouble,String name){
            this.aDouble = aDouble;
            this.name = name;
        }

        public String getName(){
            return name;
        }

        @Override
        public String toString() {
            return aDouble + "  " +name;
        }


        @Override
        public int compareTo(Flower flower) {
            if (this.aDouble > flower.aDouble)
                return 1;
            else
                return -1;
        }
    }
}
