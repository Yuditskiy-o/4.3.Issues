package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileOpenManagerTest {
    private FileOpenManager manager = new FileOpenManager();

    private String html = ".html";
    private String browser = "Chrome";
    private String txt = ".txt";
    private String viewer1 = "Microsoft Word";
    private String img = ".png";
    private String viewer2 = "Viewer";
    private String doc = ".docx";
    private String keyWithCaps = "JPEGAPP";

    @Nested
    public class EmptyManager {

        @Test
        public void shouldReturnNullIfNoApp() {
            String expected = null;
            String actual = manager.getApp(html);
            assertEquals(expected, actual);
        }

        @Test
        public void shouldReturnEmptyIfNothingToRemove() {
            Map<String, String> expected = new HashMap<>();
            manager.removeKey(html);
            HashMap<String, String> actual = manager.getMaps();
            assertEquals(expected, actual);
        }

        @Test
        public void shouldGetEmptyIfNoKeys() {
            List<String> expected = List.of();
            List<String> actual = manager.getAllKeys();
            assertEquals(expected, actual);
        }

        @Test
        public void shouldGetEmptyIfNoValues() {
            List<String> expected = List.of();
            List<String> actual = manager.getAllValues();
            assertEquals(expected, actual);
        }
    }

    @Nested
    public class SingleItemManager {

        @BeforeEach
        public void setup() {
            manager = new FileOpenManager();
            manager.registerApp(html, browser);
        }

        @Test
        public void shouldReturnApp() {
            String expected = browser;
            String actual = manager.getApp(html);
            assertEquals(expected, actual);
        }

        @Test
        public void shouldRemoveKey() {
            Map<String, String> expected = new HashMap<>();
            manager.removeKey(html);
            HashMap<String, String> actual = manager.getMaps();
            assertEquals(expected, actual);
        }

        @Test
        public void shouldGetOneKey() {
            List<String> expected = List.of(html);
            List<String> actual = manager.getAllKeys();
            assertEquals(expected, actual);
        }

        @Test
        public void shouldGetOneValue() {
            List<String> expected = List.of(browser);
            List<String> actual = manager.getAllValues();
            assertEquals(expected, actual);
        }
    }

    @Nested
    public class MultipleItemsManager {

        @BeforeEach
        public void setup() {
            manager = new FileOpenManager();
            manager.registerApp(html, browser);
            manager.registerApp(img, viewer2);
            manager.registerApp(txt, viewer1);
        }

        @Test
        public void shouldAddAll() {
            HashMap<String, String> expected = new HashMap<>();
            expected.put(html, browser);
            expected.put(txt, viewer1);
            expected.put(img, viewer2);
            HashMap<String, String> actual = manager.getMaps();
            assertEquals(expected, actual);
        }

        @Test
        public void shouldReturnOneApp() {
            String expected = browser;
            String actual = manager.getApp(html);
            assertEquals(expected, actual);
        }

        @Test
        public void shouldReturnNullIfInvalidKey() {
            String expected = null;
            String actual = manager.getApp(doc);
            assertEquals(expected, actual);
        }

        @Test
        public void shouldReturnNullIfKeyWithCaps() {
            String expected = null;
            String actual = manager.getApp(keyWithCaps);
            assertEquals(expected, actual);
        }

        @Test
        public void shouldRemoveKey() {
            manager.removeKey(txt);
            Map<String, String> expected = new HashMap<>();
            expected.put(html, browser);
            expected.put(img, viewer2);
            HashMap<String, String> actual = manager.getMaps();
            assertEquals(expected, actual);
        }

        @Test
        public void shouldNotRemoveKeyIfCaps() {
            manager.removeKey(keyWithCaps);
            Map<String, String> expected = new HashMap<>();
            expected.put(html, browser);
            expected.put(txt, viewer1);
            expected.put(img, viewer2);
            HashMap<String, String> actual = manager.getMaps();
            assertEquals(expected, actual);
        }

        @Test
        public void shouldNotRemoveKeyIfInvalid() {
            manager.removeKey(doc);
            Map<String, String> expected = new HashMap<>();
            expected.put(html, browser);
            expected.put(txt, viewer1);
            expected.put(img, viewer2);
            HashMap<String, String> actual = manager.getMaps();
            assertEquals(expected, actual);
        }

        @Test
        public void shouldGetAllKeysSorted() {
            List<String> expected = List.of(html, img, txt);
            List<String> actual = manager.getAllKeys();
            assertEquals(expected, actual);
        }

        @Test
        public void shouldGetAllValuesSorted() {
            List<String> expected = List.of(browser, viewer1, viewer2);
            List<String> actual = manager.getAllValues();
            assertEquals(expected, actual);
        }
    }
}