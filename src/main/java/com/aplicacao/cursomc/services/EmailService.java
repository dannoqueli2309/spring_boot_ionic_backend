package com.aplicacao.cursomc.services;

import com.aplicacao.cursomc.domain.Pedido;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

  void sendOrderConfirmationEmail(Pedido obj);

  void sendEmail(SimpleMailMessage msg);

  void sendOrderConfirmationHtmlEmail(Pedido obj);

  void sendHtmlEmail(MimeMessage msg);
}
