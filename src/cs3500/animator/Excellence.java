package cs3500.animator;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cs3500.animator.controller.Controller;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.EditorView;
import cs3500.animator.view.Factory;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.VisualView;
import cs3500.animator.model.ExCELlenceModel;
import cs3500.animator.view.TextualView;

/**
 * A Main class for interactively testing the visual view.
 */
public final class Excellence {
  /**
   * Provides entrance into code supporting view functionality. Users call the main function
   * within this class supporting 4 input flags: -in, -view, -out, and -speed.
   * @param args -in, the input file to be read into a new model
   *             -view, the type of view to be supplied (text, visual, or svg)
   *             -out, out to have view displayed in console, otherwise the name of the output file
   *             -speed, the speed at which the view is supplied
   * @throws IOException if input cannot be read
   */
  public static void main(String[] args) throws IOException {
    String viewType = null;
    Readable inFile = null;

    // Default destination is console
    String outdest = "System.out";
    Appendable output = System.out;
    int speed = 1;
    int argNum = 0;
    JFrame popup = new JFrame();
    popup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    while (argNum < args.length) {
      try {
        switch (args[argNum]) {
          case "-in":
            inFile = new FileReader(args[argNum + 1]);
            break;
          case "-view":
            viewType = args[argNum + 1];
            break;
          case "-out":
            outdest = args[argNum + 1];
            break;
          case "-speed":
            speed = Integer.parseInt(args[argNum + 1]);
            break;
          default:
            JOptionPane.showMessageDialog(popup,"Valid input flags are: -in, -view," +
                    "-out, and -speed");
            throw new IllegalArgumentException("Valid input flags are: -in, -view," +
                    "-out, and -speed");
        }
      } catch (IndexOutOfBoundsException e) {
        JOptionPane.showMessageDialog(popup,"Ran out of inputs");
        throw new IllegalArgumentException("Ran out of inputs");

      }
      argNum = argNum + 2;
    }
    if (inFile == null || viewType == null) {
      JOptionPane.showMessageDialog(popup,"input file and viewtype can't be null");
      throw new IllegalArgumentException("input file and viewtype can't be null");
    }

    if (outdest.equals("out")) {
      output = System.out;
      outdest = "System.out";
    }
    else if (!outdest.equals("System.out")) {
      output = new StringBuilder();
    }

    ExCELlenceModel.Builder builder = new ExCELlenceModel.Builder();
    AnimationReader.parseFile(inFile, builder);

    IView view = new Factory(viewType, builder.build()).getView();


    if (view instanceof EditorView) {
      Controller controller = new Controller(builder.build(), (EditorView) view, speed);
    }


    if (view instanceof VisualView) {
      view.showView();
      // Sets speed and starts Swing Timer
      view.setSpeed(speed);
    }

    if (view instanceof TextualView) {
      if (outdest.equals("System.out")) {
        view.showView();
      }

      output.append("canvas " + (int)view.getModel().getTopLeft().getX() + " "
              + (int)view.getModel().getTopLeft().getY() + " " + view.getModel().getWindowWidth()
              + " " + view.getModel().getWindowHeight() + "\n");
      output.append(view.getModel().getState());
    }

    if (view instanceof SVGView) {
      view.setSpeed(speed);
      if (outdest.equals("System.out")) {
        view.showView();
      }

      output.append(view.text());
    }



    if (!outdest.equals("System.out")) {
      FileWriter writer = new FileWriter(new File("D:\\OOD_Outputs\\" + outdest));
      writer.write(output.toString());
      writer.close();
    }
  }
}