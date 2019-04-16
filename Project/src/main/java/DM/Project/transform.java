package DM.Project;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
public class transform {

    public String transform1() throws IOException, URISyntaxException, TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(new File("/home/manisha/Downloads/DM project/STOCK.xsl"));
        Transformer transformer = factory.newTransformer(xslt);

        Source text = new StreamSource(new File("/home/manisha/Downloads/DM project/STOCK.xml"));
        transformer.transform(text, new StreamResult(new File("/home/manisha/Downloads/DM project/output.xml")));
       // Source text1 = new StreamSource(new File("/home/manisha/Downloads/DM project/STOCK.xml"));
       // System.out.println(text1);
        File file = new File("/home/manisha/Downloads/DM project/output.xml"); 
        
        BufferedReader br = new BufferedReader(new FileReader(file)); 
        
        String st= br.readLine(); 
        char ch[]=st.toCharArray();
        StringBuffer out=new StringBuffer();
        for(int i=38;i<ch.length;i++) 
          out.append(ch[i]); 
        return out.toString();
}
    public String transform2() throws IOException, URISyntaxException, TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(new File("/home/manisha/Downloads/DM project/FixedDeposit.xsl"));
        Transformer transformer = factory.newTransformer(xslt);

        Source text = new StreamSource(new File("/home/manisha/Downloads/DM project/FixedDeposit.xml"));
        transformer.transform(text, new StreamResult(new File("/home/manisha/Downloads/DM project/output2.xml")));
       // Source text1 = new StreamSource(new File("/home/manisha/Downloads/DM project/STOCK.xml"));
       // System.out.println(text1);
        File file = new File("/home/manisha/Downloads/DM project/output2.xml"); 
        
        BufferedReader br = new BufferedReader(new FileReader(file)); 
        
        String st= br.readLine(); 
        System.out.println(st);
       /* char ch[]=st.toCharArray();
        StringBuffer out=new StringBuffer();
        for(int i=38;i<ch.length;i++) 
          out.append(ch[i]); */
        return st;
}
    //Jhalak
    public String transform3() throws IOException, URISyntaxException, TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(new File("/home/manisha/Downloads/DM project/gold.xsl"));
        Transformer transformer = factory.newTransformer(xslt);

        Source text = new StreamSource(new File("/home/manisha/Downloads/DM project/gold.xml"));
        transformer.transform(text, new StreamResult(new File("/home/manisha/Downloads/DM project/output.xml")));
       // Source text1 = new StreamSource(new File("/home/manisha/Downloads/DM project/STOCK.xml"));
       // System.out.println(text1);
        File file = new File("/home/manisha/Downloads/DM project/output.xml"); 
        
        BufferedReader br = new BufferedReader(new FileReader(file)); 
        
        String st= br.readLine(); 
        char ch[]=st.toCharArray();
        StringBuffer out=new StringBuffer();
        for(int i=38;i<ch.length;i++) 
          out.append(ch[i]); 
        return out.toString();
}
    //Jyoti
    
    
    public String transform_HomeLoan() throws IOException, URISyntaxException, TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt_HomeLoan = new StreamSource(new File("/home/manisha/Downloads/DM project/HomeLoan.xsl"));
        Transformer transformer = factory.newTransformer(xslt_HomeLoan);

        Source text = new StreamSource(new File("/home/manisha/Downloads/DM project/HomeLoan.xml"));
        transformer.transform(text, new StreamResult(new File("/home/manisha/Downloads/DM project/output_HomeLoan.xml")));
        File file = new File("/home/manisha/Downloads/DM project/output_HomeLoan.xml"); 
        
        BufferedReader br = new BufferedReader(new FileReader(file)); 
        
        String st= br.readLine(); 
        return st;
}
}
