package cs3500.animator.view;


import cs3500.animator.model.ExCELlenceOperationsReadOnly;

import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;


/**
 * Represents a textBased view of the animation.
 */
public class TextualView implements IView {

  private ExCELlenceOperationsReadOnly model;
  private Appendable out;

  /**
   * Constructs a textual view.
   */
  public TextualView(ExCELlenceOperationsReadOnly model, Appendable out) {
    this.model = model;
    this.out = out;
  }


  @Override
  public String text() {
    throw new UnsupportedOperationException("Method not used for this view");
  }

  @Override
  public void showView() throws IOException {
    StringBuilder toPrint = new StringBuilder();
    toPrint.append(model.getState());
    out.append("canvas " + (int)model.getTopLeft().getX() + " " + (int)model.getTopLeft().getY()
            + " " + model.getWindowWidth() + " " + model.getWindowHeight() + "\n");
    out.append(toPrint.toString());
  }

  @Override
  public void refresh() {
    throw new UnsupportedOperationException(("text view outputs all at once, no refreshing"));
  }

  @Override
  public void setSpeed(int speed) {
    throw new UnsupportedOperationException("text view outputs all at once, can't set speed");
  }

  @Override
  public ExCELlenceOperationsReadOnly getModel() {
    return this.model;
  }


  @Override
  public void setListener(ActionListener listener, ListSelectionListener listener2) {
    throw new UnsupportedOperationException("No listeners in this view");
  }


  @Override
  public int getSpeed() {
    throw new UnsupportedOperationException("No commands in this view");
  }

  @Override
  public int getTick() {
    throw new UnsupportedOperationException("No commands in this view");
  }

  @Override
  public Timer getTimer() {
    throw new UnsupportedOperationException("No Timer in this view");
  }

  @Override
  public void pressPause() {
    throw new UnsupportedOperationException("No buttons in this view.");
  }

  @Override
  public void pressRestart() {
    throw new UnsupportedOperationException("No buttons in this view.");
  }

  @Override
  public void pressSpeedUp() {
    throw new UnsupportedOperationException("No buttons in this view.");
  }

  @Override
  public void pressSlowDown() {
    throw new UnsupportedOperationException("No buttons in this view.");
  }

  @Override
  public void pressLoop() {
    throw new UnsupportedOperationException("No buttons in this view.");
  }

  @Override
  public void updateModel(ExCELlenceOperationsReadOnly m) {
    throw new UnsupportedOperationException("No buttons in this view.");
  }

  @Override
  public JList getShapesKeyframesList() {
    throw new UnsupportedOperationException("No JList in this view");
  }

  @Override
  public JTextField getTextField() {
    throw new UnsupportedOperationException("No JTextField in this view");
  }

  @Override
  public JButton getDeleteShapeButton() {
    throw new UnsupportedOperationException("No buttons in this view.");
  }

  @Override
  public JButton getDeleteKeyframeButton() {
    throw new UnsupportedOperationException("No buttons in this view.");
  }

  @Override
  public JButton getModifyKeyframeButton() {
    throw new UnsupportedOperationException("No buttons in this view.");
  }

  @Override
  public boolean isLooping() {
    throw new UnsupportedOperationException("Looping not used in this view.");
  }

}

