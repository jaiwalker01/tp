package seedu.address.ui;

import java.util.Set;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.employee.Employee;

/**
 * Panel containing the list of persons.
 */
public class EmployeeListPanel extends UiPart<Region> {
    private static final String FXML = "EmployeeListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(EmployeeListPanel.class);

    @FXML
    private ListView<Employee> employeeListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public EmployeeListPanel(ObservableList<Employee> personList) {
        super(FXML);
        employeeListView.setItems(personList);
        employeeListView.setCellFactory(listView -> new EmployeeListViewCell());

        Platform.runLater(() -> {
            ScrollBar verticalBar = getVerticalScrollBar(employeeListView);
            if (verticalBar != null) {
                employeeListView.addEventFilter(ScrollEvent.SCROLL, event -> {
                    if (event.getDeltaY() != 0) {
                        double scrollAmount = -event.getDeltaY() * 0.0008;
                        double newValue = clamp(
                                verticalBar.getValue() + scrollAmount,
                                verticalBar.getMin(),
                                verticalBar.getMax()
                        );
                        verticalBar.setValue(newValue);
                        event.consume();
                    }
                });
            }
        });
    }

    /**
     * Returns the vertical scrollbar of the given ListView, if present.
     */
    private ScrollBar getVerticalScrollBar(ListView<?> listView) {
        Set<Node> nodes = listView.lookupAll(".scroll-bar");
        for (Node node : nodes) {
            if (node instanceof ScrollBar) {
                ScrollBar bar = (ScrollBar) node;
                if (bar.getOrientation() == Orientation.VERTICAL) {
                    return bar;
                }
            }
        }
        return null;
    }

    /**
     * Clamps the value between min and max.
     */
    private double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Employee} using a {@code PersonCard}.
     */
    class EmployeeListViewCell extends ListCell<Employee> {
        @Override
        protected void updateItem(Employee person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new EmployeeCard(person, getIndex() + 1).getRoot());
            }
        }
    }

}
