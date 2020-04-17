package cs3500.animator.model;

import java.util.Objects;

/**
 * Represents an Ellipse shape.
 */
public class Ellipse extends AShape {
  Posn center;
  double xRadius;
  double yRadius;

  /**
   * Constructor for Ellipse class.
   *
   * @param n the name
   * @param p the position
   * @param w the width
   * @param h the height
   * @param c the color
   */
  public Ellipse(String n, Posn p, double w, double h, IColor c) {
    super(n, p, w, h, c);
    if (w <= 0 || h <= 0) {
      throw new IllegalArgumentException("Height and width must be greater than 0");
    }
    this.center = this.position;
    this.xRadius = this.width / 2;
    this.yRadius = this.height / 2;

  }

  /**
   * Determines if the other object is the same as this ellipse or has same parameters.
   *
   * @param that the other object
   * @return whether that object is the same as this or has the same width, height, color, Posn
   */
  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }
    if (!(that instanceof Ellipse)) {
      return false;
    }
    return this.getName().equals(((Ellipse) that).getName()) &&
            this.getPosn().equals(((Ellipse) that).getPosn()) &&
            Math.abs(this.width - ((Ellipse) that).width) < .0001 &&
            Math.abs(this.height - ((Ellipse) that).height) < .0001 &&
            this.color.equals(((Ellipse) that).color);
  }

  /**
   * Hashes position, width, height, color.
   *
   * @return hashcode of this Ellipse.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.color, this.name);
  }
}

