package ru.netology.manager;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
public class FileOpenManager {
    HashMap<String, String> maps = new HashMap<>();

    public HashMap<String, String> getMaps() {
        return maps;
    }

    public void registerApp(String extension, String app) {
        maps.put(extension, app);
    }

    public String getApp(String extension) {
        return maps.get(extension);
    }

    public void removeKey(String extension) {
        maps.remove(extension);
    }

    public List<String> getAllKeys() {
        Comparator byAlphabet = Comparator.naturalOrder();
        Set<String> extensions = new HashSet<>();
        extensions.addAll(maps.keySet());
        ArrayList<String> listExtensions = new ArrayList<>(extensions);
        listExtensions.sort(byAlphabet);
        return listExtensions;
    }

    public List<String> getAllValues() {
        Comparator byAlphabet = Comparator.naturalOrder();
        Set<String> extensions = new HashSet<>();
        extensions.addAll(maps.values());
        ArrayList<String> listExtensions = new ArrayList<>(extensions);
        listExtensions.sort(byAlphabet);
        return listExtensions;
    }
}
