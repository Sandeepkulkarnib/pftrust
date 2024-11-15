<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template match="form3A">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

            <fo:layout-master-set>
                <fo:simple-page-master master-name="simpleA3"
                                       page-height="29.7cm" page-width="21cm"
                                       margin-top="0.3cm" margin-bottom="0.3cm"
                                       margin-left="1cm" margin-right="1cm">
                    <fo:region-body/>
                </fo:simple-page-master>
            </fo:layout-master-set>


            <fo:page-sequence master-reference="simpleA3">
                <fo:flow flow-name="xsl-region-body" font-size="10pt" line-height="1"
                         font-family="Times-Roman" word-spacing="6pt" letter-spacing="1pt">


                    <!-- logo and address -->
                    <fo:block>
                        <fo:block line-height="1" font-weight="700" text-align="center">FORM 3A</fo:block>
                        <fo:block line-height="1" font-weight="700" text-align="center">
                            Form No. 3A(Revised) (See Paragraphs 35 &amp; 42 Employees Provident Fund Scheme,1952)
                        </fo:block>
                        <fo:block line-height="1" font-weight="700" text-align="center">
                            (See Paragraph 19 of the Employees Pension Scheme,1995)
                        </fo:block>
                        <fo:block line-height="1" font-weight="500" text-align="center">
                             (FOR UNEXEMPTED ESTABLISHMENT ONLY)
                        </fo:block>
                        <fo:block line-height="1" text-align="center" font-weight="500">
                            Contribution Card for the Currency Period from  Mar <xsl:value-of select="startDate"/> to feb <xsl:value-of select="endDate"/> 
                        </fo:block>
                    </fo:block>
                    <fo:block line-height="1" font-weight="500" margin-top="10pt">
                        <fo:block margin-top="5pt">
                            1) Account No : <xsl:value-of select="accountNo"/>
                        </fo:block>
                        <fo:block margin-top="5pt">
                            2) Name/Surname (in block Capitals) : <xsl:value-of select="name"/>
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
                        <fo:block margin-top="3pt"> 
                            5) Statutory rate of contribution : <xsl:value-of select="contributionRate"/>
                        </fo:block>
                        <fo:block margin-top="3pt"> 
                            6) Voluntary higher rate of employee's : NO
                        </fo:block>
                        <fo:block margin-top="3pt"> 
                            7) Employer Contribution on Hr.Wages to EPF (ER) Y/N : NO
                        </fo:block>
                        <fo:block margin-top="3pt"> 
                            8) Voluntary Contribution to Pension Y/N : NO
                        </fo:block>
                    </fo:block>
                    <fo:block margin-top="10pt">

                        <fo:table border-top="1pt solid black">
                            <fo:table-column  column-width="12.5%"/>
                            <fo:table-column  column-width="12.5%"/>
                            <fo:table-column  column-width="12.5%"/>
                            <fo:table-column  column-width="12.5%"/>
                            <fo:table-column  column-width="12.5%"/>
                            <fo:table-column  column-width="12.5%"/>
                            <fo:table-column  column-width="12.5%"/>
                            <fo:table-column  column-width="12.5%"/>
                            <fo:table-body>

                                <fo:table-row>
                                    <fo:table-cell number-rows-spanned="2" text-align="left" padding="5pt 10pt" border-right="1pt solid black" border-left="1pt solid black">
                                        <fo:block>Months</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell number-columns-spanned="2" text-align="left" padding="5pt 10pt" border-right="1pt solid black" border-bottom="1pt solid black">
                                        <fo:block>Employee's Share</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell number-columns-spanned="2" text-align="left" padding="5pt 10pt" border-right="1pt solid black" border-bottom="1pt solid black">
                                        <fo:block>Employer's Share</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell number-rows-spanned="2" text-align="left" padding="5pt 10pt" border-right="1pt solid black">
                                        <fo:block>Refund of adv.</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell number-rows-spanned="2" text-align="left" padding="5pt 10pt" border-right="1pt solid black">
                                        <fo:block>No. of day's /period of non cont. service. If</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell number-rows-spanned="2" text-align="center" padding="5pt 10pt" border-right="1pt solid black">
                                        <fo:block>Remarks</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
									<!-- <fo:table-cell text-align="left" padding="5pt 10pt" border-right="1pt solid black">
                                        <fo:block></fo:block>
                                    </fo:table-cell>-->
                                    <fo:table-cell text-align="left" padding="5pt 10pt" border-right="1pt solid black">
                                        <fo:block>Amt. of Wages</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  text-align="left" padding="5pt 10pt" border-right="1pt solid black">
                                        <fo:block>EPF</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="left" padding="5pt 10pt" border-right="1pt solid black">
                                        <fo:block>EPF &amp; 8-1/3% if</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="left" padding="5pt 10pt" border-right="1pt solid black">
                                        <fo:block>Pension fund 8-1/3%</fo:block>
                                    </fo:table-cell>
                                    <!-- <fo:table-cell text-align="left" padding="5pt 10pt" border-right="1pt solid black">
                                        <fo:block></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="left" padding="5pt 10pt" border-right="1pt solid black">
                                        <fo:block></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell number-rows-spanned="2" text-align="center" padding="5pt 10pt">
                                        <fo:block></fo:block>
                                    </fo:table-cell> -->
								</fo:table-row>>
                                <fo:table-row border-top="1pt solid black">
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black" border-left="1pt solid black">
                                        <fo:block>1</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block>2</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block>3</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block>4 (A)</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block>4 (B)</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block>5</fo:block>5>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block>6</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block>7</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
        							
	                                <xsl:for-each select="form3AContribution">
	                                <fo:table-row border-top="1pt solid black">
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black" border-left="1pt solid black">
                                        <fo:block><xsl:value-of select="monthAndYear"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="right" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block><xsl:value-of select="pfBase"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="right" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block><xsl:value-of select="epf"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right ="1pt solid black">
                                        <fo:block><xsl:value-of select="epfAnd"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right ="1pt solid black">
                                        <fo:block><xsl:value-of select="pensionFund"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right ="1pt solid black">
                                        <fo:block> --- </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right ="1pt solid black">
                                        <fo:block> --- </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-right="1pt solid black">
                                        <fo:block><xsl:value-of select="remarks"/></fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                </xsl:for-each>
                                
                                <fo:table-row>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border="1pt solid black" border-left="1pt solid black">
                                        <fo:block>Total</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border="1pt solid black">
                                        <fo:block><xsl:value-of select="pfBaseTotal"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border="1pt solid black">
                                        <fo:block><xsl:value-of select="epfTotal"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border="1pt solid black">
                                        <fo:block><xsl:value-of select="epfAndTotal"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border="1pt solid black">
                                        <fo:block><xsl:value-of select="pensionFundTotal"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border="1pt solid black">
                                        <fo:block></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border="1pt solid black">
                                        <fo:block></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" padding="2pt 10pt" border-top="1pt solid black" border-bottom="1pt solid black" border-right="1pt solid black">
                                        <fo:block></fo:block>
                                    </fo:table-cell>
                                </fo:table-row>

                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                    <fo:block margin-top="3pt">
                        Certified that the total amount of contribution (both share) indicated in this card i.e.    Rs. <xsl:value-of select="sumOfEpfAndEpfand"/>
                    </fo:block>
                    <fo:block margin-top="3pt">
                        has alredy been remitted in full in EPF A/C No.1 and Pension Fund A/C No.10 Rs. <xsl:value-of select="pensionFundTotal"/>
                    </fo:block>
                    
                    <fo:block margin-top="3pt" line-height="1">
                        Certified that the difference between the total of the contribution show under columns(3) and 4(a) &amp; 4(b) of the above table
                        and that arrived at on the total wages show in Col(2) at the perscribed rate in solely due to rounding off of contribution
                        to the nearest ruppee under the rules.
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
                    <fo:table margin-top="15pt">
                        <fo:table-column column-width="10%"/>
                        <fo:table-column column-width="90%"/>
                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell text-align="left" padding="2pt 10pt">
                                    <fo:block>Note :</fo:block>
                                </fo:table-cell>
                                <fo:table-cell text-align="left" padding="2pt 10pt" line-height="1">
                                    <fo:block>
										In respecct Form sent to Regional office during the course of the currency period for the purpose of final
										settlement of the accounts of the member who had left service detail of date and reason for leaving service.
										Should be furninshed under column7(A) &amp; (B) in respect of those are not member of the Pension Fund 
										the employers share of contribution to the EPF will be 8-1/3 or 10% as the case may be and is to be show 
										under column 4(a)
									</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>
                </fo:flow>
            </fo:page-sequence>

        </fo:root>
    </xsl:template>

</xsl:stylesheet>
