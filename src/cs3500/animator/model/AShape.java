package cs3500.animator.model;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Represents abstract shape class.
 */
public abstract class AShape implements IShape {
  protected String name;
  protected Posn position;
  protected double width;
  protected double height;
  protected IColor color;
  private ArrayList<IMotion> motions = new ArrayList<>();
  private CopyOnWriteArrayList<Keyframe> keyframes = new CopyOnWriteArrayList<>();


  /**
   * Constructor for the shape class.
   * @param n the name
   * @param p the Posn
   * @param w the width
   * @param h the height
   * @param c the color
   */
  public AShape(String n, Posn p, double w, double h, IColor c) {
    this.name = n;
    this.position = p;
    this.width = w;
    this.height = h;
    this.color = c;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void setName(String n) {
    this.name = n;
  }

  @Override
  public Posn getPosn() {
    return this.position;
  }

  @Override
  public void setPosn(Posn p) {
    this.position = p;
  }

  @Override
  public double getWidth() {
    return this.width;
  }

  @Override
  public void setWidth(double w) {
    this.width = w;
  }

  @Override
  public double getHeight() {
    return this.height;
  }

  @Override
  public void setHeight(double h) {
    this.height = h;
  }

  @Override
  public IColor getColor() {
    return this.color;
  }

  @Override
  public void setColor(IColor c) {
    this.color = c;
  }

  @Override
  public ArrayList<IMotion> getMotions() {
    return this.motions;
  }

  @Override
  public CopyOnWriteArrayList<Keyframe> getKeyframes() {
    return this.keyframes;
  }

  @Override
  public void addMotion(IMotion m) {
    this.motions.add(m);
  }

  @Override
  public void addKeyframe(Keyframe k) {
    this.keyframes.add(k);
  }

  @Override
  public void removeMotion(IMotion m) {
    if (!this.motions.contains(m)) {
      throw new IllegalArgumentException("Given motion does not exist for this shape");
    }
    this.motions.remove(m);
  }

  @Override
  public void removeKeyframe(Keyframe k) {
    if (!this.keyframes.contains(k)) {
      throw new IllegalArgumentException("Given keyframe does not exist for this shape");
    }
    this.keyframes.remove(k);
  }

  @Override
  public String toString() {
    if (this instanceof Rectangle) {
      return "Rectangle: " + this.name + " "
              + "Posn: " + this.position.getX() + " " + this.position.getY() + " "
              + "Width: " + this.width + " "
              + "Height: " + this.height + " "
              + "Red: " + this.color.getRed() + " "
              + "Green: " + this.color.getGreen() + " "
              + "Blue: " + this.color.getBlue();
    }
    else {
      return "Ellipse: " + this.name + " "
              + "Posn: " + this.position.getX() + " " + this.position.getY() + " "
              + "Width: " + this.width + " "
              + "Height: " + this.height + " "
              + "Red: " + this.color.getRed() + " "
              + "Green: " + this.color.getGreen() + " "
              + "Blue: " + this.color.getBlue();
    }
  }
}
