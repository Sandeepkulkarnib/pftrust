<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template match="transferOutDispatchLetter">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

            <fo:layout-master-set>
                <fo:simple-page-master master-name="simpleA4"
                                       page-height="29.7cm" page-width="21cm"
                                       margin-top="1cm" margin-bottom="1cm"
                                       margin-left="1cm" margin-right="1cm">
                    <fo:region-body/>
                    <fo:region-after region-name="xsl-region-after" extent=".3in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>


            <fo:page-sequence master-reference="simpleA4">

                <fo:static-content flow-name="xsl-region-after" font-size="12pt" line-height="1"
                                   font-family="Times-Roman" word-spacing="6pt" letter-spacing="1pt">
                    <fo:block>
                        Registered Office : Gateway Building, Apollo Bunder, Mumbai - 400 001
                    </fo:block>
                </fo:static-content>


                <fo:flow flow-name="xsl-region-body" font-size="12pt" line-height="1.5"
                         font-family="Times-Roman" word-spacing="6pt" letter-spacing="1pt">


                    <!-- logo and address -->
                    <fo:block>
                        <fo:external-graphic src="http://3.110.35.227:8080/images/mahindra-full-logo.png" content-height="75pt" content-width="75pt"/>
                    </fo:block>

                    <fo:block>

                        <fo:table line-height="1" word-spacing="6pt">
                            <fo:table-column column-width="15%"/>
                            <fo:table-column column-width="60%"/>
                            <fo:table-column column-width="25%"/>

                            <fo:table-body>
                                <fo:table-row>

                                    <fo:table-cell>
                                        <fo:block>
                                            <fo:block>User: <xsl:value-of select="createdBy"/></fo:block>
                                        </fo:block>
                                    </fo:table-cell>

                                    <fo:table-cell>
                                        <fo:block text-align="center" font-weight="600">
                                            <fo:block>COREINTEGRA GLOBAL SERVICES PVT LIMITED</fo:block>
                                            <fo:block>Staff Provident Fund</fo:block>
                                            <fo:block>VINMAR HOUSE, PLOT NO. A/41, ROAD NO. 2, MIDC,</fo:block>
                                            <fo:block>ANDHERI (EAST)</fo:block>
                                            <fo:block>MUMBAI - 400093</fo:block>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>Tel: (022) 68135627</fo:block>
                                        <fo:block>68135628</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                    <!-- logo and address end -->

                    <fo:table margin-top="20pt" line-height="1">
                        <fo:table-column column-width="50%"/>
                        <fo:table-column column-width="50%"/>

                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell>
                                    <fo:block text-align="left">
                                        <fo:block>Ref. No. SPF/ <xsl:value-of select="refNo"/></fo:block>
                                        <fo:block>Stl.S. No. : <xsl:value-of select="stlNo"/></fo:block>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block text-align="right">
                                        <fo:block><xsl:value-of select="date"/></fo:block>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>

                    </fo:table>



                    <fo:table margin-top="20pt" line-height="1">
                        <fo:table-column column-width="75%"/>
                        <fo:table-column column-width="25%"/>

                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell>
                                    <fo:block text-align="left" font-size="11" font-weight="600">
                                        <fo:block><xsl:value-of select="payeeName"/></fo:block>
                                        <fo:block><xsl:value-of select="addressLine1"/></fo:block>
                                        <fo:block><xsl:value-of select="addressLine2"/></fo:block>
                                        <fo:block><xsl:value-of select="addressLine3"/></fo:block>
                                        <fo:block><xsl:value-of select="addressLine4"/></fo:block>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block text-align="right">
                                        <fo:block>Registered A. D.</fo:block>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>

                    <fo:block margin-top="20pt" margin-bottom="10pt">Dear Sir/Madam, </fo:block>

                    <fo:block margin-left="40pt" word-spacing="8pt" line-height="1.2">
                        <fo:block>Sub : Transfer of Provident fund Accumulation of</fo:block>
                        <fo:block>Mr./Mrs. <xsl:value-of select="name"/></fo:block>
                        <fo:block>
                            <fo:inline padding-right="100pt">From : <xsl:value-of select="fromPfNumber"/></fo:inline>
                            <fo:block padding-right="100pt">To : <xsl:value-of select="toPfNumber"/></fo:block>
                        </fo:block>
                    </fo:block>

                    <fo:block margin-top="20pt" word-spacing="6pt"
                              line-height="1.2" text-align="justify">This has reference to your Form-13 application for transfer of
                        Provident Fund accumulations of the above ex-employee.</fo:block>

                    <fo:block margin-top="20pt" word-spacing="6pt"
                              line-height="1.2" text-align="justify">Please find enclosed our UTR/RRN Sr.No/IFT Ref No/cheque/DD no. <fo:inline><xsl:value-of select="chequeNumber"/></fo:inline>
                        dated <fo:inline><xsl:value-of select="paymentDate"/></fo:inline>
                    for Rs. <fo:inline><xsl:value-of select="amount"/></fo:inline> (<fo:inline><xsl:value-of select="amountInWords"/></fo:inline>).
                    drawn on <fo:inline><xsl:value-of select="bank"/></fo:inline>., Mumbai in your favour being the Provident Fund accumulations of
                    Mr./Mrs. <fo:inline><xsl:value-of select="name"/></fo:inline> together with Annexure "K" for your record.</fo:block>

                    <fo:block margin-top="20pt" word-spacing="6pt"
                              line-height="1.2" text-align="justify">Kindly credit the amount to the member's account no
                    maintained by you and send us your stamped receipt for this payment.</fo:block>

                    <fo:block margin-top="20pt" margin-right="10%" line-height="1">
                        Thanking you,
                    </fo:block>


                    <fo:block margin-top="20pt" margin-right="10%" line-height="1" word-spacing="8pt">
                        <fo:block>Yours faithfully,</fo:block>
                        <fo:block>FOR COREINTEGRA GLOBAL SERVICES PVT LIMITED</fo:block>
                        <fo:block>STAFF PROVIDENT FUND</fo:block>
                    </fo:block>


                    <fo:block margin-top="40pt" margin-right="10%" word-spacing="8pt" line-height="1">
                        Authorized Signatory.
                    </fo:block>


                    <fo:block margin-top="15pt" margin-right="10%" word-spacing="8pt" line-height="1">
                        <fo:block>C.C.C/O <fo:inline><xsl:value-of select="name"/></fo:inline></fo:block>
                        <fo:block margin-left="58pt"><xsl:value-of select="currentEmployerName"/></fo:block>
                        <fo:block margin-left="58pt"><xsl:value-of select="currentEmployerAddressLine1"/></fo:block>
                        <fo:block margin-left="58pt"><xsl:value-of select="currentEmployerAddressLine2"/></fo:block>
                        <fo:block margin-left="58pt"><xsl:value-of select="currentEmployerAddressLine3"/></fo:block>
                        <fo:block margin-left="58pt"><xsl:value-of select="currentEmployerAddressLine4"/></fo:block>
                    </fo:block>

                </fo:flow>
            </fo:page-sequence>

        </fo:root>
    </xsl:template>

</xsl:stylesheet>
