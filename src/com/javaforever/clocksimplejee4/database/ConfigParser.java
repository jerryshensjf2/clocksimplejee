package com.javaforever.clocksimplejee4.database;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXException;
import java.util.Properties;

//ʹ��DefaultHandler�ĺô� �� ���س��г����з���,
public class ConfigParser extends DefaultHandler {
	// ����һ��Properties ������� dbhost dbuser dbpassword��ֵ
	private Properties props;
	String currentSet;

	String currentName;
	private StringBuffer currentValue = new StringBuffer();

	// ��������ʼ��props
	public ConfigParser() {
		this.props = new Properties();
	}

	public Properties getProps() {
		return this.props;
	}

	// ���忪ʼ����Ԫ�صķ���. �����ǽ�<xxx>�е�����xxx��ȡ����.
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		currentValue.delete(0, currentValue.length());
		this.currentName = qName;
	}

	// �����ǽ�<xxx></xxx>֮���ֵ���뵽currentValue
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		currentValue.append(ch, start, length);
	}

	// ������</xxx>������,��֮ǰ�����ƺ�ֵһһ��Ӧ������props��
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		props.put(qName.toLowerCase(), currentValue.toString().trim());
	}
}
