import javax.swing.*;
import java.awt.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Created by luisa on 7/04/18.
 */
public class showP {
    private JPanel Proyecto;
    private JTextArea textS;
    private JTextArea textH;
    private JScrollPane scrS;
    private JScrollPane scrH;
    private JLabel spam;
    private JLabel ham;
    private JButton cargar ;
    private JButton entrenar;
    private JLabel espacio1;
    private JLabel espacio2;
    private JLabel espacio3;
    private JFrame thisWindow ;

    public showP() throws IOException {
        thisWindow = new JFrame("Proyecto2");
        thisWindow.setContentPane(Proyecto);
        thisWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        thisWindow.pack();
        thisWindow.setSize(1250,800);
        thisWindow.setVisible(true);
        Training read = new Training();

        //File workingDirectory = new File(System.getProperty("HamSpam.dir"));
       // System.out.println(workingDirectory.getPath().toString());
        final JFileChooser jfc = new JFileChooser();
        String[] pathC = {""};
        final int[] count = {0};
        final String[] spam = {""};
        final String[] ham = {""};
        entrenar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (jfc.showOpenDialog(thisWindow) !=JFileChooser.APPROVE_OPTION)

                    return;
                File f = jfc.getSelectedFile();
                pathC[0] = f.getPath().toString();
                try {
                    read.Read(pathC[0]);
                    read.Reading(0.6);
                    read.trainHam();
                    read.trainSpam();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        cargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Classifier classi = new Classifier();
                final Hashtable<String, List<String>>[] classifier = new Hashtable[]{new Hashtable<String, List<String>>()};
                textS.setText("");
                spam[0] = "";
                textH.setText("");
                ham[0] = "";
                if (jfc.showOpenDialog(thisWindow) !=JFileChooser.APPROVE_OPTION)

                    return;
                File f = jfc.getSelectedFile();
                pathC[0] = f.getPath().toString();
                System.out.println(f.getPath().toString());
                try {
                    read.Read(pathC[0]);
                    classifier[0] =  classi.classifying(read,read.getClas());
                    classi.generate(classifier[0]);
                    for(String sentence:classi.spam)spam[0] = spam[0] + "> "+sentence+"\n";
                    for(String sentence:classi.ham) ham[0] = ham[0] + ">"+sentence+"\n";

                    textS.setText(spam[0]);
                    textH.setText(ham[0]);

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });





    }

}
