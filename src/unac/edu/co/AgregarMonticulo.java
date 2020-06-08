package unac.edu.co;

public class AgregarMonticulo {
    static final int NM = 20;
    private int EL;
    private int[] v;

    public AgregarMonticulo() {
        EL = 0;
        v = new int[NM];
    }
    public static int padre(int i) {
        return (i - 1) / 2;
    }
    public static int ChildL(int i) {
        return (2 * i + 1);
    }
    public static int ChildR(int i) {
        return (2 * i + 1) + 1;
    }
    public void insert(int key) {
        if (monticuloLleno()) {
            previous();
        }
        v[EL] = key;
        floatUp(EL);
        EL++;
    }
    private boolean monticuloLleno() {
        return (EL == v.length);
    }
    private void previous() {
        int[] previousV = v;
        v = new int[EL + NM];
        for (int j = 0; j < EL; j++)
            v[j] = previousV[j];
    }
    private void floatUp(int i) {
        int newKey = v[i];
        while ((i > 0) && (v[padre(i)] > newKey)) {
            v[i] = v[padre(i)];
            i = padre(i);
        }
        v[i] = newKey;
    }
    public int SearchMinimum() throws Exception {
        if (isEmpty())
            throw new Exception("Acceso a montículo vacío");
        return v[0];
    }
    public boolean isEmpty() {
        return EL == 0;
    }
    public int removeMinimum() throws Exception {
        if (isEmpty())
            throw new Exception("Acceso a montículo vacío");
        int less;
        less = v[0];
        v[0] = v[EL - 1];
        criba(0);
        EL--;
        return less;
    }
    public void criba(int root) {
        boolean ismound;
        int child;
        ismound = false;
        while ((root < EL / 2) && !ismound) {
            if (ChildL(root) == (EL - 1))
                child = ChildL(root);
            else {
                if (v[ChildL(root)] < v[ChildR(root)])
                    child = ChildL(root);
                else
                    child = ChildR(root);
            }
            if (v[child] < v[root]) {
                int t = v[root];
                v[root] = v[child];
                v[child] = t;
                root = child;
            } else
                ismound = true;
        }
    }
    public static void criba2(int v[], int raiz, int last) {
        boolean ismound;
        int child;
        int numElem = last + 1;
        ismound = false;
        while ((raiz < numElem / 2) && !ismound) {
            if (Monticulo.ChildL(raiz) == (numElem - 1))
                child = Monticulo.ChildL(raiz);
            else {
                if (v[Monticulo.ChildL(raiz)] > v[Monticulo.ChildR(raiz)])
                    child =Monticulo.ChildL(raiz);
                else
                    child = Monticulo.ChildR(raiz);
            }
            if (v[child] > v[raiz]) {
                int t = v[raiz];
                v[raiz] = v[child];
                v[child] = t;
                raiz = child;
            } else
                ismound = true;
        }
    }
    public static void ordination(int v[], int n) {
        int j;
        for (j = n / 2; j >= 0; j--)
            criba2(v, j, n - 1);
        for (j = n - 1; j >= 1; j--) {
            int t;
            t = v[0];
            v[0] = v[j];
            v[j] = t;
            criba2(v, 0, j - 1);
        }
    }
    public void print() {
        System.out.print("\nMontículo: ");
        for (int i = 0; i< EL; i++) {
            System.out.print(v[i] + " ");
        }
    }
    public void order() {
        ordination( v, EL);
    }
    public void paint(String prefix, boolean isCola, int n) {
        if (n < EL) {
            System.out.println(prefix + (isCola ? "└-- " : "├--- ") + v[n]);
            paint(prefix + (isCola ? "    " : "|   "), false, ChildL(n));
            paint(prefix + (isCola ? "    " : "│   "), true, ChildR(n));
        }
    }
    public void paint() {
        System.out.println();
        paint("", true, 0);
    }
}
