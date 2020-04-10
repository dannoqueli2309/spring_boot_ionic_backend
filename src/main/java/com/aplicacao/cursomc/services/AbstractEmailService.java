package com.aplicacao.cursomc.services;

import com.aplicacao.cursomc.domain.Pedido;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

public abstract class AbstractEmailService implements EmailService {

  @Value("${default.sender}")
  private String sender;


  @Value("${default.recipient}")
  private String recipient;

  @Override
  public void sendOrderConfirmationEmail(Pedido pedido) {
    SimpleMailMessage simpleMailMessage = PreparedSimpleMailMessage(pedido);
    sendEmail(simpleMailMessage);
  }

  protected SimpleMailMessage PreparedSimpleMailMessage(Pedido pedido) {
    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    simpleMailMessage.setTo(recipient);
    simpleMailMessage.setFrom(sender);
    simpleMailMessage.setSubject("Pedido confirmado! Código: " + pedido.getId());
    simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
    simpleMailMessage.setText(pedido.toString());
    return simpleMailMessage;
  }

}
