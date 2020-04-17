package cs3500.animator.view;


import cs3500.animator.model.Ellipse;
import cs3500.animator.model.ExCELlenceModel;
import cs3500.animator.model.ExCELlenceOperationsReadOnly;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Rectangle;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.JList;
import javax.swing.event.ListSelectionListener;

/**
 * Represents scalable vector graphics view of the ExCELlenceModel in formatted text.
 */
public class SVGView implements IView {

  ExCELlenceOperationsReadOnly model = new ExCELlenceModel();
  Appendable output;
  private int speed = 1;

  /**
   * Constructs an SVG view.
   *
   * @param model  represents a given model.
   * @param speed  represents a given setSpeed.
   * @param output represents the output to be used.
   */
  public SVGView(ExCELlenceOperationsReadOnly model, int speed, Appendable output) {
    if (model == null || speed <= 0 || output == null) {
      throw new IllegalArgumentException();
    }
    this.model = model;
    this.speed = speed;
    this.output = output;
  }


  /**
   * Utility method extracted from: https://www.techiedelight.com/get-map-key-from-value-java/ Gets
   * the key of a HashMap given a certain value.
   *
   * @param map   represents a given map.
   * @param value represents a given value
   * @param <K>   Represents the key type.
   * @param <V>   Represents the value type.
   * @return a key representing the given value.
   */
  protected static <K, V> K getKey(Map<K, V> map, V value) {
    for (Map.Entry<K, V> entry : map.entrySet()) {
      if (value.equals(entry.getValue())) {
        return entry.getKey();
      }
    }
    return null;
  }

  @Override
  public String text() {
    String svgStrings = "";

    svgStrings =
            svgStrings + "<svg width=" + "\"" + model.getWindowWidth() + "\"" + " " + "height=" +
                    "\"" + model.getWindowHeight() + "\"" + " " +
                    "version=" + "\"" + "1.1\"" + "\n" + "xmlns=" + "http://www.w3.org/2000/svg" +
                    ">" + "\n";
    HashMap<String, IShape> shapes = model.getShapes();

    //String state = "";
    for (IShape s : shapes.values()) {
      if (s instanceof Rectangle) {
        svgStrings =
                svgStrings + "<rect " + "id=" + "\"" + getKey(shapes, s) + "\"" + " " + "x=" + "\""
                        + s.getPosn().getX() + "\""
                        + " " + "y=" + "\"" + s.getPosn().getY() + "\"" + " "
                        + "width"
                        + "=" + "\"" + s.getWidth() + "\"" + " " + "height=" + "\""
                        + s.getHeight() + "\""
                        + " " + "fill=" + "\"(rgb " + s.getColor().getRed() + ", "
                        + s.getColor().getGreen() + ", " + s.getColor().getBlue() + ")\"" + " "
                        + "visibility=\"" + hiddenIfNoMotions(s) + " " + ">" + "\n";

      }
      if (s instanceof Ellipse) {
        svgStrings =
                svgStrings + "<eclipse " + "id=" + getKey(shapes, s) + " " + "cx=" + "\""
                        + s.getPosn().getX() + "\"" + " " + "cy=" + "\"" + s.getPosn().getY()
                        + "\"" + " " + "rx=" + "\"" + s.getWidth() + "\"" + " " + "ry=" + "\""
                        + s.getHeight() + "\"" + " " + "fill=" + "\"(rgb " + s.getColor().getRed()
                        + ", " + s.getColor().getGreen() + ", " + s.getColor().getBlue() + ")\""
                        + " " + "visibility=\"" + hiddenIfNoMotions(s) + " " + ">" + "\n";
      }

      for (int i = 0; i < s.getMotions().size() - 1; i++) {
        if (s instanceof Rectangle) {
          if ((s.getMotions().get(0).getPosn().getX() != s.getMotions().get(1).getPosn().getX())) {
            svgStrings = svgStrings + "<animate attributeType=xml begin=" + "\""
                    + (s.getMotions().get(0).getT1() * 1000) / speed + "ms" + "\"" + " "
                    + "dur=" + "\"" + ((s.getMotions().get(0).getT2() * 1000 / speed)
                    - (s.getMotions().get(0).getT1() * 1000 / speed)) + "ms\"" + " "
                    + "attributeName=" + "\"x\"" + " " + "from=" + "\""
                    + s.getMotions().get(0).getPosn().getX() + "\"" + " " + "to="
                    + "\"" + s.getMotions().get(1).getPosn().getX() + "\"" + " " + "fill="
                    + "\"freeze\"" + "\n";
          }
          if ((s.getMotions().get(0).getPosn().getY() != s.getMotions().get(1).getPosn().getY())) {
            svgStrings = svgStrings + "<animate attributeType=xml begin=" + "\"" +
                    (s.getMotions().get(0).getT1() * 1000) / speed + "ms" + "\"" + " "
                    + "dur=" + "\"" + ((s.getMotions().get(0).getT2() * 1000 / speed)
                    - (s.getMotions().get(0).getT1() * 1000 / speed)) + "ms\"" + " "
                    + "attributeName=" + "\"y\"" + " " + "from=" + "\""
                    + s.getMotions().get(0).getPosn().getY() + "\"" + " " + "to=" + "\""
                    + s.getMotions().get(1).getPosn().getY() + "\"" + " " + "fill="
                    + "\"freeze\"" + "\n";
          }
          if ((s.getMotions().get(0).getWidth() != s.getMotions().get(1).getWidth())) {
            svgStrings = svgStrings + "<animate attributeType=xml begin=" + "\""
                    + (s.getMotions().get(0).getT1() * 1000) / speed + "ms" + "\"" + " " + "dur="
                    + "\"" + ((s.getMotions().get(0).getT2() * 1000 / speed)
                    - (s.getMotions().get(0).getT1() * 1000 / speed)) + "ms\"" + " "
                    + "attributeName=" + "\"width\"" + " " + "from=" + "\""
                    + s.getMotions().get(0).getWidth() + "\"" + " " + "to=" + "\""
                    + s.getMotions().get(1).getWidth() + "\"" + " " + "fill=" + "\"freeze\"" + "\n";
          }
          if ((s.getMotions().get(0).getHeight() != s.getMotions().get(1).getHeight())) {
            svgStrings = svgStrings + "<animate attributeType=xml begin=" + "\""
                    + (s.getMotions().get(0).getT1() * 1000) / speed + "ms" + "\"" + " "
                    + "dur=" + "\"" + ((s.getMotions().get(0).getT2() * 1000 / speed)
                    - (s.getMotions().get(0).getT1() * 1000 / speed)) + "ms\"" + " "
                    + "attributeName=" + "\"height\"" + " " + "from=" + "\""
                    + s.getMotions().get(0).getHeight() + "\"" + " " + "to=" + "\""
                    + s.getMotions().get(1).getHeight() + "\"" + " " + "fill="
                    + "\"freeze\"" + "\n";
          }
          if ((s.getMotions().get(0).getColor() != s.getMotions().get(1).getColor())) {
            svgStrings = svgStrings + "<animate attributeType=xml begin=" + "\"" +
                    (s.getMotions().get(0).getT1() * 1000) / speed + "ms" + "\"" + " "
                    + "dur=" + "\"" + ((s.getMotions().get(0).getT2() * 1000 / speed)
                    - (s.getMotions().get(0).getT1() * 1000 / speed)) + "ms\"" + " "
                    + "attributeName=" + "\"fill\"" + " " + "from=" + "\"" + "rgb("
                    + s.getMotions().get(0).getColor().getRed() + ", "
                    + s.getMotions().get(0).getColor().getGreen() + ", "
                    + s.getMotions().get(0).getColor().getBlue() + ")\"" + " " + "to="
                    + "\"" + "rgb(" + s.getMotions().get(1).getColor().getRed() + ", "
                    + s.getMotions().get(1).getColor().getGreen() + ", "
                    + s.getMotions().get(1).getColor().getBlue() + ")\"" + " " + "fill="
                    + "\"freeze\"" + "\n";
          }
        }
        if (s instanceof Ellipse) {
          if ((s.getMotions().get(0).getPosn().getX() != s.getMotions().get(1).getPosn().getX())) {
            svgStrings = svgStrings + "<animate attributeType=xml begin=" + "\""
                    + (s.getMotions().get(0).getT1() * 1000) / speed + "ms" + "\"" + " "
                    + "dur=" + "\"" + ((s.getMotions().get(0).getT2() * 1000 / speed)
                    - (s.getMotions().get(0).getT1() * 1000 / speed)) + "ms\"" + " "
                    + "attributeName=" + "\"cx\"" + " " + "from=" + "\""
                    + s.getMotions().get(0).getPosn().getX() + "\"" + " " + "to=" + "\""
                    + s.getMotions().get(1).getPosn().getX() + "\"" + " " + "fill="
                    + "\"freeze\"" + "\n";
          }
          if ((s.getMotions().get(0).getPosn().getY() != s.getMotions().get(1).getPosn().getY())) {
            svgStrings = svgStrings + "<animate attributeType=xml begin=" + "\""
                    + (s.getMotions().get(0).getT1() * 1000) / speed + "ms" + "\"" + " "
                    + "dur=" + "\"" + ((s.getMotions().get(0).getT2() * 1000 / speed)
                    - (s.getMotions().get(0).getT1() * 1000 / speed)) + "ms\"" + " "
                    + "attributeName=" + "\"cy\"" + " " + "from=" + "\""
                    + s.getMotions().get(0).getPosn().getY() + "\"" + " " + "to="
                    + "\"" + s.getMotions().get(1).getPosn().getY() + "\"" + " " + "fill="
                    + "\"freeze\"" + "\n";
          }
          if ((s.getMotions().get(0).getWidth() != s.getMotions().get(1).getWidth())) {
            svgStrings = svgStrings + "<animate attributeType=xml begin=" + "\""
                    + (s.getMotions().get(0).getT1() * 1000) / speed + "ms" + "\"" + " " + "dur="
                    + "\"" + ((s.getMotions().get(0).getT2() * 1000 / speed)
                    - (s.getMotions().get(0).getT1() * 1000 / speed)) + "ms\"" + " "
                    + "attributeName=" + "\"rx\"" + " " + "from=" + "\""
                    + s.getMotions().get(0).getWidth() + "\"" + " " + "to=" + "\""
                    + s.getMotions().get(1).getWidth() + "\"" + " " + "fill="
                    + "\"freeze\"" + "\n";
          }
          if ((s.getMotions().get(0).getHeight() != s.getMotions().get(1).getHeight())) {
            svgStrings = svgStrings + "<animate attributeType=xml begin=" + "\""
                    + (s.getMotions().get(0).getT1() * 1000) / speed + "ms" + "\"" + " " + "dur="
                    + "\"" + ((s.getMotions().get(0).getT2() * 1000 / speed)
                    - (s.getMotions().get(0).getT1() * 1000 / speed)) + "ms\"" + " "
                    + "attributeName=" + "\"ry\"" + " " + "from=" + "\""
                    + s.getMotions().get(0).getHeight() + "\"" + " " + "to=" + "\""
                    + s.getMotions().get(1).getHeight() + "\"" + " " + "fill="
                    + "\"freeze\"" + "\n";
          }
          if ((s.getMotions().get(0).getColor() != s.getMotions().get(1).getColor())) {
            svgStrings = svgStrings + "<animate attributeType=xml begin=" + "\""
                    + (s.getMotions().get(0).getT1() * 1000) / speed + "ms" + "\"" + " " + "dur="
                    + "\"" + ((s.getMotions().get(0).getT2() * 1000 / speed)
                    - (s.getMotions().get(0).getT1() * 1000 / speed)) + "ms\"" + " "
                    + "attributeName=" + "\"fill\"" + " " + "from=" + "\"" + "rgb("
                    + s.getMotions().get(0).getColor().getRed() + ", "
                    + s.getMotions().get(0).getColor().getGreen() + ", "
                    + s.getMotions().get(0).getColor().getBlue() + ")\"" + " " + "to=" + "\""
                    + "rgb(" + s.getMotions().get(1).getColor().getRed() + ", "
                    + s.getMotions().get(1).getColor().getGreen() + ", "
                    + s.getMotions().get(1).getColor().getBlue() + ")\"" + " " + "fill="
                    + "\"freeze\"" + "\n";
          }
        }
      }
      if (s instanceof Rectangle) {
        svgStrings = svgStrings + "</rect>" + "\n";
      }
      if (s instanceof Ellipse) {
        svgStrings = svgStrings + "</eclipse>" + "\n";
      }
    }
    return svgStrings;
  }

  /**
   * Produces a string to indicate whether a shape should be visible or not based on whether it has
   * motions.
   *
   * @param s represents a given Shape.
   * @return a String being either "visible" or "hidden" based on the presence of a shape's motions.
   */
  private String hiddenIfNoMotions(IShape s) {
    if (s.getMotions().isEmpty()) {
      return "hidden" + "\"" + " " + "opacity=" + "\"0\"";
    }
    return "visible" + "\"";
  }

  @Override
  public void showView() throws IOException {
    output.append(this.text());
  }

  @Override
  public void refresh() {
    throw new UnsupportedOperationException(("svg view outputs all at once, no refreshing"));
  }

  @Override
  public void setSpeed(int speed) {
    this.speed = speed;
  }

  @Override
  public ExCELlenceOperationsReadOnly getModel() {
    return this.model;
  }

  @Override
  public void setListener(ActionListener listener, ListSelectionListener listener2) {
    throw new UnsupportedOperationException("No listeners in this view");
  }

  @Override
  public int getSpeed() {
    return this.speed;
  }

  @Override
  public int getTick() {
    throw new UnsupportedOperationException("No tick in this view");
  }

  @Override
  public Timer getTimer() {
    throw new UnsupportedOperationException("No Timer in this view");
  }

  @Override
  public void pressPause() {
    throw new UnsupportedOperationException("No buttons in this view.");
  }

  @Override
  public void pressRestart() {
    throw new UnsupportedOperationException("No buttons in this view.");
  }

  @Override
  public void pressSpeedUp() {
    throw new UnsupportedOperationException("No buttons in this view.");
  }

  @Override
  public void pressSlowDown() {
    throw new UnsupportedOperationException("No buttons in this view.");
  }

  @Override
  public void pressLoop() {
    throw new UnsupportedOperationException("No buttons in this view.");
  }

  @Override
  public void updateModel(ExCELlenceOperationsReadOnly m) {
    throw new UnsupportedOperationException("No buttons in this view.");
  }

  @Override
  public JList getShapesKeyframesList() {
    throw new UnsupportedOperationException("No JList in this view");
  }

  @Override
  public JButton getDeleteShapeButton() {
    throw new UnsupportedOperationException("No buttons in this view.");
  }

  @Override
  public JButton getDeleteKeyframeButton() {
    throw new UnsupportedOperationException("No buttons in this view.");
  }

  @Override
  public JButton getModifyKeyframeButton() {
    throw new UnsupportedOperationException("No buttons in this view.");
  }

  @Override
  public JTextField getTextField() {
    throw new UnsupportedOperationException("No JTextField in this view");
  }

  @Override
  public boolean isLooping() {
    throw new UnsupportedOperationException("Looping not used in this view.");
  }
}
