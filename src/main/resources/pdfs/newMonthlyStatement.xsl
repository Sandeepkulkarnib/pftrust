<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template match="monthlyStatement">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

            <fo:layout-master-set>
                <fo:simple-page-master master-name="simpleA4"
                                       page-width="29.7cm" page-height="21cm"
                                       margin-top="0.3cm" margin-bottom="0.3cm"
                                       margin-left="1cm" margin-right="1cm">
                    <fo:region-body/>

                </fo:simple-page-master>
            </fo:layout-master-set>


            <fo:page-sequence master-reference="simpleA4">
                <fo:flow flow-name="xsl-region-body" font-size="11pt" line-height="1.2"
                         font-family="Times-Roman" word-spacing="6pt" letter-spacing="1pt">

                    <fo:block padding="10pt"
                              padding-top="0pt" padding-bottom="5pt"
                              border="1pt solid red">

                        <fo:table line-height="1">
                            <fo:table-column column-width="10%"/>
                            <fo:table-column column-width="90%"/>

                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0">
                                            <fo:external-graphic src="http://3.110.35.227:8080/images/mahindra-full-logo.png" content-height="75pt" content-width="75pt"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0" text-align="center" line-height="1.2">
                                            <fo:block font-weight="600">COREINTEGRA GLOBAL SERVICES PVT LIMITED  .  STAFF  PROVIDENT  FUND</fo:block>
                                            <fo:block>MONTHLY PROVIDENT FUND MEMBER'S BALANCE AS ON <xsl:value-of select="closingDate"/></fo:block>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                        <!-- logo and address end -->

                        <fo:block margin="5pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" line-height="1" padding="5pt pt0">

                            <fo:block>
                                <fo:inline>Employee Name : <xsl:value-of select="name"/></fo:inline>
                                <fo:inline padding-left="35pt">Token No. : <xsl:value-of select="tokenNumber"/></fo:inline>
                                <fo:inline padding-left="35pt">P.F.No. : <xsl:value-of select="pfNumber"/></fo:inline>
                                <fo:inline padding-left="35pt">Unit Code. : <xsl:value-of select="unitCode"/></fo:inline>
                            </fo:block>

                            <fo:block margin-top="5pt">
<!--                                <fo:inline padding-left="35pt">Pern No. : 900000</fo:inline>-->
                                <fo:inline>Last Rec Dt : <xsl:value-of select="lastRecoveryDate"/></fo:inline>
                                <fo:inline padding-left="35pt">Status : <xsl:value-of select="status"/></fo:inline>
                                <fo:inline padding-left="35pt">Settlement Dt. : <xsl:value-of select="statusDate"/></fo:inline>
                            </fo:block>

                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-top="0pt" margin-bottom="0"
                                  border="1pt solid black" border-top="0" line-height="1">

                            <fo:table >
                                <fo:table-column column-width="50%"/>
                                <fo:table-column column-width="50%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell margin="0" padding="0" border-right="1pt solid black">

                                            <fo:table >
                                                <fo:table-column column-width="50%"/>
                                                <fo:table-column column-width="25%"/>
                                                <fo:table-column column-width="25%"/>


                                                <fo:table-body>

                                                    <fo:table-row font-weight="600" line-height="1" font-size="11">
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

                                        </fo:table-cell>
                                        <fo:table-cell margin="0" padding="0">

                                            <fo:table >

                                                <fo:table-column column-width="50%"/>
                                                <fo:table-column column-width="25%"/>
                                                <fo:table-column column-width="25%"/>

                                                <fo:table-body>

                                                    <fo:table-row font-weight="600" line-height="1" font-size="11">
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

                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>


                        <fo:block margin="0pt"
                                  border="1pt solid black"
                                  border-top="0" line-height="1">

                            <fo:table >
                                <fo:table-column column-width="50%"/>
                                <fo:table-column column-width="50%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell margin="0" padding="0" border-right="1pt solid black">

                                            <fo:table >

                                                <fo:table-column column-width="50%"/>
                                                <fo:table-column column-width="25%"/>
                                                <fo:table-column column-width="25%"/>

                                                <fo:table-body>

                                                    <fo:table-row font-size="10" line-height="1.2">
                                                        <fo:table-cell padding="1pt" >
                                                            <fo:block><xsl:value-of select="/monthlyStatement/nominees[1]/name"/></fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="1pt">
                                                            <fo:block><xsl:value-of select="/monthlyStatement/nominees[1]/relationship"/></fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="1pt">
                                                            <fo:block><xsl:value-of select="/monthlyStatement/nominees[1]/share"/></fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>

                                                    <fo:table-row font-size="10" line-height="1.2">
                                                        <fo:table-cell padding="1pt" >
                                                            <fo:block><xsl:value-of select="/monthlyStatement/nominees[2]/name"/></fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="1pt">
                                                            <fo:block><xsl:value-of select="/monthlyStatement/nominees[2]/relationship"/></fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="1pt">
                                                            <fo:block><xsl:value-of select="/monthlyStatement/nominees[2]/share"/></fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>

                                                </fo:table-body>
                                            </fo:table>

                                        </fo:table-cell>
                                        <fo:table-cell margin="0" padding="0">

                                            <fo:table >
                                                <fo:table-column column-width="50%"/>
                                                <fo:table-column column-width="25%"/>
                                                <fo:table-column column-width="25%"/>

                                                <fo:table-body>

                                                    <fo:table-row font-size="10" line-height="1.2">
                                                        <fo:table-cell padding="1pt" >
                                                            <fo:block><xsl:value-of select="/monthlyStatement/nominees[3]/name"/></fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="1pt">
                                                            <fo:block><xsl:value-of select="/monthlyStatement/nominees[3]/relationship"/></fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="1pt">
                                                            <fo:block><xsl:value-of select="/monthlyStatement/nominees[3]/share"/></fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>

                                                    <fo:table-row font-size="10" line-height="1.2">
                                                        <fo:table-cell padding="1pt" >
                                                            <fo:block><xsl:value-of select="/monthlyStatement/nominees[4]/name"/></fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="1pt">
                                                            <fo:block><xsl:value-of select="/monthlyStatement/nominees[4]/relationship"/></fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="1pt">
                                                            <fo:block><xsl:value-of select="/monthlyStatement/nominees[4]/share"/></fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>

                                                </fo:table-body>
                                            </fo:table>

                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>


                        <fo:block line-height="1" margin-top="10pt" font-size="11" font-weight="600">

                            <fo:table>
                                <fo:table-column column-width="22%" />
                                <fo:table-column column-width="13%"/>
                                <fo:table-column column-width="13%"/>
                                <fo:table-column column-width="13%"/>
                                <fo:table-column column-width="13%"/>
                                <fo:table-column column-width="13%"/>
                                <fo:table-column column-width="13%"/>
                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Account Particulars</fo:block>
                                            <fo:block>====================</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>Member</fo:block>
                                            <fo:block>Contribution</fo:block>
                                            <fo:block>Tax Free</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>Member</fo:block>
                                            <fo:block>Contribution</fo:block>
                                            <fo:block>Taxable</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>Company</fo:block>
                                            <fo:block>Contribution</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>Vpf</fo:block>
                                            <fo:block>Contribution</fo:block>
                                            <fo:block>Tax Free</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>Vpf</fo:block>
                                            <fo:block>Contribution</fo:block>
                                            <fo:block>Taxable</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>Total</fo:block>
                                            <fo:block>(Rupees)</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>
                        </fo:block>

                        <fo:block margin-top="10pt"
                                  border-bottom="1pt dashed black" padding-bottom="5pt">
                            <fo:table>
                                <fo:table-column column-width="22%" />
                                <fo:table-column column-width="13%"/>
                                <fo:table-column column-width="13%"/>
                                <fo:table-column column-width="13%"/>
                                <fo:table-column column-width="13%"/>
                                <fo:table-column column-width="13%"/>
                                <fo:table-column column-width="13%"/>
                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell  text-align="left">
                                            <fo:block>Opening Balance as on <xsl:value-of select="yearOpeningDate"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="yearOpeningMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell  text-align="right">
                                            <fo:block>0.00</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell  text-align="right">
                                            <fo:block><xsl:value-of select="yearOpeningCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="yearOpeningVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>0.00</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="yearOpeningTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>

                        </fo:block>


                        <fo:block margin-bottom="10pt"
                                  padding-top="10pt" padding-bottom="10pt"
                                  border-bottom="1pt dashed black"
                                  line-height="1.2">

                            <fo:table>
                                <fo:table-column column-width="22%" />
                                <fo:table-column column-width="13%"/>
                                <fo:table-column column-width="13%"/>
                                <fo:table-column column-width="13%"/>
                                <fo:table-column column-width="13%"/>
                                <fo:table-column column-width="13%"/>
                                <fo:table-column column-width="13%"/>
                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell  text-align="left">
                                            <fo:block>Monthly Contribution (<xsl:value-of select="finantialPeriod"/>): </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell  text-align="right">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell  text-align="right">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell  text-align="right">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell  text-align="right">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell  text-align="right">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                    <xsl:for-each select="contributionDetails">
                                        <fo:table-row>
                                            <fo:table-cell  text-align="left">
                                                <fo:block><xsl:value-of select="monthName"/></fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell text-align="right">
                                                <fo:block><xsl:value-of select="pfMemberContributionTaxFree"/></fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell text-align="right">
                                                <fo:block><xsl:value-of select="pfMemberContributionTaxable"/></fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell  text-align="right">
                                                <fo:block><xsl:value-of select="pfCompanyContribution"/></fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell  text-align="right">
                                                <fo:block><xsl:value-of select="pfVpfContributionTaxFree"/></fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell  text-align="right">
                                                <fo:block><xsl:value-of select="pfVpfContributionTaxable"/></fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell text-align="right">
                                                <fo:block><xsl:value-of select="pfTotalContribution"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                    </xsl:for-each>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>



                    <fo:block padding-bottom="10pt"
                              line-height="1.2">

                        <fo:table>
                            <fo:table-column column-width="22%" />
                            <fo:table-column column-width="13%"/>
                            <fo:table-column column-width="13%"/>
                            <fo:table-column column-width="13%"/>
                            <fo:table-column column-width="13%"/>
                            <fo:table-column column-width="13%"/>
                            <fo:table-column column-width="13%"/>
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell  text-align="left">
                                        <fo:block>Total Contributions </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  text-align="right">
                                        <fo:block><xsl:value-of select="totalMemberContributionTaxFree"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  text-align="right">
                                        <fo:block><xsl:value-of select="totalMemberContributionTaxable"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="right">
                                        <fo:block><xsl:value-of select="totalCompanyContribution"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  text-align="right">
                                        <fo:block><xsl:value-of select="totalVpfContributionTaxFree"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  text-align="right">
                                        <fo:block><xsl:value-of select="totalVpfContributionTaxable"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  text-align="right">
                                        <fo:block><xsl:value-of select="totalContribution"/></fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>


                    <fo:block padding-bottom="5pt"
                              line-height="1.2">

                        <fo:table>
                            <fo:table-column column-width="22%" />
                            <fo:table-column column-width="13%"/>
                            <fo:table-column column-width="13%"/>
                            <fo:table-column column-width="13%"/>
                            <fo:table-column column-width="13%"/>
                            <fo:table-column column-width="13%"/>
                            <fo:table-column column-width="13%"/>
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell  text-align="left">
                                        <fo:block>Transfer from Other Fund </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  text-align="right">
                                        <fo:block><xsl:value-of select="transferInMemberContribution"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  text-align="right">
                                        <fo:block>0.00</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="right">
                                        <fo:block><xsl:value-of select="transferInCompanyContribution"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  text-align="right">
                                        <fo:block><xsl:value-of select="transferInVpfContribution"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  text-align="right">
                                        <fo:block>0.00</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  text-align="right">
                                        <fo:block><xsl:value-of select="transferInTotalContribution"/></fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>


                    <fo:block padding-bottom="10pt"
                              line-height="1.2" border-bottom="1pt dashed black">

                        <fo:table>
                            <fo:table-column column-width="22%" />
                            <fo:table-column column-width="13%"/>
                            <fo:table-column column-width="13%"/>
                            <fo:table-column column-width="13%"/>
                            <fo:table-column column-width="13%"/>
                            <fo:table-column column-width="13%"/>
                            <fo:table-column column-width="13%"/>
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell  text-align="left">
                                        <fo:block>Less : NRL Withdrawals </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  text-align="right">
                                        <fo:block><xsl:value-of select="loanOnMemberContribution"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  text-align="right">
                                        <fo:block>0.00</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="right">
                                        <fo:block><xsl:value-of select="loanOnCompanyContribution"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  text-align="right">
                                        <fo:block><xsl:value-of select="loanOnVpfContribution"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  text-align="right">
                                        <fo:block>0.00</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  text-align="right">
                                        <fo:block><xsl:value-of select="loanOnTotalContribution"/></fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>


                    <fo:block margin-top="5pt" padding-bottom="5pt"
                              line-height="1.2" border-bottom="1pt dashed black">

                        <fo:table>
                            <fo:table-column column-width="22%" />
                            <fo:table-column column-width="13%"/>
                            <fo:table-column column-width="13%"/>
                            <fo:table-column column-width="13%"/>
                            <fo:table-column column-width="13%"/>
                            <fo:table-column column-width="13%"/>
                            <fo:table-column column-width="13%"/>
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell  text-align="left">
                                        <fo:block>Closing Balance as on <xsl:value-of select="closingDate"/> </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  text-align="right">
                                        <fo:block><xsl:value-of select="netMemberContributionTaxFree"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  text-align="right">
                                        <fo:block><xsl:value-of select="netMemberContributionTaxable"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="right">
                                        <fo:block><xsl:value-of select="netCompanyContribution"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  text-align="right">
                                        <fo:block><xsl:value-of select="netVpfContributionTaxFree"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  text-align="right">
                                        <fo:block><xsl:value-of select="netVpfContributionTaxable"/></fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell  text-align="right">
                                        <fo:block><xsl:value-of select="netContribution"/></fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>

 <xsl:if test="transferInDetailsForMS = 'TRUST'"> 
                    <fo:block line-height="1" border-top="1pt dashed black"
                              font-size="9" padding-top="10pt"
                                margin-bottom="0" padding-bottom="0">

                        <fo:table>

                            <fo:table-column column-width="20%" />
                            <fo:table-column column-width="10%"/>
                            <fo:table-column column-width="14%"/>
                            <fo:table-column column-width="14%"/>
                            <fo:table-column column-width="30%"/>
                            <fo:table-column column-width="12%"/>

                            <fo:table-body>

                                <fo:table-row>
                                    <fo:table-cell text-align="left">
                                        <fo:block>Date of Transfer-IN</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center">
                                        <fo:block>Receipt Date</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center">
                                        <fo:block>EE contributions</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center">
                                        <fo:block>ER Contributions</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center">
                                        <fo:block>Company Name</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center">
                                        <fo:block>Document Nos.</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>

                            </fo:table-body>
                        </fo:table>
                    </fo:block>

</xsl:if>
 
                        <fo:block line-height="1" border-top="1pt dashed black" margin-bottom="0"
                                  font-size="8" padding-top="2pt" padding-bottom="1pt">

                            <fo:table>
                                <fo:table-column column-width="20%" />
                                <fo:table-column column-width="10%"/>
                                <fo:table-column column-width="14%"/>
                                <fo:table-column column-width="14%"/>
                                <fo:table-column column-width="30%"/>
                                <fo:table-column column-width="12%"/>

                                <fo:table-body>

                                    <xsl:for-each select="transferInDetailsForMS">
                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block><xsl:value-of select="createdAtDate"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="center">
                                            <fo:block><xsl:value-of select="receiptAtDate"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="center">
                                            <fo:block><xsl:value-of select="employeeContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="center">
                                            <fo:block><xsl:value-of select="employerContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="center">
                                            <fo:block><xsl:value-of select="companyName"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="center">
                                            <fo:block><xsl:value-of select="documentNumber"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                    </xsl:for-each>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="center">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="center">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="center">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="center">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="center">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>
                        </fo:block>

<fo:block padding-left="10pt">1.The Closing Balance shown above exclude interest for the year.</fo:block>

<fo:block padding-left="10pt">2.Interest on PF account will get credited on 31st March after interest rate notified by Government.</fo:block>
                    </fo:block>

            </fo:flow>
            </fo:page-sequence>

        </fo:root>
    </xsl:template>

</xsl:stylesheet>
