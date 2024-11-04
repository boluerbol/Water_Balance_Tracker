package com.erbaproger.water_balance_tracker.exceptions;

import javax.swing.*;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
}
