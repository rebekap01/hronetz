
// hronetz car project
//
// members and roles:
//
//      Jakub Malý              backend, finalisation, UML diagram
//      Tomáš Červenka          loading data from database
//      Rebeka Prívozníková     user interface, images
//      Arkadiy Deliev          idea (mostly absent)

package Group;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

class hronetz extends JFrame implements ActionListener {

    Vector<choice> modelTypes;
    Vector<battery> batteryTypes;
    Vector<engine> engineTypes;
    Vector<paint> paintTypes;
    Vector<wheels> wheelTypes;
    Vector<interior> interiorTypes;
    Vector<String> modelNames, batteryNames, engineNames, paintNames, wheelNames, interiorNames;

    String path;

    JPanel North;
    JPanel South;
    JPanel Center;
    JButton submit;
    JComboBox modelbox, batterybox, enginebox, paintbox, wheelbox, interiorbox;
    int model;

    hronetz(String path) {
        super("GUI");
        
        modelTypes = new Vector<choice>();
        batteryTypes = new Vector<battery>();
        engineTypes = new Vector<engine>();
        paintTypes = new Vector<paint>();
        wheelTypes = new Vector<wheels>();
        interiorTypes = new Vector<interior>();
        this.path = path;

         modelNames = new Vector<String>();
        for (int i=0; i<modelTypes.size() ; i++) {
            modelNames.add(modelTypes.elementAt(i).name);
        }

        this.setSize(1800, 1000);
        this.getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        modelbox = new JComboBox(modelNames);

        North = new JPanel();
        this.add(North, BorderLayout.NORTH);
        North.setOpaque(false);
        modelbox.addActionListener(this);
        North.add(modelbox);

        Center = new JPanel();
        this.add(Center, BorderLayout.CENTER);
        Center.setOpaque(false);

        South = new JPanel();
        this.add(South, BorderLayout.SOUTH);
        South.setOpaque(false);

        setVisible(true);
        setResizable(false);

    }

    public void actionPerformed(ActionEvent e) {
        Graphics g = Center.getGraphics();
        if (e.getSource() == modelbox) {
            model =modelbox.getSelectedIndex();
            North.revalidate();
            batteryNames = new Vector<String>();
            for (int i=0; i<batteryTypes.size() ; i++) {
                if (batteryTypes.elementAt(i).isCompatible(model))
                    batteryNames.add(batteryTypes.elementAt(i).name);
            }

            engineNames = new Vector<String>();
            for (int i=0; i<engineTypes.size() ; i++) {
                if (engineTypes.elementAt(i).isCompatible(model))
                    engineNames.add(engineTypes.elementAt(i).name);
            }

            paintNames = new Vector<String>();
            for (int i=0; i<paintTypes.size() ; i++) {
                if (paintTypes.elementAt(i).isCompatible(model))
                    paintNames.add(paintTypes.elementAt(i).name);
            }

            wheelNames = new Vector<String>();
            for (int i=0; i<wheelTypes.size() ; i++) {
                if (wheelTypes.elementAt(i).isCompatible(model))
                    wheelNames.add(wheelTypes.elementAt(i).name);
            }

            interiorNames = new Vector<String>();
            for (int i=0; i<interiorTypes.size() ; i++) {
                if (interiorTypes.elementAt(i).isCompatible(model))
                    interiorNames.add(interiorTypes.elementAt(i).name);
            }
            batterybox = new JComboBox(batteryNames);
            enginebox = new JComboBox(engineNames);
            paintbox = new JComboBox(paintNames);
            wheelbox = new JComboBox(wheelNames);
            interiorbox = new JComboBox(interiorNames);
            batterybox.addActionListener(this);
            North.add(batterybox);
            enginebox.addActionListener(this);
            North.add(enginebox);
            paintbox.addActionListener(this);
            North.add(paintbox);
            wheelbox.addActionListener(this);
            North.add(wheelbox);
            interiorbox.addActionListener(this);
            North.add(interiorbox);
            submit = new JButton("Submit");
            submit.addActionListener(this);
            North.add(submit);
        }
        if(e.getSource()== submit) {

            car selectedCar = new car();

            // store model data
            selectedCar.add(modelTypes.elementAt(model));

            // store battery data
            for (int i = 0; i < batteryTypes.size(); i++) {
                if (batteryTypes.elementAt(i).name == batterybox.getSelectedItem())
                    selectedCar.add(batteryTypes.elementAt(i));
            }
            // store engine data
            for (int i = 0; i < engineTypes.size(); i++) {
                if (engineTypes.elementAt(i).name == enginebox.getSelectedItem())
                    selectedCar.add(engineTypes.elementAt(i));

            }
            // store paint data
            for (int i = 0; i < paintTypes.size(); i++) {
                if (paintTypes.elementAt(i).name == paintbox.getSelectedItem())
                    selectedCar.add(paintTypes.elementAt(i));

            }

            // store wheel data
            for (int i = 0; i < wheelTypes.size(); i++) {
                if (wheelTypes.elementAt(i).name == wheelbox.getSelectedItem())
                    selectedCar.add(wheelTypes.elementAt(i));

            }

            // store interior data
            for (int i = 0; i < interiorTypes.size(); i++) {
                if (interiorTypes.elementAt(i).name == interiorbox.getSelectedItem())
                    selectedCar.add(interiorTypes.elementAt(i));

            }
            g.fillPolygon(new int[]{500, 600, 600, 800, 800, 500}, new int[]{500, 500, 410, 410, 600, 600}, 6);
            g.setColor(Color.BLUE);
            g.fillRect(600, 450, 70, 50);
            g.setColor(Color.LIGHT_GRAY);
            g.fillOval(550, 570, 70, 70);
            g.fillOval(700, 570, 70, 70);
            g.setColor(Color.YELLOW);
            g.fillRect(500, 520, 30, 25);
        }
    }


class dataLoader {

    FileReader fileReader;
    char data;
    int[] iterations;

    dataLoader(String path) throws Exception {
        fileReader = new FileReader(path);
        getData();

        for (int i = 0; i < getData(); i++) {

        }
    }

    void getData() throws Exception {
        int a = fileReader.read();

        if (a < 0)
            throw new Exception("data loaded");
        data = (char) a;
    }

    void loadParameter(int iterations) throws Exception {
        String word = new String();
        String[] out = new String[];

        for (int i = 0; i < iterations; i++) {
            while (data != ',') {
                word += data;
                getData();
            }
            out[i] = word;
            word = "";
            getData();
        }
    }


    void readLine(String path) throws IOException {

        FileReader fileReader = new FileReader(path);

        try {


        }
    }
}

class car {
    Vector<choice> settings;
    int price;
    Color[] colors;

    car() {
        settings = new Vector<choice>();

        for (int i = 0; i < 6; i++)
            price+=settings.elementAt(i).price;

        colors = new Color[3];
        for (int i = 0; i < 3; i++)
            colors[i] = settings.elementAt(i+3).color;
    }
    void add(choice item)
        {
            settings.add(item);
        }
}

// choice is the base class for settings of the car
class choice {

    String name;
    int price;
    boolean[] compatibility;
    Color color;

    choice(String name, int price, boolean[] compatibility) {
        this.name = name;
        this.price = price;
        this.compatibility = compatibility;
        color = null;
    }

// accessors
    String getName() { return name; }
    int getPrice() { return price; }
    boolean isCompatible(int index) { return compatibility[index]; }
}

//all choices (as extensions of "choice")
class battery extends choice {

    int range;

    battery(String name, int price, boolean[] compatibility, int range) {
        super(name, price, compatibility);
        this.range = range;
    }

    int getRange() { return range; }
}

class engine extends choice {

    int power;
    int wheels;

    engine(String name, int price, boolean[] compatibility, int power, int wheels) {
        super(name, price, compatibility);
        this.power = power;
        this.wheels = wheels;
    }

    int getPower() { return power; }
    int getWheels() { return wheels; }
}

class paint extends choice {

    paint (String name, int price, boolean[] compatibility, Color color) {
        super(name, price, compatibility);
        this.color = color;
    }

    Color getColor () { return color; }
}

class wheels extends choice {

    int width;

    wheels(String name, int price, boolean[] compatibility, Color color, int width) {
        super(name, price, compatibility);
        this.color = color;
        this.width = width;
    }

    Color getColor() { return color; }
    int getWidth() { return width; }
}

class interior extends choice {

    interior(String name, int price, boolean[] compatibility, Color color) {
        super(name, price, compatibility);
        this.color = color;
    }
}

public class carProject {
    public static void main(String[] args) {
        hronetz hello = new hronetz("");
    }
}
