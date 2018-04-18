package org.simplix.core.files;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FileUtils {
    
    public static void openFile ( File file ) {
        
        try {
            Desktop.getDesktop().open ( file );
        } catch ( IOException e ) {
            throw new RuntimeException ( e );
        }
    }

    public static File createTempTxtFile() {
        
        return createTempFile ( "Simplix", ".txt" );
    }

    public static File createTempFile ( String fileNameExtension) {
        
        return createTempFile ( "Simplix", fileNameExtension );
    }

    public static File createTempFile ( String fileName, String fileNameExtension) {
        
        try {
            return File.createTempFile ( fileName, fileNameExtension );
        } catch ( IOException e ) {
            throw new RuntimeException ( e );
        }
    }
}
