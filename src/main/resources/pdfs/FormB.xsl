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
                        <fo:block line-height="1.5" font-weight="700" text-align="center">FORM B</fo:block>
                        <fo:block line-height="1.5" font-weight="700" text-align="center">
                            [SEE RULE 4(B)]
                        </fo:block>
                        <fo:block line-height="1.5" font-weight="700" text-align="center">
                            SET-ON AND SET-OFF OF ALLCABLE SURPLUS UNDER SECTION 15
                        </fo:block>
                    </fo:block>
                    <fo:block margin-top="20pt">

                        <fo:table border-top="1pt solid black">
                            <fo:table-column column-width="20%"/>
                            <fo:table-column column-width="20%"/>
                            <fo:table-column column-width="20%"/>
                            <fo:table-column column-width="20%"/>
                            <fo:table-column column-width="20%"/>
                            <fo:table-body>

                                <fo:table-row border-top="1pt solid black">
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-left="1pt solid black" border-right="1pt solid black">
                                        <fo:block>Accounting Year</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block>Amount allocable as bonus (in Rs.)</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block>Amount payable as bonus (in Rs.)</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block>Amount of set-on or set-off (in Rs.)</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block>Total set-on or set-off carried forward</fo:block>
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
                                        <fo:block><xsl:value-of select="fiscalYear"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block><xsl:value-of select="totalAllocableBonus"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block><xsl:value-of select="payableBonus"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right ="1pt solid black">
                                        <fo:block><xsl:value-of select="setOnOff"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block><xsl:value-of select="carriedForward"/></fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <!-- </xsl:for-each> -->
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                    <fo:block margin-top="5pt">
						@ Section 2 (4) (b)
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>

        </fo:root>
    </xsl:template>

</xsl:stylesheet>
