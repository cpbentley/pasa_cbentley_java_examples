package pasa.cbentley.java.examples.swing.anim;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.Timer;

public class DemoJLayerAnim {
   
   
   public static void main(String[] args) {
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            new DemoJLayerAnim().createUI();
         }
      });
   }

   private JButton mOrderButton;

   public void createUI() {
      JFrame f = new JFrame("TapTapTap");

      final LayerUIWait layerUI = new LayerUIWait();
      JPanel panel = createPanel();
      JLayer<JPanel> jlayer = new JLayer<JPanel>(panel, layerUI);

      final Timer stopper = new Timer(4000, new ActionListener() {
         public void actionPerformed(ActionEvent ae) {
            layerUI.stop();
         }
      });
      stopper.setRepeats(false);

      mOrderButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent ae) {
            layerUI.start();
            if (!stopper.isRunning()) {
               stopper.start();
            }
         }
      });

      f.add(jlayer);

      f.setSize(300, 200);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setLocationRelativeTo(null);
      f.setVisible(true);
   }

   private JPanel createPanel() {
      JPanel p = new JPanel();

      ButtonGroup entreeGroup = new ButtonGroup();
      JRadioButton radioButton;
      p.add(radioButton = new JRadioButton("Beef", true));
      entreeGroup.add(radioButton);
      p.add(radioButton = new JRadioButton("Chicken"));
      entreeGroup.add(radioButton);
      p.add(radioButton = new JRadioButton("Vegetable"));
      entreeGroup.add(radioButton);

      p.add(new JCheckBox("Ketchup"));
      p.add(new JCheckBox("Mustard"));
      p.add(new JCheckBox("Pickles"));

      p.add(new JLabel("Special requests:"));
      p.add(new JTextField(20));

      mOrderButton = new JButton("Place Order");
      p.add(mOrderButton);

      return p;
   }
}