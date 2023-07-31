package Net.AmarisT.EmployeesRequirement.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.util.StringUtils;

import Net.AmarisT.EmployeesRequirement.Model.Empleado;
import Net.AmarisT.EmployeesRequirement.Model.RestApiJson;
import Net.AmarisT.EmployeesRequirement.Model.RestApiJsonID;

@Controller
@RequestMapping(value = "/Employees")
public class EmployeesController {

	@GetMapping(value = "/index")
	public String Home(Model model) {

		return "home";
	}

	// @GetMapping("/index")
	@GetMapping(value = "/list")
	public String mostrarIndex(Model model) {

		try {
			RestApiJson control = restApi();
			List<Empleado> empl = control.getData();
			model.addAttribute("empleado", empl);
			return "employees/list";
		} catch (Exception e) {
			model.addAttribute("listErrorMessage",e.getMessage());
			System.out.println(e.getMessage());
			return "employees/error";
		}
	}

//	@PostMapping(value = "/EmpID")
//	public void EmployeeForm(@PathVariable("id") int idEmploye, Model model) {
//		
//		String nuev = EmployeID(idEmploye, model);
//	}

	@PostMapping(value = "/EmpID")
	public String EmployeID(@RequestParam("idbut") String idEmploye, Model model) {

		try {
			if (String.valueOf(idEmploye).length() == 0) {
				return mostrarIndex(model);
			} else {
				RestApiJsonID control = restApiID(Integer.parseInt(idEmploye));
				System.out.println(control.getData().getEmployee_name());
				model.addAttribute("idEmploye", control);
				double salario = SalarioAnual(control.getData().getEmployee_salary());
				model.addAttribute("SalaryA", salario);
				return "employees/empleado";
			}
		} catch (Exception e) {
			model.addAttribute("listErrorMessage",e.getMessage());
			return "employees/error";
		}
	}

	private RestApiJson restApi() {

		RestTemplate restTemp = new RestTemplate();
		RestApiJson response = restTemp.getForObject("https://dummy.restapiexample.com/api/v1/employees",
				RestApiJson.class);
		return response;
	}

	private RestApiJsonID restApiID(int id) {

		RestTemplate restTemp = new RestTemplate();
		String url = "https://dummy.restapiexample.com/api/v1/employee/" + id;
		System.out.println(url);
		RestApiJsonID response = restTemp.getForObject(url, RestApiJsonID.class);
		return response;
	}

	private double SalarioAnual(double salarioM) {

		double salarioAn = salarioM * 12;
		return salarioAn;

	}
}
