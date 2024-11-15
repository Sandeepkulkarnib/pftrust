<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template match="settlementDispatchLetter">
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
                        This is a computer generated statement hence no signature required.
                    </fo:block>
                </fo:static-content>


                <fo:flow flow-name="xsl-region-body" font-size="12pt" line-height="1.5"
                         font-family="Times-Roman" word-spacing="6pt" letter-spacing="1pt">

                    <!-- logo and address -->
                    <fo:block>
                        <fo:external-graphic src="http://3.110.35.227:8080/images/mahindra-full-logo.png" content-height="75pt" content-width="75pt"/>
                    </fo:block>

                    <fo:block text-align="center" line-height="1.2">
                        <fo:block font-weight="600">COREINTEGRA GLOBAL SERVICES PVT LIMITED  .  STAFF  PROVIDENT  FUND</fo:block>
                        <fo:block>VINMAR HOUSE, PLOT NO. A/41, ROAD NO. 2, MIDC,</fo:block>
                        <fo:block>ANDHERI (EAST)  MUMBAI - 400093</fo:block>
                        <fo:block>Tel : (022) 68135640/5621</fo:block>
                        <fo:block>Fax : (022) 29653484</fo:block>
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
                        <fo:table-column column-width="50%"/>

                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell>
                                    <fo:block text-align="left" font-size="11" font-weight="600">
                                        <fo:block><xsl:value-of select="name"/></fo:block>
                                        <fo:block><xsl:value-of select="addressLine1"/></fo:block>
                                        <fo:block><xsl:value-of select="addressLine2"/></fo:block>
                                        <fo:block><xsl:value-of select="addressLine3"/></fo:block>
                                        <fo:block><xsl:value-of select="addressLine4"/></fo:block>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>

                    <fo:block margin-top="20pt" margin-bottom="10pt">Dear Sir/Madam, </fo:block>

                    <fo:block margin-top="20pt" margin-left="40pt" word-spacing="8pt" line-height="1.2">
                        <fo:block>Subject : Settlement of your Provident Fund dues</fo:block>
                        <fo:block>Provident fund number : <xsl:value-of select="fromPfNumber"/></fo:block>
                    </fo:block>

                    <fo:block margin-top="20pt" word-spacing="6pt"
                              line-height="1.2" text-align="justify">
                        With reference to your advance stamped receipt in Form 19, we have credited
                        your Saving Bank Account No. <fo:inline><xsl:value-of select="accountNo"/></fo:inline> with <fo:inline><xsl:value-of select="memberBank"/></fo:inline> bank with amount of
                        Rs. <fo:inline><xsl:value-of select="amount"/></fo:inline> (<fo:inline><xsl:value-of select="amountInWords"/></fo:inline>) on
                        <fo:inline><xsl:value-of select="paymentDate"/></fo:inline> by <fo:inline><xsl:value-of select="paymentMode"/></fo:inline> Facility through <fo:inline><xsl:value-of select="bank"/></fo:inline>. being the amount paid to
                        you towards the full and final settlement of your Provident Fund dues.
                    </fo:block>

                    <fo:block margin-top="20pt" word-spacing="6pt" line-height="1.2">
                        We also enclose herewith a statement for your record.
                    </fo:block>


                    <fo:block margin-top="20pt" word-spacing="6pt" line-height="1.2">
                        Kindly check your bank account for the above credit.
                    </fo:block>

                    <fo:block margin-top="20pt">Thanking you,</fo:block>
                    <fo:block margin-top="10pt">Yours faithfully,</fo:block>
                    <fo:block>For COREINTEGRA GLOBAL SERVICES PVT LIMITED,</fo:block>
                    <fo:block>STAFF PROVIDENT FUND</fo:block>

                    <fo:block margin-top="40pt">Authorized Signatory</fo:block>

                </fo:flow>
            </fo:page-sequence>

        </fo:root>
    </xsl:template>

</xsl:stylesheet>
