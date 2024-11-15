<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template match="settlementAnnexure">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

            <fo:layout-master-set>
                <fo:simple-page-master master-name="simpleA4"
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
                                            <fo:external-graphic src="http://3.110.35.227:8080/images/mahindra_logo.png" content-height="100pt" content-width="100pt"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0" text-align="center" line-height="1.2">
                                            <fo:block font-weight="600">COREINTEGRA GLOBAL SERVICES PVT LIMITED  .  STAFF  PROVIDENT  FUND</fo:block>
                                            <fo:block>ANNEXURE TO SETTLEMENT STATEMENT AS ON <xsl:value-of select="date"/></fo:block>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                        <!-- logo and address end -->

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" line-height="1" padding="5pt pt0">

                                <fo:inline>Employee Name : <xsl:value-of select="name"/></fo:inline>
                                <fo:inline padding-left="30pt">Token No. :   <xsl:value-of select="tokenNumber"/></fo:inline>
                                <fo:inline padding-left="30pt">P.F.No. :  <xsl:value-of select="pfNumber"/></fo:inline>
                                <fo:inline padding-left="30pt">Unit Code. :  <xsl:value-of select="unitCode"/></fo:inline>
                                <fo:inline padding-left="30pt">Dept.Cd :  <xsl:value-of select="unitCode"/></fo:inline>

                        </fo:block>

                        <fo:block margin-top="10pt" font-weight="600">Month Wise contribution and Interest (Figures in Rupees)</fo:block>

                        <fo:block margin="20pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" text-align="center"
                                  line-height="1">

                            <fo:table line-height="1">
                                <fo:table-column column-width="11%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="11%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Apr</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>May</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Jun</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Jul</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Aug</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Sep</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Oct</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Nov</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Dec</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Jan</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Feb</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Mar</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Total</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" font-size="9" text-align="right"
                                  line-height="1">

                            <fo:table line-height="1">

                                <fo:table-column column-width="11%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="11%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block text-align="left"
                                            font-size="10" font-weight="600">MEM. CONT A/C</fo:block>
                                        </fo:table-cell>

                                        <xsl:for-each select="memberContributions">
                                            <fo:table-cell padding="14pt 0pt">
                                                <fo:block> <xsl:value-of select="amount"/></fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>

                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block> <xsl:value-of select="totalMemberContributions"/></fo:block>
                                        </fo:table-cell>

                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>



                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" font-size="9" text-align="right"
                                  line-height="1">

                            <fo:table line-height="1">

                                <fo:table-column column-width="11%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="11%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block text-align="left" font-size="10"
                                                      font-weight="600">COMP. CONT A/C</fo:block>
                                        </fo:table-cell>

                                        <xsl:for-each select="CompanyContributions">
                                            <fo:table-cell padding="14pt 0pt">
                                                <fo:block> <xsl:value-of select="amount"/></fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>

                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block> <xsl:value-of select="totalCompanyContributions"/></fo:block>
                                        </fo:table-cell>

                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>


                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" font-size="9" text-align="right"
                                  line-height="1">

                            <fo:table line-height="1">

                                <fo:table-column column-width="11%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="11%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block text-align="left" font-size="10" font-weight="600">V.P.F. CONT A/C</fo:block>
                                        </fo:table-cell>

                                        <xsl:for-each select="vpfContributions">
                                            <fo:table-cell padding="14pt 0pt">
                                                <fo:block> <xsl:value-of select="amount"/></fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>

                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block> <xsl:value-of select="totalVpfContributions"/></fo:block>
                                        </fo:table-cell>

                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>




                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" font-size="9" text-align="right"
                                  line-height="1">

                            <fo:table line-height="1">

                                <fo:table-column column-width="11%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="11%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block text-align="left" font-size="10" font-weight="600">Total Rs.</fo:block>
                                        </fo:table-cell>

                                        <xsl:for-each select="totalContributionsMonthWise">
                                            <fo:table-cell padding="14pt 0pt">
                                                <fo:block> <xsl:value-of select="amount"/></fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>

                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block><xsl:value-of select="totalContribution"/></fo:block>
                                        </fo:table-cell>


                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>



                        <fo:block margin-top="10pt" font-weight="600"><xsl:value-of select="interestRate"/> Month wise Interest (Figures in Rupees)
                            [on monthly running balance met]</fo:block>

                        <fo:block margin="20pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" text-align="center"
                                  line-height="1">

                            <fo:table line-height="1">
                                <fo:table-column column-width="11%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="11%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Apr</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>May</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Jun</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Jul</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Aug</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Sep</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Oct</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Nov</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Dec</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Jan</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Feb</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Mar</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding="2pt 0pt">
                                            <fo:block>Total</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" font-size="9" text-align="right"
                                  line-height="1">

                            <fo:table line-height="1">

                                <fo:table-column column-width="11%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="11%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block text-align="left"
                                                      font-size="10" font-weight="600">MEM. CONT A/C</fo:block>
                                        </fo:table-cell>

                                        <xsl:for-each select="interestOnMemberContributions">
                                            <fo:table-cell padding="14pt 0pt">
                                                <fo:block> <xsl:value-of select="amount"/></fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>

                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block><xsl:value-of select="totalInterestOnMemberContribution"/></fo:block>
                                        </fo:table-cell>



                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>



                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" font-size="9" text-align="right"
                                  line-height="1">

                            <fo:table line-height="1">

                                <fo:table-column column-width="11%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="11%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block text-align="left" font-size="10"
                                                      font-weight="600">COMP. CONT A/C</fo:block>
                                        </fo:table-cell>

                                        <xsl:for-each select="interestOnCompanyContributions">
                                            <fo:table-cell padding="14pt 0pt">
                                                <fo:block> <xsl:value-of select="amount"/></fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>

                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block><xsl:value-of select="totalInterestOnCompanyContribution"/></fo:block>
                                        </fo:table-cell>

                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>


                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" font-size="9" text-align="right"
                                  line-height="1">

                            <fo:table line-height="1">

                                <fo:table-column column-width="11%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="11%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block text-align="left" font-size="10" font-weight="600">V.P.F. CONT A/C </fo:block>
                                        </fo:table-cell>


                                        <xsl:for-each select="interestOnVpfContributions">
                                            <fo:table-cell padding="14pt 0pt">
                                                <fo:block> <xsl:value-of select="amount"/></fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>

                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block><xsl:value-of select="totalInterestOnVpfContribution"/></fo:block>
                                        </fo:table-cell>

                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>




                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" border-top="0" font-size="9" text-align="right"
                                  line-height="1">

                            <fo:table line-height="1">

                                <fo:table-column column-width="11%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="6.5%" border-right="1pt solid black"/>
                                <fo:table-column column-width="11%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block text-align="left" font-size="10" font-weight="600">Total Rs. </fo:block>
                                        </fo:table-cell>


                                        <xsl:for-each select="totalInterestMonthWise">
                                            <fo:table-cell padding="14pt 0pt">
                                                <fo:block> <xsl:value-of select="amount"/></fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>

                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block><xsl:value-of select="totalInterest"/></fo:block>
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
