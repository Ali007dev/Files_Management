package com.file_management.services;

import com.file_management.interfaces.AuthInterface;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements AuthInterface {
@Override
public String getWelcomeMessage(){
    return  "K";
}

}
