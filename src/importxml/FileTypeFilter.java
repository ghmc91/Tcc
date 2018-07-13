package importxml;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Gustavo
 */
public class FileTypeFilter extends FileFilter {

    private final String extensao;
    private final String descricao;

    public FileTypeFilter(String extensao, String descricao) {
        this.extensao = extensao;
        this.descricao = descricao;
    }
    
    
    
    

    @Override
    public boolean accept(File file) {
    if(file.isDirectory()){
        return true;
    }
        return file.getName().endsWith(extensao);
    }

    @Override
    public String getDescription() {
    return descricao + String.format("(*%s)", extensao);
    }
    
}
