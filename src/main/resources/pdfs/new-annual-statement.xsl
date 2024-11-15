<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template match="annualStatement">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

            <fo:layout-master-set>
                <fo:simple-page-master master-name="simpleA4"
                                       page-width="29.7cm" page-height="21cm"
                                       margin-top="0.3cm" margin-bottom="0.3cm"
                                       margin-left="1cm" margin-right="1cm"
                >
                    <fo:region-body/>

                </fo:simple-page-master>

                <fo:simple-page-master master-name="less"
                                       page-width="29.7cm" page-height="21cm"
                                       margin-top="0.3cm" margin-bottom="0.3cm"
                                       margin-left="1cm" margin-right="1cm"
                >
                    <fo:region-body/>

                </fo:simple-page-master>

                <fo:simple-page-master master-name="annualAnnexure"
                                       page-width="29.7cm" page-height="21cm"
                                       margin-top="0.3cm" margin-bottom="0.3cm"
                                       margin-left="1cm" margin-right="1cm"
                >
                    <fo:region-body/>

                </fo:simple-page-master>

                <fo:simple-page-master master-name="annualAnnexureTwo"
                                       page-width="29.7cm" page-height="21cm"
                                       margin-top="0.3cm" margin-bottom="0.3cm"
                                       margin-left="1cm" margin-right="1cm"
                >
                    <fo:region-body/>

                </fo:simple-page-master>
            </fo:layout-master-set>


            <fo:page-sequence master-reference="simpleA4">
                <fo:flow flow-name="xsl-region-body" font-size="11pt" line-height="1.2"
                         font-family="Times-Roman" word-spacing="6pt" letter-spacing="1pt">

                    <fo:block>

                        <fo:table line-height="1">
                            <fo:table-column column-width="13%"/>
                            <fo:table-column column-width="87%"/>

                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0">
                                            <fo:external-graphic src="https://developerspftrust.coreintegra.com/api/v1/auth/downloadDocuments?document=mahindra_pf_trust/mahindra_pf_trust_d79517ab-5ac8-4d25-a135-a4ee9e09c01d_.png" content-height="100pt" content-width="100pt"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0" text-align="center" line-height="1.2">
                                            <fo:block font-weight="600">MAHINDRA &amp; MAHINDRA LIMITED STAFF PROVIDENT FUND</fo:block>
                                            <fo:block>ANNUAL STATEMENT AS ON <xsl:value-of select="closingDateWord"/></fo:block>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                        <!-- logo and address end -->

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" line-height="1" padding="5pt pt0">

                            <fo:inline>Employee Name : <xsl:value-of select="name"/></fo:inline>
                            <fo:inline padding-left="25pt">Token No. : <xsl:value-of select="tokenNumber"/></fo:inline>
                            <fo:inline padding-left="25pt">P.F.No. : 000<xsl:value-of select="pfNumber"/></fo:inline>
                            <fo:inline padding-left="25pt">Unit Code. : <xsl:value-of select="unitCode"/></fo:inline>
                            <fo:inline padding-left="25pt">Dept.Cd : <xsl:value-of select="deptCode"/></fo:inline>

                        </fo:block>




                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1" padding="3pt pt0">

                            <fo:table>
                                <fo:table-column column-width="25%"/>
                                <fo:table-column column-width="12.5%"/>
                                <fo:table-column column-width="12.5%"/>

                                <fo:table-column column-width="25%"/>
                                <fo:table-column column-width="12.5%"/>
                                <fo:table-column column-width="12.5%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block>Nominees</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block>Relationship</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block>Share%</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block>Nominees</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block>Relationship</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block>Share%</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>

                        </fo:block>



                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1" padding="3pt pt0">

                            <fo:table>
                                <fo:table-column column-width="25%"/>
                                <fo:table-column column-width="12.5%"/>
                                <fo:table-column column-width="12.5%"/>

                                <fo:table-column column-width="25%"/>
                                <fo:table-column column-width="12.5%"/>
                                <fo:table-column column-width="12.5%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block><xsl:value-of select="/annualStatement/nominee[1]/name"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><xsl:value-of select="/annualStatement/nominee[1]/relationship"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><xsl:value-of select="/annualStatement/nominee[1]/sharefr"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><xsl:value-of select="/annualStatement/nominee[2]/name"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><xsl:value-of select="/annualStatement/nominee[2]/relationship"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><xsl:value-of select="/annualStatement/nominee[2]/sharefr"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="3pt pt0">

                            <fo:table>
                                <fo:table-column column-width="25%"/>
                                <fo:table-column column-width="12.5%"/>
                                <fo:table-column column-width="12.5%"/>

                                <fo:table-column column-width="25%"/>
                                <fo:table-column column-width="12.5%"/>
                                <fo:table-column column-width="12.5%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block><xsl:value-of select="/annualStatement/nominee[3]/name"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><xsl:value-of select="/annualStatement/nominee[3]/relationship"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><xsl:value-of select="/annualStatement/nominee[3]/sharefr"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><xsl:value-of select="/annualStatement/nominee[4]/name"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><xsl:value-of select="/annualStatement/nominee[4]/relationship"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><xsl:value-of select="/annualStatement/nominee[4]/sharefr"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>

                        </fo:block>


                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="3pt pt0" font-size="11" font-weight="600">

                            <fo:table>
                                <fo:table-column column-width="35%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="17%"/>

                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Account Particulars (Figures in Rupees)</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>Employee's</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>Employer's</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>VPF</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-right="10pt">
                                            <fo:block>Total</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>
                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="3pt pt0" font-size="11" font-weight="600">

                            <fo:table>
                                <fo:table-column column-width="35%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Opening Balance as on <xsl:value-of select="yearOpeningDate"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="yearOpeningMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="yearOpeningCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="yearOpeningVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-right="10pt">
                                            <fo:block><xsl:value-of select="yearOpeningTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="3pt pt0" font-size="11">

                            <fo:table>
                                <fo:table-column column-width="35%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Opening Balance (Non Taxable)</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="nonTaxableYearOpeningMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="yearOpeningCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="nonTaxableYearOpeningVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-right="10pt">
                                            <fo:block><xsl:value-of select="nonTaxableYearOpeningTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="3pt pt0" font-size="11">

                            <fo:table>
                                <fo:table-column column-width="35%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Opening Balance (Taxable)</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="taxableYearOpeningMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>0.00</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="taxableYearOpeningVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-right="10pt">
                                            <fo:block><xsl:value-of select="taxableYearOpeningTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="3pt pt0" font-size="11" font-weight="600">

                            Additions:-

                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="3pt pt0" font-size="11" font-weight="600">

                            <fo:table>
                                <fo:table-column column-width="35%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Interest on Opening Balance</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="interestOnYearOpeningMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="interestOnYearOpeningCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="interestOnYearOpeningVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-right="10pt">
                                            <fo:block><xsl:value-of select="interestOnYearOpeningTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="3pt pt0">

                            <fo:table>
                                <fo:table-column column-width="35%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Interest on Opening Balance (Non Taxable)</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="nonTaxableInterestOnYearOpeningMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="interestOnYearOpeningCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="nonTaxableInterestOnYearOpeningVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-right="10pt">
                                            <fo:block><xsl:value-of select="nonTaxableInterestOnYearOpeningTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>


                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="3pt pt0">

                            <fo:table>
                                <fo:table-column column-width="35%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Interest on Opening Balance (Taxable)</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="taxableInterestOnYearOpeningMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>0.00</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="taxableInterestOnYearOpeningVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-right="10pt">
                                            <fo:block><xsl:value-of select="taxableInterestOnYearOpeningTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>


                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="3pt pt0" font-size="11" font-weight="600">

                            <fo:table>
                                <fo:table-column column-width="35%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="17%"/>


                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Contributions during the year</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="currentYearMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="currentYearCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="currentYearVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-right="10pt">
                                            <fo:block><xsl:value-of select="currentYearTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="3pt pt0">

                            <fo:table>
                                <fo:table-column column-width="35%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="17%"/>


                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Contributions during the year (Non Taxable)</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="nonTaxableCurrentYearMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="currentYearCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="nonTaxableCurrentYearVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-right="10pt">
                                            <fo:block><xsl:value-of select="nonTaxableCurrentYearTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="3pt pt0">

                            <fo:table>
                                <fo:table-column column-width="35%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="17%"/>


                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Contributions during the year (Taxable)</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="taxableCurrentYearMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>0.00</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="taxableCurrentYearVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-right="10pt">
                                            <fo:block><xsl:value-of select="taxableCurrentYearTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="3pt pt0" font-size="11" font-weight="600">

                            <fo:table>
                                <fo:table-column column-width="35%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="17%"/>

                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Interest on Contribution</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-right="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="3pt pt0">

                            <fo:table>
                                <fo:table-column column-width="35%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="17%"/>

                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Interest on Contribution (Non Taxable)</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="nonTaxableInterestOnCurrentYearMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="nonTaxableInterestOnCurrentYearVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-right="10pt">
                                            <fo:block><xsl:value-of select="nonTaxableInterestOnCurrentYearTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="3pt pt0">

                            <fo:table>
                                <fo:table-column column-width="35%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="17%"/>

                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Interest on Contribution (Taxable)</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="taxableInterestOnCurrentYearMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>0.00</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="taxableInterestOnCurrentYearVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-right="10pt">
                                            <fo:block><xsl:value-of select="taxableInterestOnCurrentYearTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="3pt pt0" font-size="11" font-weight="600">

                            <fo:table>
                                <fo:table-column column-width="35%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="17%"/>


                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Transfer from Other Funds</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="currentYearTransferInMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="currentYearTransferInCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="currentYearTransferInVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-right="10pt">
                                            <fo:block><xsl:value-of select="currentYearTransferInTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="3pt pt0">

                            <fo:table>
                                <fo:table-column column-width="35%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="17%"/>


                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Transfer from Other Funds (Non Taxable)</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="nonTaxableCurrentYearTransferInMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="currentYearTransferInCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="nonTaxableCurrentYearTransferInVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-right="10pt">
                                            <fo:block><xsl:value-of select="nonTaxableCurrentYearTransferInTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="3pt pt0">

                            <fo:table>
                                <fo:table-column column-width="35%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="17%"/>


                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Transfer from Other Funds (Taxable)</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="taxableCurrentYearTransferInMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>0.00</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="taxableCurrentYearTransferInVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-right="10pt">
                                            <fo:block><xsl:value-of select="taxableCurrentYearTransferInTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="3pt pt0" font-size="11" font-weight="600">

                            <fo:table>
                                <fo:table-column column-width="35%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="17%"/>

                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Interest on Transfer</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearTransferInMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearTransferInCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearTransferInVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-right="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearTransferInTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="3pt pt0">

                            <fo:table>
                                <fo:table-column column-width="35%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="17%"/>



                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Interest on Transfer (Non Taxable)</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="nonTaxableInterestOnCurrentYearTransferInMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearTransferInCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="nonTaxableInterestOnCurrentYearTransferInVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-right="10pt">
                                            <fo:block><xsl:value-of select="nonTaxableInterestOnCurrentYearTransferInTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>


                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="3pt pt0">

                            <fo:table>
                                <fo:table-column column-width="35%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="17%"/>



                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Interest on Transfer (Taxable)</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="taxableInterestOnCurrentYearTransferInMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>0.00</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="taxableInterestOnCurrentYearTransferInVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-right="10pt">
                                            <fo:block><xsl:value-of select="taxableInterestOnCurrentYearTransferInTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>


                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                    </fo:block>

                </fo:flow>
            </fo:page-sequence>

            <fo:page-sequence master-reference="less">
                <fo:flow flow-name="xsl-region-body" font-size="11pt" line-height="1.2"
                         font-family="Times-Roman" word-spacing="6pt" letter-spacing="1pt">

                    <fo:block>

                        <fo:table line-height="1">
                            <fo:table-column column-width="13%"/>
                            <fo:table-column column-width="87%"/>

                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0">
                                            <fo:external-graphic src="https://developerspftrust.coreintegra.com/api/v1/auth/downloadDocuments?document=mahindra_pf_trust/mahindra_pf_trust_d79517ab-5ac8-4d25-a135-a4ee9e09c01d_.png" content-height="100pt" content-width="100pt"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0" text-align="center" line-height="1.2">
                                            <fo:block font-weight="600">MAHINDRA &amp; MAHINDRA LIMITED STAFF PROVIDENT FUND</fo:block>
                                            <fo:block>ANNUAL STATEMENT AS ON <xsl:value-of select="closingDateWord"/></fo:block>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                        <!-- logo and address end -->

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" line-height="1" padding="3pt pt0">

                            <fo:inline>Employee Name : <xsl:value-of select="name"/></fo:inline>
                            <fo:inline padding-left="30pt">Token No. : <xsl:value-of select="tokenNumber"/></fo:inline>
                            <fo:inline padding-left="30pt">P.F.No. : 000<xsl:value-of select="pfNumber"/></fo:inline>
                            <fo:inline padding-left="30pt">Unit Code. : <xsl:value-of select="unitCode"/></fo:inline>
                            <fo:inline padding-left="30pt">Dept.Cd : <xsl:value-of select="deptCode"/></fo:inline>

                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="3pt pt0" font-size="11" font-weight="600">

                            <fo:table>
                                <fo:table-column column-width="35%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="17%"/>

                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Account Particulars (Figures in Rupees)</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>Employee's</fo:block>
                                            <fo:block>Contribution's A/C</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>Employer's</fo:block>
                                            <fo:block>Contribution's A/C</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>VPF</fo:block>
                                            <fo:block>Contribution's A/C</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-right="10pt">
                                            <fo:block>Total</fo:block>
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>
                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="3pt pt0" font-size="11" font-weight="600">

                            Less:-

                        </fo:block>



                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="3pt pt0" font-size="11" font-weight="600">

                            <fo:table>
                                <fo:table-column column-width="35%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="17%"/>


                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>NRL Withdrawals during the year</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="currentYearLoanWithdrawalMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="currentYearLoanWithdrawalCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="currentYearLoanWithdrawalVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-right="10pt">
                                            <fo:block><xsl:value-of select="currentYearLoanWithdrawalTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="3pt pt0">

                            <fo:table>
                                <fo:table-column column-width="35%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="17%"/>


                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>NRL Withdrawals (Non Taxable)</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="nonTaxableCurrentYearLoanWithdrawalMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="currentYearLoanWithdrawalCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="nonTaxableCurrentYearLoanWithdrawalVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-right="10pt">
                                            <fo:block><xsl:value-of select="nonTaxableCurrentYearLoanWithdrawalTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="3pt pt0">

                            <fo:table>
                                <fo:table-column column-width="35%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="17%"/>


                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>NRL Withdrawals (Taxable)</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="taxableCurrentYearLoanWithdrawalMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>0.00</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="taxableCurrentYearLoanWithdrawalVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-right="10pt">
                                            <fo:block><xsl:value-of select="taxableCurrentYearLoanWithdrawalTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>


                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="3pt pt0" font-size="11" font-weight="600">

                            <fo:table>
                                <fo:table-column column-width="35%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="17%"/>




                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Interest on NRL</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearLoanWithdrawalMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearLoanWithdrawalCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearLoanWithdrawalVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-right="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearLoanWithdrawalTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="3pt pt0">

                            <fo:table>
                                <fo:table-column column-width="35%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="17%"/>




                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Interest on NRL (Non Taxable)</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="nonTaxableInterestOnCurrentYearLoanWithdrawalMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearLoanWithdrawalCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="nonTaxableInterestOnCurrentYearLoanWithdrawalVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-right="10pt">
                                            <fo:block><xsl:value-of select="nonTaxableInterestOnCurrentYearLoanWithdrawalTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="3pt pt0">

                            <fo:table>
                                <fo:table-column column-width="35%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="17%"/>




                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Interest on NRL (Taxable)</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="taxableInterestOnCurrentYearLoanWithdrawalMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>0.00</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="taxableInterestOnCurrentYearLoanWithdrawalVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-right="10pt">
                                            <fo:block><xsl:value-of select="taxableInterestOnCurrentYearLoanWithdrawalTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>


                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="3pt pt0" font-size="11" font-weight="600">

                            <fo:table>
                                <fo:table-column column-width="35%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="17%"/>
                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>TDS on Interest</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="tdsOnInterestOnTaxableMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>0.00</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="tdsOnInterestOnTaxableVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-right="10pt">
                                            <fo:block><xsl:value-of select="tdsOnInterestTotal"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>


                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="10pt pt0">

                            <fo:table>
                                <fo:table-column column-width="35%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="16%"/>
                                <fo:table-column column-width="17%"/>



                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block  font-size="11" font-weight="600">Closing Balance as on <xsl:value-of select="closingDate"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block  font-size="11" font-weight="600"><xsl:value-of select="totalMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block  font-size="11" font-weight="600"><xsl:value-of select="totalCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block  font-size="11" font-weight="600"><xsl:value-of select="totalVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-right="10pt">
                                            <fo:block  font-size="11" font-weight="600"><xsl:value-of select="totalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block margin-top="2pt">Non Taxable Balance as on <xsl:value-of select="closingDate"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block margin-top="2pt"><xsl:value-of select="nonTaxableTotalMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block margin-top="2pt"><xsl:value-of select="nonTaxableTotalCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block margin-top="2pt"><xsl:value-of select="nonTaxableTotalVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-right="10pt">
                                            <fo:block margin-top="2pt"><xsl:value-of select="nonTaxableTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block margin-top="2pt">Taxable Balance as on <xsl:value-of select="closingDate"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block margin-top="2pt"><xsl:value-of select="taxableTotalMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block margin-top="2pt"><xsl:value-of select="taxableTotalCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block margin-top="2pt"><xsl:value-of select="taxableTotalVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-right="10pt">
                                            <fo:block margin-top="2pt"><xsl:value-of select="taxableTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>


                                </fo:table-body>
                            </fo:table>

                        </fo:block>


                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" line-height="1"
                                  padding="5pt" padding-left="0pt">

                            <fo:table>
                                <fo:table-column column-width="70%"/>
                                <fo:table-column column-width="30%"/>

                                <fo:table-body>

                                    <fo:table-row>

                                        <fo:table-cell>

                                            <fo:block>1.Interest @<fo:inline><xsl:value-of select="interestRate"/></fo:inline>% p.a. is calculated on monthly running
                                                balance as per PF Act &amp; Rules.</fo:block>

                                            <fo:block>2.EPS-1995 Contributions has been paid to the RPFC directly
                                                by the company.</fo:block>

                                            <fo:block>3.Error, if any should be reported within 30 days.</fo:block>

                                            <fo:block>4.Please refer <fo:inline font-weight="600">Annexure</fo:inline> (given below / attached) for during the
                                                Year's Transactions.</fo:block>

                                        </fo:table-cell>
                                        <fo:table-cell>

                                            <fo:block margin="0" padding="0">
                                                <fo:block text-align="center" font-weight="600">For Mahindra &amp; Mahindra Limited</fo:block>
                                                <fo:block text-align="center" font-weight="600">Staff Provident Fund</fo:block>
                                                <fo:block margin-top="5pt" text-align="center">
                                                    <fo:external-graphic src="https://cleansecar-prod.s3.us-east-2.amazonaws.com/OndemandFiles/f2842707-9609-406f-8fab-d3f1ebf3d183.png"
                                                                         content-height="70pt"
                                                                         content-width="200pt"/>
                                                </fo:block>
                                                <fo:block text-align="center" font-weight="600">Trustee</fo:block>
                                            </fo:block>


                                        </fo:table-cell>

                                    </fo:table-row>


                                </fo:table-body>
                            </fo:table>



                            <fo:block>
                                <fo:inline font-weight="600">Date:- <xsl:value-of select="date"/></fo:inline>
                            </fo:block>


                        </fo:block>

                    </fo:block>

                </fo:flow>
            </fo:page-sequence>

            <fo:page-sequence master-reference="annualAnnexure">
                <fo:flow flow-name="xsl-region-body" font-size="11pt" line-height="1.2"
                         font-family="Times-Roman" word-spacing="6pt" letter-spacing="1pt">

                    <fo:block>

                        <fo:table line-height="1">
                            <fo:table-column column-width="13%"/>
                            <fo:table-column column-width="87%"/>

                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0">
                                            <fo:external-graphic src="https://developerspftrust.coreintegra.com/api/v1/auth/downloadDocuments?document=mahindra_pf_trust/mahindra_pf_trust_d79517ab-5ac8-4d25-a135-a4ee9e09c01d_.png" content-height="100pt" content-width="100pt"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0" text-align="center" line-height="1.2">
                                            <fo:block font-weight="600">MAHINDRA &amp; MAHINDRA LIMITED STAFF PROVIDENT FUND</fo:block>
                                            <fo:block>ANNEXURE TO ANNUAL STATEMENT AS ON <xsl:value-of select="closingDateWord"/></fo:block>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                        <!-- logo and address end -->
                        <fo:inline padding-left="650pt">(Figures in Rupees)</fo:inline>
                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" line-height="1" padding="3pt pt0">

                            <fo:inline>Employee Name : <xsl:value-of select="name"/></fo:inline>
                            <fo:inline padding-left="30pt">Token No. : <xsl:value-of select="tokenNumber"/></fo:inline>
                            <fo:inline padding-left="30pt">P.F.No. : 000<xsl:value-of select="pfNumber"/></fo:inline>
                            <fo:inline padding-left="30pt">Unit Code. : <xsl:value-of select="unitCode"/></fo:inline>
                            <fo:inline padding-left="30pt">Dept.Cd : <xsl:value-of select="deptCode"/></fo:inline>

                        </fo:block>



                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" text-align="center"
                                  line-height="1">

                            <fo:table line-height="1">
                                <fo:table-column column-width="12%" border-right="1pt solid black"/>
                                <fo:table-column column-width="22%" border-right="1pt solid black"/>
                                <fo:table-column column-width="22%" border-right="1pt solid black"/>
                                <fo:table-column column-width="22%" border-right="1pt solid black"/>
                                <fo:table-column column-width="22%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Month</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Employee's</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Employer's</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>VPF</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Total</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>

                        </fo:block>



                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" text-align="center"
                                  line-height="1">

                            <fo:table line-height="1">

                                <fo:table-column column-width="12%" border-right="1pt solid black"/>
                                <fo:table-column column-width="22%" border-right="1pt solid black"/>
                                <fo:table-column column-width="22%" border-right="1pt solid black"/>
                                <fo:table-column column-width="22%" border-right="1pt solid black"/>
                                <fo:table-column column-width="22%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>

                                            <fo:table line-height="1">
                                                <fo:table-column column-width="50%"
                                                                 border-right="1pt solid black"/>
                                                <fo:table-column column-width="50%" />

                                                <fo:table-body>
                                                    <fo:table-row>
                                                        <fo:table-cell><fo:block>Contributions</fo:block></fo:table-cell>
                                                        <fo:table-cell><fo:block>Interest</fo:block></fo:table-cell>
                                                    </fo:table-row>
                                                </fo:table-body>
                                            </fo:table>

                                        </fo:table-cell>
                                        <fo:table-cell>

                                            <fo:table line-height="1">
                                                <fo:table-column column-width="50%"
                                                                 border-right="1pt solid black"/>
                                                <fo:table-column column-width="50%" />

                                                <fo:table-body>
                                                    <fo:table-row>
                                                        <fo:table-cell><fo:block>Contributions</fo:block></fo:table-cell>
                                                        <fo:table-cell><fo:block>Interest</fo:block></fo:table-cell>
                                                    </fo:table-row>
                                                </fo:table-body>
                                            </fo:table>

                                        </fo:table-cell>
                                        <fo:table-cell>

                                            <fo:table line-height="1">
                                                <fo:table-column column-width="50%"
                                                                 border-right="1pt solid black"/>
                                                <fo:table-column column-width="50%" />

                                                <fo:table-body>
                                                    <fo:table-row>
                                                        <fo:table-cell><fo:block>Contributions</fo:block></fo:table-cell>
                                                        <fo:table-cell><fo:block>Interest</fo:block></fo:table-cell>
                                                    </fo:table-row>
                                                </fo:table-body>
                                            </fo:table>

                                        </fo:table-cell>
                                        <fo:table-cell>

                                            <fo:table line-height="1">
                                                <fo:table-column column-width="50%"
                                                                 border-right="1pt solid black"/>
                                                <fo:table-column column-width="50%" />

                                                <fo:table-body>
                                                    <fo:table-row>
                                                        <fo:table-cell><fo:block>Contributions</fo:block></fo:table-cell>
                                                        <fo:table-cell><fo:block>Interest</fo:block></fo:table-cell>
                                                    </fo:table-row>
                                                </fo:table-body>
                                            </fo:table>

                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>

                            </fo:table>

                        </fo:block>


                        <xsl:for-each select="currentYearContributions">

                            <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                      border="1pt solid black" border-top="0" text-align="center"
                                      line-height="1">

                                <fo:table line-height="1">

                                    <fo:table-column column-width="12%" border-right="1pt solid black"/>
                                    <fo:table-column column-width="22%" border-right="1pt solid black"/>
                                    <fo:table-column column-width="22%" border-right="1pt solid black"/>
                                    <fo:table-column column-width="22%" border-right="1pt solid black"/>
                                    <fo:table-column column-width="22%"/>

                                    <fo:table-body>
                                        <fo:table-row>
                                            <fo:table-cell>
                                                <fo:block padding="3pt 0pt"><xsl:value-of select="monthName"/></fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell>

                                                <fo:table line-height="1">
                                                    <fo:table-column column-width="50%"
                                                                     border-right="1pt solid black"/>
                                                    <fo:table-column column-width="50%" />

                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="memberContributionfr"/></fo:block></fo:table-cell>
                                                            <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="interestOnMemberContributionfr"/></fo:block></fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>

                                            </fo:table-cell>
                                            <fo:table-cell>

                                                <fo:table line-height="1">
                                                    <fo:table-column column-width="50%"
                                                                     border-right="1pt solid black"/>
                                                    <fo:table-column column-width="50%" />

                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="companyContributionfr"/></fo:block></fo:table-cell>
                                                            <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="interestOnCompanyContributionfr"/></fo:block></fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>

                                            </fo:table-cell>
                                            <fo:table-cell>

                                                <fo:table line-height="1">
                                                    <fo:table-column column-width="50%"
                                                                     border-right="1pt solid black"/>
                                                    <fo:table-column column-width="50%" />

                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="vpfContributionfr"/></fo:block></fo:table-cell>
                                                            <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="interestOnVpfContributionfr"/></fo:block></fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>

                                            </fo:table-cell>
                                            <fo:table-cell>

                                                <fo:table line-height="1">
                                                    <fo:table-column column-width="50%"
                                                                     border-right="1pt solid black"/>
                                                    <fo:table-column column-width="50%" />

                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="totalContributionfr"/></fo:block></fo:table-cell>
                                                            <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="totalInterestfr"/></fo:block></fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>

                                            </fo:table-cell>
                                        </fo:table-row>
                                    </fo:table-body>

                                </fo:table>

                            </fo:block>

                        </xsl:for-each>



                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" text-align="center"
                                  line-height="1" font-weight="600">

                            <fo:table line-height="1">

                                <fo:table-column column-width="12%" border-right="1pt solid black"/>
                                <fo:table-column column-width="22%" border-right="1pt solid black"/>
                                <fo:table-column column-width="22%" border-right="1pt solid black"/>
                                <fo:table-column column-width="22%" border-right="1pt solid black"/>
                                <fo:table-column column-width="22%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block padding="3pt 0pt">Total</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>

                                            <fo:table line-height="1">
                                                <fo:table-column column-width="50%"
                                                                 border-right="1pt solid black"/>
                                                <fo:table-column column-width="50%" />

                                                <fo:table-body>
                                                    <fo:table-row>
                                                        <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="currentYearContributionsTotal/memberContributionfr"/></fo:block></fo:table-cell>
                                                        <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="currentYearContributionsTotal/interestOnMemberContributionfr"/></fo:block></fo:table-cell>
                                                    </fo:table-row>
                                                </fo:table-body>
                                            </fo:table>

                                        </fo:table-cell>
                                        <fo:table-cell>

                                            <fo:table line-height="1">
                                                <fo:table-column column-width="50%"
                                                                 border-right="1pt solid black"/>
                                                <fo:table-column column-width="50%" />

                                                <fo:table-body>
                                                    <fo:table-row>
                                                        <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="currentYearContributionsTotal/companyContributionfr"/></fo:block></fo:table-cell>
                                                        <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="currentYearContributionsTotal/interestOnCompanyContributionfr"/></fo:block></fo:table-cell>
                                                    </fo:table-row>
                                                </fo:table-body>
                                            </fo:table>

                                        </fo:table-cell>
                                        <fo:table-cell>

                                            <fo:table line-height="1">
                                                <fo:table-column column-width="50%"
                                                                 border-right="1pt solid black"/>
                                                <fo:table-column column-width="50%" />

                                                <fo:table-body>
                                                    <fo:table-row>
                                                        <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="currentYearContributionsTotal/vpfContributionfr"/></fo:block></fo:table-cell>
                                                        <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="currentYearContributionsTotal/interestOnVpfContributionfr"/></fo:block></fo:table-cell>
                                                    </fo:table-row>
                                                </fo:table-body>
                                            </fo:table>

                                        </fo:table-cell>
                                        <fo:table-cell>

                                            <fo:table line-height="1">
                                                <fo:table-column column-width="50%"
                                                                 border-right="1pt solid black"/>
                                                <fo:table-column column-width="50%" />

                                                <fo:table-body>
                                                    <fo:table-row>
                                                        <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="currentYearContributionsTotal/totalContributionfr"/></fo:block></fo:table-cell>
                                                        <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="currentYearContributionsTotal/totalInterestfr"/></fo:block></fo:table-cell>
                                                    </fo:table-row>
                                                </fo:table-body>
                                            </fo:table>

                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>

                            </fo:table>

                        </fo:block>



                        <fo:block margin="10pt" font-size="12"
                                  font-weight="600" text-align="center">
                            During the Year <xsl:value-of select="finantialPeriod"/> Transfer In and Loan Withdrawals
                        </fo:block>

                        <fo:block font-size="12" font-weight="600">Transfer In</fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" text-align="center"
                                  line-height="1" font-weight="600">

                            <fo:table line-height="1">
                                <fo:table-column column-width="25%" border-right="1pt solid black"/>
                                <fo:table-column column-width="25%" border-right="1pt solid black"/>
                                <fo:table-column column-width="25%" border-right="1pt solid black"/>
                                <fo:table-column column-width="25%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Date of Transfer</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Employee's A/C</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Employer's A/C</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>TOTAL</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" text-align="center"
                                  line-height="1">

                            <fo:table line-height="1">
                                <fo:table-column column-width="25%" border-right="1pt solid black"/>
                                <fo:table-column column-width="25%" border-right="1pt solid black"/>
                                <fo:table-column column-width="25%" border-right="1pt solid black"/>
                                <fo:table-column column-width="25%"/>

                                <fo:table-body>


                                    <xsl:for-each select="transferInDetails">

                                        <fo:table-row>
                                            <fo:table-cell padding="2pt 0pt">
                                                <fo:block><xsl:value-of select="receiptAtDate"/></fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell padding="2pt 0pt">
                                                <fo:block><xsl:value-of select="employeeContribution"/></fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell padding="2pt 0pt">
                                                <fo:block><xsl:value-of select="employerContribution"/></fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell padding="2pt 0pt">
                                                <fo:block><xsl:value-of select="total"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>

                                    </xsl:for-each>


                                    <fo:table-row>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block text-align="left"></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>


                        <fo:block font-size="12" margin-top="20pt"
                                  font-weight="600">Loan Withdrawals</fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" text-align="center"
                                  line-height="1" font-weight="600">

                            <fo:table line-height="1">
                                <fo:table-column column-width="10%" border-right="1pt solid black"/>
                                <fo:table-column column-width="20%" border-right="1pt solid black"/>
                                <fo:table-column column-width="10%" border-right="1pt solid black"/>
                                <fo:table-column column-width="15%" border-right="1pt solid black"/>
                                <fo:table-column column-width="15%" border-right="1pt solid black"/>
                                <fo:table-column column-width="15%" border-right="1pt solid black"/>
                                <fo:table-column column-width="15%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Loan Type</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Description</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Date</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Employee's A/C</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Employer's A/C</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>VPF A/C</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>TOTAL</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>

                        </fo:block>


                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" text-align="center"
                                  line-height="1">

                            <fo:table line-height="1">
                                <fo:table-column column-width="10%" border-right="1pt solid black"/>
                                <fo:table-column column-width="20%" border-right="1pt solid black"/>
                                <fo:table-column column-width="10%" border-right="1pt solid black"/>
                                <fo:table-column column-width="15%" border-right="1pt solid black"/>
                                <fo:table-column column-width="15%" border-right="1pt solid black"/>
                                <fo:table-column column-width="15%" border-right="1pt solid black"/>
                                <fo:table-column column-width="15%"/>

                                <fo:table-body>


                                    <xsl:for-each select="loanDetails">
                                        <fo:table-row>
                                            <fo:table-cell padding="2pt 0pt">
                                                <fo:block><xsl:value-of select="code"/></fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell padding="2pt 0pt">
                                                <fo:block text-align="left"><xsl:value-of select="type"/></fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell padding="2pt 0pt">
                                                <fo:block><xsl:value-of select="loanApprovalDate"/></fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell padding="2pt 0pt">
                                                <fo:block><xsl:value-of select="employeeContribution"/></fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell padding="2pt 0pt">
                                                <fo:block><xsl:value-of select="employerContribution"/></fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell padding="2pt 0pt">
                                                <fo:block><xsl:value-of select="vpfContribution"/></fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell padding="2pt 0pt">
                                                <fo:block><xsl:value-of select="totalContribution"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                    </xsl:for-each>


                                    <fo:table-row>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block text-align="left"></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>


                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                    </fo:block>

                </fo:flow>
            </fo:page-sequence>

            <fo:page-sequence master-reference="annualAnnexureTwo">
                <fo:flow flow-name="xsl-region-body" font-size="11pt" line-height="1.2"
                         font-family="Times-Roman" word-spacing="6pt" letter-spacing="1pt">

                    <fo:block>

                        <fo:table line-height="1">
                            <fo:table-column column-width="13%"/>
                            <fo:table-column column-width="87%"/>

                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0">
                                            <fo:external-graphic src="https://developerspftrust.coreintegra.com/api/v1/auth/downloadDocuments?document=mahindra_pf_trust/mahindra_pf_trust_d79517ab-5ac8-4d25-a135-a4ee9e09c01d_.png" content-height="100pt" content-width="100pt"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0" text-align="center" line-height="1.2">
                                            <fo:block font-weight="600">MAHINDRA &amp; MAHINDRA LIMITED STAFF PROVIDENT FUND</fo:block>
                                            <fo:block>ANNEXURE TO ANNUAL STATEMENT AS ON <xsl:value-of select="closingDateWord"/></fo:block>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                        <!-- logo and address end -->
                        <fo:inline padding-left="650pt">(Figures in Rupees)</fo:inline>
                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" line-height="1" padding="3pt pt0">

                            <fo:inline>Employee Name : <xsl:value-of select="name"/></fo:inline>
                            <fo:inline padding-left="30pt">Token No. : <xsl:value-of select="tokenNumber"/></fo:inline>
                            <fo:inline padding-left="30pt">P.F.No. : 000<xsl:value-of select="pfNumber"/></fo:inline>
                            <fo:inline padding-left="30pt">Unit Code. : <xsl:value-of select="unitCode"/></fo:inline>
                            <fo:inline padding-left="30pt">Dept.Cd : <xsl:value-of select="deptCode"/></fo:inline>

                        </fo:block>


                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" text-align="center"
                                  line-height="1">

                            <fo:table line-height="1">
                                <fo:table-column column-width="12%" border-right="1pt solid black"/>
                                <fo:table-column column-width="36%" border-right="1pt solid black"/>
                                <fo:table-column column-width="16%" border-right="1pt solid black"/>
                                <fo:table-column column-width="36%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Employee + VPF</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Employer</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Total</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" text-align="center"
                                  line-height="1">

                            <fo:table line-height="1">

                                <fo:table-column column-width="12%" border-right="1pt solid black"/>
                                <fo:table-column column-width="36%" border-right="1pt solid black"/>
                                <fo:table-column column-width="16%" border-right="1pt solid black"/>
                                <fo:table-column column-width="36%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="5pt 0pt 0pt 0pt">
                                            <fo:block>Month</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:table line-height="1">
                                                <fo:table-column column-width="25%" border-right="1pt solid black"/>
                                                <fo:table-column column-width="25%" border-right="1pt solid black"/>
                                                <fo:table-column column-width="25%" border-right="1pt solid black"/>
                                                <fo:table-column column-width="25%" />
                                                <fo:table-body>
                                                    <fo:table-row>
                                                        <fo:table-cell padding="5pt 0pt 0pt 0pt"><fo:block>Contri Non Taxable</fo:block></fo:table-cell>
                                                        <fo:table-cell padding="5pt 0pt 0pt 0pt"><fo:block>Contri Taxable</fo:block></fo:table-cell>
                                                        <fo:table-cell padding="5pt 0pt 0pt 0pt"><fo:block>Interest Non Taxable</fo:block></fo:table-cell>
                                                        <fo:table-cell padding="5pt 0pt 0pt 0pt"><fo:block>Interest Taxable</fo:block></fo:table-cell>
                                                    </fo:table-row>
                                                </fo:table-body>
                                            </fo:table>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:table line-height="1">
                                                <fo:table-column column-width="50%" border-right="1pt solid black"/>
                                                <fo:table-column column-width="50%" />
                                                <fo:table-body>
                                                    <fo:table-row>
                                                        <fo:table-cell padding="5pt 0pt 0pt 0pt"><fo:block>Contri Non Taxable</fo:block></fo:table-cell>
                                                        <fo:table-cell padding="5pt 0pt 0pt 0pt"><fo:block>Interest Non Taxable</fo:block></fo:table-cell>
                                                    </fo:table-row>
                                                </fo:table-body>
                                            </fo:table>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:table line-height="1">
                                                <fo:table-column column-width="25%" border-right="1pt solid black"/>
                                                <fo:table-column column-width="25%" border-right="1pt solid black"/>
                                                <fo:table-column column-width="25%" border-right="1pt solid black"/>
                                                <fo:table-column column-width="25%" />
                                                <fo:table-body>
                                                    <fo:table-row>
                                                        <fo:table-cell padding="5pt 0pt 0pt 0pt"><fo:block>Contri Non Taxable</fo:block></fo:table-cell>
                                                        <fo:table-cell padding="5pt 0pt 0pt 0pt"><fo:block>Contri Taxable</fo:block></fo:table-cell>
                                                        <fo:table-cell padding="5pt 0pt 0pt 0pt"><fo:block>Interest Non Taxable</fo:block></fo:table-cell>
                                                        <fo:table-cell padding="5pt 0pt 0pt 0pt"><fo:block>Interest Taxable</fo:block></fo:table-cell>
                                                    </fo:table-row>
                                                </fo:table-body>
                                            </fo:table>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>

                            </fo:table>

                        </fo:block>

                        <xsl:for-each select="currentYearContributionsNew">

                            <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                      border="1pt solid black" border-top="0" text-align="center"
                                      line-height="1">

                                <fo:table line-height="1">

                                    <fo:table-column column-width="12%" border-right="1pt solid black"/>
                                    <fo:table-column column-width="36%" border-right="1pt solid black"/>
                                    <fo:table-column column-width="16%" border-right="1pt solid black"/>
                                    <fo:table-column column-width="36%"/>

                                    <fo:table-body>
                                        <fo:table-row>
                                            <fo:table-cell padding="5pt 0pt 0pt 0pt">
                                                <fo:block><xsl:value-of select="monthName"/></fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell>
                                                <fo:table line-height="1">
                                                    <fo:table-column column-width="25%" border-right="1pt solid black"/>
                                                    <fo:table-column column-width="25%" border-right="1pt solid black"/>
                                                    <fo:table-column column-width="25%" border-right="1pt solid black"/>
                                                    <fo:table-column column-width="25%" />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="nonTaxableMemberVpfContribution"/></fo:block></fo:table-cell>
                                                            <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="taxableMemberVpfContribution"/></fo:block></fo:table-cell>
                                                            <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="interestOnNonTaxableMemberVpfContribution"/></fo:block></fo:table-cell>
                                                            <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="interestOnTaxableMemberVpfContribution"/></fo:block></fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:table-cell>
                                            <fo:table-cell>
                                                <fo:table line-height="1">
                                                    <fo:table-column column-width="50%" border-right="1pt solid black"/>
                                                    <fo:table-column column-width="50%" />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="companyContribution"/></fo:block></fo:table-cell>
                                                            <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="interestOnCompanyContribution"/></fo:block></fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:table-cell>
                                            <fo:table-cell>
                                                <fo:table line-height="1">
                                                    <fo:table-column column-width="25%" border-right="1pt solid black"/>
                                                    <fo:table-column column-width="25%" border-right="1pt solid black"/>
                                                    <fo:table-column column-width="25%" border-right="1pt solid black"/>
                                                    <fo:table-column column-width="25%" />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="totalContributionNonTaxable"/></fo:block></fo:table-cell>
                                                            <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="totalContributionTaxable"/></fo:block></fo:table-cell>
                                                            <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="totalInterestOnContributionNonTaxable"/></fo:block></fo:table-cell>
                                                            <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="totalInterestOnContributionTaxable"/></fo:block></fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:table-cell>
                                        </fo:table-row>
                                    </fo:table-body>

                                </fo:table>

                            </fo:block>

                        </xsl:for-each>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" text-align="center"
                                  line-height="1">

                            <fo:table line-height="1">

                                <fo:table-column column-width="12%" border-right="1pt solid black"/>
                                <fo:table-column column-width="36%" border-right="1pt solid black"/>
                                <fo:table-column column-width="16%" border-right="1pt solid black"/>
                                <fo:table-column column-width="36%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="5pt 0pt 0pt 0pt">
                                            <fo:block>Total</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:table line-height="1">
                                                <fo:table-column column-width="25%" border-right="1pt solid black"/>
                                                <fo:table-column column-width="25%" border-right="1pt solid black"/>
                                                <fo:table-column column-width="25%" border-right="1pt solid black"/>
                                                <fo:table-column column-width="25%" />
                                                <fo:table-body>
                                                    <fo:table-row>
                                                        <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="currentYearContributionsNewTotal/nonTaxableMemberVpfContribution"/></fo:block></fo:table-cell>
                                                        <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="currentYearContributionsNewTotal/taxableMemberVpfContribution"/></fo:block></fo:table-cell>
                                                        <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="currentYearContributionsNewTotal/interestOnNonTaxableMemberVpfContribution"/></fo:block></fo:table-cell>
                                                        <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="currentYearContributionsNewTotal/interestOnTaxableMemberVpfContribution"/></fo:block></fo:table-cell>
                                                    </fo:table-row>
                                                </fo:table-body>
                                            </fo:table>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:table line-height="1">
                                                <fo:table-column column-width="50%" border-right="1pt solid black"/>
                                                <fo:table-column column-width="50%" />
                                                <fo:table-body>
                                                    <fo:table-row>
                                                        <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="currentYearContributionsNewTotal/companyContribution"/></fo:block></fo:table-cell>
                                                        <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="currentYearContributionsNewTotal/interestOnCompanyContribution"/></fo:block></fo:table-cell>
                                                    </fo:table-row>
                                                </fo:table-body>
                                            </fo:table>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:table line-height="1">
                                                <fo:table-column column-width="25%" border-right="1pt solid black"/>
                                                <fo:table-column column-width="25%" border-right="1pt solid black"/>
                                                <fo:table-column column-width="25%" border-right="1pt solid black"/>
                                                <fo:table-column column-width="25%" />
                                                <fo:table-body>
                                                    <fo:table-row>
                                                        <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="currentYearContributionsNewTotal/totalContributionNonTaxable"/></fo:block></fo:table-cell>
                                                        <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="currentYearContributionsNewTotal/totalContributionTaxable"/></fo:block></fo:table-cell>
                                                        <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="currentYearContributionsNewTotal/totalInterestOnContributionNonTaxable"/></fo:block></fo:table-cell>
                                                        <fo:table-cell><fo:block padding="3pt 0pt" text-align="right"><xsl:value-of select="currentYearContributionsNewTotal/totalInterestOnContributionTaxable"/></fo:block></fo:table-cell>
                                                    </fo:table-row>
                                                </fo:table-body>
                                            </fo:table>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>

                            </fo:table>

                        </fo:block>

                    </fo:block>

                </fo:flow>
            </fo:page-sequence>

        </fo:root>
    </xsl:template>

</xsl:stylesheet>