package org.simplix.core.files;

import org.simplix.core.data.StringUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FileWriterUtils {

    public static Charset defaultCharSet = StandardCharsets.UTF_8;
    
    public static void writeStringToFile ( String string, File file ) {
        stringToFile ( string, file, false );
    }
        
    public static void writeStringLineToFile ( String string, File file ) {
        stringToFile ( string + StringUtils.currentOSNewLine, file, false );
    }
        
    public static void appendStringToFile ( String string, File file ) {
        stringToFile ( string, file, true );
    }

    public static void appendStringLineToFile ( String string, File file ) {
        stringToFile ( string + StringUtils.currentOSNewLine, file, true );
    }

    public static void stringToFile ( String string, File file, boolean appendMode ) {
        
        try {
            BufferedWriter writer = getFileWriter ( file, appendMode );
            writer.write ( string );
            closeWriter ( writer );
        } catch ( IOException e ) {
            throw new RuntimeException ( e );
        }
    }

    public static BufferedWriter getFileWriter ( File file, boolean appendMode ) {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream ( file, appendMode );
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter ( fileOutputStream, defaultCharSet );
            return new BufferedWriter ( outputStreamWriter );
        } catch ( IOException e ) {
            throw new RuntimeException ( e );
        }
    }

    public static void closeWriter ( BufferedWriter writer ) {
        
        try {
            writer.close();
        } catch ( IOException e ) {
            throw new RuntimeException ( e );
        }
    }
}
