import javax.swing.*;
import java.awt.*;

public class GraphicalUserInterface extends JFrame {
    public String text;
    JTextArea textArea = new JTextArea();

    public GraphicalUserInterface(){
        super("Results");
        setLayout(new BorderLayout());
        addComponent(textArea);
    }

    public static Component addComponent(Component component){
        return component;
    }

}
