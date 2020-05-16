package com.aplicacao.cursomc.services;

import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@Profile("test")
public class MockEmailService extends AbstractEmailService{

  private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

  @Override
  public void sendEmail(SimpleMailMessage msg) {
    LOG.info("Simulando envio de email");
    LOG.info(msg.toString());
    LOG.info("Email enviado");
  }

  @Override
  public void sendHtmlEmail(MimeMessage msg) {
    LOG.info("Simulando envio de email  Html");
    LOG.info(msg.toString());
    LOG.info("Email enviado");
  }

}
