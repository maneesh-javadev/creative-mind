/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.nic.pes.lgd.bean;

//import java.io.Serializable;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author 
 */
@Entity
@Table(name = "operations_variables")
public class OperationsVariables implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "operation_code", insertable=false, updatable=false)
    private Integer operationCode;
    
    @Column(name = "variable")
    private String variable;
    
	@Column(name = "variable_value")
    private String variableValue;
    
    @Column(name = "isactive")
    private boolean isactive;
    
    @JoinColumn(name = "operation_code", referencedColumnName = "operation_code")
    @ManyToOne
    private Operations operations;
    
    
    public OperationsVariables() {
		super();
	}

	/**
	 * @return the operationCode
	 */
	public Integer getOperationCode() {
		return operationCode;
	}

	/**
	 * @param operationCode the operationCode to set
	 */
	public void setOperationCode(Integer operationCode) {
		this.operationCode = operationCode;
	}

// TODO Remove unused code found by UCDetector
//     public OperationsVariables(Integer id) {
// 		super();
// 		this.id = id;
// 	}
    

// TODO Remove unused code found by UCDetector
// 	public OperationsVariables(Integer id, Integer operation_code,
// 			boolean isactive, Operations operations) {
// 		super();
// 		this.id = id;
// 		this.operationCode = operation_code;
// 		this.isactive = isactive;
// 		this.setOperations(operations);
// 	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the operation_code
	 */
	public Integer getOperation_code() {
		return operationCode;
	}

	/**
	 * @param operation_code the operation_code to set
	 */
	public void setOperation_code(Integer operation_code) {
		this.operationCode = operation_code;
	}

	/**
	 * @return the variable
	 */
	public String getVariable() {
		return variable;
	}

	/**
	 * @param variable the variable to set
	 */
	public void setVariable(String variable) {
		this.variable = variable;
	}

	/**
	 * @return the variableValue
	 */
	public String getVariableValue() {
		return variableValue;
	}

// TODO Remove unused code found by UCDetector
// 	public OperationsVariables(Integer id, Integer operationCode,
// 			String variable, String variableValue, boolean isactive,
// 			Operations operations) {
// 		super();
// 		this.id = id;
// 		this.operationCode = operationCode;
// 		this.variable = variable;
// 		this.variableValue = variableValue;
// 		this.isactive = isactive;
// 		this.setOperations(operations);
// 	}

	/**
	 * @param variableValue the variableValue to set
	 */
	public void setVariableValue(String variableValue) {
		this.variableValue = variableValue;
	}

	/**
	 * @return the isactive
	 */
	public boolean isIsactive() {
		return isactive;
	}

	/**
	 * @param isactive the isactive to set
	 */
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public Operations getOperations() {
		return operations;
	}

	public void setOperations(Operations operations) {
		this.operations = operations;
	}
}