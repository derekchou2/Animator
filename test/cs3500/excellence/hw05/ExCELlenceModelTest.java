package cs3500.excellence.hw05;

import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;

import cs3500.animator.model.Color;
import cs3500.animator.model.Ellipse;
import cs3500.animator.model.ExCELlenceModel;
import cs3500.animator.model.ExCELlenceOperations;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Keyframe;
import cs3500.animator.model.Motion;
import cs3500.animator.model.Posn;
import cs3500.animator.model.Rectangle;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for our ExCELlence Model.
 */
public class ExCELlenceModelTest {

  private ExCELlenceModel eModel = new ExCELlenceModel();

  private ExCELlenceModel model = new ExCELlenceModel();
  private ExCELlenceModel model2 = new ExCELlenceModel();
  private ExCELlenceModel model3 = new ExCELlenceModel();
  private ExCELlenceModel model4 = new ExCELlenceModel();
  private HashMap<String, IShape> shapes;
  private IShape rect1 = new Rectangle("rect1", new Posn(4.5, 5.8),
      5, 5, new Color(255, 0, 0));
  private IShape ellipse1 = new Ellipse("ellipse1", new Posn(4.5, 5.8),
      5, 5, new Color(255, 0, 0));
  private IShape rect2 = new Rectangle("rect2", new Posn(4.49999, 5.79999),
      5, 5, new Color(255, 0, 0));
  private IShape ellipse2 = new Ellipse("ellipse2", new Posn(4.49999, 5.79999),
      5, 5, new Color(255, 0, 0));
  private IMotion m0 = new Motion(1, 5, new Posn(20, 20), 5.5, 9.2, new Color(0, 255, 0));
  private IMotion m1 = new Motion(5, 10, new Posn(30.5, 30.5), 10.6, 10.6, new Color(0, 255, 0));
  private IMotion m2 = new Motion(5, 10, new Posn(30.5, 30.5), 10.6, 10.6, new Color(0, 255, 0));
  private IMotion m3 = new Motion(9, 12, new Posn(20.1, 20.1), 5.49999, 9.19999, new Color(0, 255,
      0));
  private IMotion m4 = new Motion(9, 12, new Posn(20.1, 20.1), 5.49999, 9.19999, new Color(0, 255,
      0));
  private IMotion m5 = new Motion(0, 4, new Posn(10, 10), 7, 9, new Color(10, 20, 30));
  private IMotion m6 = new Motion(10, 20, new Posn(30.5, 30.5), 20.5, 20.5, new Color(255, 0, 0));
  private IMotion m7 = new Motion(8, 12, new Posn(20, 20), 11, 13, new Color(20, 30, 40));
  private IMotion m8 = new Motion(1, 1, new Posn(30.5, 30.5), 20.5, 20.5, new Color(255, 0, 0));

  private Keyframe k0 = new Keyframe( 5, new Posn(20, 20), 5.5, 9.2, new Color(0, 255, 0));
  private Keyframe k1 = new Keyframe( 10, new Posn(30.5, 30.5), 10.6, 10.6, new Color(0, 255, 0));
  private Keyframe k2 = new Keyframe( 10, new Posn(30.5, 30.5), 10.6, 10.6, new Color(0, 255, 0));
  private Keyframe k3 = new Keyframe( 12, new Posn(20.1, 20.1), 5.49999, 9.19999, new Color(0, 255,
          0));
  private Keyframe k4 = new Keyframe(12, new Posn(20.1, 20.1), 5.49999, 9.19999, new Color(0, 255,
          0));
  private Keyframe k5 = new Keyframe( 4, new Posn(10, 10), 7, 9, new Color(10, 20, 30));
  private Keyframe k6 = new Keyframe( 20, new Posn(30.5, 30.5), 20.5, 20.5, new Color(255, 0, 0));
  private Keyframe k7 = new Keyframe( 12, new Posn(20, 20), 11, 13, new Color(20, 30, 40));
  private Keyframe k8 = new Keyframe( 1, new Posn(30.5, 30.5), 20.5, 20.5, new Color(255, 0, 0));



  @Test
  public void testCreate() {
    eModel.create("rect1", "rectangle",
        new Posn(0, 0), 5, 5, new Color(0, 0, 0));
    assertEquals(1, eModel.getShapesAt(0).size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateFailArg() {
    model.create("rect1", "NotEvenAShape", new Posn(6, 6), 5, 5, new Color(0, 0, 0));
  }

  @Test
  public void testAddMotionGetMotion() {
    ArrayList<IMotion> m = new ArrayList<>();
    model2.create("rect", "rectangle",
        new Posn(0, 0), 5, 5, new Color(0, 255, 0));
    model2.create("ellip", "ellipse", new Posn(1, 1), 4, 4, new Color(255, 0, 0));
    model2.addMotion("rect", m0);
    m.add(m0);
    m.add(m1);
    model2.addMotion("rect", m1);
    assertEquals(model2.getMotions("rect"), m);
    model2.addMotion("ellip", new Motion(1, 10, new Posn(1, 1), 3, 3, new Color(255, 0, 0)));
    model2.addMotion("ellip", new Motion(10, 15, new Posn(3, 3), 5, 5, new Color(0, 0, 255)));
    m.clear();
    m.add(new Motion(1, 10, new Posn(1, 1), 3, 3, new Color(255, 0, 0)));
    m.add(new Motion(10, 15, new Posn(3, 3), 5, 5, new Color(0, 0, 255)));
    assertEquals(model2.getMotions("ellip"), m);
  }

  @Test
  public void testAddKeyframeGetKeyframe() {
    ArrayList<Keyframe> k = new ArrayList<>();
    model2.create("rect", "rectangle",
            new Posn(0, 0), 5, 5, new Color(0, 255, 0));
    model2.create("ellip", "ellipse", new Posn(1, 1), 4, 4, new Color(255, 0, 0));
    model2.addKeyframe("rect", k0);
    k.add(k0);
    k.add(k1);
    model2.addKeyframe("rect", k1);
    assertEquals(model2.getKeyframes("rect"), k);
    model2.addKeyframe("ellip", new Keyframe( 10, new Posn(1, 1), 3, 3, new Color(255, 0, 0)));
    model2.addKeyframe("ellip", new Keyframe( 15, new Posn(3, 3), 5, 5, new Color(0, 0, 255)));
    k.clear();
    k.add(new Keyframe(10, new Posn(1, 1), 3, 3, new Color(255, 0, 0)));
    k.add(new Keyframe( 15, new Posn(3, 3), 5, 5, new Color(0, 0, 255)));
    assertEquals(model2.getKeyframes("ellip"), k);
  }


  @Test(expected = IllegalStateException.class)
  public void testAddMotionFail() {
    model.create("rect", "rectangle",
        new Posn(0, 0), 5, 5, new Color(0, 255, 0));
    model.addMotion("rect", m0);
    model.addMotion("rect", m2);
    model.addMotion("rect", m4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddKeyframeFail() {
    model.create("rect", "rectangle",
            new Posn(0, 0), 5, 5, new Color(0, 255, 0));
    model.addKeyframe("rect", k3);
    model.addKeyframe("rect", k4);
  }

  @Test(expected = IllegalStateException.class)
  public void testRemove() {
    eModel.create("rect1", "rectangle",
        new Posn(0, 0), 5, 5, new Color(0, 0, 0));
    assertEquals(1, eModel.getShapesAt(0).size());
    eModel.removeShape("rect1");
    assertEquals(0, eModel.getShapesAt(0).size());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveMotionFailShape() {
    eModel.create("rect1", "rectangle",
            new Posn(0, 0), 5, 5, new Color(0, 0, 0));
    eModel.addMotion("rect1", m0);
    eModel.removeMotion("rect2", m0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveKeyframeFailShape() {
    eModel.create("rect1", "rectangle",
            new Posn(0, 0), 5, 5, new Color(0, 0, 0));
    eModel.addKeyframe("rect1", k0);
    eModel.removeKeyframe("rect2", k0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveMotionFailMotion() {
    eModel.create("rect1", "rectangle",
            new Posn(0, 0), 5, 5, new Color(0, 0, 0));
    eModel.addMotion("rect1", m0);
    eModel.removeMotion("rect1", m1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveKeyframeFailKeyframe() {
    eModel.create("rect1", "rectangle",
            new Posn(0, 0), 5, 5, new Color(0, 0, 0));
    eModel.addKeyframe("rect1", k0);
    eModel.removeKeyframe("rect1", k1);
  }

  @Test
  public void testValidRemoveMotion() {
    eModel.create("rect1", "rectangle",
            new Posn(0, 0), 5, 5, new Color(0, 0, 0));
    eModel.addMotion("rect1", m0);
    eModel.addMotion("rect1", m1);
    assertEquals(2, eModel.getShape("rect1").getMotions().size());
    eModel.removeMotion("rect1", m0);
    assertEquals(1, eModel.getShape("rect1").getMotions().size());
  }

  @Test
  public void testValidRemoveKeyframe() {
    eModel.create("rect1", "rectangle",
            new Posn(0, 0), 5, 5, new Color(0, 0, 0));
    eModel.addKeyframe("rect1", k0);
    eModel.addKeyframe("rect1", k1);
    assertEquals(2, eModel.getShape("rect1").getKeyframes().size());
    eModel.removeKeyframe("rect1", k0);
    assertEquals(1, eModel.getShape("rect1").getKeyframes().size());
  }

  @Test
  public void testGetGameState() {
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

    assertEquals("shape rect1 rectangle\n"
            + "motion rect1 1 0 0 5 5 0 0 0     5 20 20 5 9 0 255 0\n"
            + "motion rect1 5 20 20 5 9 0 255 0     10 30 30 10 10 0 255 0\n"
            + "motion rect1 10 30 30 10 10 0 255 0     20 30 30 20 20 255 0 0\n"
            + "shape ellipse1 ellipse\n"
            + "motion ellipse1 1 0 0 5 5 0 0 0     5 20 20 5 9 0 255 0\n"
            + "motion ellipse1 5 20 20 5 9 0 255 0     10 30 30 10 10 0 255 0\n"
            + "motion ellipse1 10 30 30 10 10 0 255 0     20 30 30 20 20 255 0 0\n",
        eModel.getState());
  }

  @Test
  public void testGetGameState2() {
    eModel.create("rect1", "rectangle",
            new Posn(0, 0), 5, 5, new Color(0, 0, 0));
    eModel.addMotion("rect1", m6);
    eModel.addMotion("rect1", m1);
    eModel.addMotion("rect1", m0);
    eModel.create("rect2", "rectangle",
            new Posn(0, 0), 5, 5, new Color(0, 0, 0));
    eModel.addMotion("rect2", m5);
    eModel.addMotion("rect2", m7);

    assertEquals("shape rect1 rectangle\n" +
                    "motion rect1 1 0 0 5 5 0 0 0     5 20 20 5 9 0 255 0\n" +
                    "motion rect1 5 20 20 5 9 0 255 0     10 30 30 10 10 0 255 0\n" +
                    "motion rect1 10 30 30 10 10 0 255 0     20 30 30 20 20 255 0 0\n" +
                    "shape rect2 rectangle\n" +
                    "motion rect2 0 0 0 5 5 0 0 0     4 10 10 7 9 10 20 30\n" +
                    "motion rect2 4 10 10 7 9 10 20 30     12 20 20 11 13 20 30 40\n",
            eModel.getState());
  }


  @Test
  public void testSortMotions() {
    model3.create("Rect1", "rectangle", new Posn(4.5, 5.8), 5, 5,
        new Color(255, 0, 0));
    model3.addMotion("Rect1", m1);
    model3.addMotion("Rect1", m6);
    model3.addMotion("Rect1", m0);
    ArrayList<IMotion> motions = new ArrayList<>();
    motions.add(m0);
    motions.add(m1);
    motions.add(m6);
    assertEquals(motions, model3.getMotions("Rect1"));
  }

  @Test
  public void testSortKeyframes() {
    model3.create("Rect1", "rectangle", new Posn(4.5, 5.8), 5, 5,
            new Color(255, 0, 0));
    model3.addKeyframe("Rect1", k1);
    model3.addKeyframe("Rect1", k6);
    model3.addKeyframe("Rect1", k0);
    ArrayList<Keyframe> keyframes = new ArrayList<>();
    keyframes.add(k0);
    keyframes.add(k1);
    keyframes.add(k6);
    assertEquals(keyframes, model3.getKeyframes("Rect1"));
  }

  // validate is going to be private, can test using executeMotions eventually

  @Test(expected = IllegalStateException.class)
  public void testValidateMotionsFail() {
    model3.create("Rect1", "rectangle", new Posn(4.5, 5.8), 5, 5,
        new Color(255, 0, 0));
    model3.addMotion("Rect1", m0);
    model3.addMotion("Rect1", m2);
    model3.addMotion("Rect1", m3);
    model3.validateMotions("Rect1");
  }


  @Test
  public void testGetShape() {
    model4.create("rect", "rectangle", new Posn(5, 5), 3.5, 3.5, new Color(0, 0, 0));
    model4.create("ellipse", "ellipse", new Posn(4, 4), 2.2, 2.2, new Color(255, 0, 0));
    assertEquals(model4.getShape("rect"),
            new Rectangle("rect", new Posn(5, 5), 3.5, 3.5, new Color(0, 0,
        0)));
    assertEquals(model4.getShape("ellipse"),
            new Ellipse("ellipse", new Posn(4, 4), 2.2, 2.2, new Color(255,
        0, 0)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetShapeNotFound() {
    model4.create("rect", "rectangle", new Posn(5, 5), 3.5, 3.5, new Color(0, 0, 0));
    model4.create("ellipse", "ellipse", new Posn(4, 4), 2.2, 2.2, new Color(255, 0, 0));
    assertEquals(model4.getShape("rect2"),
            new Rectangle("rect2", new Posn(5, 5), 3.5, 3.5, new Color(0, 0,
        0)));
  }

  @Test(expected = IllegalStateException.class)
  public void testGetShapesFailState() {
    model.getShapesAt(0);
  }

  @Test
  public void testGetShapesAtNoMotions() {
    ExCELlenceOperations model5 = new ExCELlenceModel();
    model5.create("rect", "rectangle", new Posn(0, 0), 3, 5, new Color(0, 0, 0));
    model5.create("ellipse", "ellipse", new Posn(0, 0), 3, 5, new Color(0, 0, 0));

    ArrayList<IShape> shapesList = new ArrayList<>();
    Rectangle r1 = new Rectangle("rect", new Posn(0, 0), 3, 5, new Color(0, 0, 0));
    Ellipse e1 = new Ellipse("ellipse", new Posn(0, 0), 3, 5, new Color(0, 0, 0));

    shapesList.add(r1);
    shapesList.add(e1);

    assertEquals(shapesList, model5.getShapesAt(3));
    assertEquals(shapesList, model5.getShapesAt(0));
    assertEquals(shapesList, model5.getShapesAt(999));
  }

  @Test
  public void testGetShapesAtKeyframeNoMotions() {
    ExCELlenceOperations model5 = new ExCELlenceModel();
    model5.create("rect", "rectangle", new Posn(0, 0), 3, 5, new Color(0, 0, 0));
    model5.create("ellipse", "ellipse", new Posn(0, 0), 3, 5, new Color(0, 0, 0));

    ArrayList<IShape> shapesList = new ArrayList<>();
    Rectangle r1 = new Rectangle("rect", new Posn(0, 0), 3, 5, new Color(0, 0, 0));
    Ellipse e1 = new Ellipse("ellipse", new Posn(0, 0), 3, 5, new Color(0, 0, 0));

    shapesList.add(r1);
    shapesList.add(e1);

    assertEquals(shapesList, model5.getShapesAtKeyframe(3));
    assertEquals(shapesList, model5.getShapesAtKeyframe(0));
    assertEquals(shapesList, model5.getShapesAtKeyframe(999));
  }

  @Test
  public void testGetShapesAtOneMotion() {
    ExCELlenceOperations model5 = new ExCELlenceModel();
    model5.create("rect", "rectangle", new Posn(0, 0), 3, 5, new Color(0, 0, 0));
    model5.create("ellipse", "ellipse", new Posn(0, 0), 3, 5, new Color(0, 0, 0));
    model5.addMotion("rect", m5);
    model5.addMotion("ellipse", m5);

    ArrayList<IShape> shapesList = new ArrayList<>();
    Rectangle r1 = new Rectangle("rect", new Posn(7.5, 7.5), 6, 8, new Color(7, 15, 22));
    Ellipse e1 = new Ellipse("ellipse", new Posn(7.5, 7.5), 6, 8, new Color(7, 15, 22));
    shapesList.add(r1);
    shapesList.add(e1);

    assertEquals(shapesList, model5.getShapesAt(3));

    ArrayList<IMotion> motions = new ArrayList<>();
    motions.add(m5);
    assertEquals(motions, model5.getShapesAt(3).get(0).getMotions());
    assertEquals(motions, model5.getShapesAt(3).get(1).getMotions());

  }

  @Test
  public void testGetShapesAtKeyframeOneMotion() {
    ExCELlenceOperations model5 = new ExCELlenceModel();
    model5.create("rect", "rectangle", new Posn(0, 0), 3, 5, new Color(0, 0, 0));
    model5.create("ellipse", "ellipse", new Posn(0, 0), 3, 5, new Color(0, 0, 0));
    model5.addKeyframe("rect", k5);
    model5.addKeyframe("ellipse", k5);

    ArrayList<IShape> shapesList = new ArrayList<>();
    Rectangle r1 = new Rectangle("rect", new Posn(7.5, 7.5), 6, 8, new Color(7, 15, 22));
    Ellipse e1 = new Ellipse("ellipse", new Posn(7.5, 7.5), 6, 8, new Color(7, 15, 22));
    shapesList.add(r1);
    shapesList.add(e1);

    assertEquals(shapesList, model5.getShapesAtKeyframe(3));

    ArrayList<Keyframe> keyframes = new ArrayList<>();
    keyframes.add(k5);
    assertEquals(keyframes, model5.getShapesAtKeyframe(3).get(0).getKeyframes());
    assertEquals(keyframes, model5.getShapesAtKeyframe(3).get(1).getKeyframes());

  }

  @Test
  public void testGetShapesAtMultMotion() {
    ExCELlenceOperations model5 = new ExCELlenceModel();
    model5.create("rect", "rectangle", new Posn(0, 0), 3, 5, new Color(0, 0, 0));
    model5.create("ellipse", "ellipse", new Posn(0, 0), 3, 5, new Color(0, 0, 0));
    model5.addMotion("rect", m7);
    model5.addMotion("ellipse", m7);
    model5.addMotion("rect", m5);
    model5.addMotion("ellipse", m5);

    ArrayList<IShape> shapesList = new ArrayList<>();
    Rectangle r1 = new Rectangle("rect", new Posn(17.5, 17.5), 10, 12, new Color(17, 27, 37));
    Ellipse e1 = new Ellipse("ellipse", new Posn(17.5, 17.5), 10, 12, new Color(17, 27, 37));

    shapesList.add(r1);
    shapesList.add(e1);

    assertEquals(shapesList, model5.getShapesAt(11));

    ArrayList<IMotion> motions = new ArrayList<>();
    motions.add(m5);
    motions.add(m7);
    assertEquals(motions, model5.getShapesAt(3).get(0).getMotions());
    assertEquals(motions, model5.getShapesAt(3).get(1).getMotions());
  }

  @Test
  public void testGetShapesAtKeyframeMultMotion() {
    ExCELlenceOperations model5 = new ExCELlenceModel();
    model5.create("rect", "rectangle", new Posn(0, 0), 3, 5, new Color(0, 0, 0));
    model5.create("ellipse", "ellipse", new Posn(0, 0), 3, 5, new Color(0, 0, 0));
    model5.addKeyframe("rect", k7);
    model5.addKeyframe("ellipse", k7);
    model5.addKeyframe("rect", k5);
    model5.addKeyframe("ellipse", k5);

    ArrayList<IShape> shapesList = new ArrayList<>();
    Rectangle r1 = new Rectangle("rect", new Posn(17.5, 17.5), 10, 12, new Color(17, 27, 37));
    Ellipse e1 = new Ellipse("ellipse", new Posn(17.5, 17.5), 10, 12, new Color(17, 27, 37));

    shapesList.add(r1);
    shapesList.add(e1);

    assertEquals(shapesList, model5.getShapesAtKeyframe(10));

    ArrayList<Keyframe> keyframes = new ArrayList<>();
    keyframes.add(k5);
    keyframes.add(k7);
    assertEquals(keyframes, model5.getShapesAtKeyframe(10).get(0).getKeyframes());
    assertEquals(keyframes, model5.getShapesAtKeyframe(10).get(1).getKeyframes());
  }

  @Test
  public void testGetShapesAtKeyframeTickGreaterThanLastKeyframeTime() {
    ExCELlenceOperations model5 = new ExCELlenceModel();
    model5.create("rect", "rectangle", new Posn(0, 0), 3, 5, new Color(0, 0, 0));
    model5.create("ellipse", "ellipse", new Posn(0, 0), 3, 5, new Color(0, 0, 0));
    model5.addKeyframe("rect", k7);
    model5.addKeyframe("ellipse", k7);
    model5.addKeyframe("rect", k5);
    model5.addKeyframe("ellipse", k5);

    ArrayList<IShape> shapesList = new ArrayList<>();
    Rectangle r1 = new Rectangle("rect", new Posn(20, 20), 11, 13, new Color(20, 30, 40));
    Ellipse e1 = new Ellipse("ellipse", new Posn(20, 20), 11, 13, new Color(20, 30, 40));

    shapesList.add(r1);
    shapesList.add(e1);

    assertEquals(shapesList, model5.getShapesAtKeyframe(1000));

    ArrayList<Keyframe> keyframes = new ArrayList<>();
    keyframes.add(k5);
    keyframes.add(k7);
    assertEquals(keyframes, model5.getShapesAtKeyframe(1000).get(0).getKeyframes());
    assertEquals(keyframes, model5.getShapesAtKeyframe(1000).get(1).getKeyframes());
  }

  @Test
  public void testGetShapesAtTickGreaterThanLastMotionTime() {
    ExCELlenceOperations model5 = new ExCELlenceModel();
    model5.create("rect", "rectangle", new Posn(0, 0), 3, 5, new Color(0, 0, 0));
    model5.create("ellipse", "ellipse", new Posn(0, 0), 3, 5, new Color(0, 0, 0));
    model5.addMotion("rect", m7);
    model5.addMotion("ellipse", m7);
    model5.addMotion("rect", m5);
    model5.addMotion("ellipse", m5);

    ArrayList<IShape> shapesList = new ArrayList<>();
    Rectangle r1 = new Rectangle("rect", new Posn(20, 20), 11, 13, new Color(20, 30, 40));
    Ellipse e1 = new Ellipse("ellipse", new Posn(20, 20), 11, 13, new Color(20, 30, 40));

    shapesList.add(r1);
    shapesList.add(e1);

    assertEquals(shapesList, model5.getShapesAt(1000));

    ArrayList<IMotion> motions = new ArrayList<>();
    motions.add(m5);
    motions.add(m7);
    assertEquals(motions, model5.getShapesAt(1000).get(0).getMotions());
    assertEquals(motions, model5.getShapesAt(1000).get(1).getMotions());
  }

  @Test
  public void testGetShapesAtTickBetweenMotions() {
    ExCELlenceOperations model5 = new ExCELlenceModel();
    model5.create("rect", "rectangle", new Posn(0, 0), 3, 5, new Color(0, 0, 0));
    model5.create("ellipse", "ellipse", new Posn(0, 0), 3, 5, new Color(0, 0, 0));
    model5.addMotion("rect", m6);
    model5.addMotion("ellipse", m6);
    model5.addMotion("rect", m5);
    model5.addMotion("ellipse", m5);

    ArrayList<IShape> shapesList = new ArrayList<>();
    Rectangle r1 = new Rectangle("rect", new Posn(10, 10), 7, 9, new Color(10, 20, 30));
    Ellipse e1 = new Ellipse("ellipse", new Posn(10, 10), 7, 9, new Color(10, 20, 30));

    shapesList.add(r1);
    shapesList.add(e1);

    assertEquals(shapesList, model5.getShapesAt(7));

    ArrayList<IMotion> motions = new ArrayList<>();
    motions.add(m5);
    motions.add(m6);
    assertEquals(motions, model5.getShapesAt(1000).get(0).getMotions());
    assertEquals(motions, model5.getShapesAt(1000).get(1).getMotions());
  }

}
