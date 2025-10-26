package dev.sgwrth.orderlogger.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "articles")
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;

	@ManyToMany
	@JoinTable(
			name = "articles_orders",
			joinColumns = @JoinColumn(name = "arcticle_id", nullable = false),
			inverseJoinColumns = @JoinColumn(name = "order_id", nullable = false)
	)
	private Set<Order> orders = new HashSet<>();
	
	private String name;
	private Long priceInCents;
}
