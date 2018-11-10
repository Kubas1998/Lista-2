package com.obsidiam.input;


/**
 * Represents base of any command. In fact it just stores names of methods from InputController class
 */
public enum  BaseCommand {

    HELP("printHelp"),
    GETSUMMARY("getSummary"),
    CREATESUMMARY("createSummary"),
    CREATE_USER("createUser"),
    GET_USER("getUser"),
    CREATEELEMENT("createElement"),
    LIST_USERS("listUsers");

    private String flag;

    BaseCommand(String switchFlag){
        this.flag = switchFlag;
    }

    /**
     * Getter for Enum flag string representation.
     * @return
     */
    public String getFlagStringRep(){
        return this.flag;
    }
}
