/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbeans.util;

import entities.Account;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Properties;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Named(value = "Mailer")
@RequestScoped
public class Mailer {
    
    /**
     * Funcion utilizada para crear una nueva password al usuario que lo solicite
     * @param account objeto que incluye los datos del usuario
     * @return newPassword string que devuelve la nueva contraseña al usuario
     */

    public String sendPasswordRecoveryMessage(Account account) {
        SecureRandom random = new SecureRandom();
        String newPassword = new BigInteger(130, random).toString(32);
        newPassword = newPassword.substring(2, 7).toUpperCase();
        try {
            sendMessage(account.getEmail(),
            "¿Olvidó su contraseña para ingresar al sistema NewBorns?",
            "Estimado " + account.getName() + ",\n\n" +
            "Su contraseña ha sido reestablecida: " + newPassword + "\n" +
            "Puede ingresar con esta nueva contraseña y cambiarla "+
            "desde el menú de Perfil de su cuenta.\n\n" + 
            "Si usted no ha solicitado este cambio, favor comunicarse con " + 
            "algún administrador del sistema.");
            return newPassword;
        } catch (Exception ex) {
            return null;
        }        
    }
    
    /**
     * Funcion que envia un mensaje al usuario con su nueva contraseña
     * @param email string que contiene el mail del usuario que solicita el cambio
     * de contraseña
     * @param messageSubject string que contiene el asunto del mail
     * @param messageBody string que contiene el mensaje del mail
     */
    
    private void sendMessage(String email, String messageSubject, String messageBody) {
        final String username = "sistema.newborns@gmail.com";
        final String password = "pingeso123";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                }
          });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("from-email@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject(messageSubject);
            message.setText(messageBody);
            Transport.send(message);
            System.out.println("Mailer: email send success to " + email);
        } 
        catch (MessagingException e) {
            System.out.println("Mailer: error on email send process.");
            throw new RuntimeException(e);
        }
    }
}