package com.coreintegra.pftrust.services.pf.pdf;

import com.coreintegra.pftrust.dtos.pdf.NewContributionDetailsForMonthlyStatement;
import com.coreintegra.pftrust.dtos.pdf.NewMonthlyStatement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class NewGenerateMonthlyStatementTest {

    @Autowired
    private NewGenerateMonthlyStatement newGenerateMonthlyStatement;

    @Test
    void new_monthly_statement() throws Exception {

        NewMonthlyStatement newMonthlyStatement = new NewMonthlyStatement();

        newMonthlyStatement.setName("Rayees");
        newMonthlyStatement.setTokenNumber("123");
        newMonthlyStatement.setUnitCode("899000");
        newMonthlyStatement.setLastRecoveryDate(new Date());
        newMonthlyStatement.setStatus("Active");
        newMonthlyStatement.setStatusDate(new Date());
        newMonthlyStatement.setNominees(new ArrayList<>());
        newMonthlyStatement.setFinantialYear(2023);
        newMonthlyStatement.setClosingDate(new Date());

        List<NewContributionDetailsForMonthlyStatement> newContributionDetailsForMonthlyStatements = List.of(new NewContributionDetailsForMonthlyStatement(),
                new NewContributionDetailsForMonthlyStatement());

        newMonthlyStatement.setContributionDetails(newContributionDetailsForMonthlyStatements);

        ByteArrayOutputStream byteArrayOutputStream = newGenerateMonthlyStatement.generate(newMonthlyStatement);

        try(OutputStream outputStream = new FileOutputStream("monthly_statement.pdf")) {
            byteArrayOutputStream.writeTo(outputStream);
        }

    }
}