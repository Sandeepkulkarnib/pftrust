<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">


    <xsl:template match="loanReceipt">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

            <fo:layout-master-set>

                <fo:simple-page-master master-name="simpleA4"
                                       page-height="29.7cm" page-width="21cm"
                                       margin-top="1cm" margin-bottom="1cm"
                                       margin-left="1cm" margin-right="1cm">
                    <fo:region-body/>
                </fo:simple-page-master>

            </fo:layout-master-set>


            <fo:page-sequence master-reference="simpleA4">

                <fo:flow flow-name="xsl-region-body" font-size="12pt" line-height="1.5"
                         font-family="Times-Roman" word-spacing="6pt" letter-spacing="1pt">


                    <!-- logo and address -->
                    <fo:table line-height="1" word-spacing="6pt">
                        <fo:table-column column-width="25%"/>
                        <fo:table-column column-width="75%"/>

                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell>
                                    <fo:block>
                                        <fo:external-graphic src="http://3.110.35.227:8080/images/mahindra-full-logo.png"
                                                             content-height="110pt" content-width="110pt"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block margin-top="15pt">
                                        <fo:block font-weight="600" font-size="12pt">COREINTEGRA GLOBAL SERVICES PVT LIMITED  .  STAFF  PROVIDENT  FUND</fo:block>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>

                    <fo:block line-height="1.2" margin-top="20pt">
                        <fo:block>Date: <xsl:value-of select="approvalDate"/></fo:block>
                        <fo:block margin-top="20pt">Ref.: Application No.: <xsl:value-of select="applicationNumber"/></fo:block>
                    </fo:block>

                    <fo:block margin-top="20pt">Dear Sir / Madam,</fo:block>

                    <fo:block margin-top="20pt" line-height="1.2" text-align="justify">
                        Subject: Non Refundable Loan
                    </fo:block>

                    <fo:block margin-top="20pt" line-height="1.2" text-align="justify">
                        <fo:inline padding-left="50pt">With reference</fo:inline> to your application we have credited your
                        account No. <xsl:value-of select="accountNumber"/> with Bank "<xsl:value-of select="bankName"/>"
                        for Rs. <xsl:value-of select="amount"/> ( <xsl:value-of select="amountInWords"/> Only )
                        on <xsl:value-of select="paymentDate"/> by <xsl:value-of select="paymentMethod"/>
                        Facility through <xsl:value-of select="paymentBank"/>.
                    </fo:block>


                    <fo:block margin-top="30pt">COREINTEGRA GLOBAL SERVICES PVT LIMITED</fo:block>
                    <fo:block>Staff Provident Fund</fo:block>

                    <fo:block margin-top="30pt" padding-bottom="30pt"
                              margin-bottom="5pt"
                              border-bottom="1pt solid red">
                        This is a computer generated statement hence no signature required.</fo:block>

                    <fo:block text-align="center" font-size="12" font-weight="600"
                               margin-top="50pt"   >
                        RECEIPT
                    </fo:block>

                    <fo:block margin-top="20pt" text-align="justify">
                        Received from COREINTEGRA GLOBAL SERVICES PVT LIMITED  .  STAFF  PROVIDENT  FUND
                        The Sum of Rs. <xsl:value-of select="amount"/> (<xsl:value-of select="amountInWords"/> Only) Being
                        the Provident Fund Loan (Non-Refundable) sanctioned to me.
                    </fo:block>

                    <fo:block margin-top="50pt">Receiver's Signature</fo:block>

                    <fo:block margin-top="10pt">Name: <fo:inline padding-left="30pt"><xsl:value-of select="name"/></fo:inline></fo:block>

                    <fo:block margin-top="10pt">Company Name: <xsl:value-of select="deptCode"/> </fo:block>

                    <fo:block margin-top="10pt">
                         <fo:inline>P.F.No.: <xsl:value-of select="pfNumber"/></fo:inline>
                         <fo:inline padding-left="60pt">Token.No.: <xsl:value-of select="tokenNumber"/></fo:inline>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>

        </fo:root>
    </xsl:template>


</xsl:stylesheet>
