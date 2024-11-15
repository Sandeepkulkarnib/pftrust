package com.coreintegra.pftrust.entities.pf.query;

import com.coreintegra.pftrust.aop.TenantContext;
import com.coreintegra.pftrust.entities.base.BaseAuditingEntity;
import com.coreintegra.pftrust.entities.pf.Bank;
import com.coreintegra.pftrust.entities.pf.PaymentMode;
import com.coreintegra.pftrust.entities.pf.SAPStatus;
import com.coreintegra.pftrust.entities.pf.employee.Employee;
import com.coreintegra.pftrust.entities.tenant.Tenant;
import com.coreintegra.pftrust.util.DataUtil;
import com.coreintegra.pftrust.util.FinancialYearAndMonth;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.poi.ss.usermodel.DateUtil;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.json.JSONObject;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@FilterDef(name = "tenantFilter", parameters = {@ParamDef(name = "tenantId", type = "long")})
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public class Query extends BaseAuditingEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
    @JsonIgnore
    private Tenant tenant;
    
    private String query;

    @ManyToOne
    @JsonIgnore
    private Employee employee;

    @OneToOne
    @JsonIgnore
    private QueryType queryType;
    
    @OneToOne
    @JsonIgnore
    private QueryPriority queryPriority;

    public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public QueryType getQueryType() {
		return queryType;
	}

	public void setQueryType(QueryType queryType) {
		this.queryType = queryType;
	}

	public QueryPriority getQueryPriority() {
		return queryPriority;
	}

	public void setQueryPriority(QueryPriority queryPriority) {
		this.queryPriority = queryPriority;
	}

	public QueryStatus getQueryStatus() {
		return queryStatus;
	}

	public void setQueryStatus(QueryStatus queryStatus) {
		this.queryStatus = queryStatus;
	}

	public List<QueryDocument> getQueryDocuments() {
		return queryDocuments;
	}

	public void setQueryDocuments(List<QueryDocument> queryDocuments) {
		this.queryDocuments = queryDocuments;
	}


	@ManyToOne
    private QueryStatus queryStatus;

    @OneToMany(mappedBy = "query")
    private List<QueryDocument> queryDocuments;
    
    @PrePersist
    public void prePersist(){
        tenant = TenantContext.getCurrentTenant();
        setIsActive(true);
        setEntityId(UUID.randomUUID().toString());
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public Employee getEmployee() {
        return employee;
    }
      
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

 
    public static Query build(JSONObject jsonObject, Query query) throws ParseException {
    	
    	query.setCreatedAt(new Date());
    	query.setUpdatedAt(new Date());
    	
        return query;

    }

}
