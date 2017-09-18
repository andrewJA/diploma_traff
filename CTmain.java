import com.sun.javafx.scene.layout.region.Margins;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.ProgressBarUI;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by qual2 on 02.05.2017.
 */
public class CTmain {
    private MyJButton btnModel;
    private MyJButton btnOptim;
    private MyJPanel panel1;
    private MyJPanel panel2;
    private MyJPanel panel3;
    private MyJFrame frame;
    private JTable tableu1;
    private JTable tableu2;
    private JTable tableu3;
    private JTable tablex1;
    private JTable tablex2;
    private JTable tablex3;
    private JTable tablexk1;
    private JTable tablexk2;
    private JTable tablexk3;
    private JTable tablexint1;
    private JTable tablexint2;
    private JTable tablexint3;

    private Data_tr dt = new Data_tr();
    JScrollPane jscrlpu1;
    JScrollPane jscrlpu2;
    JScrollPane jscrlpu3;
    JScrollPane jscrlpx1;
    JScrollPane jscrlpx2;
    JScrollPane jscrlpx3;
    JScrollPane jscrlpxk1;
    JScrollPane jscrlpxk2;
    JScrollPane jscrlpxk3;
    JScrollPane jscrlpxint1;
    JScrollPane jscrlpxint2;
    JScrollPane jscrlpxint3;
    TOptimtraff EA;
    Ttraff traff,traff2,traff3;
    TtraffConnect new_connect;
    List<Integer[][]> matr_cTC;
    List<Ttraff> conTwoCross;

    private int u_p;

    public CTmain() {
        /*traff = new Ttraff(dt.L_cn,dt.M_cn,dt.N_cn);
        traff.Setx0(dt.x0_cn);
        EA = new TOptimtraff(dt.L_cn,dt.M_cn,dt.N_cn);
        EA.setTraff(traff);
        EA.getTraff().setU(dt.u_cn);
        EA.getTraff().setX(dt.x0_cn);
        EA.getTraff().setXint(dt.xint_cn);
        EA.getTraff().setXmax(dt.xp_cn);
        EA.getTraff().setUmax(dt.up_cn);
        EA.getTraff().setA(dt.A_cn);
        EA.getTraff().setI_in(dt.I_0_cn);
        EA.getTraff().setI_out(dt.I_1_cn);
        EA.getTraff().setF(dt.F_cn);
        EA.getTraff().SetAin();
        EA.getTraff().SetAout();
        EA.getTraff().setB(dt.B_cn);
        EA.getTraff().setC(dt.C_cn);
        EA.getTraff().setD(dt.D_cn);
        EA.getTraff().setPhases_cur(dt.Phases_cn);
        EA.getTraff().setPhases_m(dt.Phases_m_cn);
        EA.getTraff().setPhases_p(dt.Phases_p_cn);
        u_p = 0;
        for (int i = 0; i < EA.getTraff().getM(); i++) if (dt.up_cn[i] > u_p) u_p = dt.up_cn[i];
        EA.getTraff().setMaxphase(u_p);*/
        traff = new Ttraff(dt.Ltest,dt.Mtest,dt.Ntest);
        traff2 = new Ttraff(dt.Ltest2,dt.Mtest2,dt.Ntest2);
        traff3 = new Ttraff(dt.Ltest3,dt.Mtest3,dt.Ntest3);
        EA = new TOptimtraff(dt.Ltest,dt.Mtest,dt.Ntest);
        traff.setX0(dt.xtest);
        traff.setX(dt.xtest);
        traff.setU(dt.utest);
        traff.setXint(dt.xtest_int);
        traff.setA(dt.Atest);
        traff.setUmax(dt.umaxtest);
        traff.setXmax(dt.xmaxtest);
        traff.setI_in(dt.I0test);
        traff.setI_out(dt.I1test);
        traff.setF(dt.Ftest);
        traff.setAin();
        traff.setAout();
        traff.setB(dt.Btest);
        traff.setC(dt.Ctest);
        traff.setD(dt.Dtest);

        traff2.setX0(dt.xtest2);
        traff2.setX(dt.xtest2);
        traff2.setU(dt.utest2);
        traff2.setXint(dt.xtest_int2);
        traff2.setA(dt.Atest2);
        traff2.setUmax(dt.umaxtest2);
        traff2.setXmax(dt.xmaxtest2);
        traff2.setI_in(dt.I0test2);
        traff2.setI_out(dt.I1test2);
        traff2.setF(dt.Ftest2);
        traff2.setAin();
        traff2.setAout();
        traff2.setB(dt.Btest2);
        traff2.setC(dt.Ctest2);
        traff2.setD(dt.Dtest2);
        traff2.setPhases_cur(dt.Phases_cn);
        traff2.setPhases_m(dt.Phases_m_cn);
        traff2.setPhases_p(dt.Phases_p_cn);

        traff3.setX0(dt.xtest3);
        traff3.setX(dt.xtest3);
        traff3.setU(dt.utest3);
        traff3.setXint(dt.xtest_int3);
        traff3.setA(dt.Atest3);
        traff3.setUmax(dt.umaxtest3);
        traff3.setXmax(dt.xmaxtest3);
        traff3.setI_in(dt.I0test3);
        traff3.setI_out(dt.I1test3);
        traff3.setF(dt.Ftest3);
        traff3.setAin();
        traff3.setAout();
        traff3.setB(dt.Btest3);
        traff3.setC(dt.Ctest3);
        traff3.setD(dt.Dtest3);
        traff3.setPhases_cur(dt.Phases_cn);
        traff3.setPhases_m(dt.Phases_m_cn);
        traff3.setPhases_p(dt.Phases_p_cn);

        EA.setTraff(traff);
        EA.getTraff().setPhases_cur(dt.Phases_cn);
        EA.getTraff().setPhases_m(dt.Phases_m_cn);
        EA.getTraff().setPhases_p(dt.Phases_p_cn);

        conTwoCross = new ArrayList<>();
        matr_cTC = new ArrayList<>();
        Collections.addAll(conTwoCross, traff2, traff);
        Collections.addAll(matr_cTC, dt.R2, dt.R1);
        new_connect = new TtraffConnect(conTwoCross,matr_cTC);


        //new_connect.ConnectingCrosses();

    }


    public static void main(String[] args) {


        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException var2) {
            Logger.getLogger(CTmain.class.getName()).log(Level.SEVERE, (String) null, var2);
        }

        JFrame.setDefaultLookAndFeelDecorated(true);
        CTmain testGUI = new CTmain();

        testGUI.createButtons();
        testGUI.createTables();
        testGUI.createPanels();
        testGUI.createFrame();
    }

    private void createTables() {


        DefaultTableModel tableModel = new DefaultTableModel();
        Object[] xtest1 = {"x_test1"};
        tableModel.setColumnIdentifiers(xtest1);
        tableModel.setColumnCount(1);
        tableModel.setRowCount(dt.xtest.length);
        tablex1 = new JTable(tableModel);

        DefaultTableModel tableModel2 = new DefaultTableModel();
        Object[] xtest2 = {"x_test2"};
        tableModel2.setColumnIdentifiers(xtest2);
        tableModel2.setColumnCount(1);
        tableModel2.setRowCount(dt.xtest2.length);
        tablex2 = new JTable(tableModel2);

        DefaultTableModel tableModel3 = new DefaultTableModel();
        Object[] xtest3 = {"x_test3"};
        tableModel3.setColumnIdentifiers(xtest3);
        tableModel3.setColumnCount(1);
        tableModel3.setRowCount(dt.xtest3.length);
        tablex3 = new JTable(tableModel3);

        DefaultTableModel  tableModelint1= new DefaultTableModel();
        Object[] xint1 = {"x_int_test1"};
        tableModelint1.setColumnIdentifiers(xint1);
        tableModelint1.setColumnCount(1);
        tableModelint1.setRowCount(dt.xtest_int.length);
        tablexint1 = new JTable(tableModelint1);

        DefaultTableModel tableModeint2 = new DefaultTableModel();
        Object[] xint2 = {"x_int_test2"};
        tableModeint2.setColumnIdentifiers(xint2);
        tableModeint2.setColumnCount(1);
        tableModeint2.setRowCount(dt.xtest_int2.length);
        tablexint2 = new JTable(tableModeint2);

        DefaultTableModel tableModeint3 = new DefaultTableModel();
        Object[] xint3 = {"x_int_test3"};
        tableModeint3.setColumnIdentifiers(xint3);
        tableModeint3.setColumnCount(1);
        tableModeint3.setRowCount(dt.xtest_int3.length);
        tablexint3 = new JTable(tableModeint3);

        DefaultTableModel tableModelu1 = new DefaultTableModel();
        Object[] xu1 = {"u_test1"};
        tableModelu1.setColumnIdentifiers(xu1);
        tableModelu1.setColumnCount(1);
        tableModelu1.setRowCount(dt.utest.length);
        tableu1 = new JTable(tableModelu1);

        DefaultTableModel tableModelu2 = new DefaultTableModel();
        Object[] xu2 = {"u_test2"};
        tableModelu2.setColumnIdentifiers(xu2);
        tableModelu2.setColumnCount(1);
        tableModelu2.setRowCount(dt.utest2.length);
        tableu2 = new JTable(tableModelu2);

        DefaultTableModel tableModelu3 = new DefaultTableModel();
        Object[] xu3 = {"u_test3"};
        tableModelu3.setColumnIdentifiers(xu3);
        tableModelu3.setColumnCount(1);
        tableModelu3.setRowCount(dt.utest3.length);
        tableu3 = new JTable(tableModelu3);

        DefaultTableModel tableModexk1 = new DefaultTableModel();
        Object[] xk1 = {"x[k+1]_1"};
        tableModexk1.setColumnIdentifiers(xk1);
        tableModexk1.setColumnCount(1);
        tableModexk1.setRowCount(dt.xtest.length);
        tablexk1 = new JTable(tableModexk1);

        DefaultTableModel tableModexk2 = new DefaultTableModel();
        Object[] xk2 = {"x[k+1]_2"};
        tableModexk2.setColumnIdentifiers(xk2);
        tableModexk2.setColumnCount(1);
        tableModexk2.setRowCount(dt.xtest2.length);
        tablexk2 = new JTable(tableModexk2);

        DefaultTableModel tableModexk3 = new DefaultTableModel();
        Object[] xk3 = {"x[k+1]_3"};
        tableModexk3.setColumnIdentifiers(xk3);
        tableModexk3.setColumnCount(1);
        tableModexk3.setRowCount(dt.xtest3.length);
        tablexk3 = new JTable(tableModexk3);



        for (int i = 0; i < dt.xtest.length; i++) {
            tablex1.setValueAt(dt.xtest[i], i, 0);
        }
        for (int i = 0; i < dt.xtest2.length; i++) {
            tablex2.setValueAt(dt.xtest2[i], i, 0);
        }
        for (int i = 0; i < dt.xtest3.length; i++) {
            tablex3.setValueAt(dt.xtest3[i], i, 0);
        }
        for (int i = 0; i < dt.xtest_int.length; i++) {
            tablexint1.setValueAt(dt.xtest_int[i], i, 0);
        }
        for (int i = 0; i < dt.xtest_int2.length; i++) {
            tablexint2.setValueAt(dt.xtest_int2[i], i, 0);
        }
        for (int i = 0; i < dt.xtest_int3.length; i++) {
            tablexint3.setValueAt(dt.xtest_int3[i], i, 0);
        }
        for (int i = 0; i < dt.utest.length; i++) {
            tableu1.setValueAt(dt.utest[i], i, 0);
        }
        for (int i = 0; i < dt.utest2.length; i++) {
            tableu2.setValueAt(dt.utest2[i], i, 0);
        }
        for (int i = 0; i < dt.utest3.length; i++) {
            tableu3.setValueAt(dt.utest3[i], i, 0);
        }



        jscrlpx1 = new JScrollPane(tablex1);
        jscrlpx2 = new JScrollPane(tablex2);
        jscrlpx3 = new JScrollPane(tablex3);
        jscrlpu1 = new JScrollPane(tableu1);
        jscrlpu2 = new JScrollPane(tableu2);
        jscrlpu3 = new JScrollPane(tableu3);
        jscrlpxk1 = new JScrollPane(tablexk1);
        jscrlpxk2 = new JScrollPane(tablexk2);
        jscrlpxk3 = new JScrollPane(tablexk3);
        jscrlpxint1 = new JScrollPane(tablexint1);
        jscrlpxint2 = new JScrollPane(tablexint2);
        jscrlpxint3 = new JScrollPane(tablexint3);
        tablex1.setPreferredScrollableViewportSize(new Dimension(50, 300));
        tablex2.setPreferredScrollableViewportSize(new Dimension(50, 300));
        tablex3.setPreferredScrollableViewportSize(new Dimension(50, 300));
        tableu1.setPreferredScrollableViewportSize(new Dimension(50, 300));
        tableu2.setPreferredScrollableViewportSize(new Dimension(50, 300));
        tableu3.setPreferredScrollableViewportSize(new Dimension(50, 300));
        tablexk1.setPreferredScrollableViewportSize(new Dimension(50, 300));
        tablexk2.setPreferredScrollableViewportSize(new Dimension(50, 300));
        tablexk3.setPreferredScrollableViewportSize(new Dimension(50, 300));
        tablexint1.setPreferredScrollableViewportSize(new Dimension(50, 300));
        tablexint2.setPreferredScrollableViewportSize(new Dimension(50, 300));
        tablexint3.setPreferredScrollableViewportSize(new Dimension(50, 300));

    }
    private void createButtons() {
        this.btnModel = new MyJButton("Model");
        this.btnOptim = new MyJButton("Optim");
        this.addButtonListeners();
    }

    private void addButtonListeners() {

        this.btnModel.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                int delt = 1;
                int steps = 10;
                for (int j = 0; j < steps; j++)
                {
                EA.setTraff(traff);
                for (int i = 0; i < EA.getTraff().getX().length; i++) tablex1.setValueAt(EA.getTraff().getX()[i], i, 0);
                DoModeling(delt);
                EA.setTraff(traff2);
                for (int i = 0; i < EA.getTraff().getX().length; i++) tablex2.setValueAt(EA.getTraff().getX()[i], i, 0);
                DoModeling(delt);
                new_connect.ConnectingCrosses();
                EA.setTraff(traff);
                for (int i = 0; i < EA.getTraff().getX().length; i++) tablexk1.setValueAt(EA.getTraff().getX()[i], i, 0);
                for (int i = 0; i < EA.getTraff().getU().length; i++) tableu1.setValueAt(EA.getTraff().getU()[i], i, 0);
                for (int i = 0; i < EA.getTraff().getX().length; i++) tablexint1.setValueAt(EA.getTraff().getXint()[i], i, 0);
                EA.setTraff(traff2);
                for (int i = 0; i < EA.getTraff().getX().length; i++) tablexk2.setValueAt(EA.getTraff().getX()[i], i, 0);
                for (int i = 0; i < EA.getTraff().getU().length; i++) tableu2.setValueAt(EA.getTraff().getU()[i], i, 0);
                for (int i = 0; i < EA.getTraff().getX().length; i++) tablexint2.setValueAt(EA.getTraff().getXint()[i], i, 0);


                EA.setTraff(traff3);
                for (int i = 0; i < EA.getTraff().getX().length; i++) tablex3.setValueAt(EA.getTraff().getX()[i], i, 0);
                DoModeling(delt);
                for (int i = 0; i < EA.getTraff().getX().length; i++) tablexk3.setValueAt(EA.getTraff().getX()[i], i, 0);
                for (int i = 0; i < EA.getTraff().getU().length; i++) tableu3.setValueAt(EA.getTraff().getU()[i], i, 0);
                for (int i = 0; i < EA.getTraff().getX().length; i++) tablexint3.setValueAt(EA.getTraff().getXint()[i], i, 0);
                }
                JOptionPane.showMessageDialog(btnModel, "Modeled with steps = "+steps);
                super.mouseClicked(e);



            }
        });
        this.btnOptim.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TGATraff gatraff = new TGATraff(dt.HH, dt.nfu,dt.ncb,dt.l_chrom1);

                //progressbar
                EA.setGAtraff(gatraff);
                EA.getTraff().setPhases_ind_contr(dt.Phases_ind_contr_cn);
                EA.CreateGATraff();
                //progressbar
                EA.getGAtraff().setPmut(dt.pmut);
                EA.getGAtraff().setRR_ph(dt.RR);
                EA.getGAtraff().setGG_ph(dt.GG);
                EA.getGAtraff().setGamma(dt.gamma);
                EA.getGAtraff().GAforPhases();
                //progressbar
                JOptionPane.showMessageDialog(btnOptim, "Optimized");
                super.mouseClicked(e);
            }
        });

    }

    public void DoModeling(int delt){
        int[] kituu = EA.getTraff().getKitu();
        int[] uu = EA.getTraff().getU();
        EA.getTraff().CheckOverFlow_1();
        for (int i = 0; i < delt; i++) {
            EA.getTraff().setKit(EA.getTraff().getKit()+1);
            EA.getTraff().ModelFaster();
            for (int j = 0; j < EA.getTraff().getU().length; j++) {
                kituu[j]++;
                for (int k = 0; k <= EA.getTraff().getUmax()[j]; k++) {
                    if (kituu[j]>=EA.getTraff().getPhases()[j][k]){
                        uu[j] = (uu[j]+1)%(EA.getTraff().getUmax()[j]+1);
                        kituu[j] = 0;
                    }
                }
            }
        }
        EA.getTraff().setKitu(kituu);
        EA.getTraff().setU(uu);
    }

    private void createPanels() {
        this.panel1 = new MyJPanel("panel1", 100, 100);
        this.panel1.setPreferredSize(new Dimension(100, 50));
        this.panel1.setLayout(new FlowLayout(0));
        this.panel2 = new MyJPanel("panel2", 100, 100);
        this.panel2.setPreferredSize(new Dimension(500, 50));
        this.panel2.setLayout(new FlowLayout(0));
        this.panel3 = new MyJPanel("panel3", 100, 100);
        this.panel3.setPreferredSize(new Dimension(300, 50));
        this.panel3.setLayout(new FlowLayout(0));

        this.panel1.add(this.btnModel);

        this.panel1.add(this.btnOptim);
        this.panel2.add(this.jscrlpu1);
        this.panel2.add(this.jscrlpxint1);
        this.panel2.add(this.jscrlpx1);
        this.panel2.add(this.jscrlpxk1);
        this.panel2.add(this.jscrlpu2);
        this.panel2.add(this.jscrlpxint2);
        this.panel2.add(this.jscrlpx2);
        this.panel2.add(this.jscrlpxk2);
        this.panel3.add(this.jscrlpu3);
        this.panel3.add(this.jscrlpxint3);
        this.panel3.add(this.jscrlpx3);
        this.panel3.add(this.jscrlpxk3);
    }

    private void createFrame() {
        this.frame = new MyJFrame("TRaff Controll", 900, 600, new BorderLayout(2, 2));
        this.frame.setMinimumSize(new Dimension(430, 200));
        this.frame.setResizable(false);
        this.frame.getContentPane().add(this.panel1, "West");
        this.frame.getContentPane().add(this.panel2, "Center");
        this.frame.getContentPane().add(this.panel3, "East");

        this.frame.setVisible(true);
    }

}
