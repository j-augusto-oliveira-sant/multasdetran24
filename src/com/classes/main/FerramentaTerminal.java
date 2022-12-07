package com.classes.main;

import java.io.Console;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FerramentaTerminal {

    public static String lerSenha(){
        Console console = System.console();
        char[] senha = console.readPassword();
        StringBuilder sb_senha = new StringBuilder();
        for (char letra: senha)
            sb_senha.append(letra);
        return sb_senha.toString();
    }

    public static void clearConsole(){
        try{
            String operatingSystem = System.getProperty("os.name");//Check the current operating system

            if(operatingSystem.contains("Windows")){
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();

                startProcess.waitFor();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static String colorir_texto(String texto){
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_RESET = "\u001B[0m";
        return ANSI_YELLOW+texto+ANSI_RESET;
    }

    public static Date transformar_data(String sData) {
        String regex_date = "^(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-([12][0-9]{3})$";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false);
        if (sData.matches(regex_date)) {
            Date data_convert;
            try {
                data_convert = sdf.parse(sData);
                return data_convert;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
