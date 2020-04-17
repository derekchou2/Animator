package cs3500.excellence.hw05;

import org.junit.Test;


import java.util.ArrayList;

import cs3500.animator.model.Color;
import cs3500.animator.model.Ellipse;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Motion;
import cs3500.animator.model.Posn;
import cs3500.animator.model.Rectangle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Test cases for shapes, colors, motions equality, as well as adding motions to shapes.
 */
public class ShapeColorMotionTest {

  private IShape rect1 = new Rectangle("rect", new Posn(4.5, 5.8),
      5, 5, new Color(255, 0, 0));
  private IShape ellipse1 = new Ellipse("ellipse", new Posn(4.5, 5.8),
      5, 5, new Color(255, 0, 0));
  private IShape rect2 = new Rectangle("rect", new Posn(4.49999, 5.79999),
      5, 5, new Color(255, 0, 0));
  private IShape ellipse2 = new Ellipse("ellipse", new Posn(4.49999, 5.79999),
      5, 5, new Color(255, 0, 0));
  private IMotion m0 = new Motion(1, 5, new Posn(20, 20), 5.5, 9.2, new Color(0, 255, 0));
  private IMotion m1 = new Motion(5, 10, new Posn(30.5, 30.5), 10.6, 10.6, new Color(0, 255, 0));
  private IMotion m2 = new Motion(5, 10, new Posn(30.5, 30.5), 10.6, 10.6, new Color(0, 255, 0));


  @Test(expected = IllegalArgumentException.class)
  public void testInvalidColor() {
    IShape badShape = new Rectangle("bad", new Posn(0, 0), 5, 5, new Color(-1, 0, 0));
  }


  // non-positive size
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidShapeSize() {
    IShape badShape = new Rectangle("bad", new Posn(0, 0),
        0, 5, new Color(255, 0, 0));
  }

  @Test
  public void testSameParametersDiffShapes() {
    assertFalse(rect1.equals(ellipse1));
    assertFalse(ellipse1.equals(rect1));
  }

  @Test
  public void testSameRects() {
    assertTrue(rect1.equals(rect2));
    assertTrue(rect2.equals(rect1));
    assertTrue(rect1.equals(rect1));
  }

  @Test
  public void testSameEllipses() {
    assertTrue(ellipse1.equals(ellipse2));
    assertTrue(ellipse2.equals(ellipse1));
    assertTrue(ellipse1.equals(ellipse1));
  }

  @Test
  public void testSameColor() {
    assertTrue(rect1.getColor().equals(rect2.getColor()));
  }

  @Test
  public void testHashCode() {
    assertEquals(rect1.hashCode(), rect2.hashCode());
    assertEquals(ellipse1.hashCode(), ellipse2.hashCode());
    assertEquals(rect1.getColor().hashCode(), rect1.getColor().hashCode());
  }

  @Test
  public void testAddGetMotions() {
    assertEquals(new ArrayList<IMotion>(), rect1.getMotions());
    rect1.addMotion(m0);
    rect1.addMotion(m1);
    ArrayList<IMotion> motionList = new ArrayList<>();
    motionList.add(m0);
    motionList.add(m1);
    assertEquals(motionList, rect1.getMotions());
  }

}
