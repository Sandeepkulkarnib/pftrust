package com.coreintegra.pftrust.util;

import java.text.DecimalFormat;

public class EnglishNumberToWords {


    private static final String[] tensNames = {
            "",
            " ten",
            " twenty",
            " thirty",
            " forty",
            " fifty",
            " sixty",
            " seventy",
            " eighty",
            " ninety"
    };

    private static final String[] numNames = {
            "",
            " one",
            " two",
            " three",
            " four",
            " five",
            " six",
            " seven",
            " eight",
            " nine",
            " ten",
            " eleven",
            " twelve",
            " thirteen",
            " fourteen",
            " fifteen",
            " sixteen",
            " seventeen",
            " eighteen",
            " nineteen"
    };

    private static String convertLessThanOneThousand(int number) {
        String soFar;

        if (number % 100 < 20){
            soFar = numNames[number % 100];
            number /= 100;
        }
        else {
            soFar = numNames[number % 10];
            number /= 10;

            soFar = tensNames[number % 10] + soFar;
            number /= 10;
        }

        if (number == 0) return soFar;

        return numNames[number] + " hundred" + soFar;

    }


    public static String convert(long number) {
        // 0 to 999 999 999 999
        if (number == 0) { return "zero"; }

        String snumber = Long.toString(number);

        // pad with "0"
        //        000 00 00 000
        String mask = "0000000000";

        DecimalFormat df = new DecimalFormat(mask);

        snumber = df.format(number);

//        196048

        //  XXX nn nn nnn
        int crores = Integer.parseInt(snumber.substring(0,3));

        // nnn XX nn nnn
        int lakhs  = Integer.parseInt(snumber.substring(3,5));

        // nnn nn XX nnn
        int hundredThousands = Integer.parseInt(snumber.substring(5,7));

        // nnn nn nn XXX
        int thousands = Integer.parseInt(snumber.substring(7,10));

        String tradBillions;
        switch (crores) {
            case 0:
                tradBillions = "";
                break;
            case 1 :
                tradBillions = convertLessThanOneThousand(crores)
                        + " crore ";
                break;
            default :
                tradBillions = convertLessThanOneThousand(crores)
                        + " crore ";
        }
        String result =  tradBillions;

        String tradMillions;
        switch (lakhs) {
            case 0:
                tradMillions = "";
                break;
            case 1 :
                tradMillions = convertLessThanOneThousand(lakhs)
                        + " lakh ";
                break;
            default :
                tradMillions = convertLessThanOneThousand(lakhs)
                        + " lakh ";
        }
        result =  result + tradMillions;

        String tradHundredThousands;
        switch (hundredThousands) {
            case 0:
                tradHundredThousands = "";
                break;
            case 1 :
                tradHundredThousands = "one thousand ";
                break;
            default :
                tradHundredThousands = convertLessThanOneThousand(hundredThousands)
                        + " thousand ";
        }
        result =  result + tradHundredThousands;

        String tradThousand;
        tradThousand = convertLessThanOneThousand(thousands);
        result =  result + tradThousand;

        // remove extra spaces!
        return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
    }

//
//    public static void main(String[] args) {
//        System.out.println(EnglishNumberToWords.convert(9111112201L));
//    }


}
