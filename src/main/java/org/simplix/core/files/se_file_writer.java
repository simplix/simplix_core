package org.simplix.core.files;

import org.simplix.core.data.Conversions;
import org.simplix.core.data.TreeUtils;

import java.io.*;
import java.util.List;

public class se_file_writer {
    
    // write
    
    public static void write ( List<Object> file_path, List<Object> data ) {
        write ( file_path, data, false, false );
    }

    public static void write_line ( List<Object> file_path, List<Object> data ) {
        write ( file_path, data );
        write_new_line ( file_path );
    }

    public static void write_lines ( List<Object> file_path, List<Object> data ) {
        write ( file_path, data, false, true );
    }
        
    public static void write_new_line ( List<Object> file_path ) {
        writeNewLine ( file_path, false );
    }
    
    // append
        
    public static void append ( List<Object> file_path, List<Object> data ) {
        write ( file_path, data, true, false );
    }

    public static void append_line ( List<Object> file_path, List<Object> data ) {
        append ( file_path, data );
        append_new_line ( file_path );
    }

    public static void append_lines ( List<Object> file_path, List<Object> data ) {
        write ( file_path, data, true, true );
    }

    public static void append_new_line ( List<Object> file_path ) {
        writeNewLine ( file_path, true );
    }
    
    // helpers
        
    private static void write ( List<Object> file_path, List<Object> data, boolean appendMode, boolean writeLine ) {
        
        final BufferedWriter writer = getFileWriter ( file_path, appendMode );
        TreeUtils.forEachStringNodeInTree ( data, string -> {
            if ( writeLine ) {
                writeLine ( writer, string );
            } else {
                write ( writer, string );
            }
        } );
        FileWriterUtils.closeWriter ( writer );
    }
    
    private static void writeNewLine ( List<Object> file_path, boolean appendMode ) {
        
        final BufferedWriter writer = getFileWriter ( file_path, appendMode );
        writeNewLine ( writer );
        FileWriterUtils.closeWriter ( writer );
    }
    
    private static void write ( BufferedWriter writer, String string ) {
        
        try {
            writer.write ( string );
        } catch ( IOException e ) {
            throw new RuntimeException ( e );
        }
    }

    private static void writeLine ( BufferedWriter writer, String string ) {
        write ( writer, string );
        writeNewLine ( writer );
    }

    private static void writeNewLine ( BufferedWriter writer ) {
        
        try {
            writer.newLine();
        } catch ( IOException e ) {
            throw new RuntimeException ( e );
        }
    }

    private static BufferedWriter getFileWriter ( List<Object> file_path, boolean appendMode ) {

        File file = Conversions.listToFile ( file_path );
        return FileWriterUtils.getFileWriter ( file, appendMode );
    }
        
}
