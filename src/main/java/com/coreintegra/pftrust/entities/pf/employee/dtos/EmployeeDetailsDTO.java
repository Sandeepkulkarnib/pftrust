package com.coreintegra.pftrust.entities.pf.employee.dtos;

import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.pf.loan.dtos.LoanDetailsDTO;
import com.coreintegra.pftrust.entities.pf.settlement.dtos.SettlementDetailsDTO;
import com.coreintegra.pftrust.entities.pf.transferIn.dtos.TransferInDetailsDTO;

import java.util.List;
import java.util.Set;

public class EmployeeDetailsDTO {

    private BasicDetailsDTO basicDetailsDTO;
    private OtherDetailsDTO otherDetailsDTO;
    private List<LoanDetailsDTO> loanDetailsDTOS;
    private List<TransferInDetailsDTO> transferInDetailsDTOS;
    private List<SettlementDetailsDTO> settlementDetailsDTOS;
    private Set<Integer> annualStatementYears;

    public EmployeeDetailsDTO(BasicDetailsDTO basicDetailsDTO) {
        this.basicDetailsDTO = basicDetailsDTO;
    }

    public EmployeeDetailsDTO(BasicDetailsDTO basicDetailsDTO, OtherDetailsDTO otherDetailsDTO) {
        this.basicDetailsDTO = basicDetailsDTO;
        this.otherDetailsDTO = otherDetailsDTO;
    }

    public EmployeeDetailsDTO(BasicDetailsDTO basicDetailsDTO, OtherDetailsDTO otherDetailsDTO,
                              List<LoanDetailsDTO> loanDetailsDTOS) {
        this.basicDetailsDTO = basicDetailsDTO;
        this.otherDetailsDTO = otherDetailsDTO;
        this.loanDetailsDTOS = loanDetailsDTOS;
    }

    public EmployeeDetailsDTO(BasicDetailsDTO basicDetailsDTO, OtherDetailsDTO otherDetailsDTO,
                              List<LoanDetailsDTO> loanDetailsDTOS, List<TransferInDetailsDTO> transferInDetailsDTOS) {
        this.basicDetailsDTO = basicDetailsDTO;
        this.otherDetailsDTO = otherDetailsDTO;
        this.loanDetailsDTOS = loanDetailsDTOS;
        this.transferInDetailsDTOS = transferInDetailsDTOS;
    }

    public EmployeeDetailsDTO(BasicDetailsDTO basicDetailsDTO, OtherDetailsDTO otherDetailsDTO,
                              List<LoanDetailsDTO> loanDetailsDTOS, List<TransferInDetailsDTO> transferInDetailsDTOS,
                              List<SettlementDetailsDTO> settlementDetailsDTOS) {
        this.basicDetailsDTO = basicDetailsDTO;
        this.otherDetailsDTO = otherDetailsDTO;
        this.loanDetailsDTOS = loanDetailsDTOS;
        this.transferInDetailsDTOS = transferInDetailsDTOS;
        this.settlementDetailsDTOS = settlementDetailsDTOS;
    }

    public BasicDetailsDTO getBasicDetailsDTO() {
        return basicDetailsDTO;
    }

    public void setBasicDetailsDTO(BasicDetailsDTO basicDetailsDTO) {
        this.basicDetailsDTO = basicDetailsDTO;
    }

    public static EmployeeDetailsDTO build(Employee employee) {

        BasicDetailsDTO basicDetails = BasicDetailsDTO.build(employee);

        return new EmployeeDetailsDTO(basicDetails);
    }

    public OtherDetailsDTO getOtherDetailsDTO() {
        return otherDetailsDTO;
    }

    public void setOtherDetailsDTO(OtherDetailsDTO otherDetailsDTO) {
        this.otherDetailsDTO = otherDetailsDTO;
    }

    public List<LoanDetailsDTO> getLoanDetailsDTOS() {
        return loanDetailsDTOS;
    }

    public void setLoanDetailsDTOS(List<LoanDetailsDTO> loanDetailsDTOS) {
        this.loanDetailsDTOS = loanDetailsDTOS;
    }

    public List<TransferInDetailsDTO> getTransferInDetailsDTOS() {
        return transferInDetailsDTOS;
    }

    public void setTransferInDetailsDTOS(List<TransferInDetailsDTO> transferInDetailsDTOS) {
        this.transferInDetailsDTOS = transferInDetailsDTOS;
    }

    public List<SettlementDetailsDTO> getSettlementDetailsDTOS() {
        return settlementDetailsDTOS;
    }

    public void setSettlementDetailsDTOS(List<SettlementDetailsDTO> settlementDetailsDTOS) {
        this.settlementDetailsDTOS = settlementDetailsDTOS;
    }

    public Set<Integer> getAnnualStatementYears() {
        return annualStatementYears;
    }

    public void setAnnualStatementYears(Set<Integer> annualStatementYears) {
        this.annualStatementYears = annualStatementYears;
    }
}
