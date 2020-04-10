package com.aplicacao.cursomc.services;

import com.aplicacao.cursomc.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

  void sendOrderConfirmationEmail(Pedido obj);

  void sendEmail(SimpleMailMessage msg);
}
