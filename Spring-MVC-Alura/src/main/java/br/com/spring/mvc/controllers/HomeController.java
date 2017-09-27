package br.com.spring.mvc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.spring.mvc.dao.ProdutoDAO;
import br.com.spring.mvc.models.Produto;

@Controller
public class HomeController {

    @Autowired
    private ProdutoDAO produtoDao;

    @RequestMapping("/")
    public ModelAndView index(){
        List<Produto> produtos = produtoDao.listar();
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("produtos", produtos);
        return modelAndView;
    }

}