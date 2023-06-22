package com.softtek.impl;

import java.util.Arrays;
import java.util.Date;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.softtek.service.IPurchaseOrder;

@Service("purchaseService")
public class PurchaseOrderImpl implements IPurchaseOrder {

	@Autowired
	private JavaMailSender sender;

	@Value("${spring.mail.username}")
	private String formEmail;

	@Override
	public String purchase(String[] items, double[] prices, String[] toEmails) throws Exception {
		// calculate the bill amount
		double billAmt = 0.0;
		for (double p : prices) {
			billAmt = billAmt + p;
		}
		String msg = Arrays.toString(items) + " with prices" + Arrays.toString(prices)
		+ " are purchased with BillAmount" + billAmt;
		// send mail
		String status = sendMail(msg, toEmails);
		return msg + "------->" + status;
	}

	private String sendMail(String msg, String[] toEmails) throws Exception {
		MimeMessage message=sender.createMimeMessage();//empty mail msg
		MimeMessageHelper helper=new MimeMessageHelper(message,true);
		helper.setFrom(formEmail);
		helper.setCc(toEmails);
		helper.setSubject("Open it to know it");
		helper.setSentDate(new Date());
		helper.setText(msg);
		sender.send(message);
		return "mail send";
			}

}
