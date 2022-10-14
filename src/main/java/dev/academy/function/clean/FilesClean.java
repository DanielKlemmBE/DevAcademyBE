package dev.academy.function.clean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FilesClean {

    File _startFile;
    String _endsWithFilter;

    public FilesClean(File startFile, String endsWithFilter) {
        _endsWithFilter = endsWithFilter;
        _startFile = startFile;
    }

    public List<File> getFiles() {
        return findAll(_startFile.getPath(), _startFile);
    }

    private List<File> findAll(String path, File file) {
        if (file.isDirectory()) {
            List<File> files = new ArrayList<>();
            for (File child : file.listFiles()) {
                files.addAll(findAll(path + "/" + child.getName(), child));
            }
            return files;
        } else {
            if (path.endsWith(_endsWithFilter)) {
                return List.of(file);
            }
        }
        return List.of();
    }
}
