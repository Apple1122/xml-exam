package mid_stu;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.FontUIResource;

/**
 *
 * @author Jason
 */
public class GUI  extends JFrame implements ActionListener, ListSelectionListener{
    private CarsXmlRW carsXmlRW;
    FlowLayout flow;
    private JPanel northPanel;
    private JPanel centerPanel;
    private JPanel southPanel;
    private List<Car> carList;
    private DefaultListModel<String> listModel;
    private JList<String> jList;
    private JScrollPane leftScrollPane;
    private JPanel rightPanel;
    private JLabel title;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel priceLabel;
    private JTextField priceField;
    private JRadioButton rBtn1;
    private JRadioButton rBtn2;
    private ButtonGroup bg;
    private JButton addBtn;
    private JButton modBtn;
    private JButton delBtn;
    private JButton savBtn;
    
    public GUI(){
        super("車市資訊管理後台");
        setUIFont(new FontUIResource("微軟正黑體",Font.PLAIN,20));

        carsXmlRW = new CarsXmlRW();
        flow = new FlowLayout();
        northPanel = new JPanel(flow);
        centerPanel = new JPanel(flow);
        southPanel = new JPanel(flow);
        carList = carsXmlRW.read();
        listModel = new DefaultListModel<>();
        for(Car c: carList){
            listModel.addElement(c.getName());
        }
        jList = new JList<>(listModel); //改成 listModel
        jList.setVisibleRowCount(12);
        jList.addListSelectionListener(this);
        leftScrollPane = new JScrollPane(jList);
        rightPanel = new JPanel(new GridLayout(7, 1, 5, 5));
        title = new JLabel("車市資訊管理後台");
        nameLabel = new JLabel("車款名稱");
        nameField = new JTextField(10);
        priceLabel = new JLabel("車款價格");
        priceField = new JTextField(10);
        rBtn1 = new JRadioButton("原裝進口");
        rBtn2 = new JRadioButton("國內生產");
        bg = new ButtonGroup();
        addBtn = new JButton("新增");
        addBtn.addActionListener(this);
        modBtn = new JButton("修改");
        modBtn.addActionListener(this);
        delBtn = new JButton("刪除");
        delBtn.addActionListener(this);
        savBtn = new JButton("儲存");
        savBtn.addActionListener(this);

        initLayout();
        setBounds(0, 0, 500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    private void initLayout(){
        getContentPane().setLayout(new BorderLayout());
        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
        centerPanel.add(leftScrollPane);
        centerPanel.add(rightPanel);
        northPanel.add(title);
        rightPanel.add(nameLabel);
        rightPanel.add(nameField);
        rightPanel.add(priceLabel);
        rightPanel.add(priceField);
        rightPanel.add(rBtn1);
        rightPanel.add(rBtn2);
        bg.add(rBtn1);
        bg.add(rBtn2);
        southPanel.add(addBtn);
        southPanel.add(modBtn);
        southPanel.add(delBtn);
        southPanel.add(savBtn);
    }
    private void setUIFont (FontUIResource fui){
        Enumeration keys=UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key=keys.nextElement();
            Object value=UIManager.get(key);
            if (value != null && value instanceof FontUIResource) {
                UIManager.put(key, fui);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int i;
        Car c;
        switch(e.getActionCommand()){
                case "新增":
                    c = new Car("new",0,true);
                    carList.add(c);
                    listModel.addElement(c.getName());
                    break;
                case "修改":
                    i = jList.getSelectedIndex();
                    c = carList.get(i);
                    c.setName(nameField.getText());
                    c.setPrice(Integer.parseInt(priceField.getText()));
                    if(rBtn1.isSelected()) c.setImported(true);
                    else c.setImported(false);
                    listModel.set(i, c.getName());
                    break;
                case "刪除":
                    i = jList.getSelectedIndex();
                    carList.remove(i);
                    listModel.remove(i);
                    break;
                case "儲存":
                    carsXmlRW.write(carList);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(e.getValueIsAdjusting()){
            Car c = carList.get(jList.getSelectedIndex());
            nameField.setText(c.getName());
            priceField.setText(String.valueOf(c.getPrice()));
            if(c.isImported()) rBtn1.setSelected(true);
            else rBtn2.setSelected(true);
        }
    }
}
