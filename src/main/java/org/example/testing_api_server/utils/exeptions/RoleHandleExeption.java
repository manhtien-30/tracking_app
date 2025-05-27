package org.example.testing_api_server.utils.exeptions;

public class RoleHandleExeption extends Exception{
    public RoleNotFoundException(String message) {
        super(message);
    }
}
