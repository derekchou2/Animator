package cs3500.animator.model;

import java.util.Objects;

/**
 * Class representing a keyframe, or a snapshot of a shape's position and parameters
 * at a given time in the model.
 */
public class Keyframe {
  private int time;
  private Posn position;
  private double width;
  private double height;
  private IColor color;

  /**
   * Constructor for a keyframe representing a shape's parameters at time t.
   * @param t the time
   * @param p the position
   * @param w the width
   * @param h the height
   * @param c the color
   */
  public Keyframe(int t, Posn p, double w, double h, IColor c) {
    if (w <= 0 || h <= 0) {
      throw new IllegalArgumentException("Width and Height must be greater than 0.");
    }
    this.time = t;
    this.position = p;
    this.width = w;
    this.height = h;
    this.color = c;
  }

  /**
   * gets the time of this keyframe.
   * @return the time
   */
  public int getTime() {
    return this.time;
  }

  /**
   * gets the position of this keyframe.
   * @return the position
   */
  protected Posn getPosn() {
    return this.position;
  }

  /**
   * gets the width of this keyframe.
   * @return the width
   */
  protected double getWidth() {
    return this.width;
  }

  /**
   * gets the height of this keyframe.
   * @return the height
   */
  protected double getHeight() {
    return this.height;
  }

  /**
   * gets the color of this keyframe.
   * @return the color
   */
  protected IColor getColor() {
    return this.color;
  }

  /**
   * sets the time of this keyframe.
   * @param t the new time.
   */
  protected void setTime(int t) {
    this.time = t;
  }

  /**
   * sets the position of this keyframe.
   * @param p the new position.
   */
  public void setPosition(Posn p) {
    this.position = p;
  }

  /**
   * sets the width of this keyframe.
   * @param w the new width.
   */
  public void setWidth(double w) {
    if (w <= 0) {
      throw new IllegalArgumentException("Width must be greater than 0.");
    }
    this.width = w;
  }

  /**
   * sets the height of this keyframe.
   * @param h the new height.
   */
  public void setHeight(double h) {
    if (h <= 0) {
      throw new IllegalArgumentException("Height must be greater than 0.");
    }
    this.height = h;
  }

  /**
   * sets the color of this keyframe.
   * @param c the new color.
   */
  public void setColor(IColor c) {
    this.color = c;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof Keyframe)) {
      return false;
    }
    Keyframe that = (Keyframe) obj;

    return this.time == that.time &&
            Math.abs(this.width - (that).width) < .0001 &&
            Math.abs(this.height - (that).height) < .0001 &&
            this.position.equals(that.position) &&
            this.color.equals(that.color);
  }

  @Override
  public int hashCode() {
    return Objects.hash(time, color);
  }

  @Override
  public String toString() {
    return "" + "Time: " + getTime() + " Posn: " + getPosn().getX() + " " + getPosn().getY()
            + " Width: " + getWidth() + " Height: " + getHeight() + " Red: " + getColor().getRed()
            + " Green: " + getColor().getGreen() + " Blue: " + getColor().getBlue();
  }


}
