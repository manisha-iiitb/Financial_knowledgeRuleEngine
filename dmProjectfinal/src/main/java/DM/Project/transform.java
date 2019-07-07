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
        Source xslt = new StreamSource(new File("/home/divya/Downloads/DM project/STOCK.xsl"));
        Transformer transformer = factory.newTransformer(xslt);

        Source text = new StreamSource(new File("/home/divya/Downloads/DM project/STOCK.xml"));
        transformer.transform(text, new StreamResult(new File("/home/divya/Downloads/DM project/output.xml")));
       // Source text1 = new StreamSource(new File("/home/divya/Downloads/DM project/STOCK.xml"));
       // System.out.println(text1);
        File file = new File("/home/divya/Downloads/DM project/output.xml"); 
        
        BufferedReader br = new BufferedReader(new FileReader(file)); 
        
        String st= br.readLine(); 
        char ch[]=st.toCharArray();
        StringBuffer out=new StringBuffer();
        for(int i=38;i<ch.length;i++) 
          out.append(ch[i]); 
        return out.toString();
}
	
	public String[] transformbuy() throws IOException, URISyntaxException, TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(new File("/home/divya/Downloads/DM project/stock_buy.xsl"));
        Transformer transformer = factory.newTransformer(xslt);

        Source text = new StreamSource(new File("/home/divya/Downloads/DM project/stock_buy.xml"));
        transformer.transform(text, new StreamResult(new File("/home/divya/Downloads/DM project/output.xml")));
       // Source text1 = new StreamSource(new File("/home/divya/Downloads/DM project/STOCK.xml"));
       // System.out.println(text1);
        File file = new File("/home/divya/Downloads/DM project/output.xml"); 
        
        BufferedReader br = new BufferedReader(new FileReader(file)); 
        
        String st= br.readLine(); 
        String arr[]=st.split("#");
        for(int i=0;i<arr.length;i++)
        {
        	System.out.println(arr[i]);
        }
        return arr;
}

	public String[] transformsell() throws IOException, URISyntaxException, TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(new File("/home/divya/Downloads/DM project/stock_sell.xsl"));
        Transformer transformer = factory.newTransformer(xslt);

        Source text = new StreamSource(new File("/home/divya/Downloads/DM project/stock_sell.xml"));
        transformer.transform(text, new StreamResult(new File("/home/divya/Downloads/DM project/output.xml")));
       // Source text1 = new StreamSource(new File("/home/divya/Downloads/DM project/STOCK.xml"));
       // System.out.println(text1);
        File file = new File("/home/divya/Downloads/DM project/output.xml"); 
        
        BufferedReader br = new BufferedReader(new FileReader(file)); 
        
        String st= br.readLine(); 
        String arr[]=st.split("#");
        for(int i=0;i<arr.length;i++)
        {
        	System.out.println(arr[i]);
        }
        return arr;
}
	
	// ********************************************Divya***********************************************
	public String transform2() throws IOException, URISyntaxException, TransformerException {
		TransformerFactory factory = TransformerFactory.newInstance();
		Source xslt = new StreamSource(new File("/home/divya/Downloads/DM project/FixedDeposit.xsl"));
		Transformer transformer = factory.newTransformer(xslt);

		Source text = new StreamSource(new File("/home/divya/Downloads/DM project/FixedDeposit.xml"));
		transformer.transform(text, new StreamResult(new File("/home/divya/Downloads/DM project/outputFD.xml")));
		
		File file = new File("/home/divya/Downloads/DM project/outputFD.xml");

		BufferedReader br = new BufferedReader(new FileReader(file));

		String st = br.readLine();
		System.out.println(st);
		return st;	/*
		 * for(int i=1;i<bankname.length;i++) xml_HomeLoanEligibility.bankname[i] =
		 * bankname[i];
		 */
	        
	}

	public String transformFDA() throws IOException, URISyntaxException, TransformerException {
		TransformerFactory factory = TransformerFactory.newInstance();
		Source xslt = new StreamSource(new File("/home/divya/Downloads/DM project/fixedDepositAnalysis.xsl"));
		Transformer transformer = factory.newTransformer(xslt);

		Source text = new StreamSource(new File("/home/divya/Downloads/DM project/fixedDepositAnalysis.xml"));
		transformer.transform(text, new StreamResult(new File("/home/divya/Downloads/DM project/outputFDA.xml")));

		File file = new File("/home/divya/Downloads/DM project/outputFDA.xml");

		BufferedReader br = new BufferedReader(new FileReader(file));

		String st = br.readLine();
		System.out.println(st);
		return st;
	}

	
	// *************************************Jhalak************************************************
	public String transform3() throws IOException, URISyntaxException, TransformerException {
		TransformerFactory factory = TransformerFactory.newInstance();
		Source xslt = new StreamSource(new File("/home/divya/Downloads/DM project/gold.xsl"));
		Transformer transformer = factory.newTransformer(xslt);

		Source text = new StreamSource(new File("/home/divya/Downloads/DM project/gold.xml"));
		transformer.transform(text, new StreamResult(new File("/home/divya/Downloads/DM project/output.xml")));

		File file = new File("/home/divya/Downloads/DM project/output.xml");

		BufferedReader br = new BufferedReader(new FileReader(file));

		String st = br.readLine();
		char ch[] = st.toCharArray();
		StringBuffer out = new StringBuffer();
		for (int i = 38; i < ch.length; i++)
			out.append(ch[i]);
		return out.toString();
	}

	public String transformMF() throws IOException, URISyntaxException, TransformerException {
		TransformerFactory factory = TransformerFactory.newInstance();
		Source xslt = new StreamSource(new File("/home/divya/Downloads/DM project/mutualfund.xsl"));
		Transformer transformer = factory.newTransformer(xslt);

		Source text = new StreamSource(new File("/home/divya/Downloads/DM project/mutualfund.xml"));
		transformer.transform(text, new StreamResult(new File("/home/divya/Downloads/DM project/output.xml")));

		File file = new File("/home/divya/Downloads/DM project/output.xml");

		BufferedReader br = new BufferedReader(new FileReader(file));

		String st = br.readLine();
		char ch[] = st.toCharArray();
		StringBuffer out = new StringBuffer();
		for (int i = 38; i < ch.length; i++)
			out.append(ch[i]);
		return out.toString();
	}

   // **************************************divya***************************************************

	public String transform_HomeLoan() throws IOException, URISyntaxException, TransformerException {
		TransformerFactory factory = TransformerFactory.newInstance();
		Source xslt_HomeLoan = new StreamSource(new File("/home/divya/Downloads/DM project/HomeLoan.xsl"));
		Transformer transformer = factory.newTransformer(xslt_HomeLoan);

		Source text = new StreamSource(new File("/home/divya/Downloads/DM project/HomeLoan.xml"));
		transformer.transform(text, new StreamResult(new File("/home/divya/Downloads/DM project/output_HomeLoan.xml")));
		File file = new File("/home/divya/Downloads/DM project/output_HomeLoan.xml");

		BufferedReader br = new BufferedReader(new FileReader(file));

		String st = br.readLine();
		return st;
	}

	
	public String transform_HomeLoanEligibility() throws IOException, URISyntaxException, TransformerException {
		TransformerFactory factory = TransformerFactory.newInstance();
		Source xslt_HomeLoan = new StreamSource(new File("/home/divya/Downloads/DM project/HomeLoanEligibility.xsl"));
		Transformer transformer = factory.newTransformer(xslt_HomeLoan);

		Source text = new StreamSource(new File("/home/divya/Downloads/DM project/HomeLoanEligibility.xml"));
		transformer.transform(text,
				new StreamResult(new File("/home/divya/Downloads/DM project/output_HomeLoanEligibility.xml")));
		File file = new File("/home/divya/Downloads/DM project/output_HomeLoanEligibility.xml");

		BufferedReader br = new BufferedReader(new FileReader(file));

		String st = br.readLine();
		return st;
	}

}
