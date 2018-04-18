package org.simplix.core.gui;

import org.simplix.core.data.Conversions;

import javax.swing.*;
import java.util.List;

public class se_GUI_dialogs {
    
    static {
        try {
            UIManager.setLookAndFeel ( UIManager.getSystemLookAndFeelClassName() );
        } catch ( Exception e ) {}
    }
    
    public static void info ( List<Object> message ) {
        
        JOptionPane.showMessageDialog (
            null,
            Conversions.listToString ( message ),
            "Info",
            JOptionPane.INFORMATION_MESSAGE );
    }

    public static void error ( List<Object> message ) {
        
        JOptionPane.showMessageDialog (
            null,
            Conversions.listToString ( message ),
            "Error",
            JOptionPane.ERROR_MESSAGE );
    }
    
    public static List<Object> ask_string ( List<Object> message ) {

        String answer = JOptionPane.showInputDialog ( Conversions.listToString ( message ) );
        if ( answer == null ) answer = "";
        return Conversions.stringToList ( answer );
    }

    public static List<Object> ask_yes_no ( List<Object> question ) {

        int answer = JOptionPane.showConfirmDialog (
            null,
            Conversions.listToString ( question ),
            "Question",
            JOptionPane.YES_NO_OPTION );

        return Conversions.booleanToList ( answer == javax.swing.JOptionPane.YES_OPTION );
    }
}
