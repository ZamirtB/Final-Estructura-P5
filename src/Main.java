import unac.edu.co.AgregarMonticulo;

public class Main {
    public static void main(String[] s) {
        AgregarMonticulo list = new AgregarMonticulo();
        int [] listVector = {18, 11, 22, 33, 11, 34, 44, 2, 8, 11};
        System.out.print("Números: ");
        for (int i = 0; i < listVector.length; i++) {
            System.out.print(" " + listVector[i]);
            list.insert(listVector[i]);
        }
        list.print();
        list.paint();
        list.order();
        list.print();
        list.paint();
    }
}