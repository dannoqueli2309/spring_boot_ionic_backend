package com.aplicacao.cursomc.services;

import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class SmtpEmailService extends AbstractEmailService {

  private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

  @Autowired
  private MailSender mailSender;

  @Autowired
  private JavaMailSender javaMailSender;


  @Override
  public void sendEmail(SimpleMailMessage simpleMailMessage) {
    LOG.info("Enviando email");
    LOG.info(simpleMailMessage.toString());
    LOG.info("Email enviado");
    mailSender.send(simpleMailMessage);
  }

  @Override
  public void sendHtmlEmail(MimeMessage mimeMessage) {
    LOG.info("Enviando email");
    LOG.info(mimeMessage.toString());
    LOG.info("Email enviado");
    javaMailSender.send(mimeMessage);
  }
}
