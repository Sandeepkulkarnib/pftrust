package com.coreintegra.pftrust.services.pf.pdf;

import com.coreintegra.pftrust.dtos.pdf.FormA;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

@Service
public class GenerateFormA {

    public ByteArrayOutputStream generate(FormA formA) throws Exception {
        return generatePDFFromJavaObject(formA);
    }

    private ByteArrayOutputStream generatePDFFromJavaObject(FormA formA) throws Exception {

        ByteArrayOutputStream xmlSource = getXMLSource(formA);

        StreamSource streamSource =
                new StreamSource(new ByteArrayInputStream(xmlSource.toByteArray()));

        return generatePDF(streamSource);

    }

    private ByteArrayOutputStream getXMLSource(FormA formA) throws Exception {
        JAXBContext context;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        try {
            context = JAXBContext.newInstance(FormA.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(formA, outStream);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return outStream;
    }

    private ByteArrayOutputStream  generatePDF(StreamSource streamSource) {

        InputStream in = Model.class.getClassLoader().getResourceAsStream("pdfs/FormA.xsl");

        // create an instance of fop factory
        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        // a user agent is needed for transformation
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
        // Setup output
        ByteArrayOutputStream out =  new ByteArrayOutputStream();

        try {

            // Construct fop with desired output format
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

            // Setup XSLT
            TransformerFactory factory = TransformerFactory.newInstance();

            Transformer transformer = factory.newTransformer(new StreamSource(in));

            // Resulting SAX events (the generated FO) must be piped through to FOP
            Result res = new SAXResult(fop.getDefaultHandler());

            // Start XSLT transformation and FOP processing
            // That's where the XML is first transformed to XSL-FO and then
            // PDF is created
            transformer.transform(streamSource, res);

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return out;

    }
    
}



