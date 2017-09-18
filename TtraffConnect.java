import com.sun.javafx.scene.layout.region.Margins;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qual2 on 25.05.2017.
 */
public class TtraffConnect {

    private List<Ttraff> conCross; // List of crossroads, we need to connect
    private List<Integer[][]> conMatr; // List of matrixes of connection (R1...Rn)
    private List<Boolean> flags;

    public TtraffConnect(List<Ttraff> conCross, List<Integer[][]> conMatr) {
        this.conCross = conCross;
        this.conMatr = conMatr;
        flags = new ArrayList<>();
        for (int i = 0; i < this.conCross.size(); i++) flags.add(i, false);
    }

    public List<Ttraff> getConCross() {
        return conCross;
    }
    public List<Integer[][]> getConMatr() {
        return conMatr;
    }

    public void setConCross(List<Ttraff> conCross) {
        this.conCross = conCross;
    }
    public void setConMatr(List<Integer[][]> conMatr) {
        this.conMatr = conMatr;
    }

    public List<Double> ConnectingCrosses(){
        int new_length;
        int all_inout = 0;
        int alpha;
        int beta;
        double buf;
        List<Integer> alphas = new ArrayList<>();
        List<Integer> betas = new ArrayList<>();
        for (Ttraff ttraff : conCross) {
            all_inout += ttraff.getL();
        }
        //new_length = all_inout - 2*(conCross.size()-1);
        List<Double> new_x = new ArrayList<>();

        for (int l = 0; l < conCross.size(); l++) {
            for (int i = 0; i < conMatr.get(l).length; i++) {
                for (int j = 0; j < conMatr.get(l)[0].length; j++) {
                    if (conMatr.get(l)[i][j] != 0){
                        System.out.println("\n");
                        alpha = conCross.get(j).getI_in().get(conMatr.get(l)[i][j]-1);
                        beta = conCross.get(l).getI_out().get(i);
                        if (flags.get(l) == false){
                        buf = conCross.get(j).getX()[alpha] + conCross.get(l).getX()[beta];
                        conCross.get(j).getX()[alpha] = buf;
                        conCross.get(l).getX()[beta] = buf;
                            flags.set(l,true);}
                        else {
                            buf = conCross.get(l).getX()[beta] - conCross.get(j).getY()[alpha];
                            conCross.get(j).getX()[alpha] = buf;
                            conCross.get(l).getX()[beta] = buf;
                        }
                        //conCross.get(j).getX()[alpha] = conCross.get(l).getX()[beta];
                        alphas.add(alpha);
                        betas.add(beta);
                        System.out.println(alpha);
                        System.out.println(beta);
                        System.out.println(flags.get(l));
                        System.out.println("\n");
                    }
                }
            }
        }

        for (int l = 0; l < conCross.size(); l++) {
            for (int i = 0; i < conCross.get(l).getX().length; i++) {
                new_x.add(conCross.get(l).getX()[i]);
            }
        }


        return new_x;
    }
}
