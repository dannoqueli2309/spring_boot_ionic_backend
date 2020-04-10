package com.aplicacao.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class SmtpEmailService extends AbstractEmailService {

  private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

  @Autowired
  private MailSender mailSender;

  @Override
  public void sendEmail(SimpleMailMessage msg) {
    LOG.info("Enviando email");
    LOG.info(msg.toString());
    LOG.info("Email enviado");
    mailSender.send(msg);
  }
}
