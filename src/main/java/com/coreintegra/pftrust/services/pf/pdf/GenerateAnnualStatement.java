package com.coreintegra.pftrust.services.pf.pdf;

import com.coreintegra.pftrust.dtos.pdf.yearend.AnnualStatement;
import com.coreintegra.pftrust.entities.tenant.PdfDocumentDesign;
import com.coreintegra.pftrust.repositories.pf.PdfDocumentDesignRepository;
import org.apache.commons.io.IOUtils;
import org.apache.fop.apps.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
public class GenerateAnnualStatement {

    private final PdfDocumentDesignRepository pdfDocumentDesignRepository;

    public GenerateAnnualStatement(PdfDocumentDesignRepository pdfDocumentDesignRepository) {
        this.pdfDocumentDesignRepository = pdfDocumentDesignRepository;
    }

    public ByteArrayOutputStream generate(AnnualStatement annualStatement) throws Exception {
        return generatePDFFromJavaObject(annualStatement);
    }

    private ByteArrayOutputStream generatePDFFromJavaObject(AnnualStatement annualStatement) throws Exception {

        ByteArrayOutputStream xmlSource = getXMLSource(annualStatement);

        StreamSource streamSource =
                new StreamSource(new ByteArrayInputStream(xmlSource.toByteArray()));

        return generatePDF(streamSource);

    }

    private ByteArrayOutputStream getXMLSource(AnnualStatement annualStatement) throws Exception {
        JAXBContext context;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        try {
            context = JAXBContext.newInstance(AnnualStatement.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(annualStatement, outStream);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return outStream;
    }

    private ByteArrayOutputStream  generatePDF(StreamSource streamSource) {

        Optional<PdfDocumentDesign> optional = pdfDocumentDesignRepository
                .findByDocumentType(PdfDocumentDesign.ANNUAL_STATEMENT);

        if(optional.isEmpty()) throw new EntityNotFoundException("Document Design No Found");

        InputStream in = IOUtils.toInputStream(optional.get().getDocument(), StandardCharsets.UTF_8);

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
