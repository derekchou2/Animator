package cs3500.animator.model;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * To represent an interface for shapes.
 */
public interface IShape {
  /**
   * Returns this IShape's name.
   * @return the name
   */
  String getName();

  /**
   * Sets this IShape's name to given value.
   * @param n the new name
   */
  void setName(String n);
  /**
   * Returns this IShape's Posn.
   * @return the Posn
   */

  Posn getPosn();

  /**
   * Sets this IShape's position to given value.
   * @param p the new position
   */
  void setPosn(Posn p);


  /**
   * Returns this IShape's width.
   * @return the width
   */
  double getWidth();

  /**
   * Sets this IShape's width to given value.
   * @param w the new width
   */
  void setWidth(double w);

  /**
   * Returns this IShape's height.
   * @return the height
   */
  double getHeight();

  /**
   * Sets this IShape's height to given value.
   * @param h the new height
   */
  void setHeight(double h);

  /**
   * Returns this IShape's color.
   * @return the color
   */
  IColor getColor();

  /**
   * Sets this IShape's color to given value.
   * @param c the new color
   */
  void setColor(IColor c);

  /**
   * Returns this IShape's list of motions.
   * @return the list of motions
   */
  ArrayList<IMotion> getMotions();

  /**
   * Returns this IShape's list of keyframes.
   * @return the list of keyframes
   */
  CopyOnWriteArrayList<Keyframe> getKeyframes();

  /**
   * Adds a given motion to this shape's list of Motions.
   * @param m represents a Motion.
   */
  void addMotion(IMotion m);

  /**
   * Removes a given motion to this shape's list of Motions.
   * @param m represents a Motion.
   * @throws IllegalArgumentException if m is not in shape's list of motions
   */
  void removeMotion(IMotion m);

  /**
   * Adds a given keyframe to this shape's list of keyframes.
   * @param k represents a keyframe.
   */
  void addKeyframe(Keyframe k);

  /**
   * Removes a given keyframe from this shape's list of keyframes.
   * @param k the keyframe to be removed
   * @throws IllegalArgumentException if k is not in shape's list of keyframes
   */
  void removeKeyframe(Keyframe k);

}
