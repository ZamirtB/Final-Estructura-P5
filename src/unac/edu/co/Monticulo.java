package unac.edu.co;

public class Monticulo {
    static final int NM = 20;
    private int EL;
    private Comparador[] v;

    public Monticulo() {
        EL = 0;
        v = new Comparador[NM];
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
    private boolean moundfull() {
        return (EL == v.length);
    }
    private void enlarge() {
        Comparador[] anteriorV = v;
        v = new Comparador[EL + NM];
        for (int j = 0; j < EL; j++)
            v[j] = anteriorV[j];
    }
    private void floatUp(int i) {
        Comparador newKey = v[i];
        while ((i > 0) && (v[padre(i)].mayorQue(newKey))) {
            v[i] = v[padre(i)];
        }
        v[i] = newKey;
    }
    public void insert(Comparador key) {
        if (moundfull()) {
            enlarge();
        }
        v[EL] = key;
        floatUp(EL);
        EL++;
    }
    public Comparador buscarMinimo() throws Exception {
        if (isEmpty())
            throw new Exception("Acceso a montículo vacío");
        return v[0];
    }
    public boolean isEmpty() {
        return EL == 0;
    }
    public void criba(int raiz) {
        boolean esMonticulo;
        int hijo;
        esMonticulo = false;
        while ((raiz < EL / 2) && !esMonticulo) {
            if (ChildL(raiz) == (EL - 1))
                hijo = ChildL(raiz);
            else {
                if (v[ChildL(raiz)].menorQue(v[ChildR(raiz)]))
                    hijo = ChildL(raiz);
                else
                    hijo = ChildR(raiz);
            }
            if (v[hijo].menorQue(v[raiz])) {
                Comparador t = v[raiz];
                v[raiz] = v[hijo];
                v[hijo] = t;
                raiz = hijo;
            } else
                esMonticulo = true;
        }
    }
    public Comparador eliminarMinimo() throws Exception {
        if (isEmpty())
            throw new Exception("Acceso a montículo vacío");
        Comparador menor;
        menor = v[0];
        v[0] = v[EL - 1];
        criba(0);
        EL--;
        return menor;
    }
    public static void criba2(Comparador v[], int raiz, int ultimo) {
        boolean esMonticulo;
        int hijo;
        int numElem = ultimo + 1;
        esMonticulo = false;
        while ((raiz < numElem / 2) && !esMonticulo) {
            if (Monticulo.ChildL(raiz) == (numElem - 1))
                hijo = Monticulo.ChildL(raiz);
            else {
                if (v[Monticulo.ChildL(raiz)].mayorQue(v[Monticulo.ChildR(raiz)]))
                    hijo = Monticulo.ChildL(raiz);
                else
                    hijo = Monticulo.ChildR(raiz);
            }
            if (v[hijo].mayorQue(v[raiz])) {
                Comparador t = v[raiz];
                v[raiz] = v[hijo];
                v[hijo] = t;
                raiz = hijo;
            } else
                esMonticulo = true;
        }
    }
    public static void ordenacionMonticulo(Comparador v[], int n) {
        int j;
        for (j = n / 2; j >= 0; j--)
            criba2(v, j, n - 1);
        for (j = n - 1; j >= 1; j--) {
            Comparador t;
            t = v[0];
            v[0] = v[j];
            v[j] = t;
            criba2(v, 0, j - 1);
        }
    }
}
