package org.simplix.core.gui;

import org.simplix.core.data.Conversions;
import org.simplix.core.data.TreeUtils;
import org.simplix.core.data.se_tree;
import org.simplix.core.files.FileUtils;
import org.simplix.core.files.FileWriterUtils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class se_tree_GUI {
    
    static {
        try {
            UIManager.setLookAndFeel ( UIManager.getSystemLookAndFeelClassName() );
        } catch ( Exception e ) {
            throw new RuntimeException ( e );
        }
    }
    
    public static void show ( List<Object> tree ) {
        show_with_title ( tree, Conversions.stringToList ( "Data" ) );
    }
        
    public static void show_with_title ( List<Object> tree, List<Object> title ) {
        
        DefaultMutableTreeNode root = new DefaultMutableTreeNode ( "Data" );

        final List<DefaultMutableTreeNode> parentNodeStack = new ArrayList<>();
        parentNodeStack.add ( root ); 

        TreeUtils.visitTreeNodes (
            tree,
            list -> {},
            list -> {},
            ( list, index, is_last, level ) -> {
                String label;
                switch ( list.size() ) {
                    case 0:
                        label = "Empty list";
                        break;
                    case 1:
                        label = "List (1 item)";
                        break;
                    default:
                        label = "List (" + list.size () + " items)";
                }
                DefaultMutableTreeNode node = addNode ( parentNodeStack, label );
                parentNodeStack.add ( node );
            },
            ( list, index, is_last, level ) -> {
                parentNodeStack.remove ( parentNodeStack.size() - 1 );
            },
            ( string, index, is_last, level ) -> {
                addNode ( parentNodeStack, string );
            } );
        
        JTree treeView = new JTree ( root );
        treeView.setShowsRootHandles ( true );
        
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        treeView.setBorder ( padding );
         
        JFrame frame = new JFrame ( Conversions.listToString ( title ) );
        frame.setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
        frame.getContentPane().add ( new JScrollPane(treeView) );
        
        // frame.pack();
        frame.setSize ( 600, 400 );
        frame.setLocationRelativeTo(null); // center on screen
        
        frame.setVisible ( true );
    }
    
    private static DefaultMutableTreeNode addNode ( List<DefaultMutableTreeNode> parentNodeStack, String value ) {
        
        DefaultMutableTreeNode node = new DefaultMutableTreeNode ( value );
        parentNodeStack.get ( parentNodeStack.size() - 1 ).add ( node );
        return node;
    }

	public static void show_as_string ( List<Object> tree ) {
		showStringInTxtFile ( se_tree.to_string ( tree ) );
	}
	
	public static void show_as_string_lines ( List<Object> tree ) {
		showStringInTxtFile ( se_tree.to_string_lines ( tree ) );
	}
	
	public static void show_as_string_with_separator ( List<Object> tree, List<Object> separator ) {
		showStringInTxtFile ( se_tree.to_string_with_separator ( tree, separator ) );
	}
	
	public static void show_as_string_tree ( List<Object> tree ) {
		showStringInTxtFile ( se_tree.to_string_tree ( tree ) );
	}

	public static void show_as_canonical_string ( List<Object> tree ) {
		showStringInTxtFile ( se_tree.to_canonical_string ( tree ) );
	}
    
	public static void show_as_HTML ( List<Object> tree ) {
        showStringInTempFile ( se_tree.to_HTML ( tree ), ".html" );
	}
    
	public static void show_as_JSON ( List<Object> tree ) {
        showStringInTempFile ( se_tree.to_JSON ( tree ), ".json" );
	}
    
	public static void show_as_XML ( List<Object> tree ) {
        showStringInTempFile ( se_tree.to_XML ( tree ), ".xml" );
	}
    
	private static void showStringInTxtFile ( List<Object> string ) {
		showStringInTempFile ( string, ".txt" ); 
	}

	private static void showStringInTempFile ( List<Object> string, String fileNameExtension ) {

        File tempFile = FileUtils.createTempFile ( fileNameExtension );
        FileWriterUtils.writeStringLineToFile ( Conversions.listToString ( string ), tempFile );
        FileUtils.openFile ( tempFile );
	}

}
