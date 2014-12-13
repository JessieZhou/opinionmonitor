package com.hhhy.web.service.notice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

public class EmailReport {
    private static final Logger logger = Logger.getLogger(EmailReport.class);
    private static final int MAILRESENDTIME = 10;// 邮件重发次数
    private static Properties emailProperties;
    private static Session session;

    static {
        init();
    }

    private static void init() {
        logger.info("init email report...");
        emailProperties = new Properties();
        try {
            emailProperties.load(EmailReport.class.getClassLoader()
                    .getResourceAsStream("email.properties"));
            final String user = emailProperties.getProperty("user");
            final String password = emailProperties.getProperty("password");
            session = Session.getDefaultInstance(emailProperties,
                    new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(user, password);
                        }
                    });
            session.setDebug(false);
            logger.info("init email report success...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendMail(final EmailEntry mailEntry)
            throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        MimeMultipart mailContent = new MimeMultipart();
        MimeBodyPart bodyContentPart = createBodyContent(mailEntry.text);
        mailContent.addBodyPart(bodyContentPart);
        message.setSubject(mailEntry.subject, "utf-8");
        message.setContent(mailContent);
        List<Address> addresses;
        String toAddresses = mailEntry.toAddresses;
        if (toAddresses != null && toAddresses.length() > 0) {
            addresses = new ArrayList<Address>();
            try {
                Address addr = new InternetAddress(toAddresses);
                addresses.add(addr);
            } catch (AddressException e) {
                logger.error(toAddresses + " is an invalid address.");
            }

            message.addRecipients(Message.RecipientType.TO, addresses
                    .toArray(new Address[0]));
        }

        message.setFrom(new InternetAddress(emailProperties
                .getProperty("fromAddress")));
        int trytimes = 0;
        boolean sendSuccess = false;
        do {
            trytimes++;
            logger.info("try to send Report mail-> begain to try " + trytimes
                    + "times.");
            try {
                Transport.send(message);
                sendSuccess = true;
            } catch (SendFailedException e) {
                deleteInvalidAddresses(message, e.getInvalidAddresses());
            } catch (MessagingException e) {
                sendSuccess = false;
                logger.warn("send mail failed, reason->" + e);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e1) {
                }
            }
        } while (trytimes < MAILRESENDTIME && !sendSuccess);
        if (sendSuccess) {
            logger.info("Success to send Report mail:[" + toAddresses + "]");
        } else {
            logger.warn("After trying " + trytimes
                    + " times, still failed to send mail:[" + toAddresses
                    + "].");
        }
    }

    private static MimeBodyPart createBodyContent(String bodyContent)
            throws MessagingException {// 生成邮件主体内容，以MimeBodyPart类型返回
        MimeBodyPart contentPart = new MimeBodyPart();
        contentPart.setContent(bodyContent, "text/html;charset=utf-8");
        return contentPart;
    }

    private static void deleteInvalidAddresses(MimeMessage msg,
            Address[] invalidAddresses) throws MessagingException {
        if (invalidAddresses == null || invalidAddresses.length == 0)
            return;
        Address[] toAddresses = msg.getRecipients(Message.RecipientType.TO);// 收件人
        Address[] ccAddresses = msg.getRecipients(Message.RecipientType.CC);// 抄送
        Address[] bccAddresses = msg.getRecipients(Message.RecipientType.BCC);// 密送
        List<Address> toList = toArrsyList(toAddresses);
        List<Address> ccList = toArrsyList(ccAddresses);
        List<Address> bccList = toArrsyList(bccAddresses);
        for (Address addr : invalidAddresses) {
            logger.info("address " + addr
                    + " is invalid, delete from message's recipients.");
            if (toList.contains(addr)) {
                logger.info("remove " + addr + " from to addresses.");
                toList.remove(addr);
            }
            if (ccList.contains(addr)) {
                logger.info("remove " + addr + " from cc addresses.");
                ccList.remove(0);
            }
            if (bccList.contains(addr)) {
                logger.info("remove " + addr + " from bcc addresses.");
                bccList.remove(addr);
            }
        }
        msg.setRecipients(Message.RecipientType.TO, toList
                .toArray(new Address[0]));
        msg.setRecipients(Message.RecipientType.CC, ccList
                .toArray(new Address[0]));
        msg.setRecipients(Message.RecipientType.BCC, bccList
                .toArray(new Address[0]));
    }

    private static List<Address> toArrsyList(Address[] array) {
        List<Address> list = new ArrayList<Address>();
        if (array != null && array.length > 0) {
            for (Address addr : array)
                list.add(addr);
        }
        return list;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        EmailEntry entry = new EmailEntry();
//        entry.ccAddresses = new String[] { "zhouxuan@neotel.com.cn",
//                "shanjie@neotel.com.cn" };
        entry.subject = "邮件模块测试";
        entry.text = "有新的重要舆情需要关注: <br>标题: title"+
        "<br>摘要: summary<br>URL: <a href=\"www.google.com\">这里</a>";
        entry.toAddresses = "chenlingpeng@neotel.com.cn";
        try {
            EmailReport.sendMail(entry);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
