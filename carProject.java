
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

    String path;

    JPanel north, center, south;
    JComboBox box1, box2, box3, box4, box5, box6;
    JButton button;

    hronetz(String path) {

        modelTypes = new Vector<choice>();
        batteryTypes = new Vector<battery>();
        engineTypes = new Vector<engine>();
        paintTypes = new Vector<paint>();
        wheelTypes = new Vector<wheels>();
        interiorTypes = new Vector<interior>();
        this.path = path;



        this.setSize(1000, 1000);
        this.setResizable(false);
        this.setVisible(true);
        this.setTitle("hronetz.exe");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        north = new JPanel();
        this.add(north, BorderLayout.NORTH);
        center = new JPanel();
        this.add(center, BorderLayout.CENTER);
        south = new JPanel();
        this.add(south, BorderLayout.SOUTH);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

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
