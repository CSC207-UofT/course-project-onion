package com.onionshop.managers;

import com.onionshop.entities.Layer;
import com.onionshop.entities.Project;
import com.onionshop.events.NewProjectEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LayerManagerTest {

    NewProjectEvent newProjectEvent;
    LayerManager manager;
    List<Layer> layers;

    @TempDir
    Path tempDir1;
    String savePath;

    @BeforeEach
    public void setUp() throws Exception {
        newProjectEvent = new NewProjectEvent("beef","beefy", 100, 100);
        manager = new LayerManager();
        manager.projectManager.newProject(newProjectEvent);
        layers = manager.projectManager.getCurrentProject().getLayers();
    }

    @Test
    public void testGetSelectedLayerIndex() {
        int expected = 0;

        int actual = manager.getSelectedLayerIndex();
        assertEquals(actual, expected);
    }

    @Test
    public void testGetSelectedLayer() {
        Layer expected = layers.get(0);

        Layer actual = manager.getSelectedLayer();
        assertEquals(actual, expected);
    }

    @Test
    public void testAddLayer() {
        manager.addLayer(new Layer(120, 430, new int[]{0, 0, 0, 0}));

        assert 2 == manager.projectManager.getCurrentProject().getLayers().size();
    }

    @Test
    public void testRemoveLayerByLayer() {
        manager.removeLayer(new Layer(10, 220, new int[]{0, 0, 0, 0}));

        assert 1 == manager.projectManager.getCurrentProject().getLayers().size();
    }

    @Test
    public void testRemoveLayerByIndex() {
        manager.addLayer(new Layer(10, 220, new int[]{6, 9, 0, 0}));
        manager.removeLayer(1);

        assert 1 == manager.projectManager.getCurrentProject().getLayers().size();
    }

    @Test
    public void testSelectLayerByIndex() {
        manager.addLayer(new Layer(10, 220, new int[]{6, 3, 5, 0}));
        manager.addLayer(new Layer(10, 220, new int[]{2, 2, 2, 0}));

        manager.selectLayer(2);

        assert 2 == manager.getSelectedLayerIndex();
    }

    @Test
    public void testSelectLayerByLayer() {
        manager.addLayer(new Layer(10, 220, new int[]{6, 3, 5, 0}));
        manager.addLayer(new Layer(10, 220, new int[]{2, 2, 2, 0}));

        manager.selectLayer(2);
        Layer currentLayer = manager.getSelectedLayer();
        manager.selectLayer(currentLayer);

        assert 2 == manager.getSelectedLayerIndex();
        assert currentLayer == manager.getSelectedLayer();
    }
}