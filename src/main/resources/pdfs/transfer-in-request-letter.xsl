<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template match="transferInRequest">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

            <fo:layout-master-set>
                <fo:simple-page-master master-name="simpleA4"
                                       page-height="29.7cm" page-width="21cm"
                                       margin-top="1cm" margin-bottom="0.8cm"
                                       margin-left="1cm" margin-right="1cm">
                    <fo:region-body/>
                    <fo:region-after region-name="xsl-region-after" extent=".5in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>


            <fo:page-sequence master-reference="simpleA4">

                <fo:static-content flow-name="xsl-region-after" font-size="10pt" line-height="1"
                                   font-family="Times-Roman" font-weight="600" word-spacing="4pt"
                                   letter-spacing="1pt">
                    <fo:block>CC : </fo:block>
                    <fo:block>THE REGIONAL PROVIDENT FUND OFFICE,</fo:block>
                    <fo:block>Employees Provident Fund Organisation
4th Floor, MTNL Building, Secotr-5,</fo:block>
                    <fo:block>Charkop Market, Kandivali (West),
RO Kandivali-II, Mumabi 400 067.</fo:block>
                </fo:static-content>



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
                                        <fo:external-graphic src="http://3.110.35.227:8080/images/mahindra_logo.png"
                                                             content-height="110pt" content-width="110pt"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block>
                                        <fo:block font-weight="600" font-size="14pt">COREINTEGRA GLOBAL SERVICES PVT LIMITED  .  STAFF  PROVIDENT  FUND</fo:block>
                                        <fo:block>VINMAR HOUSE, PLOT NO. A/41, ROAD NO. 2, MIDC,</fo:block>
                                        <fo:block>ANDHERI (EAST)  MUMBAI - 400093</fo:block>
                                        <fo:block>Tel.: (022) 6813 5640</fo:block>
                                        <fo:block>Fax.: (022) 2965 3484</fo:block>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>

                    <fo:block line-height="1.2" margin-top="20pt">
                        <fo:block>Ref.No. <xsl:value-of select="refNo"/> </fo:block>
                        <fo:block><xsl:value-of select="date"/></fo:block>
                    </fo:block>

                    <fo:table line-height="1.2" margin-top="15pt" >
                        <fo:table-column column-width="70%"/>
                        <fo:table-column column-width="30%"/>

                        <fo:table-body>

                            <fo:table-row>
                                <fo:table-cell>
                                    <fo:block>
                                        <fo:block>To,</fo:block>
                                        <fo:block><xsl:value-of select="addressLine1"/></fo:block>
                                        <fo:block><xsl:value-of select="addressLine2"/></fo:block>
                                        <fo:block><xsl:value-of select="addressLine3"/></fo:block>
                                        <fo:block><xsl:value-of select="addressLine4"/></fo:block>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell><fo:block></fo:block></fo:table-cell>
                            </fo:table-row>

                        </fo:table-body>
                    </fo:table>


                    <fo:block margin-top="10pt">Dear Sir,</fo:block>

                    <fo:block margin-top="10pt" line-height="1.2" text-align="justify">
                        Sub: Transfer of Provident Fund accumulation &amp; EPS A/c of Mr./Ms.
                        <fo:inline font-weight="600"><xsl:value-of select="name"/></fo:inline> (Ex-employee of
                        <fo:inline font-weight="600"><xsl:value-of select="prevCompanyName"/>
                        </fo:inline> From P.F. A/c No.
                        <fo:inline font-weight="600"><xsl:value-of select="fromPfAccount"/></fo:inline> &amp; E.P.S. A/c No.
                        <fo:inline font-weight="600"><xsl:value-of select="fromEpsAccount"/></fo:inline> To P.F. A/c No.
                        <fo:inline font-weight="600"><xsl:value-of select="toPfAccount"/></fo:inline> &amp; E.P.S. A/c No.
                        <fo:inline font-weight="600"><xsl:value-of select="toEpsAccount"/></fo:inline>
                    </fo:block>

                    <fo:block margin-top="10pt">
                        <xsl:for-each select="reminder">
                            <fo:block margin-top="5pt" font-size="10pt" line-height="1">
                                Last Request Ref: <xsl:value-of select="ref"/>
                            </fo:block>
                        </xsl:for-each>
                    </fo:block>


                    <fo:block margin-top="20pt" line-height="1.2" text-align="justify">
                        <fo:inline padding-left="50pt">We enclose the</fo:inline> Form No.13 along with our PF Trust Bank particulars duly completed by the above member who
                        is now employed with COREINTEGRA GLOBAL SERVICES PVT LIMITED for the transfer of his Provident Fund accumulations to
                        us. We would like to inform you that COREINTEGRA GLOBAL SERVICES PVT LIMITED is an exempted establishment under Employees'
                        Provident Fund Act, 1952 and having its exempted provident fund.
                    </fo:block>

                    <fo:block margin-top="10pt" line-height="1.2" text-align="justify">
                        <fo:inline padding-left="50pt">In view of kindly</fo:inline> arrange to send us your at par cheque or electronic fund transfer (Bank details are given below)
                        in respect of Provident Fund accumulations of the above member in favors of
                        "COREINTEGRA GLOBAL SERVICES PVT LIMITED", along with the Annexure "K", at the earliest.
                    </fo:block>

                    <fo:block margin-top="20pt" line-height="1.2" font-weight="600">
                        Our PF Trust's Bank A/C Details for Electronics Transfer of Amount Thru NEFT / RTGS
                    </fo:block>

                    <fo:table line-height="1" word-spacing="4" letter-spacing="0" margin-top="10pt" >
                        <fo:table-column column-width="20%"/>
                        <fo:table-column column-width="80%"/>

                        <fo:table-body>

                            <fo:table-row>
                                <fo:table-cell><fo:block font-weight="600">Trust Name </fo:block></fo:table-cell>
                                <fo:table-cell><fo:block> : COREINTEGRA GLOBAL SERVICES PVT LIMITED</fo:block></fo:table-cell>
                            </fo:table-row>

                            <fo:table-row>
                                <fo:table-cell><fo:block font-weight="600">Bank Account:</fo:block></fo:table-cell>
                                <fo:table-cell><fo:block> : 00601110000587</fo:block></fo:table-cell>
                            </fo:table-row>

                            <fo:table-row>
                                <fo:table-cell><fo:block font-weight="600">Bank Name </fo:block></fo:table-cell>
                                <fo:table-cell><fo:block> : HDFC Bank Ltd.Manekji Wadia Bldg,Nanik Motwani Marg,Fort,Mum 400001</fo:block></fo:table-cell>
                            </fo:table-row>

                            <fo:table-row>
                                <fo:table-cell><fo:block font-weight="600">IFSC Code :</fo:block></fo:table-cell>
                                <fo:table-cell><fo:block> : HDFC0000060</fo:block></fo:table-cell>
                            </fo:table-row>

                            <fo:table-row>
                                <fo:table-cell><fo:block font-weight="600">MICR Code </fo:block></fo:table-cell>
                                <fo:table-cell><fo:block> : 400240015</fo:block></fo:table-cell>
                            </fo:table-row>

                        </fo:table-body>
                    </fo:table>

                    <fo:block margin-top="20pt">Thanking you,</fo:block>
                    <fo:block margin-top="7pt">Yours faithfully,</fo:block>
                    <fo:block>For COREINTEGRA GLOBAL SERVICES PVT LIMITED</fo:block>

                    <fo:block margin-top="35pt">Authorized Signatory</fo:block>
                    <fo:block margin-top="7pt">Encl: Form - 13 along with Coreintegra Global Services PF Trust Bank Cancelled Cheque Details</fo:block>
                </fo:flow>
            </fo:page-sequence>

        </fo:root>
    </xsl:template>

</xsl:stylesheet>
