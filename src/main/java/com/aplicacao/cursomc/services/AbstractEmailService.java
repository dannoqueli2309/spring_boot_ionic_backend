package com.aplicacao.cursomc.services;

import com.aplicacao.cursomc.domain.Pedido;
import java.util.Date;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

public abstract class AbstractEmailService implements EmailService {

  @Value("${default.sender}")
  private String sender;

  @Value("${default.recipient}")
  private String recipient;

  @Autowired
  private TemplateEngine templateEngine;

  @Autowired
  private JavaMailSender javaMailSender;

  @Override
  public void sendOrderConfirmationEmail(Pedido pedido) {
    SimpleMailMessage simpleMailMessage = PreparedSimpleMailMessage(pedido);
    sendEmail(simpleMailMessage);
  }

  protected SimpleMailMessage PreparedSimpleMailMessage(Pedido pedido) {
    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    simpleMailMessage.setTo(pedido.getCliente().getEmail());
    simpleMailMessage.setFrom(sender);
    simpleMailMessage.setSubject("Pedido confirmado! Código: " + pedido.getId());
    simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
    simpleMailMessage.setText(pedido.toString());
    return simpleMailMessage;
  }

  protected String htmlFromTemplatePedido(Pedido pedido) {
    Context context = new Context();
    context.setVariable("pedido", pedido);
    return templateEngine.process("email/confirmacaoPedido", context);
  }

  @Override
  public void sendOrderConfirmationHtmlEmail(Pedido pedido) {
    try {
      MimeMessage mimeMessage = preparedMimeMessage(pedido);
      sendHtmlEmail(mimeMessage);
    } catch (MessagingException e) {
      sendOrderConfirmationEmail(pedido);
    }
  }

  private MimeMessage preparedMimeMessage(Pedido pedido) throws MessagingException {
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
    mimeMessageHelper.setTo(pedido.getCliente().getEmail());
    mimeMessageHelper.setFrom(sender);
    mimeMessageHelper.setSubject("Pedido confirmado! codigo" + pedido.getId());
    mimeMessageHelper.setSentDate(new Date(System.currentTimeMillis()));
    mimeMessageHelper.setText(htmlFromTemplatePedido(pedido),true);
    return mimeMessage;
  }

}
