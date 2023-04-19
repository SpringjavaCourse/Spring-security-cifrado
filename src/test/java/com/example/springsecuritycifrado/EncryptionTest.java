package com.example.springsecuritycifrado;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

public class EncryptionTest {


    /**
     * BCrypt que genera su propia clave de encriptacion( salt ) de 16 bytes
     * El resultado de cifrar con Bcrypt será un string 60 caracteres:
     *
     * $a versión
     * $10 fuerza (valor de 4 a 31, por defecto 10)
     * Los 22 siguientes caracteres son el salt generado
     */
    @Test
    void bcrypTest(){

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword =  passwordEncoder.encode("admin"); // se crea contraseña encriptada
        System.out.println(hashedPassword);


        boolean matches = passwordEncoder.matches("admin",hashedPassword); // se compara contraseña encriptada con la que se ingreso
        System.out.println("la Contraseña coincide? " + matches);
    }

    /**
     * Genera una 10 veces la clave de encriptacion de 60 caracteres
     */
    @Test
    void bcryptCheckMultiplePasswords(){

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        for(int i = 0; i < 10; i++){
            System.out.println(passwordEncoder.encode("admin"));
        }

    }

    @Test
    void pbkdf2() {
        Pbkdf2PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder("miClaveSecreta" , 4, 2, 60);
        for(int i = 0; i < 10; i++){
            System.out.println(passwordEncoder.encode("admin"));
        }
    }
}
