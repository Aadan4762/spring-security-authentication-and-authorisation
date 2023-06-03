package com.adan.controller;

import com.adan.dto.Commodity;
import com.adan.entity.UserInfo;
import com.adan.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commodities")
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/new")
    public String addNewUser(@RequestBody UserInfo userInfo){
        return commodityService.addUser(userInfo);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Commodity> getAllTheProducts() {
        return commodityService.getProducts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Commodity getProductById(@PathVariable int id) {
        return commodityService.getProduct(id);
    }
}
