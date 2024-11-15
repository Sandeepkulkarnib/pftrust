<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template match="formA">
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
                        <fo:block line-height="1.5" font-weight="700" text-align="center">FORM A</fo:block>
                        <fo:block line-height="1.5" font-weight="700" text-align="center">
                            [SEE RULE 4(A)]
                        </fo:block>
                        <fo:block line-height="1.5" font-weight="700" text-align="center">
                            COMPUTATION OF THE ALLOCABLE SURPLUS UNDER SECTION 2 (4)
                        </fo:block>
                        <fo:table >
							<fo:table-column column-width="100%"/>
                            <!--<fo:table-column column-width="50%"/>-->
                            <fo:table-body>
								<fo:table-row>
									<fo:table-cell text-align="left">
										<fo:block>Name Of The Establishment : <xsl:value-of select="establishmentName"/></fo:block>
									</fo:table-cell>
								</fo:table-row>
								<fo:table-row>
									<fo:table-cell text-align="left">
										<fo:block> Accounting Year ending On The <xsl:value-of select="fiscalYear"/></fo:block>
									</fo:table-cell>
									</fo:table-row>
							</fo:table-body>	
						</fo:table>
                    </fo:block>
                    <fo:block margin-top="20pt">

                        <fo:table border-top="1pt solid black">
                            <fo:table-column column-width="20%"/>
                            <fo:table-column column-width="20%"/>
                            <fo:table-column column-width="20%"/>
                            <fo:table-column column-width="20%"/>
                            <fo:table-column column-width="20%"/>
                            <fo:table-body>

                                <fo:table-row>
                                    <fo:table-cell text-align="left" padding="5pt 10pt" border-left="1pt solid black" border-right="1pt solid black">
                                        <fo:block></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell number-columns-spanned="2" text-align="left" padding="5pt 10pt" border-right="1pt solid black">
                                        <fo:block>Sums deducted from gross profit</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="left" padding="5pt 10pt" border-right="1pt solid black">
                                        <fo:block></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="5pt 10pt" border-right="1pt solid black">
                                        <fo:block></fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row border-top="1pt solid black">
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-left="1pt solid black" border-right="1pt solid black">
                                        <fo:block>Gross Profit for the Accounting year Rs.</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block>Depreciation under section 6(a)</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block>Development rebate or Development allowance section 6(b)</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block>Direct taxes section 6(c)</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block>Further sums as are specified under the third Schedule to the Act</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row border-top="1pt solid black">
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-left="1pt solid black" border-right="1pt solid black">
                                        <fo:block>(1)</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block>(2)</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block>(3)</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block>(4)</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block>(5)</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
	                                <!-- <xsl:for-each select="form7PsContribution"> -->
	                            <fo:table-row border-top="1pt solid black" border-bottom="1pt solid black">
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-left="1pt solid black" border-right="1pt solid black">
                                        <fo:block><xsl:value-of select="grossProfit"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block><xsl:value-of select="depreciationAddmissible"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block><xsl:value-of select="taxAmount"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right ="1pt solid black">
                                        <fo:block><xsl:value-of select="directTax"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block><xsl:value-of select="dividendPayable"/></fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <!-- </xsl:for-each> -->
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                    <fo:block margin-top="15pt">
                        <fo:table border-top="1pt solid black">
                            <fo:table-column column-width="33%"/>
                            <fo:table-column column-width="34%"/>
                            <fo:table-column column-width="33%"/>
							<fo:table-body>
								<fo:table-row border-top="1pt solid black">
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-left="1pt solid black" border-right="1pt solid black">
                                        <fo:block>Total of sums deducted under Column 2,3,4, and 5</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block>available surpluss for the accounting year (Column 1 inus Column 6)</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block>Amount of allocable surplus @ 67% (*60% of Clumn 7)</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row border-top="1pt solid black">
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-left="1pt solid black" border-right="1pt solid black">
                                        <fo:block>(6)</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block>(7)</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block>(8)</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row border-top="1pt solid black" border-bottom="1pt solid black">
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-left="1pt solid black" border-right="1pt solid black">
                                        <fo:block><xsl:value-of select="totalDeduction"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block><xsl:value-of select="availableSurplus"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block><xsl:value-of select="allocableSurplus"/></fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
							</fo:table-body>
                        </fo:table>	
                    </fo:block>
                    <fo:block margin-top="5pt">
						@ Section 2 (4) (a)
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>

        </fo:root>
    </xsl:template>

</xsl:stylesheet>
