package com.obsidiam;

import com.obsidiam.input.BaseCommand;
import com.obsidiam.input.InputController;
import com.obsidiam.output.OutputController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Main class. Here is the main loop of the program. It takes input from the user and passes it to InputController
 * @see InputController
 */

public class Main {

    final private static HashMap<String,String> aliases = new HashMap<>();

    static{
        aliases.put("getSummary","[id] Pobiera fakture");
        aliases.put("createSummary","[id_seller] [id_client] Dodaj nową fakturę do bazy");
        aliases.put("createElement","[summary_id][article_name][article_price][article_amount] Dodaje nowy artykuł do faktury i bazy");
        aliases.put("createUser","[UserType type, String name, String surName, String address] Dodaje nowego użytkownika do bazy.");
        aliases.put("getUser","[id] Pobiera użytkownika z podanym id.");
        aliases.put("listUsers","Listuje użytkowników.");
        aliases.put(":q,q,quit,exit","By wyjść.");
        aliases.put("getUser","[user_id] Pobiera dane konkretnego użytkownika");
        aliases.put("getAllUsers","Pobiera nazwy wszystkich użytkowników");
        aliases.put("createUser","[Sprzedawca/Klient] [imię] [nazwisko] [adres zamieszkania] Dodaje nowego użytkownika do bazy danych");

    }

    /**
     * Entry point.
     * @param args - command line args, wont use.
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException{
        InputController inputController = InputController.getControllerInstance();
        Scanner s = new Scanner(System.in);

        while(true){
            OutputController.getControllerInstance().printPrompt();

            final String baseCommandStr = s.nextLine();
            if(baseCommandStr.contains("quit") || baseCommandStr.contains("\\q") || baseCommandStr.contains("exit") || baseCommandStr.contains(":q")){
                break;
            }

            List<BaseCommand> l = Arrays.asList(BaseCommand.values());
            if(baseCommandStr.startsWith("\\") || baseCommandStr.startsWith(":")){
                String changedCommand = baseCommandStr;
                if(baseCommandStr.contains("\\?") || baseCommandStr.contains(":?")) changedCommand = baseCommandStr.replace('?', 'h');

                String baseCommand = aliases.get(changedCommand.split(" ")[0].substring(1));

                Object[] o = l.stream().filter(predicate -> predicate.getFlagStringRep().equals(baseCommand)).toArray();
                inputController.execute((BaseCommand)(o.length > 0 ? o[0] : BaseCommand.HELP), prepareArguments(baseCommandStr));
                //inputController.execute((BaseCommand)l.stream().filter(predicate ->{return commandExists[0] = predicate.getFlagStringRep().equals(baseCommand);}).toArray()[0], prepareArguements(baseCommandStr));
            }else{
                Object[] o = l.stream().filter(predicate -> baseCommandStr.split(" ")[0].equals(predicate.getFlagStringRep())).toArray();
                inputController.execute((BaseCommand)(o.length > 0 ? o[0] : BaseCommand.HELP), prepareArguments(baseCommandStr));
            }

        }
    }

    /**
     * Utility method, prepare arguments basing on given command string.
     * @param commandString  command string - base command and params.
     * @return
     */
    private static Object[] prepareArguments(String commandString){
        Object[] args;
        String[] parts = commandString.split(" ");

        args = new Object[parts.length - 1];
        System.arraycopy(parts,1, args, 0, parts.length - 1);

        return args;
    }

    /**
     * Utility method, getter for aliases.
     * @return
     */
    public static HashMap<String,String> getAliases(){
        return aliases;
    }
}
