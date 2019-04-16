package mid_stu;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.stream.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

/**
 *
 * @author Jason
 */
public class CarsXmlRW {

    private SAXParserFactory saxParserFactory;
    private SAXParser saxParser;
    private StringWriter stringWriter;
    private XMLOutputFactory xmlOutputFactory;

    private String str;
    private List<Car> carList;
    private String name;
    private int price;
    private boolean imported;

    public CarsXmlRW() {
        carList = new ArrayList<>();   
        
    }
    
    public List<Car> read(){
    	
    	String filePath = "Cars.xml";

    	
    	saxParserFactory = SAXParserFactory.newInstance();
        
        try {
			 saxParser = saxParserFactory.newSAXParser();

			DefaultHandler handler = new DefaultHandler() 
			{
				String name = "";
				int price = 0;
				boolean bool_imported = false;
				
				boolean nameTime = false;
				boolean priceTime = false;
				
				
				public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException 
				{
					String imported = attributes.getValue("imported");

					if(qName.equals("name"))
					{
						if(imported.equalsIgnoreCase("true"))
							bool_imported = true;
						else
							bool_imported = false;	
							nameTime =true;
					}
					
					if(qName.equals("price"))
					{
						priceTime = true;
					}
					
				}

				public void characters(char ch[], int start, int length) throws SAXException 
				{			
					if(nameTime)
					{
						name = new String(ch, start, length);
						nameTime = false;
					}
					
					if(priceTime)
					{
						price = Integer.parseInt(new String(ch, start, length));
						carList.add(new Car(name, price, bool_imported));
						priceTime = false;
					}
					
					
				}

				public void endElement(String uri, String localName, String qName) throws SAXException 
				{
					if(qName.equals("Car"))
					{
//						carList.add(new Car(name, price, bool_imported));
//						name = null;
//						price = 0;
//						bool_imported = false;
					}

				}
			};
			
			saxParser.parse(filePath, handler);
	
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
        return carList;
    }
    
    public void write(List<Car> list){
        
    	
    	 stringWriter = new StringWriter();
    	 xmlOutputFactory = XMLOutputFactory.newInstance();
		
		try {
			XMLStreamWriter xMLStreamWriter = xmlOutputFactory.createXMLStreamWriter(stringWriter);
			
			xMLStreamWriter.writeStartDocument();
			xMLStreamWriter.writeStartElement("Cars");
			
			int count = 0;
			for(Car car : list)
			{
				xMLStreamWriter.writeStartElement("Car");
							
				xMLStreamWriter.writeStartElement("name");
				xMLStreamWriter.writeAttribute("imported", Boolean.toString(car.isImported()));
				xMLStreamWriter.writeCharacters(car.getName());
				xMLStreamWriter.writeEndElement();
				
				xMLStreamWriter.writeStartElement("price");
				xMLStreamWriter.writeCharacters("" + car.getPrice());
				xMLStreamWriter.writeEndElement();
			
				xMLStreamWriter.writeEndElement();
			}
			
//			xMLStreamWriter.writeEndElement();
			xMLStreamWriter.writeEndDocument();
			
			xMLStreamWriter.close();
			
			
			String xmlString = stringWriter.getBuffer().toString();
			FileWriter out = new FileWriter("Cars.xml");
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
