package com.adan.service;

import com.adan.dto.Commodity;
import com.adan.entity.UserInfo;
import com.adan.repository.UserInfoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CommodityService {

    List<Commodity> commodityList = null;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void loadProductsFromDB() {
        commodityList = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> Commodity.builder()
                        .commodityId(i)
                        .name("commodity " + i)
                        .qty(new Random().nextInt(10))
                        .price(new Random().nextInt(5000)).build()
                ).collect(Collectors.toList());
    }


    public List<Commodity> getProducts() {
        return commodityList;
    }

    public Commodity getProduct(int id) {
        return commodityList.stream()
                .filter(commodity -> commodity.getCommodityId() == id)
                .findAny()
                .orElseThrow(() -> new RuntimeException("commodity " + id + " not found"));
    }


    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return "user added to system ";
    }
}
