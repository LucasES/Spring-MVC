package br.com.spring.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.spring.mvc.dao.ProdutoDAO;
import br.com.spring.mvc.models.CarrinhoCompras;
import br.com.spring.mvc.models.CarrinhoItem;
import br.com.spring.mvc.models.Produto;
import br.com.spring.mvc.models.TipoPreco;

@Controller
@RequestMapping("/carrinho")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class CarrinhoComprasController {

	@Autowired
	private ProdutoDAO produtoDao;

	@Autowired
	private CarrinhoCompras carrinho;
	
	@RequestMapping("/add")
	public ModelAndView add(Long produtoId, TipoPreco tipo){
	    ModelAndView modelAndView = new ModelAndView("redirect:/produtos");
	    CarrinhoItem carrinhoItem = criaItem(produtoId, tipo);
	    
	    carrinho.add(carrinhoItem);
	    
	    return modelAndView;
	}

	private CarrinhoItem criaItem(Long produtoId, TipoPreco tipo){
	    Produto produto = produtoDao.find(produtoId);
	    CarrinhoItem carrinhoItem = new CarrinhoItem(produto, tipo);
	    return carrinhoItem;
	}
}
