package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.input.Clipboard;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelpWindowTest {

    private static final long FX_TIMEOUT_SECONDS = 5;

    @BeforeAll
    public static void setUpFxToolkit() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        try {
            Platform.startup(() -> {
                Platform.setImplicitExit(false);
                latch.countDown();
            });
        } catch (IllegalStateException e) {
            Platform.setImplicitExit(false);
            latch.countDown();
        }
        assertTrue(latch.await(FX_TIMEOUT_SECONDS, TimeUnit.SECONDS));
    }

    @Test
    public void constructor_populatesHelpSections() throws Exception {
        HelpWindow helpWindow = runOnFxThread(() -> new HelpWindow(new Stage()));

        VBox helpSections = getPrivateField(helpWindow, "helpSections", VBox.class);

        assertEquals(11, helpSections.getChildren().size());
    }

    @Test
    public void copyUrl_copiesUserGuideUrlToClipboard() throws Exception {
        HelpWindow helpWindow = runOnFxThread(() -> new HelpWindow(new Stage()));
        Button copyButton = getPrivateField(helpWindow, "copyButton", Button.class);

        runOnFxThread(() -> {
            copyButton.fire();
            return null;
        });

        String clipboardContent = runOnFxThread(() -> Clipboard.getSystemClipboard().getString());
        assertEquals(HelpWindow.USERGUIDE_URL, clipboardContent);
    }

    @Test
    public void showAndHide_updatesShowingState() throws Exception {
        HelpWindow helpWindow = runOnFxThread(() -> new HelpWindow(new Stage()));

        runOnFxThread(() -> {
            helpWindow.show();
            return null;
        });
        assertTrue(runOnFxThread(helpWindow::isShowing));

        runOnFxThread(() -> {
            helpWindow.focus();
            return null;
        });

        runOnFxThread(() -> {
            helpWindow.hide();
            return null;
        });
        assertFalse(runOnFxThread(helpWindow::isShowing));
    }

    @Test
    public void copyUrlMethod_setsClipboardContent() throws Exception {
        HelpWindow helpWindow = runOnFxThread(() -> new HelpWindow(new Stage()));
        Method copyUrlMethod = HelpWindow.class.getDeclaredMethod("copyUrl");
        copyUrlMethod.setAccessible(true);

        runOnFxThread(() -> {
            copyUrlMethod.invoke(helpWindow);
            return null;
        });

        String clipboardContent = runOnFxThread(() -> Clipboard.getSystemClipboard().getString());
        assertEquals(HelpWindow.USERGUIDE_URL, clipboardContent);
    }

    private static <T> T runOnFxThread(FxCallable<T> action) throws Exception {
        AtomicReference<T> result = new AtomicReference<>();
        AtomicReference<Throwable> error = new AtomicReference<>();
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                result.set(action.call());
            } catch (Throwable t) {
                error.set(t);
            } finally {
                latch.countDown();
            }
        });

        assertTrue(latch.await(FX_TIMEOUT_SECONDS, TimeUnit.SECONDS));
        if (error.get() != null) {
            throw new AssertionError(error.get());
        }
        return result.get();
    }

    private static <T> T getPrivateField(Object target, String fieldName, Class<T> fieldType) throws Exception {
        Class<?> currentClass = target.getClass();
        while (currentClass != null) {
            try {
                Field field = currentClass.getDeclaredField(fieldName);
                field.setAccessible(true);
                return fieldType.cast(field.get(target));
            } catch (NoSuchFieldException e) {
                currentClass = currentClass.getSuperclass();
            }
        }
        throw new NoSuchFieldException(fieldName);
    }

    @FunctionalInterface
    private interface FxCallable<T> {
        T call() throws Exception;
    }
}
