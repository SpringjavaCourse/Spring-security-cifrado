package com.example.springsecuritycifrado;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

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
     * Genera una 10 veces la clave de encriptacion de 60 caracteres con el metodo BCrypt
     */
    @Test
    void bcryptCheckMultiplePasswords(){

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        for(int i = 0; i < 10; i++){
            System.out.println(passwordEncoder.encode("admin"));
        }

    }

    /**
     * Genera una clave de encriptacion de 60 caracteres con el metodo pdkdf2
     */
    @Test
    void pbkdf2() {
        Pbkdf2PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder("miClaveSecreta" , 4, 2, 60);
        for(int i = 0; i < 10; i++){
            System.out.println(passwordEncoder.encode("admin"));
        }
    }

//    @Test
//    void argon() {
//        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder(1, 1024, 1,20,3);
//        for(int i = 0; i < 10; i++){
//            System.out.println(passwordEncoder.encode("admin"));
//        }
//    }

//    @Test
//    void scrypt() {
//        SCryptPasswordEncoder passwordEncoder = new SCryptPasswordEncoder();
//        for(int i = 0; i < 10; i++){
//            System.out.println(passwordEncoder.encode("admin"));
//        }
//    }

    @Test
    void springPasswordEncoder(){

        String idForEncode = "bcrypt";// "bcrypt", "pbkdf2", "scrypt", "argon2"
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
//        encoders.put("pbdkf2", new Pbkdf2PasswordEncoder("miClaveSecreta", 10000));
//        encoders.put("argon2", new Argon2PasswordEncoder());
        encoders.put("sha256", new StandardPasswordEncoder());



        PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("bcrypt", encoders);

        String hashedPassword = passwordEncoder.encode("admin");
        System.out.println(hashedPassword);
    }


}
