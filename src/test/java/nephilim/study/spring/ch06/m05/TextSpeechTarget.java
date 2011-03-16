package nephilim.study.spring.ch06.m05;

public class TextSpeechTarget implements TextSpeech{
	
	private String text;
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String sayStoredText() {
		return text;
	}
}
