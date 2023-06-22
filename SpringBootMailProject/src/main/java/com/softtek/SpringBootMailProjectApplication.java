package com.softtek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.softtek.service.IPurchaseOrder;

@SpringBootApplication
public class SpringBootMailProjectApplication {

	public static void main(String[] args) {
		ApplicationContext context =SpringApplication.run(SpringBootMailProjectApplication.class, args);
		IPurchaseOrder order= context.getBean("purchaseService",IPurchaseOrder.class);
		try {
			String msg=order.purchase(new String[] {"shirt" , "trouser" , "watch"},new double[] {5000,6875,9876},new String[] {"mussyshaikh@gmail.com","muskanssh@gmail.com"});
			System.out.println(msg);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		
		
		
	}

}
