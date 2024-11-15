<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template match="loan">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

            <fo:layout-master-set>
                <fo:simple-page-master master-name="simpleA4"
                                       page-width="29.7cm" page-height="21cm"
                                       margin-top="0.3cm" margin-bottom="0.3cm"
                                       margin-left="1cm" margin-right="1cm"
                                       >
                    <fo:region-body/>

                </fo:simple-page-master>


                <fo:simple-page-master master-name="loanHistory"
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

                        <fo:table line-height="1" margin="0pt 5pt">
                            <fo:table-column column-width="13%"/>
                            <fo:table-column column-width="87%"/>

                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0">
                                            <fo:external-graphic src="http://3.110.35.227:8080/images/CoreIntegra.png" content-height="100pt" content-width="100pt"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell margin="0" padding="0">
                                        <fo:block margin="0" padding="0" text-align="center" line-height="1.2">
                                            <fo:block font-weight="600">COREINTEGRA GLOBAL SERVICES PVT LIMITED  .  STAFF  PROVIDENT  FUND</fo:block>
                                            <fo:block>NON REFUNDABLE WITHDRAWAL WORKSHEET</fo:block>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>

                        <!-- logo and address end -->

                        <fo:block border-bottom="1pt dashed red" padding-bottom="5pt">

                            <fo:table line-height="1">
                                <fo:table-column column-width="40%" />
                                <fo:table-column column-width="20%" />
                                <fo:table-column column-width="20%" />
                                <fo:table-column column-width="20%" />

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Employee Name : <xsl:value-of select="name"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Token No. : <xsl:value-of select="tokenNumber"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>P.F.No. : <xsl:value-of select="pfNumber"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Unit Code. : <xsl:value-of select="unitCode"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>

                            <fo:table line-height="1" margin-top="5pt">
                                <fo:table-column column-width="40%" />
                                <fo:table-column column-width="30%"/>
                                <fo:table-column column-width="30%" />

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Dept. Name : <xsl:value-of select="department"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Application No. : <xsl:value-of select="applicationNumber"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Last Recovery Date : <xsl:value-of select="lastRecoveryDate"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>


                            <fo:table line-height="1"  margin-top="5pt">
                                <fo:table-column column-width="40%" />
                                <fo:table-column column-width="30%"/>
                                <fo:table-column column-width="30%" />

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Date of Birth. : <xsl:value-of select="dateOfBirth"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Date of Joining PF. : <xsl:value-of select="dateOfJoiningPf"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>


                            <fo:table line-height="1"  margin-top="5pt">
                                <fo:table-column column-width="40%" />
                                <fo:table-column column-width="30%"/>
                                <fo:table-column column-width="30%" />

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Date. of Joining Prior : <xsl:value-of select="dateOfJoiningPrior"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Application Date. : <xsl:value-of select="applicationDate"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>PF Base Salary : <xsl:value-of select="pfBase"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>

                        </fo:block>


                        <fo:block>

                            <fo:table line-height="1"  margin-top="5pt">
                                <fo:table-column column-width="30%" />
                                <fo:table-column column-width="70%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Ln Code : <xsl:value-of select="loanCode"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block>
                                                <fo:inline>Ln Type : <xsl:value-of select="loanType"/></fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>


                            <fo:table line-height="1"  margin-top="5pt">
                                <fo:table-column column-width="30%"/>
                                <fo:table-column column-width="70%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Loan Amount Applied :</fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block>
                                                <fo:inline><xsl:value-of select="appliedAmount"/></fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>


                            <fo:table line-height="1"  margin-top="5pt">
                                <fo:table-column column-width="30%"/>
                                <fo:table-column column-width="70%"/>

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Cost Involved :</fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block>
                                                <fo:inline><xsl:value-of select="cost"/></fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>

                        </fo:block>



                        <fo:block line-height="1" margin-top="5pt" font-size="11" font-weight="600">

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
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>Mem Cont</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>Empr Cont</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>Vpf Cont</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>Total Rs.</fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>
                        </fo:block>

                        <fo:block margin-top="5pt">Additions: </fo:block>

                        <fo:block margin-top="2pt">

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
                                        <fo:table-cell text-align="left" padding-top="2pt">
                                            <fo:block>Contributions during the year</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="2pt">
                                            <fo:block><xsl:value-of select="currentYearMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="2pt">
                                            <fo:block><xsl:value-of select="currentYearCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="2pt">
                                            <fo:block><xsl:value-of select="currentYearVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="2pt">
                                            <fo:block><xsl:value-of select="currentYearTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left" padding-top="2pt">
                                            <fo:block>Transfer from Other Funds</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="2pt">
                                            <fo:block><xsl:value-of select="currentYearTiMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="2pt">
                                            <fo:block><xsl:value-of select="currentYearTiCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="2pt">
                                            <fo:block><xsl:value-of select="currentYearTiVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right" padding-top="2pt">
                                            <fo:block><xsl:value-of select="currentYearTiTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                        <fo:block margin-top="2pt">Less: </fo:block>


                        <fo:block margin-top="2pt">

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
                                            <fo:block><xsl:value-of select="currentYearLwMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="currentYearLwCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="currentYearLwVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="currentYearLwTotalContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                        <fo:block margin-top="2pt" padding="5pt 0pt">

                            <fo:table>
                                <fo:table-column column-width="40%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>

                                <fo:table-body>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Total</fo:block>
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


                        <fo:block margin-top="0pt" padding="2pt 0pt">

                            <fo:table>
                                <fo:table-column column-width="40%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>
                                <fo:table-column column-width="15%"/>

                                <fo:table-body>

                                    <xsl:if test="loanCode = '01' or loanCode = '13' or loanCode = '11' or loanCode = '12'">
                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Less: Minimum Balance Retained</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>(1000)</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block>(1000)</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block></fo:block>
                                        </fo:table-cell>>
                                    </fo:table-row>
                                    </xsl:if>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>PF Net Amount Available</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="netMemberContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="netCompanyContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="netVpfContribution"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="netContribution"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                    <fo:table-row>
                                        <fo:table-cell text-align="left">
                                            <fo:block>Withdrawal Amount</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="loanAmountOnMember"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="loanAmountOnCompany"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="loanAmountOnVpf"/></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell text-align="right">
                                            <fo:block><xsl:value-of select="totalLoanAmount"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                        <fo:block margin-top="10pt" font-weight="600">WITHDRAWAL ELIGIBILITY </fo:block>

                        <fo:block>

                            <fo:table line-height="1"  margin-top="5pt">
                                <fo:table-column  />
                                <fo:table-column />
                                <fo:table-column  />

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>No.of years membership : <xsl:value-of select="noOfYearsOfMembership"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Minimum Required : <xsl:value-of select="minimumRequiredYears"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Actual : <xsl:value-of select="actualNoOfYears"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>


                            <fo:table line-height="1"  margin-top="5pt">
                                <fo:table-column  />
                                <fo:table-column />

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Marriage / Education Loan Maximum permitted : 3 time</fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Already Availed : <xsl:value-of select="count"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>


                        </fo:block>

                        <fo:block margin-top="5pt">Least of the following Amounts :-</fo:block>

                        <fo:block>
                            <fo:table line-height="1"  margin-top="5pt">
                                <fo:table-column  />
                                <fo:table-column />
                                <fo:table-column />
                                <fo:table-column />

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block>1) Month Salary(For(<fo:inline><xsl:value-of select="salaryMonths"/></fo:inline>) Month)</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block text-align="right"><xsl:value-of select="loanAmountBasedOnSalary"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                        <xsl:if test="loanCode='98'">
                                            <fo:block>2) PF Accumulated Balance(<fo:inline><xsl:value-of select="totalPfBalance"/></fo:inline>%)</fo:block>
                                            </xsl:if>
                                            <xsl:if test="loanCode='99' or loanCode = '01' or loanCode = '13' or loanCode = '11' or loanCode = '12' or loanCode = '02' or loanCode = '03' or loanCode = '04' or loanCode =  '08' or loanCode = '14' or loanCode = '10'">
                                            <fo:block>2) PF Accumulated Balance</fo:block>
                                            </xsl:if>>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block text-align="right"><xsl:value-of select="loanAmountBasedOnPf"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block>3) Applied For</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block text-align="right"><xsl:value-of select="appliedAmount"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block>1) Cost involved</fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block text-align="right"><xsl:value-of select="cost"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block font-weight="600">ELIGIBLE AMOUNT : </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block font-weight="600" text-align="right"><xsl:value-of select="eligibleAmount"/></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>

                                </fo:table-body>
                            </fo:table>

                        </fo:block>


                        <fo:block>
                            <fo:table line-height="1"  margin-top="20pt">
                                <fo:table-column  />
                                <fo:table-column />
                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block>
                                                <fo:inline>User: <xsl:value-of select="createdBy"/></fo:inline>
                                                <fo:inline padding-left="20pt">Date: <xsl:value-of select="approvedDate"/></fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block>
                                                <fo:block text-align="center">For COREINTEGRA GLOBAL SERVICES PVT LIMITED</fo:block>
                                                <fo:block text-align="center">Staff Provident Fund</fo:block>
                                                 <fo:block text-align="center" margin-top="35pt">Trustee</fo:block>
                                                      <!--  <fo:block margin-top="05pt" text-align="center">
                                                <fo:external-graphic src="https://cleansecar-prod.s3.us-east-2.amazonaws.com/OndemandFiles/f2842707-9609-406f-8fab-d3f1ebf3d183.png"
                                                                         content-height="70pt"
                                                                         content-width="70pt"/>
                                                
                                               </fo:block> -->
                                                <!-- <fo:block text-align="center" >Trustee</fo:block> -->
                                            </fo:block>
<!-- margin-top="00pt"--> 
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>

                        </fo:block>

                    </fo:block>

            </fo:flow>
            </fo:page-sequence>

            <fo:page-sequence master-reference="loanHistory">
                <fo:flow flow-name="xsl-region-body" font-size="11pt" line-height="1.2"
                         font-family="Times-Roman" word-spacing="6pt" letter-spacing="1pt">

                    <fo:block padding="10pt"
                              padding-top="0pt" padding-bottom="5pt">

                        <fo:table line-height="1" margin="0pt 5pt">
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
                                            <fo:block>NON REFUNDABLE WITHDRAWAL WORKSHEET</fo:block>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>

                        <!-- logo and address end -->

                        <fo:block border-bottom="1pt dashed red" padding-bottom="5pt">

                            <fo:table line-height="1">
                                <fo:table-column column-width="40%" />
                                <fo:table-column column-width="20%" />
                                <fo:table-column column-width="20%" />
                                <fo:table-column column-width="20%" />

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Employee Name : <xsl:value-of select="name"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Token No. : <xsl:value-of select="tokenNumber"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>P.F.No. : <xsl:value-of select="pfNumber"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Unit Code. : <xsl:value-of select="unitCode"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>

                            <fo:table line-height="1" margin-top="5pt">
                                <fo:table-column column-width="40%" />
                                <fo:table-column column-width="30%"/>
                                <fo:table-column column-width="30%" />

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Dept. Name : <xsl:value-of select="department"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Application No. : <xsl:value-of select="applicationNumber"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Last Recovery Date : <xsl:value-of select="lastRecoveryDate"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>


                            <fo:table line-height="1"  margin-top="5pt">
                                <fo:table-column column-width="40%" />
                                <fo:table-column column-width="30%"/>
                                <fo:table-column column-width="30%" />

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Date of Birth. : <xsl:value-of select="dateOfBirth"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Date of Joining. : <xsl:value-of select="dateOfJoining"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Date of Joining PF. : <xsl:value-of select="dateOfJoiningPf"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>


                            <fo:table line-height="1"  margin-top="5pt">
                                <fo:table-column column-width="40%" />
                                <fo:table-column column-width="30%"/>
                                <fo:table-column column-width="30%" />

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Date. of Joining Prior : <xsl:value-of  select="dateOfJoiningPrior"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>Application Date. : <xsl:value-of select="applicationDate"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block><fo:inline>PF Base Salary : <xsl:value-of select="pfBase"/></fo:inline></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>

                        </fo:block>


                        <fo:block margin-top="10pt">

                            <fo:table line-height="1"  margin-top="10pt">
                                <fo:table-column column-width="25%" />
                                <fo:table-column column-width="15%" />
                                <fo:table-column column-width="30%" />
                                <fo:table-column column-width="10%" />
                                <fo:table-column column-width="20%" />

                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block font-weight="600"><fo:inline>Loan Code</fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block font-weight="600"><fo:inline>Loan Type</fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block font-weight="600"><fo:inline>Loan Date</fo:inline></fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block text-align="right"
                                                      font-weight="600"><fo:inline>Loan Amount</fo:inline></fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>


                                    <xsl:for-each select="loanHistory/loan">
                                        <fo:table-row>
                                            <fo:table-cell>
                                                <fo:block padding-top="10pt">NRL Loan History</fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell>
                                                <fo:block padding-top="10pt"><fo:inline><xsl:value-of select="loanCode"/></fo:inline></fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell>
                                                <fo:block padding-top="10pt"><fo:inline><xsl:value-of select="loanType"/></fo:inline></fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell>
                                                <fo:block padding-top="10pt"><fo:inline><xsl:value-of select="date"/></fo:inline></fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell>
                                                <fo:block text-align="right"
                                                          padding-top="10pt"><fo:inline><xsl:value-of select="amount"/></fo:inline></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                    </xsl:for-each>


                                </fo:table-body>
                            </fo:table>


                        </fo:block>




                    </fo:block>

                </fo:flow>
            </fo:page-sequence>


        </fo:root>
    </xsl:template>

</xsl:stylesheet>
