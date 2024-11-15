<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template match="loanHistorySheet">
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
                                            <fo:block><fo:inline>Application No. : <xsl:value-of select="applicationNo"/></fo:inline></fo:block>
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


                                    <xsl:for-each select="loan">
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
