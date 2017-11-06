/*
 * Copyright 2017 Jani Pasanen
 * 
 *
 */
/**
 *
 * @author Jani Pasanen
 */
package lunchmeny;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LunchmenyModel {

    // Bilder
    // http://starbowling.se/menu-xml
    // Namn på rätter        
    // http://starbowling.se/lunch-xml
    private static String daysMeatCheck = "";
    private static String daysFishCheck = "";
    private static String daysPastaCheck = "";
    private static String daysVegetarianCheck = "";

    public static String daysMeat = "";
    public static String daysFish = "";
    public static String daysPasta = "";
    public static String daysVegetarian = "";

    public void lasInDagensLunch(String dow) throws IOException, SAXException {

        try {

            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser saxParser = spf.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                boolean meat = false;
                boolean fish = false;
                boolean pasta = false;
                boolean vegetarian = false;

                public void startElement(String uri, String localName,
                        String qName, Attributes attributes)
                        throws SAXException {

                    // System.out.println("Start Element :" + qName);
                    if (qName.equalsIgnoreCase(dow + "_meat") 
                            && !qName.equalsIgnoreCase("saturday_meat") 
                            || !qName.equalsIgnoreCase("sunday_meat")
                            ) {
                        meat = true;
                    }

                    
                    if (qName.equalsIgnoreCase(dow + "_fish") 
                            && !qName.equalsIgnoreCase("saturday_fish") 
                            || !qName.equalsIgnoreCase("sunday_fish")
                            ) {
                        fish = true;
                    }

                    if (qName.equalsIgnoreCase(dow + "_pasta") 
                            && !qName.equalsIgnoreCase("saturday_pasta") 
                            || !qName.equalsIgnoreCase("sunday_pasta")
                            ) {
                        pasta = true;
                    }

                    if (qName.equalsIgnoreCase(dow + "_veg") 
                            && !qName.equalsIgnoreCase("saturday_veg") 
                            || !qName.equalsIgnoreCase("sunday_veg")
                            ) {
                        vegetarian = true;
                    }

                }

                public void endElement(String uri, String localName,
                        String qName)
                        throws SAXException {

                    //  System.out.println("End Element :" + qName);
                }

                /* TODO: Lägg till null if kontroll på XML så att värden sätts till någonting om dag är lördag eller söndag eller om rätter inte har satts. 
                    Skall hantera alla dagar och alla
                      rätter. Om ej definierade skall denna sätta standardtext.*/
                public void characters(char ch[], int start, int length)
                        throws SAXException {

                    //   System.out.println(new String(ch, start, length));
                    if (meat) {
                        daysMeatCheck = new String(ch, start, length);
                        if (daysMeatCheck != null) {
                            daysMeat = new String(ch, start, length);
                        } else {
                            daysMeat = " ";
                        }
                        //       System.out.println("Meat : " + daysMeat);
                        meat = false;
                    } else {
                        daysMeat = " ";
                    }

                    if (fish) {
                        daysFishCheck = new String(ch, start, length);
                        if (daysFishCheck != null) {
                            daysFish = new String(ch, start, length);
                        } else {
                            daysFish = " ";
                        }

                        //     System.out.println("Fish : " + daysFish);
                        fish = false;
                    } else {
                        daysFish = " ";
                    }

                    if (pasta) {
                        daysPastaCheck = new String(ch, start, length);
                        if (daysPastaCheck != null) {
                            daysFish = new String(ch, start, length);
                        } else {
                            daysFish = " ";
                        }
                        //    System.out.println("Pasta : " + daysPasta);
                        pasta = false;
                    } else {
                        daysPasta = " ";
                    }

                    if (vegetarian) {
                        daysVegetarianCheck = new String(ch, start, length);
                        if (daysVegetarianCheck != null) {
                            daysVegetarian = new String(ch, start, length);
                        } else {
                            daysVegetarian = " ";
                        }
                        //      System.out.println("Vegetarian : " + daysVegetarian);
                        vegetarian = false;
                    } else {
                        daysVegetarian = " ";
                    }

                }

            };

            URL url = new URL("http://starbowling.se/lunch-xml");
            InputStream input = url.openStream();
            Reader reader = new InputStreamReader(input, "UTF-8");

            InputSource is = new InputSource(reader);
            is.setEncoding("UTF-8");

            saxParser.parse(is, handler);

        } catch (IOException | ParserConfigurationException | SAXException e) {
            System.out.println("Trying to read XML file from URL =" + e);
            e.printStackTrace();
            // Loggning till syslog
            Logger.getLogger(LunchmenyController.class.getName()).log(Level.SEVERE, null, e);
  
        }
    }


}
