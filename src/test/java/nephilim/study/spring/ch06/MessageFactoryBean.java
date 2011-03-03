package nephilim.study.spring.ch06;

import org.springframework.beans.factory.FactoryBean;

public class MessageFactoryBean implements FactoryBean<Message> {

	String text; 
	
	public void setText(String text) {
		this.text = text;
	}
	
	public Message getObject() throws Exception {
		return Message.newMessage(text);
	}

	public Class<?> getObjectType() {
		return Message.class;
	}

	public boolean isSingleton() {
		return false;
	}
	
}

class Message {
	String text;
	
	private Message(String text) {
		this.text =text;
	}
	
	
	public String getText() {
		return text;
	}
	
	public static Message newMessage(String text) {
		System.out.println("[Messsage created!]");		// 너무 중요한 일이라 가정
		return new Message(text);
	}
}
