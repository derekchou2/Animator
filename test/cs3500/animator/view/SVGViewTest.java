package cs3500.animator.view;

import cs3500.animator.model.Color;
import cs3500.animator.model.ExCELlenceModel;
import cs3500.animator.model.ExCELlenceOperations;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.Motion;
import cs3500.animator.model.Posn;
import java.util.HashMap;
import org.junit.Test;

import static cs3500.animator.view.SVGView.getKey;
import static org.junit.Assert.assertEquals;

/**
 * Test cases for the SVG view.
 */
public class SVGViewTest {

  private ExCELlenceOperations eModel = new ExCELlenceModel();


  private IMotion m0 = new Motion(1, 5, new Posn(20, 20), 5.5, 9.2, new Color(0, 255, 0));
  private IMotion m1 = new Motion(5, 10, new Posn(30.5, 30.5), 10.6, 10.6, new Color(0, 255, 0));
  private IMotion m2 = new Motion(5, 10, new Posn(30.5, 30.5), 10.6, 10.6, new Color(0, 255, 0));
  private IMotion m3 = new Motion(9, 12, new Posn(20.1, 20.1), 5.49999, 9.19999, new Color(0, 255,
          0));
  private IMotion m4 = new Motion(9, 12, new Posn(20.1, 20.1), 5.49999, 9.19999, new Color(0, 255,
          0));
  private IMotion m5 = new Motion(-5, 10, new Posn(30.5, 30.5), 10.5, 10.5, new Color(255, 0, 0));
  private IMotion m6 = new Motion(10, 20, new Posn(30.5, 30.5), 20.5, 20.5, new Color(255, 0, 0));
  private IMotion m7 = new Motion(50, 100, new Posn(30.5, 30.5), 20.5, 20.5, new Color(255, 0, 0));


  @Test (expected = IllegalArgumentException.class)
  public void testInit() {
    Appendable out = System.out;
    ExCELlenceOperations mod = new ExCELlenceModel();
    SVGView s = new SVGView(mod, 2, out);
    SVGView x = new SVGView(mod, 0, out);
  }

  @Test
  public void testText() {
    eModel.create("rect1", "rectangle",
            new Posn(0, 0), 5, 5, new Color(0, 0, 0));
    eModel.addMotion("rect1", m6);
    eModel.addMotion("rect1", m1);
    eModel.addMotion("rect1", m0);
    eModel.create("ellipse1", "ellipse",
            new Posn(0, 0), 5, 5, new Color(0, 0, 0));
    eModel.addMotion("ellipse1", m1);
    eModel.addMotion("ellipse1", m6);
    eModel.addMotion("ellipse1", m0);
    eModel.create("hiddenRect", "rectangle", new Posn(20, 20), 4, 4, new Color(0, 255, 0));

    Appendable output = System.out;
    SVGView svg = new SVGView(eModel, 1, output);
    eModel.setWindowHeight(500);
    eModel.setWindowWidth(600);
    // textualView text = new textualView();

    assertEquals("<svg width=\"600\" height=\"500\" version=\"1.1\"\n"
                    + "xmlns=http://www.w3.org/2000/svg>\n"
                    + "<rect id=\"rect1\" x=\"0.0\" y=\"0.0\" width=\"5.0\" height=\"5.0\" " +
                    "fill=\"(rgb 0, 0, 0)\" visibility=\"visible\" >\n"
                    + "<animate attributeType=xml begin=\"1000ms\" dur=\"4000ms\" " +
                    "attributeName=\"x\" from=\"20.0\" to=\"30.5\" fill=\"freeze\"\n"
                    + "<animate attributeType=xml begin=\"1000ms\" dur=\"4000ms\" " +
                    "attributeName=\"y\" from=\"20.0\" to=\"30.5\" fill=\"freeze\"\n"
                    + "<animate attributeType=xml begin=\"1000ms\" dur=\"4000ms\" " +
                    "attributeName=\"width\" from=\"5.5\" to=\"10.6\" fill=\"freeze\"\n"
                    + "<animate attributeType=xml begin=\"1000ms\" dur=\"4000ms\" " +
                    "attributeName=\"height\" from=\"9.2\" to=\"10.6\" fill=\"freeze\"\n"
                    + "<animate attributeType=xml begin=\"1000ms\" dur=\"4000ms\" " +
                    "attributeName=\"fill\" from=\"rgb(0, 255, 0)\" to=\"rgb(0, 255, 0)\" " +
                    "fill=\"freeze\"\n"
                    + "<animate attributeType=xml begin=\"1000ms\" dur=\"4000ms\" " +
                    "attributeName=\"x\" from=\"20.0\" to=\"30.5\" fill=\"freeze\"\n"
                    + "<animate attributeType=xml begin=\"1000ms\" dur=\"4000ms\" " +
                    "attributeName=\"y\" from=\"20.0\" to=\"30.5\" fill=\"freeze\"\n"
                    + "<animate attributeType=xml begin=\"1000ms\" dur=\"4000ms\" " +
                    "attributeName=\"width\" from=\"5.5\" to=\"10.6\" fill=\"freeze\"\n"
                    + "<animate attributeType=xml begin=\"1000ms\" dur=\"4000ms\" " +
                    "attributeName=\"height\" from=\"9.2\" to=\"10.6\" fill=\"freeze\"\n"
                    + "<animate attributeType=xml begin=\"1000ms\" dur=\"4000ms\" " +
                    "attributeName=\"fill\" from=\"rgb(0, 255, 0)\" to=\"rgb(0, 255, 0)\" " +
                    "fill=\"freeze\"\n"
                    + "</rect>\n" + "<rect id=\"hiddenRect\" x=\"20.0\" y=\"20.0\" width=\"4.0\"" +
                    " height=\"4.0\" fill=\"(rgb 0, 255, 0)\" visibility=\"hidden\"" +
                    " opacity=\"0\" >\n"
                    + "</rect>\n"
                    + "<eclipse id=ellipse1 cx=\"0.0\" cy=\"0.0\" rx=\"5.0\" ry=\"5.0\" " +
                    "fill=\"(rgb 0, 0, 0)\" visibility=\"visible\" >\n"
                    + "<animate attributeType=xml begin=\"1000ms\" dur=\"4000ms\" " +
                    "attributeName=\"cx\" from=\"20.0\" to=\"30.5\" fill=\"freeze\"\n"
                    + "<animate attributeType=xml begin=\"1000ms\" dur=\"4000ms\" " +
                    "attributeName=\"cy\" from=\"20.0\" to=\"30.5\" fill=\"freeze\"\n"
                    + "<animate attributeType=xml begin=\"1000ms\" dur=\"4000ms\" " +
                    "attributeName=\"rx\" from=\"5.5\" to=\"10.6\" fill=\"freeze\"\n"
                    + "<animate attributeType=xml begin=\"1000ms\" dur=\"4000ms\" " +
                    "attributeName=\"ry\" from=\"9.2\" to=\"10.6\" fill=\"freeze\"\n"
                    + "<animate attributeType=xml begin=\"1000ms\" dur=\"4000ms\" " +
                    "attributeName=\"fill\" from=\"rgb(0, 255, 0)\" to=\"rgb(0, 255, 0)\" " +
                    "fill=\"freeze\"\n"
                    + "<animate attributeType=xml begin=\"1000ms\" dur=\"4000ms\" " +
                    "attributeName=\"cx\" from=\"20.0\" to=\"30.5\" fill=\"freeze\"\n"
                    + "<animate attributeType=xml begin=\"1000ms\" dur=\"4000ms\" " +
                    "attributeName=\"cy\" from=\"20.0\" to=\"30.5\" fill=\"freeze\"\n"
                    + "<animate attributeType=xml begin=\"1000ms\" dur=\"4000ms\" " +
                    "attributeName=\"rx\" from=\"5.5\" to=\"10.6\" fill=\"freeze\"\n"
                    + "<animate attributeType=xml begin=\"1000ms\" dur=\"4000ms\" " +
                    "attributeName=\"ry\" from=\"9.2\" to=\"10.6\" fill=\"freeze\"\n"
                    + "<animate attributeType=xml begin=\"1000ms\" dur=\"4000ms\" " +
                    "attributeName=\"fill\" from=\"rgb(0, 255, 0)\" to=\"rgb(0, 255, 0)\"" +
                    " fill=\"freeze\"\n"
                    + "</eclipse>\n",
            svg.text());
  }

  @Test
  public void testGetKey() {
    HashMap<String, Integer> h = new HashMap<>();
    h.put("one", 1);
    h.put("two", 2);
    h.put("three", 3);
    assertEquals(getKey(h, 2), "two");
  }
}