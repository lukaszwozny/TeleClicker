package com.mygdx.teleclicker.Enums;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by Senpai on 01/08/2016.
 */
public enum DBStatusEnum {
    AUTHORIZATION_FAILED("Authorization failed."),
    SUCCES("Succes. :)",Color.GREEN),
    ERROR("Error"),
    CONNECTING("Connecting to server."),
    FAILED("Connection failed. Try again."),
    CANCELLED("Connection cancelled. Try again."),
    NOT_CONNECTED("Connection problem. Try again."),
    CONNECTION_TIMEOUT("Connection time out."),

    PLAYER_ADDED("Player added successfully.",Color.GREEN),
    PLAYER_DOESNT_EXIST("Player doesn't exist."),
    PLAYER_ALREADY_EXIST("Player already exist."),
    LOGIN_IS_NULL("Login field is empty."),
    EMAIL_IS_NULL("Email field is empty."),
    PASSWORD_IS_NULL("Password field is empty."),
    INCORRECT_PASSWORD("Incorrect password.");

    private String message;

    private Color messageColor;

    private DBStatusEnum(String message) {
        this(message,Color.RED);
    }

    private DBStatusEnum(String message, Color messageColor){
        this.message = message;
        this.messageColor = messageColor;
    }

    @Override
    public String toString() {
        return message;
    }

    public Color getMessageColor() {
        return messageColor;
    }
}
