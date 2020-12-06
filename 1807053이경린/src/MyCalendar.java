import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Calendar;
//18017053 이경린
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class MyCalendar {
   public static void main(String args[])throws IOException {
	   
      GUI gui = new GUI();
      
      
      gui.setVisible(true);      
   }
}

class GUI extends JFrame{
   private int width, height, x, y;
   private int year, month, week, lastDay;
   private String birthday;
   private Calendar cal;
   private GridBagConstraints layout;
   private ImageIcon icon[];
   private Dimension screen_size;
   private JLabel image, upBanner, downBanner;
   private datePanel datePan;
   private weekPanel weekPan;
   private monthPanel monthPan;
   
   public GUI() {
      super("Calender");
      screen_size = Toolkit.getDefaultToolkit().getScreenSize();
      width = 400;
      height = 560;
      x = (int)(screen_size.getWidth()/2 - (width/2));
      y = (int)(screen_size.getHeight()/2 - (height/2));
      
      layout = new GridBagConstraints();
      layout.fill = GridBagConstraints.BOTH;
      layout.weightx = 1.0;
      layout.weighty = 1.0;
      cal = Calendar.getInstance();
      
      year = cal.get(Calendar.YEAR);
      month = cal.get(Calendar.MONTH);
      week = cal.get(Calendar.DAY_OF_WEEK);
      
      birthday = "19991119";
      
      setBounds(x-500, y, width, height);
      getContentPane().setBackground(Color.white);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLayout(new GridBagLayout());
      
      icon = new ImageIcon[12];
      for(int i = 0; i < 12; i++) {
         icon[i] = new ImageIcon("src/image/" + (i+1) + "월.jpg");
      }
      
      upBanner = new JLabel(" ");
      upBanner.setHorizontalAlignment(JLabel.CENTER);
      upBanner.setFont(upBanner.getFont().deriveFont(20.0f));
      layout.weighty = 0.05;
      layout.gridx = 0;
      layout.gridy = 0;
      layout.gridwidth = 1;
      layout.gridheight = 1;
      add(upBanner, layout);
      
      image = new JLabel(icon[month]);
      layout.weighty = 0.35;
      layout.gridx = 0;
      layout.gridy = 1;
      layout.gridwidth = 1;
      layout.gridheight = 1;
      add(image, layout);
      
      monthPan = new monthPanel();
      layout.weighty = 0.05;
      layout.gridx = 0;
      layout.gridy = 2;
      layout.gridwidth = 1;
      layout.gridheight = 1;
      add(monthPan, layout);
      
      weekPan = new weekPanel();
      layout.weighty = 0.1;
      layout.gridx = 0;
      layout.gridy = 3;
      layout.gridwidth = 1;
      layout.gridheight = 1;
      add(weekPan, layout);
      
      datePan = new datePanel();
      layout.weighty = 0.4;
      layout.gridx = 0;
      layout.gridy = 4;
      layout.gridwidth = 1;
      layout.gridheight = 1;
      add (datePan, layout);
      
      datePan.setCalendar(); 
      
      downBanner = new JLabel(" ");
      downBanner.setHorizontalAlignment(JLabel.CENTER);
      downBanner.setFont(downBanner.getFont().deriveFont(15.0f));
      layout.weighty = 0.1;
      layout.gridx = 0;
      layout.gridy = 5;
      layout.gridwidth = 1;
      layout.gridheight = 1;
      add(downBanner, layout);
   }
   
   class monthPanel extends JPanel implements ActionListener{
      private JButton back, next;
      private JLabel selectedDate;
      public monthPanel() {
         setLayout(new FlowLayout());
         setBackground(Color.white);
         
         back = new JButton("◀");
         back.setBackground(Color.white);
         back.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 255))));
         back.setFont(back.getFont().deriveFont(15.0f));
         back.addActionListener(this);
         add(back);
         
         selectedDate = new JLabel(year + "년" + (month+1)+"월");
         selectedDate.setFont(selectedDate.getFont().deriveFont(20.0f));         
         add(selectedDate);
         
         next = new JButton("▶");
         next.setBackground(Color.white);
         next.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 255))));
         next.setFont(back.getFont().deriveFont(15.0f));
         next.addActionListener(this);
         add(next);
      }
      
      public String getDate() {
         return selectedDate.getText();
      }

      @Override
      public void actionPerformed(ActionEvent e) {
         String str = e.getActionCommand();
         
         if(str.equals("◀")){
            if(month == 0) {
               month = 11;
               year--;
            } else
               month--;
         } else if (str.equals("▶")) {
            if(month == 11) {
               month = 0;
               year++;
            } else
               month++;
         }
         
         datePan.init();
         
         image.setIcon(icon[month]);
         selectedDate.setText(year + "년" + (month+1)+"월");
         datePan.setCalendar(); 
      }
   }
   
   class weekPanel extends JPanel {
      private String str_week[];
      private JLabel week[];
      
      public weekPanel() {
         setLayout(new GridLayout(1, 7));
         setBackground(Color.white);
         
         str_week = new String[] {"일", "월", "화", "수", "목", "금", "토"};
         week = new JLabel[7];
         for(int i = 0; i < 7; i++) {
            week[i] = new JLabel(str_week[i]);
            week[i].setForeground(Color.white);
            week[i].setHorizontalAlignment(JLabel.CENTER);
            if(i == 0 )
               week[i].setForeground(Color.RED);
            else if (i == 6)
               week[i].setForeground(Color.BLUE);
            else
               week[i].setForeground(Color.GRAY);
            add(week[i]);
         }
      }
   }
   
   class datePanel extends JPanel implements ActionListener, MouseListener{
      private JButton date[];
      public datePanel () {
         setLayout(new GridLayout(6, 7));
         
         date = new JButton[42];
         for(int i = 0; i < 42; i++) {
            date[i] = new JButton(" ");
            date[i].setBackground(Color.white);
            date[i].setFont(date[i].getFont().deriveFont(18.0f));
            date[i].setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 255))));
            date[i].addActionListener(this);
            date[i].addMouseListener(this);
            if(i == 0 || i%7 == 0)
               date[i].setForeground(Color.red);
            else if (i == 6 || i == 13 || i == 20 || i == 27 ||
                  i == 34 || i == 41) {
            
               date[i].setForeground(Color.blue);
            }
            else
               date[i].setForeground(Color.black);
            add(date[i]);
         }
      }
      
      public void init() {
         for(int i = 0; i < 42; i++) {
            date[i].setText(" ");
         }
      }
      
      public void setCalendar() {
         int getYear, getMonth;
         
         Calendar start = Calendar.getInstance();
         Calendar end = Calendar.getInstance();
         
         getYear = year;
         getMonth = month;
         
         start.set(getYear, getMonth, 1);
         end.set(getYear, getMonth+1, 1);
         end.add(Calendar.DATE, -1);
         
         week = start.get(Calendar.DAY_OF_WEEK);
         lastDay = end.get(Calendar.DATE);
         
         for(int i = 0; i < lastDay; i++) {
            date[(i + week-1)].setText((i+1)+"");
         }
      }

      @Override
      public void actionPerformed(ActionEvent e) {
         String event = e.getActionCommand();
         
         if(event.equals(" ")) {
            return;
         }
         
         Calendar sc = Calendar.getInstance();
         Calendar ec = Calendar.getInstance();
         
         sc.set(1999, 11, 19);
         ec.set(year, (month+1), Integer.parseInt(event));
         
         long d1 = sc.getTime().getTime();
         long d2 = ec.getTime().getTime();
         
         int days = (int)((d2-d1)/(1000*60*60*24));
         
         downBanner.setText("내가 태어난지 " + days + "일 차입니다.");
      }

      @Override
      public void mouseClicked(MouseEvent e) {}

      @Override
      public void mousePressed(MouseEvent e) {}

      @Override
      public void mouseReleased(MouseEvent e) {}

      @Override
      public void mouseEntered(MouseEvent e) {
         JButton b = (JButton)e.getSource();
         if(b.getText().equals(" ")) {
            return;
         }
         
         int event = Integer.parseInt(b.getText());
         int cnt = week-1, idx = 1;
         String w = "";
         
         for(int i = 0; i < event; i++ ) {
            if(cnt == 7) 
               cnt = 1;
            else 
               cnt ++;
         } // 요일 구함
         
         switch (cnt) {
         case 1:
            w = "일";
            break;
         case 2:
            w = "월";
            break;
         case 3:
            w = "화";
            break;
         case 4:
            w = "수";
            break;
         case 5:
            w = "목";
            break;
         case 6:
            w = "금";
            break;
         case 7:
            w = "토";
            break;
         }
         
         upBanner.setText(year + "년 " + (month+1) + "월 " + event + "일 " + w + "요일입니다.");
         
      }

      
      
      @Override
      public void mouseExited(MouseEvent e) {
         upBanner.setText(" ");
         
      }
   }
}

