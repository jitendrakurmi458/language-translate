/*
 * GoogleTranslater.java
 *
 * Created on March 23, 2007, 10:01 PM
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

package net.kenware.langtrans;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;

/*
 * 3rd party Apache Libs
 */
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

/*
 * 3rd party Jericho libs
 */
import au.id.jericho.lib.html.*;

/**
 * A module for taking an input string along with country codes (source/target)
 * and converts it to the target language.  It uses Google's Language Tool
 * site to do the actual translating.  
 * 
 * This program was done to simplify and speed up my ability to translate 
 * quick message.
 *
 * @author uuklanger
 */
public class GoogleTranslater {
    // =============================================================
    //  Constructors and Init
    // =============================================================
    
    // <editor-fold defaultstate="collapsed" desc="Constructors and Init">
    /**
     * Creates a new instance of GoogleTranslater
     */
    public GoogleTranslater() {
        return;
    }
    // </editor-fold>
    
    // =============================================================
    //  Public Methods
    // =============================================================
    
    // <editor-fold defaultstate="collapsed" desc="Public Methods">
    /**
     * translates source string (sin) into a response using the source/target
     * languages.
     *
     * @param sin String in
     * @param sourcelang source language (ISO3166 code)
     * @param targetlang target language (ISO3166 code)
     * @return returns a text string in the target language
     */
    public String urlTranslate(String sin, String sourcelang, String targetlang) {
        String response = "";
        String url = null;
        HttpClient client = new HttpClient();
        HttpMethod method = null;
        String responsebody = null;
        
        try {
            String s = URLEncoder.encode(sin,"UTF-8");
            url = "http://google.com/translate_t?hl=en&ie=UTF8&text=" + s + "&langpair=" + sourcelang + "%7C" + targetlang;
            client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
            
            method = new GetMethod(url);
            method.setFollowRedirects(true);
            
            client.executeMethod(method);
            responsebody = method.getResponseBodyAsString();
            method.releaseConnection();
            
            response = extractResponse(responsebody);
            
        } catch (HttpException he) {
            System.err.println("Http error connecting to '" + url + "'");
            System.err.println(he.getMessage());
            
        } catch (IOException ioe){
            System.err.println("Unable to connect to '" + url + "'");
            
        } catch(Exception ex) {
            System.err.println("EXCEPTION: " + ex.getMessage());
        } // end-try-catch
        
        return(response);
    }
    // </editor-fold>
    
    // =============================================================
    //  Private Methods
    // =============================================================
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    /**
     * Using jericho libraries, this will extract the response
     * text from the HTML blob returned from google Language Tools.
     *
     * @param html HTML blob
     * @return Response text
     */
    private String extractResponse(String html) {
        String response = null;
        String attribute = null;
        Element linkElement = null;
        List linkElements = null;
        
        try {
            Source source = new Source(html);
            source.setLogWriter(new OutputStreamWriter(System.err)); // send log messages to stderr
            source.fullSequentialParse();
            
            //
            // Looking for the following tag shape<div id=result_box dir=ltr>
            //
            linkElements=source.findAllElements(HTMLElementName.DIV);
            
            //
            // for each element (of type) found
            //
            for (Iterator i=linkElements.iterator(); i.hasNext();) {
                linkElement=(Element)i.next();
                attribute=linkElement.getAttributeValue("id");

                //
                // if there is an attribute and if it is "result_box"
                // then display return it.
                // 
                if (attribute != null) {
                    if (attribute.equals("result_box")) {
                        response = linkElement.getContent().extractText();
                    } // end-if
                } // end-if
            } // end-for
            
        } catch(Exception ex) {
            System.err.println("EXCEPTION: " + ex.getMessage());
        } // end-try-catch
        
        return(response);
    }
    // </editor-fold>
    
    // =============================================================
    //  TEST MAIN
    // =============================================================
    
    // <editor-fold defaultstate="collapsed" desc="Test Main">
    /**
     * Test main for development
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GoogleTranslater gt = new GoogleTranslater();
        String s = "I like my hand";
        System.out.println("INPUT: [" + s + "]");
        
        System.out.println("OUTPUT: [" + gt.urlTranslate(s,"en","fr") + "]");
        
        System.exit(0);
    }
    // </editor-fold>
} // end-class
