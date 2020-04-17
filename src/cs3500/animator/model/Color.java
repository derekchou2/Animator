package cs3500.animator.model;

import java.util.Objects;

/**
 * Our class representing a color.
 */
public class Color implements IColor {
  private int red;
  private int green;
  private int blue;

  /**
   * Constructor for a color.
   * @param r the amount of red in the color
   * @param g the amount of green in the color
   * @param b the amount of blue in the color
   *
   * @throws IllegalArgumentException if any value is not between 0 and 255, inclusive
   */
  public Color(int r, int g, int b) {
    if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
      throw new IllegalArgumentException("rgb values must be between 0 and 255, inclusive");
    }
    this.red = r;
    this.green = g;
    this.blue = b;
  }

  public int getRed() {
    return this.red;
  }

  public int getBlue() {
    return this.blue;
  }

  public int getGreen() {
    return this.green;
  }

  /**
   * Determines if the other object is the same as this color or has same parameters.
   *
   * @param that the other object
   * @return whether that object is the same as this or has the same rgb values
   */
  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }
    if (!(that instanceof Color)) {
      return false;
    }
    return this.getRed() == ((Color) that).getRed() &&
        this.getBlue() == ((Color) that).getBlue() &&
        this.getGreen() == ((Color) that).getGreen();
  }

  /**
   * Hashes red, blue, and green values of this color.
   *
   * @return hashcode of this color.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.blue, this.red, this.green);
  }
}
