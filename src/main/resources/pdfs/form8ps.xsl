<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template match="form8ps">
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
                <fo:flow flow-name="xsl-region-body" font-size="11pt" line-height="1.2"
                         font-family="Times-Roman" word-spacing="6pt" letter-spacing="1pt">


                    <!-- logo and address -->
                    <fo:block>
                        <fo:block line-height="1.5" font-weight="700" text-align="center">FORM No.8 (P.S.)</fo:block>
                        <fo:block line-height="1.5" font-weight="700" text-align="center">
                            (For Exempted Establishments only)
                        </fo:block>
                        <fo:block line-height="1.5" font-weight="700" text-align="center">
                            THE EMPLOYEES' PENSION SCHEME, 1995 [PARAGRAPH 20]
                        </fo:block>
                    </fo:block>
                    <fo:block line-height="1.5" font-weight="500" margin-top="10pt">
                        Annual statement of Contribution For the Currency period from <xsl:value-of select="contributionFrom"/> to <xsl:value-of select="contributioTo"/>
                    </fo:block>
                    <fo:table >
							<fo:table-column column-width="50%"/>
                            <fo:table-column column-width="50%"/>
                            <fo:table-body>
								<fo:table-row>
									<fo:table-cell text-align="left">
										<fo:block>Name &amp; Address of the Establishment :</fo:block>
									</fo:table-cell>
									<fo:table-cell text-align="left">
										<fo:block> <xsl:value-of select="nameAndEstablishment"/></fo:block>
									</fo:table-cell>
								</fo:table-row>
							</fo:table-body>	
						</fo:table>
                    <!-- <fo:block>
                        Name &amp; Address of the Establishment : <xsl:value-of select="nameAndEstablishment"/>
                    </fo:block> -->
                    <fo:block>
                        Code No.of the Establishment : <xsl:value-of select="codeNumber"/>
                    </fo:block>
                    <fo:block margin-top="20pt">

                        <fo:table border-top="1pt solid black">
                            <fo:table-column column-width="5%"/>
                            <fo:table-column column-width="30%"/>
                            <fo:table-column column-width="20%"/>
                            <fo:table-column column-width="20%"/>
                            <fo:table-column column-width="15%"/>
                            <fo:table-column column-width="10%"/>
                            <fo:table-body start-indent="0pt">
                                <fo:table-row>
                                    <fo:table-cell text-align="center" padding="2pt" border-right="1pt solid black">
                                        <fo:block>SR No.</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt" border-right="1pt solid black">
                                        <fo:block>Account Number</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt" border-right="1pt solid black">
                                        <fo:block>Name of the member (in block letters)</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt" border-right="1pt solid black">
                                        <fo:block>Wages, retaining allowances (if any) and D.A. including cash
                                        value of food, concession paid during the currency period</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt" border-right="1pt solid black">
                                        <fo:block>Contribution to Pension Fund 8.33% (Rs)</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt">
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
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block>(4)</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block>(5)</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" >
                                        <fo:block>(6)</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row border-top="1pt solid black" height="100pt" border-bottom="1pt solid black">
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block ><xsl:value-of select="srNo"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block> <xsl:value-of select="acctNo"/> </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block><xsl:value-of select="name"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block><xsl:value-of select="pfBase"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block><xsl:value-of select="pension"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt">
                                        <fo:block><xsl:value-of select="remarks"/></fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                    <fo:block>
                        <fo:table>
                            <fo:table-column column-width="35%"/>
                            <fo:table-column column-width="10%"/>
                            <fo:table-column column-width="20%"/>
                            <fo:table-column column-width="25%"/>
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell text-align="left" padding="2pt">
                                        <fo:block>Reconciliation of Remittances</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="left" padding="2pt">
                                        <fo:block>Total</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="left" padding="2pt">
                                        <fo:block>Rs. <xsl:value-of select="pfBase"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="left" padding="2pt">
                                        <fo:block>Rs. <xsl:value-of select="pension"/></fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                    <fo:block margin-top="5pt">
                        <fo:table>
                            <fo:table-column column-width="5%"/>
                            <fo:table-column column-width="15%"/>
                            <fo:table-column column-width="10%"/>
                            <fo:table-column column-width="70%"/>
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell text-align="center" padding="1pt">
                                        <fo:block>Sl No</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="1pt">
                                        <fo:block>Month</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="1pt">
                                        <fo:block>Pension Fund</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="left" padding="1pt">
                                        <fo:block>Contributions A/c.No.10</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                    <fo:block margin-top="5pt" border-bottom="1pt solid black">
                        <fo:table>
                            <fo:table-column column-width="30%"/>
                            <fo:table-column column-width="35%"/>
                            <fo:table-column column-width="35%"/>
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell text-align="left" padding="1pt">
                                        <fo:block>
                                            <fo:table>
                                                <fo:table-column column-width="20%"></fo:table-column>
                                                <fo:table-column column-width="40%"></fo:table-column>
                                                <fo:table-column column-width="40%"></fo:table-column>
                                                <fo:table-body>
													<xsl:for-each select="form7PsContribution">
	                                                    <fo:table-row>
	                                                        <fo:table-cell text-align="center" padding="1pt">
	                                                            <fo:block><xsl:value-of select="srNo"/></fo:block>
	                                                        </fo:table-cell>
	                                                        <fo:table-cell text-align="center" padding="1pt">
	                                                            <fo:block><xsl:value-of select="monthAndYear"/></fo:block>
	                                                        </fo:table-cell>
	                                                        <fo:table-cell text-align="center" padding="1pt">
	                                                            <fo:block><xsl:value-of select="pensionFund"/></fo:block>
	                                                        </fo:table-cell>
	                                                    </fo:table-row>
                                                    </xsl:for-each>
                                                    <!-- <fo:table-row>
                                                        <fo:table-cell text-align="center" padding="1pt">
                                                            <fo:block>2.</fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell text-align="center" padding="1pt">
                                                            <fo:block>apr-1999</fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell text-align="center" padding="1pt">
                                                            <fo:block>Rs.</fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell text-align="center" padding="1pt">
                                                            <fo:block>3.</fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell text-align="center" padding="1pt">
                                                            <fo:block>apr-1999</fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell text-align="center" padding="1pt">
                                                            <fo:block>Rs.</fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell text-align="center" padding="1pt">
                                                            <fo:block>4.</fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell text-align="center" padding="1pt">
                                                            <fo:block>apr-1999</fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell text-align="center" padding="1pt">
                                                            <fo:block>Rs.</fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell text-align="center" padding="1pt">
                                                            <fo:block>5.</fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell text-align="center" padding="1pt">
                                                            <fo:block>apr-1999</fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell text-align="center" padding="1pt">
                                                            <fo:block>Rs.</fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell text-align="center" padding="1pt">
                                                            <fo:block>6.</fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell text-align="center" padding="1pt">
                                                            <fo:block>apr-1999</fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell text-align="center" padding="1pt">
                                                            <fo:block>Rs.</fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell text-align="center" padding="1pt">
                                                            <fo:block>7.</fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell text-align="center" padding="1pt">
                                                            <fo:block>apr-1999</fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell text-align="center" padding="1pt">
                                                            <fo:block>Rs.</fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell text-align="center" padding="1pt">
                                                            <fo:block>8.</fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell text-align="center" padding="1pt">
                                                            <fo:block>apr-1999</fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell text-align="center" padding="1pt">
                                                            <fo:block>Rs.</fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell text-align="center" padding="1pt">
                                                            <fo:block>9.</fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell text-align="center" padding="1pt">
                                                            <fo:block>apr-1999</fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell text-align="center" padding="1pt">
                                                            <fo:block>Rs.</fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell text-align="center" padding="1pt">
                                                            <fo:block>10.</fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell text-align="center" padding="1pt">
                                                            <fo:block>apr-1999</fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell text-align="center" padding="1pt">
                                                            <fo:block>Rs.</fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell text-align="center" padding="1pt">
                                                            <fo:block>11.</fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell text-align="center" padding="1pt">
                                                            <fo:block>apr-1999</fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell text-align="center" padding="1pt">
                                                            <fo:block>Rs.</fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row border-bottom="1pt solid black">
                                                        <fo:table-cell text-align="center" padding="1pt">
                                                            <fo:block>12.</fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell text-align="center" padding="1pt">
                                                            <fo:block>apr-1999</fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell text-align="center" padding="1pt">
                                                            <fo:block>Rs.</fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row> -->
                                                    <fo:table-row>
                                                        <fo:table-cell text-align="left" padding="1pt">
                                                            <fo:block>Total</fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell text-align="center" padding="1pt">
                                                            <fo:block>Rs.</fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell text-align="center" padding="1pt">
                                                            <fo:block><xsl:value-of select="pension"/></fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                </fo:table-body>
                                            </fo:table>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="left" padding="1pt">
                                        <fo:block>Certified that the difference between the figures
                                            of total Pension Fund contributions remitted during the currency
                                            period &amp; the total Pension Fund contribution shown under col.5
                                            is solely due to the rounding of amounts to the nearest Rupee under
                                            the rules.</fo:block>
                                        <fo:block margin-top="60pt">
                                            Signature of the Employer (With Office Seal)
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="left" padding="1pt">
                                        <fo:block>(i) Total number of contribution cards enclosed Form 7 (PS)...........................</fo:block>
                                        <fo:block>(ii) Certified that Form 7 (PS) completed, of all the members listed
                                        in this statement are enclosed, except those already sent during the
                                        course of the currency period for the final settlement of the concerned members
                                        account "Remarks" furnished against the names of the respective members above.</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                    <fo:block margin-top="2pt">
                        <fo:table>
                            <fo:table-column column-width="10%"></fo:table-column>
                            <fo:table-column column-width="90%"></fo:table-column>
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block>Note:</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block>
                                            (1) The names of all members including those who had left service during the currency
                                            period should be included in this statement, Where the Form 7 (PS) in respect of such
                                            members had left service were already sent to the Regional Office for the purpose of
                                            final settlement of their accounts, the fact should stated against the members in the
                                            "Remarks" column above thus. 'Form 7 (PS) already sent in the month of <xsl:value-of select="closureDate"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell>
                                        <fo:block></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block margin-top="2pt">
                                            (2) In case of substantial variation in the wages. contributions of any members as
                                            compared to those shown in previous statement the reason should be explained
                                            adequately in the "Remarks" column.
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>

        </fo:root>
    </xsl:template>

</xsl:stylesheet>
