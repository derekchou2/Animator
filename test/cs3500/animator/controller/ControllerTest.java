package cs3500.animator.controller;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;


import cs3500.animator.model.Color;
import cs3500.animator.model.ExCELlenceModel;
import cs3500.animator.model.ExCELlenceOperations;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.Motion;
import cs3500.animator.model.Posn;
import cs3500.animator.view.EditorView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.Timer;
import org.junit.Test;

/**
 * Implements a series of tests to ensure that the controller properly connects the model and View
 * to perform and handle ActionEvents.
 */
public class ControllerTest {

  private ExCELlenceOperations model = new ExCELlenceModel();

  private IMotion m0 = new Motion(1, 5, new Posn(20, 20), 5.5, 9.2, new Color(0, 255, 0));
  private IMotion m1 = new Motion(5, 10, new Posn(30.5, 30.5), 10.6, 10.6, new Color(0, 255, 0));
  private IMotion m2 = new Motion(5, 10, new Posn(30.5, 30.5), 10.6, 10.6, new Color(0, 255, 0));
  private IMotion m3 = new Motion(9, 12, new Posn(20.1, 20.1), 5.49999, 9.19999, new Color(0, 255,
          0));
  private IMotion m4 = new Motion(9, 12, new Posn(20.1, 20.1), 5.49999, 9.19999, new Color(0, 255,
          0));
  private IMotion m5 = new Motion(-5, 10, new Posn(30.5, 30.5), 10.5, 10.5, new Color(255, 0, 0));
  private IMotion m6 = new Motion(10, 20, new Posn(30.5, 30.5), 20.5, 20.5, new Color(255, 0, 0));
  private IMotion m7 = new Motion(50, 100, new Posn(30.5, 30.5), 20.5, 20.5, new Color(255, 0, 0));

  @Test
  public void testActionPerformed() throws InterruptedException {
    model.create("rect1", "rectangle", new Posn(0, 0), 5, 5, new Color(0, 0, 0));
    model.addMotion("rect1", m6);
    model.addMotion("rect1", m1);
    model.addMotion("rect1", m0);
    model.create("ellipse1", "ellipse",
            new Posn(0, 0), 5, 5, new Color(0, 0, 0));
    model.addMotion("ellipse1", m1);
    model.addMotion("ellipse1", m6);
    model.addMotion("ellipse1", m0);

    EditorView eView = new EditorView("Editor", model);
    JTextField input = eView.getTextField();

    long now = System.currentTimeMillis();

    ActionListener c = new Controller(model, eView, 1);
    ActionEvent restartEvent = new ActionEvent(eView, 1, "Restart", now, 0);
    ActionEvent pauseEvent = new ActionEvent(eView, 2, "Pause", now, 0);
    ActionEvent speedUpEvent = new ActionEvent(eView, 3, "Speed_Up", now, 0);
    ActionEvent speedDownEvent = new ActionEvent(eView, 4, "Speed_Down", now, 0);
    ActionEvent loopEvent = new ActionEvent(eView, 5, "Loop", now, 0);
    ActionEvent addShapeEvent = new ActionEvent(eView, 6, "Add_Shape", now, 0);
    ActionEvent addKeyframeEvent = new ActionEvent(eView, 6, "Add_Keyframe", now, 0);
    ActionEvent modifyKeyframeEvent = new ActionEvent(eView, 6, "Modify_Keyframe", now, 0);
    ActionEvent deleteShapeEvent = new ActionEvent(eView, 6, "Delete_Shape", now, 0);
    ActionEvent deleteKeyframeEvent = new ActionEvent(eView, 6, "Delete_Keyframe", now, 0);

    eView.setSpeed(1);

    c.actionPerformed(pauseEvent);
    Timer t = eView.getTimer();
    int panelTick = eView.getTick(); // Should be equal to the panel's tick.
    int speed = eView.getSpeed(); // Should be equal to the eView's speed.

    assertTrue(!t.isRunning());
    c.actionPerformed(pauseEvent);
    assertTrue(t.isRunning());

    c.actionPerformed(restartEvent);
    assertEquals(0, panelTick, 0.001); //Check that eView.panel.tick() == 0) since restart
    // performed.
    c.actionPerformed(speedUpEvent);
    assertEquals(500, t.getDelay(), 0.001); // Since speed started at a delay of 1000, x2 means
    // that the delay is not 1000 / 2 = 500.
    c.actionPerformed(speedDownEvent);
    assertEquals(1000, t.getDelay(), 0.001); // vice versa, speed x0.5 means that the
    // delay is not 500 / 0.5 = 1000\
    //assertEquals(panelTick, 10);

    assertEquals(eView.isLooping(), false);
    c.actionPerformed(loopEvent);
    assertTrue(t.isRunning());
    assertEquals(eView.isLooping(), true);

    input.setText("rectangle rect2 25 25 5.5 9.2 0 255 0");

    c.actionPerformed(addShapeEvent);
    assertEquals(model.getShapes().toString(), "{rect1=Rectangle: rect1 Posn: 0.0 0.0 Width: "
            + "5.0 Height: 5.0 Red: 0 Green: 0 Blue: 0," +
            " ellipse1=Ellipse: ellipse1 Posn: 0.0 0.0 Width:"
            + " 5.0 Height: 5.0 Red: 0 Green: 0 Blue: 0, rect2=Rectangle: " +
            "rect2 Posn: 25.0 25.0 Width: "
            + "5.5 Height: 9.2 Red: 0 Green: 255 Blue: 0}");

    input.setText("rect1 1 0 0 5 5 0 0 0");
    c.actionPerformed(addKeyframeEvent);
    input.setText("rect1 5 20 20 5.5 9.2 0 255 0");
    c.actionPerformed(addKeyframeEvent);
    input.setText("rect1 7 25 25 5.5 9.2 0 255 0");
    c.actionPerformed(addKeyframeEvent);
    input.setText("rect1 10 30.5 30.5 10.6 10.6 0 255 0");
    c.actionPerformed(addKeyframeEvent);
    input.setText("rect1 20 30.5 30.5 20.5 20.5 255 0 0");
    c.actionPerformed(addKeyframeEvent);

    assertEquals(model.getKeyframes("rect1").toString(), "[Time: 1 Posn: 0.0 0.0 "
            + "Width: 5.0 Height: 5.0 Red: 0 Green: 0 Blue: 0, Time: 5 Posn: 20.0 20.0 Width: 5.5 "
            + "Height: 9.2 Red: 0 Green: 255 Blue: 0, Time: 7 " +
            "Posn: 25.0 25.0 Width: 5.5 Height: 9.2 "
            + "Red: 0 Green: 255 Blue: 0, Time: 10 Posn: 30.5 30.5 Width: 10.6 Height: 10.6 Red: 0 "
            + "Green: 255 Blue: 0, Time: 20 Posn: 30.5 30.5 Width: 20.5 Height: " +
            "20.5 Red: 255 Green: " + "0 Blue: 0]");
  }

  @Test
  public void testValueChanged() {
    model.create("rect1", "rectangle", new Posn(0, 0), 5, 5, new Color(0, 0, 0));
    model.addMotion("rect1", m6);
    model.addMotion("rect1", m1);
    model.addMotion("rect1", m0);
    model.create("ellipse1", "ellipse",
            new Posn(0, 0), 5, 5, new Color(0, 0, 0));
    model.addMotion("ellipse1", m1);
    model.addMotion("ellipse1", m6);
    model.addMotion("ellipse1", m0);

    EditorView eView = new EditorView("Editor", model);
    JTextField input = eView.getTextField();
    JList keyFrames = eView.getShapesKeyframesList();
    JButton deleteShape = eView.getDeleteShapeButton();
    JButton deleteKeyframe = eView.getDeleteKeyframeButton();
    JButton modifyKeyframe = eView.getModifyKeyframeButton();

    input.setText("Time: 5 Posn: 20 20 Width: 5.5 Height: 9.2 Red: 0 Green: 255 Blue: 0");

    long now = System.currentTimeMillis();

    ActionListener c = new Controller(model, eView, 1);
    ActionEvent modifyKeyframeEvent = new ActionEvent(eView, 6, "Modify_Keyframe", now, 0);
    ActionEvent deleteShapeEvent = new ActionEvent(eView, 6, "Delete_Shape", now, 0);
    ActionEvent deleteKeyframeEvent = new ActionEvent(eView, 6, "Delete_Keyframe", now, 0);
    ActionEvent addKeyframeEvent = new ActionEvent(eView, 6, "Add_Keyframe", now, 0);

    input.setText("rect1 1 0 0 5 5 0 0 0");
    c.actionPerformed(addKeyframeEvent);
    input.setText("rect1 5 20 20 5.5 9.2 0 255 0");
    c.actionPerformed(addKeyframeEvent);
    input.setText("rect1 7 25 25 5.5 9.2 0 255 0");
    c.actionPerformed(addKeyframeEvent);
    input.setText("rect1 10 30.5 30.5 10.6 10.6 0 255 0");
    c.actionPerformed(addKeyframeEvent);
    input.setText("rect1 20 30.5 30.5 20.5 20.5 255 0 0");
    c.actionPerformed(addKeyframeEvent);

    eView.setSpeed(1);

    assertEquals(keyFrames.isSelectionEmpty(), true);
    assertEquals(deleteShape.isEnabled(), false);
    assertEquals(modifyKeyframe.isEnabled(), false);
    assertEquals(deleteKeyframe.isEnabled(), false);

  }
}