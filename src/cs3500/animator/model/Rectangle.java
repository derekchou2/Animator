package cs3500.animator.model;

import java.util.Objects;

/**
 * Represents a rectangle shape.
 */
public class Rectangle extends AShape {

  Posn topLeft;
  Posn topRight;
  Posn botLeft;
  Posn botRight;

  /**
   * Constructor for Rectangle class.
   *
   * @param n the name
   * @param p the position
   * @param w the width
   * @param h the height
   * @param c the color
   */
  public Rectangle(String n, Posn p, double w, double h, IColor c) {
    super(n, p, w, h, c);
    if (w <= 0 || h <= 0) {
      throw new IllegalArgumentException("Height and width must be greater than 0");
    }
    this.topLeft = this.position;
    this.topRight = new Posn(topLeft.getX() + width, topLeft.getY());
    this.botLeft = new Posn(topLeft.getX(), topLeft.getY() - height);
    this.botRight = new Posn(topRight.getX(), topRight.getY() - height);
  }

  /**
   * Determines if the other object is the same as this rectangle or has same parameters.
   *
   * @param that the other object
   * @return whether that object is the same as this or has the same width, height, color, Posn
   */
  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }
    if (!(that instanceof Rectangle)) {
      return false;
    }
    return this.getName().equals(((Rectangle) that).getName()) &&
            this.getPosn().equals(((Rectangle) that).getPosn()) &&
            Math.abs(this.width - ((Rectangle) that).width) < .0001 &&
            Math.abs(this.height - ((Rectangle) that).height) < .0001 &&
            this.color.equals(((Rectangle) that).color);
  }

  /**
   * Hashes position, width, height, color.
   *
   * @return hashcode of this rectangle.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.color, this.name);
  }
}
