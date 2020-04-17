package cs3500.animator.model;

import java.util.Objects;

/**
 * To represent a 2-D position.
 */
public class Posn {

  private double x;
  private double y;

  /**
   * Constructor for Posn class.
   *
   * @param x the x position
   * @param y the y position
   */
  public Posn(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * gets the x position of the Posn.
   *
   * @return x position
   */
  public double getX() {
    return this.x;
  }

  /**
   * gets the y position of the Posn.
   *
   * @return y position
   */
  public double getY() {
    return this.y;
  }

  /**
   * Determines if the other object is the same as this Posn or has same parameters.
   *
   * @param that the other object
   * @return whether that object is the same as this or has the same x and y values
   */
  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }
    if (!(that instanceof Posn)) {
      return false;
    }
    return Math.abs(this.getX() - ((Posn) that).getX()) < .0001 &&
        Math.abs(this.getY() - ((Posn) that).getY()) < .0001;
  }

  /**
   * Hashes x and y values of this Posn.
   *
   * @return hashcode of this Posn.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.x, this.y);
  }
}
