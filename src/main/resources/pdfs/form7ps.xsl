<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template match="form7ps">
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
                <fo:flow flow-name="xsl-region-body" font-size="11pt" line-height="1.5"
                         font-family="Times-Roman" word-spacing="6pt" letter-spacing="1pt">


                    <!-- logo and address -->
                    <fo:block>
                        <fo:block line-height="1.5" font-weight="700" text-align="center">FORM No.7 (P.S.)</fo:block>
                        <fo:block line-height="1.5" font-weight="700" text-align="center">
                            (For Exempted Establishments only)
                        </fo:block>
                        <fo:block line-height="1.5" font-weight="700" text-align="center">
                            THE EMPLOYEES' PENSION SCHEME, 1995
                        </fo:block>
                        <fo:block line-height="1.5" font-weight="500" text-align="center">
                            [Paragraph 19]
                        </fo:block>
                        <fo:block line-height="1.5" text-align="center" font-weight="500">
                            Contribution card for members for the year <xsl:value-of select="fiscalYear"/>
                        </fo:block>
                    </fo:block>
                    <fo:block line-height="1.5" font-weight="500" margin-top="10pt">
                        <fo:block margin-top="5pt">
                            1) A/c. No. :  <xsl:value-of select="accountNo"/>
                        </fo:block>
                        <fo:block margin-top="5pt">
                            2) Name (in block Capitals) : <xsl:value-of select="name"/>
                        </fo:block>
                        <fo:block margin-top="5pt">
                            3) Father's / Husband's Name : <xsl:value-of select="fatherName"/>
                        </fo:block>
                        <fo:table >
							<fo:table-column column-width="50%"/>
                            <fo:table-column column-width="50%"/>
                            <fo:table-body>
								<fo:table-row>
									<fo:table-cell text-align="left">
										<fo:block>4) Name &amp; Address of the Establishment :</fo:block>
									</fo:table-cell>
									<fo:table-cell text-align="left">
										<fo:block> <xsl:value-of select="nameAndAdddress"/></fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>	
						</fo:table>
                        <!-- <fo:block margin-top="5pt">
                            4) Name &amp; Address of the Establishment : <xsl:value-of select="nameAndAdddress"/>
                        </fo:block> -->
                        <fo:block margin-top="5pt"> 
                            5) Statutory rate of contribution : 8.33%
                        </fo:block>
                    </fo:block>
                    <fo:block margin-top="20pt">

                        <fo:table border-top="1pt solid black">
                            <fo:table-column column-width="20%"/>
                            <fo:table-column column-width="30%"/>
                            <fo:table-column column-width="20%"/>
                            <fo:table-column column-width="30%"/>
                            <fo:table-body>

                                <fo:table-row>
                                    <fo:table-cell text-align="left" padding="5pt 10pt" border-right="1pt solid black">
                                        <fo:block>Month</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="left" padding="5pt 10pt" border-right="1pt solid black">
                                        <fo:block>Amount of wages, retaining allowance, if any &amp; DA including cash value of food
                                        concession paid during the month</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="left" padding="5pt 10pt" border-right="1pt solid black">
                                        <fo:block>Contribution to Pension Fund 8.33% Rs.</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="5pt 10pt">
                                        <fo:block>Remarks</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row border-top="1pt solid black">
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block>(1)</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block>(2)</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block>(3)</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt">
                                        <fo:block>(4)</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
	                                <xsl:for-each select="form7PsContribution">
	                                <fo:table-row border-top="1pt solid black">
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block><xsl:value-of select="monthAndYear"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="right" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block><xsl:value-of select="pfBase"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="right" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block><xsl:value-of select="pensionFund"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-bottom ="1pt solid black">
                                        <fo:block><xsl:value-of select="remarks"/></fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                </xsl:for-each>
                                
                                <fo:table-row>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border="1pt solid black" border-left="0">
                                        <fo:block></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="right" padding="2pt 10pt" border="1pt solid black">
                                        <fo:block>Rs. <xsl:value-of select="pfBaseTotal"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="right" padding="2pt 10pt" border="1pt solid black">
                                        <fo:block>Rs. <xsl:value-of select="epsTotal"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-bottom="1pt solid black">
                                        <fo:block></fo:block>
                                    </fo:table-cell>
                                </fo:table-row>

                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                    <fo:block margin-top="5pt">
                        Certified that the difference between the total of contributions shown under Col.(3) of the
                        above table and that arrived at the total wages shown in Col.(2) at the prescribed rate is
                        solely due to the rounding off of contribution to the near Re. under rules.
                    </fo:block>
                    <fo:block margin-top="5pt">
                        Certified that the total amount of contributions indicated under Col.(3) has already been remitted
                        in full Account No. 10 (Pension Fund contribution)
                    </fo:block>
                    <fo:table margin-top="15pt">
                        <fo:table-column column-width="50%"/>
                        <fo:table-column column-width="50%"/>
                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell text-align="left" padding="2pt 10pt">
                                    <fo:block>Date: <xsl:value-of select="closureDate"/> </fo:block>
                                </fo:table-cell>
                                <fo:table-cell text-align="left" padding="2pt 10pt">
                                    <fo:block>Signature of the employer (Office Seal)</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>
                </fo:flow>
            </fo:page-sequence>

        </fo:root>
    </xsl:template>

</xsl:stylesheet>
