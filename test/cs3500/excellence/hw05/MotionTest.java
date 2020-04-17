package cs3500.excellence.hw05;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import cs3500.animator.model.Color;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.Motion;
import cs3500.animator.model.Posn;

/**
 * Tests the Motion.
 */
public class MotionTest {

  private IMotion m0 = new Motion(1, 5, new Posn(20, 20), 5.5, 9.2, new Color(0, 255, 0));
  private IMotion m1 = new Motion(5, 10, new Posn(30.5, 30.5), 10.6, 10.6, new Color(255, 0, 0));
  private IMotion m2 = new Motion(9, 12, new Posn(20, 20), 5.49999, 9.19999, new Color(0, 255, 0));
  private IMotion m3 = new Motion(9, 12, new Posn(20, 20), 5.49999, 9.19999, new Color(0, 255, 0));
  private IMotion m4 = new Motion(-5, 10, new Posn(30.5, 30.5), 10, 10, new Color(255, 0, 0));

  @Test
  public void testEquals() {
    assertFalse(m3.equals(m4));
    assertFalse(m3.equals(m0));
    assertTrue(m2.equals(m3));

  }

  @Test
  public void testHashCode() {
    assertEquals(m2.hashCode(), m3.hashCode());
  }

  @Test
  public void testGetT1() {
    assertEquals(m0.getT1(), 1);
    assertEquals(m3.getT1(), 9);
  }

  @Test
  public void testGetT2() {
    assertEquals(m4.getT2(), 10);
    assertEquals(m2.getT2(), 12);
  }

  @Test
  public void testGetColor() {
    assertEquals(m4.getColor(), new Color(255, 0, 0));
    assertEquals(m2.getColor(), new Color(0, 255, 0));
  }

  @Test
  public void testGetWidth() {
    assertEquals(10, m4.getWidth(), 0.0001);
    assertEquals(5.49999, m2.getWidth(), 0.0001);
  }

  @Test
  public void testGetHeight() {
    assertEquals(10, m4.getHeight(), 0.0001);
    assertEquals(9.19999, m2.getHeight(), 0.0001);
  }

  @Test
  public void testToString() {
    assertEquals(m0.toString(), "5 20.0 20.0 5.5 9.2 0 255 0");
    assertEquals(m1.toString(), "10 30.5 30.5 10.6 10.6 255 0 0");
  }
}