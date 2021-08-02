package com.example.football.util;

import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

@Component
public class XmlProcessorImpl implements XmlProcessor {
    @Override
    public String serialize(Object obj) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw = new StringWriter();
            marshaller.marshal(obj, sw);
            return sw.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void serialize(Object obj, String fileName) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            FileWriter fw = new FileWriter(fileName);

            marshaller.marshal(obj, fw);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> T deserialize(String input, Class<T> toType) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(toType);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
            return (T) unmarshaller.unmarshal(inputStream);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> T deserializeFromFile(String fileName, Class<T> toType) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(toType);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (T) unmarshaller.unmarshal(new FileInputStream(fileName));
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}