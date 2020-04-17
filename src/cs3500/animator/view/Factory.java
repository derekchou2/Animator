package cs3500.animator.view;

import cs3500.animator.model.ExCELlenceOperations;

/**
 * Factory class for creating 3 types of views: textual, visual, or svg based on provided String.
 */
public class Factory {
  private IView view;


  /**
   * Constructor for setting up view based on provided type.
   * @param type the type of view we want to create
   * @param model the model that the view will show
   */
  public Factory(String type, ExCELlenceOperations model) {
    if (model == null) {
      throw new IllegalArgumentException("model can't be null");
    }

    ExCELlenceOperations m;
    Appendable out;

    m = model;
    out = System.out;

    if (type.equals("text")) {
      this.view = new TextualView(m, out);
    }

    else if (type.equals("visual")) {
      this.view = new VisualView("animator", m);
    }

    else if (type.equals("svg")) {
      this.view = new SVGView(m, 1, out);
    }

    else if (type.equals("edit")) {
      this.view = new EditorView("editor animator", m);
    }

    else {
      throw new IllegalArgumentException("type must be text, visual, edit, or svg");
    }
  }

  /**
   * Gets the view from this factory.
   * @return the view
   */
  public IView getView() {
    return this.view;
  }

}
