package com.coreintegra.pftrust.util;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

class NumberFormatterTest {

    @Test
    void formatNumbers() {

        String s = NumberFormatter.formatNumbers(0f);

        System.out.println(s);

    }


    @Test
    void mergePdf() throws IOException {

        PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();

        File file = new File("136848581_REPAYMENT.pdf");
        File file1 = new File("Cert-Guide-AWS-2020.pdf");

        pdfMergerUtility.setDestinationFileName("merged_file.pdf");
        pdfMergerUtility.addSource(file);
        pdfMergerUtility.addSource(file1);

        pdfMergerUtility.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());

    }
}