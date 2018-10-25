package br.com.dss.commerce.controller;


import br.com.dss.commerce.dto.Cart;
import br.com.dss.commerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController("/cart")
public class CommerceController {

    @Autowired
    private CartService cartService;

    @RequestMapping(method = RequestMethod.GET)
    public Cart retrieveRandomCart() {
        return cartService.getRandomCarts(100).get(0);
    }

    @RequestMapping(value = "/parallel-stream", method = RequestMethod.GET)
    public Cart retrieveRandomCartWithParallelStream() {
        return cartService.getRandomCartsWithParallelStream(100).get(0);
    }

    @RequestMapping(value = "/simple-threads", method = RequestMethod.GET)
    public Cart retrieveRandomCartWithSimpleThreads() {
        return cartService.getRandomCartsWithSimpleThreads(100).get(0);
    }

    @RequestMapping(value = "/thread-pool", method = RequestMethod.GET)
    public Cart retrieveRandomCartWithThreadPool() throws ExecutionException, InterruptedException {
        return cartService.getRandomCartsWithThreadPool(100).get(0);
    }

}
