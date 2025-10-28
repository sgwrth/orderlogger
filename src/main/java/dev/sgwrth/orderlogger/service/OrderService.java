package dev.sgwrth.orderlogger.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import dev.sgwrth.orderlogger.dto.PlaceOrderDto;
import dev.sgwrth.orderlogger.entity.Article;
import dev.sgwrth.orderlogger.entity.Customer;
import dev.sgwrth.orderlogger.entity.Order;
import dev.sgwrth.orderlogger.repository.ArticleRepository;
import dev.sgwrth.orderlogger.repository.CustomerRepository;
import dev.sgwrth.orderlogger.repository.OrderRepository;

public interface OrderService {
	@Service
	class OrderServiceImpl implements OrderService {
		
		private ArticleRepository articleRepository;
		private CustomerRepository customerRepository;
		private OrderRepository orderRepository;
		
		OrderServiceImpl(
				ArticleRepository articleRepository,
				CustomerRepository customerRepository,
				OrderRepository orderRepository
		) {
			this.articleRepository = articleRepository;
			this.customerRepository = customerRepository;
			this.orderRepository = orderRepository;
		}
		
		@Override
		public List<Order> getOrders() {
			return this.orderRepository.findAll();
		}
		
		@Override
		public void saveNewOrder(PlaceOrderDto placeOrderDto) {
			final var newOrder = new Order();
			
			// Set order customer and date.
			final var customer = this.customerRepository
					.getReferenceById(placeOrderDto.customerId());
			newOrder.setCustomer(customer);
			newOrder.setOrderDate(LocalDateTime.now());
			
			// Set order articles.
			final var articlesInOrder = new HashSet<Article>();
			placeOrderDto.articleIds()
				.forEach(articleId -> {
					articlesInOrder.add(this.articleRepository
							.getReferenceById(articleId));
				});
			newOrder.setArticles(articlesInOrder);
		
			/**
			 * Add order to articles, but don't save each article!  Saving is
			 * not necessary since the relationship is owned by 'Order,' and
			 * updating both the order and the articles, followed by saving the
			 * new order, handles the process of persisting the data in the
			 * 'orders_articles' join table.
			 */
			placeOrderDto.articleIds()
				.forEach(articleId -> {
					final var article = this.articleRepository
							.getReferenceById(articleId);
					final var articleOrders = article.getOrders();
					articleOrders.add(newOrder);
				});
			
			// Save new order.
			this.orderRepository.save(newOrder);
		}
	}
	
	List<Order> getOrders();
	
	void saveNewOrder(PlaceOrderDto placeOrderDto);
}
