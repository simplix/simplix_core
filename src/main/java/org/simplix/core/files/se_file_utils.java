package org.simplix.core.files;

import org.simplix.core.data.Conversions;

import java.io.IOException;
import java.util.List;

public class se_file_utils {

    public static List<Object> create_temp_file ( List<Object> extension ) {
        
        try {
            return Conversions.stringToList (
                FileUtils.createTempFile ( Conversions.listToString ( extension ) ).getCanonicalPath() );
        } catch ( IOException e ) {
            throw new RuntimeException ( e );
        }
    }

    public static void open_file ( List<Object> file_path ) {
        
        FileUtils.openFile ( Conversions.listToFile ( file_path ) );
    }

    /*
    public static void open_file_in_browser ( List<Object> file_path ) {
    public static void edit_file ( List<Object> file_path ) {
    public static void print_file ( List<Object> file_path ) {
    public static void email_files ( List<Object> file_paths, List<Object> recipients, List<Object> subject, List<Object> message ) {
    
    public static void send_email ( List<Object> file_path ) {
    public static void open_URL ( List<Object> URL ) {
    */
}
