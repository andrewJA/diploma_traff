import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by qual2 on 28.04.2017.
 */
public class Ttraff {
    private static int maxphasec = 4;
    private int[][] A,Au;
    private double[][] B,D,E1,E2,E3;
    private int[][] C;
    private ArrayList<ArrayList<Integer>> Ain,Aout;
    private double[] x;
    double[] y,y1;
    private double[] xint;
    private int[] xover;
    private double[] x0;
    private int[] xd;
    private double[] xmax;
    private int[] u,u1;
    private int[] umax;
    private int maxphase;
    private int[][] uProg;
    private int[] Indx;
    ArrayList<Integer>[][] F;
    ArrayList<Integer> I_in, I_out;
    private int L, M, N, k_in, k_out, kit;
    private double[] ed;
    private int[] kitu;
    private int[][] Phases, Phases_opt, Phases_p, Phases_m,
    Phases_ind_contr;
    public Ttraff(int L1,int M1, int N1){
        L = L1;
        M = M1;
        N = N1;
        maxphase = maxphasec;
        x = new double[L];
        xint = new double[L];
        xmax = new double[L];
        xover = new int[L];
        u = new int[M];
        u1 = new int[M];
        umax = new int[M];
        x0 = new double[L];
        xd = new int[L];
        y = new double[L];
        y1 = new double[L];
        ed = new double[L];
        uProg = new int[N+1][M];
        A = new int[L][L];
        Au = new int[L][L];
        C = new int[L][L];
        E1 = new double[L][L];
        B = new double[L][L];
        E2 = new double[L][L];
        D = new double[L][L];
        E3 = new double[L][L];
        F = new ArrayList[L][L];
        Ain = new ArrayList<>();
        Aout = new ArrayList<>();
        Phases = new int[M][M];
        kitu = new int[M];
        Phases_opt = new int[M][maxphase];
        Phases_p = new int[M][maxphase];
        Phases_m = new int[M][maxphase];
        Phases_ind_contr = new int[M][maxphase];

    }

    public void setX(double[] x) {
        this.x = x;
    }

    public void setA(int[][] a) {
        A = a;
    }

    public void setB(double[][] b) {
        B = b;
    }

    public void setD(double[][] d) {
        D = d;
    }

    public void setC(int[][] c) {
        C = c;
    }

    public void setF(ArrayList<Integer>[][] f) {
        F = f;
    }

    public void setI_in(ArrayList<Integer> i_in) {
        I_in = i_in;
    }

    public void setI_out(ArrayList<Integer> i_out) {
        I_out = i_out;
    }

    public void setAin(){
        ArrayList<Integer> buff;
        for (int i = 0; i < L; i++) {
            if (I_in.indexOf(i) != -1){
                buff = new ArrayList<>();
                buff.add(new Integer(-1));
                Ain.add(i,buff);
            }
            else
                buff = new ArrayList<>();
            for (int j = 0; j < L; j++) {
                if ((int)(A[j][i]) > 0) buff.add(new Integer(j));
                Ain.add(i,buff);
            }

            System.out.println(Ain.get(i));

        }
    }

    public void setAout(){
        ArrayList<Integer> buff;
        for (int i = 0; i < L; i++) {
            if (I_out.indexOf(i) != -1){
                buff = new ArrayList<>();
                buff.add(new Integer(-1));
                Aout.add(i,buff);
            }
            else
                buff = new ArrayList<>();
            for (int j = 0; j < L; j++) {
                if ((int)(A[i][j]) > 0) buff.add(new Integer(j));
                Aout.add(i,buff);
            }
            System.out.println(Aout.get(i));

        }
    }

    public void SetI_in(ArrayList<Integer> I1){
        I_in.addAll(I1);
    }
    public void SetI_out(ArrayList<Integer> I1){
        I_out.addAll(I1);
    }

    public void setX0(double[] x0) {
        this.x0 = x0;
    }

    public void setXint(double[] xint) {
        this.xint = xint;
    }

    public void setXmax(double[] xmax) {
        this.xmax = xmax;
    }

    public void setUmax(int[] umax) {
        this.umax = umax;
    }

    public void setMaxphase(int maxphase) {
        this.maxphase = maxphase;
    }

    public void setIndx() {
        int k = 0;
        for (int i = 0; i < L; i++) {
            if (I_out.indexOf(i) == -1){
                k++;
                Indx = new int[k];
                Indx[k-1] = i;
            }
        }
    }

    public int getL() {
        return L;
    }

    public double[] getY() {
        return y;
    }

    public double[] getY1() {
        return y1;
    }

    public void setU(int[] u) {

        this.u = u;
    }

    public void setKitu(int[] kitu) {
        this.kitu = kitu;
    }

    public void setKit(int kit) {

        this.kit = kit;
    }

    public double[] getX() {

        return x;
    }

    public double[] getXint() {
        return xint;
    }

    public double[] getXmax() {
        return xmax;
    }

    public int[] getU() {
        return u;
    }

    public ArrayList<Integer> getI_in() {
        return I_in;
    }

    public ArrayList<Integer> getI_out() {
        return I_out;
    }

    public int getN() {
        return N;
    }

    public int getK_in() {
        return k_in;
    }

    public int getK_out() {
        return k_out;
    }

    public int getKit() {
        return kit;
    }

    public int[] getKitu() {
        return kitu;
    }

    public void setIntense(double[] x2){
        for (int i = 0; i < L; i++) {
            xint[i] = x2[i];
        }
    }

    public int[] getUmax() {
        return umax;
    }

    public int getM() {
        return M;
    }

    public int[][] getPhases() {
        return Phases;
    }
    public int max(int[] A){
        int maxi = -100;
        for (int i = 0; i < A.length; i++) {
            if (A[i]>maxi) maxi = A[i];
        }
        return maxi;
    }

    public int[][] getPhases_ind_contr() {
        return Phases_ind_contr;
    }

    public ArrayList<ArrayList<Integer>> getAin() {
        return Ain;
    }

    public ArrayList<ArrayList<Integer>> getAout() {
        return Aout;
    }

    public void Model1(){
        double delt;
        for (int i = 0; i < L; i++) {
            y[i] = 0;
            for (int j = 0; j < L; j++) {
                for (int k = 0; k < u.length; k++) {
                    if (F[i][j].indexOf(u[k]) != -1) {
                        delt = x[i] * D[i][j];
                        if (delt > B[i][j]) delt = B[i][j];
                        y[i] += delt;
                        System.out.println(delt);
                    }
                }
            }
        }

        for (int i = 0; i < L; i++) {
            //x[i] = x[i] - y[i] + y1[i] + xint[i];
            x[i] = x[i] - y[i]  + xint[i];
        }

    }

    public void ModelFaster(){
        double delt;
        for (int i = 0; i < L; i++) {
            y[i] = 0;
            if (Aout.get(i).get(0) >= 0 && Aout.get(i).isEmpty() == false) {
                for (int j = 0; j < Aout.get(i).size(); j++)
                    if (F[i][Aout.get(i).get(j)].indexOf(u[C[i][Aout.get(i).get(j)]]) != -1) {
                        delt = x[i] * D[i][Aout.get(i).get(j)];
                        if (delt > B[i][Aout.get(i).get(j)]) delt = B[i][Aout.get(i).get(j)];
                        y[i] += delt;
                    }
            }
        }
        for (int i = 0; i < L; i++) {
            y1[i] = 0;
            if (Ain.get(i).get(0) >= 0 && Ain.get(i).isEmpty() == false)
                for (int j = 0; j < Ain.get(i).size(); j++) {
                    if (F[Ain.get(i).get(j)][i].indexOf(u[C[Ain.get(i).get(j)][i]]) != -1){
                        delt = x[Ain.get(i).get(j)] * D[Ain.get(i).get(j)][i];
                        if (delt > B[Ain.get(i).get(j)][i]) delt = B[Ain.get(i).get(j)][i];
                        y1[i] += delt;
                    }
                }
        }
        for (int i = 0; i < L; i++) {
            x[i] = x[i] - y[i] + y1[i] + xint[i];
            //x[i] = x[i] - y[i]  + xint[i];
        }
    }


    public void OgrUpr(){
        for (int i = 0; i < M; i++) {
            if (u[i] >= umax[i])
                u[i] = umax[i] - 1;
        }
    }

    public void Conf(){
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                if (A[i][j] != 0)
                    if (F[i][j].indexOf(u[(int)(C[i][j])]) != -1)
                        Au[i][j] = 1;
                    else Au[i][j] = 0;
                else Au[i][j] = 0;
            }
        }
    }

    public void Model(){
        BaseFunc bs = new BaseFunc();
        E1 = bs.MultxyA(x,ed);
        E2 = bs.MultAdamar(Au,D);
        E3 = bs.MultAdamar_r(E1,E2);
        E1 = bs.MultAdamar(Au,B);
        E2 = bs.CutSubABC(E3,E1);
        E1 = bs.SubABC(E3,E2);
        y = bs.MultAxy(E1,ed);
        y1 = bs.CutSubxyz(x,y);
        E2 = bs.Transp(E1);
        y = bs.MultAxy(E2,ed);
        for (int i = 0; i < L-1; i++) {
            x[i] = y[i] + y1[i];
        }
        kit++;
    }

    public void Initial(){
        for (int i = 0; i < L; i++) {
            x[i] = x0[i];
        }
        kit = 0;
        for (int i = 0; i < M; i++) {
            u[i] = 0;
            u1[i] = 0;
            kitu[i] = 0;
        }
    }

    public int ReadKit(){
        return  kit;
    }

    public void Diskr(int d){
        double delt,s;
        for (int i = 0; i < L; i++) {
            if (xmax[i] > 0) {
                delt = xmax[i]/d;
                xd[i] = (int)(x[i]/delt) % d;
            }
            else {
                s = 0;
                if (!(I_out.indexOf(i) != -1)){
                    delt = x0[i]/d;
                    if (delt!=0)
                        xd[i] = (int)(x[i]/delt) % d;
                    else xd[i] = 0;
                }
                else xd[i] = 0;
            }
        }
    }
    public void CheckPhases(){
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < umax[i]; j++) {
                if (Phases[i][j] > Phases_p[i][j])
                    Phases[i][j] = Phases_p[i][j];
                else if (Phases[i][j] < Phases_m[i][j])
                    Phases[i][j] = Phases_m[i][j];
            }
        }
    }
    public void CheckOverFlow_1(){
        for (int i = 0; i < L-1; i++) {
            if (xmax[i]<0) xover[i] = 0;
            else if (xmax[i]<x[i]) xover[i]=1;
            else xover[i] = 0;
        }
    }

    public void setPhases_cur(int[][] phases_opt) {
        Phases = phases_opt;
    }

    public void setPhases_p(int[][] phases_p) {
        Phases_p = phases_p;
    }

    public void setPhases_m(int[][] phases_m) {
        Phases_m = phases_m;
    }

    public void setPhases_ind_contr(int[][] phases_ind_contr) {
        Phases_ind_contr = phases_ind_contr;
    }
}
