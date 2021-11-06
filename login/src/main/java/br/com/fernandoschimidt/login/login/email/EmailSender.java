package br.com.fernandoschimidt.login.login.email;

public interface EmailSender {
    void send(String to, String email);
}
