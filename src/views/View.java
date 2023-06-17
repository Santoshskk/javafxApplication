package views;

import javafx.scene.Parent;

/**
 * The type View.
 */
public abstract class View {
    private Parent root;

    /**
     * Instantiates a new View.
     */
    public View(){
        this.root=initializeView();
    }

    /**
     * Initialize view parent.
     *
     * @return the parent
     */
    protected abstract Parent initializeView();

    /**
     * Gets root.
     *
     * @return the root
     */
    public Parent getRoot() {
        return root;
    }
}

