package uz.msi.crmmarketboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import uz.msi.crmmarketboot.entity.Customer;
import uz.msi.crmmarketboot.repository.CustomerDao;

import java.sql.SQLException;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerDao customerDao;

    @ResponseBody
    @RequestMapping("/save")
    public Customer saveCustomer(@RequestBody Customer customer) {
        return customerDao.save(customer);
    }

    // pathvariables controller spring
    // request param
    // create method findByUsername with requestParam *!!! to git
    // create method deleteByUsername with pathVariable *!!! to git

    @ResponseBody
    @RequestMapping("/find-by-id")
    public Optional<Customer> findById(Long id) {
        return customerDao.findById(id);
    }

    @ResponseBody
    @RequestMapping("/update-customer")
    public Customer updateCustomer(Customer customer) {
        return customerDao.save(customer);
    }

    @ResponseBody
    @RequestMapping("/delete-customer")
    public Optional<Customer> deleteCustomer(Long id) {
        return customerDao.deleteById(id);
    }
}