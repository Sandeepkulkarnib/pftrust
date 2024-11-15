package com.coreintegra.pftrust.services.pf.pdf;

import com.coreintegra.pftrust.dtos.pdf.NewMonthlyStatement;
import com.coreintegra.pftrust.repositories.pf.PdfDocumentDesignRepository;
import org.apache.fop.apps.*;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.net.URISyntaxException;

@Service
public class NewGenerateMonthlyStatement {

    private final PdfDocumentDesignRepository pdfDocumentDesignRepository;

    public NewGenerateMonthlyStatement(PdfDocumentDesignRepository pdfDocumentDesignRepository) {
        this.pdfDocumentDesignRepository = pdfDocumentDesignRepository;
    }

    public ByteArrayOutputStream generate(NewMonthlyStatement monthlyStatement) throws Exception {
        return generatePDFFromJavaObject(monthlyStatement);
    }

    private ByteArrayOutputStream generatePDFFromJavaObject(NewMonthlyStatement monthlyStatement) throws Exception {

        ByteArrayOutputStream xmlSource = getXMLSource(monthlyStatement);

        StreamSource streamSource =
                new StreamSource(new ByteArrayInputStream(xmlSource.toByteArray()));

        return generatePDF(streamSource);

    }

    private ByteArrayOutputStream getXMLSource(NewMonthlyStatement monthlyStatement) throws Exception {
        JAXBContext context;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        try {
            context = JAXBContext.newInstance(NewMonthlyStatement.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(monthlyStatement, outStream);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return outStream;
    }

    private ByteArrayOutputStream  generatePDF(StreamSource streamSource)
            throws FOPException, TransformerException, IOException, URISyntaxException {

//        Optional<PdfDocumentDesign> optional = pdfDocumentDesignRepository
//                .findByDocumentType(PdfDocumentDesign.MONTHLY_STATEMENT);
//
//        if(optional.isEmpty()) throw new EntityNotFoundException("Document Design No Found");
//
//        InputStream in = IOUtils.toInputStream(optional.get().getDocument(), StandardCharsets.UTF_8);


        InputStream in = Model.class.getClassLoader().getResourceAsStream("pdfs/newMonthlyStatement.xsl");

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
