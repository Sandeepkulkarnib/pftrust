<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template match="workSheet">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

            <fo:layout-master-set>
                <fo:simple-page-master master-name="simpleA4"
                                       page-width="29.7cm" page-height="21cm"
                                       margin-top="0.3cm" margin-bottom="0.3cm"
                                       margin-left="1cm" margin-right="1cm"
                                       >
                    <fo:region-body/>

                </fo:simple-page-master>


                <fo:simple-page-master master-name="annexure"
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
                                            <fo:external-graphic src="http://3.110.35.227:8080/images/mahindra_logo.png" content-height="75pt" content-width="75pt"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0" text-align="center" line-height="1.2">
                                            <fo:block font-weight="600">COREINTEGRA GLOBAL SERVICES PVT LIMITED  .  STAFF  PROVIDENT  FUND</fo:block>
                                            <fo:block>Statement by the Trustees to a member seceding from the fund</fo:block>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                        <!-- logo and address end -->

                        <fo:block margin-top="7pt">
                            <fo:table line-height="1">
                                <fo:table-column/>
                                <fo:table-column/>
                                <fo:table-column/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell margin="0" padding="0">
                                            <fo:block margin="0" padding="0">
                                                TransferOut No. : <fo:inline><xsl:value-of select="settlementNumber"/></fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell margin="0" padding="0">
                                            <fo:block>Date of Cessation: <fo:inline><xsl:value-of select="dateOfCessation"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell margin="0" padding="0">
                                            <fo:block>Document No. : <fo:inline><xsl:value-of select="documentNo"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" line-height="1" padding="5pt pt0">

                            <fo:block>
                                <fo:inline>Employee Name : <fo:inline><xsl:value-of select="name"/></fo:inline></fo:inline>
                                <fo:inline padding-left="30pt">Pern No. : <fo:inline><xsl:value-of select="pernNumber"/></fo:inline></fo:inline>
                                <fo:inline padding-left="30pt">P.F.No. : <fo:inline><xsl:value-of select="pfNumber"/></fo:inline></fo:inline>
                                <fo:inline padding-left="30pt">Unit Code. : <fo:inline><xsl:value-of select="unitCode"/></fo:inline></fo:inline>
                                <fo:inline padding-left="30pt">Status : <xsl:value-of select="status"/></fo:inline>
                            </fo:block>

                            <fo:block margin-top="5pt">
                                <fo:inline>Dt.of Birth : <fo:inline><xsl:value-of select="dateOfBirth"/></fo:inline></fo:inline>
                                <fo:inline padding-left="30pt">Dt.of Joining PF : <fo:inline><xsl:value-of select="dateOfJoiningPf"/></fo:inline></fo:inline>
                                <fo:inline padding-left="30pt">DOJ Previous Employer : <fo:inline><xsl:value-of select="dateOfJoiningPrior"/></fo:inline></fo:inline>
                                <fo:inline padding-left="30pt">Last Rec Dt. : <fo:inline><xsl:value-of select="lastRecoveryDate"/></fo:inline></fo:inline>
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
                                                            <fo:block><xsl:value-of select="/workSheet/nominee[1]/name"/></fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="1pt">
                                                            <fo:block><xsl:value-of select="/workSheet/nominee[1]/relationship"/></fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="1pt">
                                                            <fo:block><xsl:value-of select="/workSheet/nominee[1]/share"/></fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>

                                                    <fo:table-row font-size="10" line-height="1.2">
                                                        <fo:table-cell padding="1pt" >
                                                            <fo:block><xsl:value-of select="/workSheet/nominee[2]/name"/></fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="1pt">
                                                            <fo:block><xsl:value-of select="/workSheet/nominee[2]/relationship"/></fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="1pt">
                                                            <fo:block><xsl:value-of select="/workSheet/nominee[2]/share"/></fo:block>
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
                                                            <fo:block><xsl:value-of select="/workSheet/nominee[3]/name"/></fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="1pt">
                                                            <fo:block><xsl:value-of select="/workSheet/nominee[3]/relationship"/></fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="1pt">
                                                            <fo:block><xsl:value-of select="/workSheet/nominee[3]/share"/></fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>

                                                    <fo:table-row font-size="10" line-height="1.2">
                                                        <fo:table-cell padding="1pt" >
                                                            <fo:block><xsl:value-of select="/workSheet/nominee[4]/name"/></fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="1pt">
                                                            <fo:block><xsl:value-of select="/workSheet/nominee[4]/relationship"/></fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="1pt">
                                                            <fo:block><xsl:value-of select="/workSheet/nominee[4]/share"/></fo:block>
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
                                <fo:table-column column-width="40%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>

                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Account Particulars (Figures in Rupees)</fo:block>
                                            <fo:block>====================</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>Member</fo:block>
                                            <fo:block>Contribution</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>Company</fo:block>
                                            <fo:block>Contribution</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>Vpf</fo:block>
                                            <fo:block>Contribution</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>Total</fo:block>
                                            <fo:block>(Rupees)</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>
                        </fo:block>

                        <fo:block margin-top="10pt">Additions: </fo:block>

                        <fo:block margin-top="5pt">

                            <fo:table>
                                <fo:table-column column-width="40%" />
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>

                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Opening Balance as on <fo:inline><xsl:value-of select="yearOpeningDate"/></fo:inline></fo:block>
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
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="yearOpeningTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left" padding-top="10pt">
                                            <fo:block>Interest on Opening Balance @ <fo:inline><xsl:value-of select="interestRate"/></fo:inline>% p.a</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right"  padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnYearOpeningMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnYearOpeningCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnYearOpeningVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnYearOpeningTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left" padding-top="10pt">
                                            <fo:block>Contributions for the year</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="currentYearMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="currentYearCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="currentYearVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="currentYearTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left" padding-top="10pt">
                                            <fo:block>Interest on Contribution for the year @ <fo:inline><xsl:value-of select="interestRate"/></fo:inline>% p.a</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right"  padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>


                                    <fo:table-row>
                                        <fo:table-cell text-align="left" padding-top="10pt">
                                            <fo:block>Transfer from Other Funds</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="currentYearTransferInMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="currentYearTransferInCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="currentYearTransferInVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="currentYearTransferInTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left" padding-top="10pt">
                                            <fo:block>Interest on Transfer In @ <fo:inline><xsl:value-of select="interestRate"/></fo:inline>% p.a</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right"  padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearTransferInMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearTransferInCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearTransferInVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearTransferInTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                        <fo:block margin-top="10pt">Less: </fo:block>


                        <fo:block margin-top="5pt">

                            <fo:table>
                                <fo:table-column column-width="40%" />
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>

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
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="currentYearLoanWithdrawalTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left" padding-top="10pt">
                                            <fo:block>Interest on NR withdrawals @ <fo:inline><xsl:value-of select="interestRate"/></fo:inline> % p.a</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right"  padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearLoanWithdrawalMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearLoanWithdrawalCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearLoanWithdrawalVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="10pt">
                                            <fo:block><xsl:value-of select="interestOnCurrentYearLoanWithdrawalTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>




                        <fo:block margin-top="10pt" border-top="1pt dashed black"
                                  border-bottom="1pt dashed black" padding="5pt 0pt">

                            <fo:table>
                                <fo:table-column column-width="40%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>

                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>PF Balance Due/Settlement Date: <fo:inline><xsl:value-of select="dueDate"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="totalMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="totalCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="totalVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="totalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>


                        <fo:block margin-top="10pt">
                            <fo:table>
                                <fo:table-column column-width="20%" />
                                <fo:table-column column-width="10%"/>
                                <fo:table-column column-width="20%"/>
                                <fo:table-column column-width="10%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="25%"/>

                                <fo:table-body>
									
									<fo:table-row>
                                        <fo:table-cell text-align="center">
                                            <fo:block>
                                                <fo:block>Income Tax : <xsl:value-of select="incomeTax"/></fo:block>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="center">
                                            <fo:block>
                                                <fo:block>+</fo:block>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>Edu.cess : <xsl:value-of select="eduCess"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block> = </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block> TDS Amount: </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="tds"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                    <fo:table-row>
                                        <fo:table-cell text-align="center">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="center">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>NET Amount:</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="netContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>


                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                        <fo:block margin-top="10pt" margin-left="60%">
                            <fo:block text-align="center">For COREINTEGRA GLOBAL SERVICES PVT LIMITED</fo:block>
                            <fo:block text-align="center">Staff Provident Fund</fo:block>
                            <fo:block text-align="center" margin-top="35pt">Authorized Signatory</fo:block>
                        </fo:block>

                        <fo:block>
                            <fo:inline>Date:- <xsl:value-of select="processedOnDate"/></fo:inline>
                        </fo:block>

                    </fo:block>

            </fo:flow>
            </fo:page-sequence>




            <fo:page-sequence master-reference="annexure">
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
                                            <fo:block>ANNEXURE TO SETTLEMENT STATEMENT AS ON <xsl:value-of select="settlementAnnexure/date"/></fo:block>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                        <!-- logo and address end -->

                        <fo:block margin="10pt 0pt" margin-bottom="0pt" margin-top="0pt"
                                  border="1pt solid black" line-height="1" padding="5pt pt0">

                            <fo:inline>Employee Name : <xsl:value-of select="settlementAnnexure/name"/></fo:inline>
                            <fo:inline padding-left="30pt">Token No. :   <xsl:value-of select="tokenNumber"/></fo:inline>
                            <fo:inline padding-left="30pt">P.F.No. :  <xsl:value-of select="pfNumber"/></fo:inline>
                            <fo:inline padding-left="30pt">Unit Code. :  <xsl:value-of select="unitCode"/></fo:inline>
                            <fo:inline padding-left="30pt">Dept.Cd :  <xsl:value-of select="deptCode"/></fo:inline>

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

                                        <xsl:for-each select="settlementAnnexure/memberContributions">
                                            <fo:table-cell padding="14pt 0pt">
                                                <fo:block> <xsl:value-of select="amount"/></fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>

                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block> <xsl:value-of select="settlementAnnexure/totalMemberContributions"/></fo:block>
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

                                        <xsl:for-each select="settlementAnnexure/CompanyContributions">
                                            <fo:table-cell padding="14pt 0pt">
                                                <fo:block> <xsl:value-of select="amount"/></fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>

                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block> <xsl:value-of select="settlementAnnexure/totalCompanyContributions"/></fo:block>
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

                                        <xsl:for-each select="settlementAnnexure/vpfContributions">
                                            <fo:table-cell padding="14pt 0pt">
                                                <fo:block> <xsl:value-of select="amount"/></fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>

                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block> <xsl:value-of select="settlementAnnexure/totalVpfContributions"/></fo:block>
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

                                        <xsl:for-each select="settlementAnnexure/totalContributionsMonthWise">
                                            <fo:table-cell padding="14pt 0pt">
                                                <fo:block> <xsl:value-of select="amount"/></fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>

                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block><xsl:value-of select="settlementAnnexure/totalContribution"/></fo:block>
                                        </fo:table-cell>


                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>



                        <fo:block margin-top="10pt" font-weight="600"><xsl:value-of select="settlementAnnexure/interestRate"/> Month wise Interest (Figures in Rupees)
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

                                        <xsl:for-each select="settlementAnnexure/interestOnMemberContributions">
                                            <fo:table-cell padding="14pt 0pt">
                                                <fo:block> <xsl:value-of select="amount"/></fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>

                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block><xsl:value-of select="settlementAnnexure/totalInterestOnMemberContribution"/></fo:block>
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

                                        <xsl:for-each select="settlementAnnexure/interestOnCompanyContributions">
                                            <fo:table-cell padding="14pt 0pt">
                                                <fo:block> <xsl:value-of select="amount"/></fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>

                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block><xsl:value-of select="settlementAnnexure/totalInterestOnCompanyContribution"/></fo:block>
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


                                        <xsl:for-each select="settlementAnnexure/interestOnVpfContributions">
                                            <fo:table-cell padding="14pt 0pt">
                                                <fo:block> <xsl:value-of select="amount"/></fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>

                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block><xsl:value-of select="settlementAnnexure/totalInterestOnVpfContribution"/></fo:block>
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


                                        <xsl:for-each select="settlementAnnexure/totalInterestMonthWise">
                                            <fo:table-cell padding="14pt 0pt">
                                                <fo:block> <xsl:value-of select="amount"/></fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>

                                        <fo:table-cell padding="14pt 0pt">
                                            <fo:block><xsl:value-of select="settlementAnnexure/totalInterest"/></fo:block>
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
