package cs3500.animator.model;

/**
 * To represent an interface for motions.
 */
public interface IMotion {
  /**
   * Gets this motion's start time.
   * @return the start time
   */
  public int getT1();

  /**
   * Gets this motion's end time.
   * @return the end time
   */
  public int getT2();

  /**
   * Returns this Motion's end position.
   * @return the Posn
   */
  public Posn getPosn();

  /**
   * Returns this Motion's end width.
   * @return the width
   */
  public double getWidth();

  /**
   * Returns this Motion's end height.
   * @return the height
   */
  public double getHeight();

  /**
   * Returns this Motion's end color.
   * @return the color
   */
  public IColor getColor();

}
