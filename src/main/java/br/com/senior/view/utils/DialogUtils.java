package br.com.senior.view.utils;

import javax.swing.JOptionPane;

public class DialogUtils {
    
    
    public static void showInfo(String message){
        showMessageDialog(message, JOptionPane.INFORMATION_MESSAGE, "Informação");
    }
    
    public static void showInfo(String message, String title){
        showMessageDialog(message, JOptionPane.INFORMATION_MESSAGE, title);
    }
    
     public static void showWarning(String message){
        showMessageDialog(message, JOptionPane.WARNING_MESSAGE, "Aviso");
    }
    
    public static void showWarning(String message, String title){
        showMessageDialog(message, JOptionPane.WARNING_MESSAGE, title);
    }
    
     public static void showError(String message){
        showMessageDialog(message, JOptionPane.ERROR_MESSAGE, "Informação");
    }
    
    public static void showError(String message, String title){
        showMessageDialog(message, JOptionPane.ERROR_MESSAGE, title);
    }
    
    private static void showMessageDialog(String message, int optionType, String title){
        JOptionPane.showMessageDialog(null, message, title, optionType);
    }
    
    public static boolean openConfirmDialog(String message){
        return openConfirmDialog(message, "Confirmação");
    }
    public static boolean openConfirmDialog(String message, String title){
        int ret = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
        return ret == JOptionPane.YES_OPTION;
    }
    
}
