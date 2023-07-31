package Net.AmarisT.EmployeesRequirement.Model;

import java.util.List;

public class RestApiJson {
	
	private String status;
	private List<Empleado> data;
	private String message;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Empleado> getData() {
		return data;
	}
	public void setData(List<Empleado> data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
