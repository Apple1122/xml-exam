package xmlFinalExam;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;


public class OrderRW {
	
	private StringWriter stringWriter;
	private XMLOutputFactory xmlOutputFactory;
	
	public void write(List<Order> orders)
	{
	       
    	
	    	 stringWriter = new StringWriter();
	    	 xmlOutputFactory = XMLOutputFactory.newInstance();
			
			try {
				XMLStreamWriter xMLStreamWriter = xmlOutputFactory.createXMLStreamWriter(stringWriter);
				
				xMLStreamWriter.writeStartDocument();
				xMLStreamWriter.writeStartElement("Orders");

				for(Order order : orders)
				{
					xMLStreamWriter.writeStartElement("Order");
					
					xMLStreamWriter.writeStartElement("Item");
					xMLStreamWriter.writeCharacters(order.getItem());
					xMLStreamWriter.writeEndElement();
					
					xMLStreamWriter.writeStartElement("Address");
					xMLStreamWriter.writeCharacters(order.getAddress());
					xMLStreamWriter.writeEndElement();
					
					xMLStreamWriter.writeEndElement();
				}
				xMLStreamWriter.writeEndElement();
				xMLStreamWriter.writeEndDocument();
				
				xMLStreamWriter.close();
				
				
				String xmlString = stringWriter.getBuffer().toString();
				FileWriter out = new FileWriter("src/Transaction.xml");
				out.write(xmlString);
				out.close();
				
				stringWriter.close();
				
				
			} catch (XMLStreamException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

}
