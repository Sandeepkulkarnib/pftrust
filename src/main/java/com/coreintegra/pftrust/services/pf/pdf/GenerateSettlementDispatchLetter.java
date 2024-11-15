package com.coreintegra.pftrust.services.pf.pdf;

import com.coreintegra.pftrust.dtos.pdf.settlement.SettlementDispatchLetter;
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
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
public class GenerateSettlementDispatchLetter {

    private final PdfDocumentDesignRepository pdfDocumentDesignRepository;

    public GenerateSettlementDispatchLetter(PdfDocumentDesignRepository pdfDocumentDesignRepository) {
        this.pdfDocumentDesignRepository = pdfDocumentDesignRepository;
    }

    public ByteArrayOutputStream generate(SettlementDispatchLetter settlementDispatchLetter) throws Exception {
        return generatePDFFromJavaObject(settlementDispatchLetter);
    }

    private ByteArrayOutputStream generatePDFFromJavaObject(SettlementDispatchLetter settlementDispatchLetter) throws Exception {

        ByteArrayOutputStream xmlSource = getXMLSource(settlementDispatchLetter);

        StreamSource streamSource =
                new StreamSource(new ByteArrayInputStream(xmlSource.toByteArray()));

        return generatePDF(streamSource);

    }

    private ByteArrayOutputStream getXMLSource(SettlementDispatchLetter settlementDispatchLetter) throws Exception {
        JAXBContext context;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        try {
            context = JAXBContext.newInstance(SettlementDispatchLetter.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(settlementDispatchLetter, outStream);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return outStream;
    }

    private ByteArrayOutputStream  generatePDF(StreamSource streamSource)
            throws FOPException, TransformerException, IOException, URISyntaxException {

        Optional<PdfDocumentDesign> optional = pdfDocumentDesignRepository
                .findByDocumentType(PdfDocumentDesign.SETTLEMENT_DISPATCH_LETTER);

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
