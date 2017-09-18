/**
 * Created by qual2 on 30.04.2017.
 */
public class TGATraff {
    private int HH_ph, GG_ph, RR_ph;
    private int[][][] PopParChrom;
    private int[][] son1, son2;
    private double[][] Fuh;
    private int[] Pareto;
    private int[] RangPareto;
    private int l_chrom;
    private int[] zb;
    private int[] Params;
    private int s_k;
    private int ncb;
    private double pmut;
    private double gamma;
    private double[] Fu1, Fu2;
    private int nfu_ph;
    private int Rang1, Rang2;
    private Data_tr dat;

    public TGATraff(int HH1, int nfu1, int ncb1, int l_chrom1){
        Data_tr dat = new Data_tr();
        HH_ph = HH1;
        nfu_ph = nfu1;
        ncb = ncb1;
        l_chrom = l_chrom1;
        PopParChrom = new int[HH_ph][l_chrom][ncb];
        son1 = new int[l_chrom][ncb];
        son2 = new int[l_chrom][ncb];
        Fuh = new double[HH_ph][nfu_ph];
        zb = new int[ncb];
        Fu1 = new double[nfu_ph];
        Fu2 = new double[nfu_ph];
        RangPareto = new int[HH_ph];
        Params = new int[l_chrom];
    }

    public void GAforPhases(){
        int ks, k1, k2;
        int g_num, r_num, Rmax, i_max;

        double ps, ps1;
        for (int i = 0; i < HH_ph; i++) {
            for (int j = 0; j < l_chrom; j++) {
                for (int l = 0; l < ncb; l++) {
                    PopParChrom[i][j][l] = (int)(2*Math.random());
                }
                Fuh[i] = FuncGA(Fuh[i],PopParChrom[i], dat.L_cn, dat.M_cn, dat.N_cn);
            }
        }
        g_num = 1;
        do {
            for (int i = 0; i < HH_ph; i++) {
                RangPareto[i] = GA_RangPareto(Fuh[i]);
            }
            r_num = 1;
            do {
                k1 = (int)(HH_ph*Math.random());
                k2 = (int)(HH_ph*Math.random());
                ps = (1 + gamma*RangPareto[k1])/(1 + RangPareto[k1]);
                ps1 = (1 + gamma*RangPareto[k2])/(1 + RangPareto[k2]);
                if (ps < ps1) ps = ps1;
                if (Math.random() < ps) {
                    ks = (int)(Math.random()*l_chrom);
                    for (int i = 0; i < ks; i++) {
                        son1[i] = PopParChrom[k1][i];
                        son2[i] = PopParChrom[k2][i];
                    }
                    for (int i = ks; i < l_chrom; i++) {
                        son1[i] = PopParChrom[k2][i];
                        son2[i] = PopParChrom[k1][i];
                    }
                    if (Math.random() < pmut)
                        son1[(int)(l_chrom*Math.random())][(int)(ncb*Math.random())] = (int)(2*Math.random());
                    Fu1 = FuncGA(Fu1, son1, dat.L_cn, dat.M_cn, dat.N_cn);
                    //FuncGA(son1, Fu1);
                    Rang1 = GA_RangPareto(Fu1);
                    i_max = 0;
                    Rmax = RangPareto[0];
                    for (int i = 0; i < HH_ph; i++) {
                        if (RangPareto[i] > Rmax) {
                            Rmax = RangPareto[i];
                            i_max = i;
                        }
                    }
                    if (Rmax > Rang1){
                        for (int i = 0; i < l_chrom; i++) {
                            PopParChrom[i_max][i] = son1[i];
                        }
                        for (int i = 0; i < nfu_ph; i++) {
                            Fuh[i_max][i] = Fu1[i];
                        }
                        for (int i = 0; i < HH_ph; i++) {
                            RangPareto[i] = GA_RangPareto(Fuh[i]);
                        }
                    }
                    if (Math.random()<pmut)
                        son2[(int)(l_chrom*Math.random())][(int)(ncb*Math.random())] = (int)(2*Math.random());
                    Fu2 = FuncGA(Fu2, son2, dat.L_cn, dat.M_cn, dat.N_cn);
                    //FuncGA(son2, Fu2);
                    Rang2 = GA_RangPareto(Fu2);
                    i_max = 0;
                    Rmax = RangPareto[0];
                    for (int i = 0; i < HH_ph; i++) {
                        if (RangPareto[i] > Rmax) {
                            Rmax = RangPareto[i];
                            i_max = i;
                        }
                    }
                    if (Rmax > Rang2){
                        for (int i = 0; i < l_chrom; i++) {
                            PopParChrom[i_max][i] = son2[i];
                        }
                        for (int i = 0; i < nfu_ph; i++) {
                            Fuh[i_max][i] = Fu2[i];
                        }
                        for (int i = 0; i < HH_ph; i++) {
                            RangPareto[i] = GA_RangPareto(Fuh[i]);
                        }
                    }
                }
                r_num++;
            } while (r_num > RR_ph);
            g_num++;
            //EndGeneration;
        } while (g_num > GG_ph);
        ChoosePareto();
    }

    public double[] FuncGA(double[] Fu, int[][] sol, int L, int M, int N){
        int s_in_min = 10;
        int n_pen;
        double s1, s11, s2, dd, dd1;
        TOptimtraff EA = new TOptimtraff(L,M,N);
        EA.Gray_to_Phases(sol);
        EA.getTraff().Initial();
        s1 = 0;
        dd = 0;
        int[] bkitu  = EA.getTraff().getKitu();
        int[] bu = EA.getTraff().getU();
        do {
            EA.getTraff().setKit(EA.getTraff().getKit()+1);
            EA.getTraff().Model1();
            for (int i = 0; i < EA.getTraff().getM(); i++) {
                bkitu[i] = EA.getTraff().getKitu()[i] + 1;
                for (int j = 0; j < EA.getTraff().getUmax()[i]; j++) {
                    if (bkitu[i] >= EA.getTraff().getPhases()[i][j]){
                        bu[i] = (EA.getTraff().getU()[i] + 1)^(EA.getTraff().getUmax()[i]+1);
                        bkitu[i] = 0;
                    }
                }
            }
            dd1 = 0;
            n_pen = 0;

            for (int i = 0; i < EA.getTraff().getL() - 1; i++) {
                if (EA.getTraff().getXmax()[i] >= 0)
                    if (EA.getTraff().getX()[i] > EA.getTraff().getXmax()[i]) {
                        dd1 += EA.getTraff().getX()[i] - EA.getTraff().getXmax()[i];
                        n_pen++;
                    }
            }
            if (n_pen == 0) n_pen = 1;
            dd += dd1 / EA.getTraff().getN() / n_pen;
            s11 = 0;
            for (int i = 0; i < EA.getTraff().getL(); i++) {
                if (EA.getTraff().getI_in().indexOf(i) != -1 )
                    s11 += EA.getTraff().getX()[i];
            }
            s1 += s11/EA.getTraff().getN();
        } while (EA.getTraff().getKit() > EA.getTraff().getN());
        s2 = 0;
        for (int i = 0; i < EA.getTraff().getL(); i++) {
            if (EA.getTraff().getI_out().indexOf(i) != -1 ){
                s2 += EA.getTraff().getX()[i];
            }
        }
        s2 = s2/EA.getTraff().getN();
        Fu[0] = (int)(-s2 / EA.getTraff().getK_out() + dd);
        Fu[1] = (int)(s1 / EA.getTraff().getK_in() - s2 / EA.getTraff().getK_out() + dd);
        EA.getTraff().setKitu(bkitu);
        EA.getTraff().setU(bu);
        return Fu;
    }

    public int GA_RangPareto(double[] Fu){
        int r = 0;
        boolean fl, fl1 = false;
        for (int i = 0; i < HH_ph; i++) {
            fl=true;
            for (int j = 0; j < nfu_ph; j++) {
                if (Fuh[i][j] > Fu[j]) fl = false;
            }
            if (fl == true) {
                fl = false;
                for (int j = 0; j < nfu_ph; j++) {
                    if (Fuh[i][j] < Fu[j]) fl1 = true;
                }
                if (fl1 == true) r++;
            }
        }
        return r;
    }

    public int[][][] getPopParChrom() {
        return PopParChrom;
    }

    public int[] getZb() {
        return zb;
    }

    public int[] getParams() {
        return Params;
    }

    public int getL_chrom() {

        return l_chrom;
    }

    public void setGG_ph(int GG_ph) {
        this.GG_ph = GG_ph;
    }

    public void setRR_ph(int RR_ph) {
        this.RR_ph = RR_ph;
    }

    public void setPmut(double pmut) {
        this.pmut = pmut;
    }

    public void setGamma(double gamma) {
        this.gamma = gamma;
    }

    public void ChoosePareto(){
        int k = 0;
        for (int i = 0; i < HH_ph; i++) {
            if (RangPareto[i] == 0) {
                k++;
                Pareto = new int[k];
                Pareto[k] = Pareto[0];
            }
            if (k == 1) {
                Pareto = new int[k+1];
                Pareto[k] = Pareto[0];
            }
        }
    }
}
