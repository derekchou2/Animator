package cs3500.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.NoSuchElementException;
import java.util.Scanner;


import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import cs3500.animator.model.Color;
import cs3500.animator.model.ExCELlenceOperations;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Keyframe;
import cs3500.animator.model.Posn;
import cs3500.animator.view.EditorView;

/**
 * Our implementation of a controller for the ExCELlence model, listening for the user's button
 * clicks and changing its view accordingly.
 */
public class Controller implements ActionListener, ListSelectionListener {
  private EditorView view;
  private ExCELlenceOperations model;


  /**
   * Constructor for controller, setting the model and view that it controls, with starting speed.
   * @param model the model the controller is manipulating
   * @param v the view the controller is controlling
   * @param speed starting speed
   */
  public Controller(ExCELlenceOperations model, EditorView v, int speed) {
    this.model = model;
    this.view = v;
    view.setListener(this, this);
    view.showView();
    view.setSpeed(speed);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Pause":
        view.pressPause();
        break;

      case "Speed_Up":
        view.pressSpeedUp();
        break;

      case "Speed_Down":
        view.pressSlowDown();
        break;

      case "Restart":
        view.pressRestart();
        break;

      case "Loop":
        view.pressLoop();
        break;

      case "Delete_Shape":
        // Remove from model shape with same name as toString value in JList
        for (IShape s : model.getShapes().values()) {
          if (view.getShapesKeyframesList().getSelectedValue().equals(s.toString())) {
            model.removeShape(s.getName());
          }
        }
        break;

      case "Delete_Keyframe":
        // Remove from model keyframe with same name as toString value in JList
        for (IShape s : model.getShapes().values()) {
          for (Keyframe k : s.getKeyframes()) {
            if (view.getShapesKeyframesList().getSelectedValue().equals(k.toString())) {
              s.removeKeyframe(k);
            }
          }
        }
        break;

      case "Add_Shape":
        this.addShape();
        break;

      case "Add_Keyframe":
        this.addKeyframe();
        break;

      case "Modify_Keyframe":
        this.modifyKeyframe();
        break;

      default:
        throw new IllegalArgumentException("No action with that command string");
    }
    view.updateModel(this.model);
  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
    if (!e.getValueIsAdjusting()) {

      if (view.getShapesKeyframesList().getSelectedIndex() == -1) {
        //No selection, can't delete shapes/keyframes or modify keyframes
        view.getDeleteKeyframeButton().setEnabled(false);
        view.getDeleteShapeButton().setEnabled(false);
        view.getModifyKeyframeButton().setEnabled(false);

      } else {
        //Selection, enable buttons
        view.getDeleteKeyframeButton().setEnabled(true);
        view.getDeleteShapeButton().setEnabled(true);
        view.getModifyKeyframeButton().setEnabled(true);
      }
    }
  }

  /**
   * Reads from input box once add shape button is pressed to create a shape of given parameters.
   * User must follow input format:
   * (Shapetype) name x y width height r g b
   * invalid input types or attempting to create invalid shape will notify user in text box
   * Users will also be notified of successful creation.
   */
  private void addShape() {
    try {

      Scanner s = new Scanner(view.getTextField().getText());
      String shapeType = s.next();
      String name = s.next();
      String xPos = s.next();
      String yPos = s.next();
      String width = s.next();
      String height = s.next();
      String red = s.next();
      String green = s.next();
      String blue = s.next();


      try {
        switch (shapeType) {
          case "rectangle":
            this.model.create(name, "rectangle", new Posn(Double.parseDouble(xPos),
                            Double.parseDouble(yPos)), Double.parseDouble(width),
                    Double.parseDouble(height),
                    new Color(Integer.parseInt(red), Integer.parseInt(green),
                            Integer.parseInt(blue)));
            this.view.getTextField().setText("new " + shapeType + " " + name + " added!");
            break;
          case "ellipse":
            this.model.create(name, "ellipse", new Posn(Double.parseDouble(xPos),
                            Double.parseDouble(yPos)), Double.parseDouble(width),
                    Double.parseDouble(height),
                    new Color(Integer.parseInt(red), Integer.parseInt(green),
                            Integer.parseInt(blue)));
            this.view.getTextField().setText("new " + shapeType + " " + name + " added!");

            break;
          default:
            throw new IllegalArgumentException("Shapetype must be rectangle or ellipse");
        }
      } catch (IllegalArgumentException ise) {
        this.view.getTextField().setText(ise.getMessage());
      }
    } catch (NoSuchElementException nse) {
      this.view.getTextField().setText("Add Shape format: (shapetype)" +
              " name x y width height r g b");
    }
  }

  /**
   * Reads from input box once add keyframe button is pressed
   * to create a keyframe of given parameters.
   * User must follow input format:
   * name time x y width height r g b
   * invalid input types or attempting to create invalid keyframe will notify user in text box
   * Users will also be notified of successful creation.
   */
  private void addKeyframe() {
    try {
      Scanner s = new Scanner(view.getTextField().getText());
      String name = s.next();
      String time = s.next();
      String xPos = s.next();
      String yPos = s.next();
      String width = s.next();
      String height = s.next();
      String red = s.next();
      String green = s.next();
      String blue = s.next();

      for (IShape shape : this.model.getShapes().values()) {
        try {
          if (shape.getName().equals(name)) {
            model.addKeyframe(name, new Keyframe(Integer.parseInt(time),
                    new Posn(Double.parseDouble(xPos), Double.parseDouble(yPos)),
                    Double.parseDouble(width), Double.parseDouble(height),
                    new Color(Integer.parseInt(red), Integer.parseInt(green),
                            Integer.parseInt(blue))));
            this.view.getTextField().setText("Keyframe added for " + name + " at time " + time);
          }
        } catch (IllegalArgumentException ise) {
          this.view.getTextField().setText(ise.getMessage());
        }
      }
    } catch (NoSuchElementException nse) {
      this.view.getTextField().setText("Add Keyframe format: name" +
              " time x y width height r g b");
    }
  }

  /**
   * Reads from input box once modify keyframe button is pressed
   * to modify selected keyframe with given parameters.
   * User must follow input format:
   * x y width height r g b
   * invalid input types or attempting to modify keyframe illegally will notify user in text box
   * Users will also be notified of successful modification of keyframe.
   */
  private void modifyKeyframe() {
    try {
      Scanner s = new Scanner(view.getTextField().getText());
      String xPos = s.next();
      String yPos = s.next();
      String width = s.next();
      String height = s.next();
      String red = s.next();
      String green = s.next();
      String blue = s.next();

      for (IShape shape : model.getShapes().values()) {
        for (Keyframe k : shape.getKeyframes()) {
          try {
            if (view.getShapesKeyframesList().getSelectedValue().equals(k.toString())) {
              k.setPosition(new Posn(Double.parseDouble(xPos), Double.parseDouble(yPos)));
              k.setWidth(Double.parseDouble(width));
              k.setHeight(Double.parseDouble(height));
              k.setColor(new Color(Integer.parseInt(red), Integer.parseInt(green),
                      Integer.parseInt(blue)));
              this.view.getTextField().setText("Modified keyframe at time " + k.getTime());
            }
          } catch (IllegalArgumentException ise) {
            this.view.getTextField().setText(ise.getMessage());
          }
        }
      }
    } catch (NoSuchElementException nse) {
      this.view.getTextField().setText("Modify Keyframe format: x" +
              " y width height r g b");
    }
  }

}
