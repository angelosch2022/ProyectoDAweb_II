package com.cibertec.proyectoDAWeb_ii.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Utils {

    public static void main(String[] args) {
        
        var password = "12345678";
        
        System.out.println("Original: " + password);
        System.out.println("Encriptado: " + encriptarPassword(password));
    }
    
    public static String encriptarPassword(String password){
        
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                
        return encoder.encode(password);
    }
}
