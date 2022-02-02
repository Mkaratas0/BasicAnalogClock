import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.time.temporal.TemporalField;


import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Clock  extends Canvas {

    public static void main(String[] args) { new Clock();
    }
    int N=12;
    int []xloc=new int[N];
    int []yloc=new int[N];
    int []secxloc=new int[60];
    int []secyloc=new int[60];

    public Clock() {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                JFrame frame = new JFrame("Mustafa's Time");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new ClockFace());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class ClockFace extends JPanel {


        public ClockFace() {
            this.startClock();
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(600   , 600);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();

            for(int i=0;i<N;i++){
                xloc[i]=300+((int)(230*Math.cos(-Math.toRadians(360/N)*i+Math.PI/2)));
                yloc[i]=300-((int)(230*Math.sin(-Math.toRadians(360/N)*i+Math.PI/2)));
            }
            g.setFont(new Font("arista",Font.BOLD, 36));
            g.setColor(Color.red);

            LocalTime now = LocalTime.now();
            int seconds = now.getSecond();
            int minutes = now.getMinute();
            int hours = now.getHour();


            g2d.setColor(Color.black);
            g2d.fillRect(0, 0, 600, 600);
            g2d.setColor(Color.yellow);
            g2d.translate(300, 300);
            g.drawString(12+"", xloc[0], yloc[0]);
            for(int i=1;i<N;i++)
                g.drawString(i+"", xloc[i], yloc[i]);


            for (int i = 0; i < 12; i++) {

                g2d.drawLine(0, -260, 0, -300);
                g2d.rotate(Math.PI / 6);

            }

            g2d.rotate(seconds * Math.PI / 30);
            g2d.drawLine(0, 0, 0, -290);

            g2d.rotate(2 * Math.PI - seconds * Math.PI / 30);
            g2d.rotate(minutes * Math.PI / 30);
            g2d.setStroke(new BasicStroke(3));
            g2d.drawLine(0, 0, 0, -250);

            g2d.rotate(2 * Math.PI - minutes * Math.PI / 30);
            g2d.rotate(hours * Math.PI / 6);
            g2d.setStroke(new BasicStroke(6));
            g2d.drawLine(0, 0, 0, -200);

            g2d.dispose();
        }

        public void startClock() {
            Timer timer = new Timer(500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    repaint();
                }
            });
            timer.start();
        }

    }

}