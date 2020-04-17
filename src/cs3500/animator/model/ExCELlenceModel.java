package cs3500.animator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import cs3500.animator.util.AnimationBuilder;


/**
 * Represents the model for the ExCELlence program that creates shapes and processes motions.
 */
public class ExCELlenceModel implements ExCELlenceOperations {

  private LinkedHashMap<String, IShape> shapes = new LinkedHashMap<>();

  // Default values to be set later by animationReader
  private Posn topLeft = new Posn(0, 0);
  private int width = 0;
  private int height = 0;


  @Override
  public void create(String name, String shape, Posn p, double w, double h, IColor c) {
    if (shapes.containsKey(name)) {
      throw new IllegalArgumentException("There is already a shape with that name");
    }
    switch (shape) {
      case "rectangle":
        Rectangle r = new Rectangle(name, p, w, h, c);
        shapes.put(name, r);
        break;
      case "ellipse":
        Ellipse e = new Ellipse(name, p, w, h, c);
        shapes.put(name, e);
        break;
      default:
        throw new IllegalArgumentException("shape must be 'rectangle' or 'ellipse'");
    }
  }

  @Override
  public void addMotion(String name, IMotion m) {
    if (!shapes.containsKey(name)) {
      throw new IllegalArgumentException("There is no shape represented by that key.");
    }
    ArrayList<Integer> times_t1 = new ArrayList<>();
    ArrayList<Integer> times_t2 = new ArrayList<>();

    ArrayList<IMotion> sortedMotions = new ArrayList<>();
    ArrayList<IMotion> copy = new ArrayList<>();
    copy.addAll(shapes.get(name).getMotions());
    copy.add(m);

    for (int i = 0; i < copy.size(); i++) {
      times_t1.add(copy.get(i).getT1());
    }
    Collections.sort(times_t1);

    for (int i = 0; i < copy.size(); i++) {
      times_t2.add(copy.get(i).getT2());
    }
    Collections.sort(times_t2);


    for (int i = 0; i < times_t1.size(); i++) {
      for (int j = 0; j < copy.size(); j++) {
        if ((copy.get(j).getT1() == (times_t1.get(i)))
                && (copy.get(j).getT2() == (times_t2.get(i)))) {
          sortedMotions.add(copy.get(j));
        }
      }
    }
    copy.clear();
    copy.addAll(sortedMotions);

    // Validates that the given motion doesn't create a time overlap before adding
    validateMotionList(copy);

    shapes.get(name).addMotion(m);
    this.sortMotions(name);
  }

  @Override
  public void addKeyframe(String name, Keyframe k) {
    if (!shapes.containsKey(name)) {
      throw new IllegalArgumentException("There is no shape represented by that key.");
    }

    // If empty list, can just add immediately
    if (this.getShape(name).getKeyframes().size() == 0) {
      shapes.get(name).addKeyframe(k);
      return;
    }

    // Else check if there exists a keyframe with same time as given already
    for (Keyframe kf : this.getKeyframes(name)) {
      if (kf.getTime() == k.getTime()) {
        throw new IllegalArgumentException("Cannot add keyframe, one exists at that time already" +
                ", modify or delete existing keyframe.");
      }
    }

    // If no exception thrown, safe to add keyframe, sort after
    shapes.get(name).addKeyframe(k);
    this.sortKeyframes(name);
  }

  @Override
  public void removeMotion(String name, IMotion m) {
    if (!shapes.containsKey(name)) {
      throw new IllegalArgumentException("There is no shape represented by that key.");
    }

    shapes.get(name).removeMotion(m);
  }

  @Override
  public void removeKeyframe(String name, Keyframe k) {
    if (!shapes.containsKey(name)) {
      throw new IllegalArgumentException("There is no shape represented by that key.");
    }

    shapes.get(name).removeKeyframe(k);
  }


  @Override
  public ArrayList<IMotion> getMotions(String name) {
    if (!shapes.containsKey(name)) {
      throw new IllegalArgumentException("There is no shape represented by that key.");
    }
    ArrayList<IMotion> copy = new ArrayList<>();
    copy.addAll(shapes.get(name).getMotions());
    return copy;
  }

  @Override
  public ArrayList<Keyframe> getKeyframes(String name) {
    if (!shapes.containsKey(name)) {
      throw new IllegalArgumentException("There is no shape represented by that key.");
    }
    ArrayList<Keyframe> copy = new ArrayList<>();
    copy.addAll(shapes.get(name).getKeyframes());
    return copy;
  }


  @Override
  public void sortMotions(String name) {
    if (!shapes.containsKey(name)) {
      throw new IllegalArgumentException("There is no shape represented by that key.");
    }
    ArrayList<Integer> times_t1 = new ArrayList<>();
    ArrayList<Integer> times_t2 = new ArrayList<>();
    ArrayList<IMotion> m = shapes.get(name).getMotions();
    ArrayList<IMotion> sortedMotions = new ArrayList<>();

    for (int i = 0; i < m.size(); i++) {
      times_t1.add(m.get(i).getT1());
    }
    for (int i = 0; i < m.size(); i++) {
      times_t2.add(m.get(i).getT2());
    }

    Collections.sort(times_t1);
    Collections.sort(times_t2);

    for (int i = 0; i < times_t1.size(); i++) {
      for (int j = 0; j < m.size(); j++) {
        if ((m.get(j).getT1() == (times_t1.get(i)))
                && (m.get(j).getT2() == (times_t2.get(i)))) {
          sortedMotions.add(m.get(j));
        }
      }
    }
    m.clear();
    m.addAll(sortedMotions);
  }

  @Override
  public void sortKeyframes(String name) {
    if (!shapes.containsKey(name)) {
      throw new IllegalArgumentException("There is no shape represented by that key.");
    }
    ArrayList<Integer> times = new ArrayList<>();
    CopyOnWriteArrayList<Keyframe> k = shapes.get(name).getKeyframes();
    ArrayList<Keyframe> sortedKeyframes = new ArrayList<>();

    for (int i = 0; i < k.size(); i++) {
      times.add(k.get(i).getTime());
    }

    Collections.sort(times);

    for (int i = 0; i < times.size(); i++) {
      for (int j = 0; j < k.size(); j++) {
        if ((k.get(j).getTime() == (times.get(i)))) {
          sortedKeyframes.add(k.get(j));
        }
      }
    }
    k.clear();
    k.addAll(sortedKeyframes);

  }


  @Override
  public void validateMotions(String name) {
    this.sortMotions(name);

    ArrayList<IMotion> motions = shapes.get(name).getMotions();
    validateMotionList(motions);
  }

  @Override
  public void setTopLeft(Posn p) {
    this.topLeft = p;
  }

  @Override
  public void setWindowWidth(int w) {
    this.width = w;
  }

  @Override
  public void setWindowHeight(int h) {
    this.height = h;
  }

  /**
   * Ensures times aren't overalpping for a list of motions.
   *
   * @param motions represents a given ArrayList<IMotion></IMotion>
   * @throws IllegalStateException if adjacent motions overlap times.
   */
  private void validateMotionList(ArrayList<IMotion> motions) {
    for (int i = 1; i < motions.size(); i++) {
      if (motions.get(i).getT1() < motions.get(i - 1).getT2()) {
        throw new IllegalStateException("Motion times cannot overlap");
      }
    }
  }

  @Override
  public ArrayList<IShape> getShapesAt(int tick) {
    if (this.shapes.size() == 0) {
      throw new IllegalStateException("There are no shapes in the model");
    }

    for (String name : shapes.keySet()) {
      this.validateMotions(name);
    }
    ArrayList<IShape> shapesList = new ArrayList<>();

    IShape newShape;
    for (IShape s : shapes.values()) {
      // If shape has no motions, just return a copy, regardless of tick value
      if (s.getMotions().size() == 0) {
        newShape = getShape(s);
        shapesList.add(newShape);
      }

      // If no motion exists in s that contains tick, find last completed motion before tick
      // and use it's parameters for newShape
      if (!tickWithinShape(s, tick)) {
        ArrayList<IMotion> reverseList = new ArrayList<>();
        reverseList.addAll(s.getMotions());
        Collections.reverse(reverseList);

        for (int i = 0; i < reverseList.size(); i++) {
          if (reverseList.get(i).getT2() < tick) {
            IMotion mostRcnt = reverseList.get(i);

            if (s instanceof Rectangle) {
              newShape = new Rectangle(s.getName(), mostRcnt.getPosn(), mostRcnt.getWidth(),
                      mostRcnt.getHeight(), mostRcnt.getColor());
            } else {
              newShape = new Ellipse(s.getName(), mostRcnt.getPosn(), mostRcnt.getWidth(),
                      mostRcnt.getHeight(), mostRcnt.getColor());
            }
            for (IMotion mot : s.getMotions()) {
              newShape.addMotion(mot);
            }
            shapesList.add(newShape);
            break;
          }
        }
      }

      // Motion exists in s that contains tick, find which one
      Iterator<IMotion> iterator = s.getMotions().iterator();
      while (iterator.hasNext()) {
        IMotion m = iterator.next();
        if (tickWithinMotion(m, tick)) {
          // If first motion, use it and shape's parameters in interpolate function
          if (s.getMotions().indexOf(m) == 0) {
            if (s instanceof Rectangle) {
              newShape = new Rectangle(s.getName(),
                      new Posn(interpolate(tick, m.getT1(), m.getT2(), s.getPosn().getX(),
                              m.getPosn().getX()),
                              interpolate(tick, m.getT1(), m.getT2(), s.getPosn().getY(),
                                      m.getPosn().getY())),
                      interpolate(tick, m.getT1(), m.getT2(), s.getWidth(), m.getWidth()),
                      interpolate(tick, m.getT1(), m.getT2(), s.getHeight(), m.getHeight()),
                      new Color((int) interpolate(tick, m.getT1(), m.getT2(),
                              s.getColor().getRed(), m.getColor().getRed()),
                              (int) interpolate(tick, m.getT1(), m.getT2(),
                                      s.getColor().getGreen(), m.getColor().getGreen()),
                              (int) interpolate(tick, m.getT1(), m.getT2(),
                                      s.getColor().getBlue(), m.getColor().getBlue()))
              );
            } else {
              newShape = new Ellipse(s.getName(),
                      new Posn(interpolate(tick, m.getT1(), m.getT2(), s.getPosn().getX(),
                              m.getPosn().getX()),
                              interpolate(tick, m.getT1(), m.getT2(), s.getPosn().getY(),
                                      m.getPosn().getY())),
                      interpolate(tick, m.getT1(), m.getT2(), s.getWidth(), m.getWidth()),
                      interpolate(tick, m.getT1(), m.getT2(), s.getHeight(), m.getHeight()),
                      new Color((int) interpolate(tick, m.getT1(), m.getT2(),
                              s.getColor().getRed(), m.getColor().getRed()),
                              (int) interpolate(tick, m.getT1(), m.getT2(),
                                      s.getColor().getGreen(), m.getColor().getGreen()),
                              (int) interpolate(tick, m.getT1(), m.getT2(),
                                      s.getColor().getBlue(), m.getColor().getBlue()))
              );
            }
          }
          // If not first motion, use it and previous motion in interpolate function
          else {
            IMotion mprev = s.getMotions().get(s.getMotions().indexOf(m) - 1);
            if (s instanceof Rectangle) {
              newShape = new Rectangle(s.getName(),
                      new Posn(interpolate(tick, m.getT1(), m.getT2(), mprev.getPosn().getX(),
                              m.getPosn().getX()),
                              interpolate(tick, m.getT1(), m.getT2(), mprev.getPosn().getY(),
                                      m.getPosn().getY())),
                      interpolate(tick, m.getT1(), m.getT2(), mprev.getWidth(), m.getWidth()),
                      interpolate(tick, m.getT1(), m.getT2(), mprev.getHeight(), m.getHeight()),
                      new Color((int) interpolate(tick, m.getT1(), m.getT2(),
                              mprev.getColor().getRed(), m.getColor().getRed()),
                              (int) interpolate(tick, m.getT1(), m.getT2(),
                                      mprev.getColor().getGreen(), m.getColor().getGreen()),
                              (int) interpolate(tick, m.getT1(), m.getT2(),
                                      mprev.getColor().getBlue(), m.getColor().getBlue()))
              );
            } else {
              newShape = new Ellipse(s.getName(),
                      new Posn(interpolate(tick, m.getT1(), m.getT2(), mprev.getPosn().getX(),
                              m.getPosn().getX()),
                              interpolate(tick, m.getT1(), m.getT2(), mprev.getPosn().getY(),
                                      m.getPosn().getY())),
                      interpolate(tick, m.getT1(), m.getT2(), mprev.getWidth(), m.getWidth()),
                      interpolate(tick, m.getT1(), m.getT2(), mprev.getHeight(), m.getHeight()),
                      new Color((int) interpolate(tick, m.getT1(), m.getT2(),
                              mprev.getColor().getRed(), m.getColor().getRed()),
                              (int) interpolate(tick, m.getT1(), m.getT2(),
                                      mprev.getColor().getGreen(), m.getColor().getGreen()),
                              (int) interpolate(tick, m.getT1(), m.getT2(),
                                      mprev.getColor().getBlue(), m.getColor().getBlue()))
              );
            }
          }

          for (IMotion mot : s.getMotions()) {
            newShape.addMotion(mot);
          }
          shapesList.add(newShape);
          break;
        }
      }
    }
    return shapesList;
  }

  @Override
  public ArrayList<IShape> getShapesAtKeyframe(int tick) {
    if (this.shapes.size() == 0) {
      throw new IllegalStateException("There are no shapes in the model");
    }

    ArrayList<IShape> shapesList = new ArrayList<>();

    IShape newShape;
    for (IShape s : shapes.values()) {
      // If shape has no keyframes, just return a copy, regardless of tick value
      if (s.getKeyframes().size() == 0) {
        newShape = getShape(s);
        shapesList.add(newShape);
      } else { // Shape has keyframes

        // If tick is greater than time of last keyframe, use last keyframe's parameters
        if (tick > s.getKeyframes().get(s.getKeyframes().size() - 1).getTime()) {
          Keyframe lastKF = s.getKeyframes().get(s.getKeyframes().size() - 1);
          if (s instanceof Rectangle) {
            newShape = new Rectangle(s.getName(), lastKF.getPosn(), lastKF.getWidth(),
                    lastKF.getHeight(), lastKF.getColor());
          } else {
            newShape = new Ellipse(s.getName(), lastKF.getPosn(), lastKF.getWidth(),
                    lastKF.getHeight(), lastKF.getColor());
          }

          for (IMotion mot : s.getMotions()) {
            newShape.addMotion(mot);
          }
          for (Keyframe kf : s.getKeyframes()) {
            newShape.addKeyframe(kf);
          }
          shapesList.add(newShape);
        }
      }

      // Must interpolate between 2 keyframes, find which 2
      Iterator<Keyframe> iterator = s.getKeyframes().iterator();
      while (iterator.hasNext()) {
        Keyframe k = iterator.next();

        if (tick < k.getTime()) {
          // If first Keyframe, use it and shape's parameters in interpolate function
          if (s.getKeyframes().indexOf(k) == 0) {
            if (s instanceof Rectangle) {
              newShape = new Rectangle(s.getName(),
                      new Posn(interpolate(tick, 0, k.getTime(), s.getPosn().getX(),
                              k.getPosn().getX()),
                              interpolate(tick, 0, k.getTime(), s.getPosn().getY(),
                                      k.getPosn().getY())),
                      interpolate(tick, 0, k.getTime(), s.getWidth(), k.getWidth()),
                      interpolate(tick, 0, k.getTime(), s.getHeight(), k.getHeight()),
                      new Color((int) interpolate(tick, 0, k.getTime(),
                              s.getColor().getRed(), k.getColor().getRed()),
                              (int) interpolate(tick, 0, k.getTime(),
                                      s.getColor().getGreen(), k.getColor().getGreen()),
                              (int) interpolate(tick, 0, k.getTime(),
                                      s.getColor().getBlue(), k.getColor().getBlue()))
              );
            } else {
              newShape = new Ellipse(s.getName(),
                      new Posn(interpolate(tick, 0, k.getTime(), s.getPosn().getX(),
                              k.getPosn().getX()),
                              interpolate(tick, 0, k.getTime(), s.getPosn().getY(),
                                      k.getPosn().getY())),
                      interpolate(tick, 0, k.getTime(), s.getWidth(), k.getWidth()),
                      interpolate(tick, 0, k.getTime(), s.getHeight(), k.getHeight()),
                      new Color((int) interpolate(tick, 0, k.getTime(),
                              s.getColor().getRed(), k.getColor().getRed()),
                              (int) interpolate(tick, 0, k.getTime(),
                                      s.getColor().getGreen(), k.getColor().getGreen()),
                              (int) interpolate(tick, 0, k.getTime(),
                                      s.getColor().getBlue(), k.getColor().getBlue()))
              );
            }
          }
          // If not first keyframe, use it and previous keyframe in interpolate function
          else {
            Keyframe kprev = s.getKeyframes().get(s.getKeyframes().indexOf(k) - 1);
            if (s instanceof Rectangle) {
              newShape = new Rectangle(s.getName(),
                      new Posn(interpolate(tick, kprev.getTime(), k.getTime(),
                              kprev.getPosn().getX(), k.getPosn().getX()),
                              interpolate(tick, kprev.getTime(), k.getTime(),
                                      kprev.getPosn().getY(), k.getPosn().getY())),
                      interpolate(tick, kprev.getTime(), k.getTime(), kprev.getWidth(),
                              k.getWidth()), interpolate(tick, kprev.getTime(), k.getTime(),
                      kprev.getHeight(), k.getHeight()),
                      new Color((int) interpolate(tick, kprev.getTime(), k.getTime(),
                              kprev.getColor().getRed(), k.getColor().getRed()),
                              (int) interpolate(tick, kprev.getTime(), k.getTime(),
                                      kprev.getColor().getGreen(), k.getColor().getGreen()),
                              (int) interpolate(tick, kprev.getTime(), k.getTime(),
                                      kprev.getColor().getBlue(), k.getColor().getBlue()))
              );
            } else {
              newShape = new Ellipse(s.getName(),
                      new Posn(interpolate(tick, kprev.getTime(), k.getTime(),
                              kprev.getPosn().getX(), k.getPosn().getX()),
                              interpolate(tick, kprev.getTime(), k.getTime(),
                                      kprev.getPosn().getY(), k.getPosn().getY())),
                      interpolate(tick, kprev.getTime(), k.getTime(), kprev.getWidth(),
                              k.getWidth()),
                      interpolate(tick, kprev.getTime(), k.getTime(), kprev.getHeight(),
                              k.getHeight()),
                      new Color((int) interpolate(tick, kprev.getTime(), k.getTime(),
                              kprev.getColor().getRed(), k.getColor().getRed()),
                              (int) interpolate(tick, kprev.getTime(), k.getTime(),
                                      kprev.getColor().getGreen(), k.getColor().getGreen()),
                              (int) interpolate(tick, kprev.getTime(), k.getTime(),
                                      kprev.getColor().getBlue(), k.getColor().getBlue()))
              );
            }
          }

          for (IMotion mot : s.getMotions()) {
            newShape.addMotion(mot);
          }
          for (Keyframe kf : s.getKeyframes()) {
            newShape.addKeyframe(kf);
          }
          shapesList.add(newShape);
          break;
        }
      }

    }
    return shapesList;
  }

  /**
   * General interpolation function to find value of variable in between times ta and tb.
   *
   * @param t  time at which we are calculating interpolated value, between ta and tb
   * @param ta beginning of time interval
   * @param tb end of time interval
   * @param a  value at ta
   * @param b  value at tb
   * @return interpolated value at time t
   */
  public double interpolate(double t, double ta, double tb, double a, double b) {
    return (a * ((tb - t) / (tb - ta))) + (b * ((t - ta) / (tb - ta)));
  }

  /**
   * Returns whether given tick exists in time interval of given motion.
   *
   * @param m    the motion we are checking
   * @param tick the tick
   * @return is tick in motion interval
   */
  private boolean tickWithinMotion(IMotion m, int tick) {
    return (tick >= m.getT1()) && (tick <= m.getT2());
  }

  /**
   * Returns whether tick is within any motion interval of given shape.
   *
   * @param s    the given shape
   * @param tick the tick
   * @return is tick in motion interval of shape
   */
  private boolean tickWithinShape(IShape s, int tick) {
    for (IMotion m : s.getMotions()) {
      if (tickWithinMotion(m, tick)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String getKey(IShape s) {
    for (Map.Entry<String, IShape> entry : this.shapes.entrySet()) {
      if (entry.getValue().equals(s)) {
        return entry.getKey();
      }
    }
    throw new IllegalArgumentException("Given shape does not exist in model's list");
  }

  /**
   * Returns new shape with same parameters as given shape.
   *
   * @param shape given shape
   * @return new shape, same as given
   */
  private IShape getShape(IShape shape) {
    if (shape instanceof Rectangle) {
      IShape copy = new Rectangle(shape.getName(), shape.getPosn(),
              shape.getWidth(),
              shape.getHeight(),
              shape.getColor());
      for (IMotion m : shape.getMotions()) {
        copy.addMotion(m);
      }
      return copy;
    } else {
      IShape copy = new Ellipse(shape.getName(), shape.getPosn(),
              shape.getWidth(),
              shape.getHeight(),
              shape.getColor());
      for (IMotion m : shape.getMotions()) {
        copy.addMotion(m);
      }
      return copy;
    }
  }

  @Override
  public IShape getShape(String name) {
    if (!shapes.containsKey(name)) {
      throw new IllegalArgumentException("There is no shape represented by that key.");
    }
    if (shapes.get(name) instanceof Rectangle) {
      IShape copy = new Rectangle((shapes.get(name)).getName(), (shapes.get(name)).getPosn(),
              (shapes.get(name)).getWidth(),
              (shapes.get(name)).getHeight(),
              shapes.get(name).getColor());
      for (IMotion m : shapes.get(name).getMotions()) {
        copy.addMotion(m);
      }
      for (Keyframe k : shapes.get(name).getKeyframes()) {
        copy.addKeyframe(k);
      }
      return copy;
    } else {
      IShape copy = new Ellipse((shapes.get(name)).getName(), (shapes.get(name)).getPosn(),
              (shapes.get(name)).getWidth(),
              (shapes.get(name)).getHeight(),
              shapes.get(name).getColor());
      for (IMotion m : shapes.get(name).getMotions()) {
        copy.addMotion(m);
      }
      for (Keyframe k : shapes.get(name).getKeyframes()) {
        copy.addKeyframe(k);
      }
      return copy;
    }
  }

  @Override
  public void removeShape(String name) {
    if (!shapes.containsKey(name)) {
      throw new IllegalArgumentException("There is no shape represented by that key.");
    }
    shapes.remove(name);
  }

  @Override
  public String getState() {
    for (String name : shapes.keySet()) {
      this.sortMotions(name);
      this.validateMotions(name);
    }

    String state = "";
    for (IShape s : shapes.values()) {
      if (s instanceof Rectangle) {
        state = state + "shape " + getKey(s) + " rectangle" + "\n";
        state = state + "motion " + getKey(s) + " " + s.getMotions().get(0).getT1() + " " +
                (int) s.getPosn().getX() + " " + (int) s.getPosn().getY() + " " + (int) s.getWidth()
                + " " + (int) s.getHeight() + " " + s.getColor().getRed() + " "
                + s.getColor().getGreen() + " " + s.getColor().getBlue() + "     "
                + s.getMotions().get(0).toString() + "\n";
      }
      if (s instanceof Ellipse) {
        state = state + "shape " + getKey(s) + " ellipse" + "\n";
        state = state + "motion " + getKey(s) + " " + s.getMotions().get(0).getT1() + " " +
                (int) s.getPosn().getX()
                + " " + (int) s.getPosn().getY() + " " + (int) s.getWidth() + " "
                + (int) s.getHeight() + " " + s.getColor().getRed() + " " + s.getColor().getGreen()
                + " " + s.getColor().getBlue() + "     " + s.getMotions().get(0).toString() + "\n";
      }

      for (IMotion m : s.getMotions()) {
        if (s.getMotions().indexOf(m) == 0) {
          // Don't print if first motion in list of motions
        } else {
          if (s instanceof Rectangle) {
            state = state + "motion " + getKey(s) + " "
                    + s.getMotions().get(s.getMotions().indexOf(m) - 1).toString()
                    + "     " + m.toString() + "\n";
          }
          if (s instanceof Ellipse) {
            state = state + "motion " + getKey(s) + " "
                    + s.getMotions().get(s.getMotions().indexOf(m) - 1).toString()
                    + "     " + m.toString() + "\n";
          }
        }
      }
    }
    return state;
  }

  @Override
  public Posn getTopLeft() {
    return new Posn(this.topLeft.getX(), this.topLeft.getY());
  }

  @Override
  public int getWindowWidth() {
    return this.width;
  }

  @Override
  public int getWindowHeight() {
    return this.height;
  }

  @Override
  public HashMap<String, IShape> getShapes() {
    for (String name : shapes.keySet()) {
      this.sortMotions(name);
      this.validateMotions(name);
    }
    HashMap<String, IShape> s = (HashMap<String, IShape>) shapes.clone();
    return s;
  }

  /**
   * Builder class for our Excellence Model. AnimationReader will call methods from this class to
   * instantiate a model from the input file.
   */
  public static final class Builder implements AnimationBuilder<ExCELlenceOperations> {
    private ExCELlenceOperations model;


    public Builder() {
      this.model = new ExCELlenceModel();
    }

    @Override
    public ExCELlenceOperations build() {
      return this.model;
    }

    @Override
    public AnimationBuilder<ExCELlenceOperations> setBounds(int x, int y, int width, int height) {
      model.setTopLeft(new Posn(x, y));
      model.setWindowHeight(height);
      model.setWindowWidth(width);
      return this;
    }

    @Override
    public AnimationBuilder<ExCELlenceOperations> declareShape(String name, String type) {
      model.create(name, type, new Posn(0, 0), 1, 1, new Color(0, 0, 0));
      return this;
    }

    @Override
    public AnimationBuilder<ExCELlenceOperations> addMotion(String name, int t1, int x1, int y1,
                                                            int w1, int h1, int r1, int g1, int b1,
                                                            int t2, int x2, int y2, int w2, int h2,
                                                            int r2, int g2, int b2) {
      model.addMotion(name, new Motion(t1, t2, new Posn(x2, y2), w2, h2, new Color(r2, g2, b2)));
      model.addKeyframe(name, new Keyframe(t2, new Posn(x2, y2), w2, h2, new Color(r2, g2, b2)));
      return this;
    }

    @Override
    // do nothing for now
    public AnimationBuilder<ExCELlenceOperations> addKeyframe(String name, int t, int x, int y,
                                                              int w, int h, int r, int g, int b) {
      return this;
    }
  }
}
