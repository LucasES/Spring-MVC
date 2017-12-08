package br.com.spring.mvc.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.spring.mvc.builders.ProdutoBuilder;
import br.com.spring.mvc.config.DataSourceConfigurationTest;
import br.com.spring.mvc.config.JPAConfiguration;
import br.com.spring.mvc.models.Produto;
import br.com.spring.mvc.models.TipoPreco;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JPAConfiguration.class, ProdutoDAO.class, DataSourceConfigurationTest.class})
@ActiveProfiles("test")
public class ProdutoDAOTest {

	@Autowired
	private ProdutoDAO produtoDao;
	
	@Test
	@Transactional
	public void deveSomarTodosOsPrecosPorTipoLivro() {

	    List<Produto> livrosImpressos = ProdutoBuilder.newProduto(TipoPreco.IMPRESSO, BigDecimal.TEN).more(3).buildAll();
	    List<Produto> livrosEbook = ProdutoBuilder.newProduto(TipoPreco.EBOOK, BigDecimal.TEN).more(3).buildAll();

	    livrosImpressos.stream().forEach(produtoDao::gravar);
	    livrosEbook.stream().forEach(produtoDao::gravar);
	    
	    BigDecimal valor = produtoDao.somaPrecoPorTipoPreco(TipoPreco.EBOOK);
	    Assert.assertEquals(new BigDecimal(40).setScale(2), valor);
	}
}
