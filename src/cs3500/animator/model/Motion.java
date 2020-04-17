package cs3500.animator.model;

import java.util.Objects;

/**
 * Represents a motion to be carried out by a shape involving its start & end time, position change,
 * and color.
 */
public class Motion implements IMotion {

  private int t1;
  private int t2;
  private Posn position;
  private double width;
  private double height;
  private IColor color;

  /**
   * Constructor for a Motion representing the initial position, color and size of a shape and final
   * position, color and size of a shape during a specified period of time.
   *
   * @param t1 Represents the start time of the motion.
   * @param t2 Represents the end time of the motion.
   * @param p  Represents the end position of the motion.
   * @param w  Represents the width of the shape at the end of the motion.
   * @param h  Represents the height of the shape at the end of the motion.
   * @param c  Represent the color of the shape at the end of the motion.
   */
  public Motion(int t1, int t2, Posn p, double w, double h, IColor c) {
    if (w <= 0 || h <= 0) {
      throw new IllegalArgumentException("Width and Height must beb greater than 0.");
    }
    this.t1 = t1;
    this.t2 = t2;
    this.position = p;
    this.width = w;
    this.height = h;
    this.color = c;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof Motion)) {
      return false;
    }
    Motion that = (Motion) obj;

    return this.t1 == that.t1 && this.t2 == that.t2 &&
        Math.abs(this.width - (that).width) < .0001 &&
        Math.abs(this.height - (that).height) < .0001 &&
        this.position.equals(that.position) &&
        this.color.equals(that.color);
  }

  @Override
  public int hashCode() {
    return Objects.hash(t1, t2, color);
  }

  @Override
  public int getT1() {
    return this.t1;
  }

  @Override
  public int getT2() {
    return this.t2;
  }

  @Override
  public Posn getPosn() {
    return this.position;
  }

  @Override
  public double getWidth() {
    return this.width;
  }

  @Override
  public double getHeight() {
    return this.height;
  }

  @Override
  public IColor getColor() {
    return this.color;
  }

  @Override
  public String toString() {
    return "" + getT2() + " " + (int)getPosn().getX() + " " + (int)getPosn().getY() + " "
            + (int)getWidth() + " " + (int)getHeight() + " " + getColor().getRed() + " "
        + getColor().getGreen() + " " + getColor().getBlue();
  }
}
