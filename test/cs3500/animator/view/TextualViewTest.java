package cs3500.animator.view;

import cs3500.animator.model.Color;
import cs3500.animator.model.ExCELlenceModel;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.Motion;
import cs3500.animator.model.Posn;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the textual view.
 */
public class TextualViewTest {

  private ExCELlenceModel eModel = new ExCELlenceModel();


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


  // Test Viable when textualView outputs String for testing.
  @Test
  public void testShow() throws IOException {
    eModel.create("R", "rectangle",
        new Posn(0, 0), 5, 5, new Color(0, 0, 0));
    eModel.addMotion("R", m6);
    eModel.addMotion("R", m1);
    eModel.addMotion("R", m0);
    eModel.create("C", "ellipse",
        new Posn(0, 0), 5, 5, new Color(0, 0, 0));
    eModel.addMotion("C", m1);
    eModel.addMotion("C", m6);
    eModel.addMotion("C", m0);

    StringBuilder output = new StringBuilder();
    IView textualView = new TextualView(eModel, output);
    textualView.showView();
    assertEquals("canvas 0 0 0 0\n" +
            "shape R rectangle\n" +
            "motion R 1 0 0 5 5 0 0 0     5 20 20 5 9 0 255 0\n" +
            "motion R 5 20 20 5 9 0 255 0     10 30 30 10 10 0 255 0\n" +
            "motion R 10 30 30 10 10 0 255 0     20 30 30 20 20 255 0 0\n" +
            "shape C ellipse\n" +
            "motion C 1 0 0 5 5 0 0 0     5 20 20 5 9 0 255 0\n" +
            "motion C 5 20 20 5 9 0 255 0     10 30 30 10 10 0 255 0\n" +
            "motion C 10 30 30 10 10 0 255 0     20 30 30 20 20 255 0 0\n", output.toString());

  }


}