package com.obsidiam.input;

import com.obsidiam.Main;
import com.obsidiam.managers.SummaryManager;
import com.obsidiam.managers.UserManager;
import com.obsidiam.output.OutputController;
import com.obsidiam.util.model.Summary;
import com.obsidiam.util.model.User;
import com.obsidiam.util.model.UserType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * This class is reliable for parsing and interacting with data passed by the user from the standard input.
 */
public class InputController{
    private volatile static InputController inputControllerInstance = new InputController();
    private Object[] args = new Object[0];

    private OutputController outputController = OutputController.getControllerInstance();

    private UserManager userManager = new UserManager();
    private SummaryManager summaryManager = new SummaryManager();

    //Implementation of Singleton pattern.

    /**
     * Get and returns instance of this controller.
     * @return inputController instance.
     */
    public static InputController getControllerInstance(){
        return inputControllerInstance;
    }

    /**
     * Execute the command represented by BaseCommand object.
     * @param baseCommand baseCommand object
     * @param args arguments passed by the user
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @see BaseCommand
     */
    public void execute(BaseCommand baseCommand, Object[] args)throws IllegalArgumentException, IllegalAccessException {
        if (baseCommand != null){
            try {

                this.args = args;
                Method method = InputController.class.getDeclaredMethod(baseCommand.getFlagStringRep());
                method.setAccessible(true);
                method.invoke(inputControllerInstance);
            }catch (NoSuchMethodException | SecurityException | InvocationTargetException ex){
                outputController.printMessage(new LogRecord(Level.SEVERE, "No such command as "+baseCommand.getFlagStringRep()+" because of "+ex.toString()));
            }
        }
    }
    //Those are just helper methods, the cannot be used from the outside so documenting them is totally useless, their names tell everything.
    private void getSummary(){
        if (args.length == 1 || Integer.parseInt(args[0].toString()) >= 0){
            Summary summary = summaryManager.getSummary(Integer.parseInt(args[0].toString()));
            outputController.printSummary(summary);
        }
        else {
            System.out.println("Podałeś nieprawidłowe argumenty, zobacz pomoc");
        }
    }

    private void createSummary(){
        if (args.length == 2){
            int result = summaryManager.createSummary(Integer.parseInt(args[0].toString()), Integer.parseInt(args[1].toString()));

            if (result > 1)
                System.out.println("Dodano nową fakturę dla ");
            else
                System.out.println("Coś poszło nie tak");
        }
    }

    private void createElement(){
        if (args.length == 4){
            int result = summaryManager.createSummaryElement(
                    Integer.parseInt(args[0].toString()),
                    args[0].toString(),
                    Float.parseFloat(args[0].toString()),
                    Integer.parseInt(args[0].toString())
            );

            if (result == 1)
                System.out.println("Pomyślnie dodano nowy artykuł do faktury.");
            else
                System.out.println("Coś poszło nie tak");
        }
    }

    private void getAllUsers(){
        List<User> users = userManager.getAllUsers();
        for(User user: users){
            System.out.println(" ");
            outputController.printUser(user);
        }
    }

    private void printHelp(){
        System.out.println("Komendy: ");
        HashMap<String,String> aliases = Main.getAliases();

        aliases.forEach((alias,cmd) ->{
            System.out.println(String.format("%-20s %s", alias , ": "+ cmd));
        });
    }

    private void getUser(){
        if(args.length == 1) {
            User user = userManager.getUser(Integer.parseInt(args[0].toString()));
            LogRecord logRecord = new LogRecord(Level.ALL, user.toString());
            OutputController.getControllerInstance().printMessage(logRecord);
        }
    }

    private void createUser(){
        if(args.length == 4) {
            int result = userManager.createUser(UserType.valueOf((String) args[0]), args[1].toString(), args[2].toString(), args[3].toString());
            if(result != -1 && result != 0){
                LogRecord logRecord = new LogRecord(Level.INFO, "Utworzono użytkownika.");
                OutputController.getControllerInstance().printMessage(logRecord);
            }else{
                LogRecord logRecord = new LogRecord(Level.SEVERE, "Coś poszło nie tak.");
                OutputController.getControllerInstance().printMessage(logRecord);
            }
        }
    }

    private void listUsers(){
        userManager.getAllUsers().forEach(user ->{
            LogRecord record = new LogRecord(Level.INFO, user.toString());
            OutputController.getControllerInstance().printMessage(record);
        });
    }

}
