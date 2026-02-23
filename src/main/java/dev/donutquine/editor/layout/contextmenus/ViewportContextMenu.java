package dev.donutquine.editor.layout.contextmenus;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.function.Function;

import javax.swing.JMenuItem;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import dev.donutquine.editor.Editor;
import dev.donutquine.editor.MessageDialogs;
import dev.donutquine.editor.gizmos.Gizmos;
import dev.donutquine.editor.layout.components.Table;
import dev.donutquine.editor.layout.components.TablePopupMenuListener;
import dev.donutquine.editor.layout.windows.EditorWindow;
import dev.donutquine.editor.renderer.impl.EditorStage;
import dev.donutquine.renderer.impl.swf.objects.DisplayObject;
import dev.donutquine.renderer.impl.swf.objects.MovieClip;
import dev.donutquine.renderer.impl.swf.objects.TextField;

public class ViewportContextMenu extends ContextMenu {
    private final Editor editor;
    private final EditorWindow window;

    public ViewportContextMenu(EditorWindow window) {
        super(window.getFrame(), null);

        this.window = window;
        this.editor = window.getEditor();

        this.add("Remove from stage", event -> this.printExportName());
    }

    private void printExportName() {
        EditorStage stage = EditorStage.getInstance();
        Gizmos gizmos = stage.getGizmos();
        DisplayObject touchedObject = gizmos.getTouchedObject();
        if (touchedObject != null) {
            stage.removeChild(touchedObject);
            gizmos.setMousePressed(false);
            gizmos.setTouchedObject(null);
        }
    }

    private MovieClip getMovieClip(DisplayObject selectedObject) {
        if (selectedObject.isMovieClip()) {
            return (MovieClip) selectedObject;
        }

        return null;
    }
}
