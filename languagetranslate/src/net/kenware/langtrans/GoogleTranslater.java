/*
 * GoogleTranslater.java
 *
 * Created on March 23, 2007, 10:01 PM
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
 *
 * @author klanger
 */
public class GoogleTranslater {
    
    /**
     * Creates a new instance of GoogleTranslater
     */
    public GoogleTranslater() {
        return;
    }
    
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
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GoogleTranslater gt = new GoogleTranslater();
        String s = "I like my hand";
        System.out.println("INPUT: [" + s + "]");
        
        System.out.println("OUTPUT: [" + gt.urlTranslate(s,"en","fr") + "]");
        
        System.exit(0);
    }
} // end-class
