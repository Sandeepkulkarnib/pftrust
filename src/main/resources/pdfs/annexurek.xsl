<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template match="annuxurek">
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
                        <fo:table line-height="1" word-spacing="6pt">
                            <fo:table-column column-width="100pt"/>
                            <fo:table-column/>

                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell padding-bottom="10pt">
                                        <fo:block padding-bottom="10pt">
                                            <fo:external-graphic src="url('http://3.110.35.227:8080/images/mahindra-full-logo.png')"
                                                                 content-height="75pt" content-width="75pt"/>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell>
                                        <fo:block  text-align="center" margin-top="10pt">
                                            <fo:block>
                                                COREINTEGRA GLOBAL SERVICES PVT LIMITED  .  STAFF  PROVIDENT  FUND
                                            </fo:block>
                                            <fo:block>
                                                VINMAR HOUSE, PLOT NO. A/41, ROAD NO. 2, MIDC,
                                            </fo:block>
                                            <fo:block>
                                                ANDHERI (EAST)  MUMBAI - 400093
                                            </fo:block>
                                            <fo:block margin-top="10pt">
                                                PF TRANSFER DETAILS - ANNEXURE ' K '.
                                            </fo:block>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                    <!-- logo and address end -->

                    <fo:block color="#27292d" margin-top="20pt" line-height="1.2">
                        Co-Cd/  Serial  No./Doc.No.   <xsl:value-of select="unitCode"/>   /
                        <xsl:value-of select="serialNumber"/>   /   <xsl:value-of select="documentNumber"/>
                    </fo:block>

                    <fo:list-block color="#27292d"
                                   margin-top="10pt" line-height="1.2"
                                   word-spacing="6pt"  margin-right="20pt">

                        <fo:list-item>
                            <fo:list-item-label end-indent="label-end()">
                                <fo:block>1.</fo:block>
                            </fo:list-item-label>

                            <fo:list-item-body start-indent="body-start()">
                                <fo:block>No.  &amp;  Date  of  advice  for  transfer  of  account  in  respect  of  the  following  subscriber: </fo:block>
                            </fo:list-item-body>
                        </fo:list-item>


                        <fo:list-item margin-top="10pt">
                            <fo:list-item-label end-indent="label-end()">
                                <fo:block>2.</fo:block>
                            </fo:list-item-label>

                            <fo:list-item-body start-indent="body-start()">
                                <fo:block>
                                    <fo:inline text-align="left">From A/C No. <xsl:value-of select="fromAccountNumber"/> </fo:inline>
                                    <fo:inline text-align="right"> To A/C No. <xsl:value-of select="toAccountNumber"/></fo:inline>
                                </fo:block>
                            </fo:list-item-body>
                        </fo:list-item>

                        <fo:list-item margin-top="10pt">
                            <fo:list-item-label end-indent="label-end()">
                                <fo:block> </fo:block>
                            </fo:list-item-label>
                            <fo:list-item-body>
                                <fo:block line-height="1" margin-top="10pt" word-spacing="6pt" margin-left="13pt">
                                    <fo:table padding="0" margin="0">
                                        <fo:table-column column-width="30%" padding="0" margin="0"/>
                                        <fo:table-column column-width="70%" padding="0" margin="0"/>

                                        <fo:table-body>
                                            <fo:table-row>
                                                <fo:table-cell padding="0" margin="0">

                                                    <xsl:if test="transferOutType = 'RPFC'">
                                                        <fo:block padding="0" margin="0">Name and Address of</fo:block>
                                                        <fo:block padding="0" margin="0">Current Employer: </fo:block>
                                                    </xsl:if>

                                                    <xsl:if test="transferOutType = 'TRUST'">
                                                        <fo:block padding="0" margin="0">Payee Name and Address: </fo:block>
                                                    </xsl:if>

                                                </fo:table-cell>
                                                <fo:table-cell padding="0" margin="0">
                                                <xsl:if test="transferOutType = 'TRUST'">
                                                    <fo:block padding="0" margin="0"><xsl:value-of select="payeeName"/></fo:block>
                                                
                                                    <fo:block padding="0" margin="0"><xsl:value-of select="addressLine1"/></fo:block>
                                                    <fo:block padding="0" margin="0"><xsl:value-of select="addressLine2"/></fo:block>
                                                    <fo:block padding="0" margin="0"><xsl:value-of select="addressLine3"/></fo:block>
                                                    <fo:block padding="0" margin="0"><xsl:value-of select="addressLine4"/></fo:block>
                                                    </xsl:if>
                                           
                                                <xsl:if test="transferOutType = 'RPFC'">
                                                    <fo:block padding="0" margin="0"><xsl:value-of select="currentEmployerName"/></fo:block>
                                                
                                                    <fo:block padding="0" margin="0"><xsl:value-of select="currentAddressLine1"/></fo:block>
                                                    <fo:block padding="0" margin="0"><xsl:value-of select="currentAddressLine2"/></fo:block>
                                                    <fo:block padding="0" margin="0"><xsl:value-of select="currentAddressLine3"/></fo:block>
                                                    <fo:block padding="0" margin="0"><xsl:value-of select="currentAddressLine4"/></fo:block>
                                                    </xsl:if>
                                                </fo:table-cell>
                                            </fo:table-row>
                                        </fo:table-body>
                                    </fo:table>
                                </fo:block>
                            </fo:list-item-body>
                        </fo:list-item>


                        <fo:list-item margin-top="15pt">
                            <fo:list-item-label end-indent="label-end()">
                                <fo:block>3.</fo:block>
                            </fo:list-item-label>
                            <fo:list-item-body start-indent="body-start()">
                                <fo:block>Name of Subscriber : <xsl:value-of select="subscriber"/></fo:block>
                            </fo:list-item-body>
                        </fo:list-item>


                        <fo:list-item margin-top="10pt">
                            <fo:list-item-label end-indent="label-end()">
                                <fo:block>4.</fo:block>
                            </fo:list-item-label>
                            <fo:list-item-body start-indent="body-start()">
                                <fo:block>Balance of P. F. accumulation as on <xsl:value-of select="yearOpeningDate"/></fo:block>
                                <fo:table>
                                    <fo:table-column column-width="70%"/>
                                    <fo:table-column column-width="30%"/>

                                    <fo:table-body>
                                        <fo:table-row>
                                            <fo:table-cell text-align="left">
                                                <fo:block>a. Employee's Contribution to P. F. </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell text-align="right">
                                                <fo:block><xsl:value-of select="yearOpeningEmployeeContribution"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>

                                        <fo:table-row>
                                            <fo:table-cell text-align="left">
                                                <fo:block>b. Employer's Contribution to P. F. </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell text-align="right">
                                                <fo:block><xsl:value-of select="yearOpeningEmployerContribution"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>

                                        <fo:table-row>
                                            <fo:table-cell text-align="left">
                                                <fo:block>c. Voluntary Contribution to P. F. </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell text-align="right">
                                                <fo:block><xsl:value-of select="yearOpeningVoluntaryContribution"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                    </fo:table-body>
                                </fo:table>

                            </fo:list-item-body>
                        </fo:list-item>

                        <fo:list-item margin-top="10pt">
                            <fo:list-item-label end-indent="label-end()">
                                <fo:block>5. </fo:block>
                            </fo:list-item-label>
                            <fo:list-item-body start-indent="body-start()">
                                <fo:block>Contribution &amp; Interest during current </fo:block>
                                <fo:block>accounting period (upto the last day of transfer ) </fo:block>


                                <fo:table margin-top="10pt">
                                    <fo:table-column column-width="70%"/>
                                    <fo:table-column column-width="30%"/>

                                    <fo:table-body>

                                        <fo:table-row>
                                            <fo:table-cell text-align="left">
                                                <fo:block>d. Employee Contribution to P. F. </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell text-align="right">
                                                <fo:block><xsl:value-of select="employeeContribution"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>

                                        <fo:table-row>
                                            <fo:table-cell text-align="left">
                                                <fo:block>e. Employee Contribution Transfer Received </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell text-align="right">
                                                <fo:block><xsl:value-of select="employeeContributionTransferIn"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>

                                        <fo:table-row>
                                            <fo:table-cell padding-top="10pt" text-align="left">
                                                <fo:block>f. Interest On Employee's Contribution P.F </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell padding-top="10pt" text-align="right">
                                                <fo:block><xsl:value-of select="interestOnEmployeeContribution"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>


                                        <fo:table-row>
                                            <fo:table-cell text-align="left">
                                                <fo:block>g. Employer's Contribution to P. F. </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell text-align="right">
                                                <fo:block><xsl:value-of select="employerContribution"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>

                                        <fo:table-row>
                                            <fo:table-cell text-align="left">
                                                <fo:block>h. Employer's Contribution Transfer Received </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell text-align="right">
                                                <fo:block><xsl:value-of select="employerContributionTransferIn"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>

                                        <fo:table-row>
                                            <fo:table-cell padding-top="10pt" text-align="left">
                                                <fo:block>i. Interest On Employer's Contribution P.F </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell padding-top="10pt" text-align="right">
                                                <fo:block><xsl:value-of select="interestOnEmployerContribution"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>

                                        <fo:table-row>
                                            <fo:table-cell text-align="left">
                                                <fo:block>j. Voluntary Contribution to P. F.</fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell text-align="right">
                                                <fo:block><xsl:value-of select="voluntaryContribution"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>

                                        <fo:table-row>
                                            <fo:table-cell padding-top="10pt" text-align="left">
                                                <fo:block>k. Interest On Voluntary Contribution P.F </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell padding-top="10pt" text-align="right">
                                                <fo:block><xsl:value-of select="interestOnVoluntaryContribution"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>


                                    </fo:table-body>
                                </fo:table>

                            </fo:list-item-body>
                        </fo:list-item>

                        <fo:list-item margin-top="10pt">
                            <fo:list-item-label end-indent="label-end()">
                                <fo:block>6. </fo:block>
                            </fo:list-item-label>
                            <fo:list-item-body start-indent="body-start()">
                                <fo:table>
                                    <fo:table-column column-width="70%"/>
                                    <fo:table-column column-width="30%"/>

                                    <fo:table-body>
                                        <fo:table-row>
                                            <fo:table-cell text-align="left">
                                                <fo:block>Total Contribution ( a to k ) </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell text-align="right">
                                                <fo:block><xsl:value-of select="totalContribution"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                    </fo:table-body>
                                </fo:table>

                            </fo:list-item-body>
                        </fo:list-item>


                        <fo:list-item margin-top="10pt">
                            <fo:list-item-label end-indent="label-end()">
                                <fo:block>7. </fo:block>
                            </fo:list-item-label>
                            <fo:list-item-body start-indent="body-start()">
                                <fo:table>
                                    <fo:table-column column-width="70%"/>
                                    <fo:table-column column-width="30%"/>

                                    <fo:table-body>
                                        <fo:table-row>
                                            <fo:table-cell text-align="left">
                                                <fo:block> Withdrawals on loan (N.R. ) </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell text-align="right">
                                                <fo:block><xsl:value-of select="loanWithdrawals"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                    </fo:table-body>
                                </fo:table>

                            </fo:list-item-body>
                        </fo:list-item>


                        <fo:list-item margin-top="10pt">
                            <fo:list-item-label end-indent="label-end()">
                                <fo:block>8. </fo:block>
                            </fo:list-item-label>
                            <fo:list-item-body start-indent="body-start()">
                                <fo:table>
                                    <fo:table-column column-width="70%"/>
                                    <fo:table-column column-width="30%"/>

                                    <fo:table-body>
                                        <fo:table-row>
                                            <fo:table-cell text-align="left">
                                                <fo:block>Net Credit to the account as on <xsl:value-of select="dueDate"/> </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell text-align="right">
                                                <fo:block><xsl:value-of select="netCredit"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                    </fo:table-body>
                                </fo:table>

                            </fo:list-item-body>
                        </fo:list-item>

                        <fo:list-item margin-top="10pt">
                            <fo:list-item-label end-indent="label-end()">
                                <fo:block>9. </fo:block>
                            </fo:list-item-label>
                            <fo:list-item-body start-indent="body-start()">
                                <fo:table>
                                    <fo:table-column column-width="70%"/>
                                    <fo:table-column column-width="30%"/>

                                    <fo:table-body>
                                        <fo:table-row>
                                            <fo:table-cell text-align="left">
                                                <fo:block>Date of Joining Service ( M&amp;M Ltd ) </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell text-align="right">
                                                <fo:block><xsl:value-of select="dateOfJoiningService"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                    </fo:table-body>
                                </fo:table>

                            </fo:list-item-body>
                        </fo:list-item>
                        <fo:list-item >
                            <fo:list-item-label end-indent="label-end()">
                                <fo:block>10. </fo:block>
                            </fo:list-item-label>
                            <fo:list-item-body start-indent="body-start()">
                                <fo:table>
                                    <fo:table-column column-width="70%"/>
                                    <fo:table-column column-width="30%"/>

                                    <fo:table-body>
                                        <fo:table-row>
                                            <fo:table-cell text-align="left">
                                                <fo:block>Date of Joining P. F. </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell text-align="right">
                                                <fo:block><xsl:value-of select="dateOfJoiningPf"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                    </fo:table-body>
                                </fo:table>

                            </fo:list-item-body>
                        </fo:list-item>
                        <fo:list-item>
                            <fo:list-item-label end-indent="label-end()">
                                <fo:block>11. </fo:block>
                            </fo:list-item-label>
                            <fo:list-item-body start-indent="body-start()">
                                <fo:table>
                                    <fo:table-column column-width="50%"/>
                                    <fo:table-column column-width="50%"/>

                                    <fo:table-body>
                                        <fo:table-row>
                                            <fo:table-cell text-align="left">
                                                <fo:block>Employees Pension Scheme A/C No </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell text-align="right">
                                                <fo:block><xsl:value-of select="epsNumber"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                    </fo:table-body>
                                </fo:table>

                            </fo:list-item-body>
                        </fo:list-item>
                        <fo:list-item>
                            <fo:list-item-label end-indent="label-end()">
                                <fo:block>12. </fo:block>
                            </fo:list-item-label>
                            <fo:list-item-body start-indent="body-start()">
                                <fo:table>
                                    <fo:table-column column-width="70%"/>
                                    <fo:table-column column-width="30%"/>

                                    <fo:table-body>
                                        <fo:table-row>
                                            <fo:table-cell text-align="left">
                                                <fo:block>Date of Leaving Service </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell text-align="right">
                                                <fo:block><xsl:value-of select="dateOfLeavingService"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                    </fo:table-body>
                                </fo:table>

                            </fo:list-item-body>
                        </fo:list-item>
                        <fo:list-item>
                            <fo:list-item-label end-indent="label-end()">
                                <fo:block>13. </fo:block>
                            </fo:list-item-label>
                            <fo:list-item-body start-indent="body-start()">
                                <fo:table>
                                    <fo:table-column column-width="70%"/>
                                    <fo:table-column column-width="30%"/>

                                    <fo:table-body>
                                        <fo:table-row>
                                            <fo:table-cell text-align="left">
                                                <fo:block>DOJ of Previous Employer </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell text-align="right">
                                                <fo:block><xsl:value-of select="dateOfJoiningPreviousEmployer"/></fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                    </fo:table-body>
                                </fo:table>

                            </fo:list-item-body>
                        </fo:list-item>

                    </fo:list-block>


                    <fo:table margin-top="30pt">
                        <fo:table-column column-width="50%"/>
                        <fo:table-column column-width="50%"/>

                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell text-align="left">
                                    <fo:block>User: <fo:inline><xsl:value-of select="createdBy"/></fo:inline> </fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block line-height="1.2" word-spacing="6pt" letter-spacing="1pt">
                                        <fo:block text-align="center">COREINTEGRA GLOBAL SERVICES PVT LIMITED</fo:block>
                                        <fo:block text-align="center">STAFF PROVIDENT FUND</fo:block>
                                        <fo:block text-align="center" margin-top="40pt">Authorized Signatory</fo:block>
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
