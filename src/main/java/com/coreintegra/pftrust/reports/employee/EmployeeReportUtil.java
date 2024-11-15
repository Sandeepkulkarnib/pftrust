package com.coreintegra.pftrust.reports.employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeReportUtil {

    public static List<String> getCOLUMNS(){

        List<String> COLUMNS = new ArrayList<>(Arrays.asList("S.NO.", "PROVIDENT FUND",
                "PERN NO", "EMPLOEE NAME", "TOKEN NUMBER", "UNIT CODE", "DATE OF JOINING PF",
                "DATE OF JOINING", "UAN NUMBER", "DATE OF BIRTH", "LOCATION", "PAYROLL AREA",
                "CONTACT NO", "ALT CONTACT NUMBER", "EMAIL", "PERSONAL EMAIL", "DESIGNATION",
                "FROM EPS", "TO EPS", "PAN NO", "AADHAR NO", "LAST RECOVERY DATE", "CURRENT PF BASE",
                "GENDER", "STATUS", "DEPARTMENT", "ADDR LINE 1", "ADDR LINE 2", "ADDR LINE 3",
                "ADDR LINE 4", "BANK NAME", "ACCOUNT NO", "IFSC CODE", "MICR CODE"));

        int prevCompanyCountItr = 6;

        for (int pc = 0; pc < prevCompanyCountItr; pc++) {
            COLUMNS.add("PREV COMPANY NAME");
            COLUMNS.add("DATE OF JOINING");
            COLUMNS.add("DATE OF LEAVING");
            COLUMNS.add("ADDR LINE 1");
            COLUMNS.add("ADDR LINE 2");
            COLUMNS.add("ADDR LINE 3");
            COLUMNS.add("ADDR LINE 4");
        }

        int nomineeItr = 4;

        for (int nm = 0; nm < nomineeItr; nm++) {
            COLUMNS.add("NOMINEE NAME");
            COLUMNS.add("RELATIONSHIP");
            COLUMNS.add("SHARE");
        }

        return COLUMNS;
    }

}
