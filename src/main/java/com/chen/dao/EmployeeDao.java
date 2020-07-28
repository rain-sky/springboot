package com.chen.dao;

import com.chen.pojo.Department;
import com.chen.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {

    private static Map<Integer, Employee> employees = null;
    //员工有所属的部门
    @Autowired
    private DepartmentDao departmentDao;

    static {
        employees = new HashMap<Integer, Employee>();
        employees.put(101, new Employee(1001, "小明", "763467851@qq.com", 0, new Department(101, "教学部")));
        employees.put(102, new Employee(1002, "小华", "216467851@qq.com", 1, new Department(102, "市场部")));
        employees.put(103, new Employee(1003, "小红", "216479851@qq.com", 0, new Department(101, "教学部")));
        employees.put(104, new Employee(1004, "小李", "216467851@qq.com", 1, new Department(102, "市场部")));
        employees.put(105, new Employee(1005, "小东", "364667851@qq.com", 0, new Department(103, "教研部")));
    }

    private static Integer initId = 1006;

    public void save(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(initId++);
            employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
            employees.put(employee.getId(), employee);
        }
    }

    public Collection<Employee> getAll () {
        return employees.values();
    }
//通过id查询员工
    public Employee getEmployeeById (Integer id){
        return employees.get(id);
    }
//删除员工通过id
    public void delete (Integer id){
        employees.remove(id);
    }

}